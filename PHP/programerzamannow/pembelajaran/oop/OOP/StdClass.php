<?php

$array = [
    "firstName" => "Joko",
    "lastName" => "Purwanto"
];

$person = (object) $array;
var_dump($person);

echo "First Name : {$person->firstName}" . PHP_EOL;
echo "Last Name : {$person->lastName}" . PHP_EOL;