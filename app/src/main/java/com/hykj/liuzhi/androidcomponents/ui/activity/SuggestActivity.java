package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuggestActivity extends BaseActivity {
    @BindView(R.id.iv_suggest_back)
    ImageView ivSuggestBack;
    @BindView(R.id.tv_suggest_commit)
    TextView tvSuggestCommit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_suggest_back, R.id.tv_suggest_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_suggest_back:
                finish();
                break;
            case R.id.tv_suggest_commit:
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

