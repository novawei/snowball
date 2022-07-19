package com.nova.common.security.util;

import com.nova.service.uac.api.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public static List<GrantedAuthority> toGrantedAuthorities(Collection<String> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities.size());
		for (String authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
		return grantedAuthorities;
    }
}
