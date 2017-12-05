package com.babuwyt.consignor.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.entity.OrderEntity;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/10.
 */
//R.layout.adapter_releaseorder
public class ReleaseOrderAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<ArrayList<OrderEntity>> map;

    public ReleaseOrderAdapter(Context context) {
        mContext = context;
        map = new ArrayList<ArrayList<OrderEntity>>();
    }

    public void setMap(ArrayList<ArrayList<OrderEntity>> map) {
        if (map != null) {
            this.map = map;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return map.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return map.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return map.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        GroupHolder holder = null;
        if (view == null) {
            holder = new GroupHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_releaseorder_group, null);
            holder.jiantou = view.findViewById(R.id.jiantou);
            holder.num = view.findViewById(R.id.num);
            holder.tv_group = view.findViewById(R.id.tv_group);
            view.setTag(holder);
        } else {
            holder = (GroupHolder) view.getTag();
        }
        holder.jiantou.setImageResource(R.drawable.icon_jiantou_up);
        if (!b) {
            holder.jiantou.setImageResource(R.drawable.icon_jiantou_down);
        }
        switch (i) {
            case 0:
                holder.tv_group.setText("未发布");
                break;
            case 1:
                holder.tv_group.setText("已发布");
                break;
            case 2:
                holder.tv_group.setText("已拒绝");
                break;
        }
        holder.num.setText("("+map.get(i).size()+")");
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
//        if (i1==2||i1==3){
//            return null;
//        }
        OrderEntity orderEntity = map.get(i).get(i1);

        ChildHolder holder = null;
        if (view == null) {
            holder = new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_releaseorder, null);
            holder.image_delete = view.findViewById(R.id.image_delete);
            holder.layout_xiugai = view.findViewById(R.id.layout_xiugai);
            holder.layout_fabu = view.findViewById(R.id.layout_fabu);
            holder.tv_creattime = view.findViewById(R.id.tv_creattime);
            holder.tv_form = view.findViewById(R.id.tv_form);
            holder.tv_to = view.findViewById(R.id.tv_to);
            holder.tv_chexing = view.findViewById(R.id.tv_chexing);
            holder.tv_huowu = view.findViewById(R.id.tv_huowu);
            holder.tv_orderno = view.findViewById(R.id.tv_orderno);
            holder.tv_time = view.findViewById(R.id.tv_time);
            holder.layout_btn = view.findViewById(R.id.layout_btn);
            holder.tv_daodatime = view.findViewById(R.id.tv_daodatime);
            view.setTag(holder);
        } else {
            holder = (ChildHolder) view.getTag();
        }
        if (TextUtils.isEmpty(orderEntity.getOrderNumber())){
            holder.tv_orderno.setText(orderEntity.getOrderSysNumber());
        }else {
            holder.tv_orderno.setText(orderEntity.getOrderNumber());
        }
        holder.tv_creattime.setText(orderEntity.getCreatetime()==null ? "" : orderEntity.getCreatetime());
        holder.tv_form.setText(orderEntity.getRouteFrom()==null ? "" : orderEntity.getRouteFrom());
        holder.tv_to.setText(orderEntity.getRouteTo()==null ? "" : orderEntity.getRouteTo());
        holder.tv_chexing.setText(orderEntity.getCarType()==null ? "" : orderEntity.getCarType());
        holder.tv_huowu.setText(orderEntity.getGoodsName()==null ? "" : orderEntity.getGoodsName());
        holder.tv_time.setText(orderEntity.getExtractTime()==null ? "" : orderEntity.getExtractTime());
        holder.tv_daodatime.setText(orderEntity.getFarrivetime()==null ? "" : orderEntity.getFarrivetime());
        if (i==1){
            holder.layout_btn.setVisibility(View.GONE);
            holder.image_delete.setVisibility(View.GONE);
        }else {
            holder.layout_btn.setVisibility(View.VISIBLE);
            holder.image_delete.setVisibility(View.VISIBLE);
        }

        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack!=null){
                    callBack.delete(i,i1);
                }
            }
        });
        holder.layout_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack!=null){
                    callBack.fabu(i,i1);
                }
            }
        });
        holder.layout_xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack!=null){
                    callBack.xiugai(i,i1);
                }
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class ChildHolder {
        ImageView image_delete;
        TextView tv_creattime,tv_daodatime, tv_form, tv_to, tv_chexing, tv_huowu, tv_time,tv_orderno;
        LinearLayout layout_btn,layout_xiugai,layout_fabu;
    }

    class GroupHolder {
        ImageView jiantou;
        TextView num;
        TextView tv_group;

    }

    public interface CallBack{
        void delete(int groupPosition, int childPosition);
        void xiugai(int groupPosition, int childPosition);
        void fabu(int groupPosition, int childPosition);
    }
    private CallBack callBack;
    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }
}
