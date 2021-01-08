package com.cas.service.inqueryService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface InqueryService {

    List<LinkedHashMap<String, Object>> inquery(String statementId, String sqlText, Map<String, String> paramsMap);
}
