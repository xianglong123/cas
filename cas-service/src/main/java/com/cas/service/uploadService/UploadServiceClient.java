package com.cas.service.uploadService;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.cas.pojo.ExcelModel;
import com.cas.utils.EasyExcelUtil;
import jdk.internal.util.xml.impl.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Excel 导出至本地
 */
@Slf4j
@Service
public class UploadServiceClient implements UploadService {

    private static final int FILESIZE = 6*1024*1024;

    @Override
    public void writeExcelOneSheetOnceWrite(HttpServletResponse response) throws IOException {
        OutputStream out = null;
        // 生成EXCEL并制定输出路径
        out = new FileOutputStream("/Users/xianglong/Desktop/aa.xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

        // 设置SHEET
        Sheet sheet = new Sheet(1, 0);
        sheet.setSheetName("sheet1");

        // 设置标题
        Table table = new Table(1);
        List<List<String>> titles = new ArrayList<>();
        titles.add(Arrays.asList("用户ID"));
        titles.add(Arrays.asList("名称"));
        titles.add(Arrays.asList("年龄"));
        titles.add(Arrays.asList("生日"));
        table.setHead(titles);

        // 查询数据导出即可，比如说一次性总共查询出100条数据
        List<List<String>> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(Arrays.asList("ID_" + i, "小明" + i, String.valueOf(i), new Date().toString()));
        }

        writer.write0(userList, sheet, table);
        writer.finish();
        out.flush();
        log.info("导出完毕");
    }


    @Override
    public void exportSysSystemExcel(HttpServletResponse response) throws Exception {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            //设置EXCEL 名称
            String fileName = new String(("SystemExcel").getBytes(), "UTF-8");

            //设置SHEET名称
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("系统列表sheet1");

            //设置标题
            Table table = new Table(1);
            List<List<String>> titles = new ArrayList<>();
            titles.add(Arrays.asList("系统名称"));
            titles.add(Arrays.asList("系统标识"));
            titles.add(Arrays.asList("描述"));
            titles.add(Arrays.asList("状态"));
            titles.add(Arrays.asList("创建人"));
            titles.add(Arrays.asList("创建时间"));

            table.setHead(titles);

            //查数据写EXCEL
            List<List<String>> dataList = new ArrayList<>();

            for (int i = 0; i <= 10; i++) {
                dataList.add(
                        Arrays.asList(
                                "系统名称" + i, "系统标识" + i, "描述" + i, "状态" + i, "创建人" + i, "创建时间" + i
                        )
                );
            }
            writer.write0(dataList,sheet,table);

            //下载Excel
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            writer.finish();
            out.flush();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    log.info(e.toString());
                }
            }
        }
    }

    @Override
    public void readExcelhttp(HttpServletRequest request) throws Exception {
        InputStream inputStream = getUploadInputStream(request);
        List<ExcelModel> objectList = EasyExcelUtil.readExcelTiehModel(inputStream, ExcelModel.class, ExcelTypeEnum.XLSX);
        for (ExcelModel excelModel : objectList) {
            System.out.println(excelModel);
        }
    }

    private static InputStream getUploadInputStream(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("uploadFile");
        if (file.getSize() > FILESIZE) {
            throw new RuntimeException("文件大于6M");
        }
        InputStream input = null;
        try {
            input = file.getInputStream();
            return input;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
