package com.nova.common.i18n.util;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * Locale based on HTTP Header 'Accept-Language'
 */
public final class I18nUtils {
    public static String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        MessageSource messageSource = SpringUtil.getBean("messageSource");
        return messageSource.getMessage(code, null, locale);
    }

    public static String getMessage(String code, Object... objects) {
        Locale locale = LocaleContextHolder.getLocale();
        MessageSource messageSource = SpringUtil.getBean("messageSource");
        return messageSource.getMessage(code, objects, locale);
    }

    public static String getMessage(Locale locale, String code) {
        MessageSource messageSource = SpringUtil.getBean("messageSource");
        return messageSource.getMessage(code, null, locale);
    }

    public static String getMessage(Locale locale, String code, Object... objects) {
        MessageSource messageSource = SpringUtil.getBean("messageSource");
        return messageSource.getMessage(code, objects, locale);
    }
}


