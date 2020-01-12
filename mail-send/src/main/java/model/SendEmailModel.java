package model;

/**
 * 邮件发送参数封装
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/1/12 01:22
 */
public class SendEmailModel {
    public static final String EMAIL_TEXT_KEY = "email_balance_key" ;
    public static final String EMAIL_IMAGE_KEY = "email_req_num_key" ;
    public static final String EMAIL_FILE_KEY = "email_open_account_key" ;

    /**
     * 收件人邮箱
     */
    private String receiver ;


    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
