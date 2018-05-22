package com.thebaileybrew.letslearn;

public class multiquestion {

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
    String selectedAnswer;

    public multiquestion(String firstNumber, String secondNumber, String correct, String mathFunc, String incorrectOne, String incorrectTwo, String incorrectThree) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.correct = correct;
        this.mathFunc = mathFunc;
        this.incorrectOne = incorrectOne;
        this.incorrectTwo = incorrectTwo;
        this.incorrectThree = incorrectThree;
    }
}
