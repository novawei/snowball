package com.nova.common.core.util;

import lombok.SneakyThrows;

public final class BeanUtils extends org.springframework.beans.BeanUtils {
    @SneakyThrows
    public static <T> T convert(Object source, Class<T> clazz){
        if (source == null) {
            return null;
        }
        T target = clazz.newInstance();
        copyProperties(source, target);
        return target;
    }
}
