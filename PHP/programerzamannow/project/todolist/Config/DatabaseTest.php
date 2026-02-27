<?php

use Config\Database;

require_once __DIR__ . '/Database.php';

try {
    $db = Database::getConnection();
    echo "Sukses Terhubung Dengan Database " . PHP_EOL;
} catch (PDOException $e) {
    echo "Gagal Terhubung Dengan Database " . $e->getMessage() . PHP_EOL;
}
