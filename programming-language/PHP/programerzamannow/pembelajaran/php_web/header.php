<?php

header('Application: Belajar PHP Web');
header('Author: Gendhi Ramona');

$client = $_SERVER['HTTP_CLIENT_NAME'];

echo "Hello $client";
