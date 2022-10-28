package me.zz.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qingzhi
 * @date 2022/10/28 16:38
 */
public class LoggerFactory {
    private static Map<Class, Logger> loggerMap = new HashMap<>();

    public static <T> Logger getLogger(Class<T> clz) {
        if (loggerMap.containsKey(clz)) {
            return loggerMap.get(clz);
        }

        Logger logger = new Logger(clz);
        loggerMap.put(clz, logger);
        return logger;
    }
}
