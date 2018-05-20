package com.thebaileybrew.letslearn;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdapterMulti extends RecyclerView.Adapter<RVAdapterMulti.MathMultiFunction> {

    private RadioButton lastCheckedRB = null;

    public static class MathMultiFunction extends RecyclerView.ViewHolder {
        CardView cvMulti;
        TextView firstDigitView;
        TextView mathFunctionView;
        TextView secondDigitView;
        RadioGroup multiRadioGroup;
        RadioButton radioBone;
        RadioButton radioBtwo;
        RadioButton radioBthree;
        RadioButton radioBfour;

        MathMultiFunction(View itemView) {
            super(itemView);
            cvMulti = itemView.findViewById(R.id.cardview);
            firstDigitView = itemView.findViewById(R.id.first_digit);
            mathFunctionView = itemView.findViewById(R.id.math_function);
            secondDigitView = itemView.findViewById(R.id.second_digit);
            multiRadioGroup = itemView.findViewById(R.id.radio_group_multi);
            radioBone = itemView.findViewById(R.id.radio_button_one);
            radioBtwo = itemView.findViewById(R.id.radio_button_two);
            radioBthree = itemView.findViewById(R.id.radio_button_three);
            radioBfour = itemView.findViewById(R.id.radio_button_four);
        }
    }

    private List<multiquestion> QuestionsMulti;

    RVAdapterMulti(List<multiquestion> QuestionsMulti) {this.QuestionsMulti = QuestionsMulti;}

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MathMultiFunction onCreateViewHolder (ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_multi,viewGroup,false);
        MathMultiFunction mmf = new MathMultiFunction(v);
        return mmf;
    }

    @Override
    public void onBindViewHolder(MathMultiFunction mmf, int i) {
        mmf.firstDigitView.setText(QuestionsMulti.get(i).firstNumber);
        mmf.secondDigitView.setText(QuestionsMulti.get(i).secondNumber);
        mmf.mathFunctionView.setText(QuestionsMulti.get(i).mathFunc);
        mmf.multiRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // Change logic here to validate correct answers.
                // If checked ID.getText = correctAnswer
                // Then change highlight color to green
                // If checked ID.getText = inCorrectAnswer
                // Then chenge highlight color to red
                // For all instances make remaining radiobuttons uncheckable.


                RadioButton checked_rb = (RadioButton) group.findViewById(checkedId);
                if (lastCheckedRB != null) {
                    lastCheckedRB.setChecked(false);
                }
                //store the clicked radiobutton
                lastCheckedRB = checked_rb;

            }
        });
        mmf.radioBone.setText(QuestionsMulti.get(i).correct);
        mmf.radioBtwo.setText(QuestionsMulti.get(i).incorrectOne);
        mmf.radioBthree.setText(QuestionsMulti.get(i).incorrectTwo);
        mmf.radioBfour.setText(QuestionsMulti.get(i).incorrectThree);
    }

    @Override
    public int getItemCount() {return QuestionsMulti.size();}
}
