package com.babuwyt.documentary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.util.DensityUtils;
import com.babuwyt.documentary.util.XUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/14.
 */

public class LoadExamineAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> mList;
    private ViewHolder holder;

    public LoadExamineAdapter(Context context){
        this.context=context;
        mList=new ArrayList<String>();
    }
    public void setmList(ArrayList<String> list){
        if (list!=null){
            mList=list;
        }
    }

    @Override
    public int getCount() {
        return mList.size();
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
            view= LayoutInflater.from(context).inflate(R.layout.adapter_loadexamine_item,null);
            x.view().inject(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        int sWidth=DensityUtils.deviceWidthPX(context);
        int space=DensityUtils.dip2px(context,(float) 47);
        holder.image.getLayoutParams().height=(sWidth-space)/3-DensityUtils.dip2px(context,(float) 10);
        holder.image.getLayoutParams().width=(sWidth-space)/3-DensityUtils.dip2px(context,(float) 10);
        holder.re_layout.getLayoutParams().height=(sWidth-space)/3;
        holder.re_layout.getLayoutParams().width=(sWidth-space)/3;
        x.image().bind(holder.image, BaseURL.BASE_IMAGE_URI+mList.get(i), XUtil.options(false));
        return view;
    }
    class ViewHolder{
        @ViewInject(R.id.image)
        ImageView image;
        @ViewInject(R.id.re_layout)
        RelativeLayout re_layout;
    }

}
