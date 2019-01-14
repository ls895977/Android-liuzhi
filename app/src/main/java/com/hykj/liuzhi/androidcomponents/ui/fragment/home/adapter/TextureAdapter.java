package com.hykj.liuzhi.androidcomponents.ui.fragment.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean.TextureBase;
import com.hykj.liuzhi.androidcomponents.ui.fragment.home.view.FixGridLayout;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class TextureAdapter extends BaseMultiItemQuickAdapter<TextureBase, BaseViewHolder> {
    Context context;

    public TextureAdapter(Context context1, List<TextureBase> data) {
        super(data);
        this.context = context1;
        addItemType(TextureBase.TextureIitem1, R.layout.layout_adapter_item_image_text_inside);
        addItemType(TextureBase.TextureIitem2, R.layout.item_album);//专辑
        addItemType(TextureBase.TextureIitem3, R.layout.item_dissection);//分列
    }

    @Override
    protected void convert(BaseViewHolder helper, TextureBase item) {
        switch (helper.getItemViewType()) {
            case TextureBase.TextureIitem1:
                ImageView imageView = helper.getView(R.id.softLanguage_back);
                Glide.with(context).load(item.getVideo_image()).into(imageView);
                helper.setText(R.id.softLanguage_Name, item.getVideo_name());
                break;
            case TextureBase.TextureIitem2://专辑
                ImageView img = helper.getView(R.id.button_img);
                Glide.with(context).load(item.getVideo_image()).into(img);
                break;
            case TextureBase.TextureIitem3://专辑
                RoundedImageView bgc = helper.getView(R.id.imageView1);
                Glide.with(context).load(item.getVideo_image()).into(bgc);
                helper.setText(R.id.name, item.getVideo_name());
                helper.setText(R.id.context, item.getVideo_detail());
                FixGridLayout addLinear = helper.getView(R.id.myll);
                for (int i = 0; i < item.getLabelsdata().size(); i++) {
                    if( item.getLabelsdata().size()>4){
                        return;
                    }
                    View view = LinearLayout.inflate(context, R.layout.tv_item, null);
                    TextView tv = view.findViewById(R.id.item_tv);
                    tv.setText(item.getLabelsdata().get(i).getVideolabel_name());
                    addLinear.addView(view);
                }
                break;
        }
    }
}
