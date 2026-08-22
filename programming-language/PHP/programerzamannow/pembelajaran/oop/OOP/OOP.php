<?php

/**
==========  LIST OF LESSON  ==========
START OF THE LINE

# 1. Introduction
# 2. Properties
# 3. Functions
# 4. This
# 5. Constant
# 6. Self
# 7. Constructor
# 8. Destructor
# 9. Inheritance
# 10. Namespace

# 11. Import
# 12. Visibility
# 13. Function Overriding
# 14. Parent
# 15. Constructor Overriding
# 16. Polymorphism
# 17. Type Check and Cast
# 18. Abstract
# 19. Getter and Setter
# 20. Interface

# 21. Traits
# 22. Final
# 23. Anonymous
# 24. Static
# 25. StdClass
# 26. Object Iteration
# 27. Generator
# 28. Object Cloning
# 29. Overloading
# 30.

# 31. Covariance and Contravariance
# 32. DateTime
# 33. Exception

END OF THE LINE
============================================================================
START OF SECTION 1

# 1. Introduction #
often naming class with CamelCase
its fine when you make a class name that is not the same with file name

&& making a class &&

class Person
{

}

&& making an object &&

require_once 'data/Person.php';
$person = new Person;

END OF SECTION 1
============================================================================
START OF SECTION 2

# 2. Properties #
# Properties Manipulation
# Properties Data Type
# Properties Default
# Properties Nullable

-> Fields/Properties/Atrributes is data that you can put inside an object
-> But, before you can input data into Fields, you need to declare what data that object will have inside a class
-> Making a Field same as a variable, but you put it inside the class

class Person
{
    public $name;
    public $address;
    public $country;
}

=============================
** Properties Manipulation **

$person = new Person;

↓↓↓↓ Adding ↓↓↓↓
$person->name = 'Gendhi';
$person->address = 'Jl.Ampera No.1';
$person->country = 'Indonesia';

↓↓↓↓ Accesing It ↓↓↓↓
echo "Name : {$person->name}".PHP_EOL;
echo "Address : {$person->address}".PHP_EOL;
echo "Country : {$person->country}".PHP_EOL;


==========================
** Properties Data Type **

-> adding a data type place it in between the field
-> make sure the data type is same with the data that you input
-> always remember there is type juggling on PHP

class Person
{
    public string $name;
    public string $address;
    public string $country;
}

========================
** Properties Default **

class person
{
    public string $name;
    public string $address;
    public string $country = "Indonesia";
}

=========================
** Properties Nullable **

Use the ? before the data type
class Person
{
    public ?string $name;
    public ?string $address;
    public ?string $country;
}

END OF SECTION 2
============================================================================
START OF SECTION 3

# 3. Functions #

How to create a function inside a class
and how to access it

&& On Class &&
public function sayHello(string $name)
{
    echo "Hello $name".PHP_EOL;
}


&& How to Call &&
$person1 = new Person;
$person1->sayHello('Rifki');


END OF SECTION 3
============================================================================
START OF SECTION 4

# 4. This #
This use to access a property inside a class

&& on class &&
class Person
{
    public string $name;

    public function sayHello(?string $name): void
    {
        if (is_null($name)) {
            echo "Hi, my name is $this->name".PHP_EOL;
        } else {
            echo "Hi $name, my name is $this->name".PHP_EOL;
        }
    }
}

&& on code &&

$person = new Person();
$person->name = "Budi";
$person->sayHello(null);

$person2 = new Person();
$person2->name = "Ramona";
$person2->sayHello("Joko");


END OF SECTION 4
============================================================================
START OF SECTION 5

# 5. Constant #
# Const Basic
# Const Class

Const Feature On PHP 7.4
Normally when declare a constant, you use uppercase
You dont need to use $ to declare a constant


=================
** Const Basic **
define('APPLICATION', 'Belajar OOP');

echo APPLICATION.PHP_EOL;
** Const PHP 7.4 **
const APP_VERSION = '1.0.0';

echo APP_VERSION.PHP_EOL;

=================
** Const Class **

class Person
{
    const AUTHOR = 'Ramona';
}
to access different like you would acces an object/Properties

&& On Code &&
-> to acces it, bit different from accesing an object you use ::
because constant is not an object, ..

echo Person::AUTHOR.PHP_EOL;

END OF SECTION 5
============================================================================
START OF SECTION 6

# 6. Self #

-> The self keyword is used to access a constant inside a class
-> instead you acces it using ClassName::CONSTANT_NAME
-> You can use self::CONSTANT_NAME to access a constant inside a class

&& On Class &&
class Person
{
    const AUTHOR = 'Ramona';

    public function info(): void
    {
        echo 'Author: '.self::AUTHOR.PHP_EOL;
    }
}

&& On Code &&
$person1->info();


END OF SECTION 6
============================================================================
START OF SECTION 7

# 7. Constructor #

-> Constructor is used to create an object
-> Constructor is called automatically when an object is created

&& On Class &&
public function __construct(string $name, ?string $address)
{
    $this->name = $name;
    $this->address = $address;
    echo "Name : ". $this->name .", Address : ". $this->address .PHP_EOL;
}

&& On Code &&
require_once 'data/Person.php';

$person1 = new Person('Ramona', 'Jl. Merpati No. 1');
var_dump($person1);

END OF SECTION 7
============================================================================
START OF SECTION 8

# 8. Destructor #

-> Destructor is used to destroy an object
-> Desctructor is called automatically when an object is no longer used

&& On Class &&
public function __destruct()
{
    echo "Object Person $this->name has been destroyed".PHP_EOL;
}

&& On Code &&
Destructor will be called automatically when the object is no longer used
require_once 'data/Person.php';

$person1 = new Person('Ramona', 'Jl. Merpati No. 1');
$person2 = new Person('Hana', 'Jl. Merpati No. 2');

echo 'End Of Program'.PHP_EOL;

END OF SECTION 8
============================================================================
START OF SECTION 9

# 9. Inheritance #

@@ Look Up File Manager.php, Inheritance.php

-> Inheritance is used to create a new class from an existing class
-> using the "extends" keyword

&& On Class &&
class Manager
{
    public string $name;
    public function sayHello(string $name): void
    {
        echo "Hi $name, my name is $this->name" . PHP_EOL;
    }
}

class VicePresident extends Manager
{

}

&& On Code &&

$manager = new Manager();
$manager->name = "Ramona";
$manager->sayHello("Joko");

$vp = new VicePresident();
$vp->name = "Budi";
$vp->sayHello("Joko");

END OF SECTION 9
============================================================================
START OF SECTION 10

# 10. Namespace #
# Namespace Class
# Namespace Function, Constant
# Namespace Global

@@ Look Up File Conflict.php, Helper.php, Namespace.php

-> Namespace used for better organization
-> Namespace can be used to create a new class from an existing class,
that way if you want to use the same class name on same file, you can use the namespace

=====================
** Namespace Class **

&& On Class Conflict.php &&
namespace Data\One {
    class Conflit
    {

    }
}

namespace Data\Two {
    class Conflit
    {

    }
}


&& On Code Namespace.php &&

$conflict1 = new \Data\One\Conflit();
$conflict2 = new \Data\Two\Conflit();


==================================
** Namespace Function, Constant **

&& On Code File Helper.php &&

namespace Helper;

function helpMe(): void
{
    echo "Help Me" . PHP_EOL;
}

const APPLICATION = "Belajar OOP";

&& On Code Namespace.php &&
echo Helper\APPLICATION . PHP_EOL;
Helper\helpMe();

======================
** Namespace Global **

&& On Code Namespace.php &&

namespace {
}

Example:

namespace {
    require_once "data/Conflict.php";
    require_once "data/Helper.php";

    $conflict1 = new \Data\One\Conflit();
    $conflict2 = new \Data\Two\Conflit();

    echo Helper\APPLICATION . PHP_EOL;
    Helper\helpMe();
}


END OF SECTION 10
============================================================================
START OF SECTION 11

# 11. Import #
# Use
# Alias
# Group

@@ Look Up File Conflict.php, Helper.php, Import.php

-> you can use "use" keyword to import a class, function, or constant

=========
** Use **

-> when using "use" keyword you can't use same class name but there is work around

&& On Code Import.php &&

use Data\One\Conflict;
use function Helper\helpMe;
use const Helper\APPLICATION;

$conflict1 = new Conflict();
$conflict2 = new \Data\Two\Conflict();
^^^^^^^^^^
-> for this part since use can't use same class name, you need to use "\" just like calling namespace normal way

helpMe();

echo APPLICATION . PHP_EOL;

===========
** Alias **

-> with this feature you can give an alias to a class, function, or constant
-> when using "use" keyword you can use same class name by using alias or "as" keyword

&& On Code ImportAlias.php &&

use Data\One\Conflict as Conflict1;
use Data\Two\Conflict as Conflict2;
^^^
-> as you can see now you can use same class name by using alias instead of calling it normal way

use function Helper\helpMe as help;
use const Helper\APPLICATION as APP;

$conflict1 = new Conflict1();
$conflict2 = new Conflict2();
-> and now you be able calling

help();

echo APP . PHP_EOL;

===========
** Group **

-> by using group feature you can import multiple class, function, or constant at once
-> use the curly brace or {}

@@ adding 2 new class on Conflict.php (Sample, Dummy)

&& On Code ImportGroup.php &&

use Data\One\{Conflict as Conflict1, Dummy, Sample};

$conflict1 = new Conflict1();
$dummy = new Dummy();
$sample = new Sample();


END OF SECTION 11
============================================================================
START OF SECTION 12

# 12. Visibility #
# Visibility

@@ Look Up File Product.php, Visibility.php

-> Visibility or Acces Modifier
-> a feature in where a properties, function, or constant can be accessed
-> there are 3 visibility: public, protected, private
-> public is a default visibility

Modifier  | Class | Subclass | World
public    | Yes   | Yes      | Yes
protected | Yes   | Yes      | No
private   | Yes   | No       | No


END OF SECTION 12
============================================================================
START OF SECTION 13

# 13. Function Overriding #

@@ Look Up File Manager.php, FunctionOverriding.php

-> overriding is a feature in where a function can be override by a subclass
-> this feature is useful when you want to change the behavior of a parent class

-> in the override function you can actually change the paramater and return, but dont do that PHP will give you a warning for it
-> so its not recommended to do that


END OF SECTION 13
============================================================================
START OF SECTION 14

# 14. Parent Keyword #

@@ Look Up File Shape.php, Parent.php

-> parent keyword is used to access a parent properties, function, or constant
-> this feature is useful when you want to access a parent properties, function, or constant
-> especially if the parent properties, function, or constant having the same name


END OF SECTION 14
============================================================================
START OF SECTION 15

# 15. Constructor Overriding #

@@ Look Up File Manager.php, FunctionOverriding.php

-> constructor overloading is a feature in where a class can have multiple constructor just like function overloading
-> this feature is useful when you want to create multiple constructor
-> but when making constructor for a subclass its recomended to use parent constructor inside the subclass constructor
-> unless the parent doesnt have constructor then you dont need to use parent constructor

-> the reason why you need to use parent constructor inside the subclass constructor, so that it make sure every code in the parent constructor will be executed

END OF SECTION 15
============================================================================
START OF SECTION 16

# 16. Polymorphism #
# Polymorphism
# Function Argument Polymorphism


==================
** Polymorphism **

@@ Look Up File Programmer.php, Polymorphism.php

-> Polymorphism is a feature in where an object can have multiple form
-> Polymorphism have strong relation with inheritance
-> But remember you can only use it within the class that inheritance, even if you let's say make 1 file
and in that file have 2 require_once, and you want to use the class on other file and combine it, you can't use polymorphism


====================================
** Function Argument Polymorphism **

@@ Look Up File Programmer.php, Polymorphism.php


END OF SECTION 16
============================================================================
START OF SECTION 17

# 17. Type Check and Cast

@@ Look Up File Programmer.php, Polymorphism.php

-> you already know how to conversion data type but not a class

-> for object data type, you dont need to explicitly convert it
-> but to make sure and to make it safe before doing Casting, you need to Type Check(Data Type Checking) using "instanceof" keyword
-> instanceof have boolean value true or false

END OF SECTION 17
============================================================================
START OF SECTION 18

# 18. Abstract #
# Abstract Class
# Abstract Function

-> you still can use polymorphism

1. Methods:
    Abstract methods cannot have an implementation (i.e., they are declared without a body).
    Concrete methods can have an implementation (i.e., they can contain block code).

2. Properties:
    Abstract classes can have properties (variables) with default values and can also have visibility modifiers (public, protected, private).

3. Constructors:
    Abstract classes can have constructors with implementation. Subclasses can call the parent constructor using parent::__construct().

4. Static Methods:
    Abstract classes can also have static methods with implementations.

====================
** Abstract Class **

@@ Look Up File Location.php, AbstractClass.php

-> Abstract Class is a class that can't make an object directly, only by inheritance
-> To make abstract class you need to use "abstract" keyword before the class
-> that way you can use abstract class as Contract to child class

=======================
** Abstract Function **

@@ Look Up File Animal.php, AbstracFunction.php

-> When making Abstract Class, you can also make Abstract Function
-> To make abstract function you need to use "abstract" keyword before the function
-> When making Abstract Function you can't make block code for that function
-> that mean if you want to use the Abstract Function you need to do Override on the child class
-> Abstract Function can't have acces modifier private


Code Example of Abstract:
abstract class Animal {
    protected $name; // Property

    public function __construct($name) { // Constructor
        $this->name = $name;
    }

    abstract public function makeSound(); // Abstract method

    public function getName() { // Concrete method
        return $this->name;
    }

    public static function info() { // Static method
        return "This is an animal class.";
    }
}

class Dog extends Animal {
    public function makeSound() {
        return "{$this->name} says Bark";
    }

}
END OF SECTION 18
============================================================================
START OF SECTION 19

# 19. Getter and Setter
# Encasulaption
# Getter and Setter

@@ Look Up File Category.php, GetterAndSetter.php


===================
** Encapsulation **

Before Going deeper into Getter and Setter, you needd to know about Encapsulation

-> Encapsulation is making sure sensitive data is hidden from outside access
-> That way it will protect data of an object to still be valid and good
-> to do that when making properties use access modifier private, that way it can't be access and modified from outside
-> to access and change it, you need to provide a function to change and get properties

Getter is function to get data field
Setter is function to change data field

Data Type | Getter Method     | Setter Method
boolean   | isXXX():bool      | setXXX(bool value)
others    | getXXX():dataType | setXXX(dataType value)


=======================
** Getter and Setter **

For this section just look up the file


END OF SECTION 19
============================================================================
START OF SECTION 20

# 20. Interface

@@ Look Up File Car.php, Interface.php

-> Abstract Class can be used as contract with child class, but more apropriate for contract is Interface
-> to inherit Interface you need to use keyword "implements" not "extends"
-> and not like class, you can implements more than 1 interface, what it means is that when child class only have 1 parent class.
but with Interface you can have more than 1 parent interface
-> you still can use polymorphism

-> something to consider <-
-> if the contract itself all Abstract better use Interface because it supports multiple inheritance

1. Methods:
    All methods in an interface are implicitly abstract and cannot have an implementation. They must be implemented by any class that implements the interface.

2. Properties:
    Interfaces cannot have properties. They can only define constants.

3. Constructors:
    Interfaces cannot have constructors or any implementation. Classes that implement the interface must define their own constructors.

4. Static Methods:
    Interfaces cannot have static methods.


Code Example of Interface:
interface SoundMaker {
    public function makeSound(); // No implementation
}

class Dog implements SoundMaker {
    protected $name;

    public function __construct($name) { // Constructor in implementing class
        $this->name = $name;
    }

    public function makeSound() {
        return "{$this->name} says Bark";
    }
}


END OF SECTION 20
============================================================================
START OF SECTION 21

# 21. Traits #
# Traits
# Traits Properties
# Traits Overriding
# Traits Visibility Override
# Traits Conflict
# Trait Inheritance

@@ Look Up File SayGoodBye.php, Trait.php

-> Traits are a way to reuse code between classes
-> Traits are similar to abstract classes, but they can have properties and methods

Methods:
    Traits can contain both abstract methods (which must be implemented by the class using the trait) and concrete methods (which have an implementation).
    Traits can also provide default implementations for methods that can be overridden by the class using the trait.

Properties:
    Traits can have properties, and these properties can have visibility modifiers (public, protected, private).
    However, properties defined in traits are not inherited by the classes that use the trait; they are part of the class that uses the trait.

Constructors:
    Traits cannot have constructors. If a class using a trait has a constructor, it must define its own constructor.
    However, you can call methods from the trait within the class's constructor.

Static Methods:
    Traits can have static methods with implementations. These static methods can be called using the class that uses the trait.


=======================
** Traits Overriding **

-> The order of traits overriding
-> ParentClass = Override by => Trait = Override by => ChildClass

================================
** Traits Visibility Override **

use SayGoodBye, SayHello, HasName, CanRun {
    hello as private;
    goodBye as private;
}

=====================
** Traits Conflict **

@@ Look Up File TraitConflict.php

-> you can use "insteadof" keyword to avoid conflict

use A, B {
    A::doA insteadof B;
    B::doB insteadof A;
}

========================
** Traits Inheritance **

@@ Look Up File SayGoodBye.php

-> Trait same as class, trait can only inheritance to other trait
-> but trait can use other trait similar to interface that can implement multiple interface

trait All
{
    use SayGoodBye, SayHello, HasName, CanRun {
    // hello as private;
    // goodBye as private;
    }
}

class Person extends ParentPerson
{
    use All;

    public function run(): void
    {
        echo "Person {$this->name} is running" . PHP_EOL;
    }
}




Code Example of Trait:
trait SoundMaker {
    protected $name;

    public function setName($name) { // Concrete method
        $this->name = $name;
    }
    public function makeSound() { // Concrete method
        return "{$this->name} makes a sound.";
    }
}


trait Barking {
    public function makeSound() { // Overriding method
        return "{$this->name} says Bark";
    }
}


class Dog {
    use SoundMaker, Barking; // Using multiple traits

    public function __construct($name) {
        $this->setName($name); // Calling method from trait
    }
}

class Cat {
    use SoundMaker; // Using a single trait

    public function __construct($name) {
        $this->setName($name); // Calling method from trait
    }
    public function makeSound() { // Overriding method
        return "{$this->name} says Meow";
    }
}

END OF SECTION 21
============================================================================
START OF SECTION 22

# 22. Final #
# Final Class
# Final Function

@@ Look Up File SocialMedia.php

-> to use it you need to use "final" keyword before the xxxx

=================
** Final Class **

-> if you using final class you can't inheritance to other class


====================
** Final Function **

-> if you using final function you can't override it
-> this is good if you want to lock implementation from method that way it can't be change with child class


END OF SECTION 22
============================================================================
START OF SECTION 23

# 23. Anonymous Class #
# Anonymous Class
# Anonymous Class Constructor

@@ Look Up File AnonymousClass.php

=====================
** Anonymous Class **

-> Anonymous class is a class that doesn't have a name
-> anonymous class can declare class at the same time initialize object directly
-> this is good for when you want to make simple interface or abstract without needing to make class first

END OF SECTION 23
============================================================================
START OF SECTION 24

# 24. Static #
# Static Property
# Static Function

@@ Look Up File helper/MathHelper.php, Static.php

END OF SECTION 24
============================================================================
START OF SECTION 25

# 25. StdClass #

@@ Look Up File StdClass.php

-> StdClass is an empty class feature from PHP
-> often used for when you try to convert from data type to object
-> Std Class usefull when you want to convert array to object automatically

END OF SECTION 25
============================================================================
START OF SECTION 26

# 26. Object Iteration #
# Iterator

@@ Look Up File ObjectIteration.php

-> doing iteration with "foreach" on object


==============
** Iterator **

-> Iterator is an interface that allows you to loop through an object
-> Iterator is an object that implements the IteratorAggregate interface

IteratorAggregate, Iterator, ArrayIterator

END OF SECTION 26
============================================================================
START OF SECTION 27

# 27. Generator #

@@ Look Up File Generator.php

-> Generator is a function that can be used to generate an iterator
-> you can use "yield" keyword to generate an iterator

END OF SECTION 27
============================================================================
START OF SECTION 28

# 28. Object Cloning #
# Object Cloning
# __clone function

@@ Look Up File Student.php, ObjectCloning.php

-> sometime you want to clone object automatically rater than doing it manually
-> to clone object you need to use "clone" keyword
-> even if the property is private you can still clone it


======================
** __clone function **

-> sometimes you just want to clone some property from one object to another
-> by using __clone function you can do that


END OF SECTION 28
============================================================================
START OF SECTION 29

# 29. Overloading #
# Properties Overloading
# Function Overloading

-> Overloading is feature that dynamicly making property or function
-> Overloading have strong realtion with magic function


============================
** Properties Overloading **

@@ Look Up File PropertiesOverloading.php

Dynamic Properties

-> when accessing property, automatically property will be access but if turn out the property that you want doesn't exist,
PHP will not giving you error, PHP will do Fallback into magic function.
-> That way you can make property dynamicly by utilizing magic function

Some of magic function for Properties Overloading
Magic Function             | Description
__set($name, $value): void | executed when changing properties that doesn't exist
__get($name): mixed        | executed when accessing properties that doesn't exist
__isset($name): bool       | executed when checking isset() or empty() properties that doesn't exist
__unset($name): void       | executed when unset() properties that doesn't exist


==========================
** Function Overloading **

Dynamic Function

-> When accessing function, automatically function will be access but if turn out the function that you want doesn't exist,
PHP will not giving you error, PHP will do Fallback into magic function.
-> That way you can make function dynamicly by utilizing magic function

Some of magic function for Properties Overloading
Magic Function                          | Description
__call($name, $arguments)               | executed when calling functions that doesn't exist
static __callStatic($name, $arguments)  | executed when calling static functions that doesn't exist


END OF SECTION 29
============================================================================
START OF SECTION 30

END OF SECTION 30
============================================================================
START OF SECTION 31

# 31. Covariance and Contravariance #
# Covariance
# Contravariance


================
** Covariance **

@@ Look Up File Animal.php, AnimalShelter.php, Covariance.php

-> when you overriding function from parent class, often the child class will make the same exact function from the parent
-> Covariance make it possible so that you can override the return function from parent class with return value that more specific

-> mengembailakan return value lebih spesifik atau ke child class

====================
** Contravariance **

@@ Look Up File Animal.php, Food.php, Covariance.php

-> Contravariance allowing child class to make a function argument less spesific from its parent class


END OF SECTION 31
============================================================================
START OF SECTION 32

# 32. DateTime #
# DateInterval
# DateTimeZone
# Format DateTime
# Parse DateTime

@@ Look Up File DateTime.php

-> Manipulate date and time

Some of function for DateTime
DateTime Function                 | Description
setTime($hour, $minute, $second)  | set time
setDate($year, $month, $day)      | set date
setTimestamp($timestamp)          | set timestamp

==================
** DateInterval **

-> Manipulate some date and time using DateInterval function

==================
** DateTimeZone **

-> Set timezone

=====================
** Format DateTime **

-> Format date and time

====================
** Parse DateTime **

-> Parse date and time



END OF SECTION 32
============================================================================
START OF SECTION 33

# 33. Exception #
# Try and Catch
# Finally
# Debug Exception

@@ Look Up File ValidationException.php, Validation.php, Exception.php, LoginRequest.php

-> Exception is a special kind of error that is thrown when something goes wrong
-> Exception is used to handle error


===================
** Try and Catch **

-> In Block Try you calling method that can throw exception
-> In Block Catch you do someting if exception is thrown


=============
** Finally **

-> Finally is a block of code that will be executed regardless of whether the try block throws an exception or not


=====================
** Debug Exception **

-> using getTrace() function you can get information about exception


END OF SECTION 33
*/
