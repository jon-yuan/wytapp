package com.babuwyt.jonylibrary.pulltorefresh.layout;

import android.content.Context;
import android.util.AttributeSet;


import com.babuwyt.jonylibrary.pulltorefresh.JDRefreshHeader;
import com.babuwyt.jonylibrary.pulltorefresh.header.RefreshHeader;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by lenovo on 2017/8/9.
 */

public class RefreshLayout extends PtrFrameLayout {

    RefreshHeader mHeaderView;

    public RefreshLayout(Context context) {
        super(context);
        initView();
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        mHeaderView = new RefreshHeader(getContext());
        setHeaderView(mHeaderView);
        addPtrUIHandler(mHeaderView);
    }
}
