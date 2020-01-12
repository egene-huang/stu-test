package mail.client;

import mail.client.context.ClientContext;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/1/12 16:56
 */
public class MailSender {

    /**
     *
     */
    private ClientContext clientContext;

    public MailSender(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public void sendEmail() {
        Properties prop = clientContext.prop();
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect(EmailParam.emailHost, EmailParam.emailSender, EmailParam.password);
        //4、创建邮件
        // Message message = createEmail01(session,receiver,title,body);
        Message message = createEmail01(session,receiver,title,body);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }
}
