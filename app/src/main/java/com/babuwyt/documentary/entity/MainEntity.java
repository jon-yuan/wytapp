package com.babuwyt.documentary.entity;

import com.babuwyt.documentary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/7/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class MainEntity {
    private String fid;
        private int areano;
        private String driverName;
        private String fSendCarNo;
        private String fTaskState;
        private String fownerid;

    public String getFownerid() {
        return fownerid;
    }

    public void setFownerid(String fownerid) {
        this.fownerid = fownerid;
    }

    public int getAreano() {
        return areano;
    }

    public void setAreano(int areano) {
        this.areano = areano;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getfSendCarNo() {
        return fSendCarNo;
    }

    public void setfSendCarNo(String fSendCarNo) {
        this.fSendCarNo = fSendCarNo;
    }

    public String getfTaskState() {
        return fTaskState;
    }

    public void setfTaskState(String fTaskState) {
        this.fTaskState = fTaskState;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
    /**areano = 0;
     driverName = "\U97e9\U660a\U5029";
     fSendCarNo = D7520170711001;
     fTaskState = 5;
     *"areano":0,"driverName":"赵铁柱","fSendCarNo":"D11020170725002","fTaskState":"5"}
     */
}
