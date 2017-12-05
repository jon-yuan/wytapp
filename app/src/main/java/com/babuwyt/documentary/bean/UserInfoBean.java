package com.babuwyt.documentary.bean;

import com.babuwyt.documentary.entity.UserInfoEntity;
import com.babuwyt.documentary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/7/27.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class UserInfoBean {
    private boolean success;
    private String msg;
    private UserInfoEntity obj;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserInfoEntity getObj() {
        return obj;
    }

    public void setObj(UserInfoEntity obj) {
        this.obj = obj;
    }


    /**
     * "success": true,
     "msg": "登录成功",
     "obj": {
     */
}
