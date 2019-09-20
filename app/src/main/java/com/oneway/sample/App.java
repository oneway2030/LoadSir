package com.oneway.sample;

import android.app.Application;
import com.oneway.loaisir.callback.DefEmptyCallBack;
import com.oneway.loaisir.callback.DefHintCallback;
import com.oneway.loaisir.callback.DefLoadingCallback;
import com.oneway.loaisir.callback.SuccessCallback;
import com.oneway.loaisir.LoadSir;

public class App extends Application {

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
        DefEmptyCallBack emptyCallBack = new DefEmptyCallBack(R.string.fine_no_data, R.mipmap.status_empty, true);
        LoadSir.beginBuilder()
                .addCallback(emptyCallBack)
                .addCallback(errorCallback)
                .addCallback(loadingCallback)
                .setDefaultCallback(SuccessCallback.class)
                .commit();
    }
}
