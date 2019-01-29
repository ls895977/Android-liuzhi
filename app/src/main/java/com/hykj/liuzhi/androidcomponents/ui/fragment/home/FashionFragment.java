package com.hykj.liuzhi.androidcomponents.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.mock.Mock;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailSoftArticleActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailVideoActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.RecommendAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter.FashionAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.base.ViewPagerFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FirstpagedataBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.CustomLoadMoreView;
import com.hykj.liuzhi.androidcomponents.ui.widget.RecycleViewDivider;
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
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 潮流
 *
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */
public class FashionFragment extends ViewPagerFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    FashionAdapter mAdapter;
    private SmartRefreshLayout smartRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home_fashion, container, false);
            unbinder = ButterKnife.bind(this, rootView);
            smartRefreshLayout = rootView.findViewById(R.id.home_refreshLayout);
            initView();
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView() {
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        smartRefreshLayout.setEnableRefresh(true);//启用刷新
        smartRefreshLayout.setEnableLoadmore(false);//启用加载
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                backData(page);
                refreshlayout.finishRefresh();
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        RecycleViewDivider dividerItemDecoration = new RecycleViewDivider(getContext(), DividerItemDecoration.VERTICAL, R.drawable.divider_mileage);
        rv.addItemDecoration(dividerItemDecoration);
        backData(page);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView == null) {
            unbinder.unbind();
        }
    }

    FashionBean entity;
    int page = 1;

    public void backData(int page) {
        HttpHelper.getFirstpagedatatrend(page, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, FashionBean.class);
                setAdapterData();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });

    }

    List<FashionBase> datas = new ArrayList<>();
    Random random = new Random();
    private FashionBase bean;
    public void setAdapterData() {
        for (int i = 0; i < entity.getData().getArray().size(); i++) {
            int indext = random.nextInt(2);
            if (indext == 0) {
                bean = new FashionBase(FashionBase.FashionItem1);
            } else {
                bean = new FashionBase(FashionBase.FashionItem2);
            }
            bean.setSofttext_id(entity.getData().getArray().get(i).getSofttext_id());
            bean.setSofttext_title(entity.getData().getArray().get(i).getSofttext_title());
            if (entity.getData().getArray().get(i).getSofttextimage() != null) {
                bean.setSofttextimage_url(entity.getData().getArray().get(i).getSofttextimage().getSofttextimage_url());
            }
            bean.setUser_nickname(entity.getData().getArray().get(i).getUserdata().getUser_nickname());
            bean.setUser_pic(entity.getData().getArray().get(i).getUserdata().getUser_pic());
            bean.setUser_id(entity.getData().getArray().get(i).getUser_id());
            datas.add(bean);
        }
        for (int i = 0; i < entity.getData().getAdvdata().size(); i++) {
            bean = new FashionBase(FashionBase.FashionItem3);
            bean.setAdv_url(entity.getData().getAdvdata().get(i).getAdv_url());
            datas.add(bean);
        }
        if (mAdapter == null) {
            mAdapter = new FashionAdapter(getContext(), datas);
            mAdapter.setOnItemClickListener(this);
            mAdapter.setLoadMoreView(new CustomLoadMoreView());
            mAdapter.setOnLoadMoreListener(this, rv);
            rv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent1 = new Intent();
        intent1.putExtra("softtextid", datas.get(position).getSofttext_id() + "");
        intent1.setClass(getContext(), DetailSoftArticleActivity.class);
        startActivity(intent1);
    }

    @Override
    public void onLoadMoreRequested() {
        rv.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                backData(page);
            }
        }, 2000);
    }

}
