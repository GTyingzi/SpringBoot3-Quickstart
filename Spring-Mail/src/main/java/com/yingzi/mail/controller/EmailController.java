package com.yingzi.mail.controller;

import com.yingzi.mail.component.EmailComponent;
import com.yingzi.mail.model.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yingzi
 * @date 2025/3/12:21:46
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailComponent emailComponent;

    /**
     * 发送纯文本的邮件
     */
    @PostMapping("/send-text")
    Boolean sendText(@RequestBody EmailDTO emailDTO) {
        return emailComponent.sendGeneralEmail(emailDTO.getSubject(), emailDTO.getContent(), emailDTO.getTo());
    }

    /**
     * 发送带附件的邮件
     */
    @PostMapping("/send-attachments")
    Boolean sendAttachments(@RequestBody EmailDTO emailDTO) {
        return emailComponent.sendAttachmentsEmail(emailDTO.getSubject(), emailDTO.getContent(), emailDTO.getTo(), emailDTO.getFilePath());
    }
}
