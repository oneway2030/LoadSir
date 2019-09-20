package com.oneway.sample.callback;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.oneway.loaisir.callback.Callback;
import com.oneway.sample.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 作者 oneway on 2019/9/20
 * 描述: 带下拉刷新的 空状态页面
 * 参考链接:
 */
public class EmptyCallBack extends Callback {

    private int imgResId = R.mipmap.status_empty;
    private int titleResId;
    private TextView tv;
    private ImageView iv;
    private SmartRefreshLayout mSmartRefreshLayout;
    private boolean isEnableRefresh = true; //默认启用下拉刷新,下拉刷新重新加载 onReload
    private int TIME_OUT = 1500;

    public EmptyCallBack() {
    }

    public EmptyCallBack(int titleResId, int imgResId, boolean isEnableRefresh) {
        this.titleResId = titleResId;
        this.imgResId = imgResId;
        this.isEnableRefresh = isEnableRefresh;
    }

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty_and_refresh;//不依赖 下拉刷新
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
        mSmartRefreshLayout = view.findViewById(R.id.SmartRefreshLayout);
        if (isEnableRefresh) {
            mSmartRefreshLayout.setEnableLoadMore(false);
            mSmartRefreshLayout.setEnableRefresh(isEnableRefresh);
            //下拉刷新
            mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    if (onReloadListener != null) {
                        onReloadListener.onReload(null);
                    }
                }
            });
        }
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

    /**
     * 重复显示
     */
    @Override
    public void onDepeatDisplay() {
        //重复显示的时候关闭下拉刷新
        if (mSmartRefreshLayout != null && mSmartRefreshLayout.getState() == RefreshState.Refreshing)
            mSmartRefreshLayout.finishRefresh(TIME_OUT);//延迟2000毫秒后结束刷新
    }


}
