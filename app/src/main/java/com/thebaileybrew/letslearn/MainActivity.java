package com.thebaileybrew.letslearn;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Vector;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    BottomNavigation mBottomNavigation;
    private PagerAdapter mPagerAdapter;
    FloatingActionButton fab;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigation = findViewById(R.id.bottomNavigation);
        fab = findViewById(R.id.floating_action_button);
        mBottomNavigation.setSelectedIndex(2,true);
        final ViewPager pager = findViewById(R.id.viewPager);
        mPagerAdapter = new com.thebaileybrew.letslearn.PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(mPagerAdapter);
        pager.setCurrentItem(2,true);

        mBottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(int i, int i1, boolean b) {
                switch (i1) {
                    case 0:
                        pager.setCurrentItem(0,true);
                        break;
                    case 1:
                        pager.setCurrentItem(1, true);
                        break;
                    case 2:
                        pager.setCurrentItem(2,true);
                        break;
                    case 3:
                        pager.setCurrentItem(3,true);
                        break;
                    case 4:
                        pager.setCurrentItem(4,true);
                        break;
                }
            }

            @Override
            public void onMenuItemReselect(int i, int i1, boolean b) {

            }
        });

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
