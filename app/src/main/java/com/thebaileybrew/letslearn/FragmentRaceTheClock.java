package com.thebaileybrew.letslearn;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.view.View.GONE;

public class FragmentRaceTheClock extends Fragment {
    public static final String TAG = "RaceTheClockFragment";

    RecyclerView recyclerView;
    RVAdapter adapter;
    private List<questions> Questions;
    TextView countdownDisplay;
    CountDownTimer raceTheClock;
    RelativeLayout cardViewLayout;
    public String counter;
    public String time;
    boolean startClock = false; // FALSE = NOT STARTED // TRUE = STARTED
    boolean countdownRunning = false;
    Button startGame;
    Button submitAnswer;
    Button startAgain;

    int firstDig;
    int secondDig;
    TextView firstNumberField;
    TextView mathFunctionSymbol;
    TextView secondNumberField;
    EditText submittedAnswerField;
    TextView correctAnswers;
    int numCorrect = 0;
    int numIncorrect = 0;

    public static FragmentRaceTheClock newInstance() {
        FragmentRaceTheClock fragment = new FragmentRaceTheClock();
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_race_the_clock, container, false);
        Questions = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_display);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        countdownDisplay = view.findViewById(R.id.stopwatch_display);
        recyclerView.setLayoutManager(layoutManager);
        //Countdown Clock initialization
        correctAnswers = view.findViewById(R.id.correct_total);
        startGame = view.findViewById(R.id.start_button_race);
        submitAnswer = view.findViewById(R.id.submit_answer_button_race);
        startAgain = view.findViewById(R.id.start_new_race);
        cardViewLayout = recyclerView.findViewById(R.id.card_border_changable);

        initializeRecyclerAdapter();
        //Starts the countdown and displays the recyclerView
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countdownRunning) {
                    //Do nothing
                } else {
                    startCountdown();
                    createQuestions();
                }
            }
        });

        //Submits the answer and moves to the next question
        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceQuestions();

            }
        });
        return view;
    }

    private void startCountdown() {
        raceTheClock = new CountDownTimer(120000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / (1000*60) %60);
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                time = String.format(Locale.US,"%01d:%02d",minutes, seconds);
                Log.i(time, "onTick: Current value of time");
                counter = String.valueOf(time);
                submitAnswer.setClickable(true);
                countdownDisplay.setText(counter);
                countdownRunning = true;

            }

            @Override
            public void onFinish() {
                countdownDisplay.setText("Time's up!");
                countdownRunning = false;
                submitAnswer.setClickable(false);
                Questions.clear();
                finishCountdown();
            }
        };
        raceTheClock.start();

    }

    private void finishCountdown() {
        startGame.setVisibility(GONE);
        correctAnswers.setText((String.valueOf(numCorrect) + " / " + (String.valueOf(numCorrect + numIncorrect))));
        startAgain.setVisibility(View.VISIBLE);
        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAgain.setVisibility(GONE);
                submitAnswer.setVisibility(View.VISIBLE);
                //Resets totals and starts timer again.
                numCorrect = 0;
                numIncorrect = 0;
            }
        });
        //Show total correct out of incorrect
    }

    private void replaceQuestions() {
        checkForCorrectResponse();
        correctAnswers.setText(String.valueOf(numCorrect));
        Questions.clear();
        submittedAnswerField.setText("");
        createQuestions();
    }

    private void checkForCorrectResponse() {
        firstNumberField = recyclerView.findViewById(R.id.first_digit);
        secondNumberField = recyclerView.findViewById(R.id.second_digit);
        mathFunctionSymbol = recyclerView.findViewById(R.id.math_function);
        submittedAnswerField = recyclerView.findViewById(R.id.answer_text_entry);

        String mathFunc = mathFunctionSymbol.getText().toString();
        String submittedAnswer = submittedAnswerField.getText().toString();
        String answerString = "";
        if (mathFunc.equals("-")) {
            int answer = secondDig - firstDig;
            answerString = String.valueOf(answer);
        } else {
            int answer = secondDig + firstDig;
            answerString = String.valueOf(answer);
        }
        if (answerString.equals(submittedAnswer)) {
            //Submitted answer is correct
            cardViewLayout = recyclerView.findViewById(R.id.card_border_changable);
            cardViewLayout.setBackgroundResource(R.drawable.card_background_color_correct);
            numCorrect = numCorrect + 1;
            Toast.makeText(getContext(),"That's Correct! The answer was " + answerString, Toast.LENGTH_SHORT).show();
        } else {
            //Submitted answer is incorrect
            cardViewLayout = recyclerView.findViewById(R.id.card_border_changable);
            cardViewLayout.setBackgroundResource(R.drawable.card_background_color_incorrect);
            Toast.makeText(getContext(),"Almost got it. The Correct answer was " + answerString, Toast.LENGTH_SHORT).show();
            numIncorrect = numIncorrect + 1;
        }

    }

    private void createQuestions() {
        // Creates random integer values for math functionality
        Random r = new Random();
        Random r2 = new Random();
        firstDig = r.nextInt(10 - 1) + 1;
        secondDig = r2.nextInt(10 - 1) + 1;
        //Converts random integer to string value
        String first = String.valueOf(firstDig);
        String second = String.valueOf(secondDig);
        if (secondDig > firstDig) {
            //Check if 2nd integer is larger than first and rotate the input and specify subtraction vs addition
            Questions.add(new questions(second,first,"-"));
        } else if (secondDig == firstDig) {
            //Check if integers are equal and specify addition
            Questions.add(new questions(first,second,"+"));
        } else {
            //Check if 1st integer is larger than second (all cases not matching if/if-else and specify addition
            Questions.add(new questions(first, second, "+"));
        }
        //Notifies the RecyclerView Adapter that dataset has changed (new question added)
        adapter.notifyDataSetChanged();
    }

    //This method creates the RecyclerView Adapter and sets the association with the data set
    private void initializeRecyclerAdapter() {
        adapter = new RVAdapter(Questions);
        recyclerView.setAdapter(adapter);
    }
}
