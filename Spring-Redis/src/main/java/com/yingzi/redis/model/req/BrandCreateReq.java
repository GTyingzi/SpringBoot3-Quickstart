package com.yingzi.redis.model.req;

import lombok.Data;

/**
 * @author yingzi
 * @date 2025/3/10:21:00
 */
@Data
public class BrandCreateReq {

    /**
     * 品牌名称
     */
    private String name;
    /**
     * 首字母
     */
    private String firstLetter;

    private Integer sort;

    /**
     * 是否为品牌制造商：0->不是；1->是
     */
    private Integer factoryStatus;

    private Integer showStatus;

    /**
     * 产品数量
     */
    private Integer productCount;

    /**
     * 产品评论数量
     */
    private Integer productCommentCount;

    /**
     * 品牌logo
     */
    private String logo;

    /**
     * 专区大图
     */
    private String bigPic;

    /**
     * 品牌故事
     */
    private String brandStory;
}
