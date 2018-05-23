package com.thebaileybrew.letslearn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class FragmentLevelChallenge extends Fragment{

    public static final String TAG = "LevelChallengeFragment";

    RecyclerView recyclerView;
    RVAdapterMulti adapterMulti;
    private List<MultiQuestion> QuestionsMulti;
    private List<Boolean> QuestionsAnswered;

    int firstDig;
    int secondDig;
    int correctAnswer;
    int incorrectOne;
    int incorrectTwo;
    int incorrectThree;
    int thisPosition;
    int questionsToFillRecycler = 10;
    int countOfCorrect;

    //levelChallenge defines the current challenge level
    int levelChallenge;
    int maxBound = 10;
    int minBound = 1;
    int adjustBound = 1;
    RadioGroup radioGroupRecycler;



    public static FragmentLevelChallenge newInstance() {
        FragmentLevelChallenge fragment = new FragmentLevelChallenge();
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_challenge, container, false);

        levelChallenge = 1;
        radioGroupRecycler = view.findViewById(R.id.radio_group_multi);
        QuestionsAnswered = new ArrayList<>();
        QuestionsMulti = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_display_multi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapterMulti = new RVAdapterMulti(QuestionsMulti, communication);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0,10);
        recyclerView.setAdapter(adapterMulti);
        recyclerView.setLayoutManager(layoutManager);
        Button levelsSubmit = view.findViewById(R.id.submit_answer_button_levels);
        levelsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countOfCorrect = Collections.frequency(QuestionsAnswered,true);
                Log.i(String.valueOf(countOfCorrect), "onClick: ");
                if (countOfCorrect == questionsToFillRecycler) {
                    Toast.makeText(v.getContext(),"Congratulations! You answered them all correct. Lets try harder ones!", Toast.LENGTH_LONG).show();
                    levelChallenge = levelChallenge + 1;
                    createQuestionBatch();
                } else if (countOfCorrect >= 3 && countOfCorrect <= (questionsToFillRecycler - 3)) {
                    Toast.makeText(v.getContext(), "You're almost there. Try again!", Toast.LENGTH_LONG).show();
                    createQuestionBatch();
                } else {
                    Toast.makeText(v.getContext(), "Let's try that again. You got " + String.valueOf(countOfCorrect) + " correct.", Toast.LENGTH_LONG).show();
                    createQuestionBatch();
                }
            }
        });

        createQuestionBatch();
        return view;
    }

    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void respond(boolean answeredCorrectly) {
            QuestionsAnswered.add(answeredCorrectly);
        }
    };

    private void createQuestionBatch() {
        //Resets the value for count of correct
        QuestionsAnswered.clear();
        countOfCorrect = 0;
        QuestionsMulti.clear();
        //Checks the current level to determine the boundaries for difficulty of random integers
        switch (levelChallenge) {
            case 1: //If level 1 (bounds 10,1,1)
                maxBound = 10;
                minBound = 1;
                adjustBound = 1;
                break;
            case 2: //If level 2 (bounds 20,1,4)
                maxBound = 20;
                minBound = 1;
                adjustBound = 4;
                break;
            case 3: //If level 3 (bounds 25,10,5)
                maxBound = 25;
                minBound = 10;
                adjustBound = 5;
                break;
            case 4: //If level 4 (bounds 30,10,7)
                maxBound = 30;
                minBound = 10;
                adjustBound = 7;
                break;
            case 5: //If level 5 (bounds 50,10,9)
                maxBound = 50;
                minBound = 10;
                adjustBound = 9;
                break;
            case 6: //If level 6 (bounds 75,10,15)
                maxBound = 75;
                minBound = 10;
                adjustBound = 15;
                break;
            case 7: //If level 7 (bounds 100,10,18)
                maxBound = 100;
                minBound = 10;
                adjustBound = 18;
                break;
            //If level 8 (bounds random integers?)
        }

        //Creates the random integers for math functions
        for (int i = 0; i < questionsToFillRecycler; i++) {
            final Random r = new Random();
            Random position = new Random();
            thisPosition = position.nextInt(4 - 1) + 1;
            //Creates the array list for incorrect answers
            firstDig = r.nextInt(maxBound - minBound) + adjustBound;
            secondDig = r.nextInt(maxBound - minBound) + adjustBound;
            if (firstDig < secondDig) {
                correctAnswer = secondDig - firstDig;
                incorrectOne = secondDig - firstDig - 1;
                incorrectTwo = secondDig - firstDig + 2;
                incorrectThree = (secondDig + firstDig) - 2;
            } else {
                correctAnswer = firstDig + secondDig;
                incorrectOne = secondDig + firstDig - 2;
                incorrectTwo = secondDig + firstDig + 1;
                incorrectThree = (firstDig - secondDig) + 3;
            }

            //Convert integer to string values
            String firstNumber = String.valueOf(firstDig);
            String secondNumber = String.valueOf(secondDig);
            String firstIncorrect = String.valueOf(incorrectOne);
            String secondIncorrect = String.valueOf(incorrectTwo);
            String thirdIncorrect = String.valueOf(incorrectThree);
            String correct = String.valueOf(correctAnswer);
            List<String> answers = new Vector<>();
            answers.add(correct);
            answers.add(firstIncorrect);
            answers.add(secondIncorrect);
            answers.add(thirdIncorrect);
            Collections.shuffle(answers);
            if (firstDig < secondDig) {
                //Checks if 1st integer is less than 2nd integer
                QuestionsMulti.add(new MultiQuestion(secondNumber, firstNumber, answers.get(0), "-", answers.get(1), answers.get(2), answers.get(3)));
            } else if (firstDig > secondDig) {
                //Checks if 1st integer is greater than 2nd integer
                QuestionsMulti.add(new MultiQuestion(secondNumber, firstNumber, answers.get(0), "+", answers.get(1), answers.get(2), answers.get(3)));
            } else {
                //Checks if 1st integer is greater than 2nd integer
                QuestionsMulti.add(new MultiQuestion(secondNumber, firstNumber, answers.get(0), "+", answers.get(1), answers.get(2), answers.get(3)));
            }
            //Updates the RecyclerView Adapter
            adapterMulti.notifyDataSetChanged();
        }
    }



}
