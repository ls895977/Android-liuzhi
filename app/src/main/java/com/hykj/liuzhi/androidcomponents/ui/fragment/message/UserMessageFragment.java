package com.hykj.liuzhi.androidcomponents.ui.fragment.message;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailSoftArticleActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MessageAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.NotifyMessageAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.UserMessageAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean.NotifyFragmentBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean.UserMessageBean;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
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
 * @date: 2018/9/27
 * @describe:
 */


public class UserMessageFragment extends Fragment implements UserMessageAdapter.OnItemClickListener {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.circle_refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    UserMessageAdapter mAdapter;
    private ACache aCache;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        page=1;
        datas.clear();
        backData();
    }

    private void initView() {
        aCache=ACache.get(getContext());
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        refreshLayout.setEnableRefresh(true);//启用刷新
        refreshLayout.setEnableLoadmore(true);//启用加载
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                backData();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                backData();
                refreshlayout.finishLoadmore();
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        backData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 用户通知
     */
    private int page = 1;
    List<UserMessageBean.DataBean.ArrayBean> datas = new ArrayList<>();
    public void backData() {
        HttpHelper.Home_authorgetmessage(aCache.getAsString("user_id"), page + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }
            @Override
            public void onSucceed(String succeed) {
                UserMessageBean entity = FastJSONHelper.getPerson(succeed, UserMessageBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    datas.add(entity.getData().getArray().get(i));
                }
                if (mAdapter == null) {
                    mAdapter = new UserMessageAdapter(datas,getContext());
                    mAdapter.setOnItemClickListener(UserMessageFragment.this);
                    rv.setAdapter(mAdapter);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {

            }
        });

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent1 = new Intent();
        intent1.putExtra("softtextid", datas.get(position).getSofttext_id()+ "");
        intent1.putExtra("stType", "个人类型");
        intent1.setClass(getContext(), DetailSoftArticleActivity.class);
        startActivity(intent1);
    }
}
