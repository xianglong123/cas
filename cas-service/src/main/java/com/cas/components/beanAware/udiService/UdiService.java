package com.cas.components.beanAware.udiService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface UdiService {

    List<LinkedHashMap<String, Object>> inquery(String statementId, String sqlText, Map<String, String> paramsMap);
}
