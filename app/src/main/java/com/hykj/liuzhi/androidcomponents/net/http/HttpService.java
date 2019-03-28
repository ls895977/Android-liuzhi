package com.hykj.liuzhi.androidcomponents.net.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface HttpService {
    @FormUrlEncoded
    @POST(ApiConstant.LOGIN)
    Observable<String> login(@FieldMap Map<String, String> map);

    //用户密码登录
    @FormUrlEncoded
    @POST(ApiConstant.PhonePassWordLoGin)
    Observable<String> PhonePassWordLoGin(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(ApiConstant.REGISTER)
    Observable<String> register(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(ApiConstant.GETUSERCOLLECTION)
    Observable<String> getusercollection(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(ApiConstant.GETUSERSELF)
    Observable<String> getUserself(@FieldMap Map<String, String> map);

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST(ApiConstant.forgetpassword)
    Observable<String> forgetpassword(@FieldMap Map<String, String> map);

    /**
     * 发送手机验证码
     */
    @FormUrlEncoded
    @POST(ApiConstant.phonecode)
    Observable<String> phonecode(@FieldMap Map<String, String> map);

    /**
     * 系统应用导航栏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_appmodel)
    Observable<String> getHome_appmodel(@FieldMap Map<String, String> map);
    /**
     * 获取用户粉丝
     */
    @FormUrlEncoded
    @POST(ApiConstant.USER_FAN)
    Observable<String> getUserFan(@FieldMap Map<String, String> map);
    /**
     * 用户点击按钮 关注
     */
    @FormUrlEncoded
    @POST(ApiConstant.USER_CLICK_ATTENTION)
    Observable<String> getUserClickAttention(@FieldMap Map<String, String> map);

    /**
     * 用户点击按钮 取消 关注
     */
    @FormUrlEncoded
    @POST(ApiConstant.USER_CLICK_Usernotfans)
    Observable<String> getUsernotfans(@FieldMap Map<String, String> map);

    /**
     * 首页=消息详情
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_systemnotificationdetail)
    Observable<String> getSystemnotificationdetail(@FieldMap Map<String, String> map);
    /**
     * 首页=模块数据
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_modeltodata)
    Observable<String> Home_modeltodata(@FieldMap Map<String, String> map);
    /**
     * 首页=平台通知
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_showsystemnotification)
    Observable<String> Home_showsystemnotification(@FieldMap Map<String, String> map);

    /**
     * 首页=用户消息
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_authorgetmessage)
    Observable<String> Home_authorgetmessage(@FieldMap Map<String, String> map);

    /**
     * 首页=推荐
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_Firstpagedata)
    Observable<String> getFirstpagedata(@FieldMap Map<String, String> map);

    /**
     * 首页=纹理
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_Firstpagedatatexture)
    Observable<String> getFirstpagedatatexture(@FieldMap Map<String, String> map);

    /**
     * 首页=潮流
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_Firstpagedatatrend)
    Observable<String> getFirstpagedatatrend(@FieldMap Map<String, String> map);

    /**
     * 首页=搜索历史
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_Userselecthistory)
    Observable<String> getUserselecthistory(@FieldMap Map<String, String> map);

    /**
     * 首页=前台搜索历史
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_selecthistory)
    Observable<String> Home_selecthistory(@FieldMap Map<String, String> map);

    /**
     * 首页=搜索
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_Userselect)
    Observable<String> getUserselect(@FieldMap Map<String, String> map);

    /**
     * 首页=签到
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_SignIn)
    Observable<String> getSignIn(@FieldMap Map<String, String> map);

    /**
     * 首页=交易信息
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_information)
    Observable<String> getInformation(@FieldMap Map<String, String> map);

    /**
     * 首页=其他用户主页 头部信息
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_userfirstpagetitle)
    Observable<String> getUserfirstpagetitle(@FieldMap Map<String, String> map);

    /**
     * 首页=签名
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_Changeautograph)
    Observable<String> getChangeautograph(@FieldMap Map<String, String> map);

    /**
     * 首页=昵称
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_Changenickname)
    Observable<String> getChangenickname(@FieldMap Map<String, String> map);

    /**
     * 我的=邮箱
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_ChangEmail)
    Observable<String> getChangEmail(@FieldMap Map<String, String> map);

    /**
     * 我的=更换头像
     */
    @Multipart
    @POST(ApiConstant.Min_ChangeUserPic)
    Observable<String> getChangeUserPic(@Part MultipartBody.Part files);

    /**
     * 我的=收藏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_UserCollection)
    Observable<String> getUserCollection(@FieldMap Map<String, String> map);

    /**
     * 我的=修改密码
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_ChangePassword)
    Observable<String> getChangePassword(@FieldMap Map<String, String> map);

    /**
     * 我的=登录记录
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_logonrecord)
    Observable<String> getlogonrecord(@FieldMap Map<String, String> map);

    /**
     * 我的=用户所有的收货地址
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_getUserAddress)
    Observable<String> getUserAddress(@FieldMap Map<String, String> map);

    /**
     * 我的=添加收货地址
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_addshopaddress)
    Observable<String> getAddshopaddress(@FieldMap Map<String, String> map);

    /**
     * 我的=删除收货地址
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_deleteaddress)
    Observable<String> deleteaddress(@FieldMap Map<String, String> map);

    /**
     * 我的=修改默认地址
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_changeadderssstatus)
    Observable<String> changeadderssstatus(@FieldMap Map<String, String> map);

    /**
     * 我的=修改收货地址
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_modifyaddress)
    Observable<String> Min_modifyaddress(@FieldMap Map<String, String> map);

    /**
     * 我的=我的订单=订单取消
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_cancellationOfOrder)
    Observable<String> CancellationOfOrder(@FieldMap Map<String, String> map);

    /**
     * 我的=投诉意见
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_addproposal)
    Observable<String> Min_addproposal(@FieldMap Map<String, String> map);

    /**
     * 我的=省
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_getprovinces)
    Observable<String> getprovinces(@FieldMap Map<String, String> map);

    /**
     * 我的=市
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_getcitys)
    Observable<String> getcitys(@FieldMap Map<String, String> map);

    /**
     * 我的=区
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_getareas)
    Observable<String> getareas(@FieldMap Map<String, String> map);

    /**
     * 我的=获取所有标签
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_getlabels)
    Observable<String> getlabels(@FieldMap Map<String, String> map);

    /**
     * 我的=修改标签
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_changelabel)
    Observable<String> changelabel(@FieldMap Map<String, String> map);

    /**
     * 我的=修改性别
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_changeusersex)
    Observable<String> changeusersex(@FieldMap Map<String, String> map);

    /**
     * 我的=修改出生日期
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_changeuserbarth)
    Observable<String> Min_changeuserbarth(@FieldMap Map<String, String> map);

    /**
     * 我的=查看物流
     */
    @FormUrlEncoded
    @POST(ApiConstant.Min_viewLogistics)
    Observable<String> Min_viewLogistics(@FieldMap Map<String, String> map);

    /**
     * 商品=获取轮播图片
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_Getsowing)
    Observable<String> Getsowing(@FieldMap Map<String, String> map);

    /**
     * 商品=商品展示
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_Goodsfirstpage)
    Observable<String> Goodsfirstpage(@FieldMap Map<String, String> map);

    /**
     * 商品=查看商品
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_showgoods)
    Observable<String> showgoods(@FieldMap Map<String, String> map);

    /**
     * 商品=商品收藏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_goodscollection)
    Observable<String> goodscollection(@FieldMap Map<String, String> map);

    /**
     * 商品=商品取消收藏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_goodsnotcollection)
    Observable<String> goodsnotcollection(@FieldMap Map<String, String> map);

    /**
     * 商品=添加购物车
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_addshopcar)
    Observable<String> addshopcar(@FieldMap Map<String, String> map);

    /**
     * 商品=查看购物车
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_showshopcar)
    Observable<String> showshopcar(@FieldMap Map<String, String> map);

    /**
     * 商品=删除购物车
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_deleteShopCar)
    Observable<String> deleteShopCar(@FieldMap Map<String, String> map);

    /**
     * 商品=添加订单
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_addorders)
    Observable<String> addorders(@FieldMap Map<String, String> map);

    /**
     * 商品=查看用户订单
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_userorders)
    Observable<String> userorders(@FieldMap Map<String, String> map);

    /**
     * 商品=确认订单页面中间的商品数据
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_intermediatedata)
    Observable<String> Shop_intermediatedata(@FieldMap Map<String, String> map);

    /**
     * 商品=用户商品搜索历史
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_usergoodsselecthistory)
    Observable<String> usergoodsselecthistory(@FieldMap Map<String, String> map);

    /**
     * 商品=商品搜索历史
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_goodsselecthistory)
    Observable<String> goodsselecthistory(@FieldMap Map<String, String> map);

    /**
     * 商=添加修改购物车数量
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_changeshopcar)
    Observable<String> Shop_changeshopcar(@FieldMap Map<String, String> map);

    /**
     * 商品=商品查询
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_selectgoods)
    Observable<String> selectgoods(@FieldMap Map<String, String> map);

    /**
     * 商品=获取所有商品类别
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_getgoodscates)
    Observable<String> getgoodscates(@FieldMap Map<String, String> map);

    /**
     * 商=确认订单
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_confirmationOfOrder)
    Observable<String> Shop_confirmationOfOrder(@FieldMap Map<String, String> map);

    /**
     * 商=删除订单
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_deleteorders)
    Observable<String> Shop_deleteorders(@FieldMap Map<String, String> map);

    /**
     * 删除搜索记录 （首页）
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_deleteselecthistory)
    Observable<String> Shop_deleteselecthistory(@FieldMap Map<String, String> map);

    /**
     * 删除搜索记录 （商城）
     */
    @FormUrlEncoded
    @POST(ApiConstant.Shop_clearuserselectgoodshistory)
    Observable<String> Shop_clearuserselectgoodshistory(@FieldMap Map<String, String> map);

    /**
     * 视频=查看视频
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_videoshow)
    Observable<String> videoshow(@FieldMap Map<String, String> map);

    /**
     * 视频=视频收藏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_videocollection)
    Observable<String> videocollection(@FieldMap Map<String, String> map);

    /**
     * 视频=视频取消收藏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_videonotcollection)
    Observable<String> videonotcollection(@FieldMap Map<String, String> map);

    /**
     * 视频=视频取消赞
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_videoisnotpoint)
    Observable<String> videoisnotpoint(@FieldMap Map<String, String> map);


    /**
     * 视频=视频点赞
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_videoispoint)
    Observable<String> videoispoint(@FieldMap Map<String, String> map);

    /**
     * 视频=视频列表
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_videolist)
    Observable<String> videolist(@FieldMap Map<String, String> map);

    /**
     * 视频=视频评论
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_videomessage)
    Observable<String> videomessage(@FieldMap Map<String, String> map);

    /**
     * 视频=视频评论回复
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_videomessagereply)
    Observable<String> videomessagereply(@FieldMap Map<String, String> map);

    /**
     * 视频=获取所有的视频评论
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_videomessageall)
    Observable<String> videomessageall(@FieldMap Map<String, String> map);

    /**
     * 视频=视频打赏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Home_videoreward)
    Observable<String> videoreward(@FieldMap Map<String, String> map);

    /**
     * 图文=图文展示
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_imagetextfirstpage)
    Observable<String> imagetextfirstpage(@FieldMap Map<String, String> map);

    /**
     * 图文=作者查看评论详情
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_showreaderreview)
    Observable<String> showreaderreview(@FieldMap Map<String, String> map);

    /**
     * 图文=查看作者图文
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_showimagetexttoauthor)
    Observable<String> showimagetexttoauthor(@FieldMap Map<String, String> map);

    /**
     * 图文=查看图文
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_imagetextpage)
    Observable<String> imagetextpage(@FieldMap Map<String, String> map);

    /**
     * 图文=图文点赞
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_imagetextispoint)
    Observable<String> imagetextispoint(@FieldMap Map<String, String> map);

    /**
     * 图文=图文取消赞
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_imagetextisnotpoint)
    Observable<String> imagetextisnotpoint(@FieldMap Map<String, String> map);

    /**
     * 图文=图文收藏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_imagetextcollection)
    Observable<String> imagetextcollection(@FieldMap Map<String, String> map);

    /**
     * 图文=图文取消收藏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_imagetextnotcollection)
    Observable<String> imagetextnotcollection(@FieldMap Map<String, String> map);

    /**
     * 图文=图文打赏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_imagetextreward)
    Observable<String> imagetextreward(@FieldMap Map<String, String> map);

    /**
     * 图文=评论
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_imagetextmessage)
    Observable<String> Cirde_imagetextmessage(@FieldMap Map<String, String> map);

    /**
     * 图文=同类图文
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_getImagetextTotype)
    Observable<String> Cirde_getImagetextTotype(@FieldMap Map<String, String> map);

    /**
     * 图文=评论列表
     */
    @FormUrlEncoded
    @POST(ApiConstant.Cirde_imagetextmessageall)
    Observable<String> Cirde_imagetextmessageall(@FieldMap Map<String, String> map);

    /**
     * 图文=查看软文
     */
    @FormUrlEncoded
    @POST(ApiConstant.Advertorial_softtextfirstpage)
    Observable<String> Advertorial_softtextfirstpage(@FieldMap Map<String, String> map);

    /**
     * 图文=软文点赞
     */
    @FormUrlEncoded
    @POST(ApiConstant.Advertorial_softtextispoint)
    Observable<String> Advertorial_softtextispoint(@FieldMap Map<String, String> map);

    /**
     * 图文=软文取消赞
     */
    @FormUrlEncoded
    @POST(ApiConstant.Advertorial_softtextisnotpoint)
    Observable<String> Advertorial_softtextisnotpoint(@FieldMap Map<String, String> map);

    /**
     * 图文=软文收藏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Advertorial_softtextcollection)
    Observable<String> Advertorial_softtextcollection(@FieldMap Map<String, String> map);

    /**
     * 图文=软文取消收藏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Advertorial_softtextnotcollection)
    Observable<String> Advertorial_softtextnotcollection(@FieldMap Map<String, String> map);

    /**
     * 图文=软文评论
     */
    @FormUrlEncoded
    @POST(ApiConstant.Advertorial_softtextmessage)
    Observable<String> Advertorial_softtextmessage(@FieldMap Map<String, String> map);

    /**
     * 图文=软文评论信息
     */
    @FormUrlEncoded
    @POST(ApiConstant.Advertorial_softtextmessageall)
    Observable<String> Advertorial_softtextmessageall(@FieldMap Map<String, String> map);

    /**
     * 图文=软文打赏
     */
    @FormUrlEncoded
    @POST(ApiConstant.Advertorial_softtextreward)
    Observable<String> Advertorial_softtextreward(@FieldMap Map<String, String> map);

    /**
     * 图文=软文浏览记录
     */
    @FormUrlEncoded
    @POST(ApiConstant.Advertorial_softtextborwse)
    Observable<String> Advertorial_softtextborwse(@FieldMap Map<String, String> map);

    /**
     * 图文=标签选择
     */
    @FormUrlEncoded
    @POST(ApiConstant.Advertorial_getimagetextlabels)
    Observable<String> Advertorial_getimagetextlabels(@FieldMap Map<String, String> map);

    /**
     * 图文=获取举报类型
     */
    @FormUrlEncoded
    @POST(ApiConstant.getreportreason)
    Observable<String> getreportreason(@FieldMap Map<String, String> map);

    /**
     * 用户发过的所有软文
     */
    @FormUrlEncoded
    @POST(ApiConstant.usersofttexts)
    Observable<String> usersofttexts(@FieldMap Map<String, String> map);

    /**
     * 订单详情
     */
    @FormUrlEncoded
    @POST(ApiConstant.showorders1)
    Observable<String> showorders(@FieldMap Map<String, String> map);

    /**
     * 用户浏览记录
     */
    @FormUrlEncoded
    @POST(ApiConstant.userbrowses)
    Observable<String> userbrowses(@FieldMap Map<String, String> map);

    /**
     * 图文浏览记录添加
     */
    @FormUrlEncoded
    @POST(ApiConstant.imagetextbrowses)
    Observable<String> imagetextbrowses(@FieldMap Map<String, String> map);

    /**
     * 软文浏览记录添加
     */
    @FormUrlEncoded
    @POST(ApiConstant.softtextborwses)
    Observable<String> softtextborwses(@FieldMap Map<String, String> map);

    /**
     * 订单支付
     */
    @FormUrlEncoded
    @POST(ApiConstant.payOrders)
    Observable<String> payOrders(@FieldMap Map<String, String> map);

    /**
     * 分享视频软文
     */
    @FormUrlEncoded
    @POST(ApiConstant.partakeofvideosofttext)
    Observable<String> partakeofvideosofttext(@FieldMap Map<String, String> map);
}