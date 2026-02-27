<?php

class Programmer
{
    public string $name;

    public function __construct(string $name = "")
    {
        $this->name = $name;
    }
}

class BackendProgrammer extends Programmer
{
}

class FrontendProgrammer extends Programmer
{
}

class Company extends Programmer
{
    public Programmer $programmer;
    // programmer ^^^ data type
}

// ** Function Argument Polymorphism **
// function sayHelloProgrammer(Programmer $programmer): void
// {
//     echo "Hello Programmer {$programmer->name}" . PHP_EOL;
// }

// ** Type Check and Cast **
function sayHelloProgrammer(Programmer $programmer): void
{
    if ($programmer instanceof BackendProgrammer) {
        echo "Hello Backend Programmer {$programmer->name}" . PHP_EOL;
    } else if ($programmer instanceof FrontendProgrammer) {
        echo "Hello Frontend Programmer {$programmer->name}" . PHP_EOL;
    } else if ($programmer instanceof Programmer) {
        echo "Hello Programmer {$programmer->name}" . PHP_EOL;
    }
}
