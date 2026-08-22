<?php

class foo
{
    // properties
    // adding null so that it nullable
    public string|int|array|null $bar = null;
}

$dummy = new foo;
$dummy->bar = "Hello";
$dummy->bar = 1;
$dummy->bar = [];

// Function
// adding in parameter and return type
function sample(string|array $data): string|array
{
    if (is_string($data)) {
        return "String";
    } elseif (is_array($data)) {
        return ["Array"];
    }

    return $data;
}

