package com.cas.utils;

import org.dom4j.*;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * @author: xianglong
 * @date: 2019/10/16 20:40
 * @version: V1.0
 * @review: xiang_long
 * xml工具
 */
public class XmlUtil {


    /**
     * map 转 xml
     *
     * @param map
     * @return
     */
    public static String map2Xml(Map<String, String> map) {
        List<Element> list = new ArrayList<>();
        for (Map.Entry<String, String> e : map.entrySet()) {
            list.add(createElement(e.getKey(), e.getValue()));
        }
        Document doc = createDocument(list.toArray(new Element[list.size()]));
        return doc.asXML();
    }

    private static Element createElement(String name, String value) {
        return new DOMElement(name).addCDATA(value);
    }

    private static Document createDocument(Element... dataElement) {
        Document doc = new DOMDocument();
        //初始化根节点
        Element root = new DOMElement("xml");
        doc.setRootElement(root);

        for (Element element : dataElement) {
            root.add(element);
        }
        return doc;
    }


    /**
     * xml 转 map
     *
     * @param xml
     * @param resMap
     * @throws Exception
     */
    public static void xml2map(String xml, Map<String, Object> resMap) throws Exception {
        ele2map(DocumentHelper.parseText(xml).getRootElement(), resMap);
    }


    /**
     * 核心方法(根据命名空间获取子节点)
     *
     * @param element
     * @param resMap
     */
    //递归算法(注意要有终结条件)
    @SuppressWarnings("unchecked")
    private static void ele2map(Element element, Map<String, Object> resMap) {
        // 获取当前节点的子节点
        List<Element> elements = element.elements();
        //子节点个数为0，则反当前节点的name和text
        if (elements.isEmpty()) {
            resMap.put(element.getName(), element.getText());
        } else if (elements.size() == 1) {
            //如果只有一个子节点就不用考虑list的情况，直接继续递归即可
            if (elements.get(0).elements().size() > 0) {
                Map<String, Object> tempMap = new HashMap<>();
                ele2map(elements.get(0), tempMap);
                resMap.put(elements.get(0).getName(), tempMap);
            } else {
                resMap.put(elements.get(0).getName(), elements.get(0).getText());
            }
        } else {
            //多个子节点要考虑list的情况，比如国歌子节点名称相同
            //构造一个Map去重
            Map<String, Object> tempMap = new HashMap<>();
            for (Element element1 : elements) {
                tempMap.put(element1.getName(), null);
            }
            //根据命名空间提取子节点--
            Set<String> keySet = tempMap.keySet();
            for (String string : keySet) {
                Namespace namespace = elements.get(0).getNamespace();
                //对于节点相同的情况，用命名空间获取子节点，然后分别遍历存List.然后装map
                List<Element> elements1 = element.elements(new QName(string, namespace));
                //如果同名的数目大于1则表示要构建list
                if (elements1.size() > 1) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (Element ele : elements1) {
                        Map<String, Object> tempMap1 = new HashMap<>();
                        ele2map(ele, tempMap1);
                        list.add(tempMap1);
                    }
                    resMap.put(string, list);
                } else {
                    //同名的数量不大于1则直接递归去
                    if (elements1.get(0).elements().size() > 0) {
                        Map<String, Object> tempMap2 = new HashMap<>();
                        ele2map(elements.get(0), tempMap2);
                        resMap.put(string, tempMap2);
                    } else {
                        resMap.put(elements1.get(0).getName(), elements1.get(0).getText());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        String res = "<html><add>123</add><bbb>222</bbb></html>";

        Document document = DocumentHelper.parseText(res);
        System.out.println(document);

    }

}
