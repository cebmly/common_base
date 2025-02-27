package com.feb.lib_common.base;

import android.app.Application;
import android.net.http.HttpResponseCache;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.BuildConfig;
import com.blankj.utilcode.util.ProcessUtils;
import com.blankj.utilcode.util.Utils;
import com.feb.lib_utils.UtilConfig;
import com.feb.net.RetrofitManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.io.File;
import java.io.IOException;



public class BaseApplication extends Application implements ViewModelStoreOwner {

    private ViewModelStore mViewModelStore;
    private static BaseApplication mInstance;

    public static BaseApplication getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Utils.init(this);
//        AutoSizeConfig.getInstance().setCustomFragment(true);
//        AutoSizeConfig.getInstance().setExcludeFontScale(true);
        mViewModelStore = new ViewModelStore();
        initARouter();
        if (ProcessUtils.isMainProcess()) {
            initLog();
            UtilConfig.init(this);
            RetrofitManager.init("http://api.borders-dev.com/duome/api/");
            initCache();
        }
    }

    private void initLog() {
        //测试模式debug
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(3)         // 控制日志超链接行数
                .tag("日志")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

    }

    private void initCache() {
        File cacheDir = new File(getCacheDir(), "http");
        try {
            HttpResponseCache.install(cacheDir, 1024 * 1024 * 128);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
        // 手动加载 module_dynamic 路由表
    }



    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return null;
    }
}
