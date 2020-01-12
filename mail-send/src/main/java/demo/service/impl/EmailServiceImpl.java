package demo.service.impl;

import demo.model.SendEmailModel;
import demo.param.BodyType;
import demo.param.EmailType;
import demo.service.EmailService;
import demo.util.EmailUtil;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/1/12 01:24
 */
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmail(String emailKey, SendEmailModel model) {
        try{
            // 异步执行
            Thread.sleep(1000);
            String textBody = EmailUtil.convertTextModel(BodyType.getByCode(emailKey),"知了","一笑");
            // 发送文本邮件
            EmailUtil.sendEmail01(model.getReceiver(), EmailType.getByCode(emailKey),textBody);
            // 发送复杂邮件:文本+图片+附件
//            String body = "自定义图片：<img src='cid:gzh.jpg'/>,网络图片：<img src='http://pic37.nipic.com/20140113/8800276_184927469000_2.png'/>";
            // EmailUtil.sendEmail02(demo.model.getReceiver(),"文本+图片+附件",body);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
