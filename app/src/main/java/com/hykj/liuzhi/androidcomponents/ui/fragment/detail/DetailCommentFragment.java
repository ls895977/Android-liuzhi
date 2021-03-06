package com.hykj.liuzhi.androidcomponents.ui.fragment.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.hykj.liuzhi.androidcomponents.ui.activity.ReportActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.circle.DetailCircleImageListBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailCommentAdapter;
import com.hykj.liuzhi.androidcomponents.ui.adapter.DetailImageTextListAdapter;
import com.hykj.liuzhi.androidcomponents.ui.popup.CommentMorePopup;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.DensityUtils;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
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


/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class DetailCommentFragment extends Fragment implements View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, CommentMorePopup.Callback {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    DetailCommentAdapter mAdapter;
    @BindView(R.id.fragment_detail_message)
    EditText message;
    private String videoid = "";
    private ACache aCache;
    @BindView(R.id.fragment_detail_refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<DetailCommetListBean.DataBean.ArrayBean> datas = new ArrayList<>();
    @BindView(R.id.iv_send)
    ImageView send;
    String status = "", imagetext_id = "";

    private int mCurrentPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private int page = 1;

    private void initView() {
        aCache = ACache.get(getActivity());
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            status = bundle.getString("status");
            if (status.equals("video")) {
                videoid = bundle.getString("videoid");
            } else {
                imagetext_id = bundle.getString("imagetext");
            }
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
                imagetextList.clear();
                if (status.equals("video")) {
                    videomessageall();
                } else {
                    Cirde_imagetextmessageall();
                }
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                if (status.equals("video")) {
                    videomessageall();
                } else {
                    Cirde_imagetextmessageall();
                }
                refreshlayout.finishLoadmore();
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        send.setOnClickListener(this);
        if (status.equals("video")) {//视频评论
            videomessageall();
        } else {
            Cirde_imagetextmessageall();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    String msg = "";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_send://发送
                if (status.equals("video")) {
                    senVideoMessage();
                } else {
                    Cirde_imagetextmessage();
                }
                break;
        }
    }

    /**
     * 发送消息
     */
    public void senVideoMessage() {
        msg = message.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(getContext(), "请输入您要评论的内容!", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHelper.videomessage(videoid + "", aCache.getAsString("user_id"), msg, new HttpHelper.HttpUtilsCallBack<String>() {
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
                        if (status.equals("video")) {
                            datas.clear();
                            videomessageall();
                        } else {
                            imagetextList.clear();
                            Cirde_imagetextmessageall();
                        }
                    }
                }
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
    DetailCommetListBean entityVideo;

    public void videomessageall() {
        HttpHelper.videomessageall(page + "", videoid, new HttpHelper.HttpUtilsCallBack<String>() {
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
                entityVideo = FastJSONHelper.getPerson(succeed, DetailCommetListBean.class);
                for (int i = entityVideo.getData().getArray().size() - 1; i >= 0; i--) {
                    datas.add(entityVideo.getData().getArray().get(i));
                }
                if (mAdapter == null) {
                    mAdapter = new DetailCommentAdapter(datas);
                    mAdapter.setOnItemChildClickListener(DetailCommentFragment.this);
                    rv.setAdapter(mAdapter);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                try {
                    if (page > 1) {
                        Toast.makeText(getContext(), "暂无更多评论！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception s) {
                }
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


    /**
     * 图文评论
     */
    public void Cirde_imagetextmessage() {
        msg = message.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(getContext(), "请输入您要评论的内容!", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpHelper.Cirde_imagetextmessage(imagetext_id, msg + "", aCache.getAsString("user_id"), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                VideomessageBean entity = FastJSONHelper.getPerson(succeed, VideomessageBean.class);
                if (entity.getCode() == 0) {
                    if (entity.getMsg().equals("评论成功")) {
                        message.setText("");
                        Toast.makeText(getContext(), "发送成功！", Toast.LENGTH_SHORT).show();
                        page = 1;
                        imagetextList.clear();
                        Cirde_imagetextmessageall();
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
     * 图文评论列表
     */
    DetailImageTextListAdapter imageTextListAdapter;
    List<DetailCircleImageListBean.DataBean> imagetextList = new ArrayList();

    public void Cirde_imagetextmessageall() {
        HttpHelper.Cirde_imagetextmessageall(page + "", imagetext_id + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                DetailCircleImageListBean entity = FastJSONHelper.getPerson(succeed, DetailCircleImageListBean.class);
                for (int i = entity.getData().size() - 1; i >= 0; i--) {
                    imagetextList.add(entity.getData().get(i));
                }
                if (imageTextListAdapter == null) {
                    imageTextListAdapter = new DetailImageTextListAdapter(imagetextList);
                    imageTextListAdapter.setOnItemChildClickListener(DetailCommentFragment.this);
                    rv.setAdapter(imageTextListAdapter);
                } else {
                    imageTextListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                if (page > 1) {
                    Toast.makeText(getContext(), "暂无更多评论！", Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        });
    }

    @Override
    public void comment() {
        View writeCommentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_back_comment, null);
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
                HttpHelper.messagereply(null, videoid, aCache.getAsString("user_id"), String.valueOf(datas.get(mCurrentPosition).getMessage_id()),
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
                                bean.setUser_id(Integer.parseInt(aCache.getAsString("user_id")));
                                bean.setUserdata(userdataBean);
                                datas.get(mCurrentPosition).reply.add(bean);
                                mAdapter.notifyItemChanged(mCurrentPosition);
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
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_back_comment, 0);
            }
        });
    }

    @Override
    public void report() {
        if (status.equals("video")) {
            if (aCache.getAsString("user_id").equals(datas.get(mCurrentPosition).getUser_id())) {
                Toast.makeText(getContext(), "自己不能举报自己！", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("title", "video");
                intent.putExtra("reportuserid", (datas.get(mCurrentPosition).getUser_id() + ""));
                intent.setClass(getContext(), ReportActivity.class);
                startActivity(intent);
            }
        } else {
            if (aCache.getAsString("user_id").equals(imagetextList.get(mCurrentPosition).getUser_id())) {
                Toast.makeText(getContext(), "自己不能举报自己！", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("title", "image");
                intent.putExtra("reportuserid", (imagetextList.get(mCurrentPosition).getUser_id() + ""));
                intent.setClass(getContext(), ReportActivity.class);
                startActivity(intent);
            }
        }
    }
}
