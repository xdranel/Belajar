<?php

require_once __DIR__ . '/GetConnection.php';

$connection = getConnection();

$username = "budi";
$password = "rahasia123";

$sql = "SELECT * FROM admin WHERE username = :username AND password = :password";
$statment = $connection->prepare($sql);

$statment->bindParam("username", $username);
$statment->bindParam("password", $password);
$statment->execute();

if ($row = $statment->fetch()) {
    echo "Sukses Login : " . $row["username"] . PHP_EOL;
} else {
    echo "Gagal Login" . PHP_EOL;
}

var_dump($statment->fetch());

$connection = null;
