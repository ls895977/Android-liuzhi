package com.hykj.liuzhi.androidcomponents.ui.adapter;

import android.content.Context;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.ArrayList;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.MutiItem;
public class RecommendAdapter extends BaseMultiItemQuickAdapter<MutiItem, BaseViewHolder> {
    public RecommendAdapter(Context context, ArrayList<MutiItem> data) {
        super(data);
        addItemType(MutiItem.IMAGE_TEXT_BOTTOM, R.layout.layout_adapter_item_image_text_bottom);
        addItemType(MutiItem.IMAGE_TEXT_INSIDE, R.layout.layout_adapter_item_image_text_inside);
        addItemType(MutiItem.IMAGE_TEXT_TOP, R.layout.layout_adapter_item_image_text_top);
        addItemType(MutiItem.SECTION_HEADER, R.layout.layout_adapter_section_header);
        addItemType(MutiItem.SOFT_ARTICLE, R.layout.layout_adapter_section_soft_header);
        addItemType(MutiItem.MORE, R.layout.layout_adapter_section_soft_header);
    }

    @Override
    protected void convert(BaseViewHolder helper, MutiItem item) {
        switch (helper.getItemViewType()) {
            case MutiItem.IMAGE_TEXT_BOTTOM:

                break;
            case MutiItem.IMAGE_TEXT_INSIDE:

                break;
            case MutiItem.IMAGE_TEXT_TOP:

                break;
            case MutiItem.SECTION_HEADER:

                break;
            case MutiItem.SOFT_ARTICLE:

                break;
        }
    }


}
