package com.llg.learnitemdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.llg.learnitemdecoration.adapter.RecyclerViewAdapter;
import com.llg.learnitemdecoration.decoration.TestDecoration;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    TestDecoration mDecoration;
    RecyclerViewAdapter mViewAdapter;

    public static final String[] datas ={"test->1","test->2","test->3","test->4","test-5","test->1","test->2","test->3","test->4","test-5","test->1","test->2","test->3","test->4","test-5","test->1","test->2","test->3","test->4","test-5","test->1","test->2","test->3","test->4","test-5","test->1","test->2","test->3","test->4","test-5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv);
        mViewAdapter = new RecyclerViewAdapter(this);
        mDecoration = new TestDecoration(mViewAdapter);

        mRecyclerView.setAdapter(mViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(mDecoration);
        mViewAdapter.setData(Arrays.asList(datas));
        mViewAdapter.notifyDataSetChanged();

       // StickyRecyclerHeadersTouchListener listener = new StickyRecyclerHeadersTouchListener(mRecyclerView,mDecoration);
       // mRecyclerView.addOnItemTouchListener(listener);
    }
}
