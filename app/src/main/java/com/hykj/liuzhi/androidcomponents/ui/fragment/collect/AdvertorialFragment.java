package com.hykj.liuzhi.androidcomponents.ui.fragment.collect;

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
import com.hykj.liuzhi.androidcomponents.bean.CircleBean1;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailCircleImageActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailSoftArticleActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailVideoActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.GoodDetailActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.AdvertorialAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.collect.bean.CollectBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.collect.bean.Collectbase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.base.ViewPagerFragment;
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

public class AdvertorialFragment extends ViewPagerFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private AdvertorialAdapter mAdapter;
    private int page = 1;
    private int number = 10;
    String type;
    List<Collectbase> datas = new ArrayList<>();
    private SmartRefreshLayout refreshLayout;

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
            refreshLayout = rootView.findViewById(R.id.shop_refreshLayout);
            ButterKnife.bind(this, rootView);
            initData();
            initView();
            initListener();
        }
        return rootView;
    }

    private void initData() {
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
        type = getArguments().getString("type");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        backData();
    }

    private void initView() {

    }

    private void initListener() {

    }

    CollectBean entity;

    public void backData() {
        HttpHelper.getUserCollection(page + "", type + "", new HttpHelper.HttpUtilsCallBack<String>() {
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
                if (page > 1) {
                    Toast.makeText(getContext(), "暂无更多数据！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    Collectbase bean;
    Collectbase bean1;

    public void setAdapterData() {
        switch (type) {
            case "1"://软文
                for (CollectBean.DataBean.ArrayBean arrayBean : entity.getData().getArray()) {
                    bean = new Collectbase(Collectbase.FashionItem1);
                    bean.setSofttext_id(arrayBean.getSofttext_id());
                    bean.setUser_nickname(arrayBean.getAuthordata().getUser_nickname());
                    bean.setUser_pic(arrayBean.getAuthordata().getUser_pic());
                    bean.setUser_id(arrayBean.getUser_id());
                    bean.setUser_autograph(arrayBean.getAuthordata().getUser_autograph());
                    if (arrayBean.getSofttextimage() != null)
                        bean.setSofttextimage_url(arrayBean.getSofttextimage().getSofttextimage_url());
                    datas.add(bean);
                }
                break;
            case "2"://美图
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
                        if (entity.getData().getArray().get(zhi).getImagetextimage() != null) {
                            bean.setImagetextimage_url(entity.getData().getArray().get(zhi).getImagetextimage().getImagetextimage_url());
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
                            bean = new Collectbase(Collectbase.Imge1);
                            bean.setArray(usedata);
                            break;
                        case 1:
                            bean = new Collectbase(Collectbase.Imge2);
                            bean.setArray(usedata);
                            break;
                        case 2:
                            bean = new Collectbase(Collectbase.Imge3);
                            bean.setArray(usedata);
                            break;
                    }
                    datas.add(bean);
                }
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
                int shopingAll = entity.getData().getArray().size();
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    if (ShopingType == 1 || shopingAll == 1) {
                        bean = new Collectbase(Collectbase.Shoping_1);
                        bean.setUser_id(entity.getData().getArray().get(i).getUser_id());
                        bean.setGoods_id(entity.getData().getArray().get(i).getGoods_id());
                        bean.setCollection_type(entity.getData().getArray().get(i).getCollection_type());
                        bean.setGoods_pic(entity.getData().getArray().get(i).getGoodsdata().getGoods_pic());
                        bean.setGoods_name(entity.getData().getArray().get(i).getGoodsdata().getGoods_name());
                        bean.setGoods_price(entity.getData().getArray().get(i).getGoodsdata().getGoods_price());
                        ShopingType = 2;
                        shopingAll--;
                    } else {
                        bean = new Collectbase(Collectbase.Shoping_2);
                        List<CircleBean1.ArrayBean> usedata = new ArrayList<>();
                        CircleBean1.ArrayBean bean1 = new CircleBean1.ArrayBean();
                        bean1.setUser_id(entity.getData().getArray().get(i).getUser_id());
                        bean1.setGoods_id(entity.getData().getArray().get(i).getGoods_id());
                        bean1.setCollection_type(entity.getData().getArray().get(i).getCollection_type());
                        bean1.setGoods_pic(entity.getData().getArray().get(i).getGoodsdata().getGoods_pic());
                        bean1.setGoods_name(entity.getData().getArray().get(i).getGoodsdata().getGoods_name());
                        bean1.setGoods_price(entity.getData().getArray().get(i).getGoodsdata().getGoods_price());
                        usedata.add(bean1);
                        CircleBean1.ArrayBean bean2 = new CircleBean1.ArrayBean();
                        bean2.setUser_id(entity.getData().getArray().get(i + 1).getUser_id());
                        bean2.setGoods_id(entity.getData().getArray().get(i + 1).getGoods_id());
                        bean2.setCollection_type(entity.getData().getArray().get(i + 1).getCollection_type());
                        bean2.setGoods_pic(entity.getData().getArray().get(i + 1).getGoodsdata().getGoods_pic());
                        bean2.setGoods_name(entity.getData().getArray().get(i + 1).getGoodsdata().getGoods_name());
                        bean2.setGoods_price(entity.getData().getArray().get(i + 1).getGoodsdata().getGoods_price());
                        usedata.add(bean2);
                        bean.setArray(usedata);
                        ShopingType = 1;
                        shopingAll = shopingAll - 2;
                        i++;
                    }
                    datas.add(bean);
                }
                break;
        }
        if (mAdapter == null) {
            mAdapter = new AdvertorialAdapter(getContext(), datas);
            mAdapter.setOnItemClickListener(this);
            mAdapter.setOnItemChildClickListener(this);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    Intent intent;
    private int ShopingType = 1;

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        intent = new Intent();
        switch (type) {
            case "1"://软文
                intent.putExtra("softtextid", datas.get(position).getSofttext_id() + "");
                intent.putExtra("stType", "个人类型");
                intent.setClass(getContext(), DetailSoftArticleActivity.class);
                startActivity(intent);
                break;
            case "3"://视频
                intent.putExtra("videoid", datas.get(position).getVideo_id() + "");
                intent.putExtra("stType", "个人类型");
                intent.setClass(getContext(), DetailVideoActivity.class);
                startActivity(intent);
                break;
        }
    }

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
            case R.id.shop1:
                intent.setClass(getContext(), GoodDetailActivity.class);
                intent.putExtra("goodsid", datas.get(position).getGoods_id() + "");
                startActivity(intent);
                break;
            case R.id.shop2:
                intent.setClass(getContext(), GoodDetailActivity.class);
                intent.putExtra("goodsid", datas.get(position).getArray().get(0).getGoods_id() + "");
                startActivity(intent);
                break;
            case R.id.shop3:
                intent.setClass(getContext(), GoodDetailActivity.class);
                intent.putExtra("goodsid", datas.get(position).getArray().get(1).getGoods_id() + "");
                startActivity(intent);
                break;
        }
    }
}