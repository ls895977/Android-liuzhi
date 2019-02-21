package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.AddCodeBean;
import com.hykj.liuzhi.androidcomponents.bean.DetailCommentBean;
import com.hykj.liuzhi.androidcomponents.bean.DetailCommetListBean;
import com.hykj.liuzhi.androidcomponents.bean.ShearBean;
import com.hykj.liuzhi.androidcomponents.bean.VideomessageBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_Share;
import com.hykj.liuzhi.androidcomponents.ui.activity.softtext.SofttextFirstPageBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailCommentAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.detail.DetailCommentFragment;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FirstpagedataBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.DefaultTopBar;
import com.hykj.liuzhi.androidcomponents.ui.widget.SoftDetailHeader;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.WxShareUtils;
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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * 软文详情
 *
 * @author: lujialei
 * @date: 2018/10/25
 * @describe:
 */
public class DetailSoftArticleActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Gson gson = new Gson();
    private ACache aCache;
    private String softtextid;
    @BindView(R.id.home_refreshLayout1)
    SmartRefreshLayout refreshLayout1;
    @BindView(R.id.fragment_detail_message)
    EditText message;
    ZLoadingDialog loding;
    private Dlg_Share share;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_article_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_send).setOnClickListener(this);
        aCache = ACache.get(this);
        softtextid = getIntent().getStringExtra("softtextid");
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        refreshLayout1.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        refreshLayout1.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        refreshLayout1.setEnableRefresh(true);//启用刷新
        refreshLayout1.setEnableLoadmore(true);//启用加载
        refreshLayout1.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                Advertorial_softtextmessageall();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout1.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                Advertorial_softtextmessageall();
                refreshlayout.finishLoadmore();
            }
        });
        postBackData();
        postAdd();
    }

    @Override
    protected View onCreateTopBar(ViewGroup view) {
        DefaultTopBar topBar = new DefaultTopBar(this, "详情", true);
        return topBar;
    }

    int page = 1;
    SofttextFirstPageBean softtextFirstPageBean;

    public void postBackData() {
        HttpHelper.Advertorial_softtextfirstpage(softtextid + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                softtextFirstPageBean = gson.fromJson(succeed, SofttextFirstPageBean.class);
                mAdapter = new DetailCommentAdapter(datas);
                mAdapter.setOnItemChildClickListener(DetailSoftArticleActivity.this);
                mAdapter.addHeaderView(new SoftDetailHeader(DetailSoftArticleActivity.this, softtextFirstPageBean, getIntent().getStringExtra("stType")));
                recyclerView.setAdapter(mAdapter);
                Advertorial_softtextmessageall();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 获取评论列表
     */
    DetailCommentAdapter mAdapter;
    private List<DetailCommetListBean.DataBean.ArrayBean> datas = new ArrayList<>();
    public void Advertorial_softtextmessageall() {
        HttpHelper.Advertorial_softtextmessageall(page + "", softtextid, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                if (page > 1) {
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
                if (page > 1) {
                    Toast.makeText(getContext(), "暂无更多评论！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_report:
                if (aCache.getAsString("user_id").equals(datas.get(position).getUser_id())) {
                    Toast.makeText(getContext(), "自己不能举报自己！", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("title", "soft");
                    intent.putExtra("reportuserid", (datas.get(position).getUser_id() + ""));
                    intent.setClass(getContext(), ReportActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_send://发送
                Advertorial_softtextmessage();
                break;
        }
    }

    /**
     * 发送消息
     */
    String msg = "";

    public void Advertorial_softtextmessage() {
        msg = message.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(getContext(), "请输入您要评论的内容!", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHelper.Advertorial_softtextmessage(softtextid + "", aCache.getAsString("user_id"), msg, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                VideomessageBean entity = FastJSONHelper.getPerson(succeed, VideomessageBean.class);
                if (entity.getCode() == 0) {
                    if (entity.getMsg().equals("恭喜您，评论成功")) {
                        message.setText("");
                        Toast.makeText(getContext(), "发送成功！", Toast.LENGTH_SHORT).show();
                        page = 1;
                        datas.clear();
                        Advertorial_softtextmessageall();
                    }
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * 添加到图文瀏覽记录
     */
    public void postAdd() {
        HttpHelper.softtextborwses(softtextid + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                AddCodeBean bean = gson.fromJson(succeed, AddCodeBean.class);

//                softtextFirstPageBean = gson.fromJson(succeed, SofttextFirstPageBean.class);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
