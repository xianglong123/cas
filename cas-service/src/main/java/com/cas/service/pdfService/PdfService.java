package com.cas.service.pdfService;

import org.springframework.stereotype.Service;

/**
 * @author: xianglong[xiang_long@suixingpay.com]
 * @date: 12:47 2020-02-24
 * @version: V1.0
 * @review:
 */
@Service
public interface PdfService {

    PdfExportService exportService();

}