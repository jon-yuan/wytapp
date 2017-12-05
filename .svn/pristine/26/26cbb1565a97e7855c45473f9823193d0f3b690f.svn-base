package com.babuwyt.consignor.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.adapter.AddressInfoAdapter;
import com.babuwyt.consignor.adapter.AddressListAdapter;
import com.babuwyt.consignor.base.BaseActivity;
import com.babuwyt.consignor.base.SessionManager;
import com.babuwyt.consignor.bean.AddressBean;
import com.babuwyt.consignor.bean.AddressListBean;
import com.babuwyt.consignor.entity.AddressEntity;
import com.babuwyt.consignor.finals.BaseURL;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.util.UHelper;
import com.babuwyt.jonylibrary.util.XUtil;
import com.babuwyt.jonylibrary.views.CustomListView;
import com.babuwyt.jonylibrary.views.dialog.PromptDialog;
import com.daimajia.swipe.util.Attributes;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/10.
 */
@ContentView(R.layout.activity_addressinfo)
public class AddressInfoActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    //    @ViewInject(R.id.listview)
//    CustomListView listview;
    @ViewInject(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ItemTouchHelper itemTouchHelper;
    private int dragFlags, swipeFlags;
    private AddressListAdapter adapter;

    private ArrayList<AddressEntity> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = (ArrayList<AddressEntity>) getIntent().getSerializableExtra("addressList");
        init();
    }

    private void init() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        mAdapter = new AddressInfoAdapter(this);
//        mAdapter.setMode(Attributes.Mode.Single);
        if (mList == null) {
            mList = new ArrayList<AddressEntity>();
        }
//        mAdapter.setmList(mList);
//        listview.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AddressListAdapter(this,mList);
        mRecyclerView.setAdapter(adapter);
        move();
        adapter.setDelete(new AddressInfoAdapter.Delete() {
            @SuppressLint("NewApi")
            @Override
            public void del(int position) {
                final AddressEntity entity = mList.get(position);
                PromptDialog dialog = new PromptDialog(AddressInfoActivity.this);
                dialog.setImg(R.drawable.icon_delete_order);
                dialog.setMsg(getString(R.string.del_address));
                dialog.setOnClick1(getString(R.string.cancal), new PromptDialog.Btn1OnClick() {
                    @Override
                    public void onClick() {

                    }
                });
                dialog.setOnClick2(getString(R.string.maketrue), new PromptDialog.Btn2OnClick() {
                    @Override
                    public void onClick() {
                        mList.remove(entity);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.create();
                dialog.showDialog();
            }
        });
        adapter.setEdit(new AddressInfoAdapter.Edit() {
            @Override
            public void edit(int position) {
                Intent intent = new Intent();
                intent.setClass(AddressInfoActivity.this, AddAddressActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("AddressEntity", mList.get(position));
                intent.putExtra("type", "edit");
                startActivityForResult(intent, 0);
            }
        });
    }
    private void move(){
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP |
                            ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT |
                            ItemTouchHelper.RIGHT;
                    swipeFlags = 0;

                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    dragFlags = ItemTouchHelper.UP |
                            ItemTouchHelper.DOWN;
                    swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }


                //return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mList, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mList, i, i - 1);
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                //return super.isLongPressDragEnabled();
                return true;
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    @Event(value = {R.id.tv_address, R.id.btn_mk})
    private void getE(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
                Intent intent = new Intent();
                intent.setClass(this, AddAddressActivity.class);
                intent.putExtra("type", "add");
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_mk:
                isTrue();
                break;
        }
    }

    /**
     * 判断地址是否有效
     * 顺序  第一个必须是提货地 最后一个必须是卸货地 并且提货地和卸货地必须同时都存在
     */
    private void isTrue() {
        if (mList == null || mList.size() > 2) {
            UHelper.showToast(this, getString(R.string.address_formpt));
            return;
        }
        if (!mList.get(0).getAddressType().equalsIgnoreCase("1") || !mList.get(mList.size() - 1).getAddressType().equalsIgnoreCase("2")) {
            UHelper.showToast(this, getString(R.string.address_order_is_fail));
            return;
        }
        Intent result = new Intent();
        result.putExtra("addresslist", mList);
        setResult(AddOrderActivity.ADDRESS_TYPE_RESULT, result);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            if (resultCode == 1) {
                mList.add((AddressEntity) data.getSerializableExtra("AddressEntity"));
//                adapter.setmList(mList);
                adapter.notifyDataSetChanged();
            }
            if (resultCode == 2) {
                int position = data.getIntExtra("position", -1);
                AddressEntity entity = (AddressEntity) data.getSerializableExtra("AddressEntity");
                if (position != -1 && entity != null) {
                    AddressEntity entity1 = mList.get(position);
                    entity1.setAddressType(entity.getAddressType());
                    entity1.setAreaName(entity.getAreaName());
                    entity1.setProvinceName(entity.getProvinceName());
                    entity1.setCityName(entity.getCityName());
                    entity1.setContactName(entity.getContactName());
                    entity1.setContactPhone(entity.getContactPhone());
                    entity1.setAreaId(entity.getAreaId());
                    entity1.setAddressDetails(entity.getAddressDetails());
                    adapter.notifyDataSetChanged();
                }
                mList.get(data.getIntExtra("position", -1));
            }
        }
    }

    public static <T> ArrayList<T> indexExChange(ArrayList<T> list, int index1, int index2) {
        T t = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, t);
        return list;
    }
}
