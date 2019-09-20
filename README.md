


### 添加依赖

```groovy
compile 'xxxxx'
```

### 第一步：配置

###### 全局配置方式
全局配置方式，使用的是单例模式，即获取的配置都是一样的。可在Application中配置，添加状态页，设置默认状态页

```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new DefEmptyCallBack())//添加各种状态页
                .addCallback(new DefHintCallback())  
                .addCallback(new DefLoadingCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }
}
```
###### 使用(三种场景)
1.使用上面全局配置过的 LoadSir

```java
   LoadService loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
        @Override
        public void onReload(View v) {
            // 重新加载逻辑
        }
    })  
    .setCallBack(EmptyCallback.class, new Transport() {
                @Override
                public void order(Context context, View view) {
                    TextView mTvEmpty = (TextView) view.findViewById(R.id.tv_empty);
                    mTvEmpty.setText("动态修改Callback的内容");
                }
            });
```

2.创建一个全新的LoadSir(不适用全局配置)

```java
LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new ErrorCallback())
                .build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // 重新加载逻辑
            }
        });
```

3.如果你即想保留全局配置，又想在某个特殊页面加点不同的配置 

```java
LoadSir loadSir = new LoadSir.getDefault()
                .cloneBuilder()  //克隆一个全局配置的副本
                .addCallback(new CustomCallBack())
                .build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // 重新加载逻辑
            }
        });
```
## 代码混淆

```xml
-dontwarn com.kingja.loadsir.**
-keep class com.kingja.loadsir.** {*;}
```











