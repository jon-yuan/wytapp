package com.babuwyt.consignor.ui.activity;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.babuwyt.consignor.R;
import com.babuwyt.consignor.base.BaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lenovo on 2017/9/15.
 */

public class TestActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private ItemTouchHelper itemTouchHelper;
    private int dragFlags, swipeFlags;
    private NormalRecyclerViewAdapter adapter;
    private ArrayList<String> mDatas = new ArrayList<>();

    private TextView test;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        test=findViewById(R.id.test);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        mDatas.add("啦啦啦啦");
        mDatas.add("哈哈哈");
        mDatas.add("你是谁");
        mDatas.add("怎么会这样");
        mDatas.add("这可如何是好");
        adapter = new NormalRecyclerViewAdapter(this, mDatas);
        mRecyclerView.setAdapter(adapter);
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
                        Collections.swap(mDatas, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mDatas, i, i - 1);
                    }
                }
                Log.d("测试",new Gson().toJson(mDatas));
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
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("测试",new Gson().toJson(mDatas));
            }
        });
    }


    class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {

        private final LayoutInflater mLayoutInflater;
        private final Context mContext;

        private ArrayList<String> mList;

        public NormalRecyclerViewAdapter(Context context, ArrayList<String> list) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
            mList = list;
        }

        @Override
        public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        }

        @Override
        public void onBindViewHolder(NormalTextViewHolder holder, int position) {
            holder.mTextView.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        public class NormalTextViewHolder extends RecyclerView.ViewHolder {
            private
            TextView mTextView;
            NormalTextViewHolder(View view) {
                super(view);
                mTextView = (TextView) view.findViewById(R.id.tv_name);
            }
        }
    }
}
