package com.babuwyt.consignor.finals;

/**
 * 此处专门放接口名
 * Created by 吴同 on 2014/12/9.
 */
public class BaseURL {
    /**
     * 每页数量
     */
    public static final int PAGESIZE = 20;
    /**
     * 请求地址
     */
    public static final String BASE_URL = "http://www.babuwyt.com:8080/tmsapi/";//测试服务器地址
//    public static final String BASE_URL = "http://123.206.75.242:8090/tmsapi/";//测试服务器地
//    public static final String BASE_URL = "http://119.29.230.77:8090/tmsapi/";//正式服务器地址
//    private static final String BASE_URL = "http://192.168.2.71:8091/";
//    public static final String BASE_URL = "http://192.168.2.41:8080/tmsapi/";
    public static final String BASE_IMAGE_URI = "http://bbkj-1253538594.picgz.myqcloud.com/";
    /**
     * 轨迹
     */
    public static final String GETSIGN = BASE_URL + "appcommon/getImgSign";
//    public static final String GETSIGN = BASE_URL + "http://123.206.75.242:8090/appcommon/getImgSign";
    /**
     * 轨迹
     */
    public static final String GUIJI = BASE_URL + "PLtrucklocation/PLgetlocation/history/format/sendcarid";
    /**
     * 车辆位置
     */
    public static final String WEIZHI = BASE_URL + "operationApp/getLocationBySendCarOrder";
    /**
     *
     * 作业状态
     * */
    public static final String GETWORKTRACK = BASE_URL + "appcommon/getworktrack";
    /**
     *
     * 注册
     * */
    public static final String REGISTER = BASE_URL + "shipperApp/register";
    /**
     *
     * 获取验证码
     * */
    public static final String GETAUTHCODE = BASE_URL + "shipperApp/getAuthCode";
    /**
     *
     * 登录
     * */
    public static final String LOGIN = BASE_URL + "shipperApp/login";
    /**
     *
     *修改密码
     * */
    public static final String CHANGEPSD = BASE_URL + "shipperApp/changePsd";
    /**
     *
     *忘记密码
     * */
    public static final String FORGETPSD = BASE_URL + "shipperApp/forgetPsd";
    /**
     *
     *绑定手机
     * */
    public static final String BINDPHONE = BASE_URL + "shipperApp/bindPhone";
    /**
     *
     *获取个人以及公司信息
     * */
    public static final String GETUSERINFO = BASE_URL + "shipperApp/getUserInfo";
    /**
     *
     *信息认证
     * */
    public static final String INFOAUTHENTICATION = BASE_URL + "shipperApp/infoAuthentication";
    /**
     *
     *新增订单保存
     * */
    public static final String ADDORDER = BASE_URL + "shipperApp/addOrder";
    /**
     *
     *获取类型  1  车辆类型 2 包装类型
     * */
    public static final String GETSYSTYPE = BASE_URL + "shipperApp/getSysType";
    /**
     *
     *获取三级地址
     * */
    public static final String GETSYSADDRESS = BASE_URL + "shipperApp/getSysAddress";
    /**
     *
     *获取三级地址
     * */
    public static final String ADDRESS_LIST= BASE_URL + "shipperApp/select/load";
    /**
     *
     *获取订单列表
     * */
    public static final String GETORDERLIST = BASE_URL + "shipperApp/getOrderList";
    /**
     *
     *删除订单
     * */
    public static final String DELORDER = BASE_URL + "shipperApp/delOrder";
    /**
     *
     *发布订单
     * */
    public static final String RELORDER = BASE_URL + "shipperApp/releaseOrder";
    /**
     *
     *订单详情
     * */
    public static final String ORDERDETAILS = BASE_URL + "shipperApp/orderDetails";
    /**
     *
     *获取订单下的地址
     * */
    public static final String GETADDRESS = BASE_URL + "shipperApp/getAddress";

}

