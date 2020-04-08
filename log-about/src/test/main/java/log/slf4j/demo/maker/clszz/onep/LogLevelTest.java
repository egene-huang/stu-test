package log.slf4j.demo.maker.clszz.onep;

import lombok.Data;
import org.apache.logging.log4j.message.ObjectMessage;
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
        //危险级别越高 值越小
        Marker test = MarkerFactory.getMarker("Test");
        Class<? extends LogLevelTest> aClass = getClass();
        Logger logger = LoggerFactory.getLogger(aClass);
        logger.info("info => hello," + aClass.getName());
        logger.warn("warn => hello," + aClass.getName());
        logger.error("erro => hello," + aClass.getName());
//        logger.error(marker, "error => hello, " + aClass.getName());
//        Marker marker = MarkerFactory.getMarker("Marker测试:");
//        logger.error(marker, "marker-error => hello," + aClass.getName());
        logger.debug("debug => hello," + aClass.getName());
        logger.error(test, "objmsg: {}", new ObjectMessage(new Person("测试", 25)));
        System.out.println("--------------------------------------------------------");
//        Configurator.setLevel(aClass.getName(), Level.DEBUG);
//        Configurator.setRootLevel(Level.DEBUG);
    }

    @Data
    static class Person {
        private String name;
        private int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
