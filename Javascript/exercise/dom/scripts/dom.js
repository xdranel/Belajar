const subButton = document.querySelector('.js-subButton');

function checkSubscribe() {
  if (subButton.innerText === 'Subscribe') {
    subButton.innerHTML = 'Subscribed';
    subButton.classList.add('isSubscribed');
  } else {
    subButton.innerHTML = 'Subscribe';
    subButton.classList.remove('isSubscribed');
  }
}
