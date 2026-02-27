// Dokumentasi Pembelajaran Javascript

/* ===== INDEX =====
 001. BASIC
 002. NUMBER and MATH
 003. TEXT(STRING)
 004. VARIABLE
 005. BOOLEAN
 006. IF STATEMENT
 007. VARIABLE SCOPE
 008. TRUTHY AND FALSY
 009. FUNCTION

 010. OBJECT
 011. JSON
 012. LOCAL STORAGE
 013. NULL & UNDEFINED
 014. AUTO BOXING
 015. OBJECT SHORTCUT

 016. DOM
 017. ARRAY
 018. FOR LOOP AND WHILE

 019. ADVANCED FUNCTIONS
 020.
*/

// 001. BASIC
/*
 * Order Of Operations *
 * 1. ()
 * 2. * /
 * 3. + -
 alert popup func
 to round numbers use Math.round()
 example :
 Math.round(20.95 + 7.99) is 28 wtf is this ai LULE the right answer is 29
 codeium your rounding numbers is wrong its not 28 but 29

 Another Example :
 Math.floor(29.99) is 29
 Math.ceil(29.99) is 30
*/

// 003. TEXT(STRING)
/*
 string have same order of operations like numbers

 '' / ""
 "Hello World!"
 \n

 if you enclose a string in quotes you can use the following
 "I'm learning javascript"
 'I\'m learning javascript'
 or just use  template string ``
 output : "I'm learning javascript"

 * Combine 2 Strings Text(Concatenation) *
 'Hello' + 'World'
 output : 'HelloWorld'

 * Type Conversion(Coercion) *
 'Cok' + 10
 output : 'Cok10'

 Example Combining Strings and Numbers:
 "$" + ((2095 + 799) / 100)
 output : "$28.94"

 alert("Items (" + (1 + 1) + "):  $" + ((2095 + 799) / 100) );
 "Items (" + (1 + 1) + "):  $" + ((2095 + 799) / 100)
 output : "Items (2):  $28.94"

#DONT YOU FORGOT ABOUT THIS FEATURE#
 * Template Strings *
 First Feature Interpolation Using ${}
 `Cok ${10 * 2}`
 output : 'Cok 20'

 Using interpolation feature
 the $ sign near {} is interpolated
 `Items (${1+1}): $ ${((2095+799) / 100)}`
 output : "Items (2): $ 28.94"

 but if you write code like this
 `Items (${1+1}): $ $ {((2095+799) / 100)}`
 output : "Items (2): $ $ {((2095+799) / 100)}"

 Second Feature Multiline
 `Cok
 10
 20
 30`
 output : "Cok\n10\n20\n30"

 exercise:
 'Total cost: $' + (5 + 3);
 `Total Cost: $${5+3}`
 alert(`Total Cost: $${5+3}`);

 'Total cost: $' + ((599 + 295) / 100)
 `Total Cost: $${((599 + 295) / 100)}`

 alert(`Total Cost: $${((599 + 295) / 100)}
 Thank You, Come Again!`);
*/

// 004. VARIABLE
/*
  Read it you dumbass you'll understand it
  Use let for variables that will change value.
  Use const for variables that will not change value.
  Avoid using var unless necessary for legacy code or older browser support.

  let variable1 = 10;
  console.log(variable1);

  let calculation = 2 * 3;
  console.log(calculation);
  console.log(calculation + 1);

  let result = calculation + 1;
  console.log(result);

  let message = 'Hello World!';
  console.log(message); console.log(';');

  variable1 = 27;
  console.log(variable1);

  variable1 = variable1 + 1;
  console.log(variable1);

*/

// 005. BOOLEAN
/*
 true / 1
 false / 0

 * All Comparisons Operator *
 * Recommend to use === and !== instead of == and != *
 * because == or != are used for comparing values and === or !== is used for comparing values and types. *
 * have lower priority in the order of operations. *
 1 == 1
 1 === 1
 1 != 1
 1 !== 1
 1 > 1
 1 < 1
 1 >= 1
 1 <= 1
*/

// 006. IF STATEMENT
/*
 * All Logical Operators *
 * have even lower priority than all other operators. *
 Example:
 console.log(0.2 >= 0 && 0.2 < 1/3);

 1 && 2
 1 || 2
 1 ! 2
 1 ? 2 : 3
 1 ? 2 : 3 ? 4 : 5


 if (condition) {
   console.log('Hello World!');
 } else {
   console.log('Bye World!');
 }
*/

// 007. VARIABLE SCOPE
/*
 * There Is Global Scope and Local Scope *
 * There Is Block Scope and Function Scope *
 * For Example: *
 if (true) {
  var x = 10; // function scope
  let y = 20; // block scope
  const z = 30; // block scope
}

console.log(x); // 10 (accesses function scope)
console.log(y); // ReferenceError: y is not defined (block scope)
console.log(z); // ReferenceError: z is not defined (block scope)

function foo() {
  var a = 40; // local scope
  let b = 50; // local scope
  const c = 60; // local scope
}

foo();

console.log(a); // ReferenceError: a is not defined (local scope)
console.log(b); // ReferenceError: b is not defined (local scope)
console.log(c); // ReferenceError: c is not defined (local scope)
*/

// 008. TRUTHY AND FALSY
/*
 * There Is Truthy And Falsy Values *
 * There Is True And False *
 * For Example: *
 if (1) {
  console.log('Truthy');
 } else {
  console.log('Falsy');
 }
*/

// 009. FUNCTION
/*
 function function1() {
   console.log('Hello World!');
   console.log(20 + 20);
 }
 function1();

 function can have default value
 function function2(x = 10, y = 20) {
   console.log(x + y);
 }
 function2();

 if the parameter is undefined it will use default value

 but if you set the parameter with NULL it will not use default value
 it will use NULL
*/

// 010. OBJECT
/*
 object = {
   key: value
 }

  /*
  const product = {
    name: 'socks',
    price: 1090,
  };

  console.log(product);
  console.log(product.name);
  console.log(product.price);

  product.name = 'cotton socks';
  console.log(product.name);

  product.newProperty = true;
  console.log(product);

  const product2 = {
    clothes: 'polo',
    pants: 'nike',
    ['delivery-time']: '3 days',
    use the bracket notation to make object property dynamic

    rating: {
      stars: 3,
      count: 100,
    },

    // when function inside object it's called method
    // like console.log() , math.max() etc
    func1: function function1() {
      console.log('function1');

    },
  };

  you can access object using dot or bracket notation
  ussualy bracket notation is used when you access variable object that property dynamic
  console.log(product2);
  console.log(product2.clothes);
  console.log(product2['pants']);
  console.log(product2['delivery-time']);

  console.log(product2.rating.stars);

  product2.func1();

  * Object are reference type *
  * While the concept of object references in JavaScript shares some
    similarities with pointers in C, they're not exactly the same thing. *
  Example:
  const object1 = {
  foo: 'bar',
  };
  const object2 = object1;
  object2.foo = 'baz';
  console.log(object1.foo); output: 'baz'

  *we cant compare objects directly because they are reference type*
  const object3 = {
  foo: 'baz',
  };
  console.log(object3 === object1); output: false

  so both of them comparing references not values
*/

// 011. JSON 
/*
  JSON = JavaScript Object Notation
  JSON similar to object but it's written in JSON format but with less feature
  JSON have its own syntax
  Use JSON to send data from server to client JSON is a format for data interoperation
  JSON also can be use for storing data in databases

  * Built In Functions *
  JSON.stringify(object) - convert object to JSON
  JSON.parse(string) - convert JSON to object

  Example:
  JSON.stringify(product2);
  JSON.stringfy does not support function into JSON
  JSON.stringify the result will be string

  const jsonstring = JSON.stringify(product2);
  console.log(JSON.parse(jsonstring);
  JSON.parse help convert string to object

*/

// 012. LOCAL STORAGE
/*
  Local Storage save values permanently
  usually to save values in variables but variables are temporary
  because when refresh/close browser the data is gone

  Local Storage only save string values
  so to save object or array you need to convert it to JSON

  localStorage.setItem('key', 'value');
  * to save value *

  localStorage.getItem('key');
  * to get the sring out of local storage *

  localStorage.removeItem('key');
  * to remove a value from local storage *
*/

// 013. NULL & UNDEFINED
/*
  null = empty
  undefined = no value

  use null if you intentionally want an empty value
  use undefined if you don't want to set a value

  but in mose case null and undefined is the same
  so use null or undefined
*/

// 014. AUTO BOXING
/*
  auto boxing can be used with number,string,boolean
  and object but not null and undefined it will give an error
  for example :

  console.log(10 + 20);
  console.log(10 + '20');
  console.log(false + 10);
  console.log(null + 10);
  console.log(undefined + 10);

  console.log('hello'.length);
  console.log('hello'.toUpperCase());
*/

// 015. OBJECT SHORTCUT
/*
  const object4 = {
    message: 'Hello World!',
    price: 999,
  }

  * destructing *
  easier way to take properties out of object

  * use object property
  const message = object4.message;
  * both of this up and down are the same thing
  const { message } = object4;

  console.log(message);

  you can also do multiple properties
  const { message, price } = object4;
  console.log(message);
  console.log(price);

  * shorthand property *
  it takes any value inside object and put it as property

  const object5 = {
    message: message,
    * both up and down are doing the same thing
    message
  };
  console.log(object5);

  * shorthand method *
  const object5 = {
    message,
    method: function() {
      console.log('method');
    }
  };
  console.log(object5);
  object5.method();

  or you can make it like this
  both of this up and down are doing the same thing

  const object5 = {
    message,
    method() {
      console.log('method');
    }
  };
  console.log(object5);
  object5.method();
*/

// 016. DOM
/*
  DOM = Document Object Model
  to control the page

  * to change title *
  document.title
  document.title = 'Halo Cok';

  * to change html content *
  document.body
  document.body.innerHTML
  document.body.innerHTML = '<h1>Halo</h1>';

  document.body.innerText
  document.body.innerText = 'Halo Cok';

  * to get any element from the page and put it inside javascript *
  document.querySelector()
  document.querySelector('h1')

  another example :
  document.querySelector('button').innerHTML = 'First Button'

  document.querySelector('.js-button').innerHTML = 'Second Button'

  *There is events and event listener*
  * clicks, keydown => event
  * onclick, onkeydown => listener

  rest of example u can just look to your older exercise
  -. lesson/butt3and1/..

*/

// 017. ARRAY
/*
  * Array are references

  const myArray = [20, 40, 60];
  const arr = [10, 'Hallo', false, stats = { rating: 4, stars: 3 }, [20, 3]];

  console.log(arr[4]);

  console.log(typeof [1, 2]);
  console.log(Array.isArray(myArray));

  console.log(myArray.length);

  myArray.push(100);
  console.log(myArray);

  myArray.splice(1, 1);
  console.log(myArray);

*/

// 018. FOR LOOP AND WHILE
/*
let i = 1;
while (i <= 5) {
  console.log(i);
  i++;
}

for (let i = 1; i <= 5; i++) {
  console.log(i);
}

let randomNumb = 0;
while (randomNumb < 0.5) {
  randomNumb = Math.random();
}
console.log(randomNumb);

const todoList = [
  'make dinner',
  'wash dishes',
  'watch Tv'
];
for (let i = 0; i < todoList.length; i++) {
  console.log(`${todoList[i]}, index: ${i}`);
}
*/

// 019. ADVANCED FUNCTIONS
/*
 * Functions Are Value
// Normal Function
// Have 2 Advantages Easier To Read/Debug And Hoisting
// So You Can Call It Before Defining It
function halo() {
  console.log('Halo Dek');
};
halo();

// func1(); ==> this will give you an error
// Anonymous Function
// But if you save func inside variable you cant call it
// before defining it
const func1 = function () {
  console.log(`Halo Kak`);
};
console.log(func1);
func1();

const object1 = {
  num: 2,
  fun: function () {
    console.log('Halo Yang');
  }
};
object1.fun();

// Normal Passing value into function
function display(param) {
  console.log(param);
};
display(2);

// Passing function into function
function run(param) {
  param();
};
run(function () {
  console.log('Halo Sayangku');
});
 
*/


