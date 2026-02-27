<?php

require_once "data/SayGoodBye.php";

use Data\Traits\{Person, SayGoodBye, SayHello};

$person = new Person();
$person->goodBye("Budi");
$person->hello("Budi");

$person->name = "Eko";
var_dump($person);

$person->run();