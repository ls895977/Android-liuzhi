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
import com.hykj.liuzhi.androidcomponents.bean.CircleBean1;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailCircleImageActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.CircleAdapter1;
import com.hykj.liuzhi.androidcomponents.ui.fragment.circle.bean.CircleFragmentBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.ui.widget.BannerHeader;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
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

public class MineCameFragment extends Fragment implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.rv_mine_camera)
    RecyclerView rvMineCamera;
    Unbinder unbinder;
    private ACache aCache;
    @BindView(R.id.circle_refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_came, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        datas = new ArrayList();
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
        rvMineCamera.setLayoutManager(new LinearLayoutManager(getContext()));
        postBackData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String author;
    private int page = 1;

    public void postBackData() {

        author = getArguments().getString("userId");
        HttpHelper.showimagetexttoauthor(author, page + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Debug.e("---------结果--" + succeed);
                CircleFragmentBean entity = FastJSONHelper.getPerson(succeed, CircleFragmentBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                if (entity.getData() == null) {
                    return;
                }
                if (entity.getData().getArray().size() == 0) {
                    return;
                }
                setAdatper(entity);
            }

            @Override
            public void onError(String error) {
                if (page > 1) {
                    Toast.makeText(getContext(), "暂无更多数据！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    CircleBean1 bean1;
    ArrayList<CircleBean1> datas;
    private CircleAdapter1 adapter;
    List<String> pics = new ArrayList();
    BannerHeader bannerHeader;
    private Banner banner;

    public void setAdatper(CircleFragmentBean entity) {
        Random random = new Random();
        for (int i = 0; i < entity.getData().getArray().size(); i++) {
            List<CircleBean1.ArrayBean> usedata = new ArrayList<>();
            int yushu = entity.getData().getArray().size() % 3;
            int type = entity.getData().getArray().size() - 3;
            int type1 = entity.getData().getArray().size() - 2;
            int size = 0;
            switch (yushu) {
                case 0:
                    size = 3;
                    break;
                case 2:
                    if (i > type) {
                        size = 2;
                    } else {
                        size = 3;
                    }
                    break;
                case 1:
                    if (i > type1) {
                        size = 1;
                    } else {
                        size = 3;
                    }
                    break;
            }
            for (int j = 0; j < size; j++) {
                int zhi;
                if (i <= 3) {
                    zhi = (i + j);
                } else {
                    zhi = (i + j - 1);
                }
                CircleBean1.ArrayBean bean = new CircleBean1.ArrayBean();
                bean.setImagetext_id(Integer.valueOf(entity.getData().getArray().get(zhi).getImagetext_id()));
                if (entity.getData().getArray().get(zhi).getImagetextimagedata() != null) {
                    bean.setImagetextimage_url(entity.getData().getArray().get(zhi).getImagetextimagedata().getImagetextimage_url());
                } else {
                    bean.setImagetextimage_url("");
                }
                usedata.add(bean);
            }
            if (i <= 3) {
                i = i + size;
            } else {
                i = i + (size - 1);
            }
            switch (random.nextInt(3)) {
                case 0:
                    bean1 = new CircleBean1(CircleBean1.THREE_SMALL, usedata);
                    break;
                case 1:
                    bean1 = new CircleBean1(CircleBean1.RIGHT_BIG, usedata);
                    break;
                case 2:
                    bean1 = new CircleBean1(CircleBean1.LEFT_BIG, usedata);
                    break;
            }
            datas.add(bean1);
        }
        try {
            if (adapter == null) {
                adapter = new CircleAdapter1(datas, getContext());
            bannerHeader = new BannerHeader(getContext());
            pics.add(entity.getData().getShowing_url());
            banner = bannerHeader.getBanner();
            banner.setImages(pics);
            banner.setImageLoader(new GlideImageLoader())
                    .setDelayTime(5000)
                    .start();
                adapter.setOnItemChildClickListener(this);
                rvMineCamera.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        } catch (Exception s) {

        }
    }

    Intent intent;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        intent = new Intent();
        switch (view.getId()) {
            case R.id.THREE_SMALL_img1:
            case R.id.RIGHT_BIG_img1:
            case R.id.LEFT_BIG_img1:
                if (datas.get(position).getArray().size() >= 1) {
                    intent.putExtra("imagetext_id", datas.get(position).getArray().get(0).getImagetext_id() + "");
                    datas.get(position).getArray().get(0).getImagetext_id();
                    intent.setClass(getContext(), DetailCircleImageActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.THREE_SMALL_img2:
            case R.id.RIGHT_BIG_img2:
            case R.id.LEFT_BIG_img2:
                if (datas.get(position).getArray().size() >= 2) {
                    intent.putExtra("imagetext_id", datas.get(position).getArray().get(1).getImagetext_id() + "");
                    datas.get(position).getArray().get(1).getImagetext_id();
                    intent.setClass(getContext(), DetailCircleImageActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.THREE_SMALL_img3:
            case R.id.RIGHT_BIG_img3:
            case R.id.LEFT_BIG_img3:
                if (datas.get(position).getArray().size() >= 3) {
                    intent.putExtra("imagetext_id", datas.get(position).getArray().get(2).getImagetext_id() + "");
                    datas.get(position).getArray().get(2).getImagetext_id();
                    intent.setClass(getContext(), DetailCircleImageActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
