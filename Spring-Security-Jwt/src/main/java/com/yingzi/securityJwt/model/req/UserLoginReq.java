package com.yingzi.securityJwt.model.req;

import lombok.Data;

/**
 * @author yingzi
 * @date 2025/3/17:14:10
 */
@Data
public class UserLoginReq {

    private String username;
    private String password;
}
