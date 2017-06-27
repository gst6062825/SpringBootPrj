package com.flow.util;

import javax.servlet.http.Cookie;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by gaosh on 2017/6/26.
 */
public class Utils {

    /**
     * 获取cookie值
     */
    public static String getCookieValue(String key, Cookie[] arrCookie) {
        if (StringUtils.isBlank(key) || null == arrCookie || arrCookie.length == 0) {
            return null;
        }
        for (Cookie cookie : arrCookie) {
            if (key.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    /**
     * 获取uid
     */
    public static Integer getUid(Integer headerUid, Cookie[] arrCookie) {
        Integer result = null;
        if (null == headerUid) {
            String cuid = Utils.getCookieValue("uid", arrCookie);
            if (StringUtils.isBlank(cuid)) {
                result = 0;
            } else {
                result = Integer.parseInt(cuid);
            }
        }
        return result;
    }
}
