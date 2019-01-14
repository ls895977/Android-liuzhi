package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.MineFileBean;
import com.hykj.liuzhi.androidcomponents.bean.UserData;
import com.hykj.liuzhi.androidcomponents.net.http.ApiConstant;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.Dlg_Photograph;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.bean.UserFileupLoadsBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.utils.CompressFileUtil;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.utils.CompressHelper;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.RxPermissions;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.body.UIProgressResponseCallBack;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.IProgressDialog;
import com.zhouyou.http.utils.HttpLog;
import com.zhouyou.http.utils.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.zhouyou.http.EasyHttp.getContext;

public class TrueNameIdenActivity extends BaseActivity implements Dlg_Photograph.OnClick {
    private int FC = 1000; //正
    private int FB = FC + 1;//反
    private int FH = FB + 1;//手持
    private int DF = FC;//默认选择
    File frontFile;
    File backFile;
    File handFile;
    @BindView(R.id.tv_truename_identyfy_back)
    ImageView tvTruenameIdentyfyBack;
    @BindView(R.id.tv_truename_identyfy_commit)
    TextView tvTruenameIdentyfyCommit;
    private RxPermissions mRxPermissions;
    private Dlg_Photograph dialogPhoto;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = REQUEST_CAPTURE + 1;
    private ImageView Positive, Opposite, Hold;
    private EditText verified_name, verified_number;
    private ACache aCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truename_identyfy);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        aCache = ACache.get(this);
        mRxPermissions = new RxPermissions(this);
        Positive = findViewById(R.id.truename_Positive);
        Opposite = findViewById(R.id.truename_Opposite);
        Hold = findViewById(R.id.truename_Hold);
        verified_name = findViewById(R.id.verified_name);
        verified_number = findViewById(R.id.verified_number);
        mRxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            frontFile = new File(Environment.getExternalStorageDirectory() + File.separator + "frontFile.jpg");
                            backFile = new File(Environment.getExternalStorageDirectory() + File.separator + "backFile.jpg");
                            handFile = new File(Environment.getExternalStorageDirectory() + File.separator + "handFile.jpg");
                        } else {
                            frontFile = new File(getCacheDir() + File.separator + "frontFile.jpg");
                            backFile = new File(getCacheDir() + File.separator + "backFile.jpg");
                            handFile = new File(getCacheDir() + File.separator + "handFile.jpg");
                        }
                        creatTemp(frontFile);
                        creatTemp(backFile);
                        creatTemp(handFile);
                    }
                });
    }

    private void creatTemp(File file) {
        if (file == null) return;
        try {
            if (file.exists())
                file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tv_truename_identyfy_back, R.id.tv_truename_identyfy_commit, R.id.truename_Positive, R.id.truename_Opposite, R.id.truename_Hold})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_truename_identyfy_back:
                finish();
                break;
            case R.id.tv_truename_identyfy_commit:
                commit();
                break;
            case R.id.truename_Positive://正面
                DF = FC;
                choose();
                break;
            case R.id.truename_Opposite://反面
                DF = FB;
                choose();
                break;
            case R.id.truename_Hold://手持
                DF = FH;
                choose();
                break;
        }
    }

    private void choose() {
        if (dialogPhoto == null)
            dialogPhoto = new Dlg_Photograph(TrueNameIdenActivity.this, this);
        dialogPhoto.show();
    }

    @Override
    public void onItem(int p) {
        switch (p) {
            case 1:
                gotoCamera();
                break;
            case 2:
                gotoPhoto();
                break;
            default:
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void gotoPhoto() {
        mRxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            choosePic();
                            dialogPhoto.dismiss();
                        } else {
                            Toast.makeText(TrueNameIdenActivity.this, "读取存储器权限未授予，去设置中心设置", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void choosePic() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    @SuppressLint("CheckResult")
    private void gotoCamera() {
        mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            carema();
                            dialogPhoto.dismiss();
                        } else {
                            Toast.makeText(TrueNameIdenActivity.this, "相机权限未授予，去设置中心设置", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public static boolean isBelowAndroidVersion(int version) {
        return Build.VERSION.SDK_INT < version;
    }

    public static String getFileProviderName(Context context) {
        return context.getPackageName() + ".fileprovider";
    }

    private void carema() {
        File tempFile = whoFile(DF);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (isBelowAndroidVersion(Build.VERSION_CODES.N))
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        else {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    FileProvider.getUriForFile(this, getFileProviderName(this), tempFile));
        }
        try {
            startActivityForResult(intent, REQUEST_CAPTURE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File whoFile(int df) {
        File tempFile = frontFile;
        if (df == FB) {
            tempFile = backFile;
        } else if (df == FH) {
            tempFile = handFile;
        }
        return tempFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File tempFile = whoFile(DF);
        switch (requestCode) {
            case REQUEST_CAPTURE:
                //知道缓存文件了
                tempFile = compress(Uri.fromFile(tempFile));
                showFile(tempFile, DF);
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (data == null) return;
                tempFile = compress(data.getData());
                showFile(tempFile, DF);
                break;
            default:
                break;
        }
        setFile(tempFile, DF);
    }

    private File compress(@io.reactivex.annotations.Nullable Uri uri) {
        try {
            File oldFile = CompressFileUtil.getTempFile(this, uri);
            return CompressHelper.getDefault(getApplicationContext()).compressToFile(oldFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    boolean frontIv1 = false, backIv1 = false, handIv1 = false;

    private void showFile(File file, int df) {
        if (df == FC) {
            frontIv1 = true;
            Positive.setImageURI(Uri.fromFile(file));
        } else if (df == FB) {
            backIv1 = true;
            Opposite.setImageURI(Uri.fromFile(file));
        } else if (df == FH) {
            handIv1 = true;
            Hold.setImageURI(Uri.fromFile(file));
        }
    }

    private void setFile(File file, int df) {
        if (df == FC) {
            frontFile = file;
        } else if (df == FB) {
            backFile = file;
        } else if (df == FH) {
            handFile = file;
        }
    }

    private void commit() {
        String name = getEditText(verified_name);
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(TrueNameIdenActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        String idcard = getEditText(verified_number);
        if (TextUtils.isEmpty(idcard)) {
            Toast.makeText(TrueNameIdenActivity.this, "请输入身份证号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (frontIv1 == false) {
            Toast.makeText(TrueNameIdenActivity.this, "请上传身份证正面", Toast.LENGTH_SHORT).show();
            return;
        }
        if (backIv1 == false) {
            Toast.makeText(TrueNameIdenActivity.this, "请上传身份证反面", Toast.LENGTH_SHORT).show();
            return;
        }
        if (handIv1 == false) {
            Toast.makeText(TrueNameIdenActivity.this, "请上传手持身份证照", Toast.LENGTH_SHORT).show();
            return;
        }
        aCache = ACache.get(this);
        EasyHttp.post(ApiConstant.Min_userfileuploads)
                .baseUrl(ApiConstant.ROOT_URL)
                .params("rname", name)
                .params("idcard", idcard)
                .params("user_id", aCache.getAsString("user_id"))
                .params("idcardpos", frontFile, listener)
                .params("idcardback", backFile, listener)
                .params("idcardpeople", handFile, listener)
                .execute(new ProgressDialogCallBack<String>(mProgressDialog, true, true) {
                    @Override
                    public void onSuccess(String succeed) {
                        UserFileupLoadsBean entity = FastJSONHelper.getPerson(succeed, UserFileupLoadsBean.class);
                        if (entity.getError() != 0) {
                            Toast.makeText(getContext(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getContext(),"上传成功！", Toast.LENGTH_SHORT).show();
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
            ProgressDialog dialog = new ProgressDialog(TrueNameIdenActivity.this);
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

    protected String getEditText(EditText et) {
        if (et != null)
            return et.getText().toString();
        return null;
    }
}
