package com.cas.excel;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.cas.pojo.ExcelModel;
import com.cas.utils.EasyExcelUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 单元测试
 */
public class EasyExcelTest {

    private static final String fileName = "/Users/xianglong/Desktop/export.xlsx";

    public static void main(String[] args) throws Exception{
        writeExcel();
        readExcel();
    }

    private static void writeExcel() throws FileNotFoundException {
        List<ExcelModel> excelModelList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ExcelModel excelModel = new ExcelModel();
            excelModel.setAddress("address" + i);
            excelModel.setAge(i + "");
            excelModel.setEmail("email" + i);
            excelModel.setHeigh("heigh" + i);
            excelModel.setLast("last" + i);
            excelModel.setName("name" + i);
            excelModel.setSax("sax" + i);
            excelModelList.add(excelModel);
        }//面试

        long beginTime = System.currentTimeMillis();
        OutputStream out = new FileOutputStream(fileName);
        EasyExcelUtil.writeExcelWithModel(out, excelModelList, ExcelModel.class, ExcelTypeEnum.XLSX);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("总耗时 %s 毫秒", (endTime - beginTime)));
        //清除缓存
        excelModelList = null;
    }

    private static void readExcel() {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            // 读入文件， 每一行对应一个 Model ,获取Model列表
            List<ExcelModel> objectList = EasyExcelUtil.readExcelTiehModel(inputStream, ExcelModel.class, ExcelTypeEnum.XLSX);
            for (ExcelModel excelModel : objectList) {
                System.out.println(excelModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
