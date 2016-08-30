package com.robert.zhihu.cache;

import android.util.Log;

import rx.Observable;

/**
 * Created by robert on 2016/8/11.
 */

public class CacheLoader {

    private static final String TAG = "CacheLoader";
    private static CacheLoader mCacheLoader;
    private final MemoryCache mMemoryCache;
    private final DiskCache mDiskCache;

    private CacheLoader() {
        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache();
    }

    public static CacheLoader getInstance() {
        if (mCacheLoader == null) {
            synchronized (CacheLoader.class) {
                if (mCacheLoader == null) {
                    mCacheLoader = new CacheLoader();
                }
            }
        }
        return mCacheLoader;
    }

    public <T> Observable<T> asDataObservable(String key, Class<T> cls, NetworkCache<T> networkCache) {
        return Observable.concat(
                memory(key, cls),
                disk(key, cls),
                network(key, cls, networkCache))
                .first(t -> t != null);
    }

    public <T> void upNewData(String key, T t) {
        Observable.just(t).doOnNext(t1 -> {
            if (null != t) {
                mDiskCache.put(key, t);
                mMemoryCache.put(key, t);
            }
        });
    }

    public void ClearMemory(String key) {
        mMemoryCache.clearCache(key);
    }

    public void clearMemoryDisk(String key) {
        mMemoryCache.clearCache(key);
        mDiskCache.clearCache(key);
    }


    private <T> Observable<T> network(String key, Class<T> cls, NetworkCache<T> networkCache) {

        return networkCache.get(key, cls)
                .doOnNext(t -> {
                    if (null != t) {
                        mMemoryCache.put(key, t);
                        mDiskCache.put(key, t);
                        Log.d(TAG, "from network");
                    }
                });
    }

    private <T> Observable<T> disk(String key, Class<T> cls) {
        return mDiskCache.get(key, cls)
                .doOnNext(t -> {
                    if (t != null) {
                        mMemoryCache.put(key, t);
                        Log.d(TAG, "from disk");
                    }
                });
    }

    private <T> Observable<T> memory(String key, Class<T> cls) {
        return mMemoryCache.get(key, cls)
                .doOnNext(t -> {
                    if (null != t) {
                        Log.d(TAG, "from memory");
                    }
                });
    }
}
