package com.robert.zhihu.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by robert on 2016/8/8.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}
