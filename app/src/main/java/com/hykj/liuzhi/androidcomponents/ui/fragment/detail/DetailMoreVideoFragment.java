package com.hykj.liuzhi.androidcomponents.ui.fragment.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.DetailCommentBean;
import com.hykj.liuzhi.androidcomponents.bean.DetailMoreVideoBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.DetailVideoActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.video.bean.VideoPointBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailCommentAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailMoreVideoAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.detail.bean.VideoDetailBean;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
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
import butterknife.Unbinder;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */
public class DetailMoreVideoFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    DetailMoreVideoAdapter mAdapter;
    @BindView(R.id.fragment_detail_item)
    SmartRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    String videoid = "";

    private void initView() {
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            videoid = bundle.getString("videoid");
        }
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        refreshLayout.setEnableRefresh(true);//启用刷新
        refreshLayout.setEnableLoadmore(true);//启用加载
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                datas.clear();
                videoispoint();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                videoispoint();
                refreshlayout.finishLoadmore();
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        videoispoint();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 所有视频
     */
    int page = 1;
    List<VideoDetailBean.DataBean.ArrayBean> datas = new ArrayList<>();

    public void videoispoint() {
        HttpHelper.videolist(page + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                VideoDetailBean entity = FastJSONHelper.getPerson(succeed, VideoDetailBean.class);
                for (int i = 0; i < entity.getData().getArray().size(); i++) {
                    datas.add(entity.getData().getArray().get(i));
                }
                if (mAdapter == null) {
                    mAdapter = new DetailMoreVideoAdapter(datas, getContext());
                    mAdapter.setOnItemClickListener(DetailMoreVideoFragment.this);
                    rv.setAdapter(mAdapter);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.putExtra("videoid", datas.get(position).getVideo_id() + "");
        intent.setClass(getContext(), DetailVideoActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
