package com.trcklst.subscription.ws.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.trcklst.subscription.ws.common.exceptions.InvalidTokenException;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static Integer getUserIdFromHeader() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return Integer.valueOf(request.getHeader("userId"));
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
}
