<?php

require_once __DIR__ . '/GetConnection.php';

$connection = getConnection();

$insert = <<<SQL
    INSERT INTO customers(id,name,email)
    VALUES('C002', 'Rizky', 'Rizky@example.com');
SQL;

$delete = <<<SQL
    DELETE FROM customers
    WHERE id = 'C002';
SQL;

$connection->exec($insert);

$connection = null;