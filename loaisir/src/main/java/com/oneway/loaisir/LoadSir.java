package com.oneway.loaisir;


import androidx.annotation.NonNull;
import com.oneway.loaisir.core.Convertor;
import com.oneway.loaisir.core.LoadService;
import com.oneway.loaisir.core.TargetContext;
import com.oneway.loaisir.utils.LoadSirUtil;
import com.oneway.loaisir.callback.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 oneway on 2019/9/20
 * 描述: LoadSir
 * 参考链接:  https://github.com/KingJA/LoadSir/blob/master/README-cn.md
 */
public class LoadSir {
    private static volatile LoadSir loadSir;
    private Builder builder;

    public static LoadSir getDefault() {
        if (loadSir == null) {
            synchronized (LoadSir.class) {
                if (loadSir == null) {
                    loadSir = new LoadSir();
                }
            }
        }
        return loadSir;
    }

    private LoadSir() {
        this.builder = new Builder();
    }

    private LoadSir(Builder builder) {
        this.builder = builder;
    }

    private LoadSir setBuilder(@NonNull Builder builder) {
        this.builder = builder;
        return this;
    }

    /**
     * 创建一个 保留全局配置 的 Builder
     */
    public Builder cloneBuilder() {
        return builder.cloneBuilder();
    }

    public static Builder beginBuilder() {
        return new Builder();
    }

    public LoadService register(@NonNull Object target) {
        return register(target, null, null);
    }

    public LoadService register(Object target, Callback.OnReloadListener onReloadListener) {
        return register(target, onReloadListener, null);
    }

    public <T> LoadService register(Object target, Callback.OnReloadListener onReloadListener, Convertor<T>
            convertor) {
        TargetContext targetContext = LoadSirUtil.getTargetContext(target);
        return new LoadService<>(convertor, targetContext, onReloadListener, builder);
    }

    public int getCount() {
        return builder.getCount();
    }


    public static class Builder {
        private List<Callback> callbacks = new ArrayList<>();
        private Class<? extends Callback> defaultCallback;

        public int getCount() {
            return callbacks.size();
        }

        public Builder addCallback(@NonNull Callback callback) {
            this.callbacks.add(callback);
            return this;
        }

        public Builder addCallbacks(@NonNull List<Callback> infos) {
            for (Callback callback : infos) {
                addCallback(callback);
            }
            return this;
        }

        public Builder setDefaultCallback(@NonNull Class<? extends Callback> defaultCallback) {
            this.defaultCallback = defaultCallback;
            return this;
        }

        public List<Callback> getCallbacks() {
            return callbacks;
        }

        public Class<? extends Callback> getDefaultCallback() {
            return defaultCallback;
        }

        public LoadSir commit() {
            return getDefault().setBuilder(this);
        }

        public LoadSir build() {
            return new LoadSir(this);
        }

        public Builder cloneBuilder() {
            Builder newBuilder = new Builder();
            newBuilder.setDefaultCallback(defaultCallback);
            newBuilder.addCallbacks(callbacks);
            return newBuilder;
        }

    }
}
