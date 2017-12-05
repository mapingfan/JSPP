package com.whu.birthday;

import javax.mail.MessagingException;

public class SenaMailTest {
    public static void main(String[] args) throws MessagingException {
        MailUtils.sendMail("lucy@whu32.com","测试邮件","测试");
    }
}
