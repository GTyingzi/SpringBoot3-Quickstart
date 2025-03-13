package com.yingzi.mail.model.dto;

import lombok.Data;

/**
 * @author yingzi
 * @date 2025/3/12:21:53
 */
@Data
public class EmailDTO {

    /**
     * 发件人
     */
    private String from;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 收件人
     */
    private String to;
    /**
     * 文件附件
     */
    private String filePath;

}
