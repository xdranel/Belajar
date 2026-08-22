<?php

function sayHello(?string $name){

    if ($name == null){
        throw new Exception("Cant Be NULL");
    }

    echo "Hello $name" . PHP_EOL;
}

function sayHelloExpression(?string $name){

    $result = $name != null ? "Hello $name" : throw new Exception("Cant Be NULL");

    echo $result . PHP_EOL;
}

sayHelloExpression("Budi");
sayHelloExpression(null);