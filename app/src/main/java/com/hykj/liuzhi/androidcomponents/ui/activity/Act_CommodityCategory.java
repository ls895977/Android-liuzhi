package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CommodityCategoryType;
import com.hykj.liuzhi.androidcomponents.bean.GetgoodScatesBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.adapter.CommodityCategoryAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.CommodityCategoryLineAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.GoodsAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.ShopSearchActivity;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopHomeBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopSeacharBean;
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

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * 商品类别
 */
public class Act_CommodityCategory extends BaseActivity implements View.OnClickListener, CommodityCategoryLineAdapter.onBackItem, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    private TextView title;
    private SmartRefreshLayout shop_refreshLayout;
    private RecyclerView mRecyclerView;
    private ACache aCache;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_commoditycategory);
        initView();
    }
    private int page = 1;
    public void initView() {
        aCache = ACache.get(this);
        findViewById(R.id.tv_homesearch_cancel).setOnClickListener(this);
        findViewById(R.id.commodity_category_status).setOnClickListener(this);
        findViewById(R.id.shop_search).setOnClickListener(this);
        mRecyclerView = findViewById(R.id.shop_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        title = findViewById(R.id.commodity_category_title);
        shop_refreshLayout = findViewById(R.id.shop_refreshLayout);
        shop_refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        shop_refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        shop_refreshLayout.setEnableRefresh(true);//启用刷新
        shop_refreshLayout.setEnableLoadmore(true);//启用加载
        shop_refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                selectgoods();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        shop_refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                selectgoods();
                refreshlayout.finishLoadmore();
            }
        });
        showPXPopupWindow();
        selectgoods();
    }

    View v1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_homesearch_cancel:
                finish();
                break;
            case R.id.commodity_category_status://商品类别
                windowpx = new PopupWindow(haderView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
                windowpx.setOutsideTouchable(true);
                windowpx.setFocusable(true);
                //让pop可以点击外面消失掉
                windowpx.setBackgroundDrawable(new ColorDrawable(0));
                windowpx.showAsDropDown(v);
                getgoodscates();//获取类别
                break;
            case R.id.shop_search:
                startActivity(new Intent(this, ShopSearchActivity.class));
                break;
        }
    }

    private PopupWindow windowpx;
    //控件下方弹出窗口
    SmartRefreshLayout popupSmartRefreshLayout;
    RecyclerView popupRecyclerView;
    private int popuPage = 1;
    private CommodityCategoryLineAdapter popuAdapter;
    View haderView;

    private void showPXPopupWindow() {
        //自定义布局，显示内容
        haderView = LayoutInflater.from(this).inflate(R.layout.dlg_commoditycategory, null);
        popupRecyclerView = haderView.findViewById(R.id.shop_RecyclerView);
        popupRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 商=获取所有商品类别
     */
    private int popuAdapterindext = 0;
    List<GetgoodScatesBean.DataBean.ArrayBean> popuDatas = new ArrayList<>();
    public void getgoodscates() {
        HttpHelper.getgoodscates(popuPage + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(Act_CommodityCategory.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                popuDatas.clear();
                GetgoodScatesBean entity = FastJSONHelper.getPerson(succeed, GetgoodScatesBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    GetgoodScatesBean.DataBean.ArrayBean bean = entity.getData().getArray().get(i);
                    bean.setOnOff(false);
                    popuDatas.add(bean);
                }
                if (popuAdapter == null) {
                    popuAdapter = new CommodityCategoryLineAdapter(getContext(), popuDatas, Act_CommodityCategory.this);
                    popuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            if (popuDatas.get(position).isOnOff()) {
                                popuDatas.get(position).setOnOff(false);
                            } else {
                                popuDatas.get(position).setOnOff(true);
                            }
                            popuAdapter.notifyDataSetChanged();
                        }
                    });
                    popupRecyclerView.setAdapter(popuAdapter);
                } else {
                    popuAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(Act_CommodityCategory.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * @param item
     */
    private String cateid = "";

    @Override
    public void OnBack(GetgoodScatesBean.DataBean.ArrayBean.ChildrenBean item) {
        title.setText(item.getGoodscate_name());
        cateid = item.getGoodscate_id() + "";
        windowpx.dismiss();
        datas.clear();
        page = 1;
        selectgoods();
    }

    public void selectgoods() {
        HttpHelper.selectgoods(page + "", "", cateid, aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(Act_CommodityCategory.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ShopHomeBean entity = FastJSONHelper.getPerson(succeed, ShopHomeBean.class);
                setAdapter(entity);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(Act_CommodityCategory.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private CommodityCategoryAdapter adapter;
    List<CommodityCategoryType> datas = new ArrayList<>();
    private int allint = 0;
    boolean kaiguan = true;

    public void setAdapter(ShopHomeBean bean) {
        allint = bean.getData().getArray().size();
        for (int i = 0; i < bean.getData().getArray().size(); i++) {
            if (allint == 1) {
                kaiguan = true;
            } else if (allint == 2) {
                kaiguan = false;
            }
            if (kaiguan) {
                CommodityCategoryType bean1 = new CommodityCategoryType(CommodityCategoryType.THREE_SMALL);
                bean1.setGoods_id(bean.getData().getArray().get(i).getGoods_id());
                bean1.setGoods_name(bean.getData().getArray().get(i).getGoods_name());
                bean1.setGoods_pic(bean.getData().getArray().get(i).getGoods_pic());
                bean1.setGoods_price(bean.getData().getArray().get(i).getGoods_price());
                datas.add(bean1);
                allint--;
                kaiguan = false;
            } else {
                List<CommodityCategoryType.ArrayBean> datasChlid = new ArrayList<>();
                CommodityCategoryType.ArrayBean bean1 = new CommodityCategoryType.ArrayBean();
                bean1.setGoods_id(bean.getData().getArray().get(i).getGoods_id());
                bean1.setGoods_name(bean.getData().getArray().get(i).getGoods_name());
                bean1.setGoods_pic(bean.getData().getArray().get(i).getGoods_pic());
                bean1.setGoods_price(bean.getData().getArray().get(i).getGoods_price());
                datasChlid.add(bean1);
                CommodityCategoryType.ArrayBean bean2 = new CommodityCategoryType.ArrayBean();
                bean2.setGoods_id(bean.getData().getArray().get(i + 1).getGoods_id());
                bean2.setGoods_name(bean.getData().getArray().get(i + 1).getGoods_name());
                bean2.setGoods_pic(bean.getData().getArray().get(i + 1).getGoods_pic());
                bean2.setGoods_price(bean.getData().getArray().get(i + 1).getGoods_price());
                datasChlid.add(bean2);
                CommodityCategoryType heandata = new CommodityCategoryType(CommodityCategoryType.RIGHT_BIG);
                heandata.setArray(datasChlid);
                datas.add(heandata);
                allint -= 2;
                i++;
                kaiguan = true;
            }
        }
        if (adapter == null) {
            adapter = new CommodityCategoryAdapter(datas, this);
            adapter.setOnItemChildClickListener(this);
            adapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private Intent intent;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        intent = new Intent();
        intent.setClass(getContext(), GoodDetailActivity.class);
        switch (view.getId()) {
            case R.id.item1:
                intent.putExtra("goodsid", datas.get(position).getArray().get(0).getGoods_id() + "");
                break;
            case R.id.item2:
                intent.putExtra("goodsid", datas.get(position).getArray().get(1).getGoods_id() + "");
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        intent = new Intent();
        intent.putExtra("goodsid", datas.get(position).getGoods_id() + "");
        intent.setClass(getContext(), GoodDetailActivity.class);
        startActivity(intent);
    }
}
