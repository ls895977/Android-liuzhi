package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.DeleteselecthistoryBean;
import com.hykj.liuzhi.androidcomponents.bean.UserselecthistoryBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.MessageEvent;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.seacrch.HomeSeacrchPagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.widget.FindSearchLayout;
import com.hykj.liuzhi.androidcomponents.ui.widget.HistorySearchLayout;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

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
public class HomeSearchActivity extends BaseActivity implements HistorySearchLayout.onClick {

    @BindView(R.id.tv_homesearch_cancel)
    TextView tvHomesearchCancel;
    @BindView(R.id.et_homesearch_input)
    EditText input;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.search_history)
    HistorySearchLayout topUser;
    @BindView(R.id.search_find)
    FindSearchLayout topShop;
    @BindView(R.id.homeSearchHistory)
    LinearLayout homeSearchHistory;
    @BindView(R.id.ll_video)
    LinearLayout ll_video;
    private ACache aCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        ButterKnife.bind(this);
        backHistoryData();
        backselecthistory();
        initView();
    }

    public void initView() {
        aCache = ACache.get(this);
        topShop.setBackData(new FindSearchLayout.BackData() {
            @Override
            public void onBack(String name) {
                input.setText(name);
            }
        });
        viewPager.setAdapter(new HomeSeacrchPagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);
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
                if (TextUtils.isEmpty(stSerch)) {
                    return;
                }
                if (stSerch.length() > 0) {
                    homeSearchHistory.setVisibility(View.GONE);
                    ll_video.setVisibility(View.VISIBLE);
                    EventBus.getDefault().post(new MessageEvent(stSerch));
                } else {
                    backHistoryData();
                    backselecthistory();
                    homeSearchHistory.setVisibility(View.VISIBLE);
                    ll_video.setVisibility(View.GONE);
                }
            }
        });
        homeSearchHistory.setVisibility(View.VISIBLE);
        ll_video.setVisibility(View.GONE);
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
                UserselecthistoryBean entity = FastJSONHelper.getPerson(succeed, UserselecthistoryBean.class);
                List<String> stList = new ArrayList<>();
                for (int i = (entity.getData().getArray().size() - 1); i >= 0; i--) {
                    stList.add(entity.getData().getArray().get(i).getSelecthistory_name());
                }
                topUser.setData(stList, HomeSearchActivity.this);
                topUser.setOnClick(HomeSearchActivity.this);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(HomeSearchActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 搜索历史
     */
    public void backselecthistory() {
        HttpHelper.Home_selecthistory(1, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(HomeSearchActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                UserselecthistoryBean entity = FastJSONHelper.getPerson(succeed, UserselecthistoryBean.class);
                List<String> stList = new ArrayList<>();
                for (int i = (entity.getData().getArray().size() - 1); i >= 0; i--) {
                    stList.add(entity.getData().getArray().get(i).getSelecthistory_name());
                }
                topShop.setDatas(stList, HomeSearchActivity.this);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(HomeSearchActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDealte() {
        Shop_deleteselecthistory();
    }

    @Override
    public void onBackData(String name) {
        input.setText(name);
    }

    /**
     * 搜索历史
     */
    public void Shop_deleteselecthistory() {
        HttpHelper.Shop_deleteselecthistory(aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(HomeSearchActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                DeleteselecthistoryBean entity = FastJSONHelper.getPerson(succeed, DeleteselecthistoryBean.class);
                if (entity.getCode() == 0 && entity.getError() == 0) {
                    backHistoryData();
                    backselecthistory();
                    Toast.makeText(HomeSearchActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String error) {
                Toast.makeText(HomeSearchActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
