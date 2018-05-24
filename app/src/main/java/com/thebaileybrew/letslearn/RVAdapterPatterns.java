package com.thebaileybrew.letslearn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

public class RVAdapterPatterns extends RecyclerView.Adapter<RVAdapterPatterns.ViewHolder> {

    private static String TAG = "RadioPatternAdapter";

    private List<PatternQuestion> questionsPattern;

    public RVAdapterPatterns(List<PatternQuestion> QuestionPatterns) {
        this.questionsPattern = QuestionPatterns;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_patterns,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //define holder.setDefinitions
        PatternQuestion currentQuestion = questionsPattern.get(position);
        holder.setFirstDigitView(currentQuestion.firstNumber);
        holder.setSecondDigitView(currentQuestion.secondNumber);
        holder.setThirdDigitView(currentQuestion.thirdNumber);
        holder.setFourthDigitView(currentQuestion.fourthNumber);
        holder.setAnswerDigitView(currentQuestion.finalAnswer);
        holder.setFirstCheckView(currentQuestion.correctAnswerOne);
        holder.setSecondCheckView(currentQuestion.correctAnswerTwo);
        holder.setThirdCheckView(currentQuestion.incorrectAnswerOne);
        holder.setFourthCheckView(currentQuestion.incorrectAnswerTwo);
        holder.setIncreasingView(currentQuestion.increase);
        holder.setDecreasingView(currentQuestion.decrease);
        holder.setLoopingView(currentQuestion.looping);

    }

    @Override
    public int getItemCount() {
        return questionsPattern.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView firstDigitView;
        private TextView secondDigitView;
        private TextView thirdDigitView;
        private TextView fourthDigitView;
        private TextView finalAnswerView;
        private TextView firstCheckView;
        private TextView secondCheckView;
        private TextView thirdCheckView;
        private TextView fourthCheckView;

        private TextView increasingView;
        private TextView decreasingView;
        private TextView loopingView;

        public ViewHolder(View itemView) {
            super(itemView);
            firstDigitView = itemView.findViewById(R.id.first_digit_pattern);
            secondDigitView = itemView.findViewById(R.id.second_digit_pattern);
            thirdDigitView = itemView.findViewById(R.id.third_digit_pattern);
            fourthDigitView = itemView.findViewById(R.id.fourth_digit_pattern);
            finalAnswerView = itemView.findViewById(R.id.last_digit_answer);
            firstCheckView = itemView.findViewById(R.id.checkbox_one);
            secondCheckView = itemView.findViewById(R.id.checkbox_two);
            thirdCheckView = itemView.findViewById(R.id.checkbox_three);
            fourthCheckView = itemView.findViewById(R.id.checkbox_four);

            increasingView = itemView.findViewById(R.id.checkbox_increase);
            decreasingView = itemView.findViewById(R.id.checkbox_decrease);
            loopingView = itemView.findViewById(R.id.checkbox_looping);
            //declare findByIdReferences for recycler xml
        }

        //Sets the text for the appropriate textviews
        public void setFirstDigitView(String firstNumber) {
            firstDigitView.setText(firstNumber);
        }
        public void setSecondDigitView(String secondNumber) {
            secondDigitView.setText(secondNumber);
        }
        public void setThirdDigitView(String thirdNumber) {
            thirdDigitView.setText(thirdNumber);
        }
        public void setFourthDigitView(String fourthNumber) {
            fourthDigitView.setText(fourthNumber);
        }
        public void setAnswerDigitView(String finalAnswer) {
            finalAnswerView.setText(finalAnswer);
        }
        public void setFirstCheckView(String correctAnswerOne) {
            firstCheckView.setText(correctAnswerOne);
        }
        public void setSecondCheckView(String correctAnswerTwo) {
            secondCheckView.setText(correctAnswerTwo);
        }
        public void setThirdCheckView(String incorrectAnswerOne) {
            thirdCheckView.setText((incorrectAnswerOne));
        }
        public void setFourthCheckView(String incorrectAnswerTwo) {
            fourthCheckView.setText(incorrectAnswerTwo);
        }
        public void setIncreasingView(String increase) {
            increasingView.setText(increase);
        }
        public void setDecreasingView(String decrease) {
            decreasingView.setText(decrease);
        }
        public void setLoopingView(String looping) {
            loopingView.setText(looping);
        }
    }



}
