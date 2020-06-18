package com.cas.service.pdfService;


import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 11:01 2020-02-23
 * @version: V1.0
 * @review:
 */
@Slf4j
public class PdfView extends AbstractPdfView {

    // 导出服务接口
    private PdfExportService pdfExportService;

    // 创建对象时载入导出服务接口
    public PdfView(PdfExportService pdfExportService) {
        log.info("PdfView 构造器启动");
        this.pdfExportService = pdfExportService;
    }

    // 调用接口实现
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("buildPdfDocument 运行start");//最后运行
        // 调用导出服务接口类 下面这段代码只会被运行一次，也就是以前运行过一次，pdf展示渲染的时候就不会再执行一次了
        pdfExportService.make(model, document, writer, request, response);
    }
}
