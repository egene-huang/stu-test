package log.slf4j.demo.filter;

import lombok.Getter;
import org.apache.logging.log4j.Level;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/4/6 18:12
 */
public class LevelRangeHandler implements LevelHandler {

    @Getter
    private final Level maxLevel;

    @Getter
    private final Level minLevel;

    public LevelRangeHandler(Level maxLevel, Level minLevel) {
        this.maxLevel = maxLevel;
        this.minLevel = minLevel;
    }

    @Override
    public boolean onMatch(Level level) {
        boolean inRange = level.isInRange(this.minLevel, this.maxLevel);
        return inRange;
    }
}
