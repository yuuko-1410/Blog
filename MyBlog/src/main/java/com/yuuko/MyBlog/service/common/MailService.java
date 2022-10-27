package com.yuuko.MyBlog.service.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@Service
@Async
public class MailService {
    @Resource
    private JavaMailSender mailSender;

    // 从配置文件中注入发件人的姓名
    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 正文
     * @throws MessagingException
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail); // 发件人
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
