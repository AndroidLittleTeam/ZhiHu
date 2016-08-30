package com.robert.zhihu.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.robert.zhihu.R;
import com.robert.zhihu.base.BaseActivity;
import com.robert.zhihu.contract.WebContract;
import com.robert.zhihu.presenter.WebPresenter;

import butterknife.BindView;

public class WebActivity extends BaseActivity<WebPresenter> implements WebContract.View {

    private String url;
    private String title;

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.pb_web)
    ProgressBar pbWeb;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initEventAndData() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        setCommonBackToolbar(mToolbar, title);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pbWeb.setProgress(newProgress);
                if (newProgress == 100) {
                    pbWeb.setVisibility(View.GONE);
                } else {
                    pbWeb.setVisibility(View.VISIBLE);
                }

                super.onProgressChanged(view, newProgress);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (TextUtils.isEmpty(url)) {
                    view.loadUrl(url);
                }
                return true;
            }
        });

        mWebView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }

        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

}
