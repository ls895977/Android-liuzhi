package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CancellPayBean;
import com.hykj.liuzhi.androidcomponents.bean.addproposalBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhouyou.http.EasyHttp.getContext;

public class SuggestActivity extends BaseActivity {
    private ACache aCache;
    @BindView(R.id.iv_suggest_back)
    ImageView ivSuggestBack;
    @BindView(R.id.tv_suggest_commit)
    TextView tvSuggestCommit;
    @BindView(R.id.tousuneirong)
    EditText tousuneirong;
    @BindView(R.id.suggest_phone)
    EditText phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        ButterKnife.bind(this);
        aCache=ACache.get(this);
    }

    @OnClick({R.id.iv_suggest_back, R.id.tv_suggest_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_suggest_back:
                finish();
                break;
            case R.id.tv_suggest_commit:
                Min_addproposal();
                break;
        }
    }

    /**
     * 提交投诉内容
     */
    public void Min_addproposal() {
        if(TextUtils.isEmpty(tousuneirong.getText().toString())){
            Toast.makeText(getContext(),"请输入你要投诉的内容！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(phone.getText().toString())){
            Toast.makeText(getContext(),"请输入你的手机号！", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHelper.Min_addproposal( aCache.getAsString("user_id") + "",phone.getText().toString(),tousuneirong.getText().toString(), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                addproposalBean entity = FastJSONHelper.getPerson(succeed, addproposalBean.class);
                if (entity.getError() == 0) {
                    Toast.makeText(getContext(), "提交成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });

    }

}

