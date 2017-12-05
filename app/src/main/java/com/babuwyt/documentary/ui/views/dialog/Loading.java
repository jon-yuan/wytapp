package com.babuwyt.documentary.ui.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.babuwyt.documentary.R;

/**
 * Created by lenovo on 2017/8/9.
 */

public class Loading extends Dialog  {
    public Loading(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);


    }
}
