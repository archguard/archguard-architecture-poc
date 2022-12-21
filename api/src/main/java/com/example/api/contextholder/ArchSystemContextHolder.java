package com.example.api.contextholder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArchSystemContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static String getContext() {
        return contextHolder.get();
    }

    public static void setContext(String context) {
        contextHolder.set(context);
    }

    public static void removeContext() {
        contextHolder.remove();
    }

}
