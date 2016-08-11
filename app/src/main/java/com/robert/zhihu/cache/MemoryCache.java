package com.robert.zhihu.cache;

import android.text.TextUtils;
import android.util.LruCache;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by robert on 2016/8/11.
 */

public class MemoryCache implements ICache {

    private LruCache<String, String> mCache;

    public MemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mCache = new LruCache<String, String>(cacheSize) {
            @Override
            protected int sizeOf(String key, String value) {
                try {
                    return value.getBytes("utf-8").length;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return value.getBytes().length;
                }
            }
        };
    }


    @Override
    public <T> Observable<T> get(String key, Class<T> cls) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                String result = mCache.get(key);
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                if (TextUtils.isEmpty(result)) {
                    subscriber.onNext(null);
                } else {
                    T t = new Gson().fromJson(result, cls);
                    subscriber.onNext(t);
                }
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public <T> void put(String key, T t) {
        if (null != t) {
            mCache.put(key, new Gson().toJson(t));
        }
    }

    public void clearCache(String key) {
        mCache.remove(key);
    }

    public void clearAll() {
        mCache.evictAll();
    }
}
