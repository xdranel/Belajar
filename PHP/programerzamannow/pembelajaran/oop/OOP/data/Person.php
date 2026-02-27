<?php

class Person
{
    public const AUTHOR = "Gendhi Ramona";
    public string $name;
    public ?string $address = null;
    public string $country = "Indonesia";

    public function __construct(string $name, ?string $address)
    {
        $this->name = $name;
        $this->address = $address;
    }

    public function sayHello(?string $name)
    {
        if (is_null($name)) {
            echo "Hi, my name is $this->name" . PHP_EOL;
        } else {
            echo "Hi $name, my name is $this->name" . PHP_EOL;
        }
    }

    public function info()
    {
        echo "Author : " . self::AUTHOR . PHP_EOL;
    }

    public function __destruct()
    {
        echo "Object Person {$this->name} has been destroyed" . PHP_EOL;
    }
}
