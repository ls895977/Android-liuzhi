package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.SignInBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBean;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhouyou.http.EasyHttp.getContext;

public class ChangeNameActivity extends BaseActivity {
    @BindView(R.id.tv_changename_back)
    ImageView tvChangenameBack;
    @BindView(R.id.tv_changename_title)
    TextView tvChangenameTitle;
    @BindView(R.id.tv_changename_complete)
    TextView tvChangenameComplete;
    @BindView(R.id.et_input)
    EditText etInput;
    private int mPosition;
    ACache aCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changename);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        aCache = ACache.get(this);
        Intent intent = getIntent();
        mPosition = intent.getIntExtra("position", 0);
    }

    private void initData() {
        if (mPosition == 1) {
            etInput.setText(LocalInfoUtils.getUserself("user_nickname"));
            tvChangenameTitle.setText("修改昵称");
        } else if (mPosition == 2) {
            etInput.setText(LocalInfoUtils.getUserself("user_autograph"));
            tvChangenameTitle.setText("修改个性签名");
        }
    }

    @OnClick({R.id.tv_changename_back, R.id.tv_changename_complete, R.id.shutdonw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_changename_back:
                finish();
                break;
            case R.id.tv_changename_complete:
                if (TextUtils.isEmpty(etInput.getText().toString())) {
                    if (mPosition == 1) {
                        Toast.makeText(getContext(), "请输入您要修改的昵称", Toast.LENGTH_SHORT).show();
                    } else if (mPosition == 2) {
                        Toast.makeText(getContext(), "请输入您要修改的个性签名", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                if (mPosition == 1) {
                    setTingChangeNickName(etInput.getText().toString());
                } else if (mPosition == 2) {
                    setTingChangeautograph(etInput.getText().toString());
                }
                break;
            case R.id.shutdonw:
                etInput.setText("");
                break;
        }
    }

    /**
     * 修改昵称
     */
    public void setTingChangeNickName(String name) {
        HttpHelper.getChangenickname(name, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                SignInBean entity = FastJSONHelper.getPerson(succeed, SignInBean.class);
                Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                aCache.put("user_nickname", etInput.getText().toString());
                setResult(2);
                finish();
            }

            @Override
            public void onError(String error) {
                if (error.equals("1")) {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                } else if (error.equals("2")) {
                    Toast.makeText(getContext(), "修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 修改签名
     */
    public void setTingChangeautograph(String key) {
        HttpHelper.getChangeautograph(key, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                SignInBean entity = FastJSONHelper.getPerson(succeed, SignInBean.class);
                Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                aCache.put("user_autograph", etInput.getText().toString());
                setResult(2);
                finish();
            }

            @Override
            public void onError(String error) {
                if (error.equals("1")) {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                } else if (error.equals("2")) {
                    Toast.makeText(getContext(), "修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
