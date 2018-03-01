package sproject.util;

import org.apache.commons.lang3.StringUtils;

public class LevelUtil {
    public final static String SEPARATOR = ".";
    public final static String ROOT = "0";

    /**
     * 构造层次字符串，如0.1.3
     * @param parentLevel
     * @param parentId
     * @return
     */
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        }else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}
