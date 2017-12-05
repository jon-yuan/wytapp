package com.babuwyt.consignor.finals;



import android.content.Context;
import android.text.TextUtils;

import com.babuwyt.consignor.entity.SignEntity;
import com.babuwyt.jonylibrary.util.SharePreferencesUtils;
import com.google.gson.Gson;
import com.tencent.cos.COSClient;
import com.tencent.cos.COSClientConfig;
import com.tencent.cos.common.COSEndPoint;
import com.tencent.cos.model.PutObjectRequest;
import com.tencent.cos.task.listener.IUploadTaskListener;

/**
 * 其他常量配置文件，如第三方请求参数等
 * Created by jon on 2014/12/1.
 */
public class Constant {
    public static String appid = "1253538594";
    public static String secretId = "AKIDb7TktwNXbefUDoTYo6EBmLjwyqcG44FE";
    public static String secretKey = "0pUyHUYZQLsFap2VmsmVHxToyAB2lKiu";
    public static String bucket = "bbkj";
    //上传腾讯云
    public static void upload(Context context,String srcPath,String fileName,String sign,IUploadTaskListener listener){

        String appid = Constant.appid;

        String peristenceId = null;

        //创建COSClientConfig对象，根据需要修改默认的配置参数
        COSClientConfig config = new COSClientConfig();
        //如设置园区
        config.setEndPoint(COSEndPoint.COS_GZ);

        COSClient cos = new COSClient(context,appid,config,peristenceId);

//        String cosPath =  "/" + filename; //cos 上的路径
        String cosPath =  fileName; //cos 上的路径
        PutObjectRequest putObjectRequest = new PutObjectRequest();
        putObjectRequest.setBucket(Constant.bucket);
        putObjectRequest.setCosPath(cosPath);
        putObjectRequest.setSrcPath(srcPath);
        putObjectRequest.setSign(sign);
        putObjectRequest.setListener(listener);
        cos.putObject(putObjectRequest);
        cos.putObjectAsyn(putObjectRequest);
    }
    /**
     * 获取签名
     * SharePrefKeys
     */
    public static SignEntity getSign(Context context){
        String data= SharePreferencesUtils.getString(context, SharePrefKeys.XML_UPLOAD_IMG_SIGN,SharePrefKeys.XML_UPLOAD_IMG_SIGN,"");
        if (!TextUtils.isEmpty(data)) {
            Gson gson = new Gson();
            SignEntity signEntity = gson.fromJson(data,SignEntity.class);
            return signEntity;
        }
        return null;
    }
    //保存sign以及 当前时间戳
    public static void saveSign(Context context,SignEntity entity){
        Gson gson = new Gson();
        String data = gson.toJson(entity);
        SharePreferencesUtils.saveString(context, SharePrefKeys.XML_UPLOAD_IMG_SIGN, SharePrefKeys.XML_UPLOAD_IMG_SIGN, data);
    }

    //难道Sign后进行对比  如果Sign小于28天 则继续使用 大于88天则请求服务器重新获取

    public static boolean isOld(SignEntity mSign){
        long newTime=System.currentTimeMillis()/1000;
        long oldTime=mSign.getTime();
        if (newTime-oldTime>88*24*3600){
            //Sign过期
            return false;
        }
        return true;
    }
}