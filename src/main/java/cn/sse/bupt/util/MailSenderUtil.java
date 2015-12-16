package cn.sse.bupt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * Created by hao.yan on 2015/12/8.
 */
@Component
public class MailSenderUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailSenderUtil.class);
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void sendEmail(String targetEmail, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(targetEmail);
        simpleMailMessage.setFrom("egovernment_sse@126.com");
        simpleMailMessage.setSubject("账户激活邮件");
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
        LOGGER.info("send email to {}", targetEmail);
    }

}
