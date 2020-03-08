package com.cas.service.questionService;

import com.cas.dao.mapper.QueAnsMapper;
import com.cas.pojo.QueAnsPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 18:37 2020-02-24
 * @version: V1.0
 * @review:
 */
@Service
public class QuestionServiceClient implements QuestionService{

    @Autowired
    private QueAnsMapper queAnsMapper;

    @Override
    public int add(QueAnsPo queAnsPo) {
        return queAnsMapper.add(queAnsPo);
    }

}
