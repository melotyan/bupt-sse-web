package cn.sse.bupt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

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
        simpleMailMessage.setSubject("测试邮件");
        simpleMailMessage.setText("测试");
        javaMailSender.send(simpleMailMessage);
        LOGGER.info("send email to {}", targetEmail);
    }

}
