package com.babuwyt.documentary.ui.activity;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.adapter.LookBigPicAdapter;
import com.babuwyt.documentary.base.BaseActivity;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.util.DensityUtils;
import com.babuwyt.documentary.util.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/14.
 * <p>
 * 查看大图
 */
@ContentView(R.layout.activity_lookbigpic)
public class LookBigPicActivity extends BaseActivity {
    public static final String MLIST = "MLIST";
    @ViewInject(R.id.viewpager)
    ViewPager viewpager;
    @ViewInject(R.id.layout_finish)
    LinearLayout layout_finish;
    @ViewInject(R.id.tv_num)
    TextView tv_num;

    private LookBigPicAdapter mAdapter;
    private ArrayList<ImageView> imageViews;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        imageViews = new ArrayList<ImageView>();
        mList = getIntent().getStringArrayListExtra(MLIST);
        int position = getIntent().getIntExtra("postion", 0);
        if (mList == null) {
            mList = new ArrayList<String>();
        }
        int width = DensityUtils.deviceWidthPX(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, width);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(width, width);
        viewpager.setLayoutParams(layoutParams1);
//        viewpager.getLayoutParams().width = width;
//        viewpager.getLayoutParams().height = width;
        for (int i = 0; i < mList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setLayoutParams(layoutParams);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            x.image().bind(imageView, BaseURL.BASE_IMAGE_URI+mList.get(i));
            imageViews.add(imageView);
        }
        mAdapter = new LookBigPicAdapter(imageViews);
        viewpager.setOffscreenPageLimit(imageViews.size());
        viewpager.setAdapter(mAdapter);
        viewpager.setCurrentItem(position);
        tv_num.setText(position+1 + "/" + mList.size());
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_num.setText((position + 1) + "/" + mList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Event(value = {R.id.layout_finish})
    private void gete(View v) {
        switch (v.getId()) {
            case R.id.layout_finish:
                finish();
                break;
        }
    }
}
