package com.cas.test;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 生成EXCEL 文件
 */
@Slf4j
public class CasTest {

    @Test
    public void writeExcelOneSheetOnceWrite() throws IOException {
        // 生成EXCEL并制定输出路径
        OutputStream out = new FileOutputStream("/Users/xianglong/Desktop/aa.xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

        // 设置SHEET
        Sheet sheet = new Sheet(1,0);
        sheet.setSheetName("sheet1");

        // 设置标题
        Table table = new Table(1);
        List<List<String>> titles = new ArrayList<>();
        titles.add(Collections.singletonList("用户ID"));
        titles.add(Collections.singletonList("名称"));
        titles.add(Collections.singletonList("年龄"));
        titles.add(Collections.singletonList("生日"));
        table.setHead(titles);

        // 查询数据导出即可，比如说一次性总共查询出100条数据
        List<List<String>> userList = new ArrayList<>();
        for (int i = 0; i < 100; i ++) {
            userList.add(Arrays.asList("ID_" + i, "小明" + i, String.valueOf(i), new Date().toString()));
        }

        writer.write0(userList, sheet, table);

        // 下载EXCEL
        writer.finish();
        log.info("导出完毕");
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //保存数据list
        List<Integer> list = new ArrayList<>();

        while(in.hasNextInt()) {
            int num = in.nextInt();
            System.out.println("第一次输入" + num);

            for(int i = 0; i < num; i++) {
                System.out.println("--" + in.nextInt());
            }

        }
    }

}
