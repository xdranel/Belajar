let inputPrice = document.querySelector('.js-price');
let inputValue = Number(inputPrice.value);
let costAfter = document.querySelector('.js-cost');

function cost() {
  if (inputValue === 0 || inputValue < 0) {
    costAfter.innerText = `Invalid Price`
  } else if (inputValue > 0 && inputValue < 40) {
    inputValue = (inputValue + 10).toFixed(2);
    costAfter.innerText = `$${inputValue}`
  } else if (inputValue > 40) {
    costAfter.innerText = `$${inputValue.toFixed(2)} - Free Shipping`
  }
}

function calculate() {
  cost();
  inputPrice.value = '';
}

function handleKeydown(event) {
  if (event.key === 'Enter') {
    calculate();
  }
}
