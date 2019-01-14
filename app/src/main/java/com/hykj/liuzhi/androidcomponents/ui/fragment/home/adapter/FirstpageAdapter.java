package com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.SoftLanguageBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class FirstpageAdapter extends BaseMultiItemQuickAdapter<SoftLanguageBean, BaseViewHolder> {
    Context context;
    public  FirstpageAdapter(Context context1, List<SoftLanguageBean> data) {
        super(data);
        this.context = context1;
        addItemType(SoftLanguageBean.IMAGE_TEXT_BOTTOM, R.layout.layout_adapter_item_image_text_bottom);
        addItemType(SoftLanguageBean.IMAGE_TEXT_INSIDE, R.layout.layout_adapter_item_image_text_inside);
        addItemType(SoftLanguageBean.IMAGE_TEXT_TOP, R.layout.layout_adapter_item_image_text_top);
        addItemType(SoftLanguageBean.SECTION_HEADER, R.layout.layout_adapter_section_header);
        addItemType(SoftLanguageBean.SOFT_ARTICLE, R.layout.layout_adapter_section_soft_header);
        addItemType(SoftLanguageBean.MORE, R.layout.layout_adapter_section_soft_header);
        addItemType(SoftLanguageBean.IMAGE_HADER, R.layout.layout_adapter_section_imgeheader);
        addItemType(SoftLanguageBean.IMAGE_BUTTOM, R.layout.layout_adapter_section_imge);
    }
    @Override
    protected void convert(BaseViewHolder helper, SoftLanguageBean item) {
        switch (helper.getItemViewType()) {
            case SoftLanguageBean.IMAGE_TEXT_BOTTOM:
                break;
            case SoftLanguageBean.IMAGE_TEXT_INSIDE:
                helper.setText(R.id.softLanguage_Name, item.getVideo_name());
                ImageView loding = helper.getView(R.id.softLanguage_back);
                Glide.with(context).load(item.getVideo_image()).into(loding);
                break;
            case SoftLanguageBean.IMAGE_TEXT_TOP:
                ImageView haderImage = helper.getView(R.id.iv_top_icon);
                Glide.with(context).load(item.getUser_pic()).into(haderImage);
                helper.setText(R.id.tv_nickName, item.getUser_nickname());
                ImageView back = helper.getView(R.id.iv_center);
                Glide.with(context).load(item.getSofttextimageURL()).into(back);
                break;
            case SoftLanguageBean.SECTION_HEADER:

                break;
            case SoftLanguageBean.SOFT_ARTICLE:

                break;
            case SoftLanguageBean.IMAGE_HADER:

                break;
            case SoftLanguageBean.IMAGE_BUTTOM:
                ImageView img = helper.getView(R.id.button_img);
                Glide.with(context).load(item.getAdv_url()).into(img);
                break;
        }
    }
}
