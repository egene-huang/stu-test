package com.jd.test.file;

import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/8 19:32
 */
public class FileTest {
    @Test
    public void createTemp()throws Exception {
        URL temp = FileTest.class.getResource("temp");
        Path tempPdf = Files.createTempFile(Paths.get(temp.toURI()), "temp_pdf", ".txt");
        boolean exists = Files.exists(tempPdf);
        System.out.println(exists);
    }
}
