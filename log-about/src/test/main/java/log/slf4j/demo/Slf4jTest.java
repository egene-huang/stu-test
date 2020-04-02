package log.slf4j.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/4/2 14:12
 */
public class Slf4jTest {

    @Test
    public void test() {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.error("hello slf4j log");
    }
}
