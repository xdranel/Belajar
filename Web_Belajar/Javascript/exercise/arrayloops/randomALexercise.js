//////// Array ////////
/*
const nums = [10, 20, 30];
console.log(nums);

nums[2] = 100;
console.log(nums);

function getLastValue() {
  const getValue = nums[nums.length - 1];
  console.log(getValue);
}
getLastValue();

function arraySwap() {
  const getSwap = nums[0];
  const getSwap2 = nums[2];
  nums[0] = getSwap2;
  nums[2] = getSwap;
}
arraySwap();
console.log(nums);

** accumulator array **
const ranArr = [1, 1, 3]
let totalArr = 0;

for (let i = 0; i < ranArr.length; i++) {
  totalArr += ranArr[i];
}
console.log(totalArr);

let totalArrDouble = [];
for (let i = 0; i < ranArr.length; i++) {
  totalArrDouble.push(ranArr[i] * 2);
}
console.log(totalArrDouble);

** taking an array, copy it and modify it without changing **
// original array
const array1 = [1, 2, 3];
const array2 = array1.slice();
array2.push(4);
console.log(array1);
console.log(array2);

** destructuring array using shortcut **
const [firstvalue, secondvalue] = [1, 2, 3];

//////// For Loops And While Loops ////////
** from 0 to 10 **
let dummy = 10;
let i = 0;
for (i; i <= dummy; i++) {
  console.log(i);
}
while (i <= dummy) {
  console.log(i);
  i++;
}

** from 5 to 0 **
let dummy = 0;
let i = 5
for (i; i >= dummy; i--) {
  console.log(i);
}
while (i >= dummy) {
  console.log(dummy);
  dummy--;
}

** just another example **
function removeWords() {
  for (let i = 1; i <= 20; i++) {
    if (i % 3 === 0) {
      console.log('Fizz');
    } else if (i % 5 === 0) {
      console.log('Buzz');
    } else if (i % 3 === 0 && i % 5 === 0) {
      console.log('FizzBuzz');
    } else {
      console.log(i);
    }
  }
}
removeWords()

////// Combination Of Both Array and Loops //////
** adding val to array **
const arr = [1, 2, 3];
const arr2 = [-2, -1, 0, 99];

function addNum(arr, value) {
  for (let i = 0; i < arr.length; i++) {
    arr[i] += value;
  }
}
addNum(arr, 2);
addNum(arr2, 3);
console.log(arr);
console.log(arr2);

/////////////////////////////

** adding each other array **
const arr = [1, 2, 3];
const arr2 = [4, 5, 6];
function addArrays() {
  for (let i = 0; i < arr.length; i++) {
    arr[i] += arr2[i];
  }
}
addArrays();
console.log(arr);

///////////////////////////

** count positive numb **
const arr = [1, -3, 5];
const arr2 = [-2, 3, -5, 7, 10];
let total = 0;

function countPositive(arr) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] > 0) {
      total++;
    }
  }
  return total;
}
console.log(countPositive(arr2));

//////////////////////////////

** minMax **
const nums = [];
const nums2 = [3];

function minMax(nums) {
  let min = 0;
  let max = 0;
  //if (nums.length === 0) {
  //  console.log(`Min: Null, Max: Null`);
  //}

  for (let i = 0; i < nums.length; i++) {
    if (nums[i] < min) {
      min = nums[i];
    }
    if (nums[i] > max) {
      max = nums[i];
    }
    if (nums[i] === nums[i]) {
      min = nums[i];
      max = nums[i];
    }
  }
  console.log(`Min: ${min}, Max: ${max}`);
}
minMax(nums);
minMax(nums2);

/////////////////////////////

** counting Words **
const arr = ['apple', 'grape', 'apple', 'apple', 'mango'];
function countWords(arr) {
  let count = {};
  for (let i = 0; i < arr.length; i++) {
    if (count[arr[i]]) {
      count[arr[i]]++;
    } else {
      count[arr[i]] = 1;
    }
  }
  console.log(count);
}
countWords(arr);

///////////////////////////

** taking an array, copy it and modify it without changing
   original array **
const array1 = [1, 2, 3];
const array2 = array1.slice();
array2.push(4);
console.log(array1);
console.log(array2);

** destructuring shortcut **
const [firstvalue, secondvalue] = [1, 2, 3];

//////////////////////////

** how to use break/continue with for loop and while loop **
for (let i = 1; i <= 10; i++) {
  if (i % 3 === 0) {
    continue;
  }
  console.log(i);
  if (i === 8) {
    break;
  }
}

let i = 1
while (i <= 10) {
  if (i % 3 === 0) {
    i++;
    continue
  }
  console.log(i)
  i++
}

** using return as break inside function **
function doubleArray(arr) {
  const totalArrDouble = [];

  for (let i = 0; i < arr.length; i++) {
    if (arr[i] === 0) {
      return totalArrDouble;
    }
    totalArrDouble.push(arr[i] * 2);
  }
  return totalArrDouble;
}
console.log(doubleArray([1, 2, 3]));
console.log(doubleArray([2, 4, 6, 0, 7]));

** just another example **
// remove word egg but only first 2 (i change it so it delete the last 2 egg)
const word = ['egg', 'apple', 'egg', 'egg', 'ham'].reverse();
function removeWords(array) {
  let arrFoo = [];
  let count = 0;
  for (let i = 0; i < array.length; i++) {
    if (array[i] === 'egg' && count < 2) {
      count++;
    } else if (arrFoo[i] !== 'egg') {
      arrFoo.push(array[i]);
    }
  }
  console.log(arrFoo.reverse());
}
removeWords(word);

** just another example **
function removeNumber() {
  for (let i = 1; i <= 20; i++) {
    if (i % 3 === 0) {
      console.log('Fizz');
    } else if (i % 5 === 0) {
      console.log('Buzz');
    } else if (i % 3 === 0 && i % 5 === 0) {
      console.log('FizzBuzz');
    } else {
      console.log(i);
    }
  }
}
removeNumber()

** just another example **
const arr = ['green', 'red', 'blue', 'red'];
const arr2 = ['red', 'green', 'green', 'red'];
function findIndex(arr, val) {
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] === val) {
      return 1
    }
  }
  return -1
}
console.log(findIndex(arr, 'red'))
console.log(findIndex(arr, 'yellow'))

function unique(arr) {
  let result = [];
  for (let i = 0; i < arr.length; i++) {
    if (findIndex(result, arr[i]) === -1) {
      result.push(arr[i]);
    }
  }
  return result;
}
console.log(unique(arr));
console.log(unique(arr2));
*/
