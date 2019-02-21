package com.hykj.liuzhi.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hykj.liuzhi.androidcomponents.MainActivity;
import com.hykj.liuzhi.androidcomponents.bean.updateTextEvent;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    public static String actStatus = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, "wx153551c2cce0e6a8");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Debug.e("------onResp-------" + baseResp.errCode);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int errCord = baseResp.errCode;
            if (errCord == 0) {
                switch (actStatus) {
                    case "ConfirmOrderActivity"://确认订单页跳转
                        Intent intent1 = new Intent();
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent1.setClass(WXPayEntryActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case "MyOrderTabFragment"://我的订单里
                        EventBus.getDefault().post(new updateTextEvent("支付了"));
                        break;
                }
            } else {
                finish();
            }
            //这里接收到了返回的状态码可以进行相应的操作，如果不想在这个页面操作可以把状态码存在本地然后finish掉这个页面，这样就回到了你调起支付的那个页面
            //获取到你刚刚存到本地的状态码进行相应的操作就可以了
            finish();
        }
    }

}