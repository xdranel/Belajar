// information stats section
const rpsResult = document.querySelector('.js-rpsResult');
const rpsMove = document.querySelector('.js-rpsMove');
const rpsScore = document.querySelector('.js-rpsScore');
const autoPlayButton = document.querySelector('.js-autoPlayButton');

// stats section
const stats = {
  result(result) {
    rpsResult.innerText = `${result}`
  },
  move(computerMove, myMove) {
    rpsMove.innerHTML = `You ${getMoveimage(myMove)} - ${getMoveimage(computerMove)} Computer`
  },
  score() {
    rpsScore.innerText = `Win: ${score.Win} Lose: ${score.Lose} Tie: ${score.Tie}`
  },
}

// score section
let score;
let storedScore = localStorage.getItem('score');
if (storedScore) {
  score = JSON.parse(storedScore);
} else {
  score = { Win: 0, Lose: 0, Tie: 0 };
}

// main code
function playGame(myMove) {
  const randomNumber = Math.random();

  let computerMove = randomMove(randomNumber);
  let result = getResult(computerMove, myMove);

  stats.move(computerMove, myMove);
  stats.result(result);
  stats.score();
  localStorage.setItem('score', JSON.stringify(score));
}

let isAutoPlay = false;
let intervalId;
function autoPlay() {
  if (!isAutoPlay) {
    intervalId = setInterval(() => {
      const playerMove = randomMove(Math.random());
      playGame(playerMove)
    }, 1000)
    isAutoPlay = true;
  } else {
    clearInterval(intervalId);
    isAutoPlay = false;
  }
}

// event listeners section
document.querySelector('.js-rockButton').addEventListener('click', () => {
  playGame('rock');
});
document.querySelector('.js-paperButton').addEventListener('click', () => {
  playGame('paper');
});
document.querySelector('.js-scissorsButton').addEventListener('click', () => {
  playGame('scissors');
});
document.querySelector('.js-resetButton').addEventListener('click', () => {
  resetGame();
});
document.querySelector('.js-autoPlayButton').addEventListener('click', () => {
  checkAutoPlay();
});
document.body.addEventListener('keydown', (event) => {
  if (event.key === 'r') {
    playGame('rock');
  } else if (event.key === 'p') {
    playGame('paper');
  } else if (event.key === 's') {
    playGame('scissors');
  }
});

// move section
function randomMove(randomNumber) {
  if (randomNumber >= 0 && randomNumber < 1 / 3) {
    return 'rock';
  } else if (randomNumber >= 1 / 3 && randomNumber < 2 / 3) {
    return 'paper';
  } else if (randomNumber >= 2 / 3 && randomNumber < 1) {
    return 'scissors';
  }
};

function getResult(computerMove, myMove) {
  if (computerMove === myMove) {
    score.Tie++;
    return 'Tie';
  } else if ((computerMove === 'rock' && myMove === 'paper') ||
    (computerMove === 'paper' && myMove === 'scissors') ||
    (computerMove === 'scissors' && myMove === 'rock')) {
    score.Win++;
    return 'You Win';
  } else {
    score.Lose++;
    return 'You Lose';
  }
};

// additional button section
function resetGame() {
  score = { Win: 0, Lose: 0, Tie: 0 };
  rpsResult.innerText = '';
  rpsMove.innerText = '';
  stats.score();
  localStorage.setItem('score', JSON.stringify(score));
};

function checkAutoPlay() {
  autoPlay();
  if (autoPlayButton.innerText === 'Auto Play') {
    autoPlayButton.innerHTML = 'Stop Auto Play';
  } else {
    autoPlayButton.innerHTML = 'Auto Play';
  }
};

// img convert
const moveType = {
  rock: 'images/rock-emoji.png',
  paper: 'images/paper-emoji.png',
  scissors: 'images/scissors-emoji.png',
};

function getMoveimage(move) {
  return `<img class="moveTypeicon" src="${moveType[move]}">`
};
