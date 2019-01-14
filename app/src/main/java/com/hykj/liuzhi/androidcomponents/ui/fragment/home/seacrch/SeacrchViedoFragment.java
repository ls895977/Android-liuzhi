package com.hykj.liuzhi.androidcomponents.ui.fragment.home.seacrch;

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
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailSoftArticleActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailVideoActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.RecommendAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter.FirstpageAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FirstpagedataBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.MessageEvent;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.SoftLanguageBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.VideoContextBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.BannerHeader;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 软文和视频
 *
 * @date: 2018/9/27
 * @describe:
 */


public class SeacrchViedoFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    FirstpageAdapter mAdapter;
    List<SoftLanguageBean> data = new ArrayList<>();
    String selecttype;
    private SmartRefreshLayout smartRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_recommend, container, false);
        unbinder = ButterKnife.bind(this, view);
        selecttype = getArguments().getString("pid");
        EventBus.getDefault().register(this);
        smartRefreshLayout=view.findViewById(R.id.home_refreshLayout1);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        smartRefreshLayout.setEnableRefresh(true);//启用刷新
        smartRefreshLayout.setEnableLoadmore(true);//启用加载
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                data.clear();
                postBackData("");
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                postBackData("");
                refreshlayout.finishLoadmore();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        Log.e("aa", selecttype + "----" + messageEvent.getMessage());
        data.clear();
        postBackData("");
    }

    VideoContextBean entity;
    private int page = 1;

    public void postBackData(String serchName) {
        HttpHelper.getuserselect(page, serchName, selecttype, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, VideoContextBean.class);
                setAdapterData(entity);//设置adapter的数据源
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置adapter数据源
     */
    public void setAdapterData(VideoContextBean bean) {
        if (bean.getCode() != 0) {
            Toast.makeText(getContext(), "返回的参数有误不能获取该结果!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selecttype.equals("2")) {
            for (int i = 0; i < bean.getData().getArray().size(); i++) {
                SoftLanguageBean bean2 = new SoftLanguageBean(SoftLanguageBean.IMAGE_TEXT_INSIDE);
                bean2.setVideo_id(bean.getData().getArray().get(i).getVideo_id());
                bean2.setVideo_image(bean.getData().getArray().get(i).getVideo_image());
                bean2.setVideo_name(bean.getData().getArray().get(i).getVideo_name());
                data.add(bean2);
            }
        }
        if (selecttype.equals("1")) {
            for (int i = 0; i < bean.getData().getArray().size(); i++) {
                SoftLanguageBean bean4 = new SoftLanguageBean(SoftLanguageBean.IMAGE_TEXT_TOP);
                bean4.setSofttext_id(bean.getData().getArray().get(i).getSofttext_id());
//            bean4.setSofttext_title(bean.getData().getArray().get(i).getSofttext_title());
                bean4.setSofttextimageURL(bean.getData().getArray().get(i).getSofttextimage().getSofttextimage_url());
                bean4.setUser_id(bean.getData().getArray().get(i).getUser_id());
                bean4.setUser_nickname(bean.getData().getArray().get(i).getUserdata().getUser_nickname());
                bean4.setUser_pic(bean.getData().getArray().get(i).getUserdata().getUser_pic());
                data.add(bean4);
            }
        }
        if (mAdapter == null) {
            mAdapter = new FirstpageAdapter(getContext(), data);
            mAdapter.setOnItemClickListener(this);
            rv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyLoadMoreToLoading();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (selecttype.equals("1")) {
            Intent intent = new Intent(getContext(), DetailSoftArticleActivity.class);
            startActivity(intent);
        } else if (selecttype.equals("2")) {
            Intent intent = new Intent();
            intent.putExtra("videoid", entity.getData().getArray().get(position).getVideo_id()+ "");
            intent.setClass(getContext(), DetailVideoActivity.class);
            startActivity(intent);
        }
    }
}
