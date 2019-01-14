package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.AliiTabBean;
import com.hykj.liuzhi.androidcomponents.bean.TabBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.AutoDisplayChildViewContainer;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.DensityUtils;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改标签
 *
 * @author: lujialei
 * @date: 2018/10/22
 * @describe:
 */
public class ChangeUserTableActivity extends BaseActivity implements View.OnClickListener {
    private ACache aCache;
    @BindView(R.id.framelayout)
    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_home_search
        setContentView(R.layout.acitivity_user_table);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        findViewById(R.id.title_leftIco).setOnClickListener(this);
        findViewById(R.id.title_rightIco).setOnClickListener(this);
    }

    private void initData() {
        aCache = ACache.get(this);
        backData();
    }

    AliiTabBean entity;

    public void backData() {
        HttpHelper.getlabels(aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(ChangeUserTableActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                entity = FastJSONHelper.getPerson(succeed, AliiTabBean.class);
                if (entity.getCode() != 0) {
                    return;
                }
                setAllTab();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ChangeUserTableActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    List<String> stLab = new ArrayList<>();
    List<String> stLab1 = new ArrayList<>();
    String stlablab = "";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_leftIco:
                finish();
                break;
            case R.id.title_rightIco:
                lablesid = "";
                stLab.clear();
                for (int i = 0; i < status.size(); i++) {
                    if (status.get(i).getIsselect() == 1) {
                        stLab.add(entity.getData().get(i).getLabel_id() + "");
                        stLab1.add(entity.getData().get(i).getLabel_name());
                    }
                }
                for (int j = 0; j < stLab.size(); j++) {
                    if (j == (stLab.size() - 1)) {
                        lablesid += stLab.get(j);
                    } else {
                        lablesid += stLab.get(j) + ",";
                    }
                }
                for (int m = 0; m < stLab1.size(); m++) {
                    if (m == (stLab1.size() - 1)) {
                        stlablab += stLab1.get(m);
                    } else {
                        stlablab += stLab1.get(m) + ",";
                    }
                }
                postChageTab();
                break;
        }
    }

    List<AliiTabBean.DataBean> status = new ArrayList<>();

    public void setAllTab() {
        AutoDisplayChildViewContainer flowLayout = new AutoDisplayChildViewContainer(this);
        flowLayout.removeAllViews();
        int padding = DensityUtils.dip2px(this, 0);
        flowLayout.setPadding(padding, padding, padding, padding);
        LayoutInflater from = LayoutInflater.from(this);
        for (int i = 0; i < entity.getData().size(); i++) {
            AliiTabBean.DataBean statsBean = new AliiTabBean.DataBean();
            statsBean.setIsselect(0);
            status.add(statsBean);
            final RelativeLayout inflate = (RelativeLayout) from.inflate(R.layout.itme_filter_term, null);
            inflate.setTag(i);
            TextView textView = (TextView) inflate.findViewById(R.id.tv);
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int) inflate.getTag();
                    if (inflate.isSelected()) {
                        inflate.setSelected(false);
                        status.get(tag).setIsselect(0);
                    } else {
                        inflate.setSelected(true);
                        status.get(tag).setIsselect(1);
                    }
//                    postChageTab(entity.getData().get(tag).getLabel_id() + "");
                }
            });
            textView.setGravity(Gravity.CENTER);
            textView.setText(entity.getData().get(i).getLabel_name());
            // 把View添加到flowLayout容器中进行显示
            flowLayout.addView(inflate);
        }
        mFrameLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(70, 0, 70, 0);//4个参数按顺序分别是左上右下
        flowLayout.setLayoutParams(layoutParams);
        mFrameLayout.addView(flowLayout);
    }

    TabBean bean;
    private String lablesid = "";

    public void postChageTab() {
        HttpHelper.changelabel(lablesid, aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(ChangeUserTableActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                bean = FastJSONHelper.getPerson(succeed, TabBean.class);
                if (bean.getCode() != 0) {
                    return;
                }
                Toast.makeText(ChangeUserTableActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                aCache.put("user_label", stlablab);
                setResult(0);
                finish();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ChangeUserTableActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
