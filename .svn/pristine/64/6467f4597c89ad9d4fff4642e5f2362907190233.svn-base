package com.babuwyt.consignor.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.base.BaseFragment;
import com.babuwyt.consignor.bean.Sign;
import com.babuwyt.consignor.entity.SignEntity;
import com.babuwyt.consignor.entity.UserInfoEntity;
import com.babuwyt.consignor.finals.BaseURL;
import com.babuwyt.consignor.finals.Constant;
import com.babuwyt.consignor.ui.activity.PhotoActivity;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.util.CameraUtils;
import com.babuwyt.jonylibrary.util.XUtil;
import com.babuwyt.jonylibrary.views.dialog.ImgCheckDialog;
import com.babuwyt.jonylibrary.views.dialog.LoadingDialog;
import com.tencent.cos.model.COSRequest;
import com.tencent.cos.model.COSResult;
import com.tencent.cos.model.PutObjectResult;
import com.tencent.cos.task.listener.IUploadTaskListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;

/**
 * Created by lenovo on 2017/9/6.
 */
@ContentView(R.layout.fragment_aompanyinfoauth)
public class CompanyInfoAuthFragment extends BaseFragment {
    @ViewInject(R.id.tv_companyname)
    EditText tv_companyname;
    @ViewInject(R.id.tv_companycode)
    EditText tv_companycode;
    @ViewInject(R.id.tv_companyperson)
    EditText tv_companyperson;
    @ViewInject(R.id.img)
    ImageView img;

    private String srcPath = "";//本地文件的绝对路径
    private String cosPath = "";//
    private String videoPath = null;
    private SignEntity sign;
    private LoadingDialog dialog;
    private UserInfoEntity entity;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public UserInfoEntity getCompanyData() {
        entity = new UserInfoEntity();
        entity.setCompanyName(tv_companyname.getText().toString());
        entity.setCompanyCode(tv_companycode.getText().toString());
        entity.setCompanyPerson(tv_companyperson.getText().toString());
        entity.setLicenseImg("shipper/"+cosPath);
        return entity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dialog = new LoadingDialog(getActivity());
    }

    @Event(value = {R.id.img})
    private void gete(View v) {
        switch (v.getId()) {
            case R.id.img:
                showDialog();
                break;
        }
    }

    @SuppressLint("NewApi")
    private void showDialog() {
        ImgCheckDialog dialog = new ImgCheckDialog(getActivity());
        dialog.setCallBackPaizhao(new ImgCheckDialog.CallBackPaizhao() {
            @Override
            public void callbackPaizhao() {
                startCamera();
            }
        });
        dialog.setCallBackXiangce(new ImgCheckDialog.CallBackXiangce() {
            @Override
            public void callbackXiangce() {
                startPhoto();
            }
        });
        dialog.create();
        dialog.showDialog();
    }

    /**
     * 启动相机
     */


    public void startCamera() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, 2);

    }

    private void startPhoto() {
        Intent intent=new Intent(getActivity(), PhotoActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 2) {
                if (data != null) {
                    // 取得返回的Uri,基本上选择照片的时候返回的是以Uri形式，但是在拍照中有得机子呢Uri是空的，所以要特别注意
                    Uri uri = data.getData();
                    /*
                     * 返回的Uri不为空时，那么图片信息数据都会在Uri中获得。如果为空，那么我们就进行下面的方式获取
                     * 拍照后保存到相册的手机
                     */
                    if (uri != null) {
                        Cursor cursor = getActivity().getContentResolver().query(uri, null,
                                null, null, null);
                        if (cursor.moveToFirst()) {
                            videoPath = cursor.getString(cursor.getColumnIndex("_data"));// 获取绝对路径
                            getPath(srcPath);
                        }
                    }
                    //小米等 拍照后不保存的手机
                    else {
                        Bitmap bm = (Bitmap) data.getExtras().get("data");
                        srcPath = CameraUtils.getPath(bm);
                        getPath(srcPath);
                    }
                }
            }
            if (requestCode == 1) {
                final String path = data.getStringExtra("PHOTO");
                getPath(path);
            }
        }
    }

    //获取到照片地址 进行压缩后上传
    private void getPath(String path) {
        File compressedImageFile = null;
        try {
            compressedImageFile = new Compressor(getActivity()).compressToFile(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        srcPath = compressedImageFile.getPath();
        Next(srcPath);
    }

    //上传图片
    private void Next(String srcPath) {
        dialog.showDialog();
        SignEntity signEntity = Constant.getSign(getActivity());
        if (signEntity != null && Constant.isOld(signEntity)) {
            this.sign = signEntity;
            upload(srcPath);
        } else {
            getSigns(srcPath);
        }

    }

    //请求服务器获取签名
    private void getSigns(final String srcPath) {
        XUtil.GetPing(BaseURL.GETSIGN, null, new MyCallBack<Sign>() {
            @Override
            public void onSuccess(Sign result) {
                super.onSuccess(result);
                if (result.isSuccess()) {
                    SignEntity entity = new SignEntity();
                    entity.setSign(result.getObj());
                    long time = System.currentTimeMillis() / 1000;
                    entity.setTime(time);
                    Constant.saveSign(getActivity(), entity);
                    sign = entity;
                    upload(srcPath);
                }
            }
        });
    }

    private void upload(String srcPath) {
        cosPath = "shipper/" + "wyt"+System.currentTimeMillis()/1000+".jpg";
        dialog.showDialog();
        Constant.upload(getActivity(), srcPath,cosPath, sign.getSign(), new IUploadTaskListener() {
            @Override
            public void onProgress(COSRequest cosRequest, long l, long l1) {
            }

            @Override
            public void onCancel(COSRequest cosRequest, COSResult cosResult) {
                dialog.dissDialog();
                PutObjectResult result = (PutObjectResult) cosResult;
            }

            @Override
            public void onSuccess(COSRequest cosRequest, COSResult cosResult) {
                dialog.dissDialog();
                PutObjectResult result = (PutObjectResult) cosResult;
                if (result != null) {
                    x.image().bind(img, BaseURL.BASE_IMAGE_URI+cosPath);
                }
            }

            @Override
            public void onFailed(COSRequest cosRequest, COSResult cosResult) {
                dialog.dissDialog();
            }
        });
    }
}
