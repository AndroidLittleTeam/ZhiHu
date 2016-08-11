package com.robert.zhihu.data;

import com.robert.zhihu.cache.CacheLoader;
import com.robert.zhihu.cache.NetworkCache;
import com.robert.zhihu.common.Constants;
import com.robert.zhihu.data.api.HotApi;
import com.robert.zhihu.entity.ApiResponse;
import com.robert.zhihu.entity.ListPopular;
import com.robert.zhihu.entity.Popular;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by robert on 2016/8/11.
 */

public class DataManager {

    private HotApi mHotApi;
    private CacheLoader mCacheLoader;

    @Inject
    public DataManager(HotApi hotApi, CacheLoader cacheLoader) {
        this.mHotApi = hotApi;
        this.mCacheLoader = cacheLoader;
    }

    /***
     * 获取分类的类型
     */
    public List<String> getTabs() {
        List<String> tabs = new ArrayList<>();
        tabs.add("科技");
        tabs.add("美女");
        tabs.add("生活");
        tabs.add("娱乐");
        tabs.add("搞笑");
        tabs.add("宅男");
        return tabs;
    }

    public Observable<List<Popular>> getPopular(int pageNum, String type) {
        return mHotApi.getPopular(pageNum, Constants.PAGE_SIZE, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<ApiResponse<List<Popular>>, Observable<List<Popular>>>() {
                    @Override
                    public Observable<List<Popular>> call(ApiResponse<List<Popular>> listApiResponse) {
                        if (listApiResponse == null) return Observable.empty();
                        List<Popular> populars = listApiResponse.getNewsList();
                        return Observable.create(subscriber -> {
                            try {
                                subscriber.onNext(populars);
                                subscriber.onCompleted();
                            } catch (Exception e) {
                                subscriber.onError(e);
                            }
                        });
                    }
                }).doOnNext(populars -> {
                    if (pageNum == 1) {
                        ListPopular listPopular = new ListPopular(populars);
                        mCacheLoader.upNewData(type, listPopular);
                    }
                });
    }

    public Observable<List<Popular>> getCachePopular(String type) {
        NetworkCache<ListPopular> networkCache = new NetworkCache<ListPopular>() {
            @Override
            public Observable<ListPopular> get(String key, Class<ListPopular> cls) {
                return getPopular(1, type)
                        .flatMap(populars -> Observable.create((Observable.OnSubscribe<ListPopular>) subscriber -> {
                            if (subscriber.isUnsubscribed()) return;
                            subscriber.onNext(new ListPopular(populars));
                            subscriber.onCompleted();
                        }));
            }
        };
        return mCacheLoader.asDataObservable(Constants.NEW_LIST + type, ListPopular.class, networkCache)
                .map(listPopular -> listPopular.data);
    }


}
