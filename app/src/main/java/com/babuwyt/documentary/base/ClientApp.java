package com.babuwyt.documentary.base;

import android.app.Application;
import android.text.TextUtils;

import com.babuwyt.documentary.entity.UserInfoEntity;
import com.babuwyt.documentary.finals.SharePrefKeys;
import com.babuwyt.documentary.util.SharePreferencesUtils;
import com.google.gson.Gson;

import org.xutils.x;

import amap.MapUtil;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by lenovo on 2017/6/28.
 */

public class ClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        x.Ext.init(this);
        initJpush();
        initUserInfo();
//        Location();
    }

    /**
     * 初始化极光推送
     */

    private void initJpush(){
        JPushInterface.setDebugMode(false); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }



    /**
     * 初始化用户信息
     */
    private void initUserInfo() {
        String data = SharePreferencesUtils.getString(this, SharePrefKeys.XML_PREFERENCES, SharePrefKeys.XML_NAME_USER_INFO, "");
        if (!TextUtils.isEmpty(data)) {
            Gson gson = new Gson();
            UserInfoEntity user = gson.fromJson(data, UserInfoEntity.class);
            SessionManager sessionManager = SessionManager.getInstance();
            if (user != null) {
                sessionManager.setUser(user);
            }
        }
    }

    /**
     * 保存登录用户的信息
     *
     * @param user
     */
    public void saveLoginUser(UserInfoEntity user) {
        if (user == null) {
            return;
        }
        Gson gson = new Gson();
        String data = gson.toJson(user);

        SharePreferencesUtils.saveString(this, SharePrefKeys.XML_PREFERENCES, SharePrefKeys.XML_NAME_USER_INFO, data);
        this.initUserInfo();


    }
    /**
     * 清除用户登录信息
     */
    public  void clearLoginUser() {
        SharePreferencesUtils.clearAll(this, SharePrefKeys.XML_PREFERENCES);
        SessionManager sessionManager = SessionManager.getInstance();
        sessionManager.setUser(null);
    }

    /**
     * 开启APP 后进行定位一次定位
     */
    private void Location(){
        MapUtil.getInstance(this).Location();
    }
}