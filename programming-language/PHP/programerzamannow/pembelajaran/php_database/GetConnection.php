<?php

function getConnection(): PDO
{
    $host = "localhost";
    $port = 3306;
    $database = "belajar_php_database";
    $username = "root";
    $password = "plm987!";


    return new PDO(
        "mysql:host=$host:$port;dbname=$database",
        $username,
        $password
    );
}
