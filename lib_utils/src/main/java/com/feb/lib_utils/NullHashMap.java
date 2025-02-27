package com.feb.lib_utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class NullHashMap<V, K> extends HashMap<String, String> {

    public NullHashMap(@NonNull Map<? extends String, ? extends String> m) {
        super(m);
    }

    public NullHashMap() {
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (value == null) {
            value = "";
        }
        return super.put(key, value);
    }
}
