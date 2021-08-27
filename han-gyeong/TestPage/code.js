/*
21/06/22 현재 기능:
1. 문제 불러오기
2. 답안 선택하기
  2-1) 1번 눌렀다가 4번 누르면 1번 지우고 4번 선택되기
  2-2) 다음이나 이전버튼 눌렀을때 선택지 초기화되기
3. 답안 저장하기 - 다음이나 이전버튼 누를경우 Dict에 사용자의 답안 저장
4. 답안 불러오기 - 다음이나 이전버튼 눌렀을때 사용자가 입력했던 답이 있으면 해당 답 불러오기

<TODO>
1. 사용자가 채점을 어떻게 할지,
2. 이미지가 있는 문제에 대해서는?

처음에 랜덤 10문제를 주고, 이 문제들에 대해서 풀도록 시킨후 마지막 채점을 누르면 채점 페이지로 연결되도록?

window.onload, HTML 로드 완료시:
updateData를 통한 데이터 업데이트,
addPreviousButtonListener 와 addNextButtonListener 를 통한 이전 버튼/다음 버튼에 대한 리스너 추가
*/
window.onload = function () {
  updateData();
  addPreviousButtonListener();
  addNextButtonListener();

  numberOf = document.getElementById("numberOf"); // 내가 몇번째인지를 나타낼 곳
  numberOutOf = document.getElementById("numberOutOf"); // 전체 문제 수를 나타낼 곳

  buttonList = [
    document.getElementById("answerFirstButton"),
    document.getElementById("answerSecondButton"),
    document.getElementById("answerThirdButton"),
    document.getElementById("answerFourthButton"),
  ];

  console.log(buttonList);
};

var data; // 문제 데이터 저장용 변수
var orderQuestion = -1; // 문제 순서 기억용 변수
let buttonList; // 버튼들의 리스트
var recentlyClickedButton; // 최근 클릭된 버튼을 저장하기 위한 변수
let isButtonPressed = false; // 버튼의 클릭 유무를 저장하기 위한 변수
let chosenAnswerList = {}; // 사용자가 입력한 답안을 저장하기 위한 변수
let numberOf; // 내 진행상황을 보기 위한 메소드
let numberOutOf;

/*
updateData() Method:
JSON url 을 줌으로써 해당 url에서 getJSON을 통해 JSON 을 가져와서, 전역변수인 data에 넣는다.
*/
function updateData() {
  var url = "tobe.json";
  $.getJSON(url, function (str) {
    data = str;
    numberOutOf.innerHTML = data.length;
  });
}

/*
fetchData(questionNumber) Method:
questionNumber를 인자로 받는데, 보통 orderQuestion 이 들어간다. 배열의 인덱스를 통해 값을 꺼낸다.
JSON에서 값을 꺼내서, 그 값을 returnValue 라는 배열에 담아서, insertData()에게 인자로 넘겨준다.
*/
function fetchData(questionNumber) {
  let returnValue = [
    questionNumber,
    data[questionNumber].question,
    data[questionNumber].choice[0],
    data[questionNumber].choice[1],
    data[questionNumber].choice[2],
    data[questionNumber].choice[3],
  ];
  insertData(returnValue);
}

/*
insertData(data) Method:
data를 인자로 받는데, fetchData Method의 결과물을 인자로 받는다.
HTML에 기존 제작되어 있던 TD들의 ID를 이용해서 텍스트 값을 넣는다.
data Array = [문제 번호(배열 인덱스, 0으로 시작), 문제 제목, 1번째 선택, 2번째 선택, 3번째 선택, 4번째 선택]
*/
function insertData(data) {
var question1 = document.getElementById("questionNumber");
  question1.innerHTML = data[0] + 1 + ". " + data[1];

  var td1 = document.getElementById("answerFirst");
  if (data[2].includes("http")) {
    td1.innerHTML = "<img src=" + data[2] + ">";
  } else {
    td1.innerHTML = data[2];
  }

  var td2 = document.getElementById("answerSecond");
  if (data[3].includes("http")) {
    td2.innerHTML = "<img src=" + data[2] + ">";
  } else {
    td2.innerHTML = data[3];
  }

  var td3 = document.getElementById("answerThird");
  if (data[4].includes("http")) {
    td3.innerHTML = "<img src=" + data[4] + ">";
  } else {
    td3.innerHTML = data[4];
  }

  var td4 = document.getElementById("answerFourth");
  if (data[5].includes("http")) {
    td4.innerHTML = "<img src=" + data[5] + ">";
  } else {
    td4.innerHTML = data[5];
  }
}

/*
이전 버튼 리스너 추가하기
orderQuestion, 문제 순서가 0번째일 경우(첫번째 문제) 반응이 없도록.
버튼이 눌려있을 경우(isButtonPressed == true) 사용자가 입력한 해당 답안을 저장.
그리고 눌려있는 버튼이 담겨있는(recentlyClickedButton)에서, 빨간색을 지우고, isButtonPressed 를 False로.
그리고 orderQuestion 을 1 줄여서 이전 문제에 대해서,
fetchData 로 문제 인출, getUserAnswer 로 사용자가 그 문제에 대해서 입력했던 답이 있는지 확인하기.
*/
function addPreviousButtonListener() {
  var previousButton = document.getElementById("previousQuestion");
  previousButton.addEventListener("click", function () {
    if (orderQuestion == 0) {
      return; // 첫번째 문제일경우 이전버튼의 반응이 없도록.
    }

    if (isButtonPressed) {
      saveUserAnswer(
        orderQuestion,
        recentlyClickedButton.getAttribute("value")
      );
      recentlyClickedButton.style.removeProperty("background-color");
      isButtonPressed = false;
    }

    orderQuestion -= 1;

    // 데이터를 인출하면서, 사용자가 해당 문제를 푼 기록이 있는지 확인하기
    fetchData(orderQuestion);
    getUserAnswer(orderQuestion);
    numberOf.innerHTML = orderQuestion + 1;
  });
}

function addNextButtonListener() {
  var nextButton = document.getElementById("nextQuestion");
  nextButton.addEventListener("click", function () {
    if (isButtonPressed) {
      saveUserAnswer(
        orderQuestion,
        recentlyClickedButton.getAttribute("value")
      );
      recentlyClickedButton.style.removeProperty("background-color");
      isButtonPressed = false;
    }

    if (orderQuestion == data.length - 1) {
      alert("마지막 문제입니다.");
      return;
    }

    orderQuestion += 1;
    // 데이터를 인출하면서, 사용자가 해당 문제를 푼 기록이 있는지 확인하기
    fetchData(orderQuestion);
    getUserAnswer(orderQuestion);
    numberOf.innerHTML = orderQuestion + 1;
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

/*
saveUserAnswer() Method:
questionNumber 와 chosenAnswer 를 인자로 받는다.
questionNumber 에는 보통 orderQuestion, 문제 순서가 들어가고, chosenAnswer은 버튼에 대한 value 값이 들어간다.
그래서 해당 값에서 questionNumber 를 키로, chosenAnswer를 Value 로 해서 
답안 저장용 Dict인 chosenAnswerList 에 넣는다.
*/
function saveUserAnswer(questionNumber, chosenAnswer) {
  chosenAnswerList[questionNumber] = chosenAnswer;
}

/*
getUserAnswer() Method:
questionNumber 를 인자로 받는다.
그래서 해당 문제 번호에 대한 답이 있는지 확인해서, 해당 버튼을 클릭하도록 getAnswerButtonListener 를 호출한다.
*/
function getUserAnswer(questionNumber) {
  if (chosenAnswerList[questionNumber] != null) {
    getAnswerButtonListener(chosenAnswerList[questionNumber] - 1);
  }
}

function submitButton() {
  if (numberOutOf.innerHTML != numberOf.innerHTML) {
    alert("아직 문제가 남았습니다.");
  }
}
