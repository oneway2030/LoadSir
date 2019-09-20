package com.oneway.sample.callback;

import android.content.Context;
import android.view.View;
import com.oneway.loaisir.callback.Callback;
import com.oneway.sample.R;

public class CustomCallBack extends Callback {

    /**
     * 填充布局
     */
    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }

    /**
     * 创建view  getRootVie=>onCreateView后回调
     */
    @Override
    protected void onViewCreate(Context context, View view) {
        super.onViewCreate(context, view);
        //可以执行 一些控件的初始化
    }

    /**
     * 将Callback添加到当前视图时的回调，View为当前Callback的布局View
     */
    @Override
    public void onAttach(Context context, View view) {
        super.onAttach(context, view);
        //可以执行些动画的开始
    }


    /**
     * 将Callback从当前视图删除时的回调，View为当前Callback的布局View
     */
    @Override
    public void onDetach() {
        super.onDetach();
        //可以执行些动画的结束
    }

    /**
     * 是否在显示Callback视图的时候显示原始图(SuccessView)，返回true显示，false隐藏
     */
    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }


    /**
     * 当前Callback的点击事件
     *
     * @return true  覆盖注册时的OnReloadListener, 回调 onReloadListener.onReload(v) 不执行
     * false 先执行onReloadEvent,后执行onReloadListener.onReload(v)
     */
    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }

}
