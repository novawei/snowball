package com.nova.common.security.exception;

import com.nova.common.core.api.ApiCode;
import com.nova.common.core.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public SecurityAuthenticationEntryPoint() {
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException, IOException {
        // Put exception into request scope (perhaps of use to a view)
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED.value());
        ApiException apiException = new ApiException(HttpStatus.UNAUTHORIZED, ApiCode.UNAUTHORIZED);
        request.setAttribute(ApiException.ERROR_API_EXCEPTION, apiException);
        // Forward to /error
        request.getRequestDispatcher("/error").forward(request, response);
    }
}
