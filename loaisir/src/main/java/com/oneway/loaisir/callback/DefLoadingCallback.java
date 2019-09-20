package com.oneway.loaisir.callback;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.StyleRes;
import com.oneway.loaisir.R;


/**
 * 作者 oneway on 2019/9/20
 * 描述:
 * 参考链接:
 */
public class DefLoadingCallback extends Callback {

    private String title;
    private String subTitle;
    private int subTitleStyleRes = -1;
    private int titleStyleRes = -1;

    private DefLoadingCallback(Builder builder) {
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.subTitleStyleRes = builder.subTitleStyleRes;
        this.titleStyleRes = builder.titleStyleRes;
        setSuccessVisible(builder.aboveable);
    }

    @Override
    protected int onCreateView() {
        return R.layout.custom_progress_layout;
    }


    @Override
    public void onAttach(Context context, View view) {
        super.onAttach(context, view);
        TextView tv = view.findViewById(R.id.tv);
        tv.setText(title);
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
//    @Override
//    protected void onViewCreate(Context context, View view) {
//        View progressLayout = View.inflate(context, R.layout.custom_progress_layout, null);
//        ProgressBar pb = progressLayout.findViewById(R.id.pb);
//        TextView tv = progressLayout.findViewById(R.id.tv);
//        tv.setText(title);
////
////        LinearLayout ll = new LinearLayout(context);
////        ll.setOrientation(LinearLayout.VERTICAL);
////        ll.setGravity(Gravity.CENTER);
////        //
////        ProgressBar progressBar = new ProgressBar(context);
////        ll.addView(progressBar);
////        if (!TextUtils.isEmpty(title)) {
////            TextView tvTitle = new TextView(context);
////            tvTitle.setText(title);
////            if (titleStyleRes == -1) {
////                tvTitle.setTextAppearance(context, android.R.style.TextAppearance_Large);
////            } else {
////                tvTitle.setTextAppearance(context, titleStyleRes);
////            }
////            ll.addView(tvTitle);
////        }
////        if (!TextUtils.isEmpty(subTitle)) {
////            TextView tvSubtitle = new TextView(context);
////            tvSubtitle.setText(subTitle);
////            if (subTitleStyleRes == -1) {
////                tvSubtitle.setTextAppearance(context, android.R.style.TextAppearance_Medium);
////            } else {
////                tvSubtitle.setTextAppearance(context, subTitleStyleRes);
////            }
////            ll.addView(tvSubtitle);
////        }
//        if (view instanceof ViewGroup) {
//            ViewGroup rootView = (ViewGroup) view;
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT);
//            lp.width= ViewGroup.LayoutParams.MATCH_PARENT;
//            lp.height= ViewGroup.LayoutParams.MATCH_PARENT;
//            rootView.addView(progressLayout, lp);
//        }
//    }

    public static class Builder {

        private String title;
        private String subTitle;
        private int subTitleStyleRes = -1;
        private int titleStyleRes = -1;
        private boolean aboveable;

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

        public Builder setAboveSuccess(boolean aboveable) {
            this.aboveable = aboveable;
            return this;
        }

        public DefLoadingCallback build() {
            return new DefLoadingCallback(this);
        }
    }
}