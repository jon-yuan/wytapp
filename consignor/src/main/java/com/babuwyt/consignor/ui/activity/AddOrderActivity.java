package com.babuwyt.consignor.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.adapter.AddOrderAddressAdapter;
import com.babuwyt.consignor.base.BaseActivity;
import com.babuwyt.consignor.base.SessionManager;
import com.babuwyt.consignor.bean.AddressListBean;
import com.babuwyt.consignor.bean.OrderBean;
import com.babuwyt.consignor.entity.AddressEntity;
import com.babuwyt.consignor.entity.OrderEntity;
import com.babuwyt.consignor.entity.TypeEntity;
import com.babuwyt.consignor.finals.BaseURL;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.util.DateUtils;
import com.babuwyt.jonylibrary.util.UHelper;
import com.babuwyt.jonylibrary.util.XUtil;
import com.babuwyt.jonylibrary.views.CustomListView;
import com.bigkoo.pickerview.TimePickerView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/11.
 */
@ContentView(R.layout.activity_addorder)
public class AddOrderActivity extends BaseActivity {
    public static final int REQUEST_CODE = 0;
    public static final int CAR_TYPE_RESULT = 1;
    public static final int PACK_TYPE_RESULT = 2;
    public static final int ADDRESS_TYPE_RESULT = 3;

    public static final String CARTYPE = "car_type";
    public static final String PACKTYPE = "pack_type";

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.tv_orderno)
    EditText tv_orderno;
    @ViewInject(R.id.tv_cartype)
    TextView tv_cartype;
    @ViewInject(R.id.tv_packtype)
    TextView tv_packtype;
    @ViewInject(R.id.tv_time)
    TextView tv_time;
    @ViewInject(R.id.tv_farrivetime)
    TextView tv_farrivetime;
    @ViewInject(R.id.listview_address)
    CustomListView listview_address;
    @ViewInject(R.id.tv_luxian)
    TextView tv_luxian;
    @ViewInject(R.id.et_remark)
    EditText et_remark;
    @ViewInject(R.id.tv_goodsName)
    EditText tv_goodsName;
    @ViewInject(R.id.et_baozhi)
    EditText et_baozhi;
    @ViewInject(R.id.et_zhongliang)
    EditText et_zhongliang;
    @ViewInject(R.id.et_num)
    EditText et_num;
    @ViewInject(R.id.et_tiji)
    EditText et_tiji;
    @ViewInject(R.id.btn_mk)
    TextView btn_mk;
    //    @ViewInject(R.id.listview_goods)
//    CustomListView listview_goods;
//    @ViewInject(R.id.tv_addorder)
//    TextView tv_addorder;
    private Intent intent;
    private String routeFrom = "";
    private String routeTo = "";
    private String carTypeId = "";//车辆类型
    private String goodsPackTypeId = "";//包装类型id
    private String showTime = "";
    private String farrivetime = "";
    private ArrayList<AddressEntity> addressList;
    private AddOrderAddressAdapter adapter;
    private String orderId;
    private String type = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderId = getIntent().getStringExtra("orderId");
        type = getIntent().getStringExtra("type");
        init();
        //如果是编辑修改则需要根据订单ID 获取订单信息
        if (type.equalsIgnoreCase("edit")) {
            toolbar.setTitle(getString(R.string.xiugaiorder));
            getOrderDetails();
        }
    }

    private void init() {
        intent = new Intent();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addressList = new ArrayList<AddressEntity>();
        adapter = new AddOrderAddressAdapter(this);
        adapter.setmList(addressList);
        listview_address.setAdapter(adapter);

    }

    @Event(value = {R.id.img_address, R.id.tv_cartype, R.id.tv_packtype, R.id.tv_time, R.id.tv_farrivetime, R.id.btn_mk})
    private void getE(View v) {
        switch (v.getId()) {
            case R.id.img_address:
                intent.setClass(this, AddressInfoActivity.class);
                intent.putExtra("addressList", addressList);
                startActivityForResult(intent, REQUEST_CODE);

                break;
            case R.id.tv_cartype:
                intent.setClass(this, CarTypeActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_packtype:
                intent.setClass(this, GoodsPackTypeActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_time:
                getTime(0);
                break;
            case R.id.tv_farrivetime:
                getTime(1);
                break;
            case R.id.btn_mk:
                AddOrder();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode && data != null) {
            switch (resultCode) {
                case CAR_TYPE_RESULT://车辆类型
                    TypeEntity entity = (TypeEntity) data.getSerializableExtra(CARTYPE);
                    tv_cartype.setText(entity.getName());
                    carTypeId = entity.getId();
                    break;
                case PACK_TYPE_RESULT://包装类型
                    TypeEntity entity1 = (TypeEntity) data.getSerializableExtra(PACKTYPE);
                    tv_packtype.setText(entity1.getName());
                    goodsPackTypeId = entity1.getId();
                    break;
                case ADDRESS_TYPE_RESULT://地址
                    ArrayList<AddressEntity> list = (ArrayList<AddressEntity>) data.getSerializableExtra("addresslist");
                    if (list != null) {
                        addressList.clear();
                        addressList.addAll(list);
                        adapter.notifyDataSetChanged();
                        setLuxian();
                    }
                    break;
            }
        }
    }

    private void setLuxian() {
        if (addressList == null || addressList.size() <= 0) {
            return;
        }

        for (AddressEntity addressEntity : addressList) {
            if (addressEntity.getAddressType().equalsIgnoreCase("1")) {
                routeFrom = addressEntity.getCityName();
                break;
            }
        }
        for (AddressEntity addressEntity : addressList) {
            if (addressEntity.getAddressType().equalsIgnoreCase("2")) {
                routeTo = addressEntity.getCityName();
            }
        }

        tv_luxian.setText(routeFrom + "-" + routeTo);
    }

    private void AddOrder() {
//        if (TextUtils.isEmpty(carTypeId) ||
//                TextUtils.isEmpty(showTime + "") ||
//                addressList.size() <= 0 ||
//                TextUtils.isEmpty(routeFrom) ||
//                TextUtils.isEmpty(routeTo)
//                )
//        {
//            UHelper.showToast(this, "订单信息不全！");
//            return;
//        }

        if (TextUtils.isEmpty(carTypeId)) {
            UHelper.showToast(this, "请选择车辆！");
            return;
        }
        if (TextUtils.isEmpty(showTime + "")) {
            UHelper.showToast(this, "请确认计划提货时间！");
            return;
        }

        if (addressList.size() <= 0 ||
                TextUtils.isEmpty(routeFrom) ||
                TextUtils.isEmpty(routeTo)) {
            UHelper.showToast(this, "请确认提卸货地是否正确");
            return;
        }


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", SessionManager.getInstance().getUser().getToken());
        map.put("userId", SessionManager.getInstance().getUser().getUserId());
        if (!TextUtils.isEmpty(tv_orderno.getText().toString().trim())) {
            map.put("orderNumber", tv_orderno.getText().toString().trim());
        }
        map.put("carTypeId", carTypeId);
        map.put("goodsPackTypeId", goodsPackTypeId);
        map.put("extractTime", showTime);
        map.put("farrivetime", farrivetime);
        map.put("loadAddress", addressList);
        map.put("routeFrom", routeFrom);
        map.put("routeTo", routeTo);
        map.put("remark", et_remark.getText().toString().trim());
        map.put("goodsName", tv_goodsName.getText().toString().trim());
        map.put("goodsNum", et_num.getText().toString());
        map.put("goodsWeight", et_zhongliang.getText().toString().trim());
        map.put("goodsVolume", et_tiji.getText().toString().trim());
        map.put("goodsHedge", et_baozhi.getText().toString().trim());
        map.put("type", type.equalsIgnoreCase("add") ? 1 : 2);
        loadingDialog.showDialog();
        XUtil.PostJsonObj(BaseURL.ADDORDER, map, new MyCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess()) {
                    finish();
                    if (type.equalsIgnoreCase("add")) {
                        UHelper.showToast(AddOrderActivity.this, "添加成功！");
                    } else {
                        UHelper.showToast(AddOrderActivity.this, "订单已修改!");
                    }
                } else {
                    UHelper.showToast(AddOrderActivity.this, result.getMsg());
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
            }
        });
    }

    private void getTime(final int i) {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (i==0){
                    showTime = DateUtils.DateToString(date, DateUtils.type1);
                    tv_time.setText(showTime);
                }else {
                    farrivetime=DateUtils.DateToString(date, DateUtils.type1);
                    tv_farrivetime.setText(farrivetime);
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .build();
        pvTime.show();

    }

    //修改订单时 获取订单详情
    private void getOrderDetails() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", SessionManager.getInstance().getUser().getUserId());
        map.put("orderId", orderId);
        loadingDialog.showDialog();
        XUtil.PostJson(BaseURL.ORDERDETAILS, map, new MyCallBack<OrderBean>() {
            @Override
            public void onSuccess(OrderBean result) {
                super.onSuccess(result);
                loadingDialog.dissDialog();
                if (result.isSuccess() && result.getObj() != null) {
                    setData(result.getObj());
                } else {
                    UHelper.showToast(AddOrderActivity.this, getString(R.string.no_orderdetails));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                loadingDialog.dissDialog();
            }
        });
    }

    private void setData(OrderEntity orderEntity) {

        tv_orderno.setText(orderEntity.getOrderNumber());
        tv_cartype.setText(orderEntity.getCarType());
        carTypeId = orderEntity.getCarTypeId();
        showTime = orderEntity.getExtractTime();
        tv_time.setText(showTime);
        farrivetime = orderEntity.getFarrivetime();
        tv_farrivetime.setText(farrivetime);

        et_remark.setText(orderEntity.getRemark());
        tv_goodsName.setText(orderEntity.getGoodsName());
        tv_packtype.setText(orderEntity.getGoodsPackType());
        goodsPackTypeId = orderEntity.getGoodsPackId();
        et_num.setText(orderEntity.getGoodsNum() + "");
        et_zhongliang.setText(orderEntity.getGoodsWeight() + "");
        et_tiji.setText(orderEntity.getGoodsVolume() + "");
        et_baozhi.setText(orderEntity.getGoodsHedge() + "");
        getAddress();
//        ArrayList<AddressEntity> list=orderEntity.getLoadAddressList();
//        addressList.addAll(list);
//        adapter.notifyDataSetChanged();
//        setLuxian();
    }

    //getAddress
    private void getAddress() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", SessionManager.getInstance().getUser().getUserId());
        map.put("token", SessionManager.getInstance().getUser().getToken());
        map.put("orderId", orderId);
        XUtil.PostJson(BaseURL.GETADDRESS, map, new MyCallBack<AddressListBean>() {
            @Override
            public void onSuccess(AddressListBean result) {
                super.onSuccess(result);
                if (result.isSuccess() && result.getObj() != null && result.getObj().size() > 0) {
                    ArrayList<AddressEntity> list = result.getObj();
                    addressList.clear();
                    addressList.addAll(list);
                    adapter.notifyDataSetChanged();
                    setLuxian();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
}
