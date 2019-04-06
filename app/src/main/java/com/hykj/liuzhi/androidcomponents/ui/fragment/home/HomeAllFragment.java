package com.hykj.liuzhi.androidcomponents.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
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
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter.FashionAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter.TextureAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.base.ViewPagerFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.TextureBase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.TextureFragBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.ui.widget.BannerHeader;
import com.hykj.liuzhi.androidcomponents.ui.widget.CustomLoadMoreView;
import com.hykj.liuzhi.androidcomponents.ui.widget.RecycleViewDivider;
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
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeAllFragment extends ViewPagerFragment implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.myrv)
    RecyclerView myrv;
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

    private String modelid, modeltype;

    private void initView() {
        modelid = getArguments().getString("modelid");
        modeltype = getArguments().getString("modeltype");
        Debug.e("-----modelid---" + modelid + "---modeltype---" + modeltype);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        smartRefreshLayout.setEnableRefresh(true);//启用刷新
        smartRefreshLayout.setEnableLoadmore(false);//启用加载
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                page = 1;
//                datas.clear();
//                backData(page);
                refreshlayout.finishRefresh();
            }
        });
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
    BannerHeader header;
    Banner banner;

    public void backData(int page) {
        HttpHelper.Home_modeltodata(page + "", modelid, modeltype, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                if (modeltype.equals("1")) {//以前潮流
                    entity = FastJSONHelper.getPerson(succeed, FashionBean.class);
                    rv.setVisibility(View.VISIBLE);
                    myrv.setVisibility(View.GONE);
                    rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    RecycleViewDivider dividerItemDecoration = new RecycleViewDivider(getContext(), DividerItemDecoration.VERTICAL, R.drawable.divider_mileage);
                    rv.addItemDecoration(dividerItemDecoration);
                    setAdapterData();
                } else if (modeltype.equals("2")) {//以前纹理
                    rv.setVisibility(View.GONE);
                    myrv.setVisibility(View.VISIBLE);
                    myrv.setLayoutManager(new LinearLayoutManager(getContext()));
                    header = new BannerHeader(getContext());
                    banner = header.getBanner();
                    TextureFragBean entity = FastJSONHelper.getPerson(succeed, TextureFragBean.class);
                    setBanner(entity.getData().getImages());
                    setDatasList(entity.getData().getArray());
                }
            }

            @Override
            public void onError(String error) {
            }
        });

    }

    /**
     * 潮流
     */
    List<FashionBase> FashionBaseDatas = new ArrayList<>();
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
            if (bean.getUser_nickname() == null || bean.getUser_nickname().equals("null")) {
                bean.setUser_nickname("");
            } else {
                bean.setUser_nickname(entity.getData().getArray().get(i).getUserdata().getUser_nickname());
            }
            if (entity.getData().getArray().get(i).getUserdata() != null) {
                bean.setUser_pic(entity.getData().getArray().get(i).getUserdata().getUser_pic());
            }
            bean.setUser_id(entity.getData().getArray().get(i).getUser_id());
            FashionBaseDatas.add(bean);
        }
        for (int i = 0; i < entity.getData().getAdvdata().size(); i++) {
            bean = new FashionBase(FashionBase.FashionItem3);
            bean.setAdv_url(entity.getData().getAdvdata().get(i).getAdv_url());
            FashionBaseDatas.add(bean);
        }
        if (mAdapter == null) {
            mAdapter = new FashionAdapter(getContext(), FashionBaseDatas);
            mAdapter.setOnItemClickListener(this);
            mAdapter.setLoadMoreView(new CustomLoadMoreView());
            rv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (modeltype.equals("1")) {//以前潮流
            Intent intent1 = new Intent();
            intent1.putExtra("softtextid", FashionBaseDatas.get(position).getSofttext_id() + "");
            intent1.putExtra("stType", "潮流类型");
            intent1.setClass(getContext(), DetailSoftArticleActivity.class);
            startActivity(intent1);
        } else if (modeltype.equals("2")) {//以前纹理
            Intent intent = new Intent();
            intent.putExtra("videoid", TextureDatas.get(position).getVideo_id() + "");
            intent.putExtra("stType", "纹理类型");
            intent.setClass(getContext(), DetailVideoActivity.class);
            startActivity(intent);
        }

    }


    List<TextureBase> TextureDatas = new ArrayList<>();
    /**
     * 以前纹理
     */
    TextureAdapter TextureMAdapter;

    public void setDatasList(List<TextureFragBean.DataBean.ArrayBean> data) {
        Random random = new Random();
        if (mAdapter == null) {
            TextureMAdapter = new TextureAdapter(getContext(), TextureDatas);
            myrv.setAdapter(TextureMAdapter);
            TextureMAdapter.addHeaderView(header);
            TextureMAdapter.setOnItemClickListener(this);
        }
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
                TextureDatas.add(new TextureBase(TextureBase.TextureIitem1, bean.getVideo_id(), bean.getVideo_name(), bean.getVideo_image(),
                        bean.getVideo_labels(), bean.getVideo_detail(), Labelsdata));
            } else if (indext == 1) {
                TextureDatas.add(new TextureBase(TextureBase.TextureIitem2, bean.getVideo_id(), bean.getVideo_name(), bean.getVideo_image(),
                        bean.getVideo_labels(), bean.getVideo_detail(), Labelsdata));
            } else {
                TextureDatas.add(new TextureBase(TextureBase.TextureIitem3, bean.getVideo_id(), bean.getVideo_name(), bean.getVideo_image(),
                        bean.getVideo_labels(), bean.getVideo_detail(), Labelsdata));
            }
        }
        if (mAdapter != null) mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置sBanner
     */
    public void setBanner(final List<TextureFragBean.DataBean.ImagesBean> images) {
        List<String> picList = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            picList.add(images.get(i).getSowing_url());
        }
        banner.setImages(picList);
        banner.setImageLoader(new GlideImageLoader())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        TextureFragBean.DataBean.ImagesBean imagesBean = images.get(position);
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
}
