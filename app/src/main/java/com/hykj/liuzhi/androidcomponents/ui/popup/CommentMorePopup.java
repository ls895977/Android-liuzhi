package com.hykj.liuzhi.androidcomponents.ui.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.hykj.liuzhi.R;

/**
 * Created by Terminator on 2019/3/28.
 */
public class CommentMorePopup extends PopupWindow {

    public Callback mCallback;

    public CommentMorePopup(Context context, Callback callback) {
        mCallback = callback;
        View content = LayoutInflater.from(context).inflate(R.layout.popup_comment_more, null);
        setContentView(content);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable());
        setTouchable(true);
        content.findViewById(R.id.tv_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) mCallback.comment();
                dismiss();
            }
        });
        content.findViewById(R.id.tv_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) mCallback.report();
                dismiss();
            }
        });
    }

    public interface Callback {

        void comment();

        void report();

    }

}
