package com.cas.components.beanAware.proxyService;

import com.alibaba.druid.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:23 2020-01-28
 * @version: V1.0
 * @review:
 */
@Service
public class ProxyServiceImpl implements ProxyService {

    @Override
    public void sayHello(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("parameters is null");
        }
        System.out.println("Hello: " + name);
    }

}
