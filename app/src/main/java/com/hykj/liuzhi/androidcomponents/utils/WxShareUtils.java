package com.hykj.liuzhi.androidcomponents.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.hykj.liuzhi.R;
import com.luck.picture.lib.tools.Constant;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;

import okhttp3.internal.platform.Platform;

import static com.zhouyou.http.EasyHttp.getContext;

public class WxShareUtils {
    /**
     * 分享网页类型至微信
     *
     * @param context 上下文
     * @param appId   微信的appId
     * @param webUrl  网页的url
     * @param title   网页标题
     * @param content 网页描述
     * @param bitmap  位图
     */
    public static void shareWeb(int type, Context context, String appId, String webUrl, String title, String content, Bitmap bitmap) {
        // 通过appId得到IWXAPI这个对象
        IWXAPI wxapi = WXAPIFactory.createWXAPI(context, appId);
        // 检查手机或者模拟器是否安装了微信
        if (!wxapi.isWXAppInstalled()) {
            Toast.makeText(getContext(), "您还没有安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        // 初始化一个WXWebpageObject对象
        WXWebpageObject webpageObject = new WXWebpageObject();
        // 填写网页的url
        webpageObject.webpageUrl = webUrl;
        // 用WXWebpageObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        // 填写网页标题、描述、位图
        msg.title = title;
        msg.description = content;
        // 如果没有位图，可以传null，会显示默认的图片
        msg.setThumbImage(bitmap);
        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // transaction用于唯一标识一个请求（可自定义）
        req.transaction = "webpage";
        // 上文的WXMediaMessage对象
        req.message = msg;
        // SendMessageToWX.Req.WXSceneSession是分享到好友会话
        // SendMessageToWX.Req.WXSceneTimeline是分享到朋友圈
        if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        }
        // 向微信发送请求
        wxapi.sendReq(req);
    }

    //    private void shareToWeibo() {
//        WebpageObject mediaObj =newWebpageObject();
//        //创建文本消息对象
//        TextObject textObject =newTextObject();
//        textObject.text= "你分享内容的描述"+“分享网页的话加上网络地址”;
//        textObject.title= getTitle();
//        //创建图片消息对象，如果只分享文字和网页就不用加图片
//        WeiboMultiMessage message =newWeiboMultiMessage();
//        ImageObject imageObject =newImageObject();
//        // 设置 Bitmap 类型的图片到视频对象里        设置缩略图。 注意：最终压缩过的缩略图大小 不得超过 32kb。
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources() , R.drawable.test);
//        imageObject.setImageObject(bitmap);
//        message.textObject= textObject;
//        message.imageObject= imageObject;
//        message.mediaObject= mediaObj;
//        shareHandler.shareMessage(message,false);
//    }
    public static WbShareHandler shareHandler;
    public static void showShare(Activity context, String title, String text, String actionUrl) {
        WbSdk.install(context, new AuthInfo(context, "3260814950", REDIRECT_URL, SCOPE));//创建微博API接口类对象
        shareHandler = new WbShareHandler(context);
        shareHandler.registerApp();
        sendMultiMessage(true, true, context, title, text, actionUrl);
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面实现方法。
     */
    private static void sendMultiMessage(boolean hasText, boolean hasImage, Activity context, String title, String text, String actionUrl) {
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        if (hasText) {
            weiboMessage.textObject = getTextObj(title, text, actionUrl);
        }
        if (hasImage) {
            weiboMessage.imageObject = getImageObj(context);
        }
        shareHandler.shareMessage(weiboMessage, false);
    }

    /**
     * WeiboSDKDemo 应用对应的权限，第三方开发者一般不需要这么多，可直接设置成空即可。
     * 详情请查看 Demo 中对应的注释。
     */
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
    public static final String REDIRECT_URL = "http://www.sina.com";

    /**
     * 创建文本消息对象。
     *
     * @return 文本消息对象。
     */
    private static TextObject getTextObj(String title, String text, String actionUrl) {
        TextObject textObject = new TextObject();
        textObject.text = text;
        textObject.title = title;
        textObject.actionUrl = actionUrl;
        return textObject;
    }

    /**
     * 创建图片消息对象。
     *
     * @return 图片消息对象。
     */
    private static ImageObject getImageObj(Context context) {
        ImageObject imageObject = new ImageObject();
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        imageObject.setImageObject(bitmap);
        return imageObject;
    }
}
