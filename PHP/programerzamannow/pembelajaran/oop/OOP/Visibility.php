<?php

require_once "data/Product.php";

$product = new ProductDescription("Apple", 5000);
$product->Description();

$product->setDescription("This is Apple with a price of 5000");
$product->Description();