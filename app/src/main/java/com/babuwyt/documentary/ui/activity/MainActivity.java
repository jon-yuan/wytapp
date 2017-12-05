package com.babuwyt.documentary.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.adapter.MainPagerAdapter;
import com.babuwyt.documentary.base.BaseActivity;
import com.babuwyt.documentary.base.SessionManager;
import com.babuwyt.documentary.bean.VersionBean;
import com.babuwyt.documentary.entity.UserInfoEntity;
import com.babuwyt.documentary.entity.VersionEntity;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.inteface.MyProgressCallBack;
import com.babuwyt.jonylibrary.util.DensityUtils;
import com.babuwyt.jonylibrary.util.UHelper;
import com.babuwyt.jonylibrary.util.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import jpush.LocalBroadcastManager;
import jpush.Util;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.relayout_msg)
    RelativeLayout relayout_msg;
    @ViewInject(R.id.tv_msgnum)
    TextView tv_msgnum;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.tabLayout)
    TabLayout tabLayout;
    @ViewInject(R.id.viewPager)
    ViewPager viewPager;
    private Intent intent;
    /**
     * 侧滑页面
     */
    @ViewInject(R.id.nav_view)
    NavigationView nav_view;
    @ViewInject(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    LinearLayout layout_der;
    private ImageView img_setting;
    private TextView tv_user;
    private TextView tv_phone;
    private TextView tv_dizhi;
    private TextView tv_gongsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initCH();
        initTabLayout();
        registerMessageReceiver();
        getVersion();
    }

    private void init() {

        intent = new Intent();
        toolbar.setTitle(getString(R.string.zuoyegenzong));
        toolbar.setNavigationIcon(R.drawable.icon_persion_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(GravityCompat.START);
            }
        });
    }

    /**
     * 初始化侧滑页面
     */
    private void initCH() {
        View headerView = nav_view.getHeaderView(0);
        layout_der = headerView.findViewById(R.id.layout_der);
        //获取状态栏高度
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        int height = DensityUtils.deviceHeightPX(this) - statusBarHeight1;
        layout_der.getLayoutParams().height = height;
        img_setting = headerView.findViewById(R.id.img_setting);
        tv_user = headerView.findViewById(R.id.tv_user);
        tv_phone = headerView.findViewById(R.id.tv_phone);
        tv_dizhi = headerView.findViewById(R.id.tv_dizhi);
        tv_gongsi = headerView.findViewById(R.id.tv_gongsi);


        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        setData();
    }

    /**
     * 设置数据
     */
    private void setData() {
        if (SessionManager.getInstance().getUser() == null) {
            return;
        }
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        tv_user.setText(userInfoEntity.getFloginname());
        tv_phone.setText(userInfoEntity.getFscenename());
        tv_dizhi.setText(userInfoEntity.getFaddress());
        tv_gongsi.setText(userInfoEntity.getFchinesename());
    }

    /**
     * 设置viewpager 与 tablayout
     */
    public void initTabLayout() {
        tabLayout.setTabMode(TabLayout.GRAVITY_CENTER);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), this));
        //设置两者同步
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 极光推送
     */
    public static boolean isForeground = false;
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!Util.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void initJpsh() {
        JPushInterface.init(getApplicationContext());
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    /**
     * 点击两次退出应用
     * 通过记录按键时间计算时间差实现
     */
    long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START);
            } else {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    Toast.makeText(MainActivity.this, getString(R.string.onclickisexit), Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
            }
        }
        return false;
    }


    /**
     * 检测版本信息
     */

    private void getVersion() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(2 + "");
        XUtil.GetPing(BaseURL.CHECKVERSION, list, new MyCallBack<VersionBean>() {
            @Override
            public void onSuccess(VersionBean o) {
                UHelper.showToast(MainActivity.this, "成功");
                if (o.isSuccess()) {
                    setVersion(o.getObj());
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("=ex====", ex + "");
            }
        });
    }
    private void setVersion(final VersionEntity entity) {
        String vsersionCode=UHelper.getAppVersionInfo(this,UHelper.TYPE_VERSION_CODE);
        if (entity.getFversion()>Integer.parseInt(vsersionCode)){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("发现新版本");
            builder.setMessage(entity.getFupdateinfo());
            builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    DownLoadFile(entity.getFurl());
                }
            });
            if (entity.getFisforceupdate()){
                builder.setCancelable(false);
            }else {
                builder.setCancelable(true);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
            }
            builder.create().show();
        }
    }
    /**
     * 下载现版本APP
     */
    private File filepath;
    private void DownLoadFile(String url) {
        UHelper.showToast(this,"下载apk");
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // 获取SD卡的目录
            String path=Environment.getExternalStorageDirectory().getPath();
            filepath = new File(path + File.separator + "apk" + File.separator + "XianChang.apk");//仅创建路径的File对象
            if (!filepath.exists()) {
                filepath.mkdir();//如果路径不存在就先创建路径
            }
        }
        // 准备进度条Progress弹窗
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setTitle("下载中...");
        //Progress弹窗设置为水平进度条
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条

            //"http://acj3.pc6.com/pc6_soure/2017-8/me.ele_190.apk"
        XUtil.DownLoadFile(url, filepath.getPath().toString(), new MyProgressCallBack<File>() {
            @Override
            public void Started() {
                dialog.show();
            }
            @Override
            public void Success(File o) {
                dialog.dismiss();
                installAPK();
            }
            @Override
            public void Loading(long total, long current, boolean isDownloading) {
                dialog.setMax((int) total);
                dialog.setProgress((int) current);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                dialog.dismiss();
                Log.d("==ex==",ex+"");
            }
        });

    }

    private void installAPK() {
        //系统应用界面，安装apk入口，看源码
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.setData(Uri.fromFile(file));
//        intent.setType("application/vnd.android.package-archive");

        //切记当要同时配Data和Type时一定要用这个方法，否则会出错
        intent.setDataAndType(Uri.fromFile(filepath),"application/vnd.android.package-archive");

        startActivityForResult(intent,0);
    }


}
