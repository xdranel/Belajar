<?php

require_once "data/Food.php";
require_once "data/Animal.php";
require_once "data/AnimalShelter.php";

$catShelter = new \Data\CatShelter();
$dogShelter = new \Data\DogShelter();

$cat = $catShelter->adopt("Luna");
echo "Cat Name : {$cat->name}".PHP_EOL;
$cat->run();

// Contravariance
$cat->eat(new \Data\AnimalFood());

$dog = $dogShelter->adopt("Sunshine");
echo "Dog Name : {$dog->name}".PHP_EOL;
$dog->run();

// Contravariance
$dog->eat(new \Data\AnimalFood());