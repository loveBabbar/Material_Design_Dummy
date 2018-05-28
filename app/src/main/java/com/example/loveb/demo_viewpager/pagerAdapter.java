package com.example.loveb.demo_viewpager;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.loveb.demo_viewpager.Chats;
import com.example.loveb.demo_viewpager.Contacts;
import com.example.loveb.demo_viewpager.Status;

/**
 * Created by loveb on 20-05-2018.
 */

public class pagerAdapter extends FragmentStatePagerAdapter{

    int mNumOfTabs;

    public pagerAdapter(android.support.v4.app.FragmentManager fm,int numberOfTabs) {
        super(fm);
        mNumOfTabs=numberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Chats();
            case 1:
                return new Status();
            case 2:
                return new Contacts();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
