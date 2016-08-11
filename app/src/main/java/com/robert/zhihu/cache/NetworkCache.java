package com.robert.zhihu.cache;

import rx.Observable;

/**
 * Created by robert on 2016/8/11.
 */

public abstract class NetworkCache<T> {
    public abstract Observable<T> get(String key, final Class<T> cls);
}
