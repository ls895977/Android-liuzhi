package com.hykj.liuzhi.androidcomponents.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.GoodDetailBean;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.CartActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.GoodDetailActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.LoginActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.GoodsAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.ShopSearchActivity;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.GetsowingBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopHomeBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.BannerHeader;
import com.hykj.liuzhi.androidcomponents.ui.widget.MoreGoodsHeader;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class ShopFragment extends Fragment {
    @BindView(R.id.search_area)
    RelativeLayout searchArea;
    @BindView(R.id.iv_cart)
    ImageView ivCart;
    @BindView(R.id.shop_RecyclerView)
    RecyclerView rv;
    @BindView(R.id.shop_refreshLayout)
    SmartRefreshLayout shop_refreshLayout;
    Unbinder unbinder;
    GoodsAdapter mAdapter;
    Banner banner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    ArrayList pics = new ArrayList();
    private void initView() {
        shop_refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        shop_refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        shop_refreshLayout.setEnableRefresh(true);//启用刷新
        shop_refreshLayout.setEnableLoadmore(true);//启用加载
        shop_refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                PostShop();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        shop_refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                refreshlayout.finishLoadmore();
            }
        });
        mAdapter = new GoodsAdapter(datas, getContext());
        BannerHeader bannerHeader = new BannerHeader(getContext());
        banner = bannerHeader.getBanner();
        banner.setPadding(20, 0, 20, 0);
        mAdapter.addHeaderView(bannerHeader);
        mAdapter.addHeaderView(new MoreGoodsHeader(getContext()));
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getContext(), GoodDetailActivity.class);
                intent.putExtra("goodsid", datas.get(position).getGoods_id() + "");
                startActivity(intent);
            }
        });
        Getsowing("1", "10", "3");
        PostShop();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.iv_cart, R.id.rl_search, R.id.search_area})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_cart:
                intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_search:

                break;
            case R.id.search_area:
                intent = new Intent(getContext(), ShopSearchActivity.class);
                startActivity(intent);
                break;
        }
    }
    public void Getsowing(String page, String number, String type) {
        HttpHelper.Getsowing(page, number, type, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                GetsowingBean entity = FastJSONHelper.getPerson(succeed, GetsowingBean.class);
                if (entity.getCode() == 0) {
                    pics.clear();
                    for (int i = 0; i < entity.getData().getArray().size(); i++) {
                        pics.add(entity.getData().getArray().get(i).getSowing_url());
                    }
                    banner.setImages(pics);
                    banner.setImageLoader(new GlideImageLoader())
                            .setDelayTime(5000)
                            .start();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    List<ShopHomeBean.DataBean.ArrayBean> datas = new ArrayList<>();
    int page = 1;
    public void PostShop() {
        HttpHelper.Goodsfirstpage(page + "", "10", "1", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ShopHomeBean entity = FastJSONHelper.getPerson(succeed, ShopHomeBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    datas.add(entity.getData().getArray().get(i));
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
