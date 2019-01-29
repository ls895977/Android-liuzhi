package com.hykj.liuzhi.androidcomponents.ui.fragment.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.BaseActivity;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MessagePagerAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean.SystemNotiFicationdetailBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.MessageTopBar;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeaageDetails extends BaseActivity {
    @BindView(R.id.context_mesage)
    TextView context_mesage;
    @BindView(R.id.mesage_name)
    TextView mesage_name;
    @BindView(R.id.mesage_time)
    TextView mesage_time;


    String systemnotificationid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagedetails);
        ButterKnife.bind(this);
        systemnotificationid = getIntent().getStringExtra("systemnotificationid");
        getSystemnotificationdetail(systemnotificationid);
    }
    @Override
    protected View onCreateTopBar(ViewGroup view) {
        MessageTopBar defaultTopBar = new MessageTopBar(this, "平台通知", true);
        defaultTopBar.getAll_read().setVisibility(View.GONE);
        return defaultTopBar;
    }
    /**
     * 消息详情接口
     */
    private void getSystemnotificationdetail(String systemnotificationid) {
        HttpHelper.getSystemnotificationdetail(systemnotificationid, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
            }

            @Override
            public void onSucceed(String succeed) {
                SystemNotiFicationdetailBean entity = FastJSONHelper.getPerson(succeed, SystemNotiFicationdetailBean.class);
                context_mesage.setText(entity.getData().getSystemnotification_text());
                mesage_name.setText(entity.getData().getSystemnotification_name());
                mesage_time.setText(TimeUtils.YMDHMS(entity.getData().getSystemnotification_creattime()+""));
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
