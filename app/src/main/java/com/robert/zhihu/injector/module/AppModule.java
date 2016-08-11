package com.robert.zhihu.injector.module;

import android.content.Context;

import com.robert.zhihu.App;
import com.robert.zhihu.cache.CacheLoader;
import com.robert.zhihu.common.Constants;
import com.robert.zhihu.data.api.HotApi;
import com.robert.zhihu.injector.scope.ContextLife;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robert on 2016/8/1.
 */
@Module
public class AppModule {

    private App mApp;

    public AppModule(App app) {
        this.mApp = app;
    }

    @ContextLife()
    @Provides
    @Singleton
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor apiKey = chain -> chain.proceed(chain.request().newBuilder().addHeader("apikey", Constants.Api_Key).build());

        return new OkHttpClient.Builder()
                .readTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(apiKey)
                .build();
    }

    @Provides
    @Singleton
    public HotApi provideHotApi(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.Base_Url)
                .build().create(HotApi.class);
    }

    @Provides
    @Singleton
    public CacheLoader provideCacheLoader() {
        return CacheLoader.getInstance();
    }

}
