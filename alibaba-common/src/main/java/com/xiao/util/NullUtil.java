package com.xiao.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * @author xiao
 */
public class NullUtil {

    public static boolean hasBlank(String... params) {
        for (String param : params) {
            if (StringUtils.isBlank(param)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNull(Object... params) {
        for (Object param : params) {
            if (param == null) {
                return true;
            }
        }
        return false;
    }
}
