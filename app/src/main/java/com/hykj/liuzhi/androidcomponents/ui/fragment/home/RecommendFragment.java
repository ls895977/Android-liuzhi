package com.hykj.liuzhi.androidcomponents.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailSoftArticleActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailVideoActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.WebViewActivity;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter.FirstpageAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.base.ViewPagerFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FirstpagedataBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.SoftLanguageBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.BannerHeader;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 推荐
 * @date: 2018/9/27
 * @describe:
 */


public class RecommendFragment extends ViewPagerFragment implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    FirstpageAdapter mAdapter;
    List<SoftLanguageBean> dataAll = new ArrayList<>();

    private Banner banner;
    private SmartRefreshLayout smartRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home_recommend, container, false);
            unbinder = ButterKnife.bind(this, rootView);
            initListener(rootView);
            postBackData();
        }
        return rootView;
    }

    BannerHeader header;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initListener(View view) {
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        smartRefreshLayout = view.findViewById(R.id.home_refreshLayout1);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        smartRefreshLayout.setEnableRefresh(true);//启用刷新
        smartRefreshLayout.setEnableLoadmore(false);//启用加载
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                videoList.clear();
                imageList.clear();
                guangGaoList.clear();
                dataAll.clear();
                postBackData();
                refreshlayout.finishRefresh();
            }
        });
        header = new BannerHeader(getContext());
        banner = header.getBanner();
        mAdapter = new FirstpageAdapter(getContext(), dataAll);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.addHeaderView(header);
        rv.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView == null) {
            unbinder.unbind();
        }
    }

    FirstpagedataBean entity;
    private int page = 1;

    public void postBackData() {
        HttpHelper.getHomeFirstpagedata(page + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, FirstpagedataBean.class);
                setBanner(entity.getData().getImagedata());//设置首页bana图
                setAdapterData(entity);//设置adapter的数据源
            }

            @Override
            public void onError(String error) {
                if (page > 1) {
                    Toast.makeText(getContext(), "暂无更多数据！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 设置sBanner
     */
    public void setBanner(final List<FirstpagedataBean.DataBean.ImagedataBean> FirstpagedataBean) {
        ArrayList<String> picList = new ArrayList();
        for (int i = 0; i < FirstpagedataBean.size(); i++) {
            picList.add(FirstpagedataBean.get(i).getSowing_url());
        }
        banner.setImages(picList);
        banner.setImageLoader(new GlideImageLoader())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        FirstpagedataBean.DataBean.ImagedataBean imagesBean = FirstpagedataBean.get(position);
                        //1为软文，2为视频，3为H5
                        Intent intent = new Intent();
                        switch (imagesBean.sowing_class) {
                            case 1:
                                intent.putExtra("videoid", imagesBean.sowing_link);
                                intent.putExtra("stType", "纹理类型");
                                intent.setClass(getContext(), DetailVideoActivity.class);
                                break;
                            case 2:
                                intent.putExtra("softtextid", imagesBean.sowing_link);
                                intent.putExtra("stType", "推荐类型");
                                intent.setClass(getContext(), DetailSoftArticleActivity.class);
                                break;
                            case 3:
                                intent.putExtra("url", imagesBean.sowing_link);
                                intent.setClass(getContext(), WebViewActivity.class);
                                break;
                        }
                        startActivity(intent);
                    }
                })
                .setDelayTime(5000)
                .start();
    }

    /**
     * 设置adapter数据源
     */
    List<FirstpagedataBean.DataBean.VideodataBean> videoList = new ArrayList<>();
    List<FirstpagedataBean.DataBean.SofttextdataBean> imageList = new ArrayList<>();
    List<FirstpagedataBean.DataBean.AdvdataBean> guangGaoList = new ArrayList<>();

    public void setAdapterData(FirstpagedataBean bean) {
        if (bean.getCode() != 0) {
            Toast.makeText(getContext(), "返回的参数有误不能获取该结果!", Toast.LENGTH_SHORT).show();
            return;
        }
        SoftLanguageBean bean1 = new SoftLanguageBean(SoftLanguageBean.SECTION_HEADER);//纹理  头
        dataAll.add(bean1);
        for (int i = 0; i < bean.getData().getVideodata().size(); i++) {//视频数据
            SoftLanguageBean bean2 = new SoftLanguageBean(SoftLanguageBean.IMAGE_TEXT_INSIDE);
            bean2.setVideo_id(bean.getData().getVideodata().get(i).getVideo_id());
            bean2.setVideo_image(bean.getData().getVideodata().get(i).getVideo_image());
            bean2.setVideo_name(bean.getData().getVideodata().get(i).getVideo_name());
            dataAll.add(bean2);
        }
        SoftLanguageBean bean3 = new SoftLanguageBean(SoftLanguageBean.SOFT_ARTICLE);//软文头
        dataAll.add(bean3);
        for (int i = 0; i < bean.getData().getSofttextdata().size(); i++) {//数据源软文
            SoftLanguageBean bean4 = new SoftLanguageBean(SoftLanguageBean.IMAGE_TEXT_TOP);
            bean4.setSofttext_id(bean.getData().getSofttextdata().get(i).getSofttext_id());
            bean4.setSofttext_title(bean.getData().getSofttextdata().get(i).getSofttext_title());
            bean4.setSofttextimageURL(bean.getData().getSofttextdata().get(i).getSofttextimage().getSofttextimage_url());
            bean4.setUser_id(bean.getData().getSofttextdata().get(i).getUser_id());
            bean4.setUser_nickname(bean.getData().getSofttextdata().get(i).getUserdata().getUser_nickname());
            bean4.setUser_pic(bean.getData().getSofttextdata().get(i).getUserdata().getUser_pic());
            dataAll.add(bean4);
        }
        SoftLanguageBean bean5 = new SoftLanguageBean(SoftLanguageBean.IMAGE_HADER);//更多内容头
        dataAll.add(bean5);
        for (int i = 0; i < bean.getData().getAdvdata().size(); i++) {//广告数据
            SoftLanguageBean bean6 = new SoftLanguageBean(SoftLanguageBean.IMAGE_GANGGAO);
            bean6.setAdv_url(bean.getData().getAdvdata().get(i).getAdv_url());
            dataAll.add(bean6);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {

        } else {

        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.my_addadd://加载更多内窜
//                page++;
//                postBackData();
                break;
            case R.id.video1://视频
                Intent intent = new Intent();
                intent.putExtra("videoid", dataAll.get(position).getVideo_id() + "");
                intent.putExtra("stType","推荐类型");
                intent.setClass(getContext(), DetailVideoActivity.class);
                startActivity(intent);
                break;
            case R.id.image1://软文
                Intent intent1 = new Intent();
                intent1.putExtra("softtextid", dataAll.get(position).getSofttext_id() + "");
                intent1.putExtra("stType","推荐类型");
                intent1.setClass(getContext(), DetailSoftArticleActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
