package com.nova.common.security.util;

import com.nova.service.uac.api.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

public class SecurityUtils {
    public static String getLoginUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }

    public static User getLoginUser() {
        String userId = getLoginUserId();
        if (StringUtils.hasText(userId)) {
            User user = CacheUtils.fetchUser(userId);
            return user;
        }
        return null;
    }
}
