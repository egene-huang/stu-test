package log.slf4j.demo.filter;

import org.apache.logging.log4j.Level;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/4/6 18:15
 */
public class LevelRangeFilterFactory implements FilterFactory {

    @Override
    public LevelHandler getHandler(Level maxLevel, Level minLevel) {
        return new LevelRangeHandler(maxLevel, minLevel);
    }

    @Override
    public void setProperties() {
        //
    }
}
