package com.babuwyt.documentary.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.ui.fragment.MainPagerFragment;

/**
 * Created by lenovo on 2017/8/7.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    public MainPagerAdapter(FragmentManager fm ,Context c) {
        super(fm);
        context=c;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String str="";
        switch (position){
            case 0:
                str=context.getString(R.string.daishenhe);
                break;
            case 1:
                str=context.getString(R.string.jinxingzhong);
                break;
        }
        return str;
    }

//    @Override
//    public Object instantiateItem(View container, int position) {
//
//
//        ((ViewGroup) container).addView();
//        return ;
//    }

//    @Override
//    public void destroyItem(View container, int position, Object object) {
//        ((ViewPager) container).removeView((View) object);
//
//    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        int type=1;
        switch (position){
            case 0:
                type=0;
                break;
            case 1:
                type=1;
                break;
        }
        return MainPagerFragment.newInstance(type);
    }

//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
}
