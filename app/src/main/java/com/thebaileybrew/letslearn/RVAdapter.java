package com.thebaileybrew.letslearn;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MathFunction> {

    public static class MathFunction extends RecyclerView.ViewHolder {
        CardView cv;
        TextView firstDigitView;
        TextView mathFunctionView;
        TextView secondDigitView;
        EditText submitAnswerView;

        MathFunction(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardview);
            firstDigitView = itemView.findViewById(R.id.first_digit);
            mathFunctionView = itemView.findViewById(R.id.math_function);
            secondDigitView = itemView.findViewById(R.id.second_digit);
            submitAnswerView = itemView.findViewById(R.id.answer_text_entry);
        }
    }

    private List<questions> Questions;

    RVAdapter(List<questions> Questions) {this.Questions = Questions;}

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MathFunction onCreateViewHolder (ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,viewGroup,false);
        MathFunction mf = new MathFunction(v);
        return mf;
    }

    @Override
    public void onBindViewHolder(MathFunction mf, int i) {
        mf.firstDigitView.setText(Questions.get(i).firstDigit);
        mf.secondDigitView.setText(Questions.get(i).secondDigit);
        mf.mathFunctionView.setText(Questions.get(i).mathFunction);
    }

    @Override
    public int getItemCount() {return Questions.size();}
}
