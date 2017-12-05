package com.babuwyt.documentary.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/14.
 */

public class LookBigPicAdapter extends PagerAdapter {
    private ArrayList<ImageView> mList;
    public LookBigPicAdapter(ArrayList<ImageView> strings){
        if (strings!=null){
            mList=strings;
        }else {
            mList=new ArrayList<ImageView>();
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);

    }
}
