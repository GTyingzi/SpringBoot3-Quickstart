package com.yingzi.mail.component;

import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

/**
 * @author yingzi
 * @date 2025/3/12:21:42
 */
@Slf4j
@Component
public class EmailComponent {


    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送纯文本的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @return 是否成功
     */
    @SneakyThrows(Exception.class)
    public boolean sendGeneralEmail(String subject, String content, String... to) {
        // 创建邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        // 设置收件人
        message.setTo(to);
        // 设置邮件主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setText(content);

        // 发送邮件
        mailSender.send(message);
        log.info("发送邮件成功");
        return true;
    }

    /**
     * 发送带附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件路径
     * @return 是否成功
     */
    @SneakyThrows(Exception.class)
    public boolean sendAttachmentsEmail(String subject, String content, String to, String filePath) {
        // 创建邮件消息
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        // 设置收件人
        helper.setTo(to);
        // 设置邮件主题
        helper.setSubject(subject);
        // 设置邮件内容
        helper.setText(content);

        // 添加附件，读取resources下的images/rabbit.jpg
        filePath = Objects.requireNonNull(this.getClass().getClassLoader().getResource(filePath)).getPath();
        if (filePath != null) {
            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
        }
        // 发送邮件
        mailSender.send(mimeMessage);
        return true;
    }

}
