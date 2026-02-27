<?php

interface HelloWorld
{
    function sayHello();
}

// Class Implementation
// class SampleHelloWorld implements HelloWorld
// {
//     public function sayHello(): void
//     {
//         echo "Hello World" . PHP_EOL;
//     }
// }

$helloWorld = new class ("Eko") implements HelloWorld {

    public string $name;

    public function __construct(string $name)
    {
        $this->name = $name;
    }

    public function sayHello(): void
    {
        echo "Hello {$this->name}" . PHP_EOL;
    }
};

$helloWorld->sayHello();

