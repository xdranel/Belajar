-- Data Produk
INSERT INTO Produk (nama_produk, nama_kategori, deskripsi, harga, stok) VALUES 
('Samsung FE', 'Smartphone',
'Model Alternatif Samsung S24, Spesifikasi : Processor Exynos 2400e, RAM 8GB, ROM 256GB',
9300000, 50),
('Iphone 13', 'Smartphone',
'Iphone 13, Spesifikasi : Processor A15 Bionic, RAM 4GB, ROM 128GB',
9000000, 50),

('ROG Zephyrus G14', 'Laptop',
'Model G14, Spesifikasi : CPU Ryzen™ 9 6900 HS, RAM 32GB, Penyimpanan 1TB, GPU Radeon™ RX 6800S',
17000000, 30),
('Lenovo Legion Slim 5', 'Laptop',
'Model Slim 5, Spesifikasi : CPU Ryzen™ 7 8845HS, RAM 32GB, Penyimpanan 512GB SSD M.2, GPU RTX™ 4060',
20700000, 30),

('LG Smart TV 50 inch', 'TV',
'Model 50 inch dibekali dengan Layar Resolusi 4K UHD', 5200000, 50),
('Samsung Smart TV 55 inch', 'TV',
'Model 55 inch dibekali dengan Layar 4K UHD', 5700000, 50),

('Kemeja Oxford Long Shirt White', 'Baju',
'Dibuat dari bahan katun, Cocok digunakan untuk kegiatan Formal', 140000, 100),
('Kaos Hitam Uniqlo', 'Baju',
'Dibuat dari bahan katun, nyaman untuk kegiatan sehari-hari', 50000, 100),

('Celana Jeans Denim Hitam', 'Celana',
'Celana jeans kasual nyaman untuk kegiatan sehari-hari', 135000, 70),
('Celana Training Pria dan Wanita Hitam', 'Celana',
'Celana Training Polos HItam Cocok Untuk Kegiatan Olahraaga', 100000, 60),

('Hoodie Abu-Abu', 'Hoodie',
'Hoodie dengan kantung di depan', 150000, 50),
('Hoodie Coklat', 'Hoodie',
'Hoodie dengan kantung di depan', 150000, 50),

('Topi Hitam Polos', 'Topi',
'Topi Polos Ring Besi Baseball Premium', 15000, 100),

('Jam Tangan Pria Alexandre Christie', 'Jam',
'Jam Tangan Pria Alexandre Christie Chronograph AC 6141 MCBBRBA Black Dial Black Stainless Steel Strap',
1300000, 50),

('Wardah UV Shield', 'Skincare',
'Melindungi kulit dari paparan sinar matahari', 200000, 50),
('Cetaphil Gentle Skin Cleanser', 'Skincare',
'Pembersih kulit wajah dan tubuh cocok untuk semua jenis kulit, termasuk kulit kering hingga berjerawat',
250000, 50),

('Cushion Foundation Concealer', 'Kosmetik',
'Cover All Perfect Cushion dengan hasil akhir sempurna dalam 1 kali tap, memberikan coverage tinggi dan hasil akhir yang flawless',
105000, 70),
('Emina Glossy Stain 3 g', 'Kosmetik',
'Melty Gel Lip Tint Glossy Finish, Tahan Lama dan Melembabkan', 45000, 100);

-- Data Customer
INSERT INTO Pelanggan (nama, email, nomor_telepon, alamat) VALUES 
('Budi Nugroho', 'budinugroho@gmail.com', '+6281259481764', 'Jl. Raya Darmo No. 15, Surabaya, Jawa Timur, 60225'),
('Joko Warsito', 'jokowa@gmail.com', '+6281234567892', 'Jl. Kenjeran No. 123, Bulak, Surabaya, Jawa Timur, 60129'),
('Deni Wicaksono', 'denwi@gmail.com', '+6282123456789', 'Jl. Mayjen Sungkono No. 45, Surabaya, Jawa Timur, 60258'),
('Eko Kurniawan', 'ekokurniawan@gmail.com', '+6281834567893', 'Jl. Tidar No. 88, Gubeng, Surabaya, Jawa Timur, 60223'),
('Ferdian Hubner', 'ferdihubner@gmail.com', '+6281923456780', 'Jl. Ir. Soekarno No. 90, Mulyorejo, Surabaya, Jawa Timur, 60116'),
('Salsabila Denisiawan', 'salsadenisiawan@gmail.com', '+6282234567894', 'Jl. Ngagel Jaya Selatan No. 12, Surabaya, Jawa Timur, 60283'),
('Azizi Asadel', 'zeeasadel@gmail.com', '+6282345678900', 'Jl. Raya Juanda No. 67, Sidoarjo, Jawa Timur, 61253'),
('Nawati Aulia', 'nawatiaulia@gmail.com', '+6282134567895', 'Jl. Pahlawan No. 4, Surabaya, Jawa Timur, 60175'),
('Shinta Nugraha', 'shintanughraha@gmail.com', '+6281223456783', 'Jl. Banyu Urip No. 30, Wonokromo, Surabaya, Jawa Timur, 60243'),
('Andini Putri', 'andiniputri@gmail.com', '+6281145678902', 'Jl. Ahmad Yani No. 50, Kebraon, Surabaya, Jawa Timur, 60293');

-- Data Pesanan
INSERT INTO Pesanan (id_pelanggan, tanggal_pesanan, status_pesanan, total_harga) VALUES 
(1, '2024-12-3', 'Berhasil', 9300000),
(3, '2024-12-3', 'Menunggu', 140000),
(6, '2024-12-3', 'Gagal', 200000),

(7, '2024-12-5', 'Berhasil', 150000),

(9, '2024-12-6', 'Berhasil', 20700000),
(2, '2024-12-6', 'Berhasil', 5200000),
(5, '2024-12-6', 'Menunggu', 135000),

(4, '2024-12-8', 'Berhasil', 105000),

(4, '2024-12-9', 'Berhasil', 1300000),
(7, '2024-12-9', 'Berhasil', 140000),

(6, '2024-12-10', 'Gagal', 45000),

(8, '2024-12-13', 'Gagal', 250000),
(2, '2024-12-13', 'Berhasil', 5700000),
(1, '2024-12-13', 'Menunggu', 100000),

(10, '2024-12-15', 'Berhasil', 150000),
(3, '2024-12-15', 'Menunggu', 15000),

(7, '2024-12-16', 'Gagal', 50000),
(4, '2024-12-16', 'Berhasil', 135000),
(6, '2024-12-16', 'Gagal', 250000),

(10, '2024-12-17', 'Menunggu', 200000),

(1, '2024-12-19', 'Berhasil', 17000000),
(5, '2024-12-19', 'Gagal', 20700000),
(3, '2024-12-19', 'Berhasil', 9300000),

(2, '2024-12-20', 'Berhasil', 140000),

(6, '2024-12-21', 'Berhasil', 15000),
(8, '2024-12-21', 'Menunggu', 100000),

(4, '2024-12-23', 'Berhasil', 105000),
(2, '2024-12-23', 'Berhasil', 150000),

(9, '2024-12-24', 'Berhasil', 5700000),
(7, '2024-12-24', 'Gagal', 135000);

-- Data Detail Pesanan
INSERT INTO detail_pesanan (id_pesanan, id_produk, jumlah_produk, harga_produk) VALUES
(1, 1, 1, 9300000),
(2, 7, 1, 140000),
(3, 15, 1, 200000),
	   
(4, 11, 1, 150000),
	   
(5, 4, 1, 20700000),
(6, 5, 1, 5200000),
(7, 9, 1, 135000),
	   
(8, 17, 1, 105000),
       
(9, 14, 1, 1300000),
(10, 7, 1, 140000),
	   
(11, 18, 1, 45000),
       
(12, 16, 1, 250000),
(13, 6, 1, 5700000),
(14, 10, 1, 100000),
       
(15, 12, 1, 150000),
(16, 13, 1, 15000),
       
(17, 8, 1, 50000),
(18, 9, 1, 135000),
(19, 16, 1, 250000),
       
(20, 15, 1, 200000),

(21, 3, 1, 17000000),
(22, 4, 1, 20700000),
(23, 1, 1, 9300000),
       
(24, 7, 1, 140000),
       
(25, 13, 1, 15000),
(26, 10, 1, 100000),
       
(27, 17, 1, 105000),
(28, 11, 1, 150000),
       
(29, 6, 1, 5700000),
(30, 9, 1, 135000);
30

-- Data Pembayaran
INSERT INTO Pembayaran (id_pesanan, jenis_pembayaran, kode_pembayaran, status_pembayaran) VALUES
(1, 'Transfer Bank', 'TRF20241203001', 'Berhasil'),
(2, 'Kartu Kredit', 'CC20241203002', NULL),
(3, 'E-Wallet', 'EWL20241203003', 'Gagal'),

(4, 'Transfer Bank', 'TRF20241205004', 'Berhasil'),

(5, 'E-Wallet', 'EWL20241206005', 'Berhasil'),
(6, 'Kartu Kredit', 'CC20241206006', 'Berhasil'),
(7, 'Transfer Bank', 'TRF20241206007', NULL),

(8, 'E-Wallet', 'EWL20241208008', 'Berhasil'),

(9, 'Kartu Kredit', 'CC20241209009', 'Berhasil'),
(10, 'E-Wallet', 'EWL20241209010', 'Berhasil'),

(11, 'Transfer Bank', 'TRF20241210011', 'Gagal'),

(12, 'E-Wallet', 'EWL20241213012', 'Gagal'),
(13, 'Kartu Kredit', 'CC20241213013', 'Berhasil'),
(14, 'Transfer Bank', 'TRF20241213014', NULL),

(15, 'E-Wallet', 'EWL20241215015', 'Berhasil'),
(16, 'Kartu Kredit', 'CC20241215016', NULL),

(17, 'Transfer Bank', 'TRF20241216017', 'Gagal'),
(18, 'E-Wallet', 'EWL20241216018', 'Berhasil'),
(19, 'Kartu Kredit', 'CC20241216019', 'Gagal'),

(20, 'Transfer Bank', 'TRF20241217020', NULL),

(21, 'E-Wallet', 'EWL20241219021', 'Berhasil'),
(22, 'Kartu Kredit', 'CC20241219022', 'Gagal'),
(23, 'Transfer Bank', 'TRF20241219023', 'Berhasil'),

(24, 'E-Wallet', 'EWL20241220024', 'Berhasil'),

(25, 'Kartu Kredit', 'CC20241221025', 'Berhasil'),
(26, 'Transfer Bank', 'TRF20241221026', NULL),

(27, 'E-Wallet', 'EWL20241223027', 'Berhasil'),
(28, 'Kartu Kredit', 'CC20241223028', 'Berhasil'),

(29, 'Transfer Bank', 'TRF20241224029', 'Berhasil'),
(30, 'E-Wallet', 'EWL20241224030', 'Gagal');
XX

-- Data Pengiriman
INSERT INTO pengiriman (id_pesanan, tanggal_pengiriman, tanggal_sampai,status_pengiriman) VALUES
(1, '2024-12-03', '2024-12-05', 'Pesanan Sampai'),

(4, '2024-12-05', '2024-12-07', 'Pesanan Sampai'),

(5, '2024-12-06', '2024-12-08', 'Pesanan Sampai'),
(6, '2024-12-06', '2024-12-08', 'Pesanan Sampai'),

(8, '2024-12-08', '2024-12-10', 'Pesanan Sampai'),

(9, '2024-12-09', '2024-12-11', 'Pesanan Sampai'),
(10, '2024-12-09', '2024-12-11', 'Pesanan Sampai'),

(13, '2024-12-13', '2024-12-15', 'Pesanan Sampai'),

(15, '2024-12-15', '2024-12-16', 'Pesanan Sampai'),

(18, '2024-12-16', '2024-12-18', 'Pesanan Sampai'),

(21, '2024-12-21', '2024-12-20', 'Pesanan Sampai'),

(23, '2024-12-19', '2024-12-21', 'Pesanan Sampai'),

(24, '2024-12-20', '2024-12-22', 'Pesanan Sampai'),

(25, '2024-12-21', '2024-12-23', 'Pesanan Sampai'),

(27, '2024-12-23', '2024-12-24', 'Pesanan Sampai'),
(28, '2024-12-23', '2024-12-25', 'Pesanan Sampai'),

(29, '2024-12-24', '2024-12-25', 'Pesanan Sampai');
XX

-- Select Kali Aja Keluar
-- Mencari Data Pelanggan Yang Memesan Kurang Dari X
SELECT p.id_pelanggan, pel.nama, pel.email, pel.nomor_telepon, pel.alamat 
FROM Pesanan p 
JOIN Pelanggan pel ON p.id_pelanggan = pel.id_pelanggan 
GROUP BY p.id_pelanggan 
HAVING COUNT(p.id_pesanan) = 2;


