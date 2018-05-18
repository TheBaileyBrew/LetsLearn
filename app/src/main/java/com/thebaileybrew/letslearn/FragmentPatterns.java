package com.thebaileybrew.letslearn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPatterns extends Fragment {

    public static final String TAG = "PatternsFragment";

    public static FragmentPatterns newInstance() {
        FragmentPatterns fragment = new FragmentPatterns();
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pattern_recognition, container, false);

        return view;
    }
}
