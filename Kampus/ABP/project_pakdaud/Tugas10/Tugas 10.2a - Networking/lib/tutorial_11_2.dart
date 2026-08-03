import 'package:flutter/material.dart';

class Tutorial11_2 extends StatefulWidget {
  const Tutorial11_2({super.key});

  @override
  State<Tutorial11_2> createState() => _Tutorial11_2State();
}

class _Tutorial11_2State extends State<Tutorial11_2> {
  List<String> data = ["Data 1", "Data 2", "Data 3"];
  final TextEditingController _controller = TextEditingController();

  void _showAddDataDialog() {
    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: const Text("Tambah Data"),
          content: TextField(
            controller: _controller,
            decoration: const InputDecoration(hintText: "Masukkan nama data"),
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.pop(context),
              child: const Text("Batal"),
            ),
            ElevatedButton(
              onPressed: () {
                if (_controller.text.isNotEmpty) {
                  setState(() {
                    data.add(_controller.text);
                  });
                  _controller.clear();
                  Navigator.pop(context);
                }
              },
              child: const Text("Simpan"),
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("User Interaction - List"),
        backgroundColor: Colors.blue,
      ),
      body: ListView.builder(
        itemCount: data.length,
        itemBuilder: (context, index) {
          return ListTile(
            leading: const Icon(Icons.list),
            title: Text(data[index]),
            trailing: IconButton(
              icon: const Icon(Icons.delete, color: Colors.red),
              onPressed: () {
                setState(() {
                  data.removeAt(index);
                });
              },
            ),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _showAddDataDialog,
        backgroundColor: Colors.blue,
        child: const Icon(Icons.add),
      ),
    );
  }
}