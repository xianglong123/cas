package com.cas.service.pdfService;

import com.cas.domain.User;
import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.util.List;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 12:51 2020-02-24
 * @version: V1.0
 * @review:
 */
@Slf4j
@Service
public class PdfServiceClient implements PdfService {

    @Override
    @SuppressWarnings("unchecked")
    public PdfExportService exportService() {
        log.info("PdfServiceClient 绑定view与参数start");
        return ((model, document, writer, request, response) -> {
            try {
                // A4纸张
                document.setPageSize(PageSize.A4);
                document.addTitle("用户信息");
                document.add(new Chunk("\n"));
                PdfPTable table = new PdfPTable(3);
                PdfPCell cell = null;
                // 字体，定义为蓝色加粗
                Font f8 = new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);

                cell = new PdfPCell(new Paragraph("id", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("user_name", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);

                List<User> userList = (List<User>) model.get("userList");
                for (User user : userList) {
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(user.getAge() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(user.getUsername()));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph("备注"));
                    table.addCell(cell);
                }
                document.add(table);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
