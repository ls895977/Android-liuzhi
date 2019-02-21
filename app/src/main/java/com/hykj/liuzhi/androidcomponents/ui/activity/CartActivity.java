package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.MainActivity;
import com.hykj.liuzhi.androidcomponents.bean.CartBean;
import com.hykj.liuzhi.androidcomponents.bean.ChangeshopcarBean;
import com.hykj.liuzhi.androidcomponents.bean.ConfirmOrderBean;
import com.hykj.liuzhi.androidcomponents.bean.Shop_intermediatedataBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.adapter.CartAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.CollectionBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopHomeBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.ui.widget.DefaultTopBar;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * 购物车
 *
 * @author: lujialei
 * @date: 2018/10/16
 * @describe:
 */
public class CartActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener, View.OnClickListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    ACache aCache;
    private TextView allPrice, chose, tvRight, rl_bottom_right;
    private LinearLayout Settlement, shop_delte;
    ZLoadingDialog dialog ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        aCache = ACache.get(this);
        allPrice = findViewById(R.id.all_price);
        rl_bottom_right = findViewById(R.id.rl_bottom_right);
        rl_bottom_right.setOnClickListener(this);
        findViewById(R.id.iv_left).setOnClickListener(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.tv_delte).setOnClickListener(this);
        chose = findViewById(R.id.tv_allchose);
        chose.setOnClickListener(this);
        tvRight = findViewById(R.id.tv_right);
        tvRight.setOnClickListener(this);
        Settlement = findViewById(R.id.shop_jiesuan);
        shop_delte = findViewById(R.id.shop_delte);
        dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据提交中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        showShopCar();
    }
    int page = 1;
    private CartAdapter adapter;
    List<CartBean.DataBean.ArrayBean> datas = new ArrayList<>();

    List<CartBean.DataBean.ArrayBean> datasXuanz = new ArrayList<>();

    public void showShopCar() {
        dialog.show();
        HttpHelper.showshopcar(page + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(CartActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                dialog.dismiss();
                CartBean entity = FastJSONHelper.getPerson(succeed, CartBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    CartBean.DataBean.ArrayBean bean = entity.getData().getArray().get(i);
                    DecimalFormat df = new DecimalFormat( "0.00");
                    double dnajia = Double.valueOf(df.format(Double.valueOf(bean.getGoodsshopcar_price()) / bean.getGoodsshopcar_num()));
                    bean.setDanjiaprice(dnajia);
                    bean.setCartShop(false);
                    datas.add(bean);
                }
                if (adapter == null) {
                    adapter = new CartAdapter(datas, CartActivity.this);
                    adapter.setOnItemChildClickListener(CartActivity.this);
                    adapter.setOnItemClickListener(CartActivity.this);
                    rv.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(CartActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.tv_jian://减
                if (datas.get(position).getGoodsshopcar_num() == 1) {
                    Toast.makeText(CartActivity.this, "该宝贝不能减少了哟！", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.show();
                changeshopcar(position, datas.get(position).getGoodsshopcar_id(), (datas.get(position).getGoodsshopcar_num() - 1));
                break;
            case R.id.tv_add://加
                changeshopcar(position, datas.get(position).getGoodsshopcar_id(), (datas.get(position).getGoodsshopcar_num() + 1));
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (datas.get(position).isCartShop()) {
            datas.get(position).setCartShop(false);
        } else {
            datas.get(position).setCartShop(true);
        }
        adapter.notifyDataSetChanged();
        addPrice();
    }

    private Double zongjia = 0.0;
    private List<String> shopcaridList = new ArrayList<>();
    private List<String> shopgoodsidList = new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_allchose://全选
                if (chose.isSelected()) {
                    chose.setSelected(false);
                    for (int i = 0; i < datas.size(); i++) {
                        datas.get(i).setCartShop(false);
                    }
                } else {
                    chose.setSelected(true);
                    for (int i = 0; i < datas.size(); i++) {
                        datas.get(i).setCartShop(true);
                    }
                }
                adapter.notifyDataSetChanged();
                addPrice();
                break;
            case R.id.rl_bottom_right://结算
                datasXuanz.clear();
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).isCartShop()) {
                        datasXuanz.add(datas.get(i));
                    }
                }
                if (datasXuanz.size() == 0) {
                    Toast.makeText(CartActivity.this, "请选择一个商品！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Shop_intermediatedata();
                break;
            case R.id.iv_left://返回
                finish();
                break;
            case R.id.tv_right://管理
                if (tvRight.getText().toString().equals("管理")) {
                    tvRight.setText("完成");
                    shop_delte.setVisibility(View.VISIBLE);
                    Settlement.setVisibility(View.GONE);
                } else {
                    tvRight.setText("管理");
                    shop_delte.setVisibility(View.GONE);
                    Settlement.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_delte://删除
                shopcarid = "";
                shopcaridList.clear();
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).isCartShop()) {
                        shopcaridList.add(datas.get(i).getGoodsshopcar_id() + "");
                        shopgoodsidList.add(datas.get(i).getGoods_id() + "");
                    }
                }
                if (shopcaridList.size() == 0) {
                    Toast.makeText(CartActivity.this, "请选择一个您要删除的物品！", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int j = 0; j < shopcaridList.size(); j++) {
                    if (j == shopcaridList.size() - 1) {
                        shopcarid += shopcaridList.get(j);
                    } else {
                        shopcarid += shopcaridList.get(j) + ",";
                    }
                }
                deleteShopCar();
                break;
        }
    }

    /**
     * 计算出总价格
     */
    public void addPrice() {
        zongjia = 0.0;
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isCartShop()) {
                zongjia += (datas.get(i).getGoodsshopcar_num() * datas.get(i).getDanjiaprice());
            }
        }
        DecimalFormat df = new DecimalFormat( "0.00");
        rl_bottom_right.setText("结算(" + df.format(zongjia) + ")");
        allPrice.setText("合计:" + df.format(zongjia));
    }

    private String shopcarid = "";

    /**
     * 删除商品
     */
    public void deleteShopCar() {
        HashMap<String, String> map = new HashMap<>();
        map.put("userid", aCache.getAsString("user_id"));
        if (shopcaridList.size() == 1) {
            map.put("shopcarid", shopcarid);
        } else {
            map.put("shopcarids", shopcarid);
        }
        dialog.show();
        HttpHelper.deleteShopCar(map, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(CartActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                dialog.dismiss();
                CollectionBean entity = FastJSONHelper.getPerson(succeed, CollectionBean.class);
                if (entity.getCode() == 0 && entity.getError() == 0) {
                    Toast.makeText(CartActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                    datas.clear();
                    showShopCar();
                }
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(CartActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 确认订单页面中间的商品数据
     */
    private String shopcarids = "", goodsid = "", goodsnum = "";

    public void Shop_intermediatedata() {
        for (int i = 0; i < datas.size(); i++) {
            if (i == (datas.size() - 1)) {
                shopcarids += datas.get(i).getGoodsshopcar_id();
                goodsid += datas.get(i).getGoods_id();
                goodsnum += datas.get(i).getGoodsshopcar_num();
            } else {
                shopcarids += datas.get(i).getGoodsshopcar_id() + ",";
                goodsid += datas.get(i).getGoods_id() + ",";
                goodsnum += datas.get(i).getGoodsshopcar_num() + ",";
            }
        }
        dialog.show();
        HttpHelper.Shop_intermediatedata("", "", shopcarids, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(CartActivity.this, failure, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSucceed(String succeed) {
                dialog.dismiss();
                Shop_intermediatedataBean entity = FastJSONHelper.getPerson(succeed, Shop_intermediatedataBean.class);
                if (entity.getCode() == 0 && entity.getMsg().equals("访问成功")) {
                    Intent intent = new Intent();
                    intent.setClass(CartActivity.this, ConfirmOrderActivity.class);
                    intent.putExtra("data", (Serializable) datasXuanz);
                    startActivity(intent);
                }
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(CartActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 修改购物车商品数量
     */
    public void changeshopcar(final int position, String shopcardid, final int num) {
        HttpHelper.Shop_changeshopcar(aCache.getAsString("user_id"), shopcardid, num + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(CartActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gosn = new Gson();
                dialog.dismiss();
                ChangeshopcarBean bean = gosn.fromJson(succeed, ChangeshopcarBean.class);
                if (bean.getCode() == 0 && bean.getError() == 0) {
                    datas.get(position).setGoodsshopcar_num(num);
                    addPrice();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(CartActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
