# TA Preparation Pack — Predictive Analytics & Environmental Intelligence

> **Purpose:** Dokumen handoff untuk membawa seluruh konteks, progress, keputusan sementara, pertanyaan terbuka, dan workflow TA ke akun ChatGPT/Codex lain agar pekerjaan bisa dilanjutkan tanpa mengulang dari awal.
>
> **Status:** DRAFT / belum final. Jangan menganggap judul, dataset, lokasi, target, atau model sudah final sebelum divalidasi melalui literatur dan bimbingan dosen.

---

# 1. Identitas & Konteks TA

- Status: Mahasiswa Informatika, semester 7
- Tahap: Persiapan proposal TA
- Kelompok/topik penelitian:
  **PREDICTIVE ANALYTICS & ENVIRONMENTAL INTELLIGENCE**
- Area yang dipilih:
  **Environmental Intelligence → Air Quality / PM2.5**
- Gaya penelitian yang diinginkan:
  - manageable untuk mahasiswa
  - menggunakan ML yang relatif sederhana
  - tidak terlalu kompleks
  - tetap punya dasar akademik dan research gap yang jelas
  - dapat dikerjakan dengan CPU untuk dataset tabular skala sedang
- Model awal:
  **Random Forest**
- Model tambahan yang masih opsional:
  **XGBoost**, hanya jika literature review menunjukkan alasan akademik yang jelas dan scope tetap manageable.

---

# 2. Draft Judul Saat Ini

## Draft

> **“Prediksi Kategori Kualitas Udara Berdasarkan Parameter Meteorologi Menggunakan Random Forest”**

### IMPORTANT

Judul di atas **MASIH DRAFT**.

Jangan langsung menganggap:
- target final sudah pasti kategori kualitas udara,
- PM2.5 sudah pasti dipakai sebagai target,
- lokasi sudah pasti,
- resolusi data sudah pasti,
- Random Forest pasti menjadi satu-satunya model,
- XGBoost pasti ditambahkan.

Semua itu masih perlu dipastikan dari:
1. teori PM2.5 dan kategori kualitas udara,
2. standar/BMKG,
3. literature review,
4. research gap,
5. ketersediaan dataset,
6. arahan dosen pembimbing.

---

# 3. Ide Dasar Penelitian

Ide awal:

```text
Data Meteorologi
(temperature, humidity, wind speed, pressure,
rainfall, dll.)
              ↓
        Random Forest
              ↓
     Kategori Kualitas Udara
```

Fokus utama adalah melihat apakah parameter meteorologi dapat digunakan untuk memprediksi kualitas udara/PM2.5 atau kategori kualitas udara.

---

# 4. Kenapa Random Forest?

Random Forest dipilih sebagai kandidat awal karena:

- relatif mudah dipahami dibanding deep learning,
- cocok untuk data tabular,
- dapat menangani hubungan nonlinear,
- relatif robust,
- tidak membutuhkan GPU untuk dataset tabular yang tidak terlalu besar,
- dapat menghasilkan feature importance,
- cocok sebagai baseline penelitian ML.

Namun alasan final pemilihan Random Forest harus didukung oleh literature review.

**Jangan menulis “Random Forest adalah metode terbaik”.**

Gunakan framing akademik seperti:

> Random Forest dipilih karena sesuai untuk data tabular dengan hubungan nonlinear dan dapat memberikan informasi mengenai kontribusi fitur, serta telah digunakan pada penelitian prediksi kualitas udara/PM2.5 sebelumnya.

---

# 5. Kandidat Data

Sumber data yang pernah dipertimbangkan:

## 5.1 BMKG + SACA&D

Kandidat utama.

Potensi:
- data meteorologi,
- data kualitas udara,
- data stasiun,
- sumber resmi/terpercaya.

Perlu dicek:
- apakah PM2.5 tersedia untuk lokasi yang dipilih,
- resolusi data,
- kelengkapan data,
- periode minimal 3 tahun,
- format dan cara akses,
- kemungkinan missing values.

## 5.2 Satu Data Jakarta + BMKG

Alternatif jika lokasi Jakarta dipilih.

Namun Jakarta sudah cukup banyak diteliti, sehingga perlu literature review lebih hati-hati.

## 5.3 OpenAQ + Data Meteorologi

Alternatif lain.

Perlu diperiksa:
- station coverage,
- temporal coverage,
- kecocokan timestamp,
- kelengkapan PM2.5,
- cara menggabungkan data dengan meteorologi.

## 5.4 Kaggle

Boleh untuk:
- latihan,
- prototyping,
- eksperimen awal.

Tetapi dataset Kaggle **jangan langsung dijadikan dataset utama TA** sebelum provenance/source asli jelas.

Dataset synthetic juga jangan digunakan sebagai dataset utama penelitian.

---

# 6. Constraint Data dari Bimbingan

Arahan dosen yang sudah didapat:

- Data dapat menggunakan **3 tahun**
- Fokus pada **1 daerah/region**
- Cari teori PM2.5
- Pelajari bagaimana BMKG melakukan kategorisasi
- Review paper sebelumnya agar penelitian tidak sama
- Jelaskan alasan pemilihan
- Cari jurnal
- Jika ada paper yang sangat mirip:
  - tambahkan karakteristik sampling,
  - ubah/diferensiasikan aspek penelitian yang masuk akal,
  - atau pertimbangkan XGBoost.
- Belum ada keputusan final tentang judul.

---

# 7. Catatan Bimbingan Terbaru

Berikut inti feedback dosen:

> “mencari teori dari PM2.5 apakah lebih baik untuk dijadikan category/input/parameter tetapi melalui (seperti bagaimana BMKG melakukannya) atau tetap dijadikan sebagai target utama”

Interpretasi praktis:

Dosen ingin kita menentukan posisi PM2.5 secara akademik:

1. PM2.5 sebagai **target regression**
2. PM2.5 sebagai dasar pembentukan **category/classification target**
3. PM2.5 sebagai **input feature**
4. atau kombinasi yang benar-benar punya alasan ilmiah.

Feedback berikutnya:

> “melihat paper sebelumnya agar tidak terjadi penelitian yang sama atau bisa dijadikan sebagai acuan”

Artinya literature review harus dilakukan sebelum mengunci desain.

Feedback:

> “alasan pemilihan”

Kita harus bisa menjelaskan alasan:
- memilih PM2.5,
- memilih lokasi,
- memilih periode,
- memilih resolusi,
- memilih fitur,
- memilih Random Forest,
- memilih classification/regression,
- dan bila ada model kedua, alasan pemilihannya.

Feedback:

> “cari jurnal”

Target awal:
**8–15 paper yang sangat relevan**, bukan sekadar paper tentang ML secara umum.

Feedback:

> “jika ada jurnal/paper yang sama mungkin bisa menambah karakteristik sampling dsbnya atau bisa ditambahkan XGBoost”

Artinya XGBoost bukan wajib.

Jangan menambahkan XGBoost hanya supaya penelitian terlihat lebih rumit.

Feedback:

> “untuk data bisa menggunakan 3 tahun dan untuk daerahnya fokuskan saja ke 1 daerah”

Ini sudah cukup jelas sebagai constraint awal:
- ±3 tahun data
- 1 region

---

# 8. Masalah Terpenting: Peran PM2.5

Ada tiga desain utama.

## A. PM2.5 sebagai target regression

```text
Temperature
Humidity
Wind Speed
Pressure
Rainfall
       ↓
Random Forest Regressor
       ↓
PM2.5 concentration
```

Output:

```text
PM2.5 = 37.4 µg/m³
```

Evaluasi:
- MAE
- RMSE
- R²
- mungkin MAPE jika secara metodologis sesuai.

---

## B. PM2.5 sebagai dasar target classification

```text
Temperature
Humidity
Wind Speed
Pressure
Rainfall
       ↓
Random Forest Classifier
       ↓
PM2.5 / Air Quality Category
```

Contoh:

```text
0–15.5       → Baik
15.6–55.4    → Sedang
55.5–150.4   → Tidak Sehat
150.5–250.4  → Sangat Tidak Sehat
>250.5       → Berbahaya
```

**PERHATIAN:**
Range di atas harus diverifikasi lagi terhadap sumber/standar yang digunakan pada penelitian.

Jika category dibuat dari PM2.5 aktual pada timestamp yang sama, jangan memasukkan PM2.5 aktual sebagai input feature yang sama-sama berada pada timestamp tersebut.

Jika:

```text
PM2.5 → Category
```

dan PM2.5 juga diberikan sebagai feature:

```text
PM2.5 + Meteorology → Category
```

maka model dapat hanya belajar threshold PM2.5.

Ini dapat membuat penelitian menjadi trivial atau mengalami leakage-like target construction.

---

## C. PM2.5 sebagai input

Misalnya:

```text
PM2.5
Temperature
Humidity
Wind Speed
Pressure
       ↓
Random Forest
       ↓
Future Air Quality Category
```

Desain seperti ini baru masuk akal jika target adalah **future condition**, misalnya:

```text
PM2.5(t)
Meteorology(t)
       ↓
Predict Category(t+1)
```

atau horizon lain.

Jika target category dibuat dari PM2.5 pada waktu yang sama, desain ini berisiko menjadi pemetaan langsung.

---

# 9. Hal Penting Tentang BMKG

BMKG memiliki halaman/materi PM2.5 dan kategorisasi kualitas udara.

Salah satu sumber BMKG yang telah ditemukan mencantumkan range:

- 0–15.5 µg/m³ → Baik
- 15.6–55.4 → Sedang
- 55.5–150.4 → Tidak Sehat
- 150.5–250.4 → Sangat Tidak Sehat
- >250.5 → Berbahaya

PM2.5 adalah particulate matter dengan diameter aerodinamis ≤ 2.5 µm.

**Namun jangan langsung menggunakan range tersebut dalam skripsi tanpa verifikasi ulang.**

Ada sumber BMKG lain yang menjelaskan kategori hourly PM2.5/Jakarta dengan range:

- 0–15 → Good
- 16–65 → Moderate
- 66–150 → Unhealthy
- 151–250 → Very Unhealthy
- >250 → Hazardous

Perbedaan ini menunjukkan bahwa kita harus menentukan:

- standar/aturan yang dipakai,
- sumber resmi,
- periode,
- resolusi,
- apakah yang dimaksud PM2.5 category atau ISPU,
- dan bagaimana transformasi target dilakukan.

Jangan mencampur dua skema kategori tanpa dasar.

---

# 10. Literature Review yang Sudah Ditemukan

Berikut paper/sumber penting yang sudah ditemukan.

## 10.1 IPB — Prediksi PM2.5 DKI Jakarta menggunakan Random Forest

Judul:

> “Pemodelan Prediksi Konsentrasi PM2.5 di DKI Jakarta menggunakan Random Forest berbasis Variabel Meteorologi dan Musim”

Karakteristik:
- DKI Jakarta
- 5 SPKU
- hourly data
- 2021–2025
- Random Forest
- meteorological + seasonal features
- temperature dan wind speed penting
- menggunakan lag/rolling mean
- R² sekitar 0.64–0.80 untuk horizon 1 jam
- performa menurun untuk horizon 24/72 jam.

**Implikasi untuk penelitian kita:**

Ini sangat dekat dengan:

```text
PM2.5 + Meteorology + RF + Jakarta
```

Jadi jangan sekadar mengulang desain tersebut.

---

## 10.2 Procedia Computer Science 2025 — Jakarta ISPU Classification

Judul:

> “Comparative Analysis of XGBoost, Random Forest, and Logistic Regression for Classifying Jakarta’s Air Pollution Index (ISPU)”

Karakteristik:
- Jakarta
- 1,367 data
- 2021–2024
- weather + air quality
- classification
- membandingkan:
  - Logistic Regression
  - Random Forest
  - XGBoost
- menggunakan feature selection scenarios.

**Implikasi:**

Jika penelitian kita:

```text
Jakarta
Meteorology
ISPU/PM2.5 Category
RF vs XGBoost
```

maka kemungkinan sangat mirip.

Jangan memilih kombinasi tersebut tanpa research gap yang jelas.

---

## 10.3 South Tangerang — Random Forest + Meteorological Factors

Paper:

> “Analysis of the Impact of Meteorological Factors on Predicting Air Quality in South Tangerang City using Random Forest Method”

Fokus:
- pengaruh faktor meteorologi,
- prediksi kualitas udara,
- Random Forest,
- skenario dengan dan tanpa meteorological variables.

**Implikasi:**

South Tangerang juga bukan otomatis “unik”.

---

## 10.4 BMKG Journal — Extreme PM2.5 Indonesia

Judul:

> “Spatio-Temporal Dynamics of Extreme PM2.5 in Indonesia: A Weather-Based Hybrid Modeling Approach”

Karakteristik:
- beberapa kota Indonesia,
- hourly PM2.5,
- temperature,
- dew point,
- wind speed,
- precipitation,
- surface pressure,
- membandingkan MLR, Random Forest, XGBoost,
- XGBoost mengungguli RF pada eksperimen tersebut,
- RF tetap menawarkan kombinasi interpretability/performance.

**Implikasi:**

Ini bisa menjadi acuan metodologi model comparison.

---

## 10.5 Pontianak — Ensemble ML

Judul:

> “Prediction of PM2.5 Concentration Using Ensemble Machine Learning in Pontianak City”

Karakteristik:
- hourly PM2.5,
- Aug 2024–Aug 2025,
- temperature,
- relative humidity,
- atmospheric pressure,
- RF + XGBoost.

**Implikasi:**

Kombinasi RF + XGBoost + meteorologi juga sudah digunakan di kota lain.

---

## 10.6 Sulawesi — Process-Informed ML

Judul:

> “Process-informed machine learning for interpretable air-quality prediction in tropical coastal cities of Sulawesi, Indonesia”

Lokasi:
- Makassar
- Kendari
- Gorontalo

Karakteristik:
- 2021
- RF/XGBoost
- multiscale meteorological feature engineering
- SHAP
- process-informed ML.

**Implikasi:**

Novelty tidak bisa hanya berupa “pakai RF/XGB di kota Indonesia”.

---

## 10.7 Jakarta — RF PM2.5 2015–2024

Judul:

> “PM2.5 Concentration Prediction Model in Jakarta Area Using Random Forest Algorithm”

Karakteristik:
- 2015–2024
- Random Forest
- historical air quality
- MAE ≈ 14.44
- RMSE ≈ 18.75
- R² ≈ 0.61

**Implikasi:**

Jakarta + RF + PM2.5 prediction sudah sangat banyak.

---

## 10.8 Kemayoran — XGBoost

Judul:

> “Prediction Analysis of PM2.5 Concentration Based on Temperature Variables Using XGBoost Algorithm”

Karakteristik:
- Kemayoran, Central Jakarta
- Jan 1–Feb 12 2017
- XGBoost
- temperature sebagai variabel utama.

---

## 10.9 Tangerang — RF vs CatBoost

Judul:

> “Comparative Analysis of the Performance of Random Forest and CatBoost for Air Quality Prediction Based on Meteorological Factor”

Karakteristik:
- Tangerang
- RF vs CatBoost
- skenario dengan/tanpa meteorological factors.

---

## 10.10 Palembang — RF vs CatBoost untuk Kategori PM2.5

Judul:

> “PERBANDINGAN RANDOM FOREST DAN CATBOOST UNTUK KLASIFIKASI KUALITAS UDARA BERBASIS PM2.5”

Karakteristik:
- Palembang
- BMKG South Sumatra
- 2021–2025
- monthly PM2.5
- 60 monthly observations
- kemudian dibuat menjadi 1,796 estimated daily observations melalui linear interpolation
- PM2.5 menjadi dasar category
- meteorological variables menjadi predictors
- RF vs CatBoost.

**Catatan metodologis penting:**

Data hasil interpolation bukan pengukuran langsung. Hal ini harus diperhatikan jika paper tersebut dijadikan referensi.

---

## 10.11 Jakarta & Surabaya — Air Quality Index ML

Judul:

> “Reconstructing air quality index in metropolitan cities via predictive machine learning approach”

Karakteristik:
- Jakarta
- Surabaya
- beberapa algoritma ML
- XGBoost memiliki performa tinggi
- SHAP digunakan.

---

## 10.12 UGM — PM2.5 Estimation via Sentinel-5P + RF

Karakteristik:
- DKI Jakarta
- Random Forest
- Sentinel-5P
- meteorological factors
- pollutants/gases
- R² ≈ 0.793
- RMSE ≈ 8.28 µg/m³.

**Implikasi:**

RF + Jakarta + PM2.5 juga sudah dilakukan dengan remote sensing.

---

# 11. Kesimpulan Literature Review Sementara

Kesimpulan sementara:

> Penelitian “Random Forest + meteorological variables + PM2.5/air quality + Jakarta” sudah sangat banyak.

Karena itu:

**Jangan mengunci penelitian pada Jakarta hanya karena datanya mudah.**

Dan:

**Jangan menambahkan XGBoost hanya untuk membuat penelitian terlihat lebih baru.**

Novelty sebaiknya berasal dari gap yang benar-benar ditemukan, misalnya:

- lokasi yang belum banyak diteliti,
- target yang berbeda,
- temporal resolution yang berbeda,
- prediction horizon,
- feature engineering,
- sampling/station selection,
- handling missing data,
- class imbalance,
- interpretable analysis,
- atau perbandingan model yang memang dibutuhkan.

---

# 12. Research Gap yang Masih Harus Dicari

Belum boleh menyatakan research gap final.

Pertanyaan yang harus dijawab:

### Target

1. Berapa paper yang memprediksi:
   - PM2.5 concentration?
   - PM2.5 category?
   - ISPU?
   - AQI?

2. Apakah meteorology-only → PM2.5 category sudah pernah dilakukan?

3. Apakah PM2.5 category lebih tepat sebagai:
   - target,
   - input,
   - parameter pembentukan target?

### Lokasi

4. Kota/region mana yang sudah terlalu banyak diteliti?

5. Kota mana yang:
   - punya data 3 tahun,
   - memiliki PM2.5,
   - punya meteorological data,
   - tetapi belum terlalu banyak diteliti?

### Temporal resolution

6. Apakah paper sebelumnya hourly?

7. Apakah daily prediction masih punya gap?

8. Apakah weekly/monthly terlalu sedikit untuk ML?

### Model

9. Apakah RF sudah cukup?

10. Apakah XGBoost memberikan research value?

11. Apakah model comparison dibutuhkan?

### Sampling

12. Satu stasiun atau beberapa stasiun?

13. Jika satu region:
   - apakah menggunakan satu station?
   - seluruh station di region lalu agregasi?
   - station dengan data paling lengkap?

### Features

14. Temperature
15. Relative humidity
16. Wind speed
17. Wind direction
18. Atmospheric pressure
19. Rainfall
20. Dew point
21. Season/month
22. Lag variables
23. Rolling mean

Tidak semua harus digunakan.

---

# 13. Jangan Menggunakan PM2.5 Sebagai Feature Secara Sembarangan

Misalnya target:

```text
Category(t)
```

yang didefinisikan langsung dari:

```text
PM2.5(t)
```

Lalu feature:

```text
PM2.5(t)
Temperature(t)
Humidity(t)
Wind(t)
```

Model akan belajar:

```text
PM2.5(t) → Category(t)
```

Ini bukan prediksi yang menarik secara ilmiah.

Lebih masuk akal jika:

### Opsi 1

```text
Meteorology(t)
      ↓
Predict PM2.5(t)
```

### Opsi 2

```text
Meteorology(t)
      ↓
Predict PM2.5 Category(t)
```

### Opsi 3 — temporal forecasting

```text
Meteorology(t)
PM2.5(t)
      ↓
Predict PM2.5(t+1)
```

atau:

```text
Meteorology(t)
PM2.5(t)
      ↓
Predict Category(t+1)
```

Opsi 3 dapat menjadi lebih kuat secara konsep, tetapi juga menambah kompleksitas.

---

# 14. Pilihan Research Design yang Sedang Dipertimbangkan

## Design A — Regression

```text
Meteorological Variables
          ↓
    Random Forest
          ↓
   PM2.5 concentration
```

Kelebihan:
- target continuous,
- evaluasi jelas,
- secara ilmiah PM2.5 prediction umum.

Kekurangan:
- sudah banyak penelitian,
- novelty harus jelas.

---

## Design B — Classification

```text
Meteorological Variables
          ↓
    Random Forest
          ↓
PM2.5 Category
```

Kelebihan:
- lebih dekat dengan “kategori kualitas udara”,
- lebih mudah dijelaskan kepada pembaca umum.

Kekurangan:
- harus memastikan definisi kategori,
- class imbalance mungkin muncul,
- literature Jakarta classification sudah cukup banyak.

---

## Design C — Forecasting

```text
PM2.5(t)
Meteorology(t)
       ↓
Random Forest
       ↓
Category(t+1)
```

Kelebihan:
- lebih jelas sebagai “prediction” daripada sekadar klasifikasi contemporaneous data.

Kekurangan:
- feature engineering temporal,
- lagging,
- data leakage harus ditangani,
- lebih kompleks.

---

# 15. Dataset Strategy

Target awal:

- periode: **3 tahun**
- region: **1 daerah**
- data:
  - PM2.5
  - meteorology
- resolution:
  **belum final**

Prioritas:
1. data resmi,
2. timestamp jelas,
3. cukup lengkap,
4. minimal 3 tahun,
5. bisa direproduksi,
6. sumber dapat dikutip.

---

# 16. Jika Menggunakan Daily Data

Contoh:

```text
Date
Temperature
Humidity
Wind Speed
Pressure
Rainfall
PM2.5
```

Kemudian:

```text
PM2.5
  ↓
Category
```

Misalnya:

```text
Date       Temp   Humidity   Wind   Pressure   PM2.5   Category
2023-01-01 29.1   78        2.3    1008       20.1    Sedang
2023-01-02 30.2   75        2.8    1007       12.5    Baik
...
```

Keuntungan:
- mudah dipahami,
- preprocessing relatif mudah,
- dataset tidak terlalu besar,
- cocok untuk beginner.

Tetapi:
- daily aggregation harus punya alasan,
- cara agregasi PM2.5 harus jelas,
- jangan asal mengambil mean jika standar penelitian membutuhkan maximum/other statistic.

---

# 17. Jika Menggunakan Hourly Data

Contoh:

```text
Timestamp
Temperature
Humidity
Wind Speed
Pressure
Rainfall
PM2.5
```

3 tahun ≈:

```text
3 × 365 × 24
≈ 26,280 observations
```

Lebih banyak data.

Kelebihan:
- lebih kaya,
- lebih cocok untuk forecasting,
- lebih banyak variasi.

Kekurangan:
- missing data,
- timestamp alignment,
- lag features,
- temporal leakage,
- preprocessing lebih kompleks.

Untuk TA beginner, daily bisa lebih manageable jika secara ilmiah dapat dibenarkan.

---

# 18. Train/Test Split

Jangan random split secara sembarangan untuk time-series.

Lebih aman:

```text
2023 → Train
2024 → Train / Validation
2025 → Test
```

Atau:

```text
70% earliest → Train
15% berikutnya → Validation
15% terakhir → Test
```

Prinsip utama:

> Data masa depan tidak boleh digunakan untuk melatih model yang memprediksi masa lalu.

Jika forecasting:
- gunakan chronological split,
- feature lag harus dibuat hanya dari informasi yang tersedia sebelum target.

---

# 19. Evaluasi Jika Classification

Metrics kandidat:

- Accuracy
- Precision
- Recall
- F1-score
- Confusion Matrix
- Macro F1 jika class imbalance.

Jangan hanya memakai accuracy jika kelas tidak seimbang.

Contoh:

```text
Class distribution:
Baik          70%
Sedang        25%
Tidak Sehat    5%
```

Model yang selalu menebak “Baik” bisa memiliki accuracy tinggi tetapi tidak berguna.

---

# 20. Evaluasi Jika Regression

Metrics kandidat:

- MAE
- RMSE
- R²

Interpretasi singkat:

### MAE

Rata-rata besar kesalahan absolut.

### RMSE

Lebih sensitif terhadap error besar.

### R²

Proporsi variasi target yang dijelaskan model.

---

# 21. Feature Importance

Random Forest dapat digunakan untuk melihat feature importance.

Contoh:

```text
Feature              Importance
---------------------------------
Wind Speed              0.31
Humidity                0.25
Temperature             0.21
Pressure                0.14
Rainfall                0.09
```

Tetapi jangan langsung mengatakan:

> “Wind speed menyebabkan PM2.5.”

Feature importance menunjukkan kontribusi prediktif model, bukan hubungan kausal.

---

# 22. Jika XGBoost Ditambahkan

Jangan langsung membuat:

```text
RF vs XGBoost
```

hanya karena terlihat lebih keren.

Pertanyaan yang harus dijawab:

> Apa alasan akademik membandingkan RF dengan XGBoost?

Contoh alasan yang mungkin:

- kedua model adalah ensemble tree-based,
- memiliki karakteristik berbeda,
- penelitian terdahulu menunjukkan hasil berbeda,
- ingin melihat trade-off performance vs interpretability.

Jika literature review tidak membutuhkan XGBoost, RF saja mungkin lebih sesuai untuk scope TA.

---

# 23. Potensi Struktur Penelitian

Jika akhirnya classification:

```text
Data Collection
      ↓
Data Cleaning
      ↓
Data Integration
      ↓
Exploratory Data Analysis
      ↓
PM2.5 Category Definition
      ↓
Feature Selection
      ↓
Train/Validation/Test Split
      ↓
Random Forest
      ↓
Evaluation
      ↓
Feature Importance
      ↓
Analysis
```

Jika RF + XGBoost:

```text
                         ┌→ Random Forest ─┐
Data → Preprocessing →   │                 ├→ Evaluation → Comparison
                         └→ XGBoost ───────┘
```

---

# 24. Part 2.5 — Literature Review & Research Gap

**Ini adalah tahap yang sedang dikerjakan sekarang.**

Goal:

> Jangan coding model dulu sebelum target, lokasi, data resolution, dan research gap cukup jelas.

Buat literature matrix:

| Paper | Year | Location | Source | Period | Resolution | Target | PM2.5 Role | Features | Model | Metrics | Main Result | Limitation | Gap |
|---|---:|---|---|---|---|---|---|---|---|---|---|---|---|

Target:
**8–15 paper highly relevant.**

Prioritas paper:
1. Indonesia
2. PM2.5
3. meteorological variables
4. RF
5. XGBoost
6. air quality classification
7. air quality forecasting.

---

# 25. Search Questions

Cari paper untuk menjawab:

```text
"PM2.5 prediction" "Random Forest" Indonesia meteorological
```

```text
"PM2.5 classification" meteorological Random Forest Indonesia
```

```text
"air quality classification" Random Forest XGBoost Indonesia
```

```text
"PM2.5" "meteorological variables" "Random Forest" Indonesia
```

```text
"air quality" "Random Forest" "BMKG"
```

Lalu search berdasarkan kandidat kota.

Jangan hanya search judul yang sama.

---

# 26. Apa yang Harus Dicatat dari Setiap Paper

Untuk setiap paper, catat:

### 1. Location
Kota/region/station.

### 2. Dataset source
BMKG, OpenAQ, government portal, Kaggle, dll.

### 3. Period
Misalnya 2021–2024.

### 4. Resolution
Hourly/daily/monthly.

### 5. Target
PM2.5 concentration / category / AQI / ISPU.

### 6. PM2.5 role
Target / feature / category basis.

### 7. Features
Meteorology apa saja.

### 8. Model
RF / XGBoost / CatBoost / LR / DL.

### 9. Preprocessing
Missing value, outlier, interpolation, scaling, aggregation.

### 10. Sampling
Station selection / region selection.

### 11. Evaluation
MAE/RMSE/R² atau Accuracy/F1.

### 12. Result
Apa yang berhasil?

### 13. Limitation
Apa yang masih kurang?

### 14. Gap
Apa yang mungkin bisa dilakukan penelitian kita?

---

# 27. Prinsip Novelty

Jangan membuat novelty seperti:

> “Penelitian ini menggunakan Random Forest, sedangkan penelitian sebelumnya menggunakan XGBoost.”

Itu belum tentu cukup.

Novelty yang lebih defensible:

> “Penelitian sebelumnya berfokus pada prediksi konsentrasi PM2.5 secara hourly di Jakarta, sedangkan penelitian ini mengevaluasi kemampuan parameter meteorologi untuk mengklasifikasikan kategori PM2.5 pada resolusi harian di [region], dengan periode pengamatan tiga tahun.”

Tetapi kalimat tersebut **baru boleh digunakan jika literature review benar-benar membuktikannya.**

---

# 28. Part 3 — Dataset Actualization + EDA

Belum masuk penuh.

Setelah Part 2.5 selesai:

1. pilih region,
2. pilih source,
3. download dataset,
4. cek schema,
5. cek missing values,
6. cek duplicate,
7. cek timestamp,
8. cek outlier,
9. cek distribution,
10. cek class distribution,
11. cek correlation,
12. tentukan preprocessing.

---

# 29. Part 4 — Preprocessing

Kemungkinan:

```text
Raw Data
   ↓
Datetime Parsing
   ↓
Duplicate Removal
   ↓
Missing Value Analysis
   ↓
Outlier Analysis
   ↓
Aggregation (jika daily)
   ↓
Feature Engineering
   ↓
Target Construction
   ↓
Train/Test Split
```

Catatan:

Jangan otomatis menghapus semua outlier.

Dalam air quality, nilai PM2.5 yang tinggi bisa merupakan kondisi nyata.

---

# 30. Part 5 — Modeling

## Baseline

Mulai dari model sederhana.

Classification:
- majority baseline
- Random Forest

Regression:
- mean baseline
- Random Forest Regressor

Jika dibutuhkan:
- XGBoost

---

# 31. Part 6 — Evaluation

Classification:

```text
Confusion Matrix
Accuracy
Precision
Recall
F1
Macro F1
```

Regression:

```text
MAE
RMSE
R²
```

Tambahkan feature importance.

Jika menggunakan SHAP, itu opsional dan hanya jika scope masih masuk akal.

---

# 32. Part 7 — Analysis

Pertanyaan analisis:

1. Fitur meteorologi mana yang paling penting?
2. Apakah hasil masuk akal secara meteorologis?
3. Bagaimana performa model pada masing-masing kategori?
4. Apakah ada class imbalance?
5. Apakah model gagal pada kategori tertentu?
6. Bagaimana kondisi ekstrem?
7. Apa keterbatasan dataset?
8. Apakah hasil konsisten dengan penelitian sebelumnya?

---

# 33. Part 8 — Proposal Writing

Kemungkinan struktur:

## BAB I Pendahuluan

1. Latar Belakang
2. Identifikasi Masalah
3. Rumusan Masalah
4. Batasan Masalah
5. Tujuan Penelitian
6. Manfaat Penelitian

## BAB II Tinjauan Pustaka

1. Air Quality
2. PM2.5
3. Meteorological Factors
4. BMKG / standar kategorisasi
5. Machine Learning
6. Random Forest
7. XGBoost (jika digunakan)
8. Evaluation Metrics
9. Penelitian Terdahulu
10. Research Gap
11. Kerangka Konseptual

## BAB III Metodologi

1. Dataset
2. Data Collection
3. Data Preprocessing
4. EDA
5. Feature Engineering
6. Target Construction
7. Train/Test Split
8. Model
9. Evaluation
10. Experiment Design

---

# 34. Yang Sudah Diputuskan

| Item | Status |
|---|---|
| Research group | PREDICTIVE ANALYTICS & ENVIRONMENTAL INTELLIGENCE |
| Domain | Air quality / PM2.5 |
| Draft title | Prediksi Kategori Kualitas Udara Berdasarkan Parameter Meteorologi Menggunakan Random Forest |
| Period | ±3 tahun |
| Region | 1 region |
| Main candidate model | Random Forest |
| PM2.5 role | Belum final |
| Target | Belum final |
| Location | Belum final |
| Resolution | Belum final |
| XGBoost | Opsional |
| Dataset source | Belum final |
| Research gap | Belum final |

---

# 35. Hal yang Jangan Dilakukan Dulu

Jangan:

- langsung membuat model final,
- langsung memilih Jakarta tanpa literature review,
- langsung membuat category tanpa standar yang jelas,
- memasukkan PM2.5(t) sebagai feature jika target Category(t) dibuat dari PM2.5(t),
- melakukan random train/test split untuk forecasting tanpa alasan,
- menambah XGBoost hanya untuk “novelty”,
- mengklaim causal relationship dari feature importance,
- menggunakan dataset synthetic sebagai dataset utama,
- mengklaim research gap sebelum membaca paper,
- menulis judul final sebelum bimbingan/literature review.

---

# 36. Roadmap Keseluruhan

```text
PART 1
Topic Selection
      ↓
PART 2
Initial Research Design
      ↓
PART 2.5  ← CURRENT
Literature Review
+ PM2.5 Theory
+ BMKG Categorization
+ Research Gap
      ↓
PART 3
Dataset Actualization
+ EDA
      ↓
PART 4
Preprocessing
      ↓
PART 5
Modeling
      ↓
PART 6
Evaluation
      ↓
PART 7
Analysis
      ↓
PART 8
Proposal / TA Writing
      ↓
Bimbingan
      ↓
Revision
```

---

# 37. Prioritas Saat Ini

## PRIORITY 1 — Literature Review

Target:
**8–15 paper**

Output:
literature matrix.

---

## PRIORITY 2 — PM2.5 Theory

Cari:
- definisi PM2.5,
- health/environment relevance,
- measurement,
- relationship dengan meteorology,
- category definitions,
- BMKG standard,
- relevant Indonesian regulation/standard.

---

## PRIORITY 3 — Decide PM2.5 Role

Pilih berdasarkan literature:

```text
Regression?
Classification?
Forecasting?
```

---

## PRIORITY 4 — Decide Region

Kriteria:

- data tersedia ±3 tahun,
- PM2.5 tersedia,
- meteorology tersedia,
- data cukup lengkap,
- literature duplication manageable.

---

## PRIORITY 5 — Decide Resolution

Bandingkan:

```text
Hourly
vs
Daily
```

Pilih berdasarkan:
- literature,
- data availability,
- scope,
- research question.

---

## PRIORITY 6 — Decide Model

Default:

```text
Random Forest
```

XGBoost hanya jika ada alasan.

---

# 38. Expected Output dari Literature Review

Pada akhir Part 2.5 kita ingin bisa mengatakan:

> “Berdasarkan X paper, sebagian besar penelitian di Indonesia menggunakan [target], [resolution], [model], dan [location]. Penelitian di [location] sudah banyak/belum banyak dilakukan. Oleh karena itu penelitian ini akan fokus pada [target] menggunakan [features] pada [resolution] selama [period], dengan [method], untuk menjawab [research question].”

Kalimat ini adalah target, bukan kesimpulan yang boleh dibuat sebelum data literature cukup.

---

# 39. Prompt untuk Codex / ChatGPT Akun Lain

Gunakan prompt berikut sebagai **initial prompt** setelah upload file Markdown ini.

---

## MASTER PROMPT — CONTINUE MY TA PROJECT

Saya sedang mengerjakan persiapan Tugas Akhir (TA) Informatika dengan topik:

**PREDICTIVE ANALYTICS & ENVIRONMENTAL INTELLIGENCE**

Area penelitian:
**Environmental Intelligence → Air Quality / PM2.5**

Saya melampirkan file:

`TA_Preparation_Pack_Air_Quality_PM25.md`

File tersebut berisi seluruh konteks, progress, keputusan sementara, hasil literature search, arahan dosen, dan roadmap penelitian saya.

### Tugas utama kamu

Baca seluruh file terlebih dahulu dan anggap file tersebut sebagai **source of truth untuk konteks project**.

Jangan langsung coding.

Saya ingin kamu bertindak sebagai **research + coding assistant untuk TA saya**.

---

## Aturan penting

### 1. Jangan menganggap draft sebagai keputusan final

Judul saat ini:

> “Prediksi Kategori Kualitas Udara Berdasarkan Parameter Meteorologi Menggunakan Random Forest”

Tetapi ini masih draft.

PM2.5 role, target, lokasi, resolution, dataset source, dan model juga belum final.

---

### 2. Prioritaskan research gap

Sebelum coding:

- review paper,
- bandingkan penelitian terdahulu,
- cari kemungkinan duplication,
- tentukan research gap,
- baru bantu finalisasi research design.

Jangan mengarang research gap.

---

### 3. Search current sources

Jika akses web tersedia, gunakan web search untuk mencari paper/sumber terbaru.

Prioritaskan:

- BMKG
- jurnal ilmiah
- IEEE
- Springer
- Elsevier
- ScienceDirect
- MDPI jika relevan
- repository universitas
- Google Scholar-like indexed sources
- sumber pemerintah Indonesia

Untuk setiap klaim akademik penting, berikan citation/source.

---

### 4. Jangan menambahkan kompleksitas tanpa alasan

Saya mahasiswa Informatika yang ingin penelitian yang manageable.

Prioritas:

```text
simple
→ understandable
→ reproducible
→ academically defensible
```

Bukan:

```text
more models = better research
```

Jika Random Forest sudah cukup, katakan bahwa RF cukup.

Jika XGBoost diperlukan berdasarkan literature, baru tambahkan.

---

### 5. PM2.5 harus dibahas secara hati-hati

Bantu saya menentukan apakah PM2.5 sebaiknya:

- regression target,
- classification target basis,
- input,
- atau future prediction feature.

Perhatikan data leakage.

Jika category(t) dibuat langsung dari PM2.5(t), jangan memasukkan PM2.5(t) sebagai feature untuk memprediksi category(t) tanpa alasan yang sangat jelas.

---

### 6. Time-series awareness

Jika menggunakan data temporal:

- jangan sembarang random split,
- gunakan chronological split,
- hindari future information leakage,
- jelaskan lag/rolling feature jika digunakan.

---

### 7. Coding workflow

Saat sudah masuk coding:

1. jelaskan tujuan step,
2. berikan struktur folder,
3. berikan kode sederhana,
4. jelaskan kode,
5. berikan cara menjalankan,
6. berikan expected output,
7. bantu debug jika error,
8. jangan lompat terlalu jauh.

Saya lebih suka workflow bertahap daripada langsung diberikan project besar.

---

# FIRST TASK

Setelah membaca `TA_Preparation_Pack_Air_Quality_PM25.md`, jangan langsung coding.

Buat saya:

## A. Project Status Summary

Tampilkan:

- sudah diputuskan,
- masih tentative,
- belum diputuskan.

## B. Research Questions

Buat kandidat research questions yang sesuai dengan konteks.

## C. Literature Review Plan

Buat search strategy untuk mendapatkan 8–15 paper yang relevan.

## D. Literature Matrix

Buat template yang bisa saya isi.

## E. PM2.5 Decision Framework

Bandingkan:

1. PM2.5 regression
2. PM2.5 category classification
3. future PM2.5/category forecasting

Jelaskan:
- target,
- features,
- risk of leakage,
- complexity,
- evaluation,
- literature duplication,
- suitability untuk TA.

## F. Research Gap

Jangan mengklaim final gap.

Buat daftar gap yang perlu diverifikasi melalui paper.

## G. Next Action

Berikan maksimal 5 pekerjaan berikutnya yang paling penting.

---

# Coding rule

Jangan membuat kode sebelum saya mengatakan:

> “mulai coding”

Saat saya sudah mengatakan mulai coding, bantu saya membangun project secara bertahap.

---

# Final behavior

Jangan mengulang semua isi file setiap kali saya bertanya.

Gunakan file sebagai project memory.

Jika ada keputusan baru dari dosen, bantu saya memperbarui:

- research design,
- literature matrix,
- roadmap,
- dataset strategy,
- code plan.

Selalu bedakan:

```text
DECIDED
TENTATIVE
OPEN QUESTION
```

---

# 40. Prompt Pendek untuk Melanjutkan Coding Nanti

Setelah research design sudah final, gunakan:

```text
Kita sudah menyelesaikan literature review dan research design.

Sekarang lanjut ke tahap coding.

Gunakan TA_Preparation_Pack_Air_Quality_PM25.md sebagai project context.

Sebelum membuat kode:
1. tampilkan final research design yang akan digunakan,
2. tampilkan dataset schema yang dibutuhkan,
3. buat struktur folder project,
4. jelaskan pipeline preprocessing → training → evaluation,
5. tunggu konfirmasi saya sebelum membuat kode penuh.

Gunakan Python dan library yang sederhana serta umum untuk ML tabular, misalnya:
- pandas
- numpy
- matplotlib
- seaborn jika diperlukan
- scikit-learn
- xgboost hanya jika memang sudah diputuskan

Jangan membuat project terlalu kompleks.

Saya ingin membangun project step-by-step dan memahami setiap bagian.
```

---

# 41. Prompt Debugging

Jika nanti ada error:

```text
Saya sedang melanjutkan project TA Air Quality / PM2.5.

Gunakan TA_Preparation_Pack_Air_Quality_PM25.md sebagai context.

Saya akan memberikan:
1. kode,
2. error,
3. struktur folder jika diperlukan.

Tolong:
- identifikasi penyebab error,
- jelaskan dengan bahasa sederhana,
- tunjukkan bagian kode yang bermasalah,
- berikan perbaikan minimal,
- jangan rewrite seluruh project jika tidak diperlukan,
- jelaskan kenapa perbaikan tersebut bekerja.
```

---

# 42. Prompt EDA

```text
Lanjutkan project TA Air Quality / PM2.5.

Saya sudah memiliki dataset.

Jangan langsung modeling.

Bantu saya melakukan EDA step-by-step:

1. load data,
2. inspect shape,
3. inspect columns,
4. datatype,
5. missing values,
6. duplicates,
7. timestamp,
8. descriptive statistics,
9. PM2.5 distribution,
10. meteorological distribution,
11. outlier investigation,
12. temporal trends,
13. correlation,
14. class distribution jika classification.

Untuk setiap step:
- jelaskan tujuan,
- berikan kode,
- jelaskan output yang perlu diperhatikan,
- jangan lanjut terlalu jauh sebelum step sebelumnya jelas.
```

---

# 43. Prompt Modeling

```text
Research design saya sudah final.

Bantu saya melakukan modeling untuk project TA Air Quality / PM2.5.

Model utama:
Random Forest.

Jika XGBoost memang sudah diputuskan dalam research design, gunakan XGBoost sebagai model pembanding.

Pipeline:

Raw/clean data
→ feature preparation
→ chronological train/test split
→ model training
→ prediction
→ evaluation
→ feature importance
→ comparison
→ interpretation

Jangan menggunakan data leakage.

Jelaskan setiap step sebelum kode.

Untuk classification gunakan metric yang sesuai:
Accuracy, Precision, Recall, F1/Macro F1, Confusion Matrix.

Untuk regression gunakan:
MAE, RMSE, R².

Jangan menyatakan feature importance sebagai causal effect.
```

---

# 44. Prompt Menulis BAB III

```text
Gunakan project context TA_Preparation_Pack_Air_Quality_PM25.md.

Saya ingin mulai menulis BAB III Metodologi.

Gunakan research design final yang sudah disepakati.

Buat draft akademik untuk:

3.1 Jenis Penelitian
3.2 Dataset dan Sumber Data
3.3 Variabel Penelitian
3.4 Data Preprocessing
3.5 Exploratory Data Analysis
3.6 Feature Engineering
3.7 Pembentukan Target
3.8 Pembagian Data
3.9 Random Forest
3.10 XGBoost jika digunakan
3.11 Evaluation Metrics
3.12 Experimental Procedure
3.13 Research Flowchart

Jangan mengarang informasi dataset.
Jika detail belum tersedia, tandai [TBD].
```

---

# 45. Prompt Literature Review

```text
Saya sedang melakukan literature review untuk TA Air Quality / PM2.5.

Gunakan project context di TA_Preparation_Pack_Air_Quality_PM25.md.

Cari paper yang paling relevan dengan:

- PM2.5
- air quality
- meteorological variables
- Random Forest
- XGBoost
- classification/regression
- Indonesia
- Indonesian cities
- BMKG

Prioritaskan paper 2021–2026, tetapi gunakan paper lama jika fundamental/relevan.

Untuk setiap paper buat:

Title
Year
Location
Dataset
Period
Resolution
Target
PM2.5 Role
Features
Model
Preprocessing
Evaluation
Result
Limitation
Potential Gap

Setelah itu:
- kelompokkan paper berdasarkan target,
- kelompokkan berdasarkan lokasi,
- identifikasi duplication,
- identifikasi potential research gap.

Jangan menyatakan suatu gap sebagai fakta jika belum didukung literature.
```

---

# 46. Prompt Bimbingan Dosen

Setelah ada hasil literature review:

```text
Saya akan melakukan bimbingan TA.

Gunakan project context dan literature matrix yang sudah kita buat.

Bantu saya menyiapkan:

1. ringkasan progress,
2. keputusan yang sudah dibuat,
3. pertanyaan yang belum terjawab,
4. research gap sementara,
5. alasan pemilihan metode,
6. alasan pemilihan dataset,
7. alasan pemilihan lokasi,
8. alasan pemilihan target,
9. 5–10 pertanyaan penting untuk dosen.

Buat dalam format singkat karena waktu bimbingan sekitar 20 menit.

Pisahkan:
DECIDED
TENTATIVE
NEED ADVISOR DECISION
```

---

# 47. Prompt Setelah Bimbingan

Setiap selesai bimbingan, gunakan:

```text
Saya baru selesai bimbingan TA.

Berikut catatan dosen:

[PASTE CATATAN]

Tolong:
1. interpretasikan feedback dosen,
2. pisahkan keputusan baru vs saran,
3. update research design,
4. update open questions,
5. tentukan apa yang harus saya kerjakan sebelum bimbingan berikutnya,
6. jika ada konflik dengan research design lama, jelaskan bagian yang berubah,
7. jangan menganggap sesuatu final jika dosen hanya menyarankan.
```

---

# 48. Aturan Project Memory

Setiap kali ada keputusan penting, simpan dengan format:

```text
DATE:
DECISION:
STATUS:
REASON:
SOURCE:
IMPACT:
NEXT ACTION:
```

Contoh:

```text
DATE: 2026-09-XX

DECISION:
Menggunakan data 3 tahun.

STATUS:
DECIDED BY ADVISOR

REASON:
Arahan dosen.

SOURCE:
Bimbingan.

IMPACT:
Dataset search dibatasi pada dataset dengan coverage minimal 3 tahun.

NEXT ACTION:
Cari region dengan data PM2.5 + meteorologi minimal 3 tahun.
```

---

# 49. Prinsip Utama Project

Selalu gunakan prinsip:

```text
Literature
   ↓
Research Gap
   ↓
Research Question
   ↓
Dataset
   ↓
Method
   ↓
Experiment
   ↓
Evaluation
   ↓
Conclusion
```

Bukan:

```text
Saya ingin pakai Random Forest
        ↓
Cari dataset
        ↓
Cari alasan
```

Penelitian harus dimulai dari problem/research gap, bukan dari model yang ingin digunakan.

---

# 50. Current Next Step

**Sekarang jangan coding.**

Kerjakan:

```text
PART 2.5
Literature Review
+
PM2.5 Theory
+
BMKG Categorization
+
Research Gap
```

Setelah itu baru:

```text
PART 3
Dataset Actualization + EDA
```

---

# END OF PROJECT CONTEXT
