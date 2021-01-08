package com.cas.components.beanAware.uploadService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UploadService {

    /**
     * 导出Excel 至本地某目录
     * @param response
     * @throws IOException
     */
    void writeExcelOneSheetOnceWrite(HttpServletResponse response) throws IOException;

    /**
     * 下载Excel
     * @param response
     * @throws Exception
     */
    void exportSysSystemExcel(HttpServletResponse response) throws Exception;

    /**
     * 解析http 传来的Excel
     */
    void readExcelhttp(HttpServletRequest request) throws Exception;


}
