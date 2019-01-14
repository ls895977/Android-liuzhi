package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.MainActivity;
import com.hykj.liuzhi.androidcomponents.bean.MineFileBean;
import com.hykj.liuzhi.androidcomponents.bean.SignInBean;
import com.hykj.liuzhi.androidcomponents.bean.UserData;
import com.hykj.liuzhi.androidcomponents.bean.UserTableBean;
import com.hykj.liuzhi.androidcomponents.constant.AppConstant;
import com.hykj.liuzhi.androidcomponents.constant.ComParamContact;
import com.hykj.liuzhi.androidcomponents.model.AuthModel;
import com.hykj.liuzhi.androidcomponents.model.LoginCache;
import com.hykj.liuzhi.androidcomponents.net.http.ApiConstant;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.token.TokenManager;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_Photograph;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.RxPermissions;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.SchedulerTransformer;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.hykj.liuzhi.androidcomponents.utils.MD5;
import com.hykj.liuzhi.androidcomponents.utils.RoundImageView;
import com.hykj.liuzhi.androidcomponents.utils.TitleBuilder;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.body.UIProgressResponseCallBack;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.IProgressDialog;
import com.zhouyou.http.utils.HttpLog;
import com.zhouyou.http.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.zhouyou.http.EasyHttp.getContext;

public class EditUserDataActivity extends BaseActivity implements Dlg_Photograph.OnClick {
    @BindView(R.id.rl_edit_userdata_changehead)
    RelativeLayout rlEditUserdataChangehead;
    @BindView(R.id.rl_edit_userdata_label)
    RelativeLayout rlEditUserdataLabel;
    @BindView(R.id.tv_edit_userdata_label)
    TextView tvEditUserdataLabel;
    @BindView(R.id.iv_sign_rightrow)
    ImageView ivSignRightrow;
    @BindView(R.id.rl_edit_userdata_nick)
    RelativeLayout rlEditUserdataNick;
    @BindView(R.id.iv_edituser_biaoqian)
    ImageView ivEdituserBiaoqian;
    @BindView(R.id.rl_edit_userdata_signname)
    RelativeLayout rlEditUserdataSignname;
    @BindView(R.id.rl_edit_userdata_sex)
    RelativeLayout rlEditUserdataSex;
    @BindView(R.id.tv_edit_userdata_sex)
    TextView tvEditUserdataSex;
    @BindView(R.id.rl_edit_userdata_email)
    RelativeLayout rlEditUserdataEmail;
    @BindView(R.id.tv_edit_userdata_mail)
    TextView mail;
    @BindView(R.id.iv_edituser_head1)
    RoundImageView haderImage;
    @BindView(R.id.tv_edit_userdata_autograph)
    TextView tv_edit_userdata_autograph;
    private ArrayList<UserTableBean> tableSexList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_userdata);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        getOptionSexData();
        seetingUser();
        photo = new Dlg_Photograph(EditUserDataActivity.this, this);
    }

    private void getOptionSexData() {
        tableSexList.add(new UserTableBean(1, "男"));
        tableSexList.add(new UserTableBean(2, "女"));

    }


    private void initView() {
        new TitleBuilder(this).setTitleText("编辑主页").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private Dlg_Photograph photo;

    @OnClick({R.id.rl_edit_userdata_changehead, R.id.rl_edit_userdata_label, R.id.rl_edit_userdata_nick,
            R.id.rl_edit_userdata_signname, R.id.rl_edit_userdata_sex, R.id.rl_edit_userdata_email,
            R.id.iv_edituser_head1})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_edit_userdata_changehead:
                break;
            case R.id.rl_edit_userdata_label:
                ChangeUserTable();
                break;
            case R.id.rl_edit_userdata_nick:
                intent = new Intent(EditUserDataActivity.this, ChangeNameActivity.class);
                intent.putExtra("position", 1);
                startActivityForResult(intent, 1);
                break;

            case R.id.rl_edit_userdata_signname:
                intent = new Intent(EditUserDataActivity.this, ChangeNameActivity.class);
                intent.putExtra("position", 2);
                startActivityForResult(intent, 1);
                break;
            case R.id.rl_edit_userdata_sex:
                ChangeUserSexTble();
                break;

            case R.id.rl_edit_userdata_email:
                intent = new Intent(EditUserDataActivity.this, BindEmailActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.iv_edituser_head1://头像
                photo.show();
                break;
        }
    }

    private void ChangeUserSexTble() {
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(EditUserDataActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = tableSexList.get(options1).getPickerViewText();
                tvEditUserdataSex.setText(tx);
            }
        }).build();
        pvOptions.setPicker(tableSexList);
        pvOptions.show();
    }

    /*更改标签*/
    private void ChangeUserTable() {
        Intent intent = new Intent(this, ChangeUserTableActivity.class);
        startActivityForResult(intent, 0);
    }
    public void seetingUser() {
        Glide.with(this).load(LocalInfoUtils.getUserself("user_pic")).into(haderImage);
        tvEditUserdataSex.setText(LocalInfoUtils.getUserself("user_sex"));
        String lablist = LocalInfoUtils.getUserself("user_label");
        if (lablist.contains("]")) {
            String[] lab1 = lablist.split("\\[");
            String[] lab2 = lab1[1].split("]");
            tvEditUserdataLabel.setText(lab2[0]);
        } else {
            tvEditUserdataLabel.setText(lablist);
        }
        mail.setText(LocalInfoUtils.getUserself("user_mail"));
        tv_edit_userdata_autograph.setText(LocalInfoUtils.getUserself("user_autograph"));
    }

    @Override
    public void onItem(final int p) {
        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .compose(new SchedulerTransformer<Boolean>())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            switch (p) {
                                case 1://拍照
                                    camera();
                                    photo.dismiss();
                                    break;
                                case 2://从手机相册选择
                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(intent, 1);
                                    photo.dismiss();
                                    break;
                            }
                        } else {
                            Toast.makeText(getContext(), "请打开读写存储卡，照相机权限 ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 调起拍照
     */
    private File currentImageFile;

    public void camera() {
        File dir = new File(Environment.getExternalStorageDirectory(), "myimage");//在sd下创建文件夹myimage；Environment.getExternalStorageDirectory()得到SD卡路径文件
        if (!dir.exists()) {    //exists()判断文件是否存在，不存在则创建文件
            dir.mkdirs();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式在android中，创建文件时，文件名中不能包含“：”冒号
        String filename = df.format(new Date());
        currentImageFile = new File(dir, filename + ".jpeg");
        if (!currentImageFile.exists()) {
            try {
                currentImageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (android.os.Build.VERSION.SDK_INT < 24) {
            currentImageFile = new File(dir, filename + ".jpeg");
            // 从文件中创建uri
            uri = Uri.fromFile(currentImageFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            // 兼容Android 7.0 使用共享文件的形式
            ContentValues contentValues = new ContentValues();
            uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        startActivityForResult(intent, 2);
    }

    Uri uri;
    private File file = null;
    String paths = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 0) {
                String lablist = LocalInfoUtils.getUserself("user_label");
                tvEditUserdataLabel.setText(lablist);
                return;
            }
        }
        if (requestCode == 1) {
            if (resultCode == 2) {
                seetingUser();
                return;
            }
        }
        switch (requestCode) {
            case 1://文件中选择照片
                if (data == null) {
                    return;
                }
                uri = data.getData();
                file = new File(getPath(uri, EditUserDataActivity.this));
                Debug.e("--------文件中选择照片" + file.getPath());
                Avatar();
                break;
            case 2://拍照完成回调
                if (android.os.Build.VERSION.SDK_INT < 24) {
                    file = currentImageFile;
                } else {
                    file = new File(getPath(uri, EditUserDataActivity.this));
                }
                Debug.e("--------拍照完成回调" + file.getPath());
                Avatar();
                break;
        }

    }

    /**
     * URI得到路劲
     *
     * @param uri
     * @param act
     * @return
     */
    public static String getPath(Uri uri, Activity act) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = act.managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void Avatar() {
//新建一个File，传入文件夹目录
        File file1 = new File(Environment.getExternalStorageDirectory(), "mywork");
//判断文件夹是否存在，如果不存在就创建，否则不创建
        if (!file1.exists()) {
            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
            file1.mkdirs();
        }
        Luban.with(this)
                .load(file)
                .ignoreBy(20)
                .setTargetDir(file1.getPath())
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @SuppressLint("CheckResult")
                    @Override
                    public void onSuccess(File file) {
                        Debug.e("onSuccess--------" + file.getPath());
                        postFile(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Debug.e("onError--------" + e.getLocalizedMessage());
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();
    }

    final UIProgressResponseCallBack listener = new UIProgressResponseCallBack() {
        @Override
        public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
            int progress = (int) (bytesRead * 100 / contentLength);
            HttpLog.e(progress + "% ");
        }
    };

    public void postFile(File files) {
        Debug.e("postFile--------" + files.getPath());
        EasyHttp.post(ApiConstant.Min_ChangeUserPic)
                .baseUrl(ApiConstant.ROOT_URL)
                .params("userpic", files, files.getName(), listener)
                .execute(new ProgressDialogCallBack<String>(mProgressDialog, true, true) {
                    @Override
                    public void onSuccess(String succeed) {
                        MineFileBean entity = FastJSONHelper.getPerson(succeed, MineFileBean.class);
                        if (entity.getFile().getError() != 0) {
                            Toast.makeText(getContext(), entity.getFile().getMsg(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Debug.e("onSuccess--------" + succeed);
                        Toast.makeText(getContext(), entity.getFile().getMsg(), Toast.LENGTH_SHORT).show();
                        getUserself();
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
            ProgressDialog dialog = new ProgressDialog(EditUserDataActivity.this);
            dialog.setMessage("上传中中...");
            return dialog;
        }
    };
    ACache aCache;

    //获取用户数据
    public void getUserself() {
        aCache = ACache.get(this);
        HttpHelper.getUserself(Integer.valueOf(aCache.getAsString("user_id")), new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                Toast.makeText(EditUserDataActivity.this, failure, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSucceed(String succeed) {
                LocalInfoUtils.saveUserself(succeed);
                Glide.with(EditUserDataActivity.this).load(LocalInfoUtils.getUserself("user_pic")).into(haderImage);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(EditUserDataActivity.this, ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
