package com.feb.net;

import androidx.annotation.NonNull;

import com.feb.lib_utils.LoggerUtils;
import com.feb.lib_utils.UtilConfig;
import com.feb.net.adapter.LifecycleCallAdapterFactory;
import com.feb.net.adapter.LiveDataCallAdapterFactory;
import com.feb.net.converter.FastJsonConverterFactory;
import com.feb.net.testlivedata.CustomDataCallAdapterFactory;
import com.franmontiel.persistentcookiejar.BuildConfig;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class RetrofitManager {

    private static String mBaseUrl = "http://api.borders-dev.com/duome/api/v1";
    public static int DEFAULT_TIME_OUT = 60;
    //用于存储retrofit
    private static ConcurrentHashMap<String, Retrofit> retrofitMap;
    private static OkHttpClient.Builder okhttpBuilder;

    static {
        SetCookieCache cookieCache = new SetCookieCache();
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(cookieCache, new SharedPrefsCookiePersistor(UtilConfig.getContext()));
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                if (BuildConfig.DEBUG) {
                    LoggerUtils.e("网路请求", message);
                }
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofitMap = new ConcurrentHashMap<>();
        okhttpBuilder = new OkHttpClient.Builder();
        okhttpBuilder
                .addInterceptor(new AccessTokenInterceptor())
                .addInterceptor(interceptor)
                .cookieJar(cookieJar)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        ;
    }

    public static void init(String baseUrl) {
        mBaseUrl = baseUrl;
        Retrofit defaultRetrofit = retrofitMap.get(baseUrl);
        if (defaultRetrofit == null) {
            defaultRetrofit = createRetrofit(baseUrl);
            retrofitMap.put(baseUrl, defaultRetrofit);
        }
    }

    private static Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okhttpBuilder.build())
                .addCallAdapterFactory(LifecycleCallAdapterFactory.INSTANCE)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addCallAdapterFactory(new CustomDataCallAdapterFactory())
                .addConverterFactory(FastJsonConverterFactory.Companion.create())
                .build();
    }
}
