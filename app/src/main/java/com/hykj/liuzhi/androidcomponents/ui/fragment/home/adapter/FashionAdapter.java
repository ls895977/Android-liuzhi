package com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.FashionBase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.SoftLanguageBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class FashionAdapter extends BaseMultiItemQuickAdapter<FashionBase, BaseViewHolder> {
    Context context;

    public FashionAdapter(Context context1, List<FashionBase> data) {
        super(data);
        this.context = context1;
        addItemType(FashionBase.FashionItem1, R.layout.home_fashion_item);
        addItemType(FashionBase.FashionItem2, R.layout.layout_adapter_item_image_text_top);
        addItemType(FashionBase.FashionItem3, R.layout.layout_adapter_section_imge);
    }
    @Override
    protected void convert(BaseViewHolder helper, FashionBase item) {
        switch (helper.getItemViewType()) {
            case FashionBase.FashionItem1:
                RoundedImageView image = helper.getView(R.id.imageView1);
                Glide.with(context).load(item.getSofttextimage_url()).into(image);
                helper.setText(R.id.hone_fashion_context, item.getSofttext_title());
                break;
            case FashionBase.FashionItem2:
                ImageView haderImage = helper.getView(R.id.iv_top_icon);
                Glide.with(context).load(item.getUser_pic()).into(haderImage);
                ImageView back = helper.getView(R.id.iv_center);
                if(item.getSofttextimage_url()!=null) {
                    Glide.with(context).load(item.getSofttextimage_url()).into(back);
                    helper.setText(R.id.tv_nickName, item.getUser_nickname());
                }
                break;
            case FashionBase.FashionItem3:
                ImageView back1 = helper.getView(R.id.button_img);
                Glide.with(context).load(item.getAdv_url()).into(back1);
                break;
        }
    }
}
