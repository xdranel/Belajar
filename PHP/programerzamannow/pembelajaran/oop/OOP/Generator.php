<?php

function getGenap(int $max): Iterator
{
    // iterator from array with ArrayIterator
    $arr = [];
    for ($i = 0; $i <= $max; $i++) {
        if ($i % 2 == 0) {
            $arr[] = $i;
        }
    }
    return new ArrayIterator($arr);
}

foreach (getGenap(10) as $value) {
    echo "Genap : $value" . PHP_EOL;
}

function getGanjil(int $max): Iterator
{
    // iteration no need array
    // using yield
    for ($i = 0; $i <= $max; $i++) {
        if ($i % 2 == 1) {
            yield $i;
        }
    }
}

foreach (getGanjil(10) as $value) {
    echo "Ganjil : $value" . PHP_EOL;
}