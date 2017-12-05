package com.babuwyt.consignor.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.base.BaseActivity;
import com.babuwyt.consignor.base.SessionManager;
import com.babuwyt.consignor.bean.AddressList;
import com.babuwyt.consignor.entity.Address;
import com.babuwyt.consignor.entity.AddressEntity;
import com.babuwyt.consignor.entity.AreaEntity;
import com.babuwyt.consignor.entity.CityEntity;
import com.babuwyt.consignor.entity.ProvinceEntity;
import com.babuwyt.consignor.finals.BaseURL;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.util.GetJsonDataUtil;
import com.babuwyt.jonylibrary.util.UHelper;
import com.babuwyt.jonylibrary.util.XUtil;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/10.
 */
@ContentView(R.layout.activity_addaddress)
public class AddAddressActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.tv_p)
    TextView tv_p;
    @ViewInject(R.id.tv_c)
    TextView tv_c;
    @ViewInject(R.id.tv_a)
    TextView tv_a;
    @ViewInject(R.id.et_address)
    EditText et_address;
    @ViewInject(R.id.et_user)
    EditText et_user;
    @ViewInject(R.id.et_tel)
    EditText et_tel;
    @ViewInject(R.id.layout_select)
    LinearLayout layout_select;
    @ViewInject(R.id.radiogroup)
    RadioGroup radiogroup;
    @ViewInject(R.id.radio1)
    RadioButton radio1;
    @ViewInject(R.id.radio2)
    RadioButton radio2;

    private Intent intent;
    private AddressEntity addressEntity;
    private ArrayList<ProvinceEntity> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<CityEntity>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<AreaEntity>>> options3Items = new ArrayList<>();
    private String AreaId="";
    private String AddressType="1";
    private int position;
    private String type;
    private ArrayList<Address> mList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
//        getCityinfo();

        position=getIntent().getIntExtra("position",-1);
        type=getIntent().getStringExtra("type");
        AddressEntity entity= (AddressEntity) getIntent().getSerializableExtra("AddressEntity");
        if (entity!=null){
            AddressType=entity.getAddressType();
            if (AddressType.equalsIgnoreCase("1")){
                radio1.setChecked(true);
                radio2.setChecked(false);
            }else {
                radio1.setChecked(false);
                radio2.setChecked(true);
            }
            AreaId=entity.getAreaId();
            tv_p.setText(entity.getProvinceName());
            tv_c.setText(entity.getCityName());
            tv_a.setText(entity.getAreaName());
            et_address.setText(entity.getAddressDetails());
            et_tel.setText(entity.getContactPhone());
            et_user.setText(entity.getContactName());
        }
        initJsonData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.addresslist, menu);
        return true;
    }

    private void init() {
        intent=new Intent();
        mList=new ArrayList<Address>();
        addressEntity=new AddressEntity();
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_address:

                        getAddressList();
                        break;
                }
                return true;
            }
        });
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio1:
                        AddressType="1";
                        break;
                        case R.id.radio2:
                        AddressType="2";
                        break;
                }
            }
        });
    }

    @SuppressLint("NewApi")
    @Event(value = {R.id.btn_mk,R.id.layout_select,R.id.tv_p,R.id.tv_c,R.id.tv_a})
    private void getE(View view){
        switch (view.getId()){
            case R.id.btn_mk:
                getData();
                break;
            case R.id.layout_select:
            case R.id.tv_p:
            case R.id.tv_c:
            case R.id.tv_a:
                showAddress();
                break;
        }
    }

    private void getData(){
        if (TextUtils.isEmpty(tv_p.getText().toString())|| TextUtils.isEmpty(tv_c.getText().toString()) ||TextUtils.isEmpty(tv_a.getText().toString())){
            UHelper.showToast(this,getString(R.string.please_select_address));
            return;
        }
        if (TextUtils.isEmpty(et_user.getText().toString().trim())){
            UHelper.showToast(this,getString(R.string.please_input_name));
            return;
        }
        if (TextUtils.isEmpty(et_tel.getText().toString().trim())){
            UHelper.showToast(this,getString(R.string.please_input_phone));
            return;
        }
        if (!UHelper.isPhone(et_tel.getText().toString().trim())){
            UHelper.showToast(this,getString(R.string.PHONE_IS_FAIL));
            return;
        }
        addressEntity.setContactPhone(et_tel.getText().toString());
        addressEntity.setContactName(et_user.getText().toString());
        addressEntity.setAddressDetails(et_address.getText().toString());
        addressEntity.setAreaId(AreaId);
        addressEntity.setProvinceName(tv_p.getText().toString());
        addressEntity.setCityName(tv_c.getText().toString());
        addressEntity.setAreaName(tv_a.getText().toString());
        addressEntity.setAddressType(AddressType);

        intent.putExtra("AddressEntity",addressEntity);
        if (type.equalsIgnoreCase("edit")){
            intent.putExtra("position",position);
            setResult(2,intent);
        }else {
            setResult(1,intent);
        }
        finish();
    }





    private void showAddress(){
        if (options1Items.size()<=0){
            UHelper.showToast(AddAddressActivity.this,"空");
            return;
        }

        OptionsPickerView pvOptions = new  OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                tv_p.setText(options1Items.get(options1).getPickerViewText());
                tv_c.setText(options2Items.get(options1).get(option2).getPickerViewText());
                tv_a.setText(options3Items.get(options1).get(option2).get(options3).getPickerViewText());
                AreaId=options3Items.get(options1).get(option2).get(options3).getAreaId();
            }
        }).build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();
    }
    private void initJsonData(){
        loadingDialog.showDialog();
        /**
         * 解析资源文件省市区json数据
         */
        String JsonData = new GetJsonDataUtil().getJson(this,"province1.json");//获取assets目录下的json文件数据
        ArrayList<ProvinceEntity> jsonBean = parseData(JsonData);//用Gson 转成实体
        options1Items=jsonBean;
        for (int i=0;i<options1Items.size();i++){//遍历省份
            ArrayList<CityEntity> CityList = new ArrayList<CityEntity>();//该省的城市列表（第二级）
            ArrayList<ArrayList<AreaEntity>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<options1Items.get(i).getCity().size(); c++){//遍历该省份的所有城市
                String CityName = options1Items.get(i).getCity().get(c).getCityName();
                String id = options1Items.get(i).getCity().get(c).getCityId();
                CityEntity entity=new CityEntity();
                entity.setCityName(CityName);
                entity.setCityId(id);
                CityList.add(entity);//添加城市
                ArrayList<AreaEntity> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (options1Items.get(i).getCity().get(c).getArea() == null
                        ||options1Items.get(i).getCity().get(c).getArea().size()==0) {
                    City_AreaList.add(new AreaEntity());
                }else {
                    for (int d=0; d < options1Items.get(i).getCity().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = options1Items.get(i).getCity().get(c).getArea().get(d).getAreaName();
                        String Areaid = options1Items.get(i).getCity().get(c).getArea().get(d).getAreaId();
                        AreaEntity areaEntity=new AreaEntity();
                        areaEntity.setAreaName(AreaName);
                        areaEntity.setAreaId(Areaid);
                        City_AreaList.add(areaEntity);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
        loadingDialog.dissDialog();
    }

    public ArrayList<ProvinceEntity> parseData(String result) {//Gson 解析
        ArrayList<ProvinceEntity> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                ProvinceEntity entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceEntity.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private void getAddressList(){
        ArrayList<String> list=new ArrayList<String >();
        list.add(SessionManager.getInstance().getUser().getUserId());
        XUtil.GetPing(BaseURL.ADDRESS_LIST,list,new MyCallBack<AddressList>(){
            @Override
            public void onSuccess(AddressList result) {
                super.onSuccess(result);
                if (result.isSuccess()){
                    mList.clear();
                    mList.addAll(result.getObj());
                    showAddressList(mList);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });

    }

    private void showAddressList(ArrayList<Address> list){
        if (list==null || list.size()<=0){
            UHelper.showToast(this,"没有可用的地址");
            return;
        }
        final OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                Address address=mList.get(options1);
                AreaId=address.getFareaNo();
                tv_p.setText(address.getProvinceName());
                tv_c.setText(address.getCityName());
                tv_a.setText(address.getAreaName());
                et_address.setText(address.getFaddressDetails());
                et_tel.setText(address.getfPhone());
                et_user.setText(address.getfLinkMan());


            }
        }).build();
        pvOptions.setPicker(list);
        pvOptions.show();
    }


}
