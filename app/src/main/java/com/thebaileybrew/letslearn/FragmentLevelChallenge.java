package com.thebaileybrew.letslearn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentLevelChallenge extends Fragment {

    public static final String TAG = "LevelChallengeFragment";

    RecyclerView recyclerView;
    RVAdapterMulti adapterMulti;
    private List<multiquestion> QuestionsMulti;

    int firstDig;
    int secondDig;
    int incorrectOne;
    int incorrectTwo;
    int incorrectThree;
    int thisPosition;



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

        initializeRecyclerAdapter();
        createQuestionBatch();

        return view;
    }

    private void createQuestionBatch() {
        //Creates the random integers for math functions
        int level = 10;
        Random r = new Random();
        Random incorrect = new Random();
        Random position = new Random();

        firstDig = r.nextInt(10-1) + 1;
        secondDig = r.nextInt(10-1) + 1;
        incorrectOne = incorrect.nextInt(10-1) + 1;
        incorrectTwo = incorrect.nextInt(10-1) + 1;
        incorrectThree = incorrect.nextInt(10-1) + 1;
        thisPosition = position.nextInt(4-1) + 1;
        //Convert integer to string values
        String firstNumber = String.valueOf(firstDig);
        String secondNumber = String.valueOf(secondDig);
        String firstIncorrect = String.valueOf(incorrectOne);
        String secondIncorrect = String.valueOf(incorrectTwo);
        String thirdIncorrect = String.valueOf(incorrectThree);
        if (firstDig < secondDig) {
            String correct = String.valueOf(secondDig-firstDig);
            //Checks if 1st integer is less than 2ns integer
            switch (thisPosition) {
                case 1: //A
                    QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, correct,"-", firstIncorrect, secondIncorrect, thirdIncorrect));
                    break;
                case 2: //C
                    QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, firstIncorrect,"-", correct, secondIncorrect, thirdIncorrect));
                    break;
                case 3: //D
                    QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, thirdIncorrect,"-", firstIncorrect, secondIncorrect, correct));
                    break;
                case 4: //B
                    QuestionsMulti.add(new multiquestion(secondNumber, firstNumber, secondIncorrect,"-", correct, firstIncorrect, thirdIncorrect));
                    break;

                    //Need remaining logic and organization 
            }
        }

    }

    private void initializeRecyclerAdapter() {
        adapterMulti = new RVAdapterMulti(QuestionsMulti);
        recyclerView.setAdapter(adapterMulti);
    }
}
