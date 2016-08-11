package com.robert.zhihu;

import android.app.Application;

import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.robert.zhihu.injector.component.AppComponent;
import com.robert.zhihu.injector.component.DaggerAppComponent;
import com.robert.zhihu.injector.module.AppModule;
import com.wkw.common_lib.Ext;
import com.wkw.common_lib.network.Network;
import com.wkw.common_lib.utils.ViewUtils;

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
        if (BuildConfig.DEBUG) {
            AndroidDevMetrics.initWith(this);
        }
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        initExtension();
    }

    public static App getApp() {
        return app;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    private void initExtension() {
        Ext.init(this, new ExtImpl());
    }

    public static final class ExtImpl extends Ext {

        @Override
        public String getCurOpenId() {
            return null;
        }

        @Override
        public String getDeviceInfo() {
            return null;
        }

        @Override
        public String getPackageNameForResource() {
            return "com.wkw.hot";
        }

        @Override
        public int getScreenHeight() {
            return ViewUtils.getScreenHeight();
        }

        @Override
        public int getScreenWidth() {
            return ViewUtils.getScreenWidth();
        }

        @Override
        public boolean isAvailable() {
            return Network.isAvailable();
        }

        @Override
        public boolean isWap() {
            return Network.isWap();
        }

        @Override
        public boolean isMobile() {
            return Network.isMobile();
        }

        @Override
        public boolean is2G() {
            return Network.is2G();
        }

        @Override
        public boolean is3G() {
            return Network.is3G();
        }

        @Override
        public boolean isWifi() {
            return Network.isWifi();
        }

        @Override
        public boolean isEthernet() {
            return Network.isEthernet();
        }
    }
}
