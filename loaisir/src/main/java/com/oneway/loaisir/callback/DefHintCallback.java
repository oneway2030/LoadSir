package com.oneway.loaisir.callback;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;
import com.oneway.loaisir.R;


/**
 * 作者 oneway on 2019/9/20
 * 描述:
 * 参考链接:
 */
public class DefHintCallback extends Callback {

    private String title;
    private String subTitle;
    private int imgResId;
    private int titleStyleRes;
    private int subTitleStyleRes;

    public DefHintCallback(Builder builder) {
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.imgResId = builder.imgResId;
        this.subTitleStyleRes = builder.subTitleStyleRes;
        this.titleStyleRes = builder.titleStyleRes;
    }

    @Override
    protected int onCreateView() {
        return R.layout.custom_hint_layout;
    }

    @Override
    public void onAttach(Context context, View view) {
        super.onAttach(context, view);
        TextView tv = view.findViewById(R.id.tv_msg);
        ImageView iv = view.findViewById(R.id.iv);
        tv.setText(title);
        if (imgResId > 0) {
            iv.setImageResource(imgResId);
        }
    }

    public static class Builder {
        private String title;
        private String subTitle;
        private int imgResId = -1;
        private int subTitleStyleRes = -1;
        private int titleStyleRes = -1;

        public Builder setHintImg(@DrawableRes int imgResId) {
            this.imgResId = imgResId;
            return this;
        }

        public Builder setTitle(String title) {
            return setTitle(title, -1);
        }

        public Builder setTitle(String title, @StyleRes int titleStyleRes) {
            this.title = title;
            this.titleStyleRes = titleStyleRes;
            return this;
        }

        public Builder setSubTitle(String subTitle) {
            return setSubTitle(subTitle, -1);
        }

        public Builder setSubTitle(String subTitle, @StyleRes int subTitleStyleRes) {
            this.subTitle = subTitle;
            this.subTitleStyleRes = subTitleStyleRes;
            return this;
        }

        public DefHintCallback build() {
            return new DefHintCallback(this);
        }
    }
}