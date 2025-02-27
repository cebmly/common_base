package com.feb.net.exception;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HttpError extends RuntimeException {
    private static final long serialVersionUID = -134024482758434333L;

    /**
     * 展示在前端的错误信息
     */
    @NonNull
    public String msg;

    /**
     * 请求失败保存失败信息
     * 1.original json:原始的json
     * 2.Throwable:抛出的异常信息，类似{@link retrofit2.HttpException}
     * 3.自定义的一些对象
     */
    @Nullable
    public final transient Object body;

    public HttpError(String message) {
        this(message, null);
    }

    public HttpError(String message, @Nullable Object body) {
        super(message);
        if (body instanceof Throwable) {
            initCause((Throwable) body);
        }
        this.msg = message != null ? msg : "null";
        this.body = body;
    }

    /**
     * {@link okhttp3.Request#tag(Class)}
     * {@link android.os.Bundle#getInt(String)}
     * 转换body为想要的类型，如果类型不匹配，返回null
     * @param <T>
     * @return
     */
    @Nullable
    public <T> T castBody() {
        try {
            return (T) body;
        }catch (ClassCastException e) {
            return null;
        }
    }

    @Nullable
    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public String toString() {
        return "HttpError {msg="
                + msg
                + ", body="
                + body
                + '}';
    }
}
