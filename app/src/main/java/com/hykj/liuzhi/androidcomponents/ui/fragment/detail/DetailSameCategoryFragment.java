package com.hykj.liuzhi.androidcomponents.ui.fragment.detail;

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
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CircleBean;
import com.hykj.liuzhi.androidcomponents.bean.CircleBean1;
import com.hykj.liuzhi.androidcomponents.bean.DetailMoreVideoBean;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailCircleImageActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.CircleAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.CircleAdapter1;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailMoreVideoAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.circle.bean.CircleFragmentBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.detail.bean.GetImageTextBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.BannerHeader;
import com.hykj.liuzhi.androidcomponents.ui.widget.HeaderCircleScroll;
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
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class DetailSameCategoryFragment extends Fragment implements BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    CircleAdapter mAdapter;
    String imagetext_id = "";
    @BindView(R.id.circle_refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_same_category, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        imagetext_id = bundle.getString("imagetext");
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
//        ArrayList<CircleBean> list = new ArrayList();
//        list.add(new CircleBean(1));
//        list.add(new CircleBean(2));
//        list.add(new CircleBean(3));
//        list.add(new CircleBean(1));
//        list.add(new CircleBean(2));
//        list.add(new CircleBean(3));
//        mAdapter = new CircleAdapter(list);
//        rv.setAdapter(mAdapter);
        backData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private int page = 1;
    ArrayList<CircleBean1> datas = new ArrayList();
    private CircleAdapter1 adapter;

    public void backData() {
        HttpHelper.Cirde_getImagetextTotype(page + "", imagetext_id, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                GetImageTextBean entity = gson.fromJson(succeed, GetImageTextBean.class);
                setAdatper(entity);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    CircleBean1 bean1;

    public void setAdatper(GetImageTextBean entity) {
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
        if (adapter == null) {
            adapter = new CircleAdapter1(datas, getContext());
            adapter.setOnItemChildClickListener(this);
            rv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
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
                    Log.e("aa", "---------------" + datas.get(position).getArray().get(0).getImagetext_id());
                    intent.putExtra("imagetext_id", datas.get(position).getArray().get(0).getImagetext_id() + "");
                    datas.get(position).getArray().get(0).getImagetext_id();
                    intent.setClass(getContext(), DetailCircleImageActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                break;
            case R.id.THREE_SMALL_img2:
            case R.id.RIGHT_BIG_img2:
            case R.id.LEFT_BIG_img2:
                if (datas.get(position).getArray().size() >= 2) {
                    Log.e("aa", "---------------" + datas.get(position).getArray().get(1).getImagetext_id());
                    intent.putExtra("imagetext_id", datas.get(position).getArray().get(1).getImagetext_id() + "");
                    datas.get(position).getArray().get(1).getImagetext_id();
                    intent.setClass(getContext(), DetailCircleImageActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                break;
            case R.id.THREE_SMALL_img3:
            case R.id.RIGHT_BIG_img3:
            case R.id.LEFT_BIG_img3:
                if (datas.get(position).getArray().size() >= 3) {
                    Log.e("aa", "---------------" + datas.get(position).getArray().get(2).getImagetext_id());
                    intent.putExtra("imagetext_id", datas.get(position).getArray().get(2).getImagetext_id() + "");
                    datas.get(position).getArray().get(2).getImagetext_id();
                    intent.setClass(getContext(), DetailCircleImageActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                break;
        }
    }
}
