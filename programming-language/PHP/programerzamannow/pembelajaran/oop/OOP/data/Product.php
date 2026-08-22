<?php

class Product
{
    private string $name;
    private int $price;
    protected ?string $description;

    public function __construct(string $name, int $price, string $description = "No Description")
    {
        $this->name = $name;
        $this->price = $price;
        $this->description = $description;
    }

    public function setDescription(string $description): void
    {
        $this->description = $description;
    }

    public function getName(): string
    {
        return $this->name;
    }

    public function getPrice(): int
    {
        return $this->price;
    }
}

class ProductDescription extends Product
{
    public function Description(): void
    {
        echo "Name: " . $this->getName() . PHP_EOL;
        echo "Price: " . $this->getPrice() . PHP_EOL;
        echo "Description: " . $this->description . PHP_EOL;
    }
}