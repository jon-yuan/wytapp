package com.babuwyt.documentary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.util.XUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/14.
 */

public class MsgCenterAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> mList;
    private ViewHolder holder;
    public MsgCenterAdapter(Context context){
        this.context=context;
        mList=new ArrayList<>();

    }
    public void setmList(ArrayList<String> list){
        if (list!=null){
            this.mList=list;
        }
    }

    @Override
    public int getCount() {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(this.context).inflate(R.layout.adapter_msgcenter_item,null);
            x.view().inject(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

        x.image().bind(holder.img_avatar, "http://www.qqjia.com/z/03/tu5465_18.jpg", XUtil.options(true));
        return view;
    }

    class ViewHolder{
        @ViewInject(R.id.img_avatar)
        ImageView img_avatar;
        @ViewInject(R.id.tv_msg)
        TextView tv_msg;
        @ViewInject(R.id.tv_time)
        TextView tv_time;
    }
}
