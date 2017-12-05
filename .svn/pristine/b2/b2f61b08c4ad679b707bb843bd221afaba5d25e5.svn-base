package com.babuwyt.consignor.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.babuwyt.consignor.R;

import java.util.ArrayList;


/**
 * Created by lenovo on 2017/8/7.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<Fragment> fragments;
    public MainPagerAdapter(FragmentManager fm , Context c,ArrayList<Fragment> fragments) {
        super(fm);
        context=c;
        this.fragments=fragments;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String str="";
        switch (position){
            case 0:
                str=context.getString(R.string.order_release);
                break;
            case 1:
                str=context.getString(R.string.order_genzong);
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
        return fragments.get(position);
    }

//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
}
