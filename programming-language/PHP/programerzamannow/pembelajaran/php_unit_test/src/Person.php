<?php

namespace Grp\Test;

class Person
{
    public function __construct(private string $name)
    {
    }

    public function sayHello(?string $name): string
    {
        if ($name == null) throw new \Exception("Person name is null");

        return "Hello $name, my name is {$this->name}";
    }

    public function sayGoodbye(?string $name): void
    {
        echo "Goodbye $name" . PHP_EOL;
    }
}