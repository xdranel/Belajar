<?php

class Product
{
    public function __construct(
        public ?string $id,
        public string $name,
        public int $price,
        public int $quantity
    ) {
    }
}

$product = new Product(id: "1", name: "Laptop", price: 1000000, quantity: 50);
var_dump($product);

echo $product->name . PHP_EOL;

