<?php

require_once __DIR__ . '/GetConnection.php';

$connection = getConnection();

$username = "ramona";
$password = "rahasia";

$sql = "SELECT * FROM admin WHERE username = :username AND password = :password";
$statment = $connection->prepare($sql);

$statment->bindParam("username", $username);
$statment->bindParam("password", $password);
$statment->execute();

$success = false;
$find_user = null;
foreach ($statment as $row) {
    // Sukses
    $success = true;
    $find_user = $row["username"];
}

if ($success){
    echo "Sukses Login : " . $find_user . PHP_EOL;
} else {
    echo "Gagal Login" . PHP_EOL;
}

$connection = null;