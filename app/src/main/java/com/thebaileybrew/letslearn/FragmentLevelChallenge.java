package com.thebaileybrew.letslearn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class FragmentLevelChallenge extends Fragment{

    public static final String TAG = "LevelChallengeFragment";

    RecyclerView recyclerView;
    RVAdapterMulti adapterMulti;
    private List<multiquestion> QuestionsMulti;

    int firstDig;
    int secondDig;
    int correctAnswer;
    int incorrectOne;
    int incorrectTwo;
    int incorrectThree;
    int thisPosition;

    int selectedAnswers;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_challenge, container, false);

        levelChallenge = 1;

        QuestionsMulti = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_display_multi);
        radioGroupRecycler = view.findViewById(R.id.radio_group_multi);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int questions = adapterMulti.getItemCount();

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        TextView levelsText = view.findViewById(R.id.level_challenge);
        levelsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initializeRecyclerAdapter();
        createQuestionBatch();
        return view;
    }

    private void createQuestionBatch() {
        //Integer values to define the minimum, maximum and adjustment for random integers
        if (selectedAnswers == 5) {
            levelChallenge++;
        }

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
        int questionsToFillRecycler = 5;
        for (int i = 0; i < questionsToFillRecycler; i++) {
            final Random r = new Random();
            Random position = new Random();
            thisPosition = position.nextInt(4 - 1) + 1;
            //Creates the array list for incorrect answers
            firstDig = r.nextInt(maxBound - minBound) + adjustBound;
            secondDig = r.nextInt(maxBound - minBound) + adjustBound;
            if (firstDig < secondDig) {
                correctAnswer = secondDig - firstDig;
            } else {
                correctAnswer = firstDig + secondDig;
            }
            //Defines incorrect random integers
            ArrayList<Integer> multiOptions = new ArrayList<>();
            while (multiOptions.size() < 3) {
                int inCorrect = r.nextInt(maxBound - minBound) + 1;
                if (!multiOptions.contains(correctAnswer)) {
                    multiOptions.add(inCorrect);
                }
            }
            incorrectOne = r.nextInt(maxBound - minBound) + 3;
            incorrectTwo = multiOptions.get(1);
            incorrectThree = multiOptions.get(2);
            //Convert integer to string values
            String firstNumber = String.valueOf(firstDig);
            String secondNumber = String.valueOf(secondDig);
            String firstIncorrect = String.valueOf(incorrectOne);
            String secondIncorrect = String.valueOf(incorrectTwo);
            String thirdIncorrect = String.valueOf(incorrectThree);
            String correct = String.valueOf(correctAnswer);
            List<String> answers = new Vector<>();
            answers.add(firstIncorrect);
            answers.add(correct);
            answers.add(secondIncorrect);
            answers.add(thirdIncorrect);
            Collections.shuffle(answers);
            if (firstDig < secondDig) {
                //Checks if 1st integer is less than 2nd integer
                QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, answers.get(0), "-", answers.get(1), answers.get(2), answers.get(3)));
            } else if (firstDig > secondDig) {
                //Checks if 1st integer is greater than 2nd integer
                QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, answers.get(0), "+", answers.get(1), answers.get(2), answers.get(3)));
            } else {
                //Checks if 1st integer is greater than 2nd integer
                QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, answers.get(0), "+", answers.get(1), answers.get(2), answers.get(3)));
            }
            //Updates the RecyclerView Adapter
            adapterMulti.notifyDataSetChanged();
        }
    }

    private void initializeRecyclerAdapter() {
        adapterMulti = new RVAdapterMulti(QuestionsMulti);
        recyclerView.setAdapter(adapterMulti);
    }


}
