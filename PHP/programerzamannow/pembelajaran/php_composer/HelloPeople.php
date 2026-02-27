<?php

use Grp\Data\People;

require_once __DIR__ . '/vendor/autoload.php';

$people = new People("Joko");

echo $people->sayHello("Theodor") . PHP_EOL;