class Mahasiswa {
  final int? id;
  final String nim;
  final String nama;
  final String jenisKelamin;
  final String jurusan;
  final String alamat;

  Mahasiswa({
    this.id,
    required this.nim,
    required this.nama,
    required this.jenisKelamin,
    required this.jurusan,
    required this.alamat,
  });

  factory Mahasiswa.fromJson(Map<String, dynamic> json) {
    return Mahasiswa(
      id: json['id'],
      nim: json['nim'].toString(),
      nama: json['nama'],
      jenisKelamin: json['jenis_kelamin'],
      jurusan: json['jurusan'],
      alamat: json['alamat'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'nim': nim,
      'nama': nama,
      'jenis_kelamin': jenisKelamin,
      'jurusan': jurusan,
      'alamat': alamat,
    };
  }
}
