<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/jquery-3.4.1.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</head>
<body>

<h1>Hello, world!</h1>
<h2>计数器:<span id="autoId">19</span></h2>

<%--<script src="common/head.jsp"></script>--%>
<script type="text/javascript">

    $(function(){
        $("#autoId").text('20');
        // alert("你是猪吗？");
        getWaitAuditNum();
    });

    function getWaitAuditNum(){
        debugger;
        $.ajax({
            url:'<%=request.getContextPath() %>/test/queryAutoId',
            method:"get",
            cache:false,
            dataType:"text",
            success: function (data) {
                getWaitAuditNum();
                $("#autoId").text(data)
            }
        });
    }


</script>
</body>
</html>