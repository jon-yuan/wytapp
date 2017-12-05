package com.babuwyt.jonylibrary.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.jonylibrary.R;

/**
 * Created by lenovo on 2017/9/20.
 */

public class CustomEditText extends LinearLayout{
    private EditText edittext;
    private TextView textview;
    public CustomEditText(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view=LayoutInflater.from(context).inflate(R.layout.view_customedittext,null);
        edittext=view.findViewById(R.id.edittext);
        textview=view.findViewById(R.id.textview);
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                customTextWatcher.beforeTextChanged(charSequence,i,i1,i2);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>0){
                    textview.setVisibility(GONE);
                }else {
                    textview.setVisibility(VISIBLE);
                }
                customTextWatcher.onTextChanged(charSequence,i,i1,i2);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                customTextWatcher.afterTextChanged(editable);
            }
        });
    }
    public void setHint(String s){
        textview.setHint(s);
        textview.setVisibility(VISIBLE);
    }
    public void setText(String s){
        edittext.setText(s);
    }

    public interface CustomTextWatcher{
        void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2);
        void onTextChanged(CharSequence charSequence, int i, int i1, int i2);
        void afterTextChanged(Editable editable);
    }
    private CustomTextWatcher customTextWatcher;
    public void addTextChangedListener(CustomTextWatcher customTextWatcher){
        this.customTextWatcher=customTextWatcher;
    }
}
