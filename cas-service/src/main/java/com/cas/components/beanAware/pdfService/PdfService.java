package com.cas.components.beanAware.pdfService;

import org.springframework.stereotype.Service;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:47 2020-02-24
 * @version: V1.0
 * @review:
 */
@Service
public interface PdfService {

    PdfExportService exportService();

}
