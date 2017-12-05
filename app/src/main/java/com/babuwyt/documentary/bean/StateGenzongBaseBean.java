package com.babuwyt.documentary.bean;

import com.babuwyt.documentary.entity.Driver;
import com.babuwyt.documentary.entity.StateGenzongEntity;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/4.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class StateGenzongBaseBean {
    private ArrayList<StateGenzongEntity> worktrack;
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }
    private boolean state;
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ArrayList<StateGenzongEntity> getWorktrack() {
        return worktrack;
    }

    public void setWorktrack(ArrayList<StateGenzongEntity> worktrack) {
        this.worktrack = worktrack;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
