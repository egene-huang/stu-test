package test.file;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/8 19:32
 */
public class FileTest {
    public static void main(String[] args)throws Exception {
        Path tempPdf = Files.createTempFile("temp_pdf", ".txt");
        boolean exists = Files.exists(tempPdf);
        System.out.println("path#toString: " + tempPdf.toString());
        System.out.println("path: " + tempPdf);
        System.out.println(tempPdf.getFileName() + ", " + tempPdf.toAbsolutePath().getFileName() + ", " + tempPdf.toFile().getPath());
    }
}
