package log.slf4j.demo.filter;

import org.apache.logging.log4j.Level;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/4/6 18:09
 */
public interface FilterFactory {

    /**
     * 获取日志过滤控制器
     * @param maxLevel
     * @param minLevel
     * @return
     */
    LevelHandler getHandler(Level maxLevel, Level minLevel);

    /**
     * same as InitalizeBean
     */
    void setProperties();
}
