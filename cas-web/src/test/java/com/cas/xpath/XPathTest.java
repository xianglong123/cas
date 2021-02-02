package com.cas.xpath;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 17:42 2020-08-01
 * @version: V1.0
 * @review: 命令全局搜：xpath.md
 */
public class XPathTest {


    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();

        // 开启验证
        documentBuilderFactory.setValidating(true);
        // 设置空间域
        documentBuilderFactory.setNamespaceAware(false);
        // 设置忽略评论
        documentBuilderFactory.setIgnoringComments(true);
        // 设置忽略元素内容空白
        documentBuilderFactory.setIgnoringElementContentWhitespace(false);
        // 设置合并
        documentBuilderFactory.setCoalescing(false);
        // 设置扩展实体引用
        documentBuilderFactory.setExpandEntityReferences(true);

        // 创建DocumentBuilder
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        // 设置异常处理对象
        builder.setErrorHandler(new ErrorHandler() {

            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println("error: " + exception.getMessage());
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println("fataError: " + exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println("WARN: " + exception.getMessage());
            }
        });

        // 将文档加载到一个Document对象中
        Document doc = builder.parse("/Users/xianglong/IdeaProjects/cas/cas-web/src/test/java/com/cas/owner/mybatis/xpath/inventory.xml");

        // 创建 XPathFactory
        XPathFactory factory = XPathFactory.newInstance();
        // 创建xpath对象
        XPath xpath = factory.newXPath();
        // 编译xpath表达式
        XPathExpression expr = xpath.compile("//book[author='Neal Stephenson']/title/text()");
        // 通过XPath表达式得到结果，第一个参数指定了XPath表达式进行查询的上下文节点，也就是在指定节点下查找符合XPath的节点，本例中的上下文节点的整个文档，第二个参数指定了Xpath表达式的返回类型
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        System.out.println("查询作者为 Neal Stephenson 的图书的标题：");
        NodeList nodes = (NodeList) result; // 强制类型转换
        for (int i = 0; i < nodes.getLength(); i ++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        System.out.println("查询1997年之后的图书的标题");
        nodes = (NodeList)xpath.evaluate("//book[@year>1997]/title/text()", doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i ++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        System.out.println("查询价格是123的图书的作者");
        nodes = (NodeList)xpath.evaluate("//book[price=123]/title/text()", doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i ++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
    }


}
