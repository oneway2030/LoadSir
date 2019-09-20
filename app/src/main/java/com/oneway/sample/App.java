package com.oneway.sample;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import com.oneway.loaisir.LoadSir;
import com.oneway.loaisir.callback.DefEmptyCallBack;
import com.oneway.loaisir.callback.DefHintCallback;
import com.oneway.loaisir.callback.DefLoadingCallback;
import com.oneway.loaisir.callback.SuccessCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.*;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class App extends Application {
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.text);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header

            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化 状态加载器
        DefLoadingCallback loadingCallback = new DefLoadingCallback.Builder()
                .setTitle("加载中....")
                .build();
        DefHintCallback errorCallback = new DefHintCallback.Builder()
                .setTitle("网络错误")
                .setHintImg(R.mipmap.status_net_error)
                .build();
        DefEmptyCallBack emptyCallBack = new DefEmptyCallBack(R.string.fine_no_data, R.mipmap.status_empty);
        LoadSir.beginBuilder()
                .addCallback(emptyCallBack)
                .addCallback(errorCallback)
                .addCallback(loadingCallback)
                .setDefaultCallback(SuccessCallback.class)
                .commit();
    }
}
