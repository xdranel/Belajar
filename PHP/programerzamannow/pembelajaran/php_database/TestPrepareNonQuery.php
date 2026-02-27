<?php

require_once __DIR__ . '/GetConnection.php';

$connection = getConnection();

$username = "budi";
$password = "rahasia123";

$sql = "INSERT INTO admin(username, password) VALUES(:username, :password)";
$statment = $connection->prepare($sql);

$statment->bindParam("username", $username);
$statment->bindParam("password", $password);
$statment->execute();

$connection = null;