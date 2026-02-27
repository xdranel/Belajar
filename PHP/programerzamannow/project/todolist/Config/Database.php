<?php

namespace Config {
    class Database
    {

        static function getConnection(): \PDO
        {
            $host = "localhost";
            $port = "3306";
            $database = "belajar_php_todolist";
            $username = "root";
            $password = "plm987!";

            return new \PDO("mysql:host=$host;port=$port;dbname=$database", $username, $password);
        }
    }
}