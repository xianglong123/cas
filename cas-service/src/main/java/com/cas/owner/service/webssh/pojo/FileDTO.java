package com.cas.owner.service.webssh.pojo;

import lombok.Data;

/**
 * @ClassName FileDTO
 * @Author luo jin jiang
 * @Date 2020/3/21 16:15
 * @Version 1.0
 */
@Data
public class FileDTO {

    private String fileName;
    private String fileSize;
    private Boolean isDir;
    private String fullFileName;
    private String mtime;

}
