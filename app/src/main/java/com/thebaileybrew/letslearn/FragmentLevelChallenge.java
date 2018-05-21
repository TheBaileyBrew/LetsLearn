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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    //levelChallenge defines the current challenge level
    int levelChallenge = 1;
    int maxBound;
    int minBound;
    int adjustBound;
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

        //TODO: Create 2nd RecyclerView
        //RecyclerView will display 10 cards

        QuestionsMulti = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_display_multi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        radioGroupRecycler = recyclerView.findViewById(R.id.radio_group_multi);

        initializeRecyclerAdapter();
        createQuestionBatch();

        return view;
    }

    private void createQuestionBatch() {
        //Integer values to define the minimum, maximum and adjustment for random integers


        //Checks the current level to determine the boundaries for difficulty of random integers
        switch (levelChallenge) {
            case 1: //If level 1 (bounds 10,1,1)
                maxBound = 10;
                minBound = 1;
                adjustBound = 1;
                break;
            case 2: //If level 2 (bounds 20,1,1)
                maxBound = 20;
                minBound = 1;
                adjustBound = 1;
                break;
            case 3: //If level 3 (bounds 25,10,5)
                maxBound = 25;
                minBound = 10;
                adjustBound = 5;
                break;
            case 4: //If level 4 (bounds 30,10,5)
                maxBound = 30;
                minBound = 10;
                adjustBound = 5;
                break;
            case 5: //If level 5 (bounds 50,10,1)
                maxBound = 50;
                minBound = 10;
                adjustBound = 1;
                break;
            case 6: //If level 6 (bounds 75,10,5)
                maxBound = 75;
                minBound = 10;
                adjustBound = 5;
                break;
            case 7: //If level 7 (bounds 100,10,10)
                maxBound = 100;
                minBound = 10;
                adjustBound = 10;
                break;

            //If level 8 (bounds random integers?)
        }

        //Creates the random integers for math functions
        int questionsToFillRecycler = 10;
        for (int i = 0; i < questionsToFillRecycler; i++) {
            Random r = new Random();
            Random position = new Random();
            thisPosition = position.nextInt(4 - 1) + 1;
            //Creates the array list for incorrect answers
            ArrayList<Integer> multiOptions = new ArrayList<>();
            firstDig = r.nextInt(maxBound - minBound) + adjustBound;
            secondDig = r.nextInt(maxBound - minBound) + adjustBound;
            correctAnswer = secondDig - firstDig;
            //Defines incorrect random integers
            while (multiOptions.size() < 4) {
                int inCorrect = r.nextInt(maxBound - minBound) + adjustBound;
                //Checks to verify that correct answer & current list of incorrects don't duplicate
                if (!multiOptions.contains(inCorrect) && !multiOptions.contains(correctAnswer)) {
                    multiOptions.add(inCorrect);
                }
            }
            //Define integers for incorrect answers
            incorrectOne = multiOptions.get(0); incorrectTwo = multiOptions.get(1); incorrectThree = multiOptions.get(2);
            //Convert integer to string values
            String firstNumber = String.valueOf(firstDig);
            String secondNumber = String.valueOf(secondDig);
            String firstIncorrect = String.valueOf(incorrectOne);
            String secondIncorrect = String.valueOf(incorrectTwo);
            String thirdIncorrect = String.valueOf(incorrectThree);
            String correct = String.valueOf(correctAnswer);
            if (firstDig < secondDig) {
                //Checks if 1st integer is less than 2nd integer
                switch (thisPosition) {
                    case 1: //A
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, correct, "-", firstIncorrect, secondIncorrect, thirdIncorrect));
                        break;
                    case 2: //C
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, firstIncorrect, "-", thirdIncorrect, correct, secondIncorrect));
                        break;
                    case 3: //D
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, thirdIncorrect, "-", firstIncorrect, secondIncorrect, correct));
                        break;
                    case 4: //B
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, secondIncorrect, "-", correct, thirdIncorrect, firstIncorrect));
                        break;
                }
            } else if (firstDig > secondDig) {
                //Checks if 1st integer is greater than 2nd integer
                switch (thisPosition) {
                    case 1: //A
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, correct, "+", firstIncorrect, secondIncorrect, thirdIncorrect));
                        break;
                    case 2: //C
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, firstIncorrect, "+", thirdIncorrect, correct, secondIncorrect));
                        break;
                    case 3: //D
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, thirdIncorrect, "+", firstIncorrect, secondIncorrect, correct));
                        break;
                    case 4: //B
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, secondIncorrect, "+", correct, firstIncorrect, thirdIncorrect));
                        break;
                }
            } else {
                //Checks if 1st integer is greater than 2nd integer
                switch (thisPosition) {
                    case 1: //A
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, correct, "+", firstIncorrect, secondIncorrect, thirdIncorrect));
                        break;
                    case 2: //C
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, firstIncorrect, "+", thirdIncorrect, correct, secondIncorrect));
                        break;
                    case 3: //D
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, thirdIncorrect, "+", firstIncorrect, secondIncorrect, correct));
                        break;
                    case 4: //B
                        QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, secondIncorrect, "+", correct, firstIncorrect, thirdIncorrect));
                        break;
                }
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
