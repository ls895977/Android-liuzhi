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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailSoftArticleActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.UserAdvertorialAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserAdvertorialBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.WatchHistoryBean;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
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
 * 软文浏览记录
 *
 * @author: lujialei
 * @date: 2018/10/27
 * @describe:
 */
public class MineSoftArticleFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView rv;
    @BindView(R.id.shop_refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ACache aCache;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_beautiful_image, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        aCache = ACache.get(getActivity());
//        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//        RecommendAdapter mAdapter = new RecommendAdapter(getContext(), Mock.getRecommendList());
//        rv.setAdapter(mAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        refreshLayout.setEnableRefresh(true);//启用刷新
        refreshLayout.setEnableLoadmore(true);//启用加载
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                userbrowses();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                userbrowses();
                refreshlayout.finishLoadmore();
            }
        });
        userbrowses();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private UserAdvertorialAdapter userAdapter;
    private int page = 1;
    List<UserAdvertorialBean.DataBean.ArrayBean> datas = new ArrayList<>();

    public void userbrowses() {
        HttpHelper.userbrowses(aCache.getAsString("user_id"), page + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                WatchHistoryBean entity = gson.fromJson(succeed, WatchHistoryBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    UserAdvertorialBean.DataBean.ArrayBean bean = new UserAdvertorialBean.DataBean.ArrayBean();
                    bean.setSofttext_id(entity.getData().getArray().get(i).getSofttext_id());
                    bean.setSofttext_title(entity.getData().getArray().get(i).getSofttext_id() + "");
                    UserAdvertorialBean.DataBean.ArrayBean.SofttextimagedataBean softtextimagedataBean = new UserAdvertorialBean.DataBean.ArrayBean.SofttextimagedataBean();
                    WatchHistoryBean.DataBean.ArrayBean.SofttextdataBean softtextdata = entity.getData().getArray().get(i).getSofttextdata();
                    UserAdvertorialBean.DataBean.ArrayBean.UserdataBean userdataBean = new UserAdvertorialBean.DataBean.ArrayBean.UserdataBean();
                    if (softtextdata != null &&
                            softtextdata.getSofttextimages() != null) {
                        softtextimagedataBean.setSofttextimage_url(softtextdata.getSofttextimages().getSofttextimage_url());
                        if (softtextdata.getUserdata() != null) {
                            userdataBean.setUser_autograph(softtextdata.getUserdata().getUser_autograph());
                            userdataBean.setUser_nickname(softtextdata.getUserdata().getUser_nickname());
                            userdataBean.setUser_pic(softtextdata.getUserdata().getUser_pic());
                        }
                    }
                    bean.setSofttextimagedata(softtextimagedataBean);
                    bean.setUserdata(userdataBean);
                    datas.add(bean);
                }
                if (userAdapter == null) {
                    userAdapter = new UserAdvertorialAdapter(getContext(), datas);
                    userAdapter.setOnItemClickListener(MineSoftArticleFragment.this);
                    rv.setAdapter(userAdapter);
                } else {
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent1 = new Intent();
        intent1.putExtra("softtextid", datas.get(position).getSofttext_id() + "");
        intent1.setClass(getContext(), DetailSoftArticleActivity.class);
        startActivity(intent1);
    }
}
