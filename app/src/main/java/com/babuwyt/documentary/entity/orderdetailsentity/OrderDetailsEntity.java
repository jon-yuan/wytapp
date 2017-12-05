package com.babuwyt.documentary.entity.orderdetailsentity;

import com.babuwyt.documentary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderDetailsEntity {

    private String fsendcarno;
    private long fpickuptime;
    private long fpicktime;
    private String ftaskstate;
    private String ftaskstatename;//自定义的变量
    private String ordergood;
    private ArrayList<String> pricture;
    private ArrayList<String> pricture1;
    private ArrayList<Fromtomap> fromtolist;
    private ArrayList<TworktracklistObj> tworktracklist;

    public String getFsendcarno() {
        return fsendcarno;
    }

    public void setFsendcarno(String fsendcarno) {
        this.fsendcarno = fsendcarno;
    }

    public long getFpickuptime() {
        return fpickuptime;
    }

    public void setFpickuptime(long fpickuptime) {
        this.fpickuptime = fpickuptime;
    }

    public long getFpicktime() {
        return fpicktime;
    }

    public void setFpicktime(long fpicktime) {
        this.fpicktime = fpicktime;
    }

    public String getFtaskstate() {
        return ftaskstate;
    }

    public void setFtaskstate(String ftaskstate) {
        this.ftaskstate = ftaskstate;
    }

    public String getFtaskstatename() {
        return ftaskstatename;
    }

    public void setFtaskstatename(String ftaskstatename) {
        this.ftaskstatename = ftaskstatename;
    }

    public String getOrdergood() {
        return ordergood;
    }

    public void setOrdergood(String ordergood) {
        this.ordergood = ordergood;
    }

    public ArrayList<String> getPricture() {
        return pricture;
    }

    public void setPricture(ArrayList<String> pricture) {
        this.pricture = pricture;
    }

    public ArrayList<Fromtomap> getFromtolist() {
        return fromtolist;
    }

    public void setFromtolist(ArrayList<Fromtomap> fromtolist) {
        this.fromtolist = fromtolist;
    }

    public ArrayList<TworktracklistObj> getTworktracklist() {
        return tworktracklist;
    }

    public void setTworktracklist(ArrayList<TworktracklistObj> tworktracklist) {
        this.tworktracklist = tworktracklist;
    }

    public ArrayList<String> getPricture1() {
        return pricture1;
    }

    public void setPricture1(ArrayList<String> pricture1) {
        this.pricture1 = pricture1;
    }
    /**
     * "obj": {
     "fsendcarno": "D12120170721001",
     "fpickuptime": 1563494820000,
     "fpicktime": 1500629601000,
     "ftaskstate": 5,
     "ordergood": "组件",
     "pricture": [
     "2017/07/21/120-610113-329791ac0c244a12aed6b56bdbf996dc.jpg",
     "2017/07/21/120-610113-abe279ae3fa943839ce5f09d017a8f65.jpg"
     ],
     "fromtomap": {
     "TO": "陕西省西安市陕西省123123人间",
     "FROM": "陕西省西安市陕西省123123123123人间"
     },
     "tworktracklist": [
     {
     "fid": 269,
     "fsendcarid": 176,
     "fstate": 1,
     "fcreatetime": 1500629601000,
     "fuserid": 121,
     "fremark": null,
     "fremark1": null
     },
     {
     "fid": 270,
     "fsendcarid": 176,
     "fstate": 2,
     "fcreatetime": 1500629759000,
     "fuserid": 121,
     "fremark": null,
     "fremark1": null
     },
     {
     "fid": 271,
     "fsendcarid": 176,
     "fstate": 2,
     "fcreatetime": 1500629774000,
     "fuserid": 121,
     "fremark": null,
     "fremark1": null
     },
     {
     "fid": 272,
     "fsendcarid": 176,
     "fstate": 3,
     "fcreatetime": 1500629894000,
     "fuserid": 1,
     "fremark": null,
     "fremark1": null
     },
     {
     "fid": 273,
     "fsendcarid": 176,
     "fstate": 4,
     "fcreatetime": 1500629958000,
     "fuserid": 121,
     "fremark": null,
     "fremark1": null
     },
     {
     "fid": 274,
     "fsendcarid": 176,
     "fstate": 5,
     "fcreatetime": 1500630305000,
     "fuserid": 1,
     "fremark": null,
     "fremark1": null
     }
     ]
     }
     */
}
