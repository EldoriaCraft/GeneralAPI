package de.dermaster.generalAPI.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    private static final Map<Class<? extends IServiceProvider>, IServiceProvider> SERVICE_MAP = new HashMap<>();

    public static void registerService(final Class<? extends IServiceProvider> clazz, IServiceProvider service) {
        SERVICE_MAP.put(clazz, service);
    }

    public static <T> T getService(final Class<? extends IServiceProvider> clazz) {
        return (T) SERVICE_MAP.get(clazz);
    }

}