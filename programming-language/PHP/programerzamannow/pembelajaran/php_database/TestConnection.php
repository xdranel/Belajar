<?php

$host = "localhost";
$port = 3306;
$database = "belajar_php_database";
$username = "root";
$password = "teyeng987!";


try {
    $connection = new PDO(
        "mysql:host=$host:$port;dbname=$database",
        $username,
        $password
    );

    echo "Sukses terhubung ke database MySQL" . PHP_EOL;

    // menutup koneksi
    $connection = null;
} catch (PDOException $e) {
    echo "Gagal terhubung ke database MySQL" . $e->getMessage() . PHP_EOL;
}
