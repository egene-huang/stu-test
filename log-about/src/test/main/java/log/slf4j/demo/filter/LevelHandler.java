package log.slf4j.demo.filter;

import org.apache.logging.log4j.Level;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/4/6 18:06
 */
public interface LevelHandler {

    /**
     * 返回true输出日志
     * @param level
     * @return
     */
    boolean onMatch(final Level level);
}
