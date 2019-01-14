package com.hykj.liuzhi.androidcomponents.ui.fragment.mine;

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
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CircleBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailCircleImageActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailSoftArticleActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.CircleAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.UserAdvertorialAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserAdvertorialBean;
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
 * 用户发过的所有软文
 */
public class UserAdvertorialFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_mine_camera)
    RecyclerView rvMineCamera;
    @BindView(R.id.shop_refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private UserAdvertorialAdapter userAdapter;
    private ACache aCache;
    private String user_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgt_useradvertorial, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData(view);
        return view;
    }

    private int page = 1;

    private void initData(View view) {
        user_id = getArguments().getString("userId");
        aCache = ACache.get(getActivity());
        rvMineCamera.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        refreshLayout.setEnableRefresh(true);//启用刷新
        refreshLayout.setEnableLoadmore(true);//启用加载
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                postBackData();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                postBackData();
                refreshlayout.finishLoadmore();
            }
        });
        postBackData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    List<UserAdvertorialBean.DataBean.ArrayBean> datas = new ArrayList<>();

    public void postBackData() {
        HttpHelper.usersofttexts(page + "", user_id, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                UserAdvertorialBean entity = FastJSONHelper.getPerson(succeed, UserAdvertorialBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    datas.add(entity.getData().getArray().get(i));
                }
                if (userAdapter == null) {
                    userAdapter = new UserAdvertorialAdapter(getContext(), datas);
                    userAdapter.setOnItemClickListener(UserAdvertorialFragment.this);
                    rvMineCamera.setAdapter(userAdapter);
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
