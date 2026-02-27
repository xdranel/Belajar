<?php

class Manager
{
    private string $name;
    private string $title;

    public function __construct(string $name = "", string $title = "Manager")
    {
        $this->name = $name;
        $this->title = $title;
    }

    public function sayHello(string $name): void
    {
        echo "Hi $name, my name is {$this->name} My Positon is Manager" . PHP_EOL;
    }

    public function getName(string $name): String
    {
        return $this->name;
    }
}

class VicePresident extends Manager
{
    public function __construct(string $name)
    {
        parent::__construct($name, "Vice President");
    }

    public function sayHello(string $name): void
    {
        echo "Hi $name, my name is {$this->getName($name)} My Positon is Vice President" . PHP_EOL;
    }

}