package com.doucome.corner.service.web.common.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * 类CookieHelper.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-3-19 上午01:48:52
 */
public class CookieHelper {

    public static final String COOKIE_PATH = "/";

    public static void writeCookie(HttpServletResponse response, String domain, String name, String value,int exptime) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(exptime);
        response.addCookie(cookie);
    }

    public static String readCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        return readCookie(cookies, name);
    }

    public static String readCookie(Cookie[] cookies, String name) {
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
