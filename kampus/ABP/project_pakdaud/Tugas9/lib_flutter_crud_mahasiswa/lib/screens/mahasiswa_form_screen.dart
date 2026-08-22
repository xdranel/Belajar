import 'package:flutter/material.dart';
import '../models/mahasiswa.dart';
import '../services/api_service.dart';

class MahasiswaFormScreen extends StatefulWidget {
  final Mahasiswa? mahasiswa;

  const MahasiswaFormScreen({super.key, this.mahasiswa});

  @override
  State<MahasiswaFormScreen> createState() => _MahasiswaFormScreenState();
}

class _MahasiswaFormScreenState extends State<MahasiswaFormScreen> {
  final _formKey = GlobalKey<FormState>();
  final _apiService = ApiService();
  
  late TextEditingController _nimController;
  late TextEditingController _namaController;
  late TextEditingController _jurusanController;
  late TextEditingController _alamatController;
  
  String _jenisKelamin = 'L'; // Default L
  bool _isLoading = false;

  @override
  void initState() {
    super.initState();
    _nimController = TextEditingController(text: widget.mahasiswa?.nim ?? '');
    _namaController = TextEditingController(text: widget.mahasiswa?.nama ?? '');
    _jurusanController = TextEditingController(text: widget.mahasiswa?.jurusan ?? '');
    _alamatController = TextEditingController(text: widget.mahasiswa?.alamat ?? '');
    
    if (widget.mahasiswa != null) {
      _jenisKelamin = widget.mahasiswa!.jenisKelamin;
    }
  }

  @override
  void dispose() {
    _nimController.dispose();
    _namaController.dispose();
    _jurusanController.dispose();
    _alamatController.dispose();
    super.dispose();
  }

  Future<void> _submit() async {
    if (!_formKey.currentState!.validate()) return;

    setState(() => _isLoading = true);

    final mhs = Mahasiswa(
      id: widget.mahasiswa?.id,
      nim: _nimController.text,
      nama: _namaController.text,
      jenisKelamin: _jenisKelamin,
      jurusan: _jurusanController.text,
      alamat: _alamatController.text,
    );

    try {
      if (widget.mahasiswa == null) {
        await _apiService.createMahasiswa(mhs);
        if (mounted) {
          ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(content: Text('Berhasil menambahkan mahasiswa')),
          );
        }
      } else {
        await _apiService.updateMahasiswa(mhs.id!, mhs);
        if (mounted) {
          ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(content: Text('Berhasil memperbarui mahasiswa')),
          );
        }
      }
      if (mounted) Navigator.pop(context, true); // Return true indicating success
    } catch (e) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Error: $e'), backgroundColor: Colors.red),
        );
      }
    } finally {
      if (mounted) setState(() => _isLoading = false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.mahasiswa == null ? 'Tambah Mahasiswa' : 'Edit Mahasiswa'),
      ),
      body: _isLoading 
        ? const Center(child: CircularProgressIndicator())
        : SingleChildScrollView(
            padding: const EdgeInsets.all(16.0),
            child: Form(
              key: _formKey,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  TextFormField(
                    controller: _nimController,
                    decoration: const InputDecoration(labelText: 'NIM'),
                    keyboardType: TextInputType.number,
                    validator: (v) => v!.isEmpty ? 'Wajib diisi' : null,
                  ),
                  const SizedBox(height: 16),
                  TextFormField(
                    controller: _namaController,
                    decoration: const InputDecoration(labelText: 'Nama Lengkap'),
                    validator: (v) => v!.isEmpty ? 'Wajib diisi' : null,
                  ),
                  const SizedBox(height: 16),
                  DropdownButtonFormField<String>(
                    initialValue: _jenisKelamin,
                    decoration: const InputDecoration(labelText: 'Jenis Kelamin'),
                    items: const [
                      DropdownMenuItem(value: 'L', child: Text('Laki-laki (L)')),
                      DropdownMenuItem(value: 'P', child: Text('Perempuan (P)')),
                    ],
                    onChanged: (val) {
                      setState(() {
                        if (val != null) _jenisKelamin = val;
                      });
                    },
                  ),
                  const SizedBox(height: 16),
                  TextFormField(
                    controller: _jurusanController,
                    decoration: const InputDecoration(labelText: 'Jurusan'),
                    validator: (v) => v!.isEmpty ? 'Wajib diisi' : null,
                  ),
                  const SizedBox(height: 16),
                  TextFormField(
                    controller: _alamatController,
                    decoration: const InputDecoration(labelText: 'Alamat'),
                    maxLines: 3,
                    validator: (v) => v!.isEmpty ? 'Wajib diisi' : null,
                  ),
                  const SizedBox(height: 32),
                  ElevatedButton(
                    onPressed: _submit,
                    child: Text(widget.mahasiswa == null ? 'Simpan' : 'Perbarui'),
                  ),
                ],
              ),
            ),
          ),
    );
  }
}
