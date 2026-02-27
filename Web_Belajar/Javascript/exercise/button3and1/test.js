const catButtonOne = document.querySelector('.js-catButtonOne');
const catButtonTwo = document.querySelector('.js-catButtonTwo');
const catButtonThree = document.querySelector('.js-catButtonThree');

// function 3 button can change style
//function handleClick(event) {
//  if (event.target.matches('.js-catButtonOne, .js-catButtonTwo, .js-catButtonThree')) {
//    event.target.classList.toggle('isToggled');
//  }
//}

// function one button at time
function handleClick(event) {
  catButtonOne.classList.remove('isToggled');
  catButtonTwo.classList.remove('isToggled');
  catButtonThree.classList.remove('isToggled');

  event.target.classList.add('isToggled');
}

catButtonOne.addEventListener('click', handleClick);
catButtonTwo.addEventListener('click', handleClick);
catButtonThree.addEventListener('click', handleClick);
