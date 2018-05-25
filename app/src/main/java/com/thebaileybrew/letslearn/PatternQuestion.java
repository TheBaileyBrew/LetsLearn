package com.thebaileybrew.letslearn;

public class PatternQuestion {

    String firstNumber;
    String secondNumber;
    String thirdNumber;
    String fourthNumber;
    String finalAnswer;
    String correctAnswerOne;
    String incorrectAnswerOne;
    String incorrectAnswerTwo;
    String incorrectAnswerThree;
    String increase = "INCREASE";
    String decrease = "DECREASE";
    String looping = "LOOPING";

    public PatternQuestion(String firstNumber, String secondNumber, String thirdNumber, String fourthNumber, String finalAnswer, String correctAnswerOne, String incorrectAnswerOne, String incorrectAnswerTwo, String incorrectAnswerThree) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
        this.fourthNumber = fourthNumber;
        this.finalAnswer = finalAnswer;
        this.correctAnswerOne = correctAnswerOne;
        this.incorrectAnswerThree = incorrectAnswerThree;
        this.incorrectAnswerOne = incorrectAnswerOne;
        this.incorrectAnswerTwo = incorrectAnswerTwo;
    }
}
