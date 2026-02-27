<?php

/**

######## List of Study ########
# 1. Constant & Variable Variables

# 2. Data Types & Type Casting
# 3. Boolean
# 4. Integer
# 5. Float
# 6. String, Heredoc, Nowdoc
# 7. NULL
# 8. Array

# 9. Operators
# 10. Arithmetic Operators
# 11. Assignment Operators
# 12. String Operators
# 13. Comparison Operators
# 14. Error Control Operators
# 15. Increment/Decrement Operators
# 16. Logical Operators
# 17. Bitwise Operators
# 18. Array Operators
# 19. Operator Precedence & Associativity

# 20. Control Structures
# 21. Loops
# 22. Switch & Match
# 23. return, declare, goto

# 24. Require, Require_Once, Include, Include_Once
# 25. Function 1
# 26. Variable Scope
# 27. Function 2

# 28. Date & Time

# 29. Function 3

# 30. Error Handling

# 31. Working With Filesystem



========================================
Start Of Section 1

# 1. Constant & Variable Variables
# Constants
# Variables


===============
** Constants **

-- Use constants when you have static data that doesn't change too often

-- 2 way to declare constants
define($name, $value);
or
const $name = $value;

-- for defined function it will check if the constant is defined or not, so the return value will be true or false
echo defined(constant_name);

-- The main difference between const and define is that const is a compile-time constant, while define is a run-time constant.
-- that mean you can't use const inside a control structure, but you can use define inside a control structure.

-- you can make define as dynamic(simple one)

$paid = 'PAID';
define('STATUS_' . $paid, PAID);

echo STATUS_PAID;
-- output: PAID

-- PHP also provide predefined and magic const like PHP_VERSION,


========================
** Variable Variables **

$foo = 'bar';
$$foo = 'baz';

echo $foo , $bar;
echo $foo , $$foo;
echo "$foo , {$$foo}";
echo "$foo , ${$foo}";
-- Output : bar baz

End Of Section 1
========================================
Start Of Section 2

# 2. Data Types & Type Casting
# Data Types
# Type Casting


================
** Data Types **

# 4 Scalar Types
- bool : true or false
- int : 1, 10, 100
- float : 1.1, 10.1
- string : "hello", 'hello'

# 4 Compound Types
- array :  $foo = array(1, 2.1, 'hello', true); or $foo = [1, 2.1, 'hello', true];
- object
- callable
- iterable

# 2 Special Types
- resource
- null

-- To check the data type of a variable, use the gettype() function or var_dump()
echo gettype($foo);
var_dump($foo);

-- Dont forget that PHP have feature Type Juggling

declare(strict_types=1);
-- strict mode
-- it will throw error if the data type is not match
-- highly recommended


==================
** Type Casting **

-- PHP also provide a few built-in type casting functions, such as int(), float(), bool(), str(), array(), etc.
-- Force a variable to a specific data type

$foo = 'bar';
$bar = (int) $foo;
echo $bar . PHP_EOL;


End Of Section 2
========================================
Start Of Section 3

# 3. Boolean

-- Boolean is a data type that can be true or false

integers 0 -0 = false
float 0.0 -0.0 = false
'' = false
'0' = false
[] empty array = false
null = false

-- you can check if a variable is a boolean using the is_bool() function

$foo = (string) true;
var_dump(is_bool($foo)); | output: bool(false)

$foo = 'false'; | output: bool(true)


End Of Section 3
========================================
Start Of Section 4

# 4. Integer
# Casting

-- Integer is a data type that can be positive or negative
-- you can use base 10, base 16, octal, binary
$foo = 5;
$foo = 0x2A;
$foo = 0b101;
$foo = 012;

PHP_INT_MIN; | output: -9223372036854775808
PHP_INT_MAX; | output: 9223372036854775807
-- becareful of overflow

=============
** Casting **

$foo = (int) ture; | output: 1
$foo = (int) false; | output: 0
$foo = (int) 5.5; | output: 5
$foo = (int) '5.9'; | output: 5
$foo = (int) '87awd'; | output: 87 | PHP will try to convert the string to an integer
however, if the string cannot be converted to an integer, it will return 0

$foo = 1_000_000; | output: 1000000
-- keep in mind when you using underscore and want to convert to integer dont do it from string to integer

-- you can use is_int() to check if a variable is an integer or not


End Of Section 4
========================================
Start Of Section 5

# 5. Float
# Casting

-- Floating number is a decimal number

$foo = 5.5;
$foo = 5.5e2; | output: 55
$foo = 5.5e-2; | output: 0.055
$foo = 5.5e+2; | output: 55
$foo = 5_000.55; | output: 5000.55

PHP_FLOAT_MIN; | output: 1.4E-45
PHP_FLOAT_MAX; | output: 3.4E+38
-- becareful of overflow
-- be very carefull when using floating number
-- never compare floating number directly

echo log(-1); | output: nan
echo PHP_FLOAT_MAX * 2; | output: inf


=============
** Casting **

$foo = (float) true; | output: 1.0
$foo = (float) false; | output: 0.0
$foo = (float) 5; | output: 5.0
$foo = (float) '5.9'; | output: 5.9
$foo = (float) '87awd'; | output: 87.0 | PHP will try to convert the string to a float
however, if the string cannot be converted to a float, it will return 0.0


-- you can use is_float() to check if a variable is a float or not

End Of Section 5
========================================
Start Of Section 6

# 6. String, Heredoc, Nowdoc
# String
# Heredoc & Nowdoc

============
** String **

-- String is a data type that can be a sequence of characters

$foo = 'hello';
$foo = "hello";

-- single quote cant accept variable, while double quote can accept variable
$foo = 'Daniel';
$bar = "{$foo} Ren";

-- Accessing characters in a string
echo $foo[1]; | output: a
echo $foo[-2]; | output: e


======================
** Heredoc & Nowdoc **

$x = 1;
$y = 2;

-- Double quote
// Heredoc
$text = <<<TEXT
Line 1 $x
Line 2 $y
Line 3
TEXT;

echo nl2br($text);

-- Single quote
// Nowdoc
$text = <<<'TEXT'
Line 1 $x
Line 2 $y
Line 3
TEXT;

-- The use of heredoc and nowdoc is when you want to use multi line string
-- or when you have html code in your string


End Of Section 6
========================================
Start Of Section 7

# 7. NULL

-- NULL is a data type that can be empty
-- case insensitive


End Of Section 7
========================================
Start Of Section 8

# 8. Array
# Array
# Map
# Array Inside Array
# Casting


===========
** Array **

-- Array is a data type that can be a sequence of values

$foo = array(1, 2.1, 'hello', true);
or
$foo = [1, 2.1, 'hello', true];

-- Accessing elements in an array
echo $foo[0]; | output: 1
echo $foo[1]; | output: 2.1
echo $foo[2]; | output: hello
echo $foo[3]; | output: 1

-- Adding elements to an array
$foo[] = 'world';
echo $foo[4]; | output: world

array_push($foo, 'world');
echo $foo[4]; | output: world

-- Modifying elements in an array
$foo[0] = 'Ramona';
echo $foo[0]; | output: Ramona

-- Removing elements from an array
unset($foo[0]);
echo $foo[0]; | output: undefined

array_pop($foo);
echo $foo[3]; | output: undefined

array_shift($foo);
echo $foo[0]; | output: undefined

-- Checking if an element exists in an array
echo isset($foo[0]); | output: true
echo isset($foo[1]); | output: false

-- Counting the number of elements in an array
echo count($foo); | output: 3


=========
** Map **

-- Map is a data type that can be a sequence of key-value pairs
-- They is either string key or integer key
-- Key null is an empty string

$foo = [
    'PHP' => '8.3',
    'Python' => '3.11',
    'JavaScript' => '17'
];

-- Accessing elements in a map
echo $foo['PHP']; | output: 8.3

-- Adding elements to a map
$foo['Java'] = '17';
echo $foo['Java']; | output: 17

-- Modifying elements in a map
$foo['PHP'] = '8.4';
echo $foo['PHP']; | output: 8.4

-- Removing elements from a map
unset($foo['PHP']);
echo $foo['PHP']; | output: undefined

-- Checking if an element exists in a map
echo isset($foo['PHP']); | output: false

$foo = [
    ['PHP', '8.3'],
    ['Python', '3.11'],
    ['JavaScript', '17']
];


========================
** Array Inside Array **

$foo = [
    'PHP' => [
        'Creator' => 'Rasmus Lerdorf',
        'Extension' => '.php',
        'Website' => 'php.net',
        'Versions' =>  [
            ['Version' => '8.3','Release' => '2023']
            ['Version' => '8.2','Release' => '2022']
        ]
    ],
];


-- Accessing elements in an array inside an array
echo $foo['PHP']['Versions'][0]['Version']; | output: 8.3

-- Adding elements to an array inside an array
$foo['PHP']['Versions'][] = ['Version' => '8.4','Release' => '2023'];
echo $foo['PHP']['Versions'][2]['Version']; | output: 8.4

-- Modifying elements in an array inside an array
$foo['PHP']['Versions'][0]['Version'] = '8.4';

-- Removing elements from an array inside an array
unset($foo['PHP']['Versions'][0]);
echo $foo['PHP']['Versions'][0]['Version']; | output: undefined

-- Checking if an element exists in an array inside an array
echo isset($foo['PHP']['Versions'][0]); | output: false


=============
** Casting **

$foo = (array) true; | output: [1]
$foo = (array) false; | output: []
$foo = (array) 5; | output: [5]
$foo = (array) '5.9'; | output: ['5.9']
$foo = (array) '87awd'; | output: ['87awd']
$foo = null; | output: []


-- use array_key_exists() to check if a key exists in an array
var_dump(array_key_exists('PHP', $foo)); | output: true


End Of Section 8
========================================
Start Of Section 9

# 9. Operators

// Arithmetic Operators (+ - * / % **)

// Assignment Operators (= += -= *= /= %= **=)

// String Operators (. .=)

// Comparison Operators (== != > < >= <= === !== <> << >> <=> ?? ?:)

// Error Control Operators (@)

// Increment/Decrement Operators (++ --)

// Logical Operators (&& || ! and or xor)

// Bitwise Operators (& | ^ ~ << >>)

// Array Operators (+ == === != !== <>)

// Execution Operators (``)

// Type Operators (instanceof)

// Null Coalescing Operator (??)


End Of Section 9
========================================
Start Of Section 10

# 10. Arithmetic Operators

$foo = 5;
$bar = 2;

echo $foo + $bar; | output: 7
echo $foo - $bar; | output: 3
echo $foo * $bar; | output: 10
echo $foo / $bar; | output: 2.5
echo $foo % $bar; | output: 1
echo $foo ** $bar; | output: 25


End Of Section 10
========================================
Start Of Section 11

# 11. Assignment Operators

$foo = 5;
$foo += 5;
echo $foo; | output: 10
$foo -= 5;
echo $foo; | output: 5
$foo *= 5;
echo $foo; | output: 25
$foo /= 5;
echo $foo; | output: 1
$foo %= 5;
echo $foo; | output: 0
$foo **= 5;
echo $foo; | output: 3125


End Of Section 11
========================================
Start Of Section 12

# 12. String Operators

$foo = 'hello';
$bar = 'world';
$foo .= $bar;
echo $foo; | output: helloworld

$baz = $foo . $bar;
echo $baz; | output: helloworld

$baz = $foo . '' . $bar;
echo $baz; | output: hello world


End Of Section 12
========================================
Start Of Section 13

# 13. Comparison Operators

$foo = 5;
$bar = 2;

echo $foo == $bar; | output: false
echo $foo != $bar; | output: true
echo $foo > $bar; | output: true
echo $foo < $bar; | output: false
echo $foo >= $bar; | output: true
echo $foo <= $bar; | output: false
echo $foo === $bar; | output: false
echo $foo !== $bar; | output: true

for == it is used to compare the values of two variables,
while for === it is used to compare the values and the data types of two variables

-- ternary operator
echo ($foo == $bar) ? 'true' : 'false';

-- null coalescing operator
echo $foo ?? $bar;


End Of Section 13
========================================
Start Of Section 14

# 14. Error Control Operators

-- suppress error

$foo = @file('file.txt'); | No error displayed

-- not recommended to use


End Of Section 14
========================================
Start Of Section 15

# 15. Increment/Decrement Operators

** Post **
$foo = 5;
echo $foo++; | output: 5
echo $foo; | output: 6

echo $foo--; | output: 5
echo $foo; | output: 4

** Pre **
$foo = 5;
echo ++$foo; | output: 6
echo --$foo; | output: 4


End Of Section 15
========================================
Start Of Section 16

# 16. Logical Operators

$foo = 5;
$bar = 2;

var_dump($foo && $bar); | output: bool(false)
var_dump($foo || $bar); | output: bool(true)
var_dump($foo xor $bar); | output: bool(true)
var_dump(!$foo || $bar); | output: bool(true)

for and, or, xor it is used to compare the values of two variables
but remember their precedence is lower than the other operators


End Of Section 16
========================================
Start Of Section 17

# 17. Bitwise Operators

$foo = 5;
$bar = 2;

echo $foo & $bar; | output: 0
echo $foo | $bar; | output: 7
echo $foo ^ $bar; | output: 7
echo ~$foo; | output: -6
echo $foo << $bar; | output: 20
echo $foo >> $bar; | output: 1


End Of Section 17
========================================
Start Of Section 18

# 18. Array Operators

$foo = array(1, 2, 3);
$bar = array(4, 5, 6);

echo $foo + $bar; | output: [1, 2, 3, 4, 5, 6]
echo $foo == $bar; | output: false
echo $foo === $bar; | output: false
echo $foo != $bar; | output: true
echo $foo !== $bar; | output: true
echo $foo <> $bar; | output: true


End Of Section 18
========================================
Start Of Section 19

# 19. Operator Precedence & Associativity

read php.net/manual/en/language.operators.precedence.php

() bracket have the highest precedence


End Of Section 19
========================================
Start Of Section 20

# 20. Control Structures

(if / else / elseif / else if)

if ($condition) {
    // code
} else if ($condition) {
    // code
} else {
    // code
}

// elseif, lets say in html
<body>
    <?php $score = 95 ?>
    <?php if ($score >= 80) : ?>
        <h1>Great</h1>
    <?php elseif ($score >= 60) : ?>
        <h1>Good</h1>
    <?php else : ?>
        <h1>Bad</h1>
    <?php endif ?>
</body>


End Of Section 20
========================================
Start Of Section 21

# 21. Loops
# While
# do-while
# for
# foreach
# Break & Continue


===========
** while **

$i = 0;

while ($i < 10) {
    echo $i++;
}

// In html

while ($1 < 10):
    echo $i++;
endwhile;

==============
** do-while **

$i = 0;

do {
    echo $i++;
} while ($i < 10);

=========
** for **

for ($i = 0; $i < 10; $i++) {
    echo $i;
}

// In html

for ($i = 0; $i < 10; $i++):
    echo $i;
endfor;

=============
** foreach **

$foo = [1, 2, 3, 4, 5];

foreach ($foo as $bar) {
    echo $bar;
}

foreach ($foo as $key => $value) {
    echo $key . ': ' . $value;
}

// In html

foreach ($foo as $key => $value):
    echo $key . ': ' . $value;
endforeach;


// Example with refrence

foreach ($foo as $key => &$value) {
    echo $key . ': ' . $value;
}
-- if you working with reference be very carefull. because even after the loop is over the reference will still be there, so if you were to change
the value the last value in the index of array will be the that change
$value = 10;
echo $value;
print_r($foo);
-- you can check this by using the print_r() function
-- so you need to unset the value after the loop is over

// Example foreach
$user = [
    "name" => "Ramona",
    "email" => "Bw0d2@example.com",
    "skills" => ['php', 'javascript', 'python']
];

-- to print out this
# 1.
foreach ($user as $key => $value) {
    echo $key . ':' . json_encode($value) . PHP_EOL;
}

# 2. using implode only if its an array
foreach ($user as $key => $value) {
    if (is_array($value)) {
        $value = implode(',', $value);
    }
    echo $key . ':' . $value . PHP_EOL;
}

# 3. nested foreach
foreach ($user as $key => $value) {
    echo $key . ':';
    if (is_array($value)) {
        foreach ($value as $skill) {
            echo $skill . ' - ';
        }
    } else {
        echo $value . PHP_EOL;
    }
}



======================
** Break & Continue **

for ($i = 0; $i < 10; $i++) {
    if ($i === 5) {
        break;
    }
    echo $i;
}

for ($i = 0; $i < 10; $i++) {
    if ($i === 5) {
        continue;
    }
    echo $i;
}

// example
for ($i = 0; $i < 10; $i++) {
    if ($i === 5) {
        break 2;
    }
    echo $i;
}
-- if you use for example break 2; that means break out of the 2 loops, so it will break out of the for loop and the while loop


End Of Section 21
========================================
Start Of Section 22

# 22. Switch & Match
# Switch
# Match


============
** Switch **

-- switch doin loose comparison
$paymentstatus = 1;

switch ($paymentstatus) {
    case 1:
        echo 'paid';
        break;
    case 2:
        echo 'Payment Pending';
        break;
    case 3:
    case 4:
        echo 'Payment Declined';
        break;
    default:
        echo 'unknown';
        break;
}

// Switch ==
// Match ===

===========
** Match **

-- match doing strict comparison
$paymentStatus = match ($paymentstatus) {
    1 > 2 => 'Paid',
    2 => 'Payment Pending',
    3, 4 => 'Payment Declined',
    default => 'Unknown',
};

echo $paymentStatus;


End Of Section 22
========================================
Start Of Section 23

# 23. return, declare, goto
# return
# declare
# goto


============
** return **

function sum()
function sum($a, $b) {
    $z = $a + $b;
    return $z;
}

$x = sum(5,10);
echo $x;

-- always remember PHP have type juggling


=============
** declare **

// ticks

// encoding

// strict_types
declare(strict_types=1);

End Of Section 23
========================================
Start Of Section 24

# 24. Require, Require_once, Include, Include_once


End Of Section 24
========================================
Start Of Section 25

# 25. Function 1
# return type
# arguments, parameters

function foo()
{
    echo "foo";
}

-- remember you can declare wherever you want but there is some conditions that you will not declare wherever
1. function inside control structures
2. function inside function
-- because it need to execute first


=================
** return type **

// function with return type
fucntion foo():int
{
    return 1;
}

// function with nullable return type
function foo():?int
{
    return 1;
}

// function with multiple return type
function foo(): int|float|array
{
    return [1.5];
}

or

function foo(): mixed
{
    return [1.5];
}


===========================
** arguments, parameters **

function foo(int $x, int $y) {
    paramater^^^^^^^^^^^^^

    return $x * $y;
}


echo foo(5.0, 10);
argument^^^^^^^^

// function accepting multiple paramaters
function foo(int|float $x, int|float $y) {
    return $x * $y;
}


// passing paramaters if you dont know how many
the three dot(...) means that you can pass any number of arguments but it will be an array
function foo(...$number) {
    $sum = 0;
    foreach ($number as $value) {
        $sum += $value;
    }
    return $sum;

    or

    return array_sum($number);
}
echo foo(1, 2, 3, 4, 5);


// Named arguments

function foo(int $x, int $y): int {
    if ($x % $y === 0) {
        return $x / $y;
    }
    return $x;
}

$x = 6;
$y = 2;

echo foo(y: $y, x: $x);

End Of Section 25
========================================
Start Of Section 26

# 26. Variable Scope

// Global scope
$x = 1;
function foo() {
    global $x;
    $x = 2;
}
foo();
echo $x;

// Local scope
function foo() {
    $x = 1;
    echo $x;
}
foo();

// Static scope
function foo() {
    static $x = 1;
    echo $x;
    $x++;
}
foo();


End Of Section 26
========================================
Start Of Section 27

# 27. Function 2
# Variable Function
# Anonymous Function
# Callable Function
# Arrow Function


=======================
** Variable Function **

-- variable function will not work with language constructs

function sub(int|float ...$number): int|float
{
    return array_sum($number);
}

$x = 'sum';

if (is_callable($x)) {
    echo $x(1,2,3,4,5);
} else {
    echo 'not callable';
}


========================
** Anonymous Function **

$x = 1;
$sum = function (int|float ...$number) use ($x): int|float {
    return array_sum($number);         ^^^ Closure

    the closure itself copies the variable from the outer scope
    so if you want to also change the variable inside the closure, you need to use reference use (&x)
}
echo $sum(1,2,3,4,5);


=======================
** Callable Function **

$array = [1,2,3,4];

$array2 = array_map(function ($element) {
    return $element * 2;
}, $array);

or

$array = [1,2,3,4];

$x = function ($element) {
    return $element * 2;
};

$array2 = array_map($x, $array);

or

$array = [1,2,3,4];

function foo($element) {
    return $element * 2;
};

$array2 = array_map('foo', $array);


// using previous example

$sum = function (callable $callback, int|float ...$number): int|float {
        return $callback(array_sum($number));
}
echo $sum('foo', 1,2,3,4,5);

function foo($element) {
    return $element * 2;
}

or

echo $sum(function foo($element) {
    return $element * 2;
}, 1,2,3,4,5);

function foo($element) {
    return $element * 2;
}


====================
** Arrow Function **

$array = [1,2,3,4];

$array2 = array_map(fn ($element) => $element * 2, $array);
// callback function

// Few differences
-- you dont need to use the "use" keyword if you want to use variables from the outer scope
-- you cant modify variables from the outer scope yes you can do for example ++$x but it only happens inside the closure
-- arrow functions cant have multi-line expressions




End Of Section 27
========================================
Start Of Section 28

# 28. Date & Time

$currentTime = time();

echo $currentTime . '<br>';

echo $currentTime + 5 * 24 * 60 * 60 . '<br>';
// 5 days
echo $currentTime - 60 * 60 * 24 . '<br>';
// 1 day
echo date('d/m/y g:ia', $currentTime + 5 * 24 * 60 * 60) .'<br>';
// 5 days
echo date('d/m/y g:ia', $currentTime - 60 * 60 * 24) .'<br>';
// 1 day


End Of Section 28
========================================
Start Of Section 29

# 29. Function 3
# array_chunk
# array_combine
# array_filter
# array_keys
# array_map
# array_merge
# array_reduce
# array_search

require "helpers.php";

** array_chunk(array $array, int $length, bool $preserve_keys = false): array
-- splits the array into chunks of the specified length

$items = [
    'a' => 1,
    'b' => 2,
    'c' => 3,
    'd' => 4,
    'e' => 5,
];
printArray(array_chunk($items, 2));
printArray(array_chunk($items, 2, true));


** array_combine(array $keys, array $values): array
-- creates an array from the given keys and values

$arr1 = ['a', 'b', 'c', 'd'];
$arr2 = [5, 7, 9];

printArray(array_combine($arr1, $arr2));
-- if the size of array not match it will throw an error


** array_filter(array $array, callback $callback, int $flag = 0): array
-- iterates through the array and returns a new array with only the elements that
pass the callback test

$arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

$even = array_filter($arr, fn($number, $key) => $number %2 === 0, ARRAY_FILTER_USE_BOTH);

$even = array_values($even);
// indexes will be reset

printArray($even);


** array_keys(array $keys, mixed $search_value, bool $strict = false): array
-- returns an array of all the keys in the array that have the specified search value

$arr = [
    "a" => "5",
    "b" => "10",
    "c" => "15",
    "d" => "5",
    "e" => "10",
];

$keys = array_keys($arr, 15, true);

-- array_keys does loose comparison by default so to set the strict set the third argument to true

printArray($keys);



** array_map(callable|null $callback, array $array, array ...$arrays): array
-- maps the callback to every element in the array

@@ Example of single array
$arr = [1, 2, 3, 4, 5, 6];
$arr = array_map(fn($number) => $number * 3, $arr);
printArray($arr);

@@ Example of multiple arrays
$arr1 = ['a' => 1, 'b' => 2, 'c' => 3];
$arr2 = ['d' => 4, 'e' => 5, 'f' => 6];

$arr = array_map(fn($number1, $number2) => $number1 * $number2, $arr1, $arr2);
-- the indexes will be reset and it no longer have string keys, because you passing more than one array
-- also make sure the length of the arrays are the same, if its not the shorter one will have empty elements

printArray($arr);


** array_merge(array ...$arrays): array
-- merge arrays

$arr1 = [1, 2, 3];
$arr2 = [6 => 4, 7 => 5, 8 => 6];
$arr3 = ['a' => 7, 8, 9, 'a' => 10];

$merged = array_merge($arr1, $arr2, $arr3);

printArray($merged);
@@ Note
-- if lets say in arr2 have a keys it will be re-order
-- if lets say in arr3 have a string keys it will have the string key but the rest will be reset/re-order
-- if lets say in arr3 have the same keys as key before it will be overwritten and change the value of the previous key that have the same key or overwritten



** array_reduce(array $array, callable $callback, mixed $initialValue = null): mixed
-- reduces the array to a single value

$invoiceItems = [
    ['price' => 9.99, 'qty' => 3, 'desc' => 'Item 1'],
    ['price' => 29.99, 'qty' => 1, 'desc' => 'Item 2'],
    ['price' => 149, 'qty' => 1, 'desc' => 'Item 3'],
    ['price' => 14.99, 'qty' => 2, 'desc' => 'Item 4'],
    ['price' => 4.99, 'qty' => 4, 'desc' => 'Item 5'],
];
$total = array_reduce(
    $invoiceItems,
    fn($sum, $item) => $sum + $item['qty'] * $item['price'],
    500 // initial value
);
echo $total;


** array_search(mixed $needle, array $haystack, bool $strict = false): mixed
-- returns the key of the first element in haystack that matches needle

$arr = ['a', 'b', 'c', 'D', 'E', 'ab', 'bc'];
$key = array_search('E', $arr);
var_dump($key);

-- carefull array_search does loose comparison

-- do strict comparison with if else
if ($key === false) {
    echo "not found";
} else {
    echo 'in index : ' .  $key;
}

or

if (in_array('a', $arr)) {
    echo "letter found";
}


** array_diff(array1, array2)
** array_diff_assoc(array1, array2)
** array_diff_key(array1, array2)
-- returns the difference between arrays

$arr1 = ['a' => 1, 'b' => 2, 'c' => 3];
$arr2 = ['b' => 4, 'e' => 5, 'f' => 6];

printArray(array_diff($arr1, $arr2));
printArray(array_diff_assoc($arr1, $arr2));
printArray(array_diff_key($arr1, $arr2));


** asort($array);
** ksort($array);
** usort($array, fn($a, $b) => $a - $b);

-- sorting an array

$arr = ['d' => 3, 'b' => 1, 'c' => 4, 'a' => 2];
printArray($arr);
usort($arr, fn($a, $b) => $a <> $b);
printArray($arr);



End Of Section 29
========================================
Start Of Section 30

# 30. Error Handling


End Of Section 30
========================================
Start Of Section 31

# 31. Working With Files


End Of Section 31
========================================
Start Of Section 32


End Of Section 32
========================================
Start Of Section 33


End Of Section 33
========================================
Start Of Section 34


End Of Section 34
========================================
Start Of Section 35


End Of Section 35
========================================
Start Of Section 36


End Of Section 36
========================================
Start Of Section 37


End Of Section 37
========================================
Start Of Section 38


End Of Section 38
========================================
Start Of Section 39


End Of Section 39
========================================
Start Of Section 40


End Of Section 40
========================================

































































 */

