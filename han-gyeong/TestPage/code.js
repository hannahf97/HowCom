/*
1. 답안을 문제랑 매칭시켜야 한다, 답을 하나하나 직접 매칭시켜야 할것같다, 그리고 그걸 저장하는 방식(데이터 변환)을 정해야 한다.
2. 디자인을 끼워맞춰야 한다.
*/
window.onload = function() {
	getQuestion();
	getNextQuestion();
	getPreviousQuestion();
}

var data;
var orderQuestion = 0;

function getQuestion() {
	var url = "data.json";
	$.getJSON(url, function(str) {
		data = str;
		console.log(data);
	});
}

function updateInfo(questionNumber) {
	insertTable(questionNumber, data[questionNumber].question, data[questionNumber].answer[0], data[questionNumber].answer[1], data[questionNumber].answer[2],data[questionNumber].answer[3]);
	/*
	for (var i = 0; i < str.length; i++) {
		insertTable(str[i].question, str[i].answer[0], str[i].answer[1], str[i].answer[2],str[i].answer[3]);
		}
*/
}
	
function insertTable(questionNumber, question, answerFirst, answerSecond, answerThird, answerFourth) {
	var question1 = document.getElementById("question");
	question1.innerHTML = (questionNumber + 1) + ". " + question;

	var td1 = document.getElementById("answerFirst")
	td1.innerHTML = answerFirst;

	var td2 = document.getElementById("answerSecond")
	td2.innerHTML = answerSecond;

	var td3 = document.getElementById("answerThird")
	td3.innerHTML = answerThird;

	var td4 = document.getElementById("answerFourth")
	td4.innerHTML = answerFourth;
}

function getPreviousQuestion() {
	var previousButton = document.getElementById("btn1");
	previousButton.addEventListener("click", function() {
		orderQuestion -= 1;
		updateInfo(orderQuestion);
	});
}

function getNextQuestion() {
	var nextButton = document.getElementById("btn2");
	nextButton.addEventListener("click", function() {
		updateInfo(orderQuestion);
		orderQuestion++;
	});
}