<?php

function foo(string $first, string $middle = "", string $last): void
{
    echo "Hello $first $middle $last" . PHP_EOL;
}

foo(first: "Gendhi", middle: "", last: "Rifki");