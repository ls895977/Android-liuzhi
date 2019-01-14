package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.bean.LoGinRecordBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.LoginRecordAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.CustomLoadMoreView;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginRecordActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycler_login_record)
    RecyclerView recyclerLoginRecord;
    @BindView(R.id.iv_loginrecord_back)
    ImageView ivLoginrecordBack;
    private List< LoGinRecordBean.DataBean.ArrayBean> mBeanList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginrecord);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerLoginRecord.setLayoutManager(layoutManager);

    }
    private void initData() {
        backData();
    }


    @OnClick(R.id.iv_loginrecord_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_loginrecord_back:
                finish();
                break;
        }

    }

    LoGinRecordBean entity;
    int page = 1;
    LoginRecordAdapter loginRecordAdapter;
    ACache aCache;

    public void backData() {
        aCache = ACache.get(this);
        HttpHelper.getlogonrecord(aCache.getAsString("user_id"), page + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(LoginRecordActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, LoGinRecordBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                if(entity.getData().getArray().size()==0){
                    loginRecordAdapter.loadMoreComplete();
                    return;
                }
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    LoGinRecordBean.DataBean.ArrayBean bean = entity.getData().getArray().get(i);
                    mBeanList.add(bean);
                }
                if (loginRecordAdapter == null) {
                    loginRecordAdapter = new LoginRecordAdapter(R.layout.item_login_record, mBeanList);
                    loginRecordAdapter.setLoadMoreView(new CustomLoadMoreView());
                    loginRecordAdapter.setOnLoadMoreListener(LoginRecordActivity.this, recyclerLoginRecord);
                    recyclerLoginRecord.setAdapter(loginRecordAdapter);
                    loginRecordAdapter.loadMoreComplete();
                } else {
                    loginRecordAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(LoginRecordActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        recyclerLoginRecord.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                backData();
                loginRecordAdapter.loadMoreComplete();
            }
        }, 2000);
    }
}
