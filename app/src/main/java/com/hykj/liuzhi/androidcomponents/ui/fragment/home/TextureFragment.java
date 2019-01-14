package com.hykj.liuzhi.androidcomponents.ui.fragment.home;

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
import android.widget.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.mock.Mock;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailVideoActivity;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter.TextureAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.base.ViewPagerFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.TextureBase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.TextureFragBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.BannerHeader;
import com.hykj.liuzhi.androidcomponents.ui.widget.CustomLoadMoreView;
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
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 纹理
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */
public class TextureFragment extends ViewPagerFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    TextureAdapter mAdapter;
    private SmartRefreshLayout smartRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home_texture, container, false);
            unbinder = ButterKnife.bind(this, rootView);
            smartRefreshLayout=rootView.findViewById(R.id.home_refreshLayout);
            initView();
            backData(page);//获取数据
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    int page = 1;
    Banner banner;
    BannerHeader header;
    private void initView() {
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        smartRefreshLayout.setEnableRefresh(true);//启用刷新
        smartRefreshLayout.setEnableLoadmore(true);//启用加载
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                backData(page);
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                refreshlayout.finishLoadmore();
            }
        });


        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        header = new BannerHeader(getContext());
        banner = header.getBanner();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView == null) {
            unbinder.unbind();
        }
    }

    TextureFragBean entity;

    public void backData(int page) {
        HttpHelper.getFirstpagedatatexture(page, new HttpHelper.HttpUtilsCallBack() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(Object succeed) {
                entity = FastJSONHelper.getPerson(succeed + "", TextureFragBean.class);
                setBanner(entity.getData().getImages());
                setDatasList(entity.getData().getArray());
            }

            @Override
            public void onError(String error) {
            }
        });
    }

    /**
     * 设置sBanner
     */
    public void setBanner(List<TextureFragBean.DataBean.ImagesBean> images) {
        ArrayList<String> picList = new ArrayList();
        for (int i = 0; i < images.size(); i++) {
            picList.add(images.get(i).getSowing_url());
        }
        banner.setImages(picList);
        banner.setImageLoader(new GlideImageLoader())
                .setDelayTime(5000)
                .start();
    }

    List<TextureBase> datas = new ArrayList<>();

    /**
     * 设置adpater数据源
     */
    public void setDatasList(List<TextureFragBean.DataBean.ArrayBean> data) {
        Random random = new Random();
        if (data.size() == 0) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            TextureFragBean.DataBean.ArrayBean bean = data.get(i);

            List<TextureBase.LabelsdataBean> Labelsdata = new ArrayList<>();
            for (int j = 0; j < bean.getLabelsdata().size(); j++) {
                TextureBase.LabelsdataBean labelsdataBean = new TextureBase.LabelsdataBean();
                labelsdataBean.setVideolabel_creattime(bean.getLabelsdata().get(j).getVideolabel_creattime());
                labelsdataBean.setVideolabel_status(bean.getLabelsdata().get(j).getVideolabel_status());
                labelsdataBean.setVideolabel_id(bean.getLabelsdata().get(j).getVideolabel_id());
                labelsdataBean.setVideolabel_name(bean.getLabelsdata().get(j).getVideolabel_name());
                Labelsdata.add(labelsdataBean);
            }
            int indext = random.nextInt(3);
            if (indext == 0) {
                datas.add(new TextureBase(TextureBase.TextureIitem1, bean.getVideo_id(), bean.getVideo_name(), bean.getVideo_image(),
                        bean.getVideo_labels(), bean.getVideo_detail(), Labelsdata));
            } else if (indext == 1) {
                datas.add(new TextureBase(TextureBase.TextureIitem2, bean.getVideo_id(), bean.getVideo_name(), bean.getVideo_image(),
                        bean.getVideo_labels(), bean.getVideo_detail(), Labelsdata));
            } else {
                datas.add(new TextureBase(TextureBase.TextureIitem3, bean.getVideo_id(), bean.getVideo_name(), bean.getVideo_image(),
                        bean.getVideo_labels(), bean.getVideo_detail(), Labelsdata));
            }
        }
        if (mAdapter == null) {
            mAdapter = new TextureAdapter(getContext(), datas);
            rv.setAdapter(mAdapter);
            mAdapter.addHeaderView(header);
            mAdapter.setOnItemClickListener(this);
        } else {
            mAdapter.notifyLoadMoreToLoading();
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.putExtra("videoid", datas.get(position).getVideo_id() + "");
        intent.setClass(getContext(), DetailVideoActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {

        } else {

        }
    }
}
