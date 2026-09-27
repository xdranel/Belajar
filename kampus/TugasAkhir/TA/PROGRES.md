# Progres TA — kualitas udara / PM2.5

Terakhir diperbarui: 26 September 2026. Sumber konteks awal: [Preparation Pack](../prompt/TA_Preparation_Pack_Air_Quality_PM25.md). Bahan belajar dan tautan sumber: [CATATAN_BELAJAR.md](CATATAN_BELAJAR.md). Bahan pertemuan berikutnya: [Panduan bimbingan Senin, 28 September 2026](PANDUAN_BIMBINGAN_2026-09-28.md).

## Posisi sekarang

**Part 2.5 — literatur, teori PM2.5, kategorisasi, dan calon research gap.** Belum memilih judul, daerah, target, dataset, atau rancangan model final. Belum mulai coding.

## Keputusan dan status

| Item | Status | Dasar / catatan |
| --- | --- | --- |
| Domain kualitas udara / PM2.5 | Arah penelitian | Dokumen konteks awal. |
| Sekitar 3 tahun data, 1 daerah | Arahan dosen | Belum memilih periode dan daerah spesifik. |
| Random Forest | Kandidat awal | Perlu disesuaikan dengan pertanyaan dan literatur; XGBoost opsional. |
| Judul draft | Sementara | "Prediksi Kategori Kualitas Udara Berdasarkan Parameter Meteorologi Menggunakan Random Forest". |
| Peran PM2.5, target, resolusi, daerah, data, gap | Terbuka | Menunggu telaah naskah penuh dan pemeriksaan ketersediaan data. |

## Yang sudah dilakukan

- Membaca kedua berkas Markdown di `prompt/` dan menyusun rekap konteks.
- Mencatat **17 kandidat kajian**; **10 naskah penuh** sudah ditelaah pada data, fitur, evaluasi, dan batas klaim. Lima di antaranya studi prediktif; lima lainnya memberi konteks pengukuran, meteorologi, atau definisi risiko. Tujuh kandidat masih pada tahap metadata/ringkasan. Rincian tiap naskah ada di catatan belajar.
- Paper JMG/BMKG menunjukkan tiga lokasi dengan data PM2.5 terukur 2022–2024 dan meteorologi ERA5 sudah diteliti. Paper AISM memakai polutan, termasuk PM2.5, pada kedua skenario; hasilnya tidak membuktikan kemampuan cuaca saja. Paper Palembang menunjukkan klasifikasi kategori dari meteorologi dengan RF sudah ada, tetapi memakai data harian hasil interpolasi dari 60 data bulanan dan split acak. Paper Pontianak memakai PM2.5 historis sebagai fitur utama dengan split kronologis, tetapi hanya sekitar satu tahun. Rincian dan sumber ada di catatan belajar.
- Memeriksa [aturan BMKG 8/2022](https://jdih.bmkg.go.id/common/dokumen/perbanno.8tahun2022.pdf) tentang kategori konsentrasi **per jam** dan [Permen LHK 14/2020](https://peraturan.bpk.go.id/Home/Download/156214/Permen%20LHK%20Nomor%2014%20Tahun%202020.pdf) tentang ISPU berbasis pengukuran **24 jam**.
- Menemukan batas penting untuk rencana data: [BMKG Data Online](https://dataonline.bmkg.go.id/faq) membatasi unduhan iklim daring bulanan hingga 2 tahun terakhir; jalur data lebih rinci/lebih lama perlu diperiksa lewat [PTSP](https://dataonline.bmkg.go.id/dataonline-home). [SACA&D](https://sacad.bmkg.go.id/ID/) adalah kandidat meteorologi, bukan bukti tersedianya PM2.5.
- Menemukan [halaman riwayat PM2.5 Stamet Iskandar](https://stamet-iskandar.bmkg.go.id/udara/) yang menawarkan CSV. Cakupan tiga tahun dan data pasangannya belum terverifikasi; akses langsung dari lingkungan kerja terhalang verifikasi situs. [Laman iklim BMKG](https://iklim.bmkg.go.id/id/kualitas-udara-indonesia/) menampilkan riwayat pemantauan tujuh hari saja.
- Mengaudit isi [arsip OpenAQ](https://docs.openaq.org/aws/about): Jakarta Pusat 2022–2024 hanya 22,75% jam PM2.5 valid; Jakarta Selatan 2023–2025 48,58% slot valid, termasuk bagian akhir 2025 yang belum tersedia. Keduanya bukan pilihan awal yang kuat untuk tiga tahun.
- Menemukan dan memeriksa [dataset NIES–BRIN Serpong](https://www.nies.go.jp/doi/10.17595/20260821.004-e.html): pengukuran PM2.5 per 3 jam, dengan 84,58% slot valid pada 2020–2022. Ada bulan kosong; pasangan NASA POWER sudah cocok pada cap waktu, tetapi interval dan representativitasnya belum teruji. Audit rinci serta syarat penggunaannya dicatat di catatan belajar.
- Menelaah [paper BRIN 2025 di Serpong](https://ejournal.brin.go.id/JTL/article/download/2887/7013): sudah ada analisis deskriptif PM2.5 dan ISPU dari instrumen yang sama untuk 2023, tanpa model prediksi. Ini batas penting untuk calon gap.
- Menelaah [JUTIF 2026 di Tangerang](https://jutif.if.unsoed.ac.id/index.php/jurnal/article/download/5412/1252): RF/CatBoost dengan dan tanpa cuaca tetap memakai PM2.5 serta enam polutan sebagai fitur; target numerik tidak dijelaskan tegas dan periode hanya sekitar dua bulan. Nilai R² tinggi tidak menjadi pembanding langsung untuk prakiraan PM2.5.
- Menelaah [AAQR 2024 di tujuh kota Indonesia](https://doi.org/10.4209/aaqr.230158) dan [AAQR 2024 di Samut Prakan, Thailand](https://doi.org/10.4209/aaqr.230321): hubungan PM2.5–meteorologi bergantung skala waktu dan lokasi; kriteria risiko harus sesuai resolusi serta standar setempat. Studi Thailand memeriksa reanalisis ERA5 terhadap stasiun cuaca, contoh pemeriksaan yang relevan untuk pasangan cuaca Serpong.
- Menguji [NASA POWER hourly API](https://power.larc.nasa.gov/docs/services/api/temporal/hourly/) pada koordinat Serpong: seluruh 7.416 cap waktu PM2.5 valid pada 2020–2022 mempunyai suhu, kelembapan, angin, dan hujan. Meteorologi ini reanalisis bergrid kasar; elevasi grid respons 371,57 m, sedangkan lokasi sensor PM 63 m. Kecocokan lokal dan interval tiga jam belum dipastikan.
- Memeriksa ulang [metadata NIES](https://www.nies.go.jp/doi/10.17595/20260821.004-e.html) dan header data: berkas berjarak tiga jam, tetapi metadata juga menyebut perhitungan rerata per jam dari rekaman per menit. Awal/akhir interval belum dijelaskan. Ketentuan dataset juga mengatur sitasi, pelaporan saat produk turunan dirilis, dan kontak penulis terkait kepenulisan bila data menjadi bagian penting dari publikasi; penerapan untuk TA perlu dibahas dengan pembimbing.
- Menelaah [paper tim pengukur Serpong 2023](https://iopscience.iop.org/article/10.1088/1755-1315/1201/1/012040/pdf): PM2.5/PM10 dan curah hujan dari lokasi yang sama dipakai pada 2020–Agustus 2022; PM direratakan tiap tiga jam dan WXT520 merekam cuaca tiap menit. Paper hanya memakai data berflag normal `N`, sedangkan berkas NIES tidak memuat kolom flag. Studi ini sudah mengkaji kaitan hujan–PM, sehingga klaim kebaruan berdasarkan Serpong/periode/cuakanya saja tidak berlaku.
- Menelaah [paper sistem pemantauan 2019](https://iopscience.iop.org/article/10.1088/1755-1315/303/1/012038/pdf): WXT520 tercatat di Serpong dan sistem mencatat data tiap menit dengan cap waktu GPS. Tabel saat itu belum mencantumkan ACSA-14 di Serpong. Keberadaan cuaca setempat terbukti, tetapi akses arsip 2020–2022 belum terbukti.
- Menyiapkan [panduan bimbingan 28 September](PANDUAN_BIMBINGAN_2026-09-28.md): ringkasan lima paper terdekat, dua opsi penelitian, tujuh pertanyaan umum untuk dosen, serta tiga pertanyaan teknis yang hanya perlu diteruskan kepada pengelola data bila diarahkan.
- Memeriksa [dataset Benowo, Surabaya](https://data.mendeley.com/datasets/compare/md22xmgxgw) sebagai pembanding: PM2.5 dan cuaca terukur tersedia sekitar 14 bulan saja, sehingga belum memenuhi arahan tiga tahun.

## Pekerjaan berikutnya, berurutan

1. **Klarifikasi tiga hal dari pengelola dataset melalui jalur pembimbing/kampus:** (a) jendela waktu yang diwakili tiap cap waktu UTC PM2.5 tiga jam; (b) apakah berkas NIES sudah menyaring flag kualitas `A`/`X`, atau apakah flag per rekaman tersedia; (c) cara memperoleh arsip WXT520 Serpong 2020–2022 serta syarat penggunaannya. Dasar pertanyaan dan sitasi ada di catatan belajar. Belum ada permintaan data yang dikirim.
2. **Nilai meteorologi pasangan Serpong 2020–2022** jika arsip WXT520 bisa diakses; bila tidak, cek ERA5 terhadap pengamatan stasiun yang tersedia. NASA POWER baru terbukti lengkap secara cap waktu; gridnya kasar. Catat dampak bulan PM2.5 yang sepenuhnya kosong.
3. **Tambah telaah studi prediktif yang sebanding**, khususnya penelitian yang membandingkan cuaca saja dengan riwayat PM2.5 pada horizon jelas dan uji kronologis. Sepuluh naskah sudah dibaca, tetapi hanya lima prediktif. [Kang dkk. (2023)](https://www.mdpi.com/2071-1050/15/14/11408) tampak relevan dari laman penerbit; metodologi lengkapnya belum diaudit sehingga belum dihitung sebagai naskah yang ditelaah. Cari akses sah untuk naskah Procedia dan IPB; bila tidak tersedia, tandai batasnya.
4. **Siapkan dua opsi pertanyaan untuk bimbingan** dari catatan belajar: (A) estimasi konsentrasi PM2.5 tiga jam dari cuaca yang mewakili interval sama, atau (B) prakiraan konsentrasi PM2.5 pada interval tiga jam berikutnya dari riwayat yang telah tersedia. Untuk B, bandingkan baseline sederhana dengan RF serta uji pada periode lebih akhir; detail baru bisa ditentukan setelah cap waktu dan data dipastikan. Keduanya belum disebut research gap final.
5. **Bawa bukti dan hambatan ke dosen pembimbing** untuk memilih target, horizon, lokasi, dan sumber data sebelum EDA/model. Pilihan kategori perlu dasar aturan yang cocok dengan resolusi PM2.5 tiga jam; kategori per jam BMKG tidak langsung berlaku.

## Pertanyaan terbuka / hambatan

- Apakah 84,58% slot PM2.5 Serpong 2020–2022 cukup setelah melihat sebaran bulan kosong?
- Apakah meteorologi NASA POWER bergrid kasar cukup mewakili kondisi sensor Serpong, atau perlu ERA5/data stasiun? Bagaimana interval tiga jam PM2.5 ditentukan terhadap cap waktu UTC?
- Apakah data historis PM2.5 dapat diakses dan dikutip dengan jelas? Dashboard terkini belum menjawabnya.
- Jika target kategori harian, aturan kategori dan cara agregasi mana yang tepat? Jangan langsung menerapkan rentang **per jam** pada rerata harian.
- Apakah tugasnya menggambarkan kondisi bersamaan atau memprakirakan waktu mendatang? Ini menentukan fitur yang boleh digunakan dan cara evaluasi.
- Apakah paper yang sangat mirip telah membuat calon gap tertentu tidak layak? Jawab setelah telaah naskah penuh, bukan dari judul/abstrak saja.

## Catatan perubahan

| Tanggal | Perubahan | Status |
| --- | --- | --- |
| 2026-09-25 | Membuat catatan belajar dan progres; verifikasi awal aturan, 12 kandidat literatur, dan jalur data. | Kajian awal, belum keputusan desain. |
| 2026-09-26 | Menelaah naskah JMG/BMKG dan AISM; mengoreksi posisi paper Tangerang Selatan sebagai model dengan polutan sebagai fitur. | 2 naskah ditelaah; keputusan desain tetap terbuka. |
| 2026-09-26 | Menelaah naskah Palembang dan Pontianak; menandai kedekatan studi Palembang dengan judul draft dan batas evaluasi akibat interpolasi data. Menemukan petunjuk arsip PM2.5 Stamet Iskandar, belum memverifikasi rentang dan kelengkapan datanya. | 4 naskah ditelaah; akses data pengamatan asli sekitar 3 tahun menjadi prioritas. |
| 2026-09-26 | Mengaudit rekaman PM2.5 OpenAQ Jakarta Pusat/Jakarta Selatan serta NIES–BRIN Serpong. Serpong 2020–2022 menjadi kandidat paling konkret; catat bulan kosong dan resolusi 3 jam. | Data target ditemukan; meteorologi pasangan, gap, dan rancangan tetap terbuka. |
| 2026-09-26 | Menelaah paper BRIN 2025 pada lokasi/instrumen Serpong; menambahnya ke matriks literatur. | 5 dari 13 naskah ditelaah; Serpong tetap kandidat data, belum gap final. |
| 2026-09-26 | Menguji NASA POWER untuk Serpong 2020–2022; 7.416 cap waktu PM2.5 valid mempunyai lima variabel cuaca. Menandai risiko grid/elevasi dan ketidakpastian interval tiga jam; Benowo hanya 14 bulan. | Jalur data berpasangan terbukti secara waktu, kualitas pasangan dan pertanyaan penelitian masih terbuka. |
| 2026-09-26 | Menelaah JUTIF Tangerang dan dua paper AAQR tentang hubungan PM2.5–meteorologi/kriteria risiko; memperbarui matriks dan urutan kerja untuk bimbingan. | 8 dari 15 kandidat ditelaah penuh (5 prediktif, 3 konteks); desain dan gap tetap terbuka. |
| 2026-09-26 | Memeriksa silang metadata NIES dengan header data 2020; mencatat ketidakjelasan interval rerata dan syarat penggunaan untuk publikasi. | Agregasi cuaca dan definisi horizon menunggu klarifikasi cap waktu. |
| 2026-09-26 | Membaca dua paper primer sistem pengukur Serpong (2019, 2023). Menemukan bukti meteorologi WXT520 di lokasi dan studi hujan–PM pada 2020–2022; mencatat beda flag kualitas paper dengan berkas NIES. | 10 dari 17 kandidat ditelaah penuh (5 prediktif, 5 konteks); tiga pertanyaan spesifik untuk pengelola data siap. |
| 2026-09-26 | Menyiapkan bahan bimbingan Senin 28 September: penjelasan paper inti, naskah pembuka singkat, dua opsi rancangan, pertanyaan umum, dan kolom pencatatan arahan dosen. | Siap untuk diskusi; belum mengubah judul, target, atau gap menjadi keputusan final. |

Saat ada keputusan baru, catat: **tanggal, keputusan, status, alasan, sumber (terutama catatan dosen), dampak, dan tindakan berikutnya**. Jangan mengubah kandidat menjadi keputusan tanpa bukti atau arahan yang jelas.
