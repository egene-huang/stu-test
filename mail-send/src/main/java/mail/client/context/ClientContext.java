package mail.client.context;

import java.util.Properties;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/1/12 16:38
 */
public interface ClientContext {

    /**
     * 邮箱服务器地址
     */
    String C_HOST = "mail.host";
    /**
     * 邮箱协议
     */
    String C_PROTOCOL = "mail.transport.protocol";
    /**
     * smtp 邮箱授权
     */
    String C_AUTH = "mail.smtp.auth";
    /**
     * 发送者
     */
    String C_USER = "mail.user";

    /**
     * 获取服务地址
     * @param host
     */
    ClientContext host(String host);


    /**
     * 协议
     * @param protocol
     * @return ClientContext
     */
    ClientContext protocol(String protocol);


    /**
     * 授权
     * @param auth
     * @return ClientContext
     */
    ClientContext auth(boolean auth);

    /**
     * 设置title
     * @param title
     * @return ClientContext
     */
    ClientContext title(String title);

    /**
     * 设置邮件内容
     * @param content
     * @return ClientContext
     */
    ClientContext content(String content);


    /**
     *
     * @return
     */
    Properties prop();

}
