package com.cas.owner.service.pdfService;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 10:54 2020-02-23
 * @version: V1.0
 * @review:
 */
public interface PdfExportService {

    void make(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response);

}
