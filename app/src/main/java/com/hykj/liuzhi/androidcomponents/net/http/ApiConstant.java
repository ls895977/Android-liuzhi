package com.hykj.liuzhi.androidcomponents.net.http;

public class ApiConstant {
    //正式
    public static final String ROOT_URL = "http://liuzhi.365hy.com";
    //用户登录
    public static final String LOGIN = "/api/index/login";
    //用户密码登录
    public static final String PhonePassWordLoGin = "/api/index/phonepasswordlogin";
    //用户注册
    public static final String REGISTER = "/api/index/register";
    //获取用户信息
    public static final String GETUSERSELF = "/api/index/getuserself";
    //发送手机验证码
    public static final String forgetpassword = "/api/index/forgetpassword";
    //忘记密码
    public static final String phonecode = "/api/index/phonecode";
    //用户关注的人
    public static final String GETUSERCOLLECTION = "/api/index/getusercollection";
    //用户的粉丝
    public static final String USER_FAN = "/api/index/getuserfanse";
    //用户点击按钮 关注
    public static final String USER_CLICK_ATTENTION = "/api/index/userfans";
    //用户点击按钮 取消关注
    public static final String USER_CLICK_Usernotfans = "/api/index/usernotfans";
    //首页=消息详情接口
    public static final String Home_systemnotificationdetail = "/api/index/systemnotificationdetail";
    //首页=模块数据
    public static final String Home_modeltodata="/api/index/modeltodata";
    //首页=系统应用导航栏
    public static final String Home_appmodel = "/api/index/appmodel";
    //首页=平台通知
    public static final String Home_showsystemnotification = "/api/index/showsystemnotification";
    //首页=用户消息
    public static final String Home_authorgetmessage = "/api/index/authorgetmessage";
    //首页=推荐
    public static final String Home_Firstpagedata = "/api/index/firstpagedata";
    //首页=纹理
    public static final String Home_Firstpagedatatexture = "/api/index/firstpagedatatexture";
    //首页=潮流
    public static final String Home_Firstpagedatatrend = "/api/index/firstpagedatatrend";
    //首页=搜索历史
    public static final String Home_Userselecthistory = "/api/index/userselecthistory";
    //首页=前台搜索历史
    public static final String Home_selecthistory = "/api/index/selecthistory";
    //首页=搜索
    public static final String Home_Userselect = "/api/index/userselect";
    //首页=签到
    public static final String Home_SignIn = "/api/index/signin";
    //首页=交易信息
    public static final String Home_information = "/api/index/information";
    //首页=其他用户主页 头部信息
    public static final String Home_userfirstpagetitle = "/api/index/userfirstpagetitle";
    //我的=签名
    public static final String Min_Changeautograph = "/api/index/changeautograph";
    //我的=昵称
    public static final String Min_Changenickname = "/api/index/changenickname";
    //我的=邮箱
    public static final String Min_ChangEmail = "/api/index/changemail";
    //我的=上传头像
    public static final String Min_ChangeUserPic = "/api/index/changeuserpic";
    //我的=收藏
    public static final String Min_UserCollection = "/api/index/usercollection";
    //我的=更改密码
    public static final String Min_ChangePassword = "/api/index/changepassword";
    //我的=实名认证
    public static final String Min_userfileuploads = "/api/index/userfileuploads";
    //我的=登录记录
    public static final String Min_logonrecord = "/api/index/logonrecord";
    //我的=用户所有的收货地址
    public static final String Min_getUserAddress = "/api/index/getuseraddress";
    //我的=添加收货地址
    public static final String Min_addshopaddress = "/api/index/addshopaddress";
    //我的=删除收货地址
    public static final String Min_deleteaddress = "/api/index/deleteaddress";
    //我的=修改默认地址
    public static final String Min_changeadderssstatus = "/api/index/changeadderssstatus";
    //我的=修改收货地址
    public static final String Min_modifyaddress = "/api/index/modifyaddress";
    //我的=我的订单=订单取消
    public static final String Min_cancellationOfOrder = "/api/index/cancellationOfOrder";
    //我的=投诉意见
    public static final String Min_addproposal = "/api/index/addproposal";
    //我的=省
    public static final String Min_getprovinces = "/api/index/getprovinces";
    //我的=市
    public static final String Min_getcitys = "/api/index/getcitys";
    //我的=区
    public static final String Min_getareas = "/api/index/getareas";
    //我的=获取所有标签
    public static final String Min_getlabels = "/api/index/getlabels";
    //我的=修改标签
    public static final String Min_changelabel = "/api/index/changelabel";
    //我的=修改性别
    public static final String Min_changeusersex = "/api/index/changeusersex";
    //我的=修改出生日期
    public static final String Min_changeuserbarth = "/api/index/changeuserbarth";
    //我的=查看物流
    public static final String Min_viewLogistics = "/api/index/viewLogistics";
    //图文(圈)=图文添加
    public static final String Circle_AddImageText = "/api/index/addimagetext";
    //商=获取轮播图片
    public static final String Shop_Getsowing = "/api/index/getsowing";
    //商=商品展示
    public static final String Shop_Goodsfirstpage = "/api/index/goodsfirstpage";
    //商=商品详情
    public static final String Shop_showgoods = "/api/index/showgoods";
    //商=商品收藏
    public static final String Shop_goodscollection = "/api/index/goodscollection";
    //商=商品取消收藏
    public static final String Shop_goodsnotcollection = "/api/index/goodsnotcollection";
    //商=添加购物车
    public static final String Shop_addshopcar = "/api/index/addshopcar";
    //商=查看购物车
    public static final String Shop_showshopcar = "/api/index/showshopcar";
    //商=删除购物车
    public static final String Shop_deleteShopCar = "/api/index/deleteShopCar";
    //商=添加订单
    public static final String Shop_addorders = "/api/index/addorders";
    //商=查看用户订单
    public static final String Shop_userorders = "/api/index/userorders";
    //商=确认订单页面中间的商品数据
    public static final String Shop_intermediatedata = "/api/index/intermediatedata";
    //商=用户商品搜索历史
    public static final String Shop_usergoodsselecthistory = "/api/index/usergoodsselecthistory";
    //商=商品搜素历史
    public static final String Shop_goodsselecthistory = "/api/index/goodsselecthistory";
    //商=商品查询
    public static final String Shop_selectgoods = "/api/index/selectgoods";
    //商=获取所有商品类别
    public static final String Shop_getgoodscates = "/api/index/getgoodscates";
    //商=添加修改购物车数量
    public static final String Shop_changeshopcar = "/api/index/changeshopcar";
    //商=确认订单
    public static final String Shop_confirmationOfOrder = "/api/index/confirmationOfOrder";
    //商=删除订单
    public static final String Shop_deleteorders = "/api/index/deleteorders";
    //删除搜索记录 （首页）
    public static final String Shop_deleteselecthistory = "/api/index/deleteselecthistory";
    //删除搜索记录 （商城）
    public static final String Shop_clearuserselectgoodshistory = "/api/index/clearuserselectgoodshistory";
    //视频=查看视频
    public static final String Home_videoshow = "/api/index/videoshow";
    //视频=视频收藏
    public static final String Home_videocollection = "/api/index/videocollection";
    //视频=视频取消收藏
    public static final String Home_videonotcollection = "/api/index/videonotcollection";
    //视频=视频取消赞
    public static final String Home_videoisnotpoint = "/api/index/videoisnotpoint";
    //视频=视频点赞
    public static final String Home_videoispoint = "/api/index/videoispoint";
    //视频=视频列表
    public static final String Home_videolist = "/api/index/videolist";
    //视频=视频评论
    public static final String Home_videomessage = "/api/index/videomessage";
    //视频=视频评论回复
    public static final String Home_videomessagereply = "/api/index/messagereply";
    //视频=视频下载
    public static final String Home_videodownloadvideo = "/api/index/downloadvideo";
    //视频=获取所有的视频评论
    public static final String Home_videomessageall = "/api/index/videomessageall";
    //视频=视频打赏
    public static final String Home_videoreward = "/api/index/videoreward";
    //图文=图文展示
    public static final String Cirde_imagetextfirstpage = "/api/index/imagetextfirstpage";
    //图文=作者查看评论详情
    public static final String Cirde_showreaderreview = "/api/index/showreaderreview";
    //图文=查看作者图文
    public static final String Cirde_showimagetexttoauthor = "/api/index/showimagetexttoauthor";
    //图文=查看图文
    public static final String Cirde_imagetextpage = "/api/index/imagetextpage";
    //图文=图文点赞
    public static final String Cirde_imagetextispoint = "/api/index/imagetextispoint";
    //图文=图文取消赞
    public static final String Cirde_imagetextisnotpoint = "/api/index/imagetextisnotpoint";
    //图文=图文收藏
    public static final String Cirde_imagetextcollection = "/api/index/imagetextcollection";
    //图文=图文取消收藏
    public static final String Cirde_imagetextnotcollection = "/api/index/imagetextnotcollection";
    //图文=图文打赏
    public static final String Cirde_imagetextreward = "/api/index/imagetextreward";
    //图文=评论
    public static final String Cirde_imagetextmessage = "/api/index/imagetextmessage";
    //图文=同类图文
    public static final String Cirde_getImagetextTotype = "/api/index/getImagetextTotype";
    //软文=评论列表
    public static final String Cirde_imagetextmessageall = "/api/index/imagetextmessageall";
    //软文=查看软文
    public static final String Advertorial_softtextfirstpage = "/api/index/softtextfirstpage";
    //软文=软文点赞
    public static final String Advertorial_softtextispoint = "/api/index/softtextispoint";
    //软文=软文取消赞
    public static final String Advertorial_softtextisnotpoint = "/api/index/softtextisnotpoint";
    //软文=软文收藏
    public static final String Advertorial_softtextcollection = "/api/index/softtextcollection";
    //软文=软文取消收藏
    public static final String Advertorial_softtextnotcollection = "/api/index/softtextnotcollection";
    //软文=软文评论
    public static final String Advertorial_softtextmessage = "/api/index/softtextmessage";
    //软文=软文评论信息
    public static final String Advertorial_softtextmessageall = "/api/index/softtextmessageall";
    //软文=软文打赏
    public static final String Advertorial_softtextreward = "/api/index/softtextreward";
    //软文=软文浏览记录
    public static final String Advertorial_softtextborwse = "/api/index/softtextborwse";
    //软文=软文添加
    public static final String Advertorial_addsofttext = "/api/index/addsofttext";
    //软文=标签选择
    public static final String Advertorial_getimagetextlabels = "api/index/getimagetextlabels";
    //举报=获取举报类型
    public static final String getreportreason = "/api/index/getreportreason";
    //举报=用户举报
    public static final String doreport = "/api/index/doreport";
    //用户发过的所有软文
    public static final String usersofttexts = "/api/index/usersofttexts";
    //用户浏览记录
    public static final String userbrowses = "/api/index/userbrowses";
    //订单详情
    public static final String showorders1 = "/api/index/showorders";
    //图文浏览记录添加
    public static final String imagetextbrowses = "/api/index/imagetextbrowses";
    //软文浏览记录添加
    public static final String softtextborwses = "/api/index/softtextborwses";
    //订单支付
    public static final String payOrders = "/api/index/payOrders";
    //分享视频软文
    public static final String partakeofvideosofttext = "/api/index/partakeofvideosofttext";
}
