package com.babuwyt.documentary.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.babuwyt.documentary.R;

import org.xutils.x;

/**
 * Created by lenovo on 2017/6/28.
 */

public abstract class BaseFragment extends AppCompatDialogFragment {
    protected Dialog mDialog;
    protected boolean isVisible;
    protected boolean isInit;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
        }
    }
    public abstract void onVisible();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isInit=true;
        return x.view().inject(this, inflater, container);
    }
}
