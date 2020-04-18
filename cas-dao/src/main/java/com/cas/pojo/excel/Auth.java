package com.cas.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 17:07 2020-04-17
 * @version: V1.0
 * @review:
 */
@Data
public class Auth extends BaseRowModel implements Serializable {

    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "身份证", index = 1)
    private String idCard;
}
