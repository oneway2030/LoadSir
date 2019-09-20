package com.oneway.loaisir.callback;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oneway.loaisir.R;

/**
 * 作者 oneway on 2018/12/17
 * 描述:
 * 参考链接:
 */
public class DefEmptyCallBack extends Callback {

    private int imgResId = R.mipmap.status_empty;
    private int titleResId;
    private TextView tv;
    private ImageView iv;

    public DefEmptyCallBack() {
    }

    public DefEmptyCallBack(int titleResId, int imgResId) {
        this.titleResId = titleResId;
        this.imgResId = imgResId;
    }

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }

    @Override
    public void onAttach(final Context context, final View view) {
        super.onAttach(context, view);
        tv = view.findViewById(R.id.tv_empty);
        iv = view.findViewById(R.id.iv_empty);
        if (titleResId > 0) {
            tv.setText(context.getResources().getString(titleResId));
        }
        iv.setImageResource(imgResId);
    }

    public void setOnIconClickListener(View.OnClickListener l) {
        if (iv != null)
            iv.setOnClickListener(l);
    }

    public void setTitle(String titleStr) {
        tv.setText(titleStr);
    }

    public void setTitle(int titleResId) {
        tv.setText(tv.getContext().getResources().getString(titleResId));
    }

    //当前Callback的点击事件，如果返回true则覆盖注册时的onReloa()，如果返回false则两者都执行，先执行onReloadEvent()。
    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }


}
