/*
** Asynchronous Code **
// Mean wont wait for a line to finish
// before going to next line
// Synchronous Code **
// Mean will wait for a line to finish
// before going to next line

** setTimeout feature **
// it doesnt wait for X sec to finish it just set timer
// and then skip it after X sec it will execute
setTimeout(function () {
  console.log('Halo Cintakuh');
  console.log('Halo Sayangkuh');
}, 3000);
console.log('Next Line 1');

** setInterval feature **
// will run function every X sec setInterval(function () {
  console.log('Halo Bubu');
  console.log('Halo Beh');
}, 3000);
console.log('Next Line 2');

** forEach feature **
// Easier and understanable way to iterate
// in this example using it with continue
// but for break there is no forEach
// so to use break better use forLoop
[
  'make dinner',
  'wash dishes',
  'go to work'
].forEach(function (value, index) {
  if (value === 'wash dishes') {
    return;
  }
  console.log(`${value}, index: ${index}`);
})

** just an example **
#1. ****
let add = function () {
  console.log(2 + 3);
};
function runTwice(add) {
  add();
  add();
}
runTwice(function () {
  console.log('12b')
});
runTwice(add);

#2. ****
const startButton = document.querySelector('.js-startButton');
let isRunning = false;
function start() {
  if (startButton.innerHTML === 'Start') {
    startButton.innerHTML = 'Loading...';
    isLoading = true;
    setTimeout(function () {
      startButton.innerHTML = 'Finished';
      isLoading = false;
    }, 1000);
  } else if (startButton.innerHTML === 'Finished') {
    startButton.innerHTML = 'Loading...';
    isLoading = true;
    setTimeout(function () {
      startButton.innerHTML = 'Start';
      isLoading = false;
    }, 1000);
  }
}

#3. ****
const addCartButton = document.querySelector('.js-addCartButton');
const addOutput = document.querySelector('.js-addOutput');
let timeoutId;
function addCart() {
  addOutput.innerText = 'Added To Cart';
  clearTimeout(timeoutId);
  timeoutId = setTimeout(function () {
    addOutput.innerText = '';
  }, 2000)
}

#4. ****
const counterButton = document.querySelector('.js-counterButton');
let notifCount = 0;
let intervalId = null;
function updateTitle(newTitle, notifCount) {
  if (intervalId) {
    clearInterval(intervalId);
  }
  if (notifCount < 0) {
    document.title = 'Error App';
  } else if (notifCount === 0) {
    document.title = 'Amazon App';
  } else {
    intervalId = setInterval(function () {
      document.title = `(${notifCount}) ${newTitle}`;
    }, 500)
  }
}
function counterNotif(event) {
  const button = event.target;
  if (button.innerHTML === 'Add') {
    notifCount++;
  } else if (button.innerHTML === 'Remove') {
    notifCount--;
  }
  updateTitle('New messages', notifCount);
}
document.querySelectorAll('.js-counterButton').forEach(button => {
  button.addEventListener('click', counterNotif);
});

** Normal Function vs Arrow Function **
// passing function into another function
// recomend to use arrow function
// but remember this not everthing use arrow function
// always remember 2 thing normal function easier to read
// and it also have hoisting feature

const regularFunc = function (param, param2) {
  console.log('Hello');
  return 5;
}
const arrowFunc = (param, param2) => {
  console.log('Hello');
  return 5;
}
arrowFunc();

// when using arrow function if the parameter is only one
// you dont need to use -> ()
const oneParam = param => {
  console.log(param + 2);
}
oneParam(2);

// one line arrow function
const oneLine = () => 2 + 3;
console.log(oneLine());

** arrow function inside object **
// when it comes to object
// use shorthand method than arrow function
const object = {
  method: () => {
  },
  method(){
  }
}

** addEventListener feature **
// had some advantage 
// it can have multiple event listener for an event
const buttonElement = document.querySelector('.js-clickButton')
const eventListener = () => {
  console.log('Hello World');
};

buttonElement.addEventListener('click', eventListener);
buttonElement.removeEventListener('click', eventListener);

buttonElement.addEventListener('click', () => {
  console.log('World Hello');
});

// i will use example
#1.
document.querySelector('.js-rockButton').addEventListener('click', () => {
  playGame('rock');
});

#2.
document.body.addEventListener('keydown', (event) => {
  if (event.key === 'r') {
    playGame('rock');
  } else if (event.key === 'p') {
    playGame('paper');
  } else if (event.key === 's') {
    playGame('scissors');
  }
});

#3.
document.querySelectorAll('.js-deleteTodoButton').forEach((deleteButton index) => {
  deleteButton.addEventListener('click', () => {
    deleteTodo(index)
  });
});

// 2 array methods other than .forEach
// .filter() and .map()

#1. filter()
// to filter the array with some condition
// return true/false to filter the array and return new array
[1, -3, 5].filter((value, index) => {
  if (value >= 0) {
    return true;
  } else {
    return false;
  }
  return value >= 0;
}));

#2. map()
// return anything input in return and put it in new array
console.log([1, 1, 3].map((value, index) => {
  return 10;
}));
console.log([1, 1, 3].map((value, index) => {
  return (value + value) * 2;
}));
console.log([1, 1, 3].map(value => (value + value) * 2));

// closure





















*/


