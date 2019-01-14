package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.adapter.OffLineVideoAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OffLineVideoActivity extends BaseActivity {
    @BindView(R.id.tv_offline_video_back)
    ImageView tvOfflineVideoBack;
    @BindView(R.id.tv_offline_video_edit)
    TextView tvOfflineVideoEdit;
    @BindView(R.id.recycler_offline_video)
    RecyclerView recyclerOfflineVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offlone_video);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initData() {
        recyclerOfflineVideo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        OffLineVideoAdapter adapter = new OffLineVideoAdapter(list);
        recyclerOfflineVideo.setAdapter(adapter);
    }

    private void initView() {
        recyclerOfflineVideo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @OnClick({R.id.tv_offline_video_back, R.id.tv_offline_video_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_offline_video_back:
                finish();
                break;
            case R.id.tv_offline_video_edit:
                Toast.makeText(this, "编辑离线视频", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
