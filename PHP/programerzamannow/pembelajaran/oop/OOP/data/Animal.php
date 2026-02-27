<?php

namespace Data;

require_once "data/Food.php";

abstract class Animal
{
    public string $name;

    abstract public function run(): void;

    abstract public function eat(AnimalFood $animal): void;
}

class Cat extends Animal
{
    public function run(): void
    {
        echo "{$this->name} is running" . PHP_EOL;
    }

    public function eat(AnimalFood $animal): void
    {
        echo "Cat is eating" . PHP_EOL;
    }

}

class Dog extends Animal
{
    public function run(): void
    {
        echo "{$this->name} is running" . PHP_EOL;
    }

    // Contravariance opposite of Covariance
    // making child become parent by using contravariance
    public function eat(Food $animal): void
    {
        echo "Dog is eating" . PHP_EOL;
    }
}