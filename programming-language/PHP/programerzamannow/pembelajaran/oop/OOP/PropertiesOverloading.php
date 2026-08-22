<?php

class Zero
{
    private array $properties = [];

    public function __get($name)
    {
        return $this->properties[$name] ?? null;
    }

    public function __set($name, $value)
    {
        $this->properties[$name] = $value;
    }

    public function __isset($name): bool
    {
        return isset($this->properties[$name]);
    }

    public function __unset($name)
    {
        unset($this->properties[$name]);
    }

    public function __call($name, $arguments)
    {
        $join = join(",", $arguments);
        echo "Call Function $name with arguments $join" . PHP_EOL;
    }

    public static function __callStatic($name, $arguments)
    {
        $join = join(",", $arguments);
        echo "Call static function $name with arguments $join" . PHP_EOL;
    }
}

$zero = new Zero();
$zero->firstname = "Rifki";
$zero->lastname = "Ananta";

echo "First Name : {$zero->firstname}" . PHP_EOL;
echo "Last Name : {$zero->lastname}" . PHP_EOL;

// __get
// echo $zero->firstname . PHP_EOL;

// __set
// $zero->firstname = "Ramona";

// __isset
// isset($zero->firstname) . PHP_EOL;

// __unset
// unset($zero->firstname);

// __call
// $zero->sayHello("Budi", "Nugraha");

// __callStatic
// Zero::sayHello("Budi", "Nugraha");