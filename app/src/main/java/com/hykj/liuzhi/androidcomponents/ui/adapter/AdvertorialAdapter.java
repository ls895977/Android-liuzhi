package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CircleBean;
import com.hykj.liuzhi.androidcomponents.ui.fragment.collect.bean.Collectbase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBase;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

//R.layout.item_advertorial
public class AdvertorialAdapter extends BaseMultiItemQuickAdapter<Collectbase, BaseViewHolder> {
    Context context;

    public AdvertorialAdapter(Context context1, List<Collectbase> data) {
        super(data);
        this.context = context1;
        addItemType(Collectbase.FashionItem1, R.layout.layout_adapter_item_image_text_top);//软文
        addItemType(Collectbase.FashionItem2, R.layout.layout_adapter_item_image_text_inside);//视频
        addItemType(Collectbase.Imge1, R.layout.layout_item_circle_three_small);//图片显示1
        addItemType(Collectbase.Imge2, R.layout.layout_item_circle_right_big);//图片显示2
        addItemType(Collectbase.Imge3, R.layout.layout_item_circle_left_big);//图片显示3
        addItemType(Collectbase.Shoping_1, R.layout.layout_item_shoping1);//商品显示1
        addItemType(Collectbase.Shoping_2, R.layout.layout_item_circle_left_big);//商品显示2
    }

    @Override
    protected void convert(BaseViewHolder helper, Collectbase item) {
        switch (helper.getItemViewType()) {
            case Collectbase.FashionItem1://软文
                ImageView haderImage = helper.getView(R.id.iv_top_icon);
                Glide.with(context).load(item.getUser_pic()).into(haderImage);
                ImageView back = helper.getView(R.id.iv_center);
                if (item.getSofttextimage_url() != null) {
                    Glide.with(context).load(item.getSofttextimage_url()).into(back);
                    helper.setText(R.id.tv_nickName, item.getUser_nickname());
                }
                break;
            case Collectbase.FashionItem2://视频
                helper.setText(R.id.softLanguage_Name, item.getVideo_name());
                ImageView loding = helper.getView(R.id.softLanguage_back);
                Glide.with(context).load(item.getVideo_image()).into(loding);
                break;
            case Collectbase.Imge1:
                break;
            case Collectbase.Imge2:
                break;
            case Collectbase.Imge3:
                break;
            case Collectbase.Shoping_1:
                break;
            case Collectbase.Shoping_2:
                break;
        }
    }


}
