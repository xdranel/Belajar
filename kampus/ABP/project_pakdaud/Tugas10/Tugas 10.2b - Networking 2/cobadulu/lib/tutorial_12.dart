import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'Product.dart';

void main() => runApp(const MyApp12());

class MyApp12 extends StatefulWidget {
  const MyApp12({super.key});
  @override
  State<MyApp12> createState() => _MyApp12State();
}

class _MyApp12State extends State<MyApp12> {
  late Future<List<Product>> products;

  @override
  void initState() {
    super.initState();
    products = fetchProduct();
  }

  Future<List<Product>> fetchProduct() async {
    final res = await http.get(Uri.parse('http://10.0.2.2:8000/api/product'));
    
    if (res.statusCode == 200) {
      var data = jsonDecode(res.body);
      var parsed = data['list'].cast<Map<String, dynamic>>();
      return parsed.map<Product>((json) => Product.fromJson(json)).toList();
    } else {
      throw Exception('Gagal koneksi ke Laravel');
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text('Katalog Produk Laravel')),
        body: FutureBuilder<List<Product>>(
          future: products,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return ListView.builder(
                itemCount: snapshot.data!.length,
                itemBuilder: (context, index) {
                  var p = snapshot.data![index];
                  return Card(
                    margin: const EdgeInsets.all(8),
                    child: ListTile(
                      leading: const Icon(Icons.shopping_bag, color: Colors.blue),
                      title: Text(p.name, style: const TextStyle(fontWeight: FontWeight.bold)),
                      subtitle: Text("Rp ${p.price}"),
                    ),
                  );
                },
              );
            } else if (snapshot.hasError) {
              return Center(child: Text("Error: ${snapshot.error}"));
            }
            return const Center(child: CircularProgressIndicator());
          },
        ),
      ),
    );
  }
}