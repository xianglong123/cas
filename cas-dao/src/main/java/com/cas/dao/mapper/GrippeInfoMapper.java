package com.cas.dao.mapper;

import com.cas.domain.grippe.GrippeCity;
import com.cas.domain.grippe.GrippeProv;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 10:56 2020-01-24
 * @version: V1.0
 * @review:
 */
public interface GrippeInfoMapper {

    int inertGrippeProvInfo(GrippeProv grippeProv);

    int inertGrippeCityInfo(GrippeCity grippeCity);

}
