package com.thebaileybrew.letslearn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentViewScores extends Fragment {

    public static final String TAG = "ScoringFragment";

    public static FragmentViewScores newInstance() {
        FragmentViewScores fragment = new FragmentViewScores();
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_scores, container, false);

        return view;
    }
}
