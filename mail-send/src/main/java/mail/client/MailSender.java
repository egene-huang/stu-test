package mail.client;

import mail.client.context.ClientContext;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

    public void sendEmail(String receiver)throws Exception {
        Properties prop = clientContext.getProp();
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect(clientContext.getHost(), clientContext.getSender(), clientContext.getPassword());
        //4、创建邮件
        // Message message = createEmail01(session,receiver,title,body);
        Message message = emailContent(session,receiver);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * 创建邮件
     * @param session
     * @param receiver
     * @return
     */
    MimeMessage emailContent(Session session, String receiver)throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        String nick = javax.mail.internet.MimeUtility.encodeText(clientContext.getNick());
        message.setFrom(new InternetAddress(nick+"<"+clientContext.getSender()+">"));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        message.setRecipients(Message.RecipientType.CC, new InternetAddress[]{new InternetAddress("jacmes@163.com"), new InternetAddress("galaxyforjob@163.com")});
        //邮件的标题
        message.setSubject(clientContext.getTitle());
        //邮件的文本内容
        message.setContent(clientContext.getContent(), "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
}
