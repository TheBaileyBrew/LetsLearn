package com.thebaileybrew.letslearn;

public class PatternQuestion {

    String firstNumber;
    String secondNumber;
    String thirdNumber;
    String fourthNumber;
    String finalAnswer;
    String correctAnswerOne;
    String correctAnswerTwo;
    String incorrectAnswerOne;
    String incorrectAnswerTwo;
    String increase = "INCREASE";
    String decrease = "DECREASE";
    String looping = "LOOPING";

    public PatternQuestion(String firstNumber, String secondNumber, String thirdNumber, String fourthNumber, String finalAnswer, String correctAnswerOne, String correctAnswerTwo, String incorrectAnswerOne, String incorrectAnswerTwo) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
        this.fourthNumber = fourthNumber;
        this.finalAnswer = finalAnswer;
        this.correctAnswerOne = correctAnswerOne;
        this.correctAnswerTwo = correctAnswerTwo;
        this.incorrectAnswerOne = incorrectAnswerOne;
        this.incorrectAnswerTwo = incorrectAnswerTwo;
    }
}
