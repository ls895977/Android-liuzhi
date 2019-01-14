package com.hykj.liuzhi.androidcomponents.ui.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.BaseActivity;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.MyOrderTabDetailsBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserordersBean;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;

import butterknife.ButterKnife;

import static com.zhouyou.http.EasyHttp.getContext;

public class MyOrderTabDetails extends BaseActivity {
    private String ordersid;
    private ACache aCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_myordertabdetails);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        aCache = ACache.get(this);
        ordersid = getIntent().getStringExtra("ordersid");
        showorders();
    }

    public void showorders() {
        HttpHelper.showorders(ordersid, aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                MyOrderTabDetailsBean entity = FastJSONHelper.getPerson(succeed, MyOrderTabDetailsBean.class);
                Log.e("aa", "---订单详情-" + succeed);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
