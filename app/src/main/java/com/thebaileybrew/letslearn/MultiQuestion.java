package com.thebaileybrew.letslearn;

public class MultiQuestion {

    String firstNumber;
    String secondNumber;
    int correctSolution;
    String correct;
    String mathFunc;
    String incorrectOne;
    String incorrectTwo;
    String incorrectThree;
    int checkedId = -1;
    boolean isAnswered;
    boolean answeredCorrectly = false;
    String blankAnswer = " ? ";

    public MultiQuestion(String firstNumber, String secondNumber, String correct, String mathFunc, String incorrectOne, String incorrectTwo, String incorrectThree) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.correct = correct;
        this.mathFunc = mathFunc;
        this.incorrectOne = incorrectOne;
        this.incorrectTwo = incorrectTwo;
        this.incorrectThree = incorrectThree;

    }
}
