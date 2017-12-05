package com.babuwyt.jonylibrary.util;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by lenovo on 2017/9/28.
 */

public class InputMoney implements InputFilter {
    @Override
    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
        if (charSequence.toString().equals(".") && i2 == 0 && i3 == 0) {//判断小数点是否在第一位
//            money.setText(0+""+charSequence+spanned);//给小数点前面加0
//            money.setSelection(2);//设置光标
        }

        if (spanned.toString().indexOf(".") != -1 && (spanned.length() - spanned.toString().indexOf(".")) > 2) {//
            if ((spanned.length() - i2) < 3) {//判断现在输入的字符是不是在小数点后面
                return "";//过滤当前输入的字符
            }
        }
        return null;
    }
}
