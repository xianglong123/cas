package com.cas.domain.grippe;

import lombok.Data;

import java.util.List;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:13 2020-01-24
 * @version: V1.0
 * @review: 流感数据
 */
@Data
public class GrippeProv {

    // 受感染人数
    private Integer confirmedCount;

    //治愈人数
    private Integer curedCount;

    //省简称
    private String provinceShortName;

    //备注
    private String comment;

    //省全称
    private String provinceName;

    //死亡人数
    private Integer deadCount;

    //怀疑人数
    private Integer suspectedCount;

    private List<GrippeCity> cities;

}
