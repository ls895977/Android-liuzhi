package com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.SoftLanguageBean;

import java.util.List;

public class FirstpageAdapter extends BaseMultiItemQuickAdapter<SoftLanguageBean, BaseViewHolder> {

    Context context;
    private int mScreenWidth;

    public FirstpageAdapter(Context context1, List<SoftLanguageBean> data) {
        super(data);
        this.context = context1;
        mScreenWidth = context1.getResources().getDisplayMetrics().widthPixels;
        addItemType(SoftLanguageBean.IMAGE_TEXT_BOTTOM, R.layout.layout_adapter_item_image_text_bottom);
        addItemType(SoftLanguageBean.IMAGE_TEXT_INSIDE, R.layout.layout_adapter_item_image_text_inside);
        addItemType(SoftLanguageBean.IMAGE_TEXT_TOP, R.layout.layout_adapter_item_image_text_top);
        addItemType(SoftLanguageBean.SECTION_HEADER, R.layout.layout_adapter_section_header);
        addItemType(SoftLanguageBean.SOFT_ARTICLE, R.layout.layout_adapter_section_soft_header);
        addItemType(SoftLanguageBean.MORE, R.layout.layout_adapter_section_soft_header);
        addItemType(SoftLanguageBean.IMAGE_HADER, R.layout.layout_adapter_section_imgeheader);
        addItemType(SoftLanguageBean.IMAGE_GANGGAO, R.layout.item_imge);
    }

    @Override
    protected void convert(BaseViewHolder helper, SoftLanguageBean item) {
        switch (helper.getItemViewType()) {
            case SoftLanguageBean.IMAGE_TEXT_BOTTOM:
                helper.addOnClickListener(R.id.video1);
                break;
            case SoftLanguageBean.IMAGE_TEXT_INSIDE:
                View view = helper.getView(R.id.rl_center);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = mScreenWidth / 3;
                view.setLayoutParams(layoutParams);
                helper.addOnClickListener(R.id.video1);
                helper.setText(R.id.softLanguage_Name, item.getVideo_name());
                ImageView loding = helper.getView(R.id.softLanguage_back);
                Glide.with(context).load(item.getVideo_image()).into(loding);
                helper.addOnClickListener(R.id.video1);
                break;
            case SoftLanguageBean.IMAGE_TEXT_TOP:
                helper.addOnClickListener(R.id.image1);
                ImageView haderImage = helper.getView(R.id.iv_top_icon);
                Glide.with(context).load(item.getUser_pic()).into(haderImage);
                helper.setText(R.id.tv_nickName, item.getUser_nickname());
                ImageView back = helper.getView(R.id.iv_center);
                Glide.with(context).load(item.getSofttextimageURL()).into(back);
                helper.addOnClickListener(R.id.image1);
                break;
            case SoftLanguageBean.SECTION_HEADER:

                break;
            case SoftLanguageBean.SOFT_ARTICLE:
                break;
            case SoftLanguageBean.IMAGE_HADER:
                helper.addOnClickListener(R.id.my_addadd);
                break;
            case SoftLanguageBean.IMAGE_GANGGAO:
                RequestOptions options = new RequestOptions();
                options.error(R.mipmap.image_text_black);
                options.diskCacheStrategy(DiskCacheStrategy.ALL);
                options.dontAnimate();
                options.priority(Priority.HIGH);
                ImageView myImage = helper.getView(R.id.button_img1111);
                Glide.with(context).load(item.getAdv_url())
                        .apply(options).into(myImage);
                helper.addOnClickListener(R.id.guanggao);
                break;
        }
    }
}
