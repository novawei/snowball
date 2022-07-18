package com.nova.common.security.exception;

import com.nova.common.core.api.ApiCode;
import com.nova.common.core.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (response.isCommitted()) {
            log.trace("Did not write to response since already committed");
            return;
        }

        // Put exception into request scope (perhaps of use to a view)
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.FORBIDDEN.value());
        ApiException apiException = new ApiException(HttpStatus.FORBIDDEN, ApiCode.FORBIDDEN);
        request.setAttribute(ApiException.ERROR_API_EXCEPTION, apiException);
        request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
        // Forward to /error
        request.getRequestDispatcher("/error").forward(request, response);
    }
}
