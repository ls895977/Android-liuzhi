package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.hykj.liuzhi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HowToGetSeedActivity extends BaseActivity {
    @BindView(R.id.iv_howtoget_seed_back)
    ImageView ivHowtogetSeedBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtoget_seed);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_howtoget_seed_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_howtoget_seed_back:
                finish();
                break;
        }
    }
}
