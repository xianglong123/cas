package com.cas.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    int queryCount();
}
