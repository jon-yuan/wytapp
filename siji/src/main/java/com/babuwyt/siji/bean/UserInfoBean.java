package com.babuwyt.siji.bean;

import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;
import com.babuwyt.siji.entity.UserInfoEntity;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/9/22.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class UserInfoBean extends BaseBean {
    private UserInfoEntity obj;

    public UserInfoEntity getObj() {
        return obj;
    }

    public void setObj(UserInfoEntity obj) {
        this.obj = obj;
    }
}
