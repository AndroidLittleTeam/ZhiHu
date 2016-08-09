package com.robert.zhihu;

import android.app.Application;

import com.robert.zhihu.injector.component.AppComponent;
import com.robert.zhihu.injector.component.DaggerAppComponent;
import com.robert.zhihu.injector.module.AppModule;

/**
 * Created by robert on 2016/8/1.
 */

public class App extends Application {
    private static App app;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getApp() {
        return app;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
