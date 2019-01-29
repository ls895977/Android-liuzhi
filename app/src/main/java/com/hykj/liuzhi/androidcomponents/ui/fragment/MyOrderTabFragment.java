package com.hykj.liuzhi.androidcomponents.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.hykj.liuzhi.androidcomponents.bean.AddCodeBean;
import com.hykj.liuzhi.androidcomponents.bean.AppPayBean;
import com.hykj.liuzhi.androidcomponents.bean.AuthResult;
import com.hykj.liuzhi.androidcomponents.bean.CancellPayBean;
import com.hykj.liuzhi.androidcomponents.bean.PayResult;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_Play;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MyOrderTabAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.base.ViewPagerFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.MyOrderTabDetails;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserordersBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopHomeBean;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2018/10/27
 * @describe:
 */


public class MyOrderTabFragment extends ViewPagerFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener, Dlg_Play.OnClick {
    private String type;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.shop_refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ACache aCache;
    private Dlg_Play play;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_order_tab, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aCache = ACache.get(getActivity());
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
                refreshlayout.finishLoadmore();
            }
        });
        type = getArguments().getString("type");
        userorders();
        regToWx();
    }

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(getContext(), APP_ID, true);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
    }

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
                    adapter.setOnItemClickListener(MyOrderTabFragment.this);
                    adapter.setOnItemChildClickListener(MyOrderTabFragment.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
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
                Log.e("aa","----取消订单------");
                appCancellPay();
                break;
            case R.id.oder_pay://立即付款
                int sum = 0;
                for (int i = 0; i < datas.get(position).getGoodsdata().size(); i++) {
                    sum += Integer.valueOf(datas.get(position).getGoodsdata().get(i).getOrdersgoods_money());
                }
                money = sum + "";
                play = new Dlg_Play(getContext(), this);
                play.setTvPrice(money);
                play.show();
                break;
            case R.id.oder_wuliu://物流
                break;
            case R.id.oder_centerAddr://确认收货
                break;
            case R.id.oder_delete://删除订单
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Log.e("aa","-------onItemClick------"+position);
//        Intent intent = new Intent();
//        intent.putExtra("ordersid", datas.get(position).getOrders_id() + "");
//        intent.setClass(getContext(), MyOrderTabDetails.class);
//        startActivity(intent);

    }

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
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private static final String APP_ID = "wx153551c2cce0e6a8";

    public void appPlayWX() {
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
                        Log.e("aa", "-------------支付成功--");
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

        ;
    };

    /**
     * 取消订单
     */
    public void appCancellPay() {
        Log.e("aa","------取消订单！-getOrders_id-"+datas.get(position1).getOrders_id());
        HttpHelper.CancellationOfOrder(datas.get(position1).getOrders_id() + "", aCache.getAsString("user_id") + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                CancellPayBean cancellPayBean = gson.fromJson(succeed, CancellPayBean.class);
                if (cancellPayBean.getError() == 0) {
                    Log.e("aa","------取消成功！--"+succeed);
                    page = 1;
                    datas.clear();
                    userorders();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
