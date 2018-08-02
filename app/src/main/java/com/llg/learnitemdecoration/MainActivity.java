package com.llg.learnitemdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.llg.learnitemdecoration.adapter.RecyclerViewAdapter;
import com.llg.learnitemdecoration.decoration.TestDecoration;
import com.llg.learnitemdecoration.model.FileItemData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    TestDecoration mDecoration;
    RecyclerViewAdapter mViewAdapter;

    List<FileItemData> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv);
        mViewAdapter = new RecyclerViewAdapter(this);

        mDecoration = new TestDecoration(mViewAdapter);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setAdapter(mViewAdapter);
        mRecyclerView.setLayoutManager(layoutmanager);
        mRecyclerView.addItemDecoration(mDecoration);

        StickyRecyclerHeadersTouchListener listener = new StickyRecyclerHeadersTouchListener(this,mRecyclerView,mDecoration);
        mRecyclerView.addOnItemTouchListener(listener);


        initDatas();
        mViewAdapter.setData(datas);
        mViewAdapter.notifyDataSetChanged();
    }

    /**初始化测试数据*/
    private void initDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 56;i++) {
            FileItemData itemData = new FileItemData("filename"+i,"filepath"+i);
            if (i<10){
                itemData.setCreateDate("18-02-02");
            }else if (i<20){
                itemData.setCreateDate("18-03-03");
            }else if (i<30){
                itemData.setCreateDate("18-04-04");
            }else if (i<40){
                itemData.setCreateDate("18-05-05");
            }else {
                itemData.setCreateDate("18-06-06");
            }
            datas.add(itemData);
        }
        datas.get(3);
    }
}
