package com.babuwyt.documentary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.documentary.R;
import com.babuwyt.documentary.entity.SignPicEntity;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.inteface.SignPicAdapterCallBack;
import com.babuwyt.documentary.util.DensityUtils;
import com.babuwyt.jonylibrary.util.XUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/8.
 */

public class SignPicAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SignPicEntity> mList;
    private ViewHolder holder;
    private SignPicAdapterCallBack mCallBack;
    public SignPicAdapter(Context c){
        context=c;
        mList=new ArrayList<SignPicEntity>();
    }
    public void setmList(ArrayList<SignPicEntity> list){
        if (list!=null){
            mList=list;
        }
    }
    public void setSignPicAdapterCallBack(SignPicAdapterCallBack callBack){
        mCallBack=callBack;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view==null){
            holder=new SignPicAdapter.ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.adapter_signadapter_item,null);
            x.view().inject(holder,view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

        int sWidth= DensityUtils.deviceWidthPX(context);
        int image_space=DensityUtils.dip2px(context,(float) 40);
        int space=DensityUtils.dip2px(context,(float) 52);
        holder.image.getLayoutParams().width=(sWidth-space)/3;
        holder.image.getLayoutParams().height=(sWidth-space)/3;
        holder.layout_bg.getLayoutParams().width=(sWidth-image_space)/3;
        x.image().bind(holder.image, BaseURL.BASE_IMAGE_URI+mList.get(i).getFpicture());
        holder.tv_num.setText("单号:"+mList.get(i).getfSignNo());
        if (mList.get(i).isIserror()){
            holder.img_check.setImageResource(R.drawable.yuan_true);
        }else {
            holder.img_check.setImageResource(R.drawable.yuan_false);
        }
        holder.layout_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.CallBack(i);
            }
        });
        return view;
    }
    class ViewHolder{
        @ViewInject(R.id.img_check)
        ImageView img_check;
        @ViewInject(R.id.image)
        ImageView image;
        @ViewInject(R.id.layout_select)
        LinearLayout layout_select;
        @ViewInject(R.id.tv_num)
        TextView tv_num;
        @ViewInject(R.id.layout_bg)
        LinearLayout layout_bg;
    }
}
