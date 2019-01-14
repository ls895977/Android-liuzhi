package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;
import com.luck.picture.lib.permissions.RxPermissions;
import com.zhouyou.http.utils.HttpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 设置按钮
 */
public class SetUpActivity extends BaseActivity {
    @BindView(R.id.rl_setup_fans)
    RelativeLayout rlSetupFans;
    @BindView(R.id.rl_setup_follows)
    RelativeLayout rlSetupFollows;
    @BindView(R.id.tv_setup_exitlogin)
    TextView tvSetupExitlogin;
    @BindView(R.id.rl_mine_setup_bindemail)
    RelativeLayout rlMineSetupBindemail;
    @BindView(R.id.rl_mine_setup_takeaddress)
    RelativeLayout rlMineSetupTakeaddress;
    @BindView(R.id.rl_mine_setup_change_pass)
    RelativeLayout rlMineSetupChangePass;
    @BindView(R.id.rl_mine_setup_realname_auth)
    RelativeLayout rlMineSetupRealnameAuth;
    @BindView(R.id.rl_mine_setup_login_record)
    RelativeLayout rlMineSetupLoginRecord;
    @BindView(R.id.rl_mine_setup_suggest)
    RelativeLayout rlMineSetupSuggest;
    private ACache aCache;
    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        initView();
        initData();
        ButterKnife.bind(this);
    }

    private void initData() {
        rxPermissions = new RxPermissions(this);
    }

    private void initView() {
        aCache = ACache.get(this);
        new TitleBuilder(SetUpActivity.this).setTitleText("账户设置").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.rl_setup_fans, R.id.rl_setup_follows, R.id.tv_setup_exitlogin, R.id.rl_mine_setup_bindemail, R.id.rl_mine_setup_takeaddress,
            R.id.rl_mine_setup_change_pass, R.id.rl_mine_setup_realname_auth, R.id.rl_mine_setup_login_record, R.id.rl_mine_setup_suggest, R.id.cller_cache})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_setup_fans://粉丝
                intent = new Intent(this, AttentionActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.rl_setup_follows://关注
                intent = new Intent(this, AttentionActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
            case R.id.tv_setup_exitlogin:
                Intent intent1 = new Intent();
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.setClass(SetUpActivity.this, LoginActivity.class);
                startActivity(intent1);
                aCache.clear();
                break;
            case R.id.rl_mine_setup_takeaddress://收货地址
                intent = new Intent(SetUpActivity.this, SelectAdressActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_setup_bindemail://绑定邮箱
                intent = new Intent(SetUpActivity.this, BindEmailActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_setup_change_pass://修改密码
                intent = new Intent(SetUpActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;

            case R.id.rl_mine_setup_realname_auth:
                rxPermissions
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    Intent intent = new Intent(SetUpActivity.this, TrueNameIdenActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SetUpActivity.this, "请打开读写存储卡权限", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.rl_mine_setup_login_record://登录记录
                intent = new Intent(SetUpActivity.this, LoginRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_setup_suggest:
                intent = new Intent(SetUpActivity.this, SuggestActivity.class);
                startActivity(intent);
                break;
            case R.id.cller_cache://清除缓存

                break;
        }
    }
}
