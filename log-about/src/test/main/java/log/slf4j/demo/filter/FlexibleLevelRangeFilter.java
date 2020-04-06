package log.slf4j.demo.filter;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.PerformanceSensitive;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/4/6 17:25
 */
@Plugin(name = "FlexibleLevelRangeFilter", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE, printObject = true)
@PerformanceSensitive("allocation")
public final class FlexibleLevelRangeFilter extends AbstractFilter {

    @Getter
    private final Level maxLevel;

    @Getter
    private final Level minLevel;

    private static ClassLoader classLoader = FlexibleLevelRangeFilter.class.getClassLoader();

    /**
     * filter factory
     */
    private static FilterFactory filterFactory = new LevelRangeFilterFactory();

    /**
     *  level handler
     */
    private LevelHandler handler;

    private FlexibleLevelRangeFilter(FilterFactory filterFactory,
                                     Level minLevel, Level maxLevel,
                                     Result onMatch, Result onMismatch) {
        super(onMatch, onMismatch);
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        try {
            handler = filterFactory.getHandler(maxLevel, minLevel);
        } catch (Exception e) {
            debug();
        }
    }

    @Override
    public Result filter(LogEvent event) {
        return filter(event.getLevel());
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        return filter(level);
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        return filter(level);
    }

    private Result filter(final Level level) {
        boolean inRange = false;
        try {
            inRange = handler.onMatch(level);
        } catch (Exception e) {
            debug();
        }
        return inRange ? onMatch : onMismatch;
    }

    private static void debug(){}

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("maxLevel", maxLevel)
                .append("minLevel", minLevel)
                .toString();
    }

    /**
     * Create a FlexibleLevelRangeFilter.
     *
     * @param factory
     *            The Level filter bean factory class
     * @param minLevel
     *            The minimum log Level.
     * @param maxLevel
     *            The maximum log Level.
     * @param match
     *            The action to take on a match.
     * @param mismatch
     *            The action to take on a mismatch.
     * @return The created ThresholdFilter.
     */
    @PluginFactory
    public static FlexibleLevelRangeFilter createFilter(
            // @formatter:off
            @PluginAttribute("factory") final String factory,
            @PluginAttribute("minLevel") final Level minLevel,
            @PluginAttribute("maxLevel") final Level maxLevel,
            @PluginAttribute("onMatch") final Result match,
            @PluginAttribute("onMismatch") final Result mismatch) {
        // @formatter:on
        final Level actualMinLevel = minLevel == null ? Level.ERROR : minLevel;
        final Level actualMaxLevel = maxLevel == null ? Level.ERROR : maxLevel;
        final Result onMatch = match == null ? Result.NEUTRAL : match;
        final Result onMismatch = mismatch == null ? Result.DENY : mismatch;
        try {
            if (factory != null && !"".equals(factory)) {
                //Construction
                Class<?> clazz = Class.forName(factory, true, classLoader);
                if (clazz.isAssignableFrom(FilterFactory.class)) {
                    filterFactory = ((FilterFactory) clazz.newInstance());
                }
            }
            filterFactory.setProperties();
        }catch (Exception e) {
            debug();
        }
        return new FlexibleLevelRangeFilter(filterFactory, actualMinLevel, actualMaxLevel, onMatch, onMismatch);
    }
}
