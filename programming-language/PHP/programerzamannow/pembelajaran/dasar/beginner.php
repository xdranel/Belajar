<?php

/**
==========  LIST OF LESSON  ==========
START OF THE LINE

Making a File Name often using CamelCase

# 1. Data Type
# 2. Variable
# 3. Array
# 4. Operator
# 5. String Manipulation
# 6. Statement
# 7. Loop
# 8. Function
# 9. Require and Include
# 10. Scope
# 11. Reference

END OF THE LINE
======================================================================================
START OF SECTION 1

# 1. Data Type #
# Integer
# Float
# Boolean
# String
# Data NULL/null

===== Integer =====
echo "Decimal : ";
var_dump(1234);

echo "Octal : ";
var_dump(0123);

echo "Hexadecimal : ";
var_dump(0x1A);

echo "Binary : ";
var_dump(0b11111111);

echo "Underscore With Integer : ";
var_dump(1_230_700_000);


===== Float =====
echo "Floating Point : ";
var_dump(1.234);

echo "Float With E Notation Plus : ";
var_dump(1.2e3);

echo "Float With E Notation Minus : ";
var_dump(1.2e-3);

echo "Underscore With Float : ";
var_dump(1_234.567);

** Integer Overflow **
Its Based On What Bit Of Your System
If it's exceeds the bit of your system, it will be overflow
and change it from integer to float

echo "Integer Overflow 32 Bit: ";
var_dump(4_294_967_296);

echo "Integer Overflow 64 Bit: ";
var_dump(9_223_372_036_854_775_807);


===== Boolean =====
Both True And False is CASE INSENSITIVE

echo "True : ";
var_dump(true);

echo "False : ";
var_dump(false);

===== String =====

** Single Quotes **
echo 'Belajar PHP';

** Double Quotes **
echo "Belajar\t PHP";
echo "\n"

** Heredoc **
echo <<<EOT
Belajar PHP
EOT;

Nowdoc Cant Parsing Variable
** Nowdoc **
echo <<<'EOT'
Belajar PHP
EOT;


===== Data NULL/null =====
Null Value represent a variable with no value
case INSENSITIVE

$name = "Dicky";
echo $name;

$name = NULL;
echo $name;
echo "\n";

to check if a variable is NULL, you can use the function is_null()
1 : True
0 : False

echo is_null($name);
echo "\n";

or you can use var_dump()
var_dump(is_null($name));
echo "\n";

END OF SECTION 1
============================================================
START OF SECTION 2

# 2. Variable #
# Variable (Normal)
# Variable (Variables)
# Constant
# Deleting Variable


===== Variable (Normal) =====
$name = "Dicky";
$age = 20;

echo "My Name Is $name";
echo "\n";
echo "I'm $age Years Old";
echo "\n";

===== Variable (Variables) =====
Taking a value from another variable and make it a variable
$name = "Dicky";
$$name = "Nugraha";

echo "My Name Is $name";
echo "\n";
echo "My Full Name Is $Dicky";
echo "\n";

===== Constant =====
when using constant use a UPPERCASE LETTERS
dont use WHITESPACE use UNDERSCORE instead
you cant declare same constant name

define("AUTHOR", "Gendhi");
define("APP_VERSION", "1.0.0");

echo "Author : ";
echo AUTHOR;
echo "\n";

echo "App Version : ".APP_VERSION;
echo "\n";

===== Deleting Variable =====
use unset() function

$country = "America";
unset($country);
echo $country;

it will produce error because the variable has been deleted

so that mean is_null() will give you error

to check if a variable exists and is not NULL
you can use isset() function

var_dump(isset($country));

END OF SECTION 2
===================================================================
START OF SECTION 3

# 3. Array #
# Operasi Array
# Map

You can use
$var = array(1,2,3,4,5);
or
$var = [1,2,3,4,5];

** Operasi Array **

$var[index] : Mengakses Data Di Index
$name = ["Juki", "Dicky", "Budi"];
var_dump($name[0]);

$var[index] = value : Mengubah Data Di Index
$name[0] = "Joko";
var_dump($name);

$var[] = value : Menambah Data Di Index Terakhir
$name[] = "Wawan";
var_dump($name);

unset($a[index]) : Menghapus Data Di Index
unset($name[1]);
var_dump($name);

count($a) : Menghitung Jumlah Data Di Array
var_dump(count($name));

** Map **
Instead of using index to access data
you use key set

$rian = [
  "id" => 1,
  "name" => "Rian",
  "age" => 20
];
How To Access :
var_dump($rian["id"]);

==> Array Inside Array
$rian = [
  "id" => 1,
  "name" => "Rian",
  "age" => 20,
  "address" => [
    "city" => "Jakarta",
    "country" => "Indonesia",
    "neighborhood" => "Jl. Rajawali No. 123"
  ]
];
How to Access :
var_dump($rian["address"]["neighborhood"]);

How To Add Manualy :
$rian["address"] = ["city" => "Jakarta", "country" => "Indonesia"];

END OF SECTION 3
===================================================================
START OF SECTION 4

# 4. Operator #
# Aritmatika
# Penugasan
# Perbandingan
# Logika
# Increment & Decrement
# Array
# Dot
# goto

================
** Aritmatika **

+$var : Positive
-$var : Negative

$a + $b : Add
$a - $b : Subtract
$a * $b : Multiply
$a / $b : Divide
$a % $b : Modulus
$a ** $b : Power

===============
** Penugasan **

Assignment
$a += $b : Add
$a -= $b : Subtract
$a *= $b : Multiply
$a /= $b : Divide
$a %= $b : Modulus

==================
** Perbandingan **

%a == %b : Equals
%a != %b : Not Equals
$a <> $b : Not Equals

%a === %b : Identical
%a !== %b : Not Identical

%a > %b : Greater Than
%a >= %b : Greater Than Equals

%a < %b : Less Than
%a <= %b : Less Than Equals

============
** Logika **

$a && $b : AND
$a and $b : AND

$a || $b : OR
$a or $b : OR

$a xor $b : XOR
!$a : NOT

===========================
** Increment & Decrement **

$a++ : Post Increment
++$a : Pre Increment

$a-- : Post Decrease
--$a : Pre Decrease

===========
** Array **

$a + $b : Union

$a == $b : Equals | Comparing same key-value
$a != $b : Not Equals
$a <> $b : Not Equals

$a === $b : Identical | Comparing same key-value, position, data type, etc
$a !== $b : Not Identical

Example :
# 1.
$a = [
  "firstName" => "Budi"
];

$b = [
  "firstName" => "Joko",
  "lastName" => "Purwanto"
];

var_dump($a + $b);
It will produce : Budi & Purwanto
array $a and $b had same first key name "firstName" so it will be merged and ignore the same key on other array

but if you do var_dump($b + $a)
It will produce : Joko & Purwanto
it will still be the same, because now the key name on $a is "firstName" and the key name on $b is "lastName"
so it will not merged anything

# 2.
$a = [
  "lastName" => "Purwanto",
  "firstName" => "Joko"
];

$b = [
  "firstName" => "Joko",
  "lastName" => "Purwanto"
];

var_dump($a == $b); Produce : True
the reason it produce True is because $a and $b had same key name
"firstName" and "lastName" and same value

var_dump($a === $b); Produce : False
the reason it produce False because the order of the array is different,
even if the key name and the value is the same because it comparing identicaly


==========
** goto **
jump to goto definition
not Recomended using it many times because it will make you confuse

$counter = 1;
while (true) {
    echo "Counter Ke-$counter".PHP_EOL;
    $counter++;

    if ($counter > 10) {
        goto end;
    }
}
end:
echo 'End Loop';


END OF SECTION 4
===================================================================
START OF SECTION 5

# 5. String Manipulation #
# Dot Operator
# Conversion
# Accesing Character
# Variable Parsing
# Curly Brace


=========
** Dot **
Recomended To use . to combine string instead of + to combine string

$name = 'Ramona';

echo 'Nama : '.$name.PHP_EOL;
echo 'Nilai : '. 100 .PHP_EOL;


================
** Conversion **

Konversi string ke int dan sebaliknya
$varString = (string) 100;
var_dump($varString);

$varInt = (int) '100';
var_dump($varInt);

$varFloat = (float) '100.10';
var_dump($varFloat);

when the doing conversion if the value is not a number, it will be converted to 0


========================
** Accesing Character **

$name = 'Ram';
echo $name[0].PHP_EOL;
echo $name[1].PHP_EOL;
echo $name[2].PHP_EOL;

when you accessing index that is not exist, it will give you a warning

======================
** Variable Parsing **

$name = 'Ram';

echo 'Hello '.$name.PHP_EOL;
Before You Do it like this

echo "Hello $name How Are You?".PHP_EOL;
But when you use variable parsing you can do it like this

=================
** Curly Brace **

$name = 'Ram';

echo "Hello $names How Are You?".PHP_EOL;
When you do it like this the variable $names is not defined
Because you combining a variable with string

Instead you can use the curly brace
echo "Hello {$name}s How Are You?".PHP_EOL;

END OF SECTION 5
===================================================================
START OF SECTION 6

# 6. Statement #
# If Statement
# Switch Statement
# Ternary Operator
# Null Coalescing Operator


==================
** If Statement **

$nilai = 50;
$grade = null;

if ($nilai >= 90 && $nilai <= 100) {
    $grade = 'A';
} elseif ($nilai >= 70 && $nilai < 90) {
    $grade = 'B';
} elseif ($nilai >= 50 && $nilai < 70) {
    $grade = 'C';
} elseif ($nilai >= 30 && $nilai < 50) {
    $grade = 'D';
} else {
    $grade = 'E';
}

======================
** Switch Statement **

switch ($grade) {
    case 'A':
        echo 'Your Grade is A, Congratulations You Pass The Test'.PHP_EOL;
        break;
    case 'B':
        echo 'Your Grade is B, Congratulations You Pass The Test'.PHP_EOL;
        break;
    case 'C':
        echo 'Your Grade is C, If You Want Value Addition You Can Do Remedial'.PHP_EOL;
        break;
    case 'D':
        echo 'Your Grade is D, You Need To Do Remedial'.PHP_EOL;
        break;
    default:
        echo 'Just Get Off'.PHP_EOL;
        break;
}

======================
** Ternary Operator **

$gender = 'Pria';
$hi = $gender == 'Pria' ? 'Hi Bro' : 'Hi Nona';

echo $hi.PHP_EOL;

==============================
** Null Coalescing Operator **

$data = [
    'Action' => null,
];

To check if the data is set or no
You can do it like this
if (isset($data['Action'])) {
    echo $data['Action'].PHP_EOL;
} else {
    echo 'Data Not Found'.PHP_EOL;
}

Or you can do it like this
$action = $data['Action'] ?? 'Data Not Found';
echo $action.PHP_EOL;

END OF SECTION 6
===================================================================
START OF SECTION 7

# 7. Loop #
# For Loop
# While Loop
# Do-While Loop
# For Each Loop


============
** For Loop **
for ($i = 0; $i < 4; $i++) {
    echo "This is loop number $i".PHP_EOL;
}

================
** While Loop **
$i = 0;
while ($i < 4) {
    echo "This is loop number $i".PHP_EOL;
    $i++;
}

===================
** Do-While Loop **
$i = 0;
do {
    echo "Loop ke - $i\n";
    $i++;
} while ($i < 5);

===================
** For Each Loop **

instead of using normal loop that using index to access data
you can use foreach loop that using key to access data

Example 1:
$names = ['Daniel', 'Fernandi'];

foreach ($names as $value) {
    echo $value.PHP_EOL;
}

Example 2:
$data = [
    'Action' => 'CRUD',
    'CRUD' => 'Create Read Update Delete',
    'PHP' => 'PHP',
    'Database' => 'MySQL',
];

foreach ($data as $key => $value) {
    echo "$key : $value".PHP_EOL;
}

END OF SECTION 7
===================================================================
START OF SECTION 8

# 8. Function #
# Function

# Argument
# Default Argument
# Data Type Argument

# Variable-Length Argument List

# Return Value
# Data Type Return Value

# Variable Function
# Anonymous Function
# Arrow Function
# Callback Function
# Recursive Function

# Manipulation Function




Even though you can make func on any location
Make sure that make a function that already get executed
if not it will give you an error


==============
** Function **

function foo()
{
    echo 'Hello World!'.PHP_EOL;
}
foo();

==============
** Argument **

function foo($name)
{
    echo "Hello $name".PHP_EOL;
}
foo('Budi');

======================
** Default Argument **

function foo($name = 'Anonymous')
{
    echo "Hello $name".PHP_EOL;
}
foo('Denis');
foo();

but becarefull if you have 2 argument and the first argument have a default value
and you passing the parameter only 1 argument it will never go to second argument
so make sure if you have more than 1 argument and you want to add default value
make sure the default value is on the last argument

function foo($firstname, $lastname = "")
{
    echo "Hello $firstname $lastname".PHP_EOL;
}
foo('Denis');
foo('Budi', 'Anduk');


========================
** Data Type Argument **

Class/Interface, self, array, callable, bool, float, int, string, interable, object, etc

Make sure you define the data type of the argument and when you passing the parameter
make sure it have same data type or data type that can be converted to same data type

function sum(int $first, int $last)
{
    $total = $first + $last;
    echo "Total $first + $last = $total".PHP_EOL;
}
sum(10, 5);
sum('10', '5');
sum(true, false);
sum([], []);

for sum([], []) it will produce error because array is not a number when converting to int


===================================
** Variable-Length Argument List **

3 dot ...

Passing a variable-length argument list to a function is similar to passing an array to a function.

Example:
function sumAll(...$values)
{
    $total = 0;
    foreach ($values as $value) {
        $total += $value;
    }
    echo 'Total '.implode(' + ', $values)." = $total".PHP_EOL;
}

sumAll(1, 3, 5, 7, 9);

But if you already have an array and want to pass it
Example:
$foo = [1, 3, 5, 7, 9];

same func;

you can pass it like this
sumAll(...[1, 3, 5, 7, 9]);
sumAll(...$foo);


==================
** Return Value **

function foo(int $value)
{
    if ($value > 5 && $value <= 10) {
        return 'A';
    } else {
        return 'B';
    }
}

============================
** Data Type Return Value **

same as data type argument you need to make sure the return value have same data type

to make return data type you can use function declaration
like below : (data type) ↓↓↓↓↓↓↓↓
function foo(int $value): string
{
    if ($value > 5 && $value <= 10) {
        return 'A';
    } else {
        return 'B';
    }
}


=======================
** Variable Function **

function sayHello(string $name, $filter)
{
    $finalName = $filter($name);
    echo "Hello $finalName".PHP_EOL;
}

function sample(string $name): string
{
    return "Sample $name";
}

sayHello('Budi', 'sample');
sayHello('Budi', 'strtoupper');

========================
** Anonymous Function **
&& Anonymous Function As Argument &&
&& Accesing Variable outside Closure &&

$sayHello = function (string $name) {
    echo "Hello $name".PHP_EOL;
};
↑↑
dont forget ; because you set anonymous function into variable
You call it as variable with $ not like normal function
↓↓
$sayHello('Budi');


&& Anonymous Function As Argument &&

function sayGoodbye(string $name, $filter)
{
    $finalName = $filter($name);
    echo "Goodbye $finalName".PHP_EOL;
}
sayGoodbye('Budi', function (string $name): string {
    return strtoupper($name);
});

Or You can just assign the anonymous func to variable
$filterFunc = function (string $name): string {
    return strtoupper($name);
};

sayGoodbye('Budi', $filterFunc);


&& Accesing Variable outside Closure &&
$firstName = 'Budi';
$lastName = 'Nugraha';

$sayHello = function () use ($firstName, $lastName) {
    echo "Hello $firstName $lastName".PHP_EOL;
};

$sayHello();


====================
** Arrow Function **

Introduce in PHP 7.4
use it when you need a simple anonymous function

$firstName = 'Budi';
$lastName = 'Nugraha';

$anonFunc = function () use ($firstName, $lastName): string {
    return "Hello $firstName $lastName".PHP_EOL;
};
echo $anonFunc();

↑↑↑↑
Instead of
You Can Do
↓↓↓↓

$anonFunc = fn () => "Hello $firstName $lastName".PHP_EOL;
echo $anonFunc();


=======================
** Callback Function **

callable make it so that you can pass any type of function as parameter

function sayHello(string $name, callable $filter)
{
    $finalName = call_user_func($filter, $name);
    Or
    $finalName = $filter($name);
    echo "Hello $finalName".PHP_EOL;
}

sayHello('Budi', 'strtoupper');
sayHello('Budi', function (string $name): string {
    return strtolower($name);
});
sayHello('Budi', fn (string $name) => strtoupper($name));

it will pass any type of function as parameter


========================
** Recursive Function **

calling itself
but becarefull when using it if you use it without limit it can cause infinite loop
and it will become overflow

function factorial(int $value): int
{
    if ($value == 1) {
        return 1;
    } else {
        return $value * factorial($value - 1);
    }
}
var_dump(factorial(5));


===========================
** Manipulation Function **
&& String &&
&& Array &&
&& is &&


&& String &&
You Can Check It On Website : https://www.php.net/manual/en/ref.string.php

few feature in php
var_dump(implode(',', [10, 11, 12, 13, 14, 15]));
var_dump(explode(' ', 'Gendhi Ramona P'));
var_dump(strtolower('END OF LINE'));
var_dump(strtoupper('end of line'));
var_dump(trim('   end of line   '));
var_dump(substr('end of line', 0, 5));

&& Array &&
You Can Check It On Website : https://www.php.net/manual/en/ref.array.php

$data = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

$dataResult = array_map(fn (int $value) => $value * 10, $data);
rsort($data);
var_dump(array_keys($data));
var_dump(array_values($data));

&& is &&
You Can Check It On Website : https://www.php.net/manual/en/ref.var.php

is_string();
is_bool();
is_int();
is_float();
is_array();
is_null();

END OF SECTION 8
===================================================================
START OF SECTION 9

# 9. Require and Include #

Require : If The File Not Exist It Will Throw Error
Include : If The File Not Exist It Will Only Give You Warning, The Program Will Keep Running

require/include_once : It Will check if the file is already included/required or not
if the file already inlcude/require the file will not get included/required again


include 'lib/fooFunction.php';
require 'lib/barFunction.php';

include_once 'lib/fooFunction.php';
require_once 'lib/barFunction.php';


END OF SECTION 9
===================================================================
START OF SECTION 10

# 10. Scope #

&& GLOBAL &&
$name = 'ZXD';
function foo()
{
    global $name; // global keyword
    echo $name.PHP_EOL;
}
foo();

&& Static &&
function foo()
{
    static $count = 1;
    echo "Count : $count".PHP_EOL;
    $count++;
}

foo();

END OF SECTION 10
===================================================================
START OF SECTION 11

# 11. Reference #
&& Pass By Reference &&


Similar to pointer in C/C++
$foo = 'baz';
$bar = &$foo;
$bar = 'bar';

echo $foo.PHP_EOL;

&& Pass By Reference &&
function increment(int &$value)
{
    $value++;
}

$count = 0;
increment($count);
echo $count.PHP_EOL;

END OF SECTION 11
===================================================================
*/
