package log.slf4j.demo.maker.clszz.thrp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/4/2 15:17
 */
public class CompareLogTest {
    @Test
    public void test() {
        Class<? extends CompareLogTest> aClass = getClass();
        Logger logger = LoggerFactory.getLogger(aClass);
        if (logger.isErrorEnabled()) {
            logger.error("compare with test hello logger");
        }
        if (logger.isInfoEnabled()) {
            logger.info("compare with test hello logger");
        }
        if (logger.isWarnEnabled()) {
            logger.warn("compare with test hello logger");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("compare with test hello logger");
        }
        if (logger.isTraceEnabled()) {
            logger.trace("compare with test hello logger");
        }
    }
}
