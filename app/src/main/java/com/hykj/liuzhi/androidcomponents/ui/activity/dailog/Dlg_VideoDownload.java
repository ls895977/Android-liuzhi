package com.hykj.liuzhi.androidcomponents.ui.activity.dailog;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.FileBean;
import com.hykj.liuzhi.androidcomponents.net.http.ApiConstant;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.FileUtils;
import com.hykj.liuzhi.androidcomponents.utils.TimeUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.utils.HttpLog;

import java.util.List;
import java.util.Random;

/**
 * 下载
 * Created by lishan on 2017/12/22.
 */
public class Dlg_VideoDownload extends BaseDialog {
    private EditText input;
    OnClick onClick;
    private String definition;
    private TextView qingxidu;
    private String videoid, name, src;
    private NumberProgressBar myNumber;
    private ACache aCache;
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Dlg_VideoDownload(Context context, String videoid1, String name1, String src1) {
        super(context);
        videoid = videoid1;
        name = name1;
        src = src1;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.layout_bottom_download_video;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
    }

    String qixidu = "";

    @Override
    protected void initView() {
        aCache = ACache.get(getContext());
        setOnClickListener(R.id.downlod_shudown);
        qingxidu = findViewById(R.id.qingxidu);
        myNumber = findViewById(R.id.myNumber);
        switch (getDefinition()) {
            case "1":
                qixidu = "标清";
                break;
            case "2":
                qixidu = "高清";
                break;
            case "3":
                qixidu = "超清";
                break;
            case "4":
                qixidu = "蓝光";
                break;
        }
        qingxidu.setText(qixidu);


    }

    @Override
    protected void initData() {




//        onDownloadFile();
    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.downlod_shudown:
                dismiss();
                break;
        }
    }

    public interface OnClick {
        void onItem(String p);
    }

    public void onDownloadFile() {
        String url = ApiConstant.ROOT_URL + ApiConstant.Home_videodownloadvideo;
        Log.e("aa", "---------" + url);
        EasyHttp.downLoad(url)
                .params("videoid", videoid)
                .params("videodefinition_definition", getDefinition())
                .params("path", Environment.getExternalStorageDirectory().getPath() + "/liuzhi/video")
                .params("downloadfilename", TimeUtils.getTime())
                .savePath(Environment.getExternalStorageDirectory().getPath() + "/liuzhi/video")
                .saveName( TimeUtils.getTime()+".mp4")
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        int progress = (int) (bytesRead * 100 / contentLength);
                        HttpLog.e(progress + "% ");
                        myNumber.setProgress(progress);
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onComplete(String path) {

                        Log.e("aa", "----" + "文件保存路径：" + path);
                    }

                    @Override
                    public void onError(ApiException e) {
                        Log.e("aa", "----onError");
                    }
                });
    }
}
