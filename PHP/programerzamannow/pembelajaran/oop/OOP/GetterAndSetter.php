<?php

require_once "data/Category.php";

use Data\{Category};

$category = new Category();
$category->setName("Laptop");
$category->setExpensive(true);

// not get validate vvvv
// $category->setName("                           ");
echo "Name : {$category->getName()}" . PHP_EOL;
echo "Expensive : {$category->isExpensive()}" . PHP_EOL;