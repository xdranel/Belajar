<?php

require_once __DIR__ . '/GetConnection.php';

$connection = getConnection();

$select = "SELECT id, name, email FROM customers";
$statment = $connection->query($select);

foreach ($statment as $row) {
    $id = $row["id"];
    $name = $row["name"];
    $email = $row["email"];

    echo "ID: $id, Name: $name, Email: $email" . PHP_EOL;
}