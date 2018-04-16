package com.app.org.base;

import android.app.Application;
//import android.support.multidex.MultiDexApplication;

import com.app.org.ApplicationDelegate;
import com.app.org.BuildConfig;
import com.github.anzewei.parallaxbacklayout.ParallaxHelper;
import com.orhanobut.logger.LogLevel;

import com.app.org.utils.BaseClassUtil;
import com.app.org.utils.BaseLogUtil;
import com.app.org.utils.BaseUtils;

import java.util.List;

/**
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 * 组件中获取Context的方法必须为:Utils.getContext()，不允许其他写法；
 *
 * @author 2016/12/2 17:02
 * @version V1.0.0
 * @name BaseApplication
 */
public class BaseApplication extends Application {

    public String ROOT_PACKAGE = "com.qqkjbasepro.org";

    private static BaseApplication sInstance;

    private List<ApplicationDelegate> mAppDelegateList;


    public static BaseApplication getIns() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        BaseLogUtil.init(getApplicationContext(), BuildConfig.DEBUG,false);
        BaseUtils.init(this);

        registerActivityLifecycleCallbacks(ParallaxHelper.getInstance());

        mAppDelegateList = BaseClassUtil.getObjectsWithInterface(this, ApplicationDelegate.class, ROOT_PACKAGE);
        for (ApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (ApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTerminate();
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (ApplicationDelegate delegate : mAppDelegateList) {
            delegate.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (ApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTrimMemory(level);
        }
    }
}
