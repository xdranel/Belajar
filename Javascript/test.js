/*
[
  'make dinner',
  'wash dishes',
  'go to work'
].forEach((value, index) => {
  if (value === 'wash dishes') {
    return;
  }
  console.log(`${value}, index: ${index}`);
})

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

const object = {
  method: () => {
  },
  method(){
  }
}

const buttonElement = document.querySelector('.js-clickButton')
const eventListener = () => {
  console.log('Hello World');
};

buttonElement.addEventListener('click', eventListener);
buttonElement.removeEventListener('click', eventListener);

buttonElement.addEventListener('click', () => {
  console.log('World Hello');
});

console.log([1, -3, 5].filter((value, index) => {
  if (value >= 0) {
    return true;
  } else {
    return false;
  }
  return value >= 0;
}));

console.log([1, 1, 3].map(value => (value + value) * 2));
*/
