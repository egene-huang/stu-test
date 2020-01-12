package mail.client.context;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/1/12 16:38
 */
@Getter
@Setter(value = AccessLevel.PRIVATE)
public class ClientContext {

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
     * 邮箱服务器地址
     */
    private String host;
    /**
     * 协议
     */
    private String protocol;

    /**
     * sender
     */
    private String sender;

    /**
     * 发送人昵称
     */
    private String nick;

    /**
     * 授权
     */
    private Boolean auth;

    /**
     * 密码
     */
    private String password;

    /**
     * title
     */
    private String title;

    /**
     * content
     */
    private String content;


    /**
     * 属性
     */
    final Properties prop = new Properties();


    public ClientContext build() {
        prop.setProperty(C_HOST, this.host);
        prop.setProperty(C_PROTOCOL, this.protocol);
        prop.setProperty(C_AUTH, this.auth.toString());
        return this;
    }

}
