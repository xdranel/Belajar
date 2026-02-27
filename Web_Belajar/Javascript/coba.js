let count = 0;
document.querySelector(".js-button").addEventListener("click", () => {
  startCount();
});

function startCount() {
  count++;
  document.querySelector(".js-count").innerHTML = count;
}
