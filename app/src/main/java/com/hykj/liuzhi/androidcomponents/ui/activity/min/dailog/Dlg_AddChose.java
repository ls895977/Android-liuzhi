package com.hykj.liuzhi.androidcomponents.ui.activity.min.dailog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.AddContextBean;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.ui.activity.AddAdressActivity;
import com.hykj.liuzhi.androidcomponents.ui.activity.dailog.BaseDialog;
import com.hykj.liuzhi.androidcomponents.ui.activity.min.adapter.AddChoseAdapter;
import com.hykj.liuzhi.androidcomponents.utils.ACache;
import com.hykj.liuzhi.androidcomponents.utils.ErrorStateCodeUtils;
import com.hykj.liuzhi.androidcomponents.utils.FastJSONHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishan on 2017/12/22.
 */
public class Dlg_AddChose extends BaseDialog {
    OnClick onClick;
    RecyclerView reProvince, reCity, reArea;
    private TextView tv_province, tv_City, tv_Area;
    private SmartRefreshLayout provinceRefresh, cityRefresh, areaRefresh;

    public Dlg_AddChose(Context context, OnClick click) {
        super(context);
        this.onClick = click;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dlg_addchose;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.add_shutdown);
        reProvince = getView(R.id.add_Re_province);
        reProvince.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        reCity = getView(R.id.reCity);
        reCity.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        reArea = getView(R.id.reArea);
        reArea.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        tv_province = getView(R.id.tv_province);
        tv_City = getView(R.id.tv_City);
        tv_Area = getView(R.id.tv_Area);
        provinceRefresh = getView(R.id.refreshLayout_province);
        provinceRefresh.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        provinceRefresh.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        cityRefresh = getView(R.id.refreshLayout_City);
        cityRefresh.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        cityRefresh.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
        areaRefresh = getView(R.id.refreshLayout_Area);
        areaRefresh.setRefreshHeader(new ClassicsHeader(getContext()));  //设置 Header 为 贝塞尔雷达 样式
        areaRefresh.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));//设置 Footer 为 球脉冲 样式
    }

    ACache aCache;

    @Override
    protected void initData() {
        aCache = ACache.get(getContext());
        provinceInit();
        cityInit();
        areaInit();
        postProvince();
    }

    /**
     * 省
     */
    private BaseQuickAdapter.OnItemClickListener itemProvince;

    public void provinceInit() {
        provinceRefresh.setEnableRefresh(true);//启用刷新
        provinceRefresh.setEnableLoadmore(true);//启用加载
        provinceRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                provincePage = 1;
                provinceList.clear();
                postProvince();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        provinceRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                provincePage++;
                postProvince();
                refreshlayout.finishLoadmore();
            }
        });
        itemProvince = new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                provincePid = provinceList.get(position).getId();
                cityList.clear();
                cityPage = 1;
                postCity();
                tv_province.setText(provinceList.get(position).getName());
                Log.e("aa", "------itemProvince---------" + position);
            }
        };
    }

    /**
     * 市
     */
    private BaseQuickAdapter.OnItemClickListener itemCity;

    public void cityInit() {
        cityRefresh.setEnableRefresh(true);//启用刷新
        cityRefresh.setEnableLoadmore(true);//启用加载
        cityRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                cityPage = 1;
                cityList.clear();
                postCity();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        cityRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                cityPage++;
                postCity();
                refreshlayout.finishLoadmore();
            }
        });
        itemCity = new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                areaList.clear();
                areaPage = 1;
                citysPid = cityList.get(position).getId();
                postArea();
                tv_City.setText(cityList.get(position).getName());
            }
        };
    }

    /**
     * 区
     */
    private BaseQuickAdapter.OnItemClickListener itemArea;
    public void areaInit() {
        areaRefresh.setEnableRefresh(true);//启用刷新
        areaRefresh.setEnableLoadmore(true);//启用加载
        areaRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                areaPage = 1;
                areaList.clear();
                postArea();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        areaRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                areaPage++;
                postArea();
                refreshlayout.finishLoadmore();
            }
        });
        itemArea = new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                tv_Area.setText(areaList.get(position).getName());
                onClick.onItem(tv_province.getText().toString(),tv_City.getText().toString(),tv_Area.getText().toString(),areaList.get(position).getParent_id()+"");
                dismiss();
            }
        };
    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.add_shutdown:
                dismiss();
                break;
        }
    }

    public interface OnClick {
        void onItem(String province,String city,String area,String regionid);
    }

    /**
     * 请求返回省
     */
    int provincePage = 1;
    private AddChoseAdapter provinceAdapter;
    List<AddContextBean.DataBean.ArrayBean> provinceList = new ArrayList<>();

    public void postProvince() {
        HttpHelper.getprovinces(provincePage + "",
                new HttpHelper.HttpUtilsCallBack<String>() {
                    @Override
                    public void onFailure(String failure) {
                        Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSucceed(String succeed) {
                        Log.e("aa", "--------");
                        AddContextBean entity = FastJSONHelper.getPerson(succeed, AddContextBean.class);
                        for (int i = 0; i < entity.getData().getArray().size(); i++) {
                            provinceList.add(entity.getData().getArray().get(i));
                        }
                        Log.e("aa", "-----11---" + provinceList.size());
                        if (provinceAdapter == null) {
                            provinceAdapter = new AddChoseAdapter(provinceList);
                            provinceAdapter.setOnItemClickListener(itemProvince);
                            reProvince.setAdapter(provinceAdapter);
                        } else {
                            provinceAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 请求返回市
     */
    int cityPage = 1;
    int provincePid = 0;
    List<AddContextBean.DataBean.ArrayBean> cityList = new ArrayList<>();
    AddChoseAdapter cityAdapter;

    public void postCity() {
        HttpHelper.getcitys(cityPage + "", provincePid + "",
                new HttpHelper.HttpUtilsCallBack<String>() {
                    @Override
                    public void onFailure(String failure) {
                        Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSucceed(String succeed) {
                        AddContextBean entity = FastJSONHelper.getPerson(succeed, AddContextBean.class);
                        for (int i = 0; i < entity.getData().getArray().size(); i++) {
                            cityList.add(entity.getData().getArray().get(i));
                        }
                        if (cityAdapter == null) {
                            cityAdapter = new AddChoseAdapter(cityList);
                            cityAdapter.setOnItemClickListener(itemCity);
                            reCity.setAdapter(cityAdapter);
                        } else {
                            cityAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 请求返回区
     */
    int areaPage = 1;
    int citysPid = 0;
    List<AddContextBean.DataBean.ArrayBean> areaList = new ArrayList<>();
    AddChoseAdapter areaAdapter;

    public void postArea() {
        HttpHelper.getareas(areaPage + "", citysPid + "",
                new HttpHelper.HttpUtilsCallBack<String>() {
                    @Override
                    public void onFailure(String failure) {
                        Toast.makeText(getContext(), failure, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSucceed(String succeed) {
                        AddContextBean entity = FastJSONHelper.getPerson(succeed, AddContextBean.class);
                        for (int i = 0; i < entity.getData().getArray().size(); i++) {
                            areaList.add(entity.getData().getArray().get(i));
                        }
                        if (areaAdapter == null) {
                            areaAdapter = new AddChoseAdapter(areaList);
                            areaAdapter.setOnItemClickListener(itemArea);
                            reArea.setAdapter(areaAdapter);
                        } else {
                            areaAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getContext(), ErrorStateCodeUtils.getRegisterErrorMessage(error), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
