package com.hykj.liuzhi.androidcomponents.ui.fragment.collect;

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
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailSoftArticleActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailVideoActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.PersonDetailActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.AdvertorialAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.collect.bean.CollectBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.collect.bean.Collectbase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter.FashionAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.base.ViewPagerFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.ui.widget.CustomLoadMoreView;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdvertorialFragment extends ViewPagerFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private AdvertorialAdapter mAdapter;
    private int page = 1;
    private int number = 10;
    String type;
    List<Collectbase> datas = new ArrayList<>();

    public static AdvertorialFragment newInstance(String type1) {
        AdvertorialFragment fragment = new AdvertorialFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_advertorial, container, false);
            ButterKnife.bind(this, rootView);
            initData();
            initView();
            initListener();
        }
        return rootView;
    }
    private void initData() {
        Bundle bundle = new Bundle();
        type = getArguments().getString("type");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        backData(page + "", type);
    }

    private void initView() {

    }

    private void initListener() {

    }

    CollectBean entity;

    public void backData(String page, String type) {
        HttpHelper.getUserCollection(page, type, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, CollectBean.class);
                setAdapterData();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    Collectbase bean;

    public void setAdapterData() {
        switch (type) {
            case "1"://软文
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    bean = new Collectbase(Collectbase.FashionItem1);
                    bean.setSofttext_id(entity.getData().getArray().get(i).getSofttext_id());
                    bean.setUser_nickname(entity.getData().getArray().get(i).getAuthordata().getUser_nickname());
                    bean.setUser_pic(entity.getData().getArray().get(i).getAuthordata().getUser_pic());
                    bean.setUser_id(entity.getData().getArray().get(i).getUser_id());
                    bean.setUser_autograph(entity.getData().getArray().get(i).getAuthordata().getUser_autograph());
                    bean.setSofttextimage_url(entity.getData().getArray().get(i).getSofttextimage().getSofttextimage_url());
                    datas.add(bean);
                }
                break;
            case "2"://美图

                break;
            case "3"://视频
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    bean = new Collectbase(Collectbase.FashionItem2);
                    bean.setUser_id(entity.getData().getArray().get(i).getUser_id());
                    bean.setVideo_id(entity.getData().getArray().get(i).getVideo_id());
                    bean.setCollection_type(entity.getData().getArray().get(i).getCollection_type());
                    bean.setVideo_name(entity.getData().getArray().get(i).getVideodata().getVideo_name());
                    bean.setVideo_image(entity.getData().getArray().get(i).getVideodata().getVideo_image());
                    datas.add(bean);
                }
                break;
            case "4"://商品

                break;
        }
        if (mAdapter == null) {
            mAdapter = new AdvertorialAdapter(getContext(), datas);
            mAdapter.setOnItemClickListener(this);
            mAdapter.setLoadMoreView(new CustomLoadMoreView());
            mAdapter.setOnLoadMoreListener(this, recyclerView);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
            mAdapter.loadMoreComplete();
        }
    }

    Intent intent;

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (type) {
            case "1"://软文
                intent = new Intent(getContext(), DetailSoftArticleActivity.class);
                break;
            case "3"://视频
                Intent intent = new Intent();
                intent.putExtra("videoid", datas.get(position).getVideo_id() + "");
                intent.setClass(getContext(), DetailVideoActivity.class);
                startActivity(intent);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onLoadMoreRequested() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                backData(page + "", type);
            }
        }, 2000);
    }
}