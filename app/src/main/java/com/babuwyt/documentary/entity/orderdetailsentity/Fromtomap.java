package com.babuwyt.documentary.entity.orderdetailsentity;

import com.babuwyt.documentary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/23.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class Fromtomap {

    private String TO;
    private String FROM;
    private String fid;
    private String ftransportno;
    private String fromto;
    private String faddress;
    private String fcustomername;
    private String fdistrict;
    private String flinkman;
    private String fphone;
    private String fseq;
    private String fromtoloadid;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFtransportno() {
        return ftransportno;
    }

    public void setFtransportno(String ftransportno) {
        this.ftransportno = ftransportno;
    }

    public String getFromto() {
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }

    public String getFaddress() {
        return faddress;
    }

    public void setFaddress(String faddress) {
        this.faddress = faddress;
    }

    public String getFcustomername() {
        return fcustomername;
    }

    public void setFcustomername(String fcustomername) {
        this.fcustomername = fcustomername;
    }

    public String getFdistrict() {
        return fdistrict;
    }

    public void setFdistrict(String fdistrict) {
        this.fdistrict = fdistrict;
    }

    public String getFlinkman() {
        return flinkman;
    }

    public void setFlinkman(String flinkman) {
        this.flinkman = flinkman;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public String getFseq() {
        return fseq;
    }

    public void setFseq(String fseq) {
        this.fseq = fseq;
    }

    public String getFromtoloadid() {
        return fromtoloadid;
    }

    public void setFromtoloadid(String fromtoloadid) {
        this.fromtoloadid = fromtoloadid;
    }

    public String getTO() {
        return TO;
    }

    public void setTO(String TO) {
        this.TO = TO;
    }

    public String getFROM() {
        return FROM;
    }

    public void setFROM(String FROM) {
        this.FROM = FROM;
    }

    /**
     *    "TO": "陕西省西安市陕西省123123人间",
     "FROM": "陕西省西安市陕西省123123123123人间"

     "fid": null,
     "ftransportno": null,
     "fromto": "FROM",
     "faddress": "陕西省西安市陕西省开元东路15号",
     "fcustomername": null,
     "fdistrict": null,
     "flinkman": null,
     "fphone": null,
     "fseq": null,
     "fromtoloadid": null
     */
}
