<?php

require_once "data/Person.php";

$person = new Person("Ramona", null);
$person->name = "Ramona";
$person->address = null;

var_dump($person);

echo "Name : {$person->name}".PHP_EOL;
echo "Address : {$person->address}".PHP_EOL;
echo "Country : {$person->country}".PHP_EOL;