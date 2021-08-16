package com.liufuyi.common;

import java.util.ServiceLoader;

public final class ServiceLoaderUtil {

    private ServiceLoaderUtil() {

    }

    public static <T> T load(Class<T> clazz) {
        try {
            return ServiceLoader.load(clazz).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }

}
