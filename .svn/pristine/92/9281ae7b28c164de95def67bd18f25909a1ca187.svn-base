package com.babuwyt.consignor.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.adapter.MainPagerAdapter;
import com.babuwyt.consignor.base.BaseFragment;
import com.babuwyt.consignor.finals.DataSynEvent;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by lenovo on 2017/9/10.
 */
@ContentView(R.layout.fragment_main)
public class MainFragment extends BaseFragment {
    @ViewInject(R.id.tabLayout)
    TabLayout tabLayout;
    @ViewInject(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private ReleaseOrderFragment releaseOrderFragment;
    private GenzongOrderFragment genzongOrderFragment;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTabLayout();
    }

    /**
     * 设置viewpager 与 tablayout
     */
    public void initTabLayout() {




        fragments=new ArrayList<Fragment>();
        releaseOrderFragment=ReleaseOrderFragment.newInstance(View.GONE);
        genzongOrderFragment=GenzongOrderFragment.newInstance(View.GONE);
        fragments.add(releaseOrderFragment);
        fragments.add(genzongOrderFragment);
        tabLayout.setTabMode(TabLayout.GRAVITY_CENTER);
        viewPager.setAdapter(new MainPagerAdapter(getChildFragmentManager(), getActivity(),fragments));
        //设置两者同步
        tabLayout.setupWithViewPager(viewPager);
    }
    @Event(value = {R.id.img_left})
    private void getE(View v){
        switch (v.getId()){
            case R.id.img_left:
                DataSynEvent event=new DataSynEvent();
                event.setType(event.DRAWER_LAYOUT_OPEN);
                EventBus.getDefault().post(event);
                break;
        }
    }
}
