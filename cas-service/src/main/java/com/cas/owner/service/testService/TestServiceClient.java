package com.cas.owner.service.testService;

import com.cas.dao.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceClient implements TestService{

    @Autowired
    private TestMapper testMapper;

    @Override
    public int queryCount() {
        return testMapper.queryCount();
    }

}
