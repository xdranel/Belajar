<?php

require_once __DIR__ . '/GetConnection.php';

$connection = getConnection();

$connection->beginTransaction();

$connection->exec("INSERT INTO comments(email, comment) VALUES ('joko@example.com', 'Cok')");
$connection->exec("INSERT INTO comments(email, comment) VALUES ('eko@example.com', 'Hi')");
$connection->exec("INSERT INTO comments(email, comment) VALUES ('budi@example.com', 'LOVE')");
$connection->exec("INSERT INTO comments(email, comment) VALUES ('joko@example.com', 'Kenapa?')");
$connection->exec("INSERT INTO comments(email, comment) VALUES ('zahra@example.com', 'Hi?')");


$connection->rollBack();

$connection = null;