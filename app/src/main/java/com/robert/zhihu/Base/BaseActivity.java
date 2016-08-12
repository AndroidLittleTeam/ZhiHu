package com.robert.zhihu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.orhanobut.logger.Logger;
import com.robert.zhihu.App;
import com.robert.zhihu.injector.component.ActivityComponent;
import com.robert.zhihu.injector.component.AppComponent;
import com.robert.zhihu.injector.component.DaggerActivityComponent;
import com.robert.zhihu.injector.module.ActivityModule;
import com.robert.zhihu.utils.PermissionUtils;
import com.wkw.common_lib.utils.AppManager;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by robert on 2016/8/8.
 */

public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements IView {

    public static final String TAG = "BaseActivity";
    protected ActivityComponent mActivityComponent;
    protected Context mContext;
    @Inject
    protected T mIPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppComponent appComponent = App.getAppComponent();
        mActivityComponent = DaggerActivityComponent
                .builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build();
        initInjector();
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (mIPresenter != null) mIPresenter.attachView(this);
        initEventAndData();
        AppManager.getAppManager().addActivity(this);
    }

    protected abstract void initInjector();

    protected abstract void initEventAndData();

    protected abstract int getLayoutId();


    protected void setCommonBackToolbar(@NonNull Toolbar toolbar, @NonNull String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    protected void setCommonBackToolbar(@NonNull Toolbar toolbar, @StringRes int resId) {
        String title = mContext.getResources().getText(resId).toString();
        this.setCommonBackToolbar(toolbar, title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
        if (mIPresenter != null) {
            mIPresenter.detachView();
        } else {
            Logger.d(TAG, "this activity " + this.getClass().getSimpleName() + "  没有设置 IPresenter!!!");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mIPresenter != null) {
            PermissionUtils.permissionResult(mIPresenter, permissions, grantResults, requestCode);
        }
    }
}
