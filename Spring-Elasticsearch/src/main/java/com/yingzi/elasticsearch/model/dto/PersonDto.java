package com.yingzi.elasticsearch.model.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author yingzi
 * @date 2025/4/14:14:13
 */
@Document(indexName = "yingzi")
@Data
public class PersonDto {

    @Id
    private String id;

    private String firstname;

    private String lastname;
}
