package com.hykj.liuzhi.androidcomponents.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MyOrderTabAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.base.ViewPagerFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.MyOrderTabDetails;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserordersBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopHomeBean;
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
import butterknife.Unbinder;

/**
 * @author: lujialei
 * @date: 2018/10/27
 * @describe:
 */


public class MyOrderTabFragment extends ViewPagerFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    private String type;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.shop_refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ACache aCache;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_order_tab, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aCache = ACache.get(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        refreshLayout.setEnableRefresh(true);//启用刷新
        refreshLayout.setEnableLoadmore(true);//启用加载
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                userorders();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                refreshlayout.finishLoadmore();
            }
        });
        type = getArguments().getString("type");
        userorders();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    List<UserordersBean.DataBean.ArrayBean> datas = new ArrayList<>();
    int page = 1;
    MyOrderTabAdapter adapter;

    public void userorders() {
        HttpHelper.userorders(aCache.getAsString("user_id"), page + "", type, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                UserordersBean entity = FastJSONHelper.getPerson(succeed, UserordersBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    datas.add(entity.getData().getArray().get(i));
                }
                if (adapter == null) {
                    adapter = new MyOrderTabAdapter(datas, getContext());
                    adapter.setOnItemClickListener(MyOrderTabFragment.this);
                    adapter.setOnItemChildClickListener(MyOrderTabFragment.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.oder_cancel://取消订单
                Log.e("aa", "-------取消订单-------" + position);
                break;
            case R.id.oder_pay://立即付款
                Log.e("aa", "-------立即付款-------" + position);
                break;
            case R.id.oder_wuliu://物流
                Log.e("aa", "-------物流-------" + position);
                break;
            case R.id.oder_centerAddr://确认收货
                Log.e("aa", "-------确认收货-------" + position);
                break;
            case R.id.oder_delete://删除订单
                Log.e("aa", "-------删除订单-------" + position);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.putExtra("ordersid", datas.get(position).getOrders_id() + "");
        intent.setClass(getContext(), MyOrderTabDetails.class);
        startActivity(intent);

    }
}
