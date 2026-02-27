<?php

// Match Expression
// function getGrade(): string
// {
//     echo "Input Grade : ";
//     $value = fgetc(STDIN);
//     $result = match ($value) {
//         "A", "B" => "You Passed" . PHP_EOL,
//         "C", => "Remedial" . PHP_EOL,
//         "D" => "You Failed" . PHP_EOL,
//         default => "Invalid Value" . PHP_EOL
//     };

//     return $result;
// };

// echo getGrade();

// // Match Expression Non-Equals
// function getGradeNonEquals(): string
// {
//     echo "Input Grade : ";
//     $value = (int) fgets(STDIN);
//     // using true
//     $result = match (true) {
//         $value >= 80 => "A" . PHP_EOL,
//         $value >= 70 => "B" . PHP_EOL,
//         $value >= 60 => "C" . PHP_EOL,
//         $value >= 50 => "D" . PHP_EOL,
//         default => "Invalid Value" . PHP_EOL
//     };

//     return $result;
// };

// echo "Your Grade : " . getGradeNonEquals();

function sayHelloName(): string
{
    echo "Input Name : ";
    $name = (string) fgets(STDIN);
    $result = match (true) {
        str_contains($name, "Mr. ") => "Sir" . PHP_EOL,
        str_contains($name, "Mrs. ") => "Madam" . PHP_EOL,
        default => "Invalid Name Start with Mr/Mrs."
    };

    return $result;
}

echo "Greetings " . sayHelloName();
