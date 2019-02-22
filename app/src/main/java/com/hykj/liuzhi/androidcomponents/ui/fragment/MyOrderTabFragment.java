package com.hykj.liuzhi.androidcomponents.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.AppPayBean;
import com.hykj.liuzhi.androidcomponents.bean.AuthResult;
import com.hykj.liuzhi.androidcomponents.bean.CancellPayBean;
import com.hykj.liuzhi.androidcomponents.bean.ConfirmationOfOrderBean;
import com.hykj.liuzhi.androidcomponents.bean.PayResult;
import com.hykj.liuzhi.androidcomponents.bean.updateTextEvent;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_Play;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MyOrderTabAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.base.ViewPagerFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.Act_ViewLogistics;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.MyOrderTabDetails;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserordersBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.wxapi.WXPayEntryActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: lujialei
 * @date: 2018/10/27
 * @describe:
 */


public class MyOrderTabFragment extends ViewPagerFragment implements BaseQuickAdapter.OnItemChildClickListener, Dlg_Play.OnClick {
    private String type;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.shop_refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ACache aCache;
    private Dlg_Play play;

    @Override
    public void onResume() {
        super.onResume();
        page++;
        userorders();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_order_tab, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    ZLoadingDialog dialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aCache = ACache.get(getActivity());
        //在事件被订阅的界面中注册EventBus
        EventBus.getDefault().register(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        refreshLayout.setEnableRefresh(true);//启用刷新
        refreshLayout.setEnableLoadmore(true);//启用加载
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                userorders();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                userorders();
                refreshlayout.finishLoadmore();
            }
        });
        type = getArguments().getString("type");
        dialog = new ZLoadingDialog(getContext());
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据提交中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        dialog.show();
        userorders();
    }

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    List<UserordersBean.DataBean.ArrayBean> datas = new ArrayList<>();
    int page = 1;
    MyOrderTabAdapter adapter;

    public void userorders() {
        HttpHelper.userorders(aCache.getAsString("user_id"), page + "", type, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                UserordersBean entity = FastJSONHelper.getPerson(succeed, UserordersBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    datas.add(entity.getData().getArray().get(i));
                }
                if (adapter == null) {
                    adapter = new MyOrderTabAdapter(datas, getContext());
                    adapter.setOnItemChildClickListener(MyOrderTabFragment.this);
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {//订单详情
                            Intent intent = new Intent();
                            intent.putExtra("bean", datas.get(position));
                            intent.setClass(getContext(), MyOrderTabDetails.class);
                            startActivityForResult(intent, 10);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    String money;
    private int position1;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        position1 = position;
        switch (view.getId()) {
            case R.id.oder_cancel://取消订单
                Log.e("aa", "----取消订单------");
                dialog.show();
                appCancellPay();
                break;
            case R.id.oder_pay://立即付款
                curre = "1";
                WXPayEntryActivity.actStatus = "MyOrderTabFragment";
                Double sum = 0.0;
                for (int i = 0; i < datas.get(position).getGoodsdata().size(); i++) {
                    sum += Double.valueOf(datas.get(position).getGoodsdata().get(i).getOrdersgoods_money());
                }
                money = sum + "";
                play = new Dlg_Play(getContext(), this);
                play.setTvPrice(money);
                play.show();
                break;
            case R.id.oder_wuliu://物流
                if (datas.get(position).getOrders_deliverystatus() == 1) {
                    Intent intent = new Intent(getContext(), Act_ViewLogistics.class);
                    intent.putExtra("ordersid", datas.get(position).getOrders_id() + "");
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "卖家还未发货！暂无物流信息！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.oder_centerAddr://确认收货
                if (datas.get(position).getOrders_deliverystatus() == 1) {
                    dialog.show();
                    Shop_confirmationOfOrder();
                } else {
                    Toast.makeText(getContext(), "卖家还未发货！暂时不能做此操作！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.oder_delete://删除订单
                Debug.e("----------" + datas.get(position).getOrders_id());
                dialog.show();
                Shop_deleteorders();
                break;
        }
    }

    /**
     * 收到支付结果
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyBtn4(updateTextEvent msg) {
        if (msg.getZhifu().equals("支付了")) {
            getActivity().finish();
        }
    }

    String curre = "";

    @Override
    public void onItem(int p) {
        play.dismiss();
        appPayOrders(p);

    }

    AppPayBean bean;

    public void appPayOrders(final int paytype) {
        HttpHelper.payOrders(datas.get(position1).getOrders_id() + "", paytype + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                bean = gson.fromJson(succeed, AppPayBean.class);
                if (bean.getCode() == 0) {
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

    public void appPlayWX() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(getActivity(), null);
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

    public void appPlayZFB() {
        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
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
                        page = 1;
                        datas.clear();
                        userorders();
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

        ;
    };

    /**
     * 取消订单
     */
    public void appCancellPay() {
        Log.e("aa", "------取消订单！-getOrders_id-" + datas.get(position1).getOrders_id());
        HttpHelper.CancellationOfOrder(datas.get(position1).getOrders_id() + "", aCache.getAsString("user_id") + "", new HttpHelper.HttpUtilsCallBack<String>() {
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
                    Log.e("aa", "------取消成功！--" + succeed);
                    page = 1;
                    datas.clear();
                    userorders();
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
    public void onStop() {
        super.onStop();
//注销EventBus
        EventBus.getDefault().unregister(this);
    }

    /**
     * 确认订单
     */
    public void Shop_confirmationOfOrder() {
        HttpHelper.Shop_confirmationOfOrder(aCache.getAsString("user_id"), datas.get(position1).getOrders_id() + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ConfirmationOfOrderBean entity = FastJSONHelper.getPerson(succeed, ConfirmationOfOrderBean.class);
                if (entity.getError() == 0) {
                    page = 1;
                    datas.clear();
                    userorders();
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
     * 删除订单
     */
    public void Shop_deleteorders() {
        HttpHelper.Shop_deleteorders(aCache.getAsString("user_id"), datas.get(position1).getOrders_id() + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Debug.e("-----onSucceed--------" + succeed);
                ConfirmationOfOrderBean entity = FastJSONHelper.getPerson(succeed, ConfirmationOfOrderBean.class);
                if (entity.getError() == 0) {
                    page = 1;
                    datas.clear();
                    userorders();
                }
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 10) {
            page = 1;
            datas.clear();
            userorders();
        }

    }
}
