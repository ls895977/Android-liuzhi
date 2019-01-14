package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.GetgoodScatesBean;
import com.hykj.liuzhi.androidcomponents.bean.UserAttentionBean;
import com.hykj.liuzhi.androidcomponents.interfaces.GlideImageLoader;
import com.hykj.liuzhi.androidcomponents.net.http.HttpHelper;
import com.hykj.liuzhi.androidcomponents.utils.LocalInfoUtils;
import com.luck.picture.lib.tools.ToastManage;
import com.orhanobut.logger.Logger;

import java.util.List;

import static com.zhouyou.http.EasyHttp.getContext;

public class CommodityCategoryLineAdapter extends BaseQuickAdapter<GetgoodScatesBean.DataBean.ArrayBean, BaseViewHolder> {
    private Context context;
    private onBackItem onBackItem;
    public CommodityCategoryLineAdapter(Context context1, @Nullable List<GetgoodScatesBean.DataBean.ArrayBean> data, onBackItem onBackItem1) {
        super(R.layout.item_commoditycategoryline, data);
        context = context1;
        this.onBackItem = onBackItem1;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GetgoodScatesBean.DataBean.ArrayBean item) {
        TextView tv = helper.getView(R.id.commodity_tv);
        tv.setText(item.getGoodscate_name());
        tv.setTextSize(18);
        RecyclerView recyclerView = helper.getView(R.id.commodity_chlide);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CommodityCategoryChlideLineAdapter adapter = new CommodityCategoryChlideLineAdapter(context, item.getChildren());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                onBackItem.OnBack(item.getChildren().get(position));
            }
        });
        if (item.isOnOff()) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    public interface onBackItem {
        void OnBack(GetgoodScatesBean.DataBean.ArrayBean.ChildrenBean item);
    }
}
