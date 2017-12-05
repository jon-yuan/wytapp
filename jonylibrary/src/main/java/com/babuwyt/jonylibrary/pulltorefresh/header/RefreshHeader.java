package com.babuwyt.jonylibrary.pulltorefresh.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.babuwyt.jonylibrary.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by lenovo on 2017/8/9.
 */

public class RefreshHeader extends FrameLayout implements PtrUIHandler {

    /**
     * 提醒文本
     */
    private TextView mTvRemind;
    private ImageView image_ref;
    private ImageView image_loading;
    private AnimationDrawable animationDrawable;
    /**
     * 状态识别
     */
    private int mState;


    /**
     * 重置
     * 准备刷新
     * 开始刷新
     * 结束刷新
     */
    public static final int STATE_RESET = -1;
    public static final int STATE_PREPARE = 0;
    public static final int STATE_BEGIN = 1;
    public static final int STATE_FINISH = 2;

    public static final int MARGIN_RIGHT = 100;


    public RefreshHeader(@NonNull Context context) {
        super(context);
        initView();
    }

    public RefreshHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RefreshHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_header_default, this, false);
        mTvRemind = (TextView) view.findViewById(R.id.tv_remain);
        image_ref = (ImageView) view.findViewById(R.id.image_ref);
        image_loading = (ImageView) view.findViewById(R.id.image_loading);
        animationDrawable=(AnimationDrawable)image_loading.getBackground();
        animationDrawable.start();
        addView(view);
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mState = STATE_RESET;
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mState = STATE_PREPARE;
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mState = STATE_BEGIN;
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        mState = STATE_FINISH;
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        switch (mState) {
            case STATE_PREPARE:
                //logo设置
//                mIvMan.setAlpha(ptrIndicator.getCurrentPercent());
//                mIvGoods.setAlpha(ptrIndicator.getCurrentPercent());
//                LayoutParams mIvManLayoutParams = (LayoutParams) mIvMan.getLayoutParams();
//                if (ptrIndicator.getCurrentPercent() <= 1) {
//                    mIvMan.setScaleX(ptrIndicator.getCurrentPercent());
//                    mIvMan.setScaleY(ptrIndicator.getCurrentPercent());
//                    mIvGoods.setScaleX(ptrIndicator.getCurrentPercent());
//                    mIvGoods.setScaleY(ptrIndicator.getCurrentPercent());
//                    int marginRight = (int) (MARGIN_RIGHT - MARGIN_RIGHT * ptrIndicator.getCurrentPercent());
//                    mIvManLayoutParams.setMargins(0, 0, marginRight, 0);
//                    mIvMan.setLayoutParams(mIvManLayoutParams);
//                }
                image_loading.setVisibility(GONE);
                image_ref.setVisibility(VISIBLE);
                if (ptrIndicator.getCurrentPercent() < 1.2) {
                    mTvRemind.setText("下拉刷新...");
                    image_ref.setImageResource(R.drawable.pullrefresh_down);
                } else {
                    mTvRemind.setText("松开刷新...");
                    image_ref.setImageResource(R.drawable.pullrefresh_up);
                }
                break;
            case STATE_BEGIN:
                mTvRemind.setText("加载中...");
                image_loading.setVisibility(VISIBLE);

                image_ref.setVisibility(GONE);
                break;
            case STATE_FINISH:
                mTvRemind.setText("加载完成...");
                image_loading.setVisibility(GONE);
                image_ref.setVisibility(VISIBLE);
                image_ref.setImageResource(R.drawable.pullrefresh_succeed);
                break;
        }

    }
}
