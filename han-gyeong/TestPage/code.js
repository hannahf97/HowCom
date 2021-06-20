/*
TODO
1. 문제별로 어떤 답을 클릭했었는지, 사용자도 확인이 가능하게?
2. 답 레이블링? 답을 어떻게 맞출지 고민.
*/

window.onload = function () {
  updateData();
  addPreviousButtonListener();
  addNextButtonListener();

  buttonList = [
    document.getElementById("answerFirstButton"),
    document.getElementById("answerSecondButton"),
    document.getElementById("answerThirdButton"),
    document.getElementById("answerFourthButton"),
  ];
};

var data;
var orderQuestion = -1;
let buttonList;
var recentlyClickedButton;
let isButtonPressed = false;

function updateData() {
  var url = "data.json";
  $.getJSON(url, function (str) {
    data = str;
    console.log(data);
  });
}

function fetchData(questionNumber) {
  let returnValue = [
    questionNumber,
    data[questionNumber].question,
    data[questionNumber].answer[0],
    data[questionNumber].answer[1],
    data[questionNumber].answer[2],
    data[questionNumber].answer[3],
  ];
  insertData(returnValue);
}

function insertData(data) {
  var question1 = document.getElementById("question");
  question1.innerHTML = data[0] + 1 + ". " + data[1];

  var td1 = document.getElementById("answerFirst");
  td1.innerHTML = data[2];

  var td2 = document.getElementById("answerSecond");
  td2.innerHTML = data[3];

  var td3 = document.getElementById("answerThird");
  td3.innerHTML = data[4];

  var td4 = document.getElementById("answerFourth");
  td4.innerHTML = data[5];
}

function addPreviousButtonListener() {
  var previousButton = document.getElementById("previousQuestion");
  previousButton.addEventListener("click", function () {
    orderQuestion -= 1;

    if (orderQuestion < 0) {
      alert("첫 문제입니다.");
      orderQuestion = -1;
      return;
    }

    if (isButtonPressed) {
      recentlyClickedButton.style.removeProperty("background-color");
      isButtonPressed = false;
    }

    fetchData(orderQuestion);
  });
}

function addNextButtonListener() {
  var nextButton = document.getElementById("nextQuestion");
  nextButton.addEventListener("click", function () {
    if (isButtonPressed) {
      recentlyClickedButton.style.removeProperty("background-color");
      isButtonPressed = false;
    }

    orderQuestion += 1;
    fetchData(orderQuestion);
  });
}

function getAnswerButtonListener(btn) {
  if (isButtonPressed) {
    recentlyClickedButton.style.removeProperty("background-color");
    isButtonPressed = false;
  }
  buttonList[btn].style.backgroundColor = "#E35252";
  recentlyClickedButton = buttonList[btn];
  isButtonPressed = true;
}
