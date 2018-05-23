package com.thebaileybrew.letslearn;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;


public class RVAdapterMulti extends RecyclerView.Adapter<RVAdapterMulti.ViewHolder> {


    private static String TAG = "RadioMultiAdapter";
    String blankAnswerField = " ? ";
    private List<MultiQuestion> questionsMulti;
    FragmentCommunication mCommunicator;

    private AdapterView.OnItemSelectedListener onSelect;

    public RVAdapterMulti(List<MultiQuestion> QuestionMulti, FragmentCommunication communication) {
        this.questionsMulti = QuestionMulti;
        mCommunicator = communication;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_multi, parent, false);
        return new ViewHolder(view, mCommunicator);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MultiQuestion currentQuestion = questionsMulti.get(position);
        holder.setFirstDigit(currentQuestion.firstNumber);
        holder.setSecondDigit(currentQuestion.secondNumber);
        holder.setMathFunction(currentQuestion.mathFunc);
        holder.setOptions(currentQuestion, position);
        holder.setMathAnswerView(currentQuestion.blankAnswer);

    }


    @Override
    public int getItemCount() {
        if (questionsMulti == null) {
            return 0;
        } else {
            return questionsMulti.size();
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayoutContainer;
        private TextView firstDigitView;
        private TextView secondDigitView;
        private TextView mathFunctionView;
        private TextView mathAnswerView;
        private RadioGroup radioGroupOptions;
        private RadioButton radioButtonOne, radioButtonTwo;
        private RadioButton radioButtonThree, radioButtonFour;
        FragmentCommunication mCommunication;

        public ViewHolder(View itemView, FragmentCommunication Communicator) {
            super(itemView);
            firstDigitView = itemView.findViewById(R.id.first_digit);
            secondDigitView = itemView.findViewById(R.id.second_digit);
            mathFunctionView = itemView.findViewById(R.id.math_function);
            mathAnswerView = itemView.findViewById(R.id.math_answer);
            radioGroupOptions = itemView.findViewById(R.id.radio_group_multi);
            radioButtonOne = itemView.findViewById(R.id.radio_button_one);
            radioButtonTwo = itemView.findViewById(R.id.radio_button_two);
            radioButtonThree = itemView.findViewById(R.id.radio_button_three);
            radioButtonFour = itemView.findViewById(R.id.radio_button_four);
            mCommunication = Communicator;

        }

        public void setFirstDigit(String firstDigit) {
            firstDigitView.setText(firstDigit);

        }
        public void setSecondDigit(String secondDigit) {
            secondDigitView.setText(secondDigit);

        }
        public void setMathFunction(String mathFunction) {
            mathFunctionView.setText(mathFunction);
        }

        public void setMathAnswerView(String blankAnswer) {
            mathAnswerView.setText(blankAnswer);
            mathAnswerView.setTextColor(radioGroupOptions.getResources().getColor(R.color.colorPrimary));
        }



        public void setOptions(final MultiQuestion question, int position) {
            Log.i(String.valueOf(mathAnswerView.getText()), "setOptions: ");
            if (!(mathAnswerView.getText().equals(" ? "))) {
                mathAnswerView.setText(" ? ");
            } else {
                mathAnswerView.setText(" ? ");
            }

            radioGroupOptions.setTag(position);
            radioButtonOne.setText(question.correct);
            radioButtonTwo.setText(question.incorrectOne);
            radioButtonThree.setText(question.incorrectTwo);
            radioButtonFour.setText(question.incorrectThree);

            if (question.mathFunc.equals("+")) {
                question.correctSolution = Integer.parseInt(question.firstNumber) + Integer.parseInt(question.secondNumber);
            } else {
                question.correctSolution = Integer.parseInt(question.firstNumber) - Integer.parseInt(question.secondNumber);
            }

            if(question.isAnswered) {
                radioGroupOptions.check(question.checkedId);
            } else {
                radioGroupOptions.check(-1);
            }
            radioGroupOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int pos = (int) group.getTag();
                    MultiQuestion que = questionsMulti.get(pos);
                    que.isAnswered = true;
                    que.checkedId = checkedId;
                    mathAnswerView.setText(String.valueOf(question.correctSolution));
                    String radioOneText = String.valueOf(radioButtonOne.getText());
                    String radioTwoText = String.valueOf(radioButtonTwo.getText());
                    String radioThreeText = String.valueOf(radioButtonThree.getText());
                    String radioFourText = String.valueOf(radioButtonFour.getText());

                    switch (que.checkedId) {
                        case R.id.radio_button_one:
                            if(radioOneText.equals(String.valueOf(question.correctSolution))) {
                                mathAnswerView.setTextColor(group.getResources().getColor(R.color.colorCorrect));
                                question.answeredCorrectly = true;
                                mCommunication.respond(true);
                            } else {
                                mathAnswerView.setTextColor(group.getResources().getColor(R.color.colorIncorrect));
                                mCommunication.respond(false);
                            }
                            break;
                        case R.id.radio_button_two:
                            if(radioTwoText.equals(String.valueOf(question.correctSolution))) {
                                mathAnswerView.setTextColor(group.getResources().getColor(R.color.colorCorrect));
                                question.answeredCorrectly = true;
                                mCommunication.respond(true);
                            } else {
                                mathAnswerView.setTextColor(group.getResources().getColor(R.color.colorIncorrect));
                                mCommunication.respond(false);
                            }
                            break;
                        case R.id.radio_button_three:
                            if(radioThreeText.equals(String.valueOf(question.correctSolution))) {
                                mathAnswerView.setTextColor(group.getResources().getColor(R.color.colorCorrect));
                                question.answeredCorrectly = true;
                                mCommunication.respond(true);
                            } else {
                                mathAnswerView.setTextColor(group.getResources().getColor(R.color.colorIncorrect));
                                mCommunication.respond(false);
                            }
                            break;
                        case R.id.radio_button_four:
                            if(radioFourText.equals(String.valueOf(question.correctSolution))) {
                                mathAnswerView.setTextColor(group.getResources().getColor(R.color.colorCorrect));
                                question.answeredCorrectly = true;
                                mCommunication.respond(true);
                            } else {
                                mathAnswerView.setTextColor(group.getResources().getColor(R.color.colorIncorrect));
                                mCommunication.respond(false);
                            }
                            break;
                    }
                }
            });
        }
    }
}
