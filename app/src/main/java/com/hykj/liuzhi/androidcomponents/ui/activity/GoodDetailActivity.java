package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailCommentAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.CollectionBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.GetsowingBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.GoodDetailDetailBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.addshopcarBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.GoodsDetailHeader;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * 查看商品
 *
 * @author: lujialei
 * @date: 2018/10/25
 * @describe:
 */
public class GoodDetailActivity extends BaseActivity {
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    private ACache aCache;
    LinearLayout myLinear;
    TextView checkBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    private void initListener() {
        tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("bean", entity);
                intent.setClass(getBaseContext(), ConfirmOrderActivity.class);
                startActivity(intent);
            }
        });
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_addshopcar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//添加购物车
                addshopcar(getIntent().getStringExtra("goodsid"));
            }
        });
    }

    DetailCommentAdapter adapter;
    private WebView tv_context;

    private void initView() {
        aCache = ACache.get(this);
        myLinear = findViewById(R.id.goods_detail_ll);
        tv_context = findViewById(R.id.goods_detail_context);
        checkBox = findViewById(R.id.goods_detail_CheckBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isSelected()) {
                    goodsnotcollection(getIntent().getStringExtra("goodsid"));
                } else {
                    goodscollection(getIntent().getStringExtra("goodsid"));
                }
            }
        });
        WebSettings webSettings = tv_context.getSettings();
        webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
        webSettings.setJavaScriptEnabled(true);//支持js
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        backShowgoods(getIntent().getStringExtra("goodsid"));
    }
    private GoodDetailDetailBean entity;
    public void backShowgoods(String goodsid) {
        HttpHelper.showgoods(goodsid, aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, GoodDetailDetailBean.class);
                if (entity.getCode() == 0) {
//                    adapter = new DetailCommentAdapter(list);
                    myLinear.addView(new GoodsDetailHeader(getBaseContext(), entity));
                    tv_context.loadDataWithBaseURL(null, entity.getData().getDetail(), "text/html", "UTF-8", null);
                    if (entity.getData().getUsercollection() == 1) {
                        checkBox.setSelected(true);
                    } else {
                        checkBox.setSelected(false);
                    }
                }

            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 收藏
     *
     * @param goodsid
     */
    public void goodscollection(String goodsid) {
        HttpHelper.goodscollection(goodsid, aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                CollectionBean entity = FastJSONHelper.getPerson(succeed, CollectionBean.class);
                if (entity.getCode() == 0) {
                    if (entity.getMsg().equals("访问成功")) {
                        checkBox.setSelected(true);
                        Toast.makeText(getContext(), "收藏成功！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 取消收藏
     *
     * @param goodsid
     */
    public void goodsnotcollection(String goodsid) {
        HttpHelper.goodsnotcollection(goodsid, aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                CollectionBean entity = FastJSONHelper.getPerson(succeed, CollectionBean.class);
                if (entity.getCode() == 0) {
                    if (entity.getMsg().equals("访问成功")) {
                        checkBox.setSelected(false);
                        Toast.makeText(getContext(), "取消收藏成功！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 添加购物车
     * @param goodsid
     */
    public void addshopcar(String goodsid) {
        HttpHelper.addshopcar(goodsid, "1", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                addshopcarBean entity = FastJSONHelper.getPerson(succeed, addshopcarBean.class);
                if (entity.getCode() == 0) {
                    if (entity.getError() == 0) {
                        checkBox.setSelected(false);
                        Toast.makeText(getContext(), "添加成功！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
