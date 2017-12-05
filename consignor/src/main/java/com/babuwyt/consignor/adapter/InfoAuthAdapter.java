package com.babuwyt.consignor.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/7.
 */

public class InfoAuthAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<Fragment> mList;
    public InfoAuthAdapter(FragmentManager fm , Context c,ArrayList<Fragment> list) {
        super(fm);
        context=c;
        mList=list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }
}
