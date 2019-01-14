package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.fragment.detail.bean.VideoDetailBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.view.FixGridLayout;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/15
 * @describe:
 */
public class DetailMoreVideoAdapter extends BaseQuickAdapter<VideoDetailBean.DataBean.ArrayBean, BaseViewHolder> {
    private Context context;

    public DetailMoreVideoAdapter(@Nullable List<VideoDetailBean.DataBean.ArrayBean> data, Context context1) {
        super(R.layout.layout_item_detail_more_video, data);
        this.context = context1;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoDetailBean.DataBean.ArrayBean item) {
        if (item.getVideolabels() != null) {
            FixGridLayout flowLayout = helper.getView(R.id.flow_layout);
            flowLayout.removeAllViews();
            for (int i = 0; i < item.getVideolabels().size(); i++) {
                if (item.getVideolabels().size() < 4) {
                    if (item.getVideolabels().get(i).getVideolabel_name() != null) {
                        View view = LayoutInflater.from(context).inflate(R.layout.tv_shoplab, null);
                        TextView name = view.findViewById(R.id.tab_shop);
                        name.setText(item.getVideolabels().get(i).getVideolabel_name());
                        flowLayout.addView(view);
                    }
                }
            }
        }
        ImageView img = helper.getView(R.id.item_detailmore_img);
        Glide.with(context).load(item.getVideo_image()).into(img);
        helper.setText(R.id.item_detailmore_title, item.getVideo_name());
        helper.setText(R.id.item_detailmore_num, item.getVideo_videonum() + "");
    }
}
