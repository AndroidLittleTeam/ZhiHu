package com.robert.zhihu.contract;

import com.robert.zhihu.base.IPresenter;
import com.robert.zhihu.base.IView;

/**
 * Created by robert on 2016/8/30.
 */

public class WebContract {

    public interface View extends IView{

    }

    public interface Presenter extends IPresenter<View>{

    }
}
