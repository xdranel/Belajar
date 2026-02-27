<?php

require "helpers.php";

// asort($array);
// ksort($array);
// usort($array, fn($a, $b) => $a - $b);

$arr = ['d' => 3, 'b' => 1, 'c' => 4, 'a' => 2];

printArray($arr);

usort($arr, fn($a, $b) => $a <> $b);

printArray($arr);