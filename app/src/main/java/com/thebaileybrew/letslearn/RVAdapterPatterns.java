package com.thebaileybrew.letslearn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

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
        holder.setSecondCheckView(currentQuestion.incorrectAnswerOne);
        holder.setThirdCheckView(currentQuestion.incorrectAnswerTwo);
        holder.setFourthCheckView(currentQuestion.incorrectAnswerThree);
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
        private CheckBox firstCheckView;
        private CheckBox secondCheckView;
        private CheckBox thirdCheckView;
        private CheckBox fourthCheckView;

        private CheckBox increasingView;
        private CheckBox decreasingView;
        private CheckBox loopingView;

        public ViewHolder(View itemView) {
            super(itemView);
            firstDigitView = itemView.findViewById(R.id.first_digit_pattern);
            secondDigitView = itemView.findViewById(R.id.second_digit_pattern);
            thirdDigitView = itemView.findViewById(R.id.third_digit_pattern);
            fourthDigitView = itemView.findViewById(R.id.fourth_digit_pattern);
            finalAnswerView = itemView.findViewById(R.id.last_digit_answer);
            firstCheckView = itemView.findViewById(R.id.checkbox_one);

            firstCheckView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        secondCheckView.setChecked(false);
                        thirdCheckView.setChecked(false);
                        fourthCheckView.setChecked(false);
                    }
                }
            });
            secondCheckView = itemView.findViewById(R.id.checkbox_two);

            secondCheckView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        firstCheckView.setChecked(false);
                        thirdCheckView.setChecked(false);
                        fourthCheckView.setChecked(false);
                    }
                }
            });
            thirdCheckView = itemView.findViewById(R.id.checkbox_three);
            thirdCheckView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        secondCheckView.setChecked(false);
                        firstCheckView.setChecked(false);
                        fourthCheckView.setChecked(false);
                    }
                }
            });
            fourthCheckView = itemView.findViewById(R.id.checkbox_four);

            fourthCheckView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        secondCheckView.setChecked(false);
                        thirdCheckView.setChecked(false);
                        firstCheckView.setChecked(false);
                    }
                }
            });
            increasingView = itemView.findViewById(R.id.checkbox_increase);

            increasingView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        decreasingView.setChecked(false);
                        loopingView.setChecked(false);
                    }
                }
            });
            decreasingView = itemView.findViewById(R.id.checkbox_decrease);

            decreasingView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        increasingView.setChecked(false);
                        loopingView.setChecked(false);
                    }
                }
            });
            loopingView = itemView.findViewById(R.id.checkbox_looping);

            loopingView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        increasingView.setChecked(false);
                        decreasingView.setChecked(false);
                    }
                }
            });
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
            firstCheckView.setChecked(false);
        }
        public void setSecondCheckView(String incorrectAnswerThree) {
            secondCheckView.setText(incorrectAnswerThree);
            secondCheckView.setChecked(false);
        }
        public void setThirdCheckView(String incorrectAnswerOne) {
            thirdCheckView.setText((incorrectAnswerOne));
            thirdCheckView.setChecked(false);
        }
        public void setFourthCheckView(String incorrectAnswerTwo) {
            fourthCheckView.setText(incorrectAnswerTwo);
            fourthCheckView.setChecked(false);
        }
        public void setIncreasingView(String increase) {
            increasingView.setText(increase);
            increasingView.setChecked(false);
        }
        public void setDecreasingView(String decrease) {
            decreasingView.setText(decrease);
            decreasingView.setChecked(false);
        }
        public void setLoopingView(String looping) {
            loopingView.setText(looping);
            loopingView.setChecked(false);
        }
    }



}
