package com.hykj.liuzhi.androidcomponents.net.http;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.hykj.liuzhi.androidcomponents.bean.AddAddressBean;
import com.hykj.liuzhi.androidcomponents.bean.AddCodeBean;
import com.hykj.liuzhi.androidcomponents.bean.AddContextBean;
import com.hykj.liuzhi.androidcomponents.bean.AliiTabBean;
import com.hykj.liuzhi.androidcomponents.bean.AppPayBean;
import com.hykj.liuzhi.androidcomponents.bean.CartBean;
import com.hykj.liuzhi.androidcomponents.bean.ChangeshopcarBean;
import com.hykj.liuzhi.androidcomponents.bean.ConfirmOrderBean;
import com.hykj.liuzhi.androidcomponents.bean.ConfirmationOfOrderBean;
import com.hykj.liuzhi.androidcomponents.bean.DeleteSelecthisteryBean;
import com.hykj.liuzhi.androidcomponents.bean.DetailCommetListBean;
import com.hykj.liuzhi.androidcomponents.bean.ForgetpasswordBean;
import com.hykj.liuzhi.androidcomponents.bean.GetgoodScatesBean;
import com.hykj.liuzhi.androidcomponents.bean.GetreporTreasonBean;
import com.hykj.liuzhi.androidcomponents.bean.InForMationBean;
import com.hykj.liuzhi.androidcomponents.bean.LoginEntity;
import com.hykj.liuzhi.androidcomponents.bean.MineFileBean;
import com.hykj.liuzhi.androidcomponents.bean.ShearBean;
import com.hykj.liuzhi.androidcomponents.bean.Shop_intermediatedataBean;
import com.hykj.liuzhi.androidcomponents.bean.SignInBean;
import com.hykj.liuzhi.androidcomponents.bean.TabBean;
import com.hykj.liuzhi.androidcomponents.bean.VideomessageBean;
import com.hykj.liuzhi.androidcomponents.bean.addproposalBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.circle.CircleDetailBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.circle.DetailCircleImageListBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.circle.DetailCirclelmageBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.ChangePasswordBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.bean.AllAddBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.bean.LoGinRecordBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.softtext.SofttextFirstPageBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.softtext.ben.GetimagetextlabelsBean;
import com.hykj.liuzhi.androidcomponents.ui.activity.video.bean.VideoPointBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.circle.bean.CircleFragmentBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.detail.bean.GetImageTextBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.detail.bean.VideoDetailBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean.NotifyFragmentBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.message.bean.SystemNotiFicationdetailBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.MyOrderTabDetailsBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserAdvertorialBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.UserordersBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.WatchHistoryBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.mine.bean.WatchHistoryBean1;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.CollectionBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.GetsowingBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.collect.bean.CollectBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FirstpagedataBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.VideoContextBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.GoodDetailDetailBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopHomeBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.ShopSeacharBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.bean.addshopcarBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.utils.permission.Debug;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HttpHelper {
    public static void login(String phone, String code, final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", code);
        httpService.login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed);
                        LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }

                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public static void PhonePassWordLoGin(String phone, String password, final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        httpService.PhonePassWordLoGin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Debug.e("----------" + succeed);
                        Logger.t("HttpHelper").i(succeed);
                        LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }

                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Debug.e("----------" + e.getMessage());
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public static void register(String phone, String code, String password, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", code);
        map.put("password", password);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed);
                        LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 用户关注的人
     *
     * @param page
     * @param number
     * @param callBack
     */
    public static void getusercollection(int page, int number, int user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("number", number + "");
        map.put("user_id", user_id + "");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getusercollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed);
                        LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {

                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

//        HttpManager.post(ApiConstant.GETUSERCOLLECTION)
//                .params("page",page + "")
//                .params("number",number + "")
//                .execute(new SimpleCallBack<GetuserfanseBean>() {
//                    @Override
//                    public void onError(ApiException e) {
//                        Logger.t("MainActivity").i(e.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(GetuserfanseBean bannerBean) {
//                        Logger.t("MainActivity").i(bannerBean.toString());
//                    }
//                });
    }

    //修改密码
    public static void forgetpassword(String phone, String password, final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone + "");
        map.put("password", password + "");
        httpService.forgetpassword(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed);
                        ForgetpasswordBean entity = FastJSONHelper.getPerson(succeed, ForgetpasswordBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    //发送手机验证码
    public static void phonecode(String phone, String type, final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone + "");
        map.put("type", type + "");
        httpService.phonecode(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed);
                        ForgetpasswordBean entity = FastJSONHelper.getPerson(succeed, ForgetpasswordBean.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 获取用户信息
     */
    public static void getUserself(int user_id, final HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id + "");
        httpService.getUserself(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Debug.e("-----------succeed--" + succeed);
                        Logger.t("HttpHelper").i(succeed);
                        LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                        if (entity.getCode() == 0) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 用户粉丝
     *
     * @param page
     * @param number
     * @param callBack
     */
    public static void getUserFan(int user_id, int page, int number, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("number", number + "");
        map.put("user_id", user_id + "");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getUserFan(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed);
                        LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 用户点击关注
     *
     * @param callBack
     */
    public static void getUserClickAttention(String click_id, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("coll", click_id + "");
        map.put("user_id", user_id + "");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getUserClickAttention(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed.toString());
                        LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getMsg()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 用户点击取消关注
     *
     * @param callBack
     */
    public static void getUsernotfans(String click_id, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("coll", click_id + "");
        map.put("user_id", user_id + "");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getUsernotfans(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed.toString());
                        LoginEntity entity = FastJSONHelper.getPerson(succeed, LoginEntity.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 首页=消息
     *
     * @param callBack
     */
    public static void getSystemnotificationdetail(String systemnotificationid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("systemnotificationid", systemnotificationid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getSystemnotificationdetail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed.toString());
                        SystemNotiFicationdetailBean entity = FastJSONHelper.getPerson(succeed, SystemNotiFicationdetailBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 首页=平台通知
     *
     * @param callBack
     */
    public static void Home_showsystemnotification(String user_id, String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("page", page);
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Home_showsystemnotification(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed.toString());
                        NotifyFragmentBean entity = FastJSONHelper.getPerson(succeed, NotifyFragmentBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 首页=用户消息
     *
     * @param callBack
     */
    public static void Home_authorgetmessage(String user_id, String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("page", page);
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Home_authorgetmessage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper").i(succeed.toString());
                        Log.e("aa", "------" + succeed);
                        NotifyFragmentBean entity = FastJSONHelper.getPerson(succeed, NotifyFragmentBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页=推荐
     *
     * @param callBack
     */
    public static void getHomeFirstpagedata(String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getFirstpagedata(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        FirstpagedataBean entity = FastJSONHelper.getPerson(succeed, FirstpagedataBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页=纹理
     *
     * @param callBack
     */
    public static void getFirstpagedatatexture(int page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Page", String.valueOf(page));
        map.put("Number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getFirstpagedatatexture(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        FirstpagedataBean entity = FastJSONHelper.getPerson(succeed, FirstpagedataBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页=潮流
     *
     * @param callBack
     */
    public static void getFirstpagedatatrend(int page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Page", String.valueOf(page));
        map.put("Number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getFirstpagedatatrend(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        FashionBean entity = FastJSONHelper.getPerson(succeed, FashionBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页=搜索历史
     *
     * @param callBack
     */
    public static void getuserselecthistory(int page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Page", String.valueOf(page));
        map.put("Number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getUserselecthistory(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        FashionBean entity = FastJSONHelper.getPerson(succeed, FashionBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页=首页=前台搜索历史
     *
     * @param callBack
     */
    public static void Home_selecthistory(int page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(page));
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Home_selecthistory(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        FashionBean entity = FastJSONHelper.getPerson(succeed, FashionBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页=搜索
     *
     * @param callBack
     */
    public static void getuserselect(String user_id, int page, String selectname, String selecttype, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("page", String.valueOf(page));
        map.put("number", "10");
        map.put("selectname", selectname);
        map.put("selecttype", selecttype);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getUserselect(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        VideoContextBean entity = FastJSONHelper.getPerson(succeed, VideoContextBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页=签到
     *
     * @param callBack
     */
    public static void getSignIn(String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getSignIn(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        SignInBean entity = FastJSONHelper.getPerson(succeed, SignInBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页=交易信息
     * @param callBack
     */
    public static void getInformation(String user_id, String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("page", page);
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getInformation(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        InForMationBean entity = FastJSONHelper.getPerson(succeed, InForMationBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页=其他用户主页 头部信息
     *
     * @param callBack
     */
    public static void getUserfirstpagetitle(String userid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userid", userid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getUserfirstpagetitle(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        SignInBean entity = FastJSONHelper.getPerson(succeed, SignInBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=签名修改
     *
     * @param callBack
     */
    public static void getChangeautograph(String autograph, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("autograph", autograph);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getChangeautograph(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        SignInBean entity = FastJSONHelper.getPerson(succeed, SignInBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=昵称修改
     *
     * @param callBack
     */
    public static void getChangenickname(String name, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getChangenickname(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        SignInBean entity = FastJSONHelper.getPerson(succeed, SignInBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getError()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=邮箱修改
     *
     * @param callBack
     */
    public static void getChangEmail(String email, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mail", email);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getChangEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        SignInBean entity = FastJSONHelper.getPerson(succeed, SignInBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=修改头像
     *
     * @param callBack
     */
    public static void geChangeUserPic(File file, final HttpUtilsCallBack<String> callBack) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("userpic", file.getName(), requestFile);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getChangeUserPic(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        MineFileBean entity = FastJSONHelper.getPerson(succeed, MineFileBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=收藏
     *
     * @param callBack
     */
    public static void getUserCollection(String page, String type, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        map.put("type", type);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getUserCollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        CollectBean entity = FastJSONHelper.getPerson(succeed, CollectBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=修改密码
     *
     * @param callBack
     */
    public static void getChangePassword(String old, String psNew, String repeat, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("old", old);
        map.put("new", psNew);
        map.put("repeat", repeat);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getChangePassword(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        ChangePasswordBean entity = FastJSONHelper.getPerson(succeed, ChangePasswordBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=登录记录
     *
     * @param callBack
     */
    public static void getlogonrecord(String user_id, String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getlogonrecord(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        LoGinRecordBean entity = FastJSONHelper.getPerson(succeed, LoGinRecordBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=用户所有的收货地址
     *
     * @param callBack
     */
    public static void getUserAddress(String user_id, String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getUserAddress(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AllAddBean entity = FastJSONHelper.getPerson(succeed, AllAddBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private static String httpFailureMsg() {
        if (NetUtils.isConnected()) {
            return "很抱歉，系统繁忙，请稍后重试。";
        } else {
            return "检查网络连接情况，请稍后重试。";
        }
    }

    /**
     * 我的=添加收货地址
     *
     * @param callBack
     */
    public static void Addshopaddress(String user_id, String name, String phone, String allname, String address, String regionid, String status, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("name", name);
        map.put("phone", phone);
        map.put("allname", allname);
        map.put("address", address);
        map.put("regionid", regionid);
        map.put("status", status);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getAddshopaddress(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AddAddressBean entity = FastJSONHelper.getPerson(succeed, AddAddressBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=删除收货地址
     *
     * @param callBack
     */
    public static void deleteaddress(String user_id, String addressid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userid", user_id);
        map.put("addressid", addressid);
        Log.e("aa", "----删除收货地址--user_id----" + user_id + "-------addressid------" + addressid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.deleteaddress(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AddAddressBean entity = FastJSONHelper.getPerson(succeed, AddAddressBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 我的=修改默认地址
     */
    public static void changeadderssstatus(String addressid, String userid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("addressid", addressid);
        map.put("userid", userid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.changeadderssstatus(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AddAddressBean entity = FastJSONHelper.getPerson(succeed, AddAddressBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 我的=修改收货地址
     *
     * @param callBack
     */
    public static void Min_modifyaddress(String user_id, String addressid, String name, String phone, String allname, String address, String regionid, String status, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("addressid", addressid);
        map.put("name", name);
        map.put("phone", phone);
        map.put("allname", allname);
        map.put("address", address);
        map.put("regionid", regionid);
        map.put("status", status);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Min_modifyaddress(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AddAddressBean entity = FastJSONHelper.getPerson(succeed, AddAddressBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getMsg()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 我的=取消订单
     */
    public static void CancellationOfOrder(String ordersid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("ordersid", ordersid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.CancellationOfOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AddAddressBean entity = FastJSONHelper.getPerson(succeed, AddAddressBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=投诉意见
     */
    public static void Min_addproposal(String user_id, String detail, String phone, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("detail", detail);
        map.put("phone", phone);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Min_addproposal(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        addproposalBean entity = FastJSONHelper.getPerson(succeed, addproposalBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=省
     *
     * @param callBack
     */
    public static void getprovinces(String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getprovinces(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AddContextBean entity = FastJSONHelper.getPerson(succeed, AddContextBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=市
     *
     * @param callBack
     */
    public static void getcitys(String page, String pid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        map.put("pid", pid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getcitys(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AddContextBean entity = FastJSONHelper.getPerson(succeed, AddContextBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=区
     *
     * @param callBack
     */
    public static void getareas(String page, String pid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        map.put("pid", pid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getareas(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AddContextBean entity = FastJSONHelper.getPerson(succeed, AddContextBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 我的=获取所有标签
     *
     * @param callBack
     */
    public static void getlabels(String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getlabels(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AliiTabBean entity = FastJSONHelper.getPerson(succeed, AliiTabBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=修改性别
     *
     * @param callBack
     */
    public static void changeusersex(String user_id, String sex, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("sex", sex);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.changeusersex(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {


                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AliiTabBean entity = FastJSONHelper.getPerson(succeed, AliiTabBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getMsg()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=修改出生日期
     *
     * @param callBack
     */
    public static void Min_changeuserbarth(String user_id, String barthday, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("barthday", barthday);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Min_changeuserbarth(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {


                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AliiTabBean entity = FastJSONHelper.getPerson(succeed, AliiTabBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getMsg()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=查看物流
     *
     * @param callBack
     */
    public static void Min_viewLogistics(String ordersid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("ordersid", ordersid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Min_viewLogistics(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {


                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed.toString());
                        AliiTabBean entity = FastJSONHelper.getPerson(succeed, AliiTabBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getMsg()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 我的=修改标签
     *
     * @param callBack
     */
    public static void changelabel(String lables, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("labels", lables);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.changelabel(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        TabBean entity = FastJSONHelper.getPerson(succeed, TabBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 商=获取轮播图片
     *
     * @param callBack
     */
    public static void Getsowing(String page, String number, String type, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", number);
        map.put("type", type);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Getsowing(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        GetsowingBean entity = FastJSONHelper.getPerson(succeed, GetsowingBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 商=商品展示
     *
     * @param callBack
     */
    public static void Goodsfirstpage(String page, String number, String ordertype, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", number);
        map.put("ordertype", ordertype);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Goodsfirstpage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        ShopHomeBean entity = FastJSONHelper.getPerson(succeed, ShopHomeBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 商=查看商品
     *
     * @param callBack
     */
    public static void showgoods(String goodsid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("goodsid", goodsid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.showgoods(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        GoodDetailDetailBean entity = FastJSONHelper.getPerson(succeed, GoodDetailDetailBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 商=商品收藏
     *
     * @param callBack
     */
    public static void goodscollection(String goodsid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("goodsid", goodsid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.goodscollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        CollectionBean entity = FastJSONHelper.getPerson(succeed, CollectionBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 商=取消收藏
     *
     * @param callBack
     */
    public static void goodsnotcollection(String goodsid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("goodsid", goodsid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.goodsnotcollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        CollectionBean entity = FastJSONHelper.getPerson(succeed, CollectionBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 商=添加购物车
     *
     * @param callBack
     */
    public static void addshopcar(String goodsid, String num, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("goodsid", goodsid);
        map.put("num", num);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.addshopcar(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        addshopcarBean entity = FastJSONHelper.getPerson(succeed, addshopcarBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 商=查看购物车
     *
     * @param callBack
     */
    public static void showshopcar(String page, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.showshopcar(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        CartBean entity = FastJSONHelper.getPerson(succeed, CartBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 商=删除购物车
     *
     * @param callBack
     */
    public static void deleteShopCar(HashMap<String, String> map, final HttpUtilsCallBack<String> callBack) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("userid", userid);
//        map.put("shopcarids", shopcarid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.deleteShopCar(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        CollectionBean entity = FastJSONHelper.getPerson(succeed, CollectionBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 商=添加订单
     *
     * @param callBack
     */
    public static void addorders(String user_id,
                                 String addressid,
                                 String goodsid,
                                 String goodsnum,
                                 String shopcarids,
                                 String paymentmethod,
                                 String ordersmessage,
                                 String deductibletype,
                                 final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("addressid", addressid);
        map.put("goodsid", goodsid);
        map.put("goodsnum", goodsnum);
        map.put("shopcarids", shopcarids);
        map.put("paymentmethod", paymentmethod);
        map.put("ordersmessage", ordersmessage);
        map.put("deductibletype", deductibletype);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.addorders(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        ConfirmOrderBean entity = FastJSONHelper.getPerson(succeed, ConfirmOrderBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 商=查看用户订单
     *
     * @param callBack
     */
    public static void userorders(String user_id, String page, String type, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("page", page);
        map.put("number", "24");
        map.put("type", type);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userorders(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        UserordersBean entity = FastJSONHelper.getPerson(succeed, UserordersBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    /**
     * 商=确认订单页面中间的商品数据
     *
     * @param callBack
     */
    public static void Shop_intermediatedata(String goodsid, String goodsnum, String shopcarids, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("goodsid", goodsid);
        map.put("goodsnum", goodsnum);
        map.put("shopcarids", shopcarids);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Shop_intermediatedata(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Debug.e("------------" + succeed);
                        Shop_intermediatedataBean entity = FastJSONHelper.getPerson(succeed, Shop_intermediatedataBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    /**
     * 商=用户商品搜索历史
     */
    public static void usergoodsselecthistory(String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("page", "1");
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.usergoodsselecthistory(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        ShopSeacharBean entity = FastJSONHelper.getPerson(succeed, ShopSeacharBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }


    /**
     * 商=商品搜索历史
     *
     * @param callBack
     */
    public static void goodsselecthistory(String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("page", "1");
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.goodsselecthistory(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        ShopSeacharBean entity = FastJSONHelper.getPerson(succeed, ShopSeacharBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }


    /**
     * 商=添加修改购物车数量
     *
     * @param callBack
     */
    public static void Shop_changeshopcar(String user_id, String shopcardid, String number, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("shopcardid", shopcardid);
        map.put("number", number);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Shop_changeshopcar(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        ChangeshopcarBean entity = FastJSONHelper.getPerson(succeed, ChangeshopcarBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    /**
     * 商=商品搜索
     *
     * @param callBack
     */
    public static void selectgoods(String page, String name, String cateid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        map.put("name", name);
        map.put("cateid", cateid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.selectgoods(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        ShopHomeBean entity = FastJSONHelper.getPerson(succeed, ShopHomeBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 商=获取所有商品类别
     *
     * @param callBack
     */
    public static void getgoodscates(String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getgoodscates(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        GetgoodScatesBean entity = FastJSONHelper.getPerson(succeed, GetgoodScatesBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 商=确认订单
     *
     * @param callBack
     */
    public static void Shop_confirmationOfOrder(String user_id, String ordersid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("ordersid", ordersid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Shop_confirmationOfOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        ConfirmationOfOrderBean entity = FastJSONHelper.getPerson(succeed, ConfirmationOfOrderBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 商=删除订单
     *
     * @param callBack
     */
    public static void Shop_deleteorders(String user_id, String ordersid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("ordersid", ordersid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Shop_deleteorders(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        ConfirmationOfOrderBean entity = FastJSONHelper.getPerson(succeed, ConfirmationOfOrderBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 删除搜索记录 （首页）
     *
     * @param callBack
     */
    public static void Shop_deleteselecthistory(String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Shop_deleteselecthistory(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        DeleteSelecthisteryBean entity = FastJSONHelper.getPerson(succeed, DeleteSelecthisteryBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 删除搜索记录 （商城）
     *
     * @param callBack
     */
    public static void Shop_clearuserselectgoodshistory(String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Shop_clearuserselectgoodshistory(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        DeleteSelecthisteryBean entity = FastJSONHelper.getPerson(succeed, DeleteSelecthisteryBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 视频=查看视频
     *
     * @param callBack
     */
    public static void videoshow(String videoid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("videoid", videoid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.videoshow(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        ShopHomeBean entity = FastJSONHelper.getPerson(succeed, ShopHomeBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 视频=视频收藏
     *
     * @param callBack
     */
    public static void videocollection(String videoid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("videoid", videoid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.videocollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 视频=视频取消收藏
     *
     * @param callBack
     */
    public static void videonotcollection(String videoid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("videoid", videoid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.videonotcollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 视频=视频取消赞
     *
     * @param callBack
     */
    public static void videoisnotpoint(String videoid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("videoid", videoid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.videoisnotpoint(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 视频=视频点赞
     *
     * @param callBack
     */
    public static void videoispoint(String videoid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("videoid", videoid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.videoispoint(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 视频=视频评论
     *
     * @param callBack
     */
    public static void videomessage(String videoid, String user_id, String message, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("videoid", videoid);
        map.put("user_id", user_id);
        map.put("message", message);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.videomessage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        VideomessageBean entity = FastJSONHelper.getPerson(succeed, VideomessageBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 视频=获取所有视频评论
     *
     * @param callBack
     */
    public static void videomessageall(String page, String videoid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("videoid", videoid);
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.videomessageall(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        VideoDetailBean entity = FastJSONHelper.getPerson(succeed, VideoDetailBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 视频=视频列表
     *
     * @param callBack
     */
    public static void videolist(String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.videolist(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        DetailCommetListBean entity = FastJSONHelper.getPerson(succeed, DetailCommetListBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 视频=视频打赏
     *
     * @param callBack
     */
    public static void videoreward(String videoid, String number, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("videoid", videoid);
        map.put("number", number);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.videoreward(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        DetailCommetListBean entity = FastJSONHelper.getPerson(succeed, DetailCommetListBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 图文=图文展示
     *
     * @param callBack
     */
    public static void imagetextfirstpage(String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "12");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.imagetextfirstpage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        CircleFragmentBean entity = FastJSONHelper.getPerson(succeed, CircleFragmentBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 图文=作者查看评论详情
     */
    public static void showreaderreview(String messageid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("messageid", messageid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.showreaderreview(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        DetailCirclelmageBean entity = FastJSONHelper.getPerson(succeed, DetailCirclelmageBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 图文=查看作者图文
     */
    public static void showimagetexttoauthor(String author, String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("author", author);
        map.put("page", page);
        map.put("number", "10");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.showimagetexttoauthor(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        DetailCirclelmageBean entity = FastJSONHelper.getPerson(succeed, DetailCirclelmageBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 图文=查看图文
     */
    public static void imagetextpage(String imagetextid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("imagetextid", imagetextid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.imagetextpage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        DetailCirclelmageBean entity = FastJSONHelper.getPerson(succeed, DetailCirclelmageBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 图文=图文取消收藏
     *
     * @param callBack
     */
    public static void imagetextnotcollection(String imagetextid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("imagetextid", imagetextid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.imagetextnotcollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 图文=图文收藏
     *
     * @param callBack
     */
    public static void imagetextcollection(String imagetextid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("imagetextid", imagetextid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.imagetextcollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 图文点赞
     *
     * @param callBack
     */
    public static void imagetextispoint(String imagetextid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("imagetextid", imagetextid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.imagetextispoint(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 图文=图文取消点赞
     *
     * @param callBack
     */
    public static void imagetextisnotpoint(String imagetextid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("imagetextid", imagetextid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.imagetextisnotpoint(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        VideoPointBean entity = FastJSONHelper.getPerson(succeed, VideoPointBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(String.valueOf(entity.getMsg()));
                            }
                        } else {
                            callBack.onFailure(String.valueOf(entity.getError()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 图文=图文打赏
     *
     * @param callBack
     */
    public static void Cirde_imagetextreward(String num, String imagetextid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("num", num);
        map.put("imagetextid", imagetextid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.imagetextreward(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        CircleDetailBean entity = gson.fromJson(succeed, CircleDetailBean.class);
                        callBack.onSucceed(succeed);
//                        if (entity.getCode() == 0) {
//                            if (entity.getError() == 0) {
//                                callBack.onSucceed(succeed);
//                            } else {
//                                callBack.onError(entity.getMsg());
//                            }
//                        } else {
//                            callBack.onFailure(entity.getError()+"");
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 图文=评论列表
     *
     * @param callBack
     */
    public static void Cirde_imagetextmessageall(String page, String imagetextid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        map.put("imagetextid", imagetextid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Cirde_imagetextmessageall(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        DetailCircleImageListBean entity = FastJSONHelper.getPerson(succeed, DetailCircleImageListBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 图文=图文评论
     *
     * @param callBack
     */
    public static void Cirde_imagetextmessage(String imagetextid, String message, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("imagetextid", imagetextid);
        map.put("message", message);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Cirde_imagetextmessage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        CircleDetailBean entity = gson.fromJson(succeed, CircleDetailBean.class);
                        callBack.onSucceed(succeed);
//                        if (entity.getCode() == 0) {
//                            if (entity.getError() == 0) {
//                                callBack.onSucceed(succeed);
//                            } else {
//                                callBack.onError(entity.getMsg());
//                            }
//                        } else {
//                            callBack.onFailure(entity.getError()+"");
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 图文=同类图文
     *
     * @param callBack
     */
    public static void Cirde_getImagetextTotype(String page, String imagetextid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("imagetextid", imagetextid);
        map.put("number", "10");
        map.put("page", page);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Cirde_getImagetextTotype(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        GetImageTextBean entity = gson.fromJson(succeed, GetImageTextBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 软文=软文浏览记录
     *
     * @param callBack
     */
    public static void Advertorial_softtextborwse(String softtextid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("softtextid", softtextid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Advertorial_softtextborwse(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        CircleDetailBean entity = gson.fromJson(succeed, CircleDetailBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 软文=软文打赏
     *
     * @param callBack
     */
    public static void Advertorial_softtextreward(String num, String user_id, String softtextid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("num", num);
        map.put("user_id", user_id);
        map.put("softtextid", softtextid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Advertorial_softtextreward(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        CircleDetailBean entity = gson.fromJson(succeed, CircleDetailBean.class);
                        callBack.onSucceed(succeed);
//                        if (entity.getCode() == 0) {
//                            if (entity.getError() == 0) {
//                                callBack.onSucceed(succeed);
//                            } else {
//                                callBack.onError(entity.getMsg());
//                            }
//                        } else {
//                            callBack.onFailure(entity.getError() + "");
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 软文=软文评论信息
     *
     * @param callBack
     */
    public static void Advertorial_softtextmessageall(String page, String softtextid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        map.put("softtextid", softtextid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Advertorial_softtextmessageall(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        CircleDetailBean entity = gson.fromJson(succeed, CircleDetailBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 软文=软文评论
     *
     * @param callBack
     */
    public static void Advertorial_softtextmessage(String softtextid, String user_id, String message, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("softtextid", softtextid);
        map.put("user_id", user_id);
        map.put("message", message);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Advertorial_softtextmessage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        CircleDetailBean entity = gson.fromJson(succeed, CircleDetailBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 软文=软文取消收藏
     *
     * @param callBack
     */
    public static void Advertorial_softtextnotcollection(String user_id, String softtextid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("softtextid", softtextid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Advertorial_softtextnotcollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        CircleDetailBean entity = gson.fromJson(succeed, CircleDetailBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 软文=软文收藏
     *
     * @param callBack
     */
    public static void Advertorial_softtextcollection(String user_id, String softtextid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("softtextid", softtextid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Advertorial_softtextcollection(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        CircleDetailBean entity = gson.fromJson(succeed, CircleDetailBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 软文=软文取消赞
     *
     * @param callBack
     */
    public static void Advertorial_softtextisnotpoint(String user_id, String softtextid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("softtextid", softtextid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Advertorial_softtextisnotpoint(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        CircleDetailBean entity = gson.fromJson(succeed, CircleDetailBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 软文=软文点赞
     *
     * @param callBack
     */
    public static void Advertorial_softtextispoint(String user_id, String softtextid, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("softtextid", softtextid);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Advertorial_softtextispoint(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        CircleDetailBean entity = gson.fromJson(succeed, CircleDetailBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 软文=查看软文
     *
     * @param callBack
     */
    public static void Advertorial_softtextfirstpage(String softtextid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("softtextid", softtextid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Advertorial_softtextfirstpage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        SofttextFirstPageBean entity = gson.fromJson(succeed, SofttextFirstPageBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 软文=标签选择
     *
     * @param callBack
     */
    public static void Advertorial_getimagetextlabels(final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.Advertorial_getimagetextlabels(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        GetimagetextlabelsBean entity = gson.fromJson(succeed, GetimagetextlabelsBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 软文=标签选择
     *
     * @param callBack
     */
    public static void getreportreason(final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getreportreason(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        GetreporTreasonBean entity = gson.fromJson(succeed, GetreporTreasonBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 用户发过的所有软文
     *
     * @param callBack
     */
    public static void usersofttexts(String page, String userId, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("number", "10");
        map.put("userid", userId);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.usersofttexts(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        UserAdvertorialBean entity = gson.fromJson(succeed, UserAdvertorialBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 用户浏览记录1软文
     */
    public static void userbrowses(String user_id, String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("page", page);
        map.put("number", "10");
        map.put("type", "1");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userbrowses(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        WatchHistoryBean entity = gson.fromJson(succeed, WatchHistoryBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 用户浏览记录2图文
     */
    public static void userbrowses1(String user_id, String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("page", page);
        map.put("number", "10");
        map.put("type", "2");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userbrowses(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        WatchHistoryBean1 entity = gson.fromJson(succeed, WatchHistoryBean1.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 订单详情
     */
    public static void showorders(String ordersid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("ordersid", ordersid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.showorders(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        MyOrderTabDetailsBean entity = FastJSONHelper.getPerson(succeed, MyOrderTabDetailsBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 图文浏览记录添加
     */
    public static void imagetextbrowses(String imagetextid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("imagetextid ", imagetextid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.imagetextbrowses(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Log.e("aa", "--------图文--" + succeed);
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        AddCodeBean entity = gson.fromJson(succeed, AddCodeBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 软文浏览记录添加
     */
    public static void softtextborwses(String softtextid, String user_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("softtextid", softtextid);
        map.put("user_id", user_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.softtextborwses(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Log.e("aa", "--------软文--" + succeed);
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        AddCodeBean entity = gson.fromJson(succeed, AddCodeBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getError() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 订单支付
     */
    public static void payOrders(String ordersid, String paytype, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        Debug.e("---ordersid-----" + ordersid);
        map.put("ordersid", ordersid);
        Debug.e("---paytype-----" + paytype);
        map.put("paytype", paytype);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.payOrders(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        AppPayBean entity = gson.fromJson(succeed, AppPayBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 分享视频软文
     */
    public static void partakeofvideosofttext(String softtext_id, String video_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("softtext_id", softtext_id);
        map.put("video_id", video_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.partakeofvideosofttext(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Logger.t("HttpHelper---").i(succeed);
                        Gson gson = new Gson();
                        ShearBean entity = gson.fromJson(succeed, ShearBean.class);
                        if (entity.getCode() == 0) {
                            if (entity.getError() == 0) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        } else {
                            callBack.onFailure(entity.getMsg() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.t("HttpHelper").i(e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public interface HttpUtilsCallBack<T> {
        public void onFailure(String failure);

        public void onSucceed(T succeed);

        public void onError(String error);
    }

    public static MultipartBody.Part getPart(String path, String key) {
        if (TextUtils.isEmpty(path))
            return null;
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        //封装参数
        RequestBody rb = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part mp = MultipartBody.Part.createFormData(key, file.getName(), rb);
        return mp;
    }
}
