<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title2</title>
</head>
<body>
<form action="/test/addQuestion" method="post" enctype="multipart/form-data">
    <span>问题描2述:</span> <input name="question" width="200px" height="100px" id="question" type="text"/>
    <span>问题地址:</span> <input name="questionUrl" id="questionUrl" type="text"/>
    <span>答案:</span> <input name="answer" id="answer" type="text"/>
    <span>关键词:</span> <input name="keywords" id="keywords" type="text"/>
    <input type="submit" value="上传"/>
</form>


</body>
</html>