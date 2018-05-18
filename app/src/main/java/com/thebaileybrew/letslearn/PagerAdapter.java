package com.thebaileybrew.letslearn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 5;



    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return FragmentRaceTheClock.newInstance();
            case 1:
                return FragmentLevelChallenge.newInstance();
            case 2:
                return FragmentHomePageSelection.newInstance();
            case 3:
                return FragmentPatterns.newInstance();
            case 4:
                return FragmentViewScores.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof Fragment) {
            return POSITION_UNCHANGED;
        } else {
            return POSITION_NONE;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}

