package com.babuwyt.documentary.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.entity.MainEntity;
import com.babuwyt.documentary.inteface.MainAdapterCallBack;
import com.babuwyt.documentary.util.XUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/13.
 */

public class MainAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MainEntity> mList;
    private ViewHolder holder;
    private MainAdapterCallBack callBack;
    private int type;
    public MainAdapter(Context context,int type) {
        this.context = context;
        this.mList = new ArrayList<MainEntity>();
        this.type=type;
    }

    public void setmList(ArrayList<MainEntity> list) {
        if (list != null) {
            this.mList = list;
        }
    }

    public void setCallBack(MainAdapterCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getCount() {
//        return 4;
        return this.mList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final MainEntity entity=mList.get(i);
        holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(this.context).inflate(R.layout.adapter_main_item, null);
            x.view().inject(holder, view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
//        x.image().bind(holder.image, "http://i0.hdslb.com/bfs/face/497620c688bf982738a32c1c00f078b7d198adb7.jpg", XUtil.options(true));
        holder.tv_name.setText(entity.getDriverName());
        holder.tv_yundan.setText(entity.getfSendCarNo());
        if (type==0){
            holder.img_icon.setVisibility(View.VISIBLE);
        }else {
            holder.img_icon.setVisibility(View.GONE);
        }
        switch (entity.getfTaskState()) {
            case "1":
                holder.tv_state.setText("装货已签到");
                break;
            case "2":
                holder.tv_state.setText("装货已拍照");
                break;
            case "3":
                holder.tv_state.setText("已装货（装货照片已审核）");
                break;
            case "4":
                holder.tv_state.setText("卸货已签到");
                break;
            case "5":
                holder.tv_state.setText("已卸货（签收单照片已审核）");
                break;
            case "6":
                holder.tv_state.setText("签收单已交回");
                break;
            case "7":
                holder.tv_state.setText("签收单已确认");
                break;
        }
        holder.btn_weizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.weizhiCallBack(i, entity);
            }
        });

        holder.btn_guiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.guijiCallBack(i, entity);
            }
        });
        holder.btn_examine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.examineCallBack(i, entity);
            }
        });
        holder.btn_qianshou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.qianshouCallBack(i, entity);
            }
        });
        holder.layout_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.detailsCallBack(i, entity);
            }
        });

        return view;
    }

    class ViewHolder {
        @ViewInject(R.id.tv_name)
        TextView tv_name;
        @ViewInject(R.id.tv_yundan)
        TextView tv_yundan;
        @ViewInject(R.id.tv_state)
        TextView tv_state;
        @ViewInject(R.id.image)
        ImageView image;
        @ViewInject(R.id.img_icon)
        ImageView img_icon;

        //底部四个button

        @ViewInject(R.id.btn_weizhi)
        LinearLayout btn_weizhi;
        @ViewInject(R.id.btn_guiji)
        LinearLayout btn_guiji;
        @ViewInject(R.id.btn_examine)
        LinearLayout btn_examine;
        @ViewInject(R.id.btn_qianshou)
        LinearLayout btn_qianshou;
        //详情页跳转layotu
        @ViewInject(R.id.layout_details)
        RelativeLayout layout_details;
    }
}
