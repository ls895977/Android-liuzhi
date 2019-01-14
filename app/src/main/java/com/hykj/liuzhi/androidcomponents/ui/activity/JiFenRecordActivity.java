package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.JiFenRecordBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.JiFenRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiFenRecordActivity extends BaseActivity {
    @BindView(R.id.iv_jifen_record_back)
    ImageView ivJifenRecordBack;
    @BindView(R.id.recycler_jifen_record)
    RecyclerView recyclerJifenRecord;
    private List<JiFenRecordBean> mBeanList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifefn_record);
        ButterKnife.bind(this);
        initView();
        initData();


    }

    private void initData() {
        for (int i = 0; i < 15; i++) {
            JiFenRecordBean jiFenRecordBean = new JiFenRecordBean();
            jiFenRecordBean.setJifentime("2018年10月20日");
            jiFenRecordBean.setJifentitle("打赏记录");
            jiFenRecordBean.setJifencount("+120");
            mBeanList.add(jiFenRecordBean);
            JiFenRecordAdapter loginRecordAdapter = new JiFenRecordAdapter(R.layout.item_jifen_record, mBeanList);
            recyclerJifenRecord.setAdapter(loginRecordAdapter);

        }
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerJifenRecord.setLayoutManager(layoutManager);
    }

    @OnClick({R.id.iv_jifen_record_back, R.id.recycler_jifen_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_jifen_record_back:
                finish();
                break;
            case R.id.recycler_jifen_record:
                break;
        }
    }
}
