package com.thebaileybrew.letslearn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class FragmentPatterns extends Fragment {

    public static final String TAG = "PatternsFragment";

    RecyclerView recyclerView;
    RVAdapterPatterns adapterPatterns;
    private List<PatternQuestion> QuestionPatterns;

    int firstNumber;
    int secondNumber;
    int thirdNumber;
    int fourthNumber;
    int correctAnswer;

    int countOfCorrectAnswers = 0;
    int countOfIncorrectAnswers = 0;
    int countOfCorrectPattern = 0;
    int countOfIncorrectPattern = 0;

    String patternFirstNumber;
    String patternSecondNumber;
    String patternThirdNumber;
    String patternFourthNumber;
    String correctCheckBoxAnswer;
    String incorrectCheckboxOne;
    String incorrectCheckboxTwo;
    String incorrectCheckboxThree;

    Boolean answeredCorrectly = false;
    Boolean patternCorrect = false;

    int patternType;
    int rateOfChange;
    //Predefined Strings
    String finalAnswer = " ? ";

    TextView correctAnswerCount;
    TextView correctPatternCount;

    TextView totalAnswerCount;
    TextView totalPatternCount;


    public static FragmentPatterns newInstance() {
        FragmentPatterns fragment = new FragmentPatterns();
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pattern_recognition, container, false);

        QuestionPatterns = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_display);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapterPatterns = new RVAdapterPatterns(QuestionPatterns);
        recyclerView.setAdapter(adapterPatterns);
        recyclerView.setLayoutManager(layoutManager);
        correctAnswerCount = view.findViewById(R.id.correct_total_pattern);
        totalAnswerCount = view.findViewById(R.id.total_pattern);
        correctPatternCount = view.findViewById(R.id.correct_total_patternID);
        totalPatternCount = view.findViewById(R.id.total_patternID);

        Button patternsSubmit = view.findViewById(R.id.submit_answer_button_pattern);
        patternsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForCorrectResponse();
                if (answeredCorrectly) {
                    Toast.makeText(v.getContext(), "That's correct. The next in the pattern is " + String.valueOf(correctAnswer), Toast.LENGTH_LONG).show();
                    correctAnswerCount.setText(String.valueOf(countOfCorrectAnswers));
                    totalAnswerCount.setText(String.valueOf(countOfCorrectAnswers + countOfIncorrectAnswers));
                } else {
                    Toast.makeText(v.getContext(), "Almost, the next in the pattern was " + String.valueOf(correctAnswer), Toast.LENGTH_LONG).show();
                    correctAnswerCount.setText(String.valueOf(countOfCorrectAnswers));
                    totalAnswerCount.setText(String.valueOf(countOfCorrectAnswers + countOfIncorrectAnswers));
                }
                if (patternCorrect) {
                    correctPatternCount.setText(String.valueOf(countOfCorrectPattern));
                    totalPatternCount.setText(String.valueOf(countOfCorrectPattern + countOfIncorrectPattern));
                } else {
                    correctPatternCount.setText(String.valueOf(countOfCorrectPattern));
                    totalPatternCount.setText(String.valueOf(countOfCorrectPattern + countOfIncorrectPattern));
                }

                createQuestionPattern();
            }
        });


        createQuestionPattern();
        return view;
    }

    private void checkForCorrectResponse() {
        CheckBox firstCheckBox = recyclerView.findViewById(R.id.checkbox_one);
        CheckBox secondCheckBox = recyclerView.findViewById(R.id.checkbox_two);
        CheckBox thirdCheckBox = recyclerView.findViewById(R.id.checkbox_three);
        CheckBox fourthCheckBox = recyclerView.findViewById(R.id.checkbox_four);
        String checkBoxOneText = firstCheckBox.getText().toString();
        String checkBoxTwoText = secondCheckBox.getText().toString();
        String checkBoxThreeText = thirdCheckBox.getText().toString();
        String checkBoxFourText = fourthCheckBox.getText().toString();
        CheckBox increaseCheckBox = recyclerView.findViewById(R.id.checkbox_increase);
        CheckBox decreaseCheckBox = recyclerView.findViewById(R.id.checkbox_decrease);
        CheckBox loopingCheckBox = recyclerView.findViewById(R.id.checkbox_looping);
        switch(patternType) {
            case 1: //INCREASING PATTERN
                if(firstCheckBox.isChecked() && checkBoxOneText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else if (secondCheckBox.isChecked() && checkBoxTwoText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else if (thirdCheckBox.isChecked() && checkBoxThreeText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else if (fourthCheckBox.isChecked() && checkBoxFourText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else {
                    answeredCorrectly = false;
                    countOfIncorrectAnswers = countOfIncorrectAnswers + 1;
                    //Sorry that's not correct
                    //Update tally score
                }
                //Check for Pattern Identification
                if (increaseCheckBox.isChecked()) {
                    patternCorrect = true;
                    countOfCorrectPattern = countOfCorrectPattern + 1;
                } else {
                    patternCorrect = false;
                    countOfIncorrectPattern = countOfIncorrectPattern + 1;
                }



                break;
            case 2: //DECREASING PATTERN
                if(firstCheckBox.isChecked() && checkBoxOneText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else if (secondCheckBox.isChecked() && checkBoxTwoText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else if (thirdCheckBox.isChecked() && checkBoxThreeText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else if (fourthCheckBox.isChecked() && checkBoxFourText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else {
                    answeredCorrectly = false;
                    countOfIncorrectAnswers = countOfIncorrectAnswers + 1;
                    //Sorry that's not correct
                    //Update tally score
                }
                break;
            case 3: //LOOPING PATTERN
                if(firstCheckBox.isChecked() && checkBoxOneText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else if (secondCheckBox.isChecked() && checkBoxTwoText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else if (thirdCheckBox.isChecked() && checkBoxThreeText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else if (fourthCheckBox.isChecked() && checkBoxFourText.equals(String.valueOf(correctAnswer))) {
                    answeredCorrectly = true;
                    countOfCorrectAnswers = countOfCorrectAnswers + 1;
                } else {
                    answeredCorrectly = false;
                    countOfIncorrectAnswers = countOfIncorrectAnswers + 1;
                    //Sorry that's not correct
                    //Update tally score
                }
                break;
        }
    }

    private void createQuestionPattern() {
        QuestionPatterns.clear();
        Random patternRateOfChange = new Random();
        Random startingNumber = new Random();
        Random r = new Random();
        int incorrect = r.nextInt(10 - 1) + 1;
        int incorrect2 = r.nextInt(20 - 5) + 10;
        int incorrect3 = r.nextInt(30 - 9) + 15;
        //Define rate of change for increase & decrease patterns
        rateOfChange = patternRateOfChange.nextInt(5 - 1) + 1;
        String rateOfChangePattern = String.valueOf(rateOfChange);
        //Define the first integer value to start pattern recognition
        firstNumber = startingNumber.nextInt(10 - 1) + 1;
        //TODO: Add Logic to determine what type of pattern should be displayed
        Random randPattern = new Random();
        patternType = randPattern.nextInt(3) + 1;
        List<String> patternAnswers = new Vector<>();
        switch(patternType) {
            case 1: //Increase
                secondNumber = firstNumber + rateOfChange;
                thirdNumber = secondNumber + rateOfChange;
                fourthNumber = thirdNumber + rateOfChange;
                correctAnswer = fourthNumber + rateOfChange;
                break;
            case 2: //Decrease
                firstNumber = firstNumber + 29;
                secondNumber = firstNumber - rateOfChange;
                thirdNumber = secondNumber - rateOfChange;
                fourthNumber = thirdNumber - rateOfChange;
                correctAnswer = fourthNumber - rateOfChange;
                break;
            case 3: //Looping
                firstNumber = firstNumber + 3;
                secondNumber = firstNumber + rateOfChange;
                thirdNumber = secondNumber + 3;
                fourthNumber = firstNumber;
                correctAnswer = secondNumber;
                break;
        }
        patternFirstNumber = String.valueOf(firstNumber);
        patternSecondNumber = String.valueOf(secondNumber);
        patternThirdNumber = String.valueOf(thirdNumber);
        patternFourthNumber = String.valueOf(fourthNumber);
        correctCheckBoxAnswer = String.valueOf(correctAnswer);
        incorrectCheckboxOne = String.valueOf(incorrect);
        incorrectCheckboxTwo = String.valueOf(incorrect2);
        incorrectCheckboxThree = String.valueOf(incorrect3);
        patternAnswers.add(correctCheckBoxAnswer);
        patternAnswers.add(incorrectCheckboxOne);
        patternAnswers.add(incorrectCheckboxTwo);
        patternAnswers.add(incorrectCheckboxThree);
        Collections.shuffle(patternAnswers);

        QuestionPatterns.add(new PatternQuestion(patternFirstNumber, patternSecondNumber, patternThirdNumber, patternFourthNumber, finalAnswer, patternAnswers.get(0), patternAnswers.get(1), patternAnswers.get(2), patternAnswers.get(3)));
        adapterPatterns.notifyDataSetChanged();

    }
}
