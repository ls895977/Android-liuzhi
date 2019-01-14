package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.MessageEvent;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.seacrch.HomeSeacrchPagerAdapter;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 搜索页面
 *
 * @author: lujialei
 * @date: 2018/10/22
 * @describe:
 */
public class HomeSearchActivity extends BaseActivity {
    @BindView(R.id.tv_homesearch_cancel)
    TextView tvHomesearchCancel;
    @BindView(R.id.et_homesearch_input)
    EditText input;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        ButterKnife.bind(this);
//        backHistoryData();
        initView();
    }

    public void initView() {
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String stSerch = input.getText().toString();
                if (stSerch.length() > 0) {
                    findViewById(R.id.homeSearchHistory).setVisibility(View.GONE);
                    findViewById(R.id.ll_video).setVisibility(View.VISIBLE);
                    EventBus.getDefault().post(new MessageEvent(input.getText().toString()));
                } else {
                    findViewById(R.id.homeSearchHistory).setVisibility(View.VISIBLE);
                    findViewById(R.id.ll_video).setVisibility(View.GONE);
                }
            }
        });
        viewPager.setAdapter(new HomeSeacrchPagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);
    }

    @OnClick(R.id.tv_homesearch_cancel)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_homesearch_cancel:
                finish();
                break;
        }
    }
    /**
     * 搜索历史
     */
    public void backHistoryData() {
        HttpHelper.getuserselecthistory(1, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(HomeSearchActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
//                entity = FastJSONHelper.getPerson(succeed, FashionBean.class);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(HomeSearchActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
