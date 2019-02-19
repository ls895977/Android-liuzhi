package com.hykj.liuzhi.androidcomponents.ui.fragment.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.ViewLogisticsBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.BaseActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.Act_MyOrder;
import com.hykj.liuzhi.androidcomponents.ui.adapter.LogisticsInfoAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MyOrderTabAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.MyOrderTabFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserordersBean;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.ArrayList;
import java.util.List;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * 物流
 */
public class Act_ViewLogistics extends BaseActivity {
    private String ordersid;
    RecyclerView recyclerView;
    SmartRefreshLayout refreshLayout;
    ZLoadingDialog dialog;
    private ImageView myImage;
    private TextView tv_deliver_company,tv_waybill_number,phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_viewlogistics);
        ordersid = getIntent().getStringExtra("ordersid");
        initView();
        initData();
    }

    private int page = 1;

    public void initView() {
        myImage = findViewById(R.id.myImage);
        tv_deliver_company=findViewById(R.id.tv_deliver_company);
        tv_waybill_number=findViewById(R.id.tv_waybill_number);
        phone=findViewById(R.id.tv_official_phone);
        dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.DKGRAY)//颜色
                .setHintText("数据提交中...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.DKGRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CCffffff")); // 设置背景色，默认白色
        recyclerView = findViewById(R.id.rv_logistics);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setFocusable(false);
        //解决ScrollView嵌套RecyclerView出现的系列问题
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        userorders();
    }

    public void initData() {


    }

    /**
     * 获取物流信息
     */
    private List<ViewLogisticsBean.DataBeanX.DataBean> datas = new ArrayList<>();

    public void userorders() {
        dialog.show();
        HttpHelper.Min_viewLogistics(ordersid, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                dialog.dismiss();
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                ViewLogisticsBean entity = FastJSONHelper.getPerson(succeed, ViewLogisticsBean.class);
                Glide.with(getContext()).load(entity.getData().getLogo()).into(myImage);
                tv_deliver_company.setText(entity.getData().getName());
                tv_waybill_number.setText(entity.getData().getNu());
                phone.setText(entity.getData().getCondition());
                for (int i = 0; i < entity.getData().getData().size(); i++) {
                    datas.add(entity.getData().getData().get(i));
                }
                recyclerView.setAdapter(new LogisticsInfoAdapter(getContext(), R.layout.item_logistics, datas));
                dialog.dismiss();
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
