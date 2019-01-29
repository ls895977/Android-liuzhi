package com.hykj.liuzhi.androidcomponents.ui.activity.dailog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;

/**
 * 支付选择
 * Created by lishan on 2017/12/22.
 */
public class Dlg_Play extends BaseDialog {
    private String tvPrice;
    OnClick onClick;
    private TextView tv_zhifubao, tv_weixin,price;
    private int payStatus=10;
    public Dlg_Play(Context context, OnClick click) {
        super(context);
        this.onClick = click;
    }

    public void setTvPrice(String tvPrice) {
        this.tvPrice = tvPrice;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dlg_play;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.zhifubao);
        setOnClickListener(R.id.weixin);
        setOnClickListener(R.id.shutdonw);
        setOnClickListener(R.id.play_commit);
        tv_zhifubao = getView(R.id.tv_zhifubao);
        tv_weixin = getView(R.id.tv_weixin);
        price=getView(R.id.price);

    }

    @Override
    protected void initData() {
    price.setText( "￥" +tvPrice);
    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.zhifubao://支付宝
                tv_zhifubao.setSelected(true);
                tv_weixin.setSelected(false);
                payStatus=2;
                break;
            case R.id.weixin://微信
                tv_zhifubao.setSelected(false);
                tv_weixin.setSelected(true);
                payStatus=1;
                break;
            case R.id.shutdonw://叉叉
                dismiss();
                break;
            case R.id.play_commit://确定付款
                if(payStatus==10){
                    Toast.makeText(getContext(),"请选择一种支付方式", Toast.LENGTH_SHORT).show();
                    return;
                }
                onClick.onItem(payStatus);
                break;
        }
    }

    public interface OnClick {
        void onItem(int p);
    }
}
