package com.jd.test.nio.copy;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/17 18:08
 */
public class CopyFile {

    //from
    static String src = "E:\\projects\\stu-test\\src\\main\\resources\\doc.txt";
    //to
    static String target = "E:\\projects\\stu-test\\src\\main\\resources\\doc_copy.txt";

    @Test
    public void copyFile()throws Exception {
        FileInputStream fi = new FileInputStream(src);
        FileChannel fiChannel = fi.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        FileOutputStream fo = new FileOutputStream(target);
        FileChannel foChannel = fo.getChannel();
        try {
            int read = fiChannel.read(buffer);
            while (read != -1) {
                if (buffer.hasRemaining()) {
                    buffer.put(" =>这是补充内容<=".getBytes());
                }
                //游标设置为包含需要读取的段
                buffer.flip();
                foChannel.write(buffer);
                //读取之前需要准备一个没有空的buffer
                buffer.clear();
                read = fiChannel.read(buffer);
            }
            System.out.println("文件复制完成");
        }catch ( Exception e) {
            e.printStackTrace();
        }finally {
            fi.close();
            fo.close();
            buffer.clear();
            fiChannel.close();
            foChannel.close();
        }
    }

    /**
     * 可以避免编码问题
     * @throws Exception
     */
    @Test
    public void cpUseFiles()throws Exception {
        FileInputStream fi = new FileInputStream(src);
        try {
            long copy = Files.copy(fi, new File(target).toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(String.format("文件复制完成, copy=%s", copy) );
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fi.close();
        }
    }
}
