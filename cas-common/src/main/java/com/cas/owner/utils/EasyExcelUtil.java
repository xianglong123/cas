package com.cas.owner.utils;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.Data;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 工具
 */
public class EasyExcelUtil {

    /**
     * 监听器
     */
    @Data
    private static class StringExcelListener extends AnalysisEventListener {
        /**
         * 自定义用于暂时存储 data
         * 可以通过实例获取该值
         */
        private List<List<String>> datas = new ArrayList<>();

        /**
         * 每解析一行都会回调invoke()方法
         *
         * @param object
         * @param context
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            List<String> stringList = (List<String>) object;
            // 数据存储到list  批量处理 或后续自己业务逻辑处理
            datas.add(stringList);
            // 根据自己业务做处理
        }

        public StringExcelListener() {
            super();
        }


        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            //解析结束销毁不用的资源
            //注意不要调用datas.clear(),否则getDatas为null
        }

    }

    /**
     * 使用 StringList 来读取Excel
     * @param inputStream Excel 输入流
     * @param excelTypeEnum Excel 格式
     * @return
     */
    public static List<List<String>> readExcelWithStringList(InputStream inputStream, ExcelTypeEnum excelTypeEnum) {
        StringExcelListener listener = new StringExcelListener();
        ExcelReader excelReader = new ExcelReader(inputStream, excelTypeEnum, null, listener);
        excelReader.read();
        return listener.getDatas();
    }

    /**
     * 使用 StringList 来写入Excel
     * @param outputStream
     * @param data
     * @param table
     * @param excelTypeEnum
     */
    public static void writeExcelWithStringList(OutputStream outputStream, List<List<String>> data, Table table, ExcelTypeEnum excelTypeEnum) {
        //这里制定不需要表头，因为string通常表头被包含在data里
        ExcelWriter writer = new ExcelWriter(outputStream, excelTypeEnum, false);
        //写第一个sheet, sheet1 数据全是List<String> 无模型映射关系，无表头
        Sheet sheet1 = new Sheet(0, 0);
        writer.write0(data, sheet1, table);
        writer.finish();
    }

    private static class ModelExcelListense<E> extends AnalysisEventListener<E> {

        private List<E> dataList = new ArrayList<>();


        public ModelExcelListense() {
            super();
        }

        @Override
        public void invoke(E object, AnalysisContext context) {
            dataList.add(object);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {

        }

        public List<E> getDataList() {
            return dataList;
        }

        public void setDataList(List<E> dataList) {
            this.dataList = dataList;
        }

    }

    /**
     * 使用 模型 来解读Excel
     */
    public static <E> List<E> readExcelTiehModel(InputStream inputStream, Class<? extends BaseRowModel> clazz, ExcelTypeEnum excelTypeEnum) {
        //解析每行结果在listener中处理
        ModelExcelListense<E> listense = new ModelExcelListense<>();
//        ExcelReader excelReader = new ExcelReader(inputStream,null, listense);
        ExcelReader excelReader = new ExcelReader(inputStream, excelTypeEnum, null, listense);
        //默认只有一列表头
        excelReader.read(new Sheet(1, 1, clazz));

        return listense.getDataList();
    }

    /**
     * 使用模型 来写入Excel
     * @param outputStream
     * @param data
     * @param clazz
     * @param excelTypeEnum
     */
    public static void writeExcelWithModel(OutputStream outputStream, List<? extends BaseRowModel> data, Class<? extends BaseRowModel> clazz, ExcelTypeEnum excelTypeEnum) {
        // 这里指定需要表头，因为model通常包含表头信息
        ExcelWriter writer = new ExcelWriter(outputStream, excelTypeEnum, true);
        //写第一个sheet, sheet1 数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 0, clazz);
        writer.write(data, sheet1);
        writer.finish();
    }


}
