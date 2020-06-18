package com.cas.dao.mapper;

import com.cas.domain.grippe.GrippeCity;
import com.cas.domain.grippe.GrippeProv;
import org.springframework.stereotype.Service;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:56 2020-01-24
 * @version: V1.0
 * @review:
 */
@Service
public interface GrippeInfoMapper {

    int inertGrippeProvInfo(GrippeProv grippeProv);

    int inertGrippeCityInfo(GrippeCity grippeCity);

}
