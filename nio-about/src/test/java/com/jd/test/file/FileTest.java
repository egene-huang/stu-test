package com.jd.test.file;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
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


    @Test
    public void test() throws Exception {
        /*File file = File.createTempFile("activity_huangyijun6_759440_1_1", ".xlsx");
        System.out.println("fileName: " + file.getName());
        Files.deleteIfExists(file.toPath());*/
//        File file = new File("xxx/xxx/xx.xlsx");
//        FileOutputStream outputStream = new FileOutputStream(file);
//        System.out.println("文件创建成功， name: " + file.getName());
        String str = "文件创建成功";
        String newstr = new String(str.getBytes("utf-8"), "utf-8");
        System.out.println("中文: " + newstr);
    }
}
