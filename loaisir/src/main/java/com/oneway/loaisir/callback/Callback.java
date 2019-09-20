package com.oneway.loaisir.callback;

import android.content.Context;
import android.view.View;

import java.io.*;


/**
 * 作者 oneway on 2019/9/20
 * 描述:
 * 参考链接:
 */
public abstract class Callback implements Serializable {
    private View rootView;
    private Context context;
    protected OnReloadListener onReloadListener;
    private boolean successViewVisible;

    public Callback() {
    }

    Callback(View view, Context context, OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
    }

    public Callback setCallback(View view, Context context, OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
        return this;
    }

    public View getRootView() {
        int resId = onCreateView();
        if (resId == 0 && rootView != null) {
            return rootView;
        }

        if (onBuildView(context) != null) {
            rootView = onBuildView(context);
        }

        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null);
        }
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadEvent(context, rootView)) {
                    return;
                }
                if (onReloadListener != null) {
                    onReloadListener.onReload(v);
                }
            }
        });
        onViewCreate(context, rootView);
        return rootView;
    }

    protected View onBuildView(Context context) {
        return null;
    }

    /**
     * 是否在显示Callback视图的时候显示原始图(SuccessView)，返回true显示，false隐藏
     */
    public boolean getSuccessVisible() {
        return successViewVisible;
    }

    void setSuccessVisible(boolean visible) {
        this.successViewVisible = visible;
    }

    /**
     * @deprecated Use {@link #( Context context, View view)} instead.
     */
    protected boolean onRetry(Context context, View view) {
        return false;
    }


    /**
     * 当前Callback的点击事件
     * @return  true  覆盖注册时的OnReloadListener, 回调 onReloadListener.onReload(v) 不执行
     *          false 先执行onReloadEvent,后执行onReloadListener.onReload(v)
     */
    protected boolean onReloadEvent(Context context, View view) {
        return false;
    }

    public Callback copy() {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        Object obj = null;
        try {
            oos = new ObjectOutputStream(bao);
            oos.writeObject(this);
            oos.close();
            ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Callback) obj;
    }

    /**
     * @since 1.2.2
     */
    public View obtainRootView() {
        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null);
        }
        return rootView;
    }

    public interface OnReloadListener extends Serializable {
        void onReload(View v);
    }

    protected abstract int onCreateView();

    /**
     * Called immediately after {@link #onCreateView()}
     *
     * @since 1.2.2
     */
    protected void onViewCreate(Context context, View view) {
    }

    /**
     * 将Callback添加到当前视图时的回调，View为当前Callback的布局View
     *
     * @since 1.2.2
     */
    public void onAttach(Context context, View view) {
    }

    /**
     *将Callback从当前视图删除时的回调，View为当前Callback的布局View
     * @since 1.2.2
     */
    public void onDetach() {
    }

    /**
     * 重复显示的时候回调
     */
    public void onDepeatDisplay() {

    }


}
