package com.babuwyt.documentary.inteface;

import com.babuwyt.documentary.entity.MainEntity;

/**
 * Created by lenovo on 2017/7/14.
 * 首页适配器中BUTTON的回调
 */

public interface MainAdapterCallBack{
    void weizhiCallBack(int position , MainEntity o);
    void guijiCallBack(int position , MainEntity o);
    void examineCallBack(int position , MainEntity o);
    void qianshouCallBack(int position , MainEntity o);
    void detailsCallBack(int position , MainEntity o);
}
