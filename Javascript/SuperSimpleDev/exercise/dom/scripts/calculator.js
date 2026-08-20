// display calculation
let displayCalculation = document.querySelector('.js-displayCalculation');
function showCalculation() {
  displayCalculation.textContent = calculation;
}

// calculator-script
let calculation = '';
let storedCalculation = localStorage.getItem('calculation');
if (storedCalculation) {
  calculation = storedCalculation;
  showCalculation();
} else {
  calculation = '';
}

// main code
function math(value) {
  calculation += value
  showCalculation();
  localStorage.setItem('calculation', calculation);
}

function calculate(calculation) {
  let result = eval(calculation);
  displayCalculation.textContent = result;
  localStorage.setItem('calculation', result);
}

function clearCalculation() {
  calculation = '';
  showCalculation();
}
