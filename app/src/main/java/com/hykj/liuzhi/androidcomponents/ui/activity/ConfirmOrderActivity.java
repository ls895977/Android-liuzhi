package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.MainActivity;
import com.hykj.liuzhi.androidcomponents.bean.AppPayBean;
import com.hykj.liuzhi.androidcomponents.bean.AuthResult;
import com.hykj.liuzhi.androidcomponents.bean.CartBean;
import com.hykj.liuzhi.androidcomponents.bean.ConfirmOrderBean;
import com.hykj.liuzhi.androidcomponents.bean.PayResult;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.bean.AllAddBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.GoodDetailDetailBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.List;
import java.util.Map;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2018/10/25
 * @describe:
 */
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {
    GoodDetailDetailBean DetailBean;
    private TextView tvAdd, shopName, shopPrice, btPrice, price_num,shouhuoren,phone;
    private ImageView imageView;
    private EditText liuyan;
    private CheckBox deductibletype;
    private ACache aCache;
    private CheckBox cb, cb1;
    private LinearLayout llView;
    ZLoadingDialog dialog ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        WXPayEntryActivity.actStatus="ConfirmOrderActivity";
        dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据提交中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        initView();
        initData();
    }

    View shopCartView;

    public void initView() {
        aCache = ACache.get(this);
        shouhuoren=findViewById(R.id.shouhuoren);
        tvAdd = findViewById(R.id.oder_tv_add);
        phone=findViewById(R.id.phone);
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
            DetailBean = (GoodDetailDetailBean) getIntent().getSerializableExtra("bean");
            shopCartView = LayoutInflater.from(this).inflate(R.layout.view_order, null);
            imageView = shopCartView.findViewById(R.id.order_shopimg);
            shopName = shopCartView.findViewById(R.id.order_shopName);
            shopPrice = shopCartView.findViewById(R.id.order_shopPrice);
            price_num = shopCartView.findViewById(R.id.order_shopPrice_num);
            if (DetailBean.getData().getImage() != null) {
                Glide.with(this).load(DetailBean.getData().getImage().get(0)).into(imageView);
            }
            shopName.setText(DetailBean.getData().getName());
            shopPrice.setText("¥" + DetailBean.getData().getPrice());
            btPrice.setText("¥" + DetailBean.getData().getPrice() + "元");
            llView.addView(shopCartView);
            goodsid = DetailBean.getData().getGid() + "";
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
                    shopcarids += datas.get(i).getGoodsshopcar_id();
                } else {
                    shopcarids += datas.get(i).getGoodsshopcar_id() + ",";
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
                if (getIntent().getSerializableExtra("data") != null) {
                    addorders1();
                } else {
                    addorders();
                }
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
     *
     * @param
     */
    private int goodsnum = 1;
    public void addorders() {
        if (TextUtils.isEmpty(addressid)) {
            Toast.makeText(getContext(), "请选择地址", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.show();
        HttpHelper.addorders(aCache.getAsString("user_id"),
                addressid,
                goodsid,
                goodsnum + "",
                "",
                paymentmethod,
                liuyan.getText().toString(),
                stDeductibletype,
                new HttpHelper.HttpUtilsCallBack<String>() {
                    @Override
                    public void onFailure(String failure) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSucceed(String succeed) {
                        entity = FastJSONHelper.getPerson(succeed, ConfirmOrderBean.class);
                        if (entity.getMsg().equals("访问成功")) {
                            appPayOrders(Integer.valueOf(paymentmethod));
                        }
                    }

                    @Override
                    public void onError(String error) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 订单生成
     * 购物车的时候进来
     *
     * @param
     */
    ConfirmOrderBean entity;
    public void addorders1() {
        if (TextUtils.isEmpty(addressid)) {
            Toast.makeText(getContext(), "请选择地址", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.show();
        HttpHelper.addorders(
                aCache.getAsString("user_id"),
                addressid,
                "",
                "",
                shopcarids,
                paymentmethod,
                liuyan.getText().toString(),
                stDeductibletype,
                new HttpHelper.HttpUtilsCallBack<String>() {
                    @Override
                    public void onFailure(String failure) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSucceed(String succeed) {
                        entity = FastJSONHelper.getPerson(succeed, ConfirmOrderBean.class);
                        if (entity.getMsg().equals("访问成功")) {
                            appPayOrders(Integer.valueOf(paymentmethod));
                        }
                    }

                    @Override
                    public void onError(String error) {
                        dialog.dismiss();
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
                    if (entity.getData().getArray().get(i).getAddress_status() == 1) {
                        addressid = entity.getData().getArray().get(i).getAddress_id() + "";
                        tvAdd.setText(entity.getData().getArray().get(i).getAddress_address());
                        shouhuoren.setText("收货人："+entity.getData().getArray().get(i).getAddress_user());
                        phone.setText(entity.getData().getArray().get(i).getAddress_phone());
                    }
                }

            }

            @Override
            public void onError(String error) {
            }
        });
    }

    /**
     * 支付接口
     * @param paytype
     */
    AppPayBean bean;
    public void appPayOrders(final int paytype) {
        HttpHelper.payOrders(entity.getData().getOrders_id() + "", paytype + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                bean = gson.fromJson(succeed, AppPayBean.class);
                if (bean.getCode() == 0) {
                    dialog.dismiss();
                    switch (paytype) {
                        case 1://微信
                            appPlayWX();
                            break;
                        case 2://支付宝
                            appPlayZFB();
                            break;
                    }
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static final int SDK_PAY_FLAG = 1;

    public void appPlayZFB() {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ConfirmOrderActivity.this);
                Map<String, String> result = alipay.payV2(bean.getAlipay().getStr(), true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public void appPlayWX() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api =api = WXAPIFactory.createWXAPI(this, null);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
        PayReq req = new PayReq();
//给req对象赋值
        req.appId = bean.getWxpay().getData().getAppid();//APPID
        req.partnerId = bean.getWxpay().getData().getPartnerid();//    商户号
        req.prepayId = bean.getWxpay().getData().getPrepayid();//  预付款ID
        req.nonceStr = bean.getWxpay().getData().getNoncestr();//随机数
        req.timeStamp = bean.getWxpay().getData().getTimestamp() + "";//时间戳
        req.packageValue = "Sign=WXPay";//固定值Sign=WXPay
        req.sign = bean.getWxpay().getData().getSign();//签名
        api.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求
    }

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;
    private static final String APP_ID = "wx153551c2cce0e6a8";

    private static final int SDK_AUTH_FLAG = 2;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Log.e("aa", "-------------支付成功--");
                        Intent intent1 = new Intent();
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent1.setClass(ConfirmOrderActivity.this, MainActivity.class);
                        startActivity(intent1);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Log.e("aa", "-------------支付失败--");
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Log.e("aa", "-------------授权成功--");
                    } else {
                        // 其他状态值则为授权失败
                        Log.e("aa", "-------------其他状态值则为授权失败--");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
}
