import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/mahasiswa.dart';

class ApiService {
  // static const String baseUrl = 'http://127.0.0.1:8000/api/mahasiswas';
  // static const String baseUrl = 'http://192.168.1.4:8000/api/mahasiswas';
  static const String baseUrl = 'http://127.0.0.1:8000/api/mahasiswas';

  Future<List<Mahasiswa>> getMahasiswas() async {
    try {
      final response = await http.get(Uri.parse(baseUrl));
      if (response.statusCode == 200) {
        final decoded = json.decode(response.body);
        final List data = decoded['data'];
        return data.map((e) => Mahasiswa.fromJson(e)).toList();
      } else {
        throw Exception('Failed to load mahasiswas');
      }
    } catch (e) {
      throw Exception('Exception while fetching data: $e');
    }
  }

  Future<Mahasiswa> createMahasiswa(Mahasiswa mahasiswa) async {
    final response = await http.post(
      Uri.parse(baseUrl),
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
      body: json.encode(mahasiswa.toJson()),
    );

    if (response.statusCode == 200 || response.statusCode == 201) {
      final decoded = json.decode(response.body);
      return Mahasiswa.fromJson(decoded['data']);
    } else {
      throw Exception('Failed to create mahasiswa: ${response.body}');
    }
  }

  Future<Mahasiswa> updateMahasiswa(int id, Mahasiswa mahasiswa) async {
    final response = await http.put(
      Uri.parse('$baseUrl/$id'),
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
      body: json.encode(mahasiswa.toJson()),
    );

    if (response.statusCode == 200) {
      final decoded = json.decode(response.body);
      return Mahasiswa.fromJson(decoded['data']);
    } else {
      throw Exception('Failed to update mahasiswa: ${response.body}');
    }
  }

  Future<void> deleteMahasiswa(int id) async {
    final response = await http.delete(
      Uri.parse('$baseUrl/$id'),
      headers: {'Accept': 'application/json'},
    );
    if (response.statusCode != 200) {
      throw Exception('Failed to delete mahasiswa: ${response.body}');
    }
  }
}
