// information section
let moveInfo = document.querySelector('.js-moveInfo');
let resultInfo = document.querySelector('.js-resultInfo');
let scoreInfo = document.querySelector('.js-scoreInfo');

const output = {
  showMove(myMove, coinResult) {
    moveInfo.innerText = `You: ${myMove} - Coin: ${coinResult}`
  },
  showResult(result) {
    resultInfo.innerText = `${result}`
  },
  showScore() {
    scoreInfo.innerText = `Win: ${score.Win} - Lose: ${score.Lose}`
  },
}

// score section
let score;
let storedScore = localStorage.getItem('score');
if (storedScore) {
  score = JSON.parse(storedScore);
} else {
  score = { Win: 0, Lose: 0 };
}

// main code
function playgame(myMove) {
  const randomNumber = Math.random();
  let coinResult = randomMove(randomNumber);
  let result = getResult(coinResult, myMove);
  output.showMove(myMove, coinResult);
  output.showResult(result);
  output.showScore();
  localStorage.setItem('score', JSON.stringify(score));
}

function randomMove(randomNumber) {
  if (randomNumber < 0.5) {
    return 'Head'
  } else {
    return 'Tail'
  }
}

function getResult(coinResult, myMove) {
  if (coinResult === myMove) {
    score.Win++;
    return 'You Win';
  } else {
    score.Lose++;
    return 'You Lose';
  }
}

function resetgame() {
  score = { Win: 0, Lose: 0 };
  moveInfo.innerText = '';
  resultInfo.innerText = '';
  output.showScore();
  localStorage.setItem('score', JSON.stringify(score));
}
