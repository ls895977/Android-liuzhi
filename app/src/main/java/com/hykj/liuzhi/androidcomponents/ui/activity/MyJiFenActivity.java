package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hykj.liuzhi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyJiFenActivity extends BaseActivity {
    @BindView(R.id.tv_myjifen_back)
    ImageView tvMyjifenBack;
    @BindView(R.id.tv_myjifen_record)
    TextView tvMyjifenRecord;
    @BindView(R.id.tv_myjifen_getseed)
    TextView tvMyjifenGetseed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myjifen);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_myjifen_back, R.id.tv_myjifen_record, R.id.tv_myjifen_getseed})
    public void onViewClicked(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.tv_myjifen_back:
                finish();
                break;
            case R.id.tv_myjifen_record:
             intent=new Intent(MyJiFenActivity.this,JiFenRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_myjifen_getseed:
                intent=new Intent(MyJiFenActivity.this,HowToGetSeedActivity.class);
                startActivity(intent);
                break;
        }
    }
}
