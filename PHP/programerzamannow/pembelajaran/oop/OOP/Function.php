<?php

require_once "data/Person.php";

$person = new Person("Budi", null);
$person->name = "Budi";
$person->sayHello(null);

$person2 = new Person("Ramona", "Jl. Merpati No. 1");
$person2->name = "Ramona";
$person2->sayHello("Joko");

$person->info();
$person2->info();