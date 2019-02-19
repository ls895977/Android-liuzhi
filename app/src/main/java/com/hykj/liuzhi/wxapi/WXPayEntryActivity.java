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
//        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, "wx153551c2cce0e6a8");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Debug.e("---11-----onNewIntent--------");
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Debug.e("------onResp-------"+resp.errCode);
        if (resp.errCode == 0) {
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
        }
    }

}