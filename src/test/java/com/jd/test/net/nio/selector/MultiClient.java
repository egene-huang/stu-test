package com.jd.test.net.nio.selector;


/**
 * 多线程出现消息混乱
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2019/12/25 15:41
 */
public class MultiClient {
    public static void main(String[] args) {
        /*[Client788]服务器响应: hello, i'm Client791
        [Client791]服务器响应: hello, i'm Client791
        */
        for (int i = 1; i < 1000; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    new Client("Client" + index).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
