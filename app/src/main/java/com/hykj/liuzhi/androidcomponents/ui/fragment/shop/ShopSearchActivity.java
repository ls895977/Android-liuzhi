package com.hykj.liuzhi.androidcomponents.ui.fragment.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.DeleteSelecthisteryBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.BaseActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.GoodDetailActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.GoodsAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopHomeBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopSeacharBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.FindSearchLayout;
import com.hykj.liuzhi.androidcomponents.ui.widget.HistorySearchLayout;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhouyou.http.EasyHttp.getContext;

public class ShopSearchActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.shop_refreshLayout)
    SmartRefreshLayout shop_refreshLayout;
    @BindView(R.id.shop_search)
    EditText shop_search;
    @BindView(R.id.shop_tab)
    LinearLayout linearTab;
    @BindView(R.id.shop_list)
    LinearLayout linearList;
    ACache aCache;
    @BindView(R.id.shop_search_history)
    HistorySearchLayout topUser;
    @BindView(R.id.shop_search_find)
    FindSearchLayout topShop;
    @BindView(R.id.shop_RecyclerView)
    RecyclerView myListRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_search);
        ButterKnife.bind(this);
        initView();
    }

    String stSerch = "";

    public void initView() {
        aCache = ACache.get(this);
        topUser.setOnClick(new HistorySearchLayout.onClick() {
            @Override
            public void onDealte() {
                Shop_clearuserselectgoodshistory();
            }

            @Override
            public void onBackData(String name) {
                shop_search.setText(name);
            }
        });
        topShop.setBackData(new FindSearchLayout.BackData(){
            @Override
            public void onBack(String name) {
                shop_search.setText(name);
            }
        });
        myListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shop_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                stSerch = shop_search.getText().toString();
                if (stSerch.length() > 0) {
                    linearTab.setVisibility(View.GONE);
                    linearList.setVisibility(View.VISIBLE);
                    datas.clear();
                    selectgoods(stSerch);
                } else {
                    backHistoryData();
                    goodsselecthistory();
                    linearTab.setVisibility(View.VISIBLE);
                    linearList.setVisibility(View.GONE);
                }
            }
        });
        shop_refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        shop_refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        shop_refreshLayout.setEnableRefresh(true);//启用刷新
        shop_refreshLayout.setEnableLoadmore(true);//启用加载
        shop_refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                selectgoods(stSerch);
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        shop_refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                selectgoods(stSerch);
                refreshlayout.finishLoadmore();
            }
        });
        backHistoryData();
        goodsselecthistory();
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
     * 用户商品搜索历史
     */
    public void backHistoryData() {
        HttpHelper.usergoodsselecthistory(aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(ShopSearchActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ShopSeacharBean entity = FastJSONHelper.getPerson(succeed, ShopSeacharBean.class);
                List<String> stList = new ArrayList<>();
                for (int i = (entity.getData().getArray().size() - 1); i >= 0; i--) {
                    stList.add(entity.getData().getArray().get(i).getGoodsselect_name());
                }
                topUser.setData(stList, ShopSearchActivity.this);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ShopSearchActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 商品搜索历史
     */
    public void goodsselecthistory() {
        HttpHelper.goodsselecthistory(aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(ShopSearchActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ShopSeacharBean entity = FastJSONHelper.getPerson(succeed, ShopSeacharBean.class);
                List<String> stList = new ArrayList<>();
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    stList.add(entity.getData().getArray().get(i).getGoodsselect_name());
                }
                topShop.setDatas(stList, ShopSearchActivity.this);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ShopSearchActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 商品搜索
     */
    private int page = 1;
    GoodsAdapter mAdapter;
    List<ShopHomeBean.DataBean.ArrayBean> datas = new ArrayList<>();

    public void selectgoods(String name) {
        Log.e("aa", "----商品搜索-------" + name);
        HttpHelper.selectgoods(page + "", name, "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(ShopSearchActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ShopHomeBean entity = FastJSONHelper.getPerson(succeed, ShopHomeBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    datas.add(entity.getData().getArray().get(i));
                }
                if (mAdapter == null) {
                    mAdapter = new GoodsAdapter(datas, getContext());
                    mAdapter.setOnItemClickListener(ShopSearchActivity.this);
                    myListRecyclerView.setAdapter(mAdapter);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ShopSearchActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * deleteselecthistory
     */

    public void Shop_clearuserselectgoodshistory() {
        HttpHelper.Shop_clearuserselectgoodshistory(aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(ShopSearchActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                DeleteSelecthisteryBean entity = FastJSONHelper.getPerson(succeed, DeleteSelecthisteryBean.class);
                if (entity.getMsg().equals("删除成功")) {
                    backHistoryData();
                    goodsselecthistory();
                    Toast.makeText(ShopSearchActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ShopSearchActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.setClass(getContext(), GoodDetailActivity.class);
        intent.putExtra("goodsid", datas.get(position).getGoods_id() + "");
        startActivity(intent);
    }

}
