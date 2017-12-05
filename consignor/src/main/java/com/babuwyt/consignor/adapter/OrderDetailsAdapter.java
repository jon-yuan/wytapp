package com.babuwyt.consignor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.finals.BaseURL;
import com.babuwyt.jonylibrary.util.DensityUtils;
import com.babuwyt.jonylibrary.util.XUtil;
import com.babuwyt.jonylibrary.views.CustomGridView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/14.
 */

public class OrderDetailsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> mList;
    private ViewHolder holder;
    private boolean isShow;
    public OrderDetailsAdapter(Context context){
        this.context=context;
        mList=new ArrayList<String>();
    }
    public void setmList(ArrayList<String> list){
        if (list!=null){
            mList=list;
        }
    }
    public void showView(boolean b ,CustomGridView c){
        isShow=b;
        if (b){
            c.setPadding(0, DensityUtils.dip2px(context,10f),0,0);
        }else {
            c.setPadding(0,0,0,0);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return isShow == true ? mList.size() : 0;
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
            view= LayoutInflater.from(context).inflate(R.layout.adapter_orderdetalis_item,null);
            x.view().inject(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        int sWidth=DensityUtils.deviceWidthPX(context);
        int space=DensityUtils.dip2px(context,(float) 55);
        holder.image.getLayoutParams().height=(sWidth-space)/4;
        holder.image.getLayoutParams().width=(sWidth-space)/4;

//        x.image().bind(holder.image, BaseURL.BASE_IMAGE_URI+mList.get(i), XUtil.options(false));
        x.image().bind(holder.image, BaseURL.BASE_IMAGE_URI+mList.get(i), XUtil.options(false));
        return view;
    }
    class ViewHolder{
        @ViewInject(R.id.image)
        ImageView image;
    }

}
