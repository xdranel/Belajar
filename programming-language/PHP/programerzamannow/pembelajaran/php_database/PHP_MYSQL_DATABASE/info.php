<?php

/**

  Check Folder/File di => /../../programerzamannow/pembelajaran/php-database/
  Check Folder/File di DataGrip

=================
List Pembelajaran
# PDO
# Proses
# Notes
# SQL Query
# Prepare Statement
# Fetch Data
# Auto Increment
# Database Transaction
# Repository Pattern

# PDO(Php Data Object)

- Sebuah Spesifikasi Interface Standard Komunikasi antara PHP dengan Database

- PDO merupakan sebuah spesifikasi, sehingga dibutuhkan implpementasinya atau extensionnya untuk mengaktifkan PDO

- PDO menyediakan abstraction layer yang sama untuk semua database
- PDO mendukung banyak database seperti MySQL, PostgreSQL, SQLite, Oracle, SQL Server, dan banyak lagi
- Yang berarti kode PDO yang sama bisa digunakan untuk semua database yang mendukung PDO
  Dengan begitu membuat PDO menjadi Flexible dibandingkan mengunakan function bawaan dari database tersebut


# Proses

  Urutan Cara Kerja PDO :
  Kode Program --Call--> PDO --Call--> PDO Driver --Call--> Database

  Untuk mengecek PDO Support bisa menggunakan phpinfo();


# Notes
- Perlu di ingat pada umumnya proses DDL tidak dilakukan di PHP, biasanya sebagai contoh pada Laravel biasanya
  ada Migration Tool
- maka dari itu dikarenakan masih belajar PHP dan Migration Toolnya belum ada biasannya DDL di simpan dalam script SQL

- Biasanya pada PHP nya lebih berfokus terhadap DML, yang mana di dalamnya terdapat function untuk mengirimkan
  perintah SQL yang tidak membutuhkan result data



# Jika sudah terkoneksi ke database kita bisa mengirimkan perintah SQL ke database dari aplikasi PHP kita
  menggunakan PDO Statement


# SQL Query

==============
@1 Execute Sql
> Cek File TestExecute.php

- Untuk mengirimkan perintah SQL kita bisa menggunakan function execute(sql)
- Function exec(sql) bisa di gunakan untuk semua jenis SQL yang tidak membutuhkan result data atay nilai return


============
@2 Query Sql
> Cek File TestQuery.php

- Untuk mengirimkan perintah SQL kita bisa menggunakan function query(sql)


================
@3 Sql Injection

> Cek File TestSqlInjection.php

=> SQL dengan Parameter
- Saat membuat aplikasai tidak mungkin akan melakukan hardcore perintah SQL di kode PHP biasanya kita akan menerima i
nput data dari user, lalu membuat perintah SQL dari input user dan mengirimnya mengunakan perintah sql

=> SQL Injection
- Merupakan sebuah teknik yang menyalahgunakan sebuah celah keamanan yang terjadi dalam lapisan basis data sebuah aplikasi
-biasanya, SQL Injection dilakukan dengan mengirim input dari user dengan perintah yang salah, sehingga menyebabkan
SQL yang dibuat menjadi tidak VALID


$sql = "SELECT * FROM admin WHERE username = '$username' AND password = '$password';";
- Query seperti ini tidak direkomendasikan
- jika ada orang yang mencoba memanipulasi input user untuk mengirimkan perintah SQL yang salah maka akan menyebabkan SQL Injection

Contoh :
$username = "admin'; #";
$password = "admin";
$sql = "SELECT * FROM admin WHERE username = '$username' AND password = '$password';";

- pada baris username dan password terdapat karakter '; #' jika sqk di print out mengunakan echo hasilnya
SELECT * FROM admin WHERE username = 'admin'; #' AND password = 'admin'

- bisa dilihat dari hasil echo tersebut pada query username menjadi 'admin'; yang menyebabkan query tersebut hanya sampai karakter ';'
dan pada karakter '#' membuat perintah setelah karakter tersebut menjadi kommentar dan tidak dijalankan oleh database


=> Solusi
1. Jangan membuat query SQL secara manual dengan mengabungkan String secara bulat-bulat karena function execute() atau query() tidak bisa
   menangani celah SQL Injection
2. Direkomendasikan ketika mengunakan function execute() atau query() jika tidak butuh paramater dari input USER ketika membuat perintah SQL


3. Mengunakan Function quote();
Contoh
$username = $connection->quote("admin'; #");
$password = $connection->quote("admin");
$sql = "SELECT * FROM admin WHERE username = $username AND password = $password;";

Jika di print out mengunakan echo hasilnya
SELECT * FROM admin WHERE username = 'admin\'; #' AND password = 'admin';

- Tetapi tetap cara ini tidak direkomendasikan untuk di gunakan


4. Mengunakan Function prepare();


====================
# Prepare Statement

> Cek File TestPrepare.php, TestPrepareIndex.php,
- Dengan mengunakan function prepare() akan menghasilkan PDOStatement, dimana kita bisa melakukan binding parameter ke perintah sql yang kita buat

Cara Buat -> :NamaParameter
$sql = "SELECT * FROM admin WHERE username = :param1 AND password = :param2";

- dengan begini SQL Injection yang di lakukan mengunakan parameter yang salah bisa di tangani oleh PDOStatement


=> Binding Parameter
Cara Binding Parameter :

$sql = "SELECT * FROM admin WHERE username = :username AND password = :password";
$statment = $connection->prepare($sql);

$statment->bindParam("username", $username);
$statment->bindParam("password", $password);
$statment->execute();


=> Binding Parameter dengan Index
Cara Binding Parameter :

$sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
$statment = $connection->prepare($sql);

$statment->bindParam(1, $username);
$statment->bindParam(2, $password);

- paramnya mengunakan angka 1, 2, 3, dst


=> Binding Parameter di execute()
Cara Binding Parameter :

$sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
$statment = $connection->prepare($sql);

$statment->execute([$username, $password]);


=> Binding Parameter dengan query lainnya

Contoh 1 :
$sql = "INSERT INTO admin(username, password) VALUES(:username, :password)";
$statment = $connection->prepare($sql);

$statment->bindParam("username", $username);
$statment->bindParam("password", $password);
$statment->execute();

Contoh 2 :
$sql = "INSERT INTO admin(username, password) VALUES(?, ?)";
$statment = $connection->prepare($sql);

$statment->execute([$username, $password]);


============
# Fetch Data

> Cek File TestFetch.php, TestFetchAll.php

- function fetch() digunakan untuk mengambil satu data dari hasil query, jika data pertama tidak ditemukan maka akan mengambil data selanjutnya
dan seterusnya. jika ternyata sudah tidak ada data lagi maka akan mengembalikan false

- function fetchAll() digunakan untuk mengambil semua data dari hasil query



================
# Auto Increment
> Cek File TestAutoIncrement.php

- function lastInsertId() digunakan untuk mengambil id terakhir dari tabel yang di insert


=====================
# Database Transaction
> Cek File TestTransaction.php, TestTransactionRollback.php

Alur Transaksi :
1. beginTransaction()
2. query()
3. commit() atau rollback()


====================
# Repository Pattern

> Cek Folder simple_database
> Cek File TestRepository.php

- Pattern Repo biasanya digunakan sebagai jembatan antar business logic aplikasi dengan semua perintah SQL ke Database
- Jadi semua perintah SQL akan ditulis di Repository, sedangkan kode business logic program hanya cukup mengunakan
Repository tersebut


@ Diagram Repository Pattern

 Business Logic --Call--> Repository --Use--> Entity/Model
                              ↓
                             Impl
                              ↓
                     Repository Implementation --Call--> Database






















*/
