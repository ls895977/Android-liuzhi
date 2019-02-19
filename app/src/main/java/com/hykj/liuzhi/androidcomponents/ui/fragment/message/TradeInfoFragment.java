package com.hykj.liuzhi.androidcomponents.ui.fragment.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.InForMationBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MessageAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.UserMessageAdapter;
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


public class TradeInfoFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    MessageAdapter mAdapter;
    private SmartRefreshLayout refreshLayout;
    private ACache aCache;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trade_info, container, false);
        refreshLayout = view.findViewById(R.id.circle_refreshLayout);
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
    List<InForMationBean.DataBean.ArrayBean> datas = new ArrayList<>();

    public void backData() {
        HttpHelper.getInformation(aCache.getAsString("user_id"), page + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                InForMationBean entity = FastJSONHelper.getPerson(succeed, InForMationBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    datas.add(entity.getData().getArray().get(i));
                }
                if (mAdapter == null) {
                    mAdapter = new MessageAdapter(datas);
                    mAdapter.setOnItemClickListener(TradeInfoFragment.this);
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

    }
}
