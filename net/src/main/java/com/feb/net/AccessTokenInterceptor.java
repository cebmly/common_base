package com.feb.net;

import android.os.Build;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.feb.lib_utils.NullHashMap;
import com.feb.lib_utils.SpUtils;
import com.feb.lib_utils.SystemUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor  implements Interceptor {

    public AccessTokenInterceptor() {

    }
    @NotNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        NullHashMap<String, String> headers = new NullHashMap<>(SystemUtils.getSystemParams());
        Request request = chain.request();
        long timestamp = System.currentTimeMillis() / 1000;
        headers.put("token", SpUtils.getToken());
        headers.put("Authorization","Bearer " + SpUtils.getToken());
        headers.put("Eve-Payload",
                "deviceId="+ DeviceUtils.getUniqueDeviceId()+"&"+"channel=android_default&appVersion=" + AppUtils.getAppVersionName() +"&platform="
                + "1&bundleId="+AppUtils.getAppIconId()  + "&osVersion=" + Build.VERSION.SDK_INT + "&lang=" + Locale.getDefault().getLanguage() + "&region="
                        + Locale.getDefault().getISO3Country() + "&locale=" + Locale.getDefault().getLanguage()
        );

        Request.Builder builder = request.newBuilder();
        HashMap<String, String> params = new HashMap<>();
        params.put("timestamp", String.valueOf(timestamp));
        if (request.body() instanceof FormBody) {
            FormBody formBody = (FormBody) request.body();
            if (formBody != null && formBody.size() > 0) {
                for (int i = 0; i < formBody.size(); i++) {
                    params.put(formBody.name(i), formBody.value(i));
                }
            }
        }
        HttpUrl url = request.url();

        if (url.querySize() > 0) {
            for (int i = 0; i < url.querySize(); i++) {
                params.put(url.queryParameterName(i), url.queryParameterValue(i));
            }
        }


        Request newRequest = builder.headers(Headers.of(headers)).url(request.url().newBuilder().addQueryParameter("timestamp", String.valueOf(timestamp)).build()).build();
        return chain.proceed(newRequest);
    }


}
