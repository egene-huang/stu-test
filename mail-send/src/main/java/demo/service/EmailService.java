package demo.service;

import demo.model.SendEmailModel;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/1/12 01:23
 */
public interface EmailService {
    void sendEmail (String emailKey, SendEmailModel model) ;
}
