package com.babuwyt.consignor.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.base.BaseFragment;
import com.babuwyt.consignor.base.SessionManager;
import com.babuwyt.consignor.ui.activity.CompanyInfoActivity;
import com.babuwyt.consignor.ui.activity.MyOrderActivity;
import com.babuwyt.consignor.ui.activity.SettingActivity;
import com.babuwyt.consignor.ui.activity.UserInfoActivity;
import com.babuwyt.jonylibrary.util.UHelper;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by lenovo on 2017/9/4.
 */
@ContentView(R.layout.fragment_mainleft)
public class MainLeftFragment extends BaseFragment {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.tv_versionName)
    TextView tv_versionName;

    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        intent = new Intent();
        tv_name.setText(SessionManager.getInstance().getUser().getUserName());
        tv_versionName.setText(UHelper.getAppVersionInfo(getActivity(), UHelper.TYPE_VERSION_NAME));
    }

    @Event(value = {R.id.layout_setting, R.id.layout_wodedingdan, R.id.layout_qiyexinxi, R.id.layout_gerenxinxi})
    private void gete(View v) {
        switch (v.getId()) {
            case R.id.layout_setting:
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_wodedingdan:
                intent.setClass(getActivity(),MyOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_qiyexinxi:
                intent.setClass(getActivity(), CompanyInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_gerenxinxi:
                intent.setClass(getActivity(), UserInfoActivity.class);
                startActivity(intent);
                break;
        }
    }

}
