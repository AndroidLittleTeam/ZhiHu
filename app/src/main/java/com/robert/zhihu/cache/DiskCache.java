package com.robert.zhihu.cache;

import android.support.annotation.Nullable;
import android.util.Log;

import com.robert.zhihu.App;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by robert on 2016/8/11.
 */

public class DiskCache implements ICache {


    private static final String NAME = ".db";
    public static long OTHER_CACHE_TIME = 10 * 60 * 1000;
    public static long WIFI_CACHE_TIME = 30 * 60 * 1000;
    private final File fileDir;

    public DiskCache() {
        fileDir = App.getApp().getCacheDir();
    }

    @Override
    public <T> Observable<T> get(String key, Class<T> cls) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                T t = (T) getDiskData(key + NAME);
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                if (t == null) {
                    subscriber.onNext(null);
                } else {
                    subscriber.onNext(t);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> void put(String key, T t) {

        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                boolean hasSaved = hasSaved(key + NAME, t);
                if (!subscriber.isUnsubscribed() && hasSaved) {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private <T> boolean hasSaved(String fileName, T t) {
        File file = new File(fileDir, fileName);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.flush();
            return true;
        } catch (FileNotFoundException e) {
            Log.e(this.getClass().getSimpleName(), "getDiskData: " + e.getMessage());
        } catch (IOException e) {
            Log.e(this.getClass().getSimpleName(), "getDiskData: " + e.getMessage());
        } finally {
            closeSilently(oos);
            closeSilently(fos);
        }
        return false;
    }


    @Nullable
    private Object getDiskData(String key) {

        File file = new File(fileDir, key);
        if (!file.exists()) return null;
        if (file.isDirectory()) return null;
        if (isCacheOver(file)) return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (IOException e) {
            Log.e(this.getClass().getSimpleName(), "getDiskData: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(this.getClass().getSimpleName(), "getDiskData: " + e.getMessage());
        } finally {
            closeSilently(ois);
            closeSilently(fis);
        }
        return null;
    }

    private boolean isCacheOver(File file) {
        if (file.exists()) return true;
        long existTime = System.currentTimeMillis() - file.lastModified();
        if (!App.ExtImpl.g().isAvailable()) return false;
        if (App.ExtImpl.g().isWifi()) {
            return existTime > WIFI_CACHE_TIME;
        } else {
            return existTime > OTHER_CACHE_TIME;
        }
    }


    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearCache(String key) {
        File file = new File(fileDir, key + NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
