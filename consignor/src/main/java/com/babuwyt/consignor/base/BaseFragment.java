package com.babuwyt.consignor.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babuwyt.jonylibrary.views.dialog.LoadingDialog;

import org.xutils.x;

/**
 * Created by lenovo on 2017/6/28.
 */

public class BaseFragment extends AppCompatDialogFragment {
    protected LoadingDialog loadingDialog;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog=new LoadingDialog(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }
}
