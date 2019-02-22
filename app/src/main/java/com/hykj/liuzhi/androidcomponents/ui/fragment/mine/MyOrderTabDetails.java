package com.hykj.liuzhi.androidcomponents.ui.fragment.mine;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.AppPayBean;
import com.hykj.liuzhi.androidcomponents.bean.AuthResult;
import com.hykj.liuzhi.androidcomponents.bean.CancellPayBean;
import com.hykj.liuzhi.androidcomponents.bean.ConfirmationOfOrderBean;
import com.hykj.liuzhi.androidcomponents.bean.MineFileBean;
import com.hykj.liuzhi.androidcomponents.bean.PayResult;
import com.hykj.liuzhi.androidcomponents.net.http.ApiConstant;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.BaseActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.EditUserDataActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_Play;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.Act_MyOrder;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MyOrderChlideTabAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.MyOrderTabDetailsBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserordersBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.TimeUtils;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;
import com.hykj.liuzhi.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.IProgressDialog;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * 订单详情
 */
public class MyOrderTabDetails extends BaseActivity implements View.OnClickListener, Dlg_Play.OnClick {
    private ACache aCache;
    private UserordersBean.DataBean.ArrayBean bean;
    private RelativeLayout item1, item2, item3;
    private TextView daojishi, oder_status, address_user, address_phone, address_address, orders_goodsmoney, deductible_money, orders_ordersmoney, orders_integral, orders_number, orders_creattime, orders_paymenttime, orders_deliverytime;
    private RecyclerView recyclerView;
    ZLoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_myordertabdetails);
        initView();
    }

    public void initView() {
        dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据提交中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        new TitleBuilder(MyOrderTabDetails.this).setTitleText("订单详情").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        oder_status = findViewById(R.id.oder_status);
        address_user = findViewById(R.id.address_user);
        address_phone = findViewById(R.id.address_phone);
        address_address = findViewById(R.id.address_address);
        orders_goodsmoney = findViewById(R.id.orders_goodsmoney);
        deductible_money = findViewById(R.id.deductible_money);
        orders_ordersmoney = findViewById(R.id.orders_ordersmoney);
        orders_paymenttime = findViewById(R.id.orders_paymenttime);
        orders_number = findViewById(R.id.orders_number);
        orders_integral = findViewById(R.id.orders_integral);
        orders_creattime = findViewById(R.id.orders_creattime);
        daojishi = findViewById(R.id.daojishi);
        orders_deliverytime = findViewById(R.id.orders_deliverytime);
        recyclerView = findViewById(R.id.myRecyclerView);
        item1 = findViewById(R.id.oder_fukuan);
        item2 = findViewById(R.id.oder_shouhuo);
        item3 = findViewById(R.id.oder_wancheng);
        aCache = ACache.get(this);
        bean = (UserordersBean.DataBean.ArrayBean) getIntent().getSerializableExtra("bean");
        oder_status.setText(bean.getOrdertype());
        Debug.e("-----状态-----" + bean.getOrdertype());
        switch (bean.getOrdertype()) {
            case "待付款":
                item1.setVisibility(View.VISIBLE);
                item2.setVisibility(View.GONE);
                item3.setVisibility(View.GONE);
                findViewById(R.id.oder_cancel).setOnClickListener(this);
                findViewById(R.id.oder_pay).setOnClickListener(this);
                break;
            case "待收货":
                if (bean.getOrders_deliverystatus() == 1) {
                    oder_status.setText("已发货");
                }
                item1.setVisibility(View.GONE);
                item2.setVisibility(View.VISIBLE);
                item3.setVisibility(View.GONE);
                findViewById(R.id.oder_wuliu).setOnClickListener(this);
                findViewById(R.id.oder_centerAddr).setOnClickListener(this);
                break;
            case "已完成":
                item1.setVisibility(View.GONE);
                item2.setVisibility(View.GONE);
                item3.setVisibility(View.VISIBLE);
                findViewById(R.id.oder_delete).setOnClickListener(this);
                break;
        }
        showorders();
    }

    String curre = "";
    MyOrderTabDetailsBean entity;

    public void showorders() {
        dialog.show();
        HttpHelper.showorders(bean.getOrders_id() + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                dialog.dismiss();
                entity = FastJSONHelper.getPerson(succeed, MyOrderTabDetailsBean.class);
                address_user.setText("收货人：" + entity.getData().getAddress_user());
                address_phone.setText(entity.getData().getAddress_phone());
                address_address.setText(entity.getData().getAddress_address());

                List<UserordersBean.DataBean.ArrayBean.GoodsdataBean> data = new ArrayList<>();
                for (int i = 0; i < entity.getData().getGoodsdata().size(); i++) {
                    UserordersBean.DataBean.ArrayBean.GoodsdataBean bean = new UserordersBean.DataBean.ArrayBean.GoodsdataBean();
                    bean.setGoods_id(entity.getData().getGoodsdata().get(i).getGoods_id());
                    bean.setOrdersgoods_num(entity.getData().getGoodsdata().get(i).getOrdersgoods_num());
                    bean.setOrdersgoods_money(entity.getData().getGoodsdata().get(i).getOrdersgoods_money());
                    bean.setGoods_pic(entity.getData().getGoodsdata().get(i).getGoods_pic());
                    bean.setGoods_name(entity.getData().getGoodsdata().get(i).getGoods_name());
                    Double danjia = Double.valueOf(entity.getData().getGoodsdata().get(i).getOrdersgoods_money()) / Double.valueOf(entity.getData().getGoodsdata().get(i).getOrdersgoods_num());
                    bean.setDanjiaprice(danjia);
                    data.add(bean);
                }
                MyOrderChlideTabAdapter adapter = new MyOrderChlideTabAdapter(data, getContext());
                orders_ordersmoney.setText("￥" + addPrice(data));
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
                orders_goodsmoney.setText("￥" + entity.getData().getOrders_goodsmoney());
                deductible_money.setText("￥" + entity.getData().getDeductible_money());
                orders_goodsmoney.setText("￥" + entity.getData().getOrders_goodsmoney());
                orders_integral.setText("返神奇种子" + entity.getData().getOrders_integral() + "颗");
                orders_number.setText(entity.getData().getOrders_number());
                orders_creattime.setText(TimeUtils.YMDHMS(entity.getData().getOrders_creattime() + ""));
                if (entity.getData().getOrders_paymenttime() != null) {
                    orders_paymenttime.setText(TimeUtils.YMDHMS(entity.getData().getOrders_paymenttime()));
                }
                if (entity.getData().getOrders_deliverytime() != null) {
                    orders_deliverytime.setText(TimeUtils.YMDHMS(entity.getData().getOrders_deliverytime()));
                }
                startRun();
//                getTimeDuring(TimeUtils.YMDHMSGESHI(entity.getData().getOrders_creattime() + ""));
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    Dlg_Play play;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.oder_cancel://取消订单
                dialog.show();
                appCancellPay();
                break;
            case R.id.oder_pay://立即付款
                curre = "1";
                WXPayEntryActivity.actStatus = "MyOrderTabFragment";
                Double sum = 0.0;
                for (int i = 0; i < bean.getGoodsdata().size(); i++) {
                    sum += Double.valueOf(bean.getGoodsdata().get(i).getOrdersgoods_money());
                }
                String money = sum + "";
                play = new Dlg_Play(MyOrderTabDetails.this, this);
                play.setTvPrice(money);
                play.show();
                break;
            case R.id.oder_wuliu://物流
                if (bean.getOrders_deliverystatus() == 1) {
                    Intent intent = new Intent(MyOrderTabDetails.this, Act_ViewLogistics.class);
                    intent.putExtra("ordersid", bean.getOrders_id() + "");
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "卖家还未发货！暂无物流信息！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.oder_centerAddr://确认收货
                if (bean.getOrders_deliverystatus() == 1) {
                    dialog.show();
                    Shop_confirmationOfOrder();
                } else {
                    Toast.makeText(getContext(), "卖家还未发货！暂时不能做此操作！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.oder_delete://删除订单
                dialog.show();
                Shop_deleteorders();
                break;
        }
    }

    /**
     * 删除订单
     */
    public void Shop_deleteorders() {
        HttpHelper.Shop_deleteorders(aCache.getAsString("user_id"), bean.getOrders_id() + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ConfirmationOfOrderBean entity = FastJSONHelper.getPerson(succeed, ConfirmationOfOrderBean.class);
                if (entity.getError() == 0) {
                    Toast.makeText(getContext(), "删除订单成功！", Toast.LENGTH_SHORT).show();
                    setResult(10);
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 取消订单
     */
    public void appCancellPay() {
        HttpHelper.CancellationOfOrder(bean.getOrders_id() + "", aCache.getAsString("user_id") + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                CancellPayBean cancellPayBean = gson.fromJson(succeed, CancellPayBean.class);
                if (cancellPayBean.getError() == 0) {
                    Toast.makeText(getContext(), "取消成功", Toast.LENGTH_SHORT).show();
                    setResult(10);
                    finish();
                }
                dialog.dismiss();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onItem(int p) {
        play.dismiss();
        appPayOrders(p);
    }

    AppPayBean appPayBean;

    public void appPayOrders(final int paytype) {
        HttpHelper.payOrders(bean.getOrders_id() + "", paytype + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                appPayBean = gson.fromJson(succeed, AppPayBean.class);
                if (appPayBean.getCode() == 0) {
                    switch (paytype) {
                        case 1://微信
                            Debug.e("----微信-------" + succeed);
                            appPlayWX();
                            break;
                        case 2://支付宝
                            Debug.e("----支付宝-------" + succeed);
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


    private static final String APP_ID = "wx153551c2cce0e6a8";
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    public void appPlayWX() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(MyOrderTabDetails.this, null);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
        PayReq req = new PayReq();
//给req对象赋值
        req.appId = appPayBean.getWxpay().getData().getAppid();//APPID
        req.partnerId = appPayBean.getWxpay().getData().getPartnerid();//    商户号
        req.prepayId = appPayBean.getWxpay().getData().getPrepayid();//  预付款ID
        req.nonceStr = appPayBean.getWxpay().getData().getNoncestr();//随机数
        req.timeStamp = appPayBean.getWxpay().getData().getTimestamp() + "";//时间戳
        req.packageValue = "Sign=WXPay";//固定值Sign=WXPay
        req.sign = appPayBean.getWxpay().getData().getSign();//签名
        api.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求
    }

    public void appPlayZFB() {
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MyOrderTabDetails.this);
                Debug.e("----------" + appPayBean.getAlipay().getStr());
                Map<String, String> result = alipay.payV2(appPayBean.getAlipay().getStr(), true);
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

    private static final int SDK_PAY_FLAG = 1;
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
                        setResult(10);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
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

    /**
     * 确认订单
     */
    public void Shop_confirmationOfOrder() {
        HttpHelper.Shop_confirmationOfOrder(aCache.getAsString("user_id"), bean.getOrders_id() + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ConfirmationOfOrderBean entity = FastJSONHelper.getPerson(succeed, ConfirmationOfOrderBean.class);
                if (entity.getError() == 0) {
                    Toast.makeText(getContext(), "确认订单成功！", Toast.LENGTH_SHORT).show();
                    setResult(10);
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 计算出总价格
     */
    private Double zongjia = 0.0;

    public String addPrice(List<UserordersBean.DataBean.ArrayBean.GoodsdataBean> data) {
        zongjia = 0.0;
        for (int i = 0; i < data.size(); i++) {
            zongjia += (data.get(i).getOrdersgoods_num() * data.get(i).getDanjiaprice());
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(zongjia);
    }

    // 倒计时
    private long mDay = 7;// 天
    private long mHour = 0;//小时,
    private long mMin = 0;//分钟,
    private long mSecond = 0;//秒
    private Timer mTimer;
    private boolean kaiguan = true;
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                if (kaiguan) {
                    Date currentTime = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date1 = formatter.format(currentTime);
                    String date2 = formatData("yyyy-MM-dd HH:mm:ss", entity.getData().getOrders_creattime());
                    jisuanshijiancha(date1, date2);
                    kaiguan = false;
                }
                daojishi.setText("还剩" + mDay + "天" + getTv(mHour )+ "小时"+getTv(mMin )+"分" + getTv(mSecond )+ "秒自动确认");
                if (mSecond == 0 && mDay == 0 && mHour == 0 && mMin == 0) {
                    mTimer.cancel();
                }
            }
        }
    };

    private String getTv(long l) {
        if (l >= 10) {
            return l + "";
        } else {
            return "0" + l;//小于10,,前面补位一个"0"
        }
    }

    /**
     * 开启倒计时
     * //time为Date类型：在指定时间执行一次。
     * timer.schedule(task, time);
     * //firstTime为Date类型,period为long，表示从firstTime时刻开始，每隔period毫秒执行一次。
     * timer.schedule(task, firstTime,period);
     * //delay 为long类型：从现在起过delay毫秒执行一次。
     * timer.schedule(task, delay);
     * //delay为long,period为long：从现在起过delay毫秒以后，每隔period毫秒执行一次。
     * timer.schedule(task, delay,period);
     */
    private void startRun() {
        mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 1;
                timeHandler.sendMessage(message);
            }
        };
        mTimer.schedule(mTimerTask, 0, 1000);
    }
    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
                if (mHour < 0) {
                    // 倒计时结束
                    mHour = 23;
                    mDay--;
                    if(mDay < 0){
                        // 倒计时结束
                        mDay = 0;
                        mHour= 0;
                        mMin = 0;
                        mSecond = 0;
                    }
                }
            }
        }
    }

    /**
     * 服务器获取时间转换
     *
     * @param dataFormat
     * @param timeStamp
     * @return
     */
    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    // 计算的时间差
    public void jisuanshijiancha(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24))
                    / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
                    * (1000 * 60 * 60))
                    / (1000 * 60);
            mDay = mDay - days;
            mHour = mHour - hours;
            mMin = mMin - minutes;
            if (days >= 7) {
                oder_status.setText("订单已确认收货");
                daojishi.setText("");
                item1.setVisibility(View.GONE);
                item2.setVisibility(View.GONE);
                item3.setVisibility(View.VISIBLE);
                findViewById(R.id.oder_delete).setOnClickListener(this);
                return;
            }
            Debug.e("-------计算结果已经过的时间--days-" + days + "--hours--" + hours + "--minutes-" + minutes);
        } catch (Exception e) {
        }
    }
}
