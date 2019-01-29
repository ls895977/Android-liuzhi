package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.MainActivity;
import com.hykj.liuzhi.androidcomponents.bean.CartBean;
import com.hykj.liuzhi.androidcomponents.bean.ConfirmOrderBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.bean.AllAddBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.SelectAdressListAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.CollectionBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.GoodDetailDetailBean;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;

import java.util.List;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2018/10/25
 * @describe:
 */
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {
    GoodDetailDetailBean bean;
    private TextView tvAdd, shopName, shopPrice, btPrice, price_num;
    private ImageView imageView;
    private EditText liuyan;
    private CheckBox deductibletype;
    private ACache aCache;
    private CheckBox cb, cb1;
    private LinearLayout llView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initView();
        initData();
    }

    View shopCartView;

    public void initView() {
        aCache = ACache.get(this);
        tvAdd = findViewById(R.id.oder_tv_add);
        llView = findViewById(R.id.shop_view);
        findViewById(R.id.oder_commit).setOnClickListener(this);
        findViewById(R.id.oder_addr).setOnClickListener(this);
        btPrice = findViewById(R.id.order_bt_price);
        liuyan = findViewById(R.id.order_shopliuYan);
        deductibletype = findViewById(R.id.oder_deductibletype);
        cb = findViewById(R.id.confirm_rd1);
        cb1 = findViewById(R.id.confirm_rd2);
        deductibletype.setOnClickListener(this);
        cb.setOnClickListener(this);
        cb1.setOnClickListener(this);
        findViewById(R.id.iv_left).setOnClickListener(this);
        if (getIntent().getSerializableExtra("bean") != null) {
            bean = (GoodDetailDetailBean) getIntent().getSerializableExtra("bean");
            shopCartView = LayoutInflater.from(this).inflate(R.layout.view_order, null);
            imageView = shopCartView.findViewById(R.id.order_shopimg);
            shopName = shopCartView.findViewById(R.id.order_shopName);
            shopPrice = shopCartView.findViewById(R.id.order_shopPrice);
            price_num = shopCartView.findViewById(R.id.order_shopPrice_num);
            if (bean.getData().getImage() != null) {
                Glide.with(this).load(bean.getData().getImage().get(0)).into(imageView);
            }
            shopName.setText(bean.getData().getName());
            shopPrice.setText("¥" + bean.getData().getPrice());
            btPrice.setText("¥" + bean.getData().getPrice() + "元");
            llView.addView(shopCartView);
            goodsid = bean.getData().getGid() + "";
        }
        if (getIntent().getSerializableExtra("data") != null) {
            List<CartBean.DataBean.ArrayBean> datas = (List<CartBean.DataBean.ArrayBean>) getIntent().getSerializableExtra("data");
            for (int i = 0; i < datas.size(); i++) {
                shopCartView = LayoutInflater.from(this).inflate(R.layout.view_order, null);
                shopCartView.setPadding(0, 10, 20, 0);
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
                layoutParam.setMargins(10, 5, 10, 0);
                shopCartView.setLayoutParams(layoutParam);
                imageView = shopCartView.findViewById(R.id.order_shopimg);
                shopName = shopCartView.findViewById(R.id.order_shopName);
                shopPrice = shopCartView.findViewById(R.id.order_shopPrice);
                price_num = shopCartView.findViewById(R.id.order_shopPrice_num);
                Glide.with(this).load(datas.get(i).getGoodsdata().getGoods_pic()).into(imageView);
                shopName.setText(datas.get(i).getGoodsdata().getGoods_name());
                shopPrice.setText("¥" + datas.get(i).getGoodsshopcar_price());
                price_num.setText("×" + datas.get(i).getGoodsshopcar_num());
                llView.addView(shopCartView);
                if (i == (datas.size() - 1)) {
                    shopcarids += datas.get(i).getGoods_id();
                } else {
                    shopcarids += datas.get(i).getGoods_id() + ",";
                }
                goodsnum += datas.get(i).getGoodsshopcar_num();
            }
            /**
             * 计算出总价格
             */
            int zongjia = 0;
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).isCartShop()) {
                    zongjia += (datas.get(i).getGoodsshopcar_num() * datas.get(i).getDanjiaprice());
                }
            }
            btPrice.setText("¥" + zongjia + "元");
        }


    }

    public void initData() {
        postBackAddData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oder_addr://选择地址
                Intent intent = new Intent(ConfirmOrderActivity.this, SelectAdressActivity.class);
                intent.putExtra("title", "ConfirmOrderActivity");
                startActivityForResult(intent, 11);
                break;
            case R.id.oder_commit://提交订单
                addorders();
                break;
            case R.id.oder_deductibletype:
                if (deductibletype.isChecked()) {
                    stDeductibletype = "1";
                } else {
                    stDeductibletype = "0";
                }
                break;
            case R.id.confirm_rd1://微信支付
                paymentmethod = "1";
                cb.setChecked(true);
                cb1.setChecked(false);
                break;
            case R.id.confirm_rd2://支付宝支付
                paymentmethod = "2";
                cb.setChecked(false);
                cb1.setChecked(true);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }

    String addressid, addrname, stDeductibletype = "0", paymentmethod = "1", shopcarids = "", goodsid = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == 11) {
            addressid = data.getStringExtra("addressid");
            addrname = data.getStringExtra("addrname");
            tvAdd.setText(addrname);
        }
    }


    /**
     * 订单生成
     * @param
     */
    private int goodsnum = 1;
    public void addorders() {
        if (TextUtils.isEmpty(addressid)) {
            Toast.makeText(getContext(), "请选择地址", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.e("aa", "----------user_id--------" + aCache.getAsString("user_id") + "/n" +
                "----------addressid--------" + addressid + "---/n" +
                "-----goodsnum-----" + goodsnum + "------/n"
                + "--------shopcarids----" + shopcarids + "------/n" +
                "----paymentmethod------" + paymentmethod + "---------/n"
                + "-----liuyan--------" + liuyan.getText().toString() + "-----/n" +
                "-------stDeductibletype-----" + stDeductibletype);

        HttpHelper.addorders(aCache.getAsString("user_id"),
                addressid,
                goodsid,
                goodsnum + "",
                shopcarids,
                paymentmethod,
                liuyan.getText().toString(),
                stDeductibletype,
                new HttpHelper.HttpUtilsCallBack<String>() {
                    @Override
                    public void onFailure(String failure) {
                        Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSucceed(String succeed) {
                        Log.e("aa", "----订单提交成功------" + succeed);
                        ConfirmOrderBean entity = FastJSONHelper.getPerson(succeed, ConfirmOrderBean.class);
                        if (entity.getMsg().equals("访问成功")) {
                            Toast.makeText(getContext(), "订单提交成功！", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent();
                            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent1.setClass(ConfirmOrderActivity.this, MainActivity.class);
                            startActivity(intent1);
                        }

                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**
     * 获取默认地址
     */
    public void postBackAddData() {
        HttpHelper.getUserAddress(aCache.getAsString("user_id"), "1", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                AllAddBean entity = FastJSONHelper.getPerson(succeed, AllAddBean.class);
                if (entity.getData().getArray().size() == 0) {
                    return;
                }
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    Log.e("aa", "---getAddress_status-------" + entity.getData().getArray().get(i).getAddress_status());
                    if (entity.getData().getArray().get(i).getAddress_status() == 1) {
                        tvAdd.setText(entity.getData().getArray().get(i).getAddress_address());
                    }
                }

            }

            @Override
            public void onError(String error) {
            }
        });
    }
}
