package com.hykj.liuzhi.androidcomponents.ui.activity.softtext;

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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.MainActivity;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.net.http.ApiConstant;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.BaseActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.issue.IssueClumnAddBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.softtext.ben.GetimagetextlabelsBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.GridImageAdapter;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.GetsowingBean;
import com.hykj.liuzhi.androidcomponents.ui.widget.FullyGridLayoutManager;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.youth.banner.Banner;
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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhouyou.http.EasyHttp.getContext;

/**
 * 软文添加
 * Created by user on 2018/10/10.
 */
public class Act_addsofttext extends BaseActivity implements View.OnClickListener, Spinner.OnItemSelectedListener {

    @BindView(R.id.ll_submit_success)
    View mLlSubmitSuccess;
    @BindView(R.id.recycler_issue_column)
    RecyclerView recyclerIssueColumn;

    private int maxSelectNum = 9;
    private int chooseMode = PictureMimeType.ofImage();
    private List<LocalMedia> selectList = new ArrayList<>();
    private int themeId;
    private GridImageAdapter mGridImageAdapter;
    private String mTitle;
    private ImageView issue_hader;
    private EditText issue_imagetexttext, addsofttext_application;
    private Spinner issue_imagetextlabelid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_addsofttext);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private Banner banner;

    private void initView() {
        banner = findViewById(R.id.banner);
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        themeId = R.style.picture_default_style;
        issue_hader = findViewById(R.id.issue_hader);
        issue_imagetexttext = findViewById(R.id.issue_imagetexttext);
        issue_imagetextlabelid = findViewById(R.id.issue_imagetextlabelid);
        findViewById(R.id.issue_submit).setOnClickListener(this);
        if (new MainActivity().getSelectList() != null && new MainActivity().getSelectList().size() > 0) {
            for (int i = 0; i < new MainActivity().getSelectList().size(); i++) {
                selectList.add(new MainActivity().getSelectList().get(i));
                File file = new File(selectList.get(0).getPath());
                Glide.with(this).load(file).into(issue_hader);
            }
        }
        addsofttext_application = findViewById(R.id.addsofttext_application);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(Act_addsofttext.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerIssueColumn.setLayoutManager(manager);
        mGridImageAdapter = new GridImageAdapter(Act_addsofttext.this, onAddPicClickListener);
        mGridImageAdapter.setList(selectList);
        mGridImageAdapter.setSelectMax(maxSelectNum);
        recyclerIssueColumn.setAdapter(mGridImageAdapter);
        mGridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
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
                            PictureSelector.create(Act_addsofttext.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(Act_addsofttext.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(Act_addsofttext.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
        Getsowing("1", "10", "3");
    }

    private void initData() {
        new TitleBuilder(Act_addsofttext.this).setTitleText(mTitle).setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getimagetextlabels();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(Act_addsofttext.this)
                    .openGallery(chooseMode)
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .selectionMode(PictureConfig.MULTIPLE)
                    .isCamera(true)
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled(true) // 裁剪是否可旋转图片
                    //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    //.videoSecond()//显示多少秒以内的视频or音频也可适用
                    //.recordVideoSecond()//录制视频秒数 默认60s
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
                    mGridImageAdapter.setList(selectList);
                    mGridImageAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    final UIProgressResponseCallBack listener = new UIProgressResponseCallBack() {
        @Override
        public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
            int progress = (int) (bytesRead * 100 / contentLength);
            HttpLog.e(progress + "% ");
        }
    };

    /**
     * @param files            //文件
     * @param imagetexttext    //图文内容
     * @param imagetextlabelid 图文标签
     * @param user_id          用户id
     */
    private ACache aCache;
    HttpParams params = new HttpParams();

    /**
     * 软文添加
     *
     * @param imagetexttext
     * @param imagetextlabelid
     */
    String imagetextlabelid = "";

    public void postAddsofTtextFile(String application, String imagetexttext) {
        aCache = ACache.get(this);
        if (selectList.size() == 0) {
            Toast.makeText(getContext(), "请选择一张图片", Toast.LENGTH_SHORT).show();
            return;
        }
        if (application.equals("")) {
            Toast.makeText(getContext(), "请输入软文标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (imagetexttext.equals("")) {
            Toast.makeText(getContext(), "请输入软文内容", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (imagetextlabelid.equals("")) {
//            Toast.makeText(getContext(), "请输入一个标签", Toast.LENGTH_SHORT).show();
//            return;
//        }
        for (int i = 0; i < selectList.size(); i++) {
            File file = new File(selectList.get(i).getPath());
            params.put("files" + i, file, listener);
        }
        EasyHttp.post(ApiConstant.Advertorial_addsofttext)
                .baseUrl(ApiConstant.ROOT_URL)
                .params("user_id", aCache.getAsString("user_id"))
                .params("application", application)
//                .params("imagetextlabelid", imagetextlabelid)
                .params("text", imagetexttext)
                .params("num", selectList.size() + "")
                .params(params)
                .execute(new ProgressDialogCallBack<String>(mProgressDialog, true, true) {
                    @Override
                    public void onSuccess(String succeed) {
                        IssueClumnAddBean entity = FastJSONHelper.getPerson(succeed, IssueClumnAddBean.class);
                        if (entity.getError() != 0) {
                            Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                        mLlSubmitSuccess.setVisibility(View.VISIBLE);
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
            ProgressDialog dialog = new ProgressDialog(Act_addsofttext.this);
            dialog.setMessage("上传中...");
            return dialog;
        }
    };

    @Override
    public void onClick(View v) {//担交
        switch (v.getId()) {
            case R.id.issue_submit:
                postAddsofTtextFile(addsofttext_application.getText().toString(), issue_imagetexttext.getText().toString());
                break;
            case R.id.issue_table://添加标签
                break;
        }
    }

    Gson gson = new Gson();
    private GetimagetextlabelsBean getimagetextlabelsBean;

    public void getimagetextlabels() {
        HttpHelper.Advertorial_getimagetextlabels(new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                getimagetextlabelsBean = gson.fromJson(succeed, GetimagetextlabelsBean.class);
                List<String> data_list = new ArrayList<String>();
                for (int i = 0; i < getimagetextlabelsBean.getData().getArray().size(); i++) {
                    data_list.add(getimagetextlabelsBean.getData().getArray().get(i).getImagetextlabel_name());
                }
                ArrayAdapter arr_adapter = new ArrayAdapter<String>(Act_addsofttext.this, android.R.layout.simple_spinner_item, data_list);
                //设置样式
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                issue_imagetextlabelid.setAdapter(arr_adapter);
                issue_imagetextlabelid.setOnItemSelectedListener(Act_addsofttext.this);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        imagetextlabelid = getimagetextlabelsBean.getData().getArray().get(position).getImagetextlabel_id() + "";
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    ArrayList pics = new ArrayList();

    public void Getsowing(String page, String number, String type) {
        HttpHelper.Getsowing(page, number, type, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                GetsowingBean entity = FastJSONHelper.getPerson(succeed, GetsowingBean.class);
                if (entity.getCode() == 0) {
                    pics.clear();
                    for (int i = 0; i < entity.getData().getArray().size(); i++) {
                        pics.add(entity.getData().getArray().get(i).getSowing_url());
                    }
                    banner.setImages(pics);
                    banner.setImageLoader(new GlideImageLoader())
                            .setDelayTime(5000)
                            .start();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
