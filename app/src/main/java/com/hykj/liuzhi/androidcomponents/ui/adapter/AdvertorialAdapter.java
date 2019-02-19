package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.CircleBean;
import com.hykj.liuzhi.androidcomponents.bean.CircleBean1;
import com.hykj.liuzhi.androidcomponents.ui.fragment.collect.bean.Collectbase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.shop.view.MyLinearLayout;
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
        addItemType(Collectbase.Shoping_2, R.layout.item_shop2);//商品显示2
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
            case Collectbase.Shoping_1:
                helper.addOnClickListener(R.id.shop1);
                Glide.with(context).load(item.getGoods_pic()).into((RoundedImageView) helper.getView(R.id.imageView1));
                helper.setText(R.id.tv_goods_name, item.getGoods_name());
                helper.setText(R.id.tv_goods_price, "¥" + item.getGoods_price());
                break;
            case Collectbase.Shoping_2:
                for (int i = 0; i < item.getArray().size(); i++) {
                    if (i == 0) {
                        Glide.with(context).load(item.getArray().get(i).getGoods_pic()).into((RoundedImageView) helper.getView(R.id.shopingImage1));
                        helper.setText(R.id.shopName1, item.getArray().get(i).getGoods_name());
                        helper.setText(R.id.tv_goods_price1, "¥" + item.getArray().get(i).getGoods_price());
                    } else {
                        Glide.with(context).load(item.getArray().get(i).getGoods_pic()).into((RoundedImageView) helper.getView(R.id.shopingImage2));
                        helper.setText(R.id.shopName2, item.getArray().get(i).getGoods_name());
                        helper.setText(R.id.tv_goods_price2, "¥" + item.getArray().get(i).getGoods_price());
                    }
                }
                helper.addOnClickListener(R.id.shop2);
                helper.addOnClickListener(R.id.shop3);
                break;
            case Collectbase.Imge1:
                helper.addOnClickListener(R.id.THREE_SMALL_img1);
                if (item.getArray().size() >= 1) {
                    Glide.with(context).load(item.getArray().get(0).getImagetextimage_url()).into((ImageView) helper.getView(R.id.THREE_SMALL_img1));
                }
                helper.addOnClickListener(R.id.THREE_SMALL_img2);
                if (item.getArray().size() >= 2) {
                    Glide.with(context).load(item.getArray().get(1).getImagetextimage_url()).into((ImageView) helper.getView(R.id.THREE_SMALL_img2));
                }
                helper.addOnClickListener(R.id.THREE_SMALL_img3);
                if (item.getArray().size() >= 3) {
                    Glide.with(context).load(item.getArray().get(2).getImagetextimage_url()).into((ImageView) helper.getView(R.id.THREE_SMALL_img3));
                }
                break;
            case Collectbase.Imge2:
                helper.addOnClickListener(R.id.RIGHT_BIG_img1);
                if (item.getArray().size() >= 1) {
                    Glide.with(context).load(item.getArray().get(0).getImagetextimage_url()).into((ImageView) helper.getView(R.id.RIGHT_BIG_img1));
                }
                helper.addOnClickListener(R.id.RIGHT_BIG_img2);
                if (item.getArray().size() >= 2) {
                    Glide.with(context).load(item.getArray().get(1).getImagetextimage_url()).into((ImageView) helper.getView(R.id.RIGHT_BIG_img2));
                }
                helper.addOnClickListener(R.id.RIGHT_BIG_img3);
                if (item.getArray().size() >= 3) {
                    Glide.with(context).load(item.getArray().get(2).getImagetextimage_url()).into((ImageView) helper.getView(R.id.RIGHT_BIG_img3));
                }
                break;
            case Collectbase.Imge3:
                helper.addOnClickListener(R.id.LEFT_BIG_img1);
                if (item.getArray().size() >= 1) {
                    Glide.with(context).load(item.getArray().get(0).getImagetextimage_url()).into((ImageView) helper.getView(R.id.LEFT_BIG_img1));
                }
                helper.addOnClickListener(R.id.LEFT_BIG_img2);
                if (item.getArray().size() >= 2) {
                    Glide.with(context).load(item.getArray().get(1).getImagetextimage_url()).into((ImageView) helper.getView(R.id.LEFT_BIG_img2));
                }
                helper.addOnClickListener(R.id.LEFT_BIG_img3);
                if (item.getArray().size() >= 3) {
                    Glide.with(context).load(item.getArray().get(2).getImagetextimage_url()).into((ImageView) helper.getView(R.id.LEFT_BIG_img3));
                }
                break;
        }
    }


}
