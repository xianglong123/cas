package com.cas.components.beanAware.webssh.pojo;

import lombok.Data;

/**
 * @ClassName SFTPData
 * @Author luo jin jiang
 * @Date 2020/3/21 17:23
 * @Version 1.0
 */
@Data
public class SFTPData {
    private String host;
    //端口号默认为22
    private Integer port = 22;
    private String username;
    private String password;
}
