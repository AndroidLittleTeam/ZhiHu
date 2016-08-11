package com.robert.zhihu.cache;

import rx.Observable;

/**
 * Created by robert on 2016/8/11.
 */

public interface ICache {
    <T> Observable<T> get(String key, Class<T> cls);

    <T> void put(String key, T t);
}
