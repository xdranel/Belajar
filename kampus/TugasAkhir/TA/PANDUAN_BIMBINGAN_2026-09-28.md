# Bahan bimbingan TA — Senin, 28 September 2026

**Tujuan pertemuan:** meminta arahan tentang pertanyaan penelitian yang layak, target yang sesuai data, sumber data, dan pekerjaan sebelum bimbingan berikutnya. Dokumen ini adalah bahan diskusi, bukan proposal atau keputusan final. Ringkasan lengkap ada di [catatan belajar](CATATAN_BELAJAR.md) dan [progres](PROGRES.md).

## 1. Baca ini dulu: posisi penelitian saat ini

| Sudah ada arahan | Kandidat yang sedang diuji | Perlu arahan dosen |
| --- | --- | --- |
| Topik kualitas udara/PM2.5; sekitar tiga tahun data; fokus satu daerah; pahami kategorisasi dan paper terkait. | Serpong 2020–2022, pengukuran PM2.5 tiap tiga jam; Random Forest sebagai kandidat model. | Apakah targetnya angka konsentrasi, kategori, atau kondisi waktu berikutnya? Apakah satu stasiun dan data cuaca yang dapat diperoleh cukup? Apa kontribusi yang dianggap layak? |

Judul awal **“Prediksi Kategori Kualitas Udara Berdasarkan Parameter Meteorologi Menggunakan Random Forest” masih rancangan**. Data Serpong yang paling konkret saat ini mempunyai 7.416 dari 8.768 slot PM2.5 valid pada 2020–2022 (84,58%), tetapi ada beberapa bulan kosong. [Metadata NIES](https://www.nies.go.jp/doi/10.17595/20260821.004-e.html) menyebut data PM2.5 tiap tiga jam. Kategori BMKG yang telah diperiksa berlaku untuk konsentrasi **per jam**, sedangkan ISPU PM2.5 memakai pengukuran **24 jam**; karena itu label kategori belum boleh langsung ditempelkan pada angka tiga jam. Rujukan: [Peraturan BMKG 8/2022](https://jdih.bmkg.go.id/common/dokumen/perbanno.8tahun2022.pdf) dan [Permen LHK 14/2020](https://peraturan.bpk.go.id/Details/163466/permen-lhk-no-14-tahun-).

## 2. Kalimat pembuka yang bisa kamu pakai (sekitar dua menit)

> “Pak/Bu, saya sudah menindaklanjuti arahan untuk mencari sekitar tiga tahun data di satu daerah, memahami PM2.5 dan kategorinya, serta membaca penelitian sebelumnya. Dari 17 kandidat paper, saya telah menelaah penuh 10; lima di antaranya penelitian prediktif. Saya menemukan arsip PM2.5 terukur di Serpong dengan cakupan tiga tahun yang cukup menjanjikan, tetapi resolusinya tiga jam dan ada bulan kosong. Saya belum mengunci judul, karena paper yang mirip sudah ada dan bentuk target harus cocok dengan data. Saya ingin meminta arahan: apakah lebih baik penelitian ini memprediksi angka konsentrasi PM2.5, kategori yang aturannya perlu disesuaikan, atau kondisi pada waktu berikutnya? Saya membawa ringkasan paper serta dua opsi rancangan untuk didiskusikan.”

Jika waktu sempit, berhenti setelah ini dan ajukan pertanyaan nomor 1–3 pada bagian 5.

## 3. Paper yang paling perlu kamu pahami untuk menjelaskan posisi TA

| Paper | Apa yang dilakukan | Pelajaran untuk TA; cara menyampaikannya |
| --- | --- | --- |
| [Indriyati dkk., JMG/BMKG 2025](https://jmg.bmkg.go.id/jmg/index.php/jmg/article/download/1163/503/4967) | Memakai PM2.5 **terukur per jam selama 2022–2024** di Kemayoran, Semarang, dan Malang, ditambah meteorologi ERA5; membandingkan regresi linear, RF, dan XGBoost untuk konsentrasi PM2.5. | “Prediksi/estimasi PM2.5 dari cuaca dengan RF sudah pernah dilakukan di Indonesia. Saya tidak ingin menyebut metode itu sendiri sebagai kebaruan.” Paper memasangkan cuaca dan target pada waktu yang sama; jangan otomatis menyebut hasilnya bukti prakiraan beberapa jam ke depan. |
| [Pratama dkk., Tekinkom 2026 (Palembang)](https://jurnal.murnisadar.ac.id/index.php?journal=Tekinkom&page=article&op=download&path[]=2747&path[]=1058) | Klasifikasi kategori berbasis PM2.5 dari meteorologi dengan RF/CatBoost. Data asalnya **60 angka bulanan**, lalu diinterpolasi menjadi 1.796 angka harian estimasi sebelum split acak. | “Judul awal saya dekat dengan paper ini. Saya perlu rancangan yang punya alasan lebih kuat daripada mengganti kota atau algoritma.” Hasil pada angka hasil interpolasi belum membuktikan hasil pada pengukuran harian asli atau periode masa depan; ini **batas bukti**, bukan tuduhan bahwa paper salah. |
| [Faryuni & Sampurno, Buletin Fisika 2026 (Pontianak)](https://ejournal3.unud.ac.id/index.php/buletinfisika/article/download/3069/1931) | Memakai riwayat PM2.5 dan cuaca untuk prediksi konsentrasi; data sekitar **satu tahun**, dengan pemisahan uji menurut waktu. | “Riwayat PM2.5 tampaknya penting bila saya ingin memperkirakan kondisi berikutnya. Saya perlu mendefinisikan kapan informasi tersebut tersedia agar tidak memakai data masa depan.” Studi ini berarti ide memakai riwayat PM2.5 juga bukan hal baru dengan sendirinya. |
| [Ihsan dkk., IOP EES 2023 (Serpong)](https://iopscience.iop.org/article/10.1088/1755-1315/1201/1/012040/pdf) | Mengkaji PM2.5, PM10, dan curah hujan dari **lokasi Serpong yang sama** pada Januari 2020–Agustus 2022; analisis deskriptif/korelasi, bukan model prakiraan. Paper menyebut sensor cuaca di lokasi. | “Serpong dan hubungan hujan–PM2.5 sudah pernah diteliti. Saya tidak akan mengklaim lokasinya sama sekali baru. Namun kemungkinan ada akses data cuaca setempat yang lebih tepat daripada cuaca bergrid.” |
| [Ihsan dkk., JTL/BRIN 2025 (Serpong)](https://ejournal.brin.go.id/JTL/article/download/2887/7013) | Mendeskripsikan PM2.5/PM10 dan ISPU dari instrumen yang sama untuk **2023**, tanpa model prediksi. | “Data dan lokasi Serpong juga sudah dipakai untuk kajian deskriptif. Jika saya memakai Serpong, pertanyaan penelitiannya harus jelas berbeda dan layak diuji.” |

**Cara membaca tabel:** “mirip” tidak berarti topik TA batal. Paper membantu melihat pekerjaan yang telah dilakukan dan batas kesimpulannya. *Research gap* baru kuat jika ada pertanyaan penting yang belum dijawab, data memadai, dan cara uji yang dapat menunjukkan jawabannya. Perbedaan lokasi, metode, atau resolusi **sendirian** belum cukup.

Paper lain yang perlu diketahui bila dosen menyinggung angka performa tinggi: [AISM Tangerang Selatan 2024](https://journal.uinjkt.ac.id/aism/article/download/38466/pdf) dan [JUTIF Tangerang 2026](https://jutif.if.unsoed.ac.id/index.php/jurnal/article/download/5412/1252) sama-sama memasukkan polutan, termasuk PM2.5, sebagai fitur dalam skenario mereka. Keduanya bukan bukti bahwa **cuaca saja** dapat menghasilkan angka prediksi PM2.5 setinggi itu. Rincian masing-masing tersedia di [matriks literatur](CATATAN_BELAJAR.md#matriks-literatur-awal).

## 4. Dua opsi yang bisa ditawarkan, tanpa mengunci pilihan

| Opsi | Pertanyaan sederhana | Kelebihan | Hal yang harus dibereskan |
| --- | --- | --- | --- |
| **A. Estimasi konsentrasi saat itu** | “Seberapa baik cuaca menggambarkan angka PM2.5 pada interval yang sama di satu lokasi?” | Target berupa angka µg/m³, tidak perlu memaksakan kategori per jam pada data tiga jam. | Sangat dekat dengan studi BMKG 2025; perlu alasan dan pembanding yang cukup kuat. Cuaca pada interval yang sama berarti **estimasi**, bukan peringatan sebelum interval terjadi. |
| **B. Prakiraan interval berikutnya** | “Dari riwayat PM2.5 dan cuaca yang sudah diketahui, seberapa baik kondisi tiga jam berikutnya dapat diperkirakan?” | Waktu penggunaan model lebih jelas; dapat dibandingkan dengan tebakan sederhana seperti memakai nilai PM2.5 terakhir. | Perlu memastikan arti cap waktu PM, ketersediaan data cuaca masa lalu, dampak bulan kosong, dan pengujian pada periode yang lebih akhir. Ide memakai riwayat juga sudah ada pada studi Pontianak, sehingga kontribusi masih perlu dipertajam. |

**Jika dosen tetap mengarahkan kategori:** tanyakan kategori **menurut aturan apa**, untuk rerata waktu **berapa lama**, dan apakah perlu mencari **data per jam** agar judul awal sesuai. PM2.5 sebagai angka konsentrasi, kategori konsentrasi PM2.5, dan ISPU adalah target berbeda. Jika label kategori pada waktu yang sama dibentuk langsung dari PM2.5 saat itu, angka PM2.5 yang sama jangan dijadikan fitur model untuk menebak labelnya.

**Penilaian sementara saya:** opsi B lebih jelas sebagai tugas prakiraan, tetapi data dan waktunya lebih sulit dipastikan. Opsi A lebih sederhana, tetapi kedekatannya dengan penelitian terdahulu membuat kontribusi perlu dirumuskan sangat hati-hati. Ini bahan pertimbangan untuk dosen, bukan keputusan akhir.

## 5. Pertanyaan utama untuk dosen (urutkan menurut waktu yang tersedia)

1. **Arah target:** “Dari arahan sebelumnya tentang peran PM2.5, apakah Bapak/Ibu lebih menyarankan target **angka konsentrasi**, **kategori**, atau **kondisi periode berikutnya**? Apa alasan akademik yang perlu saya bangun?”
2. **Tujuan prediksi:** “Apakah cukup mengestimasi kondisi pada waktu yang sama dari cuaca, atau penelitian sebaiknya benar-benar **memprakirakan** kondisi sebelum waktu target terjadi?”
3. **Kelayakan data/lokasi:** “Saya menemukan sekitar tiga tahun data PM2.5 terukur dari **satu stasiun Serpong**, resolusi tiga jam dan ada bulan kosong. Apakah satu stasiun seperti ini dapat diterima untuk fokus satu daerah, dengan batas generalisasi yang saya jelaskan?”
4. **Kontribusi:** “Paper Indonesia sudah membahas cuaca → PM2.5, dan paper Palembang sudah membahas kategori dengan RF. Menurut Bapak/Ibu, **pertanyaan atau pembanding apa** yang cukup bernilai untuk TA saya, sehingga tidak sekadar mengulang lokasi/model?”
5. **Sumber cuaca:** “Paper Serpong menyebut pengukuran cuaca di lokasi yang sama, tetapi arsipnya belum terbuka bagi saya. Apakah sebaiknya saya menempuh permintaan data melalui kampus/BRIN/NIES, atau menyiapkan alternatif cuaca reanalisis dengan batasannya?”
6. **Cakupan metode:** “Apakah Random Forest dengan pembanding sederhana dan evaluasi yang tepat sudah cukup? Dalam keadaan seperti apa Bapak/Ibu ingin saya menambah model lain, misalnya XGBoost?”
7. **Prioritas berikutnya:** “Untuk bimbingan berikutnya, hasil apa yang paling Bapak/Ibu ingin lihat: matriks paper terpilih, kepastian akses data, rumusan masalah, atau rancangan eksperimen satu halaman?”

Jika dosen menjawab umum, tanyakan **“Contoh konkretnya untuk data saya seperti apa?”** dan catat contoh tersebut. Kamu tidak perlu membacakan seluruh daftar; pilih pertanyaan sesuai alur percakapan.

## 6. Detail teknis hanya jika diminta atau untuk tindak lanjut

Tiga hal ini lebih cocok ditanyakan kepada **pengelola dataset**, setelah dosen menyarankan jalurnya:

1. Berkas PM2.5 NIES bercap 03:00 UTC mewakili jendela tiga jam yang mana? Metadata menyebut “3 hour” sekaligus uraian rerata per jam; paper Serpong mengonfirmasi pengukuran tiap tiga jam, tetapi tidak menetapkan batas jendela pada berkas rilis.
2. Apakah berkas NIES sudah menyaring rekaman dengan status alat `A` (penyesuaian) dan `X` (kesalahan)? Paper 2023 hanya memakai status normal `N`, sedangkan berkas publik tidak memiliki kolom flag.
3. Apakah arsip cuaca **WXT520 Serpong 2020–2022** dapat diperoleh beserta zona waktu, variabel, kelengkapan, dan syarat penggunaannya?

Jelaskan kepada dosen cukup begini: **“Ada tiga rincian kualitas dan penyelarasan data yang perlu saya pastikan ke pengelola sebelum analisis; saya sudah menyiapkan pertanyaannya.”**

## 7. Jika dosen bertanya balik: jawaban yang sudah didukung bukti

**“Kenapa memilih Serpong?”** Karena ada [arsip pengukuran PM2.5 terbuka](https://www.nies.go.jp/doi/10.17595/20260821.004-e.html) dengan rentang beberapa tahun; audit awal pada 2020–2022 menemukan 84,58% slot tiga jam berisi nilai PM2.5 non-hilang. Itu **alasan kelayakan data**, bukan klaim Serpong belum pernah diteliti. Kekosongan bulan dan kualitas data tetap perlu dinilai.

**“Kenapa tidak langsung memakai judul kategori?”** Judul awal masih masuk akal sebagai arah diskusi, tetapi data PM2.5 Serpong berinterval tiga jam. Aturan kategori BMKG yang telah diperiksa berbasis konsentrasi per jam; ISPU PM2.5 memakai pengukuran 24 jam. Saya ingin menentukan target dan aturan yang selaras terlebih dahulu. Bila kategori tetap diutamakan, saya akan mencari dasar agregasi/standar yang tepat atau sumber data per jam.

**“Apa bedanya estimasi dan prakiraan?”** Contoh: jika angka PM2.5 untuk pukul 09.00–12.00 diperkirakan memakai cuaca pada **interval 09.00–12.00**, itu estimasi kondisi interval yang sama. Jika angka interval berikutnya diperkirakan dari PM2.5 dan cuaca yang **sudah diketahui sebelum interval target**, itu prakiraan. Cuaca hasil observasi pada masa depan tidak boleh diam-diam dipakai sebagai fitur prakiraan.

**“Apa *research gap* dan kebaruanmu?”** Belum final. Yang sudah diketahui: studi [BMKG 2025](https://jmg.bmkg.go.id/jmg/index.php/jmg/article/download/1163/503/4967) menguji cuaca–PM2.5 di tiga kota, [Palembang 2026](https://jurnal.murnisadar.ac.id/index.php?journal=Tekinkom&page=article&op=download&path[]=2747&path[]=1058) menguji klasifikasi kategori dari cuaca, dan [Serpong 2023](https://iopscience.iop.org/article/10.1088/1755-1315/1201/1/012040/pdf) menganalisis PM–hujan setempat. Saya sedang mencari pertanyaan yang belum dijawab dengan jelas **dan** dapat diuji memakai data yang tersedia. Evaluasi menurut waktu, perbandingan dengan tebakan sederhana, dan pengamatan asli adalah calon unsur rancangan, **bukan klaim gap yang sudah terbukti**.

**“Mengapa Random Forest?”** Ia kandidat model yang wajar untuk data tabel dan masih sesuai kemampuan pengerjaan TA. Pemilihannya harus mengikuti target dan pembanding yang dibutuhkan. Menambahkan XGBoost hanya karena nama algoritmanya berbeda belum membuat pertanyaan penelitian baru.

**“Kenapa belum mulai membuat model?”** Belum jelas apakah targetnya angka atau kategori, estimasi atau prakiraan, serta bagaimana tiga jam PM2.5 dipasangkan dengan cuaca dan disaring menurut status kualitas. Keputusan ini memengaruhi bentuk data, fitur yang sah, dan cara pengujiannya. Saya ingin membawa rancangan yang disepakati sebelum masuk ke analisis/model.

## 8. Kolom catatan setelah bimbingan

| Hal | Catatan jawaban dosen |
| --- | --- |
| Tanggal/waktu dan nama pembimbing | |
| Target yang **diputuskan** atau masih disarankan | |
| Estimasi saat ini atau prakiraan masa depan | |
| Lokasi/stasiun dan periode | |
| Data/sumber cuaca dan jalur permintaan | |
| Paper yang diminta untuk dibaca | |
| Kontribusi atau pembanding yang diharapkan | |
| Pekerjaan dan tenggat sebelum bimbingan berikutnya | |

Tandai setiap jawaban sebagai **keputusan**, **saran**, atau **perlu diperiksa lagi**. Setelah pertemuan, masukkan catatan dosen apa adanya ke [PROGRES.md](PROGRES.md) sebelum mengubah judul atau rancangan.
