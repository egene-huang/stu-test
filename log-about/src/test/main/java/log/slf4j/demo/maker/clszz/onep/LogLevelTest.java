package log.slf4j.demo.maker.clszz.onep;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/4/2 15:07
 */
public class LogLevelTest {

    @Test
    public void levelTest()throws Exception {
        System.setProperty("type", "25");
//        System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        //DEBUG < INFO < WARN < ERROR < FATAL
        Class<? extends LogLevelTest> aClass = getClass();
        Logger logger = LoggerFactory.getLogger(aClass);
        logger.info("info => hello," + aClass.getName());
        logger.warn("warn => hello," + aClass.getName());
        logger.error("erro => hello," + aClass.getName());
//        logger.error(marker, "error => hello, " + aClass.getName());
        Marker marker = MarkerFactory.getMarker("Marker测试:");
        logger.error(marker, "error => hello," + aClass.getName());
        logger.debug("debug => hello," + aClass.getName());
        System.out.println("--------------------------------------------------------");
//        Configurator.setLevel(aClass.getName(), Level.DEBUG);
        Configurator.setRootLevel(Level.DEBUG);
        /*while (true){
            logger.info("changed::info => hello," + aClass.getName());
            logger.warn("changed::warn => hello," + aClass.getName());
            logger.error("changed::erro => hello," + aClass.getName());
            logger.debug("changed::debug => hello," + aClass.getName());
        }*/
    }
}
