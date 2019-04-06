package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.DetailCommetListBean;
import com.hykj.liuzhi.androidcomponents.bean.VideomessageBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailCommentAdapter;
import com.hykj.liuzhi.androidcomponents.ui.popup.CommentMorePopup;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.DensityUtils;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * Created by Terminator on 2019/4/6.
 */
public class CommentActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener
        , CommentMorePopup.Callback {

    @BindView(R.id.rv_comment)
    RecyclerView mRvComment;
    @BindView(R.id.sfl_comment)
    SmartRefreshLayout mSflComment;
    @BindView(R.id.et_comment)
    EditText mEtComment;

    private ACache mAcache;
    private int mPage = 1;
    private int mCurrentPosition;
    private String mSoftTextId;
    DetailCommentAdapter mAdapter;
    private List<DetailCommetListBean.DataBean.ArrayBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mSoftTextId = getIntent().getStringExtra("soft_text_id");
        new TitleBuilder(this).setTitleText("评论").setLeftIco(R.mipmap.common_black_back)
                .setLeftIcoListening(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        mAcache = ACache.get(this);
        mRvComment.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mSflComment.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        mSflComment.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        mSflComment.setEnableRefresh(true);//启用刷新
        mSflComment.setEnableLoadmore(true);//启用加载
        mSflComment.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                datas.clear();
                getComment();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        mSflComment.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                getComment();
                refreshlayout.finishLoadmore();
            }
        });
        mAdapter = new DetailCommentAdapter(datas);
        mAdapter.setOnItemChildClickListener(this);
        mRvComment.setAdapter(mAdapter);
        getComment();
    }

    public void getComment() {
        HttpHelper.Advertorial_softtextmessageall(String.valueOf(mPage), mSoftTextId, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                if (mPage > 1) {
                    Toast.makeText(getContext(), "暂无更多评论！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSucceed(String succeed) {
                DetailCommetListBean entity = FastJSONHelper.getPerson(succeed, DetailCommetListBean.class);
                for (int i = entity.getData().getArray().size() - 1; i >= 0; i--) {
                    datas.add(entity.getData().getArray().get(i));
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                if (mPage > 1) {
                    Toast.makeText(getContext(), "暂无更多评论！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick({R.id.title_leftIco, R.id.iv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_leftIco:
                finish();
                break;
            case R.id.iv_send:
                sendComment();
                break;
        }
    }

    public void sendComment() {
        String comment = mEtComment.getText().toString();
        if (TextUtils.isEmpty(comment)) {
            Toast.makeText(getContext(), "请输入您要评论的内容!", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHelper.Advertorial_softtextmessage(mSoftTextId + "", mAcache.getAsString("user_id"), comment
                , new HttpHelper.HttpUtilsCallBack<String>() {
                    @Override
                    public void onFailure(String failure) {
                        Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSucceed(String succeed) {
                        VideomessageBean entity = FastJSONHelper.getPerson(succeed, VideomessageBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getMsg().equals("恭喜您，评论成功")) {
                                mEtComment.setText("");
                                Toast.makeText(getContext(), "发送成功！", Toast.LENGTH_SHORT).show();
                                mPage = 1;
                                datas.clear();
                                getComment();
                            }
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mCurrentPosition = position;
        switch (view.getId()) {
            case R.id.iv_more:
                new CommentMorePopup(getContext(), this)
                        .showAsDropDown(view, DensityUtils.dip2px(getContext(), -105), DensityUtils.dip2px(getContext(), -25));
                break;
        }
    }

    @Override
    public void comment() {
        View writeCommentView = LayoutInflater.from(this).inflate(R.layout.pop_back_comment, null);
        final PopupWindow popCommentWindow = new PopupWindow(writeCommentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popCommentWindow.setAnimationStyle(R.style.AnimationBottomFade);
        popCommentWindow.setBackgroundDrawable(null);
        popCommentWindow.setOutsideTouchable(true);
        popCommentWindow.update();
        //软键盘不会挡着popupwindow
        popCommentWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popCommentWindow.setFocusable(true);
        final EditText et_back_comment = writeCommentView.findViewById(R.id.et_reply);
        et_back_comment.requestFocus();
        et_back_comment.setFocusable(true);
        et_back_comment.setFocusableInTouchMode(true);
        ImageView iv_send_comment = writeCommentView.findViewById(R.id.iv_send);
        iv_send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = et_back_comment.getText().toString();
                if (TextUtils.isEmpty(comment)) {
                    Toast.makeText(getContext(), "请输入回复内容！", Toast.LENGTH_SHORT).show();
                    return;
                }
                popCommentWindow.dismiss();
                HttpHelper.messagereply(mSoftTextId, null, mAcache.getAsString("user_id"), String.valueOf(datas.get(mCurrentPosition).getMessage_id()),
                        et_back_comment.getText().toString(), String.valueOf(datas.get(mCurrentPosition).getUser_id()), new HttpHelper.HttpUtilsCallBack<String>() {
                            @Override
                            public void onFailure(String failure) {
                                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onSucceed(String succeed) {
                                Toast.makeText(getContext(), "回复成功", Toast.LENGTH_SHORT).show();
                                DetailCommetListBean.DataBean.ArrayBean bean = new DetailCommetListBean.DataBean.ArrayBean();
                                bean.setMessage_message("回复: " + et_back_comment.getText().toString());
                                DetailCommetListBean.DataBean.ArrayBean.UserdataBean userdataBean = new DetailCommetListBean.DataBean.ArrayBean.UserdataBean();
                                userdataBean.setUser_nickname(LocalInfoUtils.getUserself("user_nickname"));
                                bean.setUser_id(Integer.parseInt(mAcache.getAsString("user_id")));
                                bean.setUserdata(userdataBean);
                                datas.get(mCurrentPosition).reply.add(bean);
                                mAdapter.notifyItemChanged(mCurrentPosition + 1);
                            }

                            @Override
                            public void onError(String error) {
                                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        popCommentWindow.showAtLocation(writeCommentView, Gravity.BOTTOM, 0, 0);
        et_back_comment.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_back_comment, 0);
            }
        });
    }

    @Override
    public void report() {
        if (mAcache.getAsString("user_id").equals(datas.get(mCurrentPosition).getUser_id())) {
            Toast.makeText(getContext(), "自己不能举报自己！", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("title", "soft");
            intent.putExtra("reportuserid", (datas.get(mCurrentPosition).getUser_id() + ""));
            intent.setClass(getContext(), ReportActivity.class);
            startActivity(intent);
        }
    }
}
