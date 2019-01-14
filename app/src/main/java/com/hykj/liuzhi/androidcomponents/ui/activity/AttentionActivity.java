package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CircleBean;
import com.hykj.liuzhi.androidcomponents.bean.UserAttentionBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.adapter.AttentionAdapter;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.luck.picture.lib.tools.ToastManage;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttentionActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.springView)
    SpringView springView;
    private AttentionAdapter mAdapter;
    private String type;
    private String title;
    private int pageIndex = 1;
    private int pageSize = 10;
    private Object usercollection;
    private List<UserAttentionBean.DataBean.ArrayBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_attention);
        ButterKnife.bind (this);
        iniData ();
        initView ();
        initListener ();


    }

    private void initListener() {
        springView.setType (SpringView.Type.FOLLOW);
        springView.setHeader (new AliHeader (this, true));
        springView.setFooter (new AliFooter (this, true));
        springView.setListener (new SpringView.OnFreshListener () {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                requestData ();
            }

            @Override
            public void onLoadmore() {
                pageIndex++;
                requestData ();
            }
        });

        mAdapter.setOnItemClickListener (new BaseQuickAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent (AttentionActivity.this, PersonDetailActivity.class);
                startActivity (intent);
            }
        });
    }

    private void iniData() {
        type = getIntent ().getStringExtra ("type");
        if ("0".equals (type)) {
            title = "关注";
        } else {
            title = "粉丝";
        }
        list = new ArrayList<>();
        recyclerView.setLayoutManager (new LinearLayoutManager (this));
        mAdapter = new AttentionAdapter (AttentionActivity.this, list, type);
//        mAdapter.setEmptyView(initEmptyView());
        recyclerView.setAdapter (mAdapter);

        requestData ();
    }

    private void requestData() {
        if ("0".equals (type)) {
            getusercollection ();
        } else {
            getUserFan ();
        }
    }

    private void getUserFan() {
        HttpHelper.getUserFan (LocalInfoUtils.getUserId (), pageIndex, pageSize, new HttpHelper.HttpUtilsCallBack<String> () {
            @Override
            public void onFailure(String failure) {
                springView.onFinishFreshAndLoad ();
                Toast.makeText(AttentionActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                UserAttentionBean person = FastJSONHelper.getPerson (succeed, UserAttentionBean.class);
                if (person != null){
                    List<UserAttentionBean.DataBean.ArrayBean> array = person.getData().getArray();
                    int size;
                    if(pageIndex == 1){
                        list.clear();
                        size = 0;
                    }else {
                        size = list.size();
                    }
                    if(null != array && !array.isEmpty()){
                        // 加载数据成功
                        list.addAll(array);
                        array.clear();
                        recyclerView.scrollToPosition(size);
                    } else {
                        if(!list.isEmpty()){
                            // 加载成功list无数据情况
                            Toast.makeText(AttentionActivity.this, "没有新数据了!", Toast.LENGTH_SHORT).show();
                        }else {
                            //列表为空
                            Toast.makeText(AttentionActivity.this, "暂无数据!", Toast.LENGTH_SHORT).show();
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }
                springView.onFinishFreshAndLoad ();
            }

            @Override
            public void onError(String error) {
                springView.onFinishFreshAndLoad ();
                Toast.makeText(AttentionActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initView() {
        new TitleBuilder (AttentionActivity.this).setTitleText (title).setLeftIco (R.mipmap.common_black_back).setLeftIcoListening (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
    }

    /**
     * 关注列表
     */
    public void getusercollection() {

        HttpHelper.getusercollection (pageIndex, pageSize, LocalInfoUtils.getUserId (), new HttpHelper.HttpUtilsCallBack<String> () {
            @Override
            public void onFailure(String failure) {
                springView.onFinishFreshAndLoad ();
                Toast.makeText(AttentionActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                UserAttentionBean person = FastJSONHelper.getPerson (succeed, UserAttentionBean.class);
                if (person != null){
                    List<UserAttentionBean.DataBean.ArrayBean> array = person.getData().getArray();
                    int size;
                    if(pageIndex == 1){
                        list.clear();
                        size = 0;
                    }else {
                        size = list.size();
                    }
                    if(null != array && !array.isEmpty()){
                        // 加载数据成功
                        list.addAll(array);
                        array.clear();
                        recyclerView.scrollToPosition(size);
                    } else {
                        if(!list.isEmpty()){
                            // 加载成功list无数据情况
                            Toast.makeText(AttentionActivity.this, "没有新数据了!", Toast.LENGTH_SHORT).show();
                        }else {
                            //列表为空
                            Toast.makeText(AttentionActivity.this, "暂无数据!", Toast.LENGTH_SHORT).show();
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }
                springView.onFinishFreshAndLoad ();
            }

            @Override
            public void onError(String error) {
                springView.onFinishFreshAndLoad ();
                Toast.makeText(AttentionActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }


}

