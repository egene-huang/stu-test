package mail.client.context.impl;

import lombok.Getter;
import mail.client.context.ClientContext;

import java.util.Properties;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/1/12 17:00
 */
@Getter
public class SmtpContextImpl implements ClientContext {
    /**
     * 邮箱服务器地址
     */
    private String host;
    /**
     * 协议
     */
    private String protocol;

    /**
     * 授权
     */
    private Boolean auth;

    /**
     * title
     */
    private String title;

    /**
     * content
     */
    private String content;

    @Override
    public ClientContext host(String host) {
        this.host = host;
        return this;
    }

    @Override
    public ClientContext protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    @Override
    public ClientContext auth(boolean auth) {
        this.auth = auth;
        return this;
    }

    @Override
    public ClientContext title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public ClientContext content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public Properties prop() {
        Properties prop = new Properties();
        prop.setProperty(C_HOST, this.host);
        prop.setProperty(C_PROTOCOL, this.protocol);
        prop.setProperty(C_AUTH, this.auth.toString());
        return prop;
    }
}
