// default quantity
let quantity = 0;
let nextQuantity = 0;
let prevQuantity = 0;
const maxQuantity = 10;
const minQuantity = 0;

// quantiti info
let cartQuantity = document.querySelector('.js-cartQuantity');

function showQuantity() {
  cartQuantity.innerHTML = `Cart Quantity: ${quantity}`
}

// cart-script
function addQuantity(number) {
  nextQuantity = quantity + number;
  if (nextQuantity > maxQuantity) {
    alert(`Cart Will Exceed the Maximum Quantity`);
  } else {
    quantity = nextQuantity;
    showQuantity();
  }
}
function removeQuantity(number) {
  prevQuantity = quantity - number;
  if (prevQuantity < minQuantity) {
    alert(`Cart Will Be Below the Minimum Quantity`);
  } else {
    quantity = prevQuantity;
    showQuantity();
  }
}
function resetQuantity() {
  quantity = 0;
  cartQuantity.innerHTML = '';
}
