package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.MainActivity;
import com.hykj.liuzhi.androidcomponents.bean.GetreporTreasonBean;
import com.hykj.liuzhi.androidcomponents.net.http.ApiConstant;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.issue.IssueClumnAddBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.GridImageAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.FullyGridLayoutManager;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.body.UIProgressResponseCallBack;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;
import com.zhouyou.http.subsciber.IProgressDialog;
import com.zhouyou.http.utils.HttpLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * @author: lujialei
 * @date: 2018/10/25
 * @describe:
 */


public class ReportActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private String reportuserid;
    private String title, reportreasonid;
    private Spinner mySpinner;
    RecyclerView recyclerView;
    private EditText issue_imagetexttext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        initView();
        initData();
    }

    public void initView() {
        reportuserid = getIntent().getStringExtra("reportuserid");
        title = getIntent().getStringExtra("title");
        mySpinner = findViewById(R.id.spinner2);
        issue_imagetexttext = findViewById(R.id.issue_imagetexttext);
        switch (title) {
            case "image"://图圈举报
                Log.e("aa", "-------图圈举报-------");
                break;
            case "video":
                Log.e("aa", "-------视频-------");
                break;
            case "soft":
                Log.e("aa", "-------软文-------");
                break;
        }
        findViewById(R.id.report_commit).setOnClickListener(this);
        recyclerView = findViewById(R.id.recycler);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(ReportActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(ReportActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(ReportActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                    }
                }
            }
        });
    }

    GridImageAdapter adapter;

    public void initData() {
        backGetreporTreason();
        themeId = R.style.picture_default_style;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.report_commit://提交
                postFile(issue_imagetexttext.getText().toString());
                break;
        }
    }

    private ArrayAdapter<String> arr_adapter;
    private List<String> data_list = new ArrayList<>();
    GetreporTreasonBean entity;

    public void backGetreporTreason() {
        data_list.clear();
        HttpHelper.getreportreason(new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                Gson gson = new Gson();
                entity = gson.fromJson(succeed, GetreporTreasonBean.class);
                for (int i = 0; i < entity.getData().size(); i++) {
                    data_list.add(entity.getData().get(i).getName());
                }
                //适配器
                arr_adapter = new ArrayAdapter<String>(ReportActivity.this, android.R.layout.simple_spinner_item, data_list);
                //设置样式
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                mySpinner.setAdapter(arr_adapter);
                mySpinner.setOnItemSelectedListener(ReportActivity.this);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        reportreasonid = entity.getData().get(position).getId() + "";
        Log.e("aa", entity.getData().get(position).getId() + "-----------" + entity.getData().get(position).getName());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private int chooseMode = PictureMimeType.ofImage();
    private int themeId;
    private int maxSelectNum = 9;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(ReportActivity.this)
                    .openGallery(chooseMode)
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .selectionMode(PictureConfig.MULTIPLE)
                    .isCamera(true)
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList1 = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList1) {
                        Log.i("图片-----》", media.getPath());
                        selectList.add(media);
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }


    /**
     * @param files            //文件
     * @param imagetexttext    //图文内容
     * @param imagetextlabelid 图文标签
     * @param user_id          用户id
     */
    private ACache aCache;
    private String imagetextlabelid = "";
    //    HttpParams params = new HttpParams();
    List<File> files = new ArrayList<>();

    public void postFile(String imagetexttext) {
        aCache = ACache.get(this);
        if (selectList.size() == 0) {
            Toast.makeText(getContext(), "请选择一张图片", Toast.LENGTH_SHORT).show();
            return;
        }
        if (imagetexttext.equals("")) {
            Toast.makeText(getContext(), "请输入图文内容", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < selectList.size(); i++) {
            File file = new File(selectList.get(i).getPath());
//            params.put("reportimage", file, listener);
            files.add(file);
        }
        EasyHttp.post(ApiConstant.doreport)
                .baseUrl(ApiConstant.ROOT_URL)
                .params("user_id", aCache.getAsString("user_id"))
                .params("reportuserid", reportuserid)//被举报人用户id
                .params("reportreasonid", reportreasonid)//举报类型id
                .params("reportcontent", imagetexttext)//举报说明
                .addFileParams("reportimage", files,listener)
                .execute(new ProgressDialogCallBack<String>(mProgressDialog, true, true) {
                    @Override
                    public void onSuccess(String succeed) {
                        IssueClumnAddBean entity = FastJSONHelper.getPerson(succeed, IssueClumnAddBean.class);
                        if (entity.getError() != 0) {
                            Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    IProgressDialog mProgressDialog = new IProgressDialog() {
        @Override
        public Dialog getDialog() {
            ProgressDialog dialog = new ProgressDialog(ReportActivity.this);
            dialog.setMessage("上传中中...");
            return dialog;
        }
    };

    final UIProgressResponseCallBack listener = new UIProgressResponseCallBack() {
        @Override
        public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
            int progress = (int) (bytesRead * 100 / contentLength);
            HttpLog.e(progress + "% ");
        }
    };

}
