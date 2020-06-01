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


<div>
    <textarea name="commentContent" id="commentContent" class="comment-content" placeholder="发表你的精彩评论，还可以输入140字" onkeyup="checkTextLen(this)"></textarea>
    <span id="checklen">还可输入 <strong>140</strong> 个汉字</span>
    <button type="button" class="published" id="publishedComment">发表</button>
</div>

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

<script type="text/javascript">
    function checkTextLen(obj) {
        var val = $(obj).val().length;
        if (val <= 140) {
            $("#checklen").html("还可输入 <strong>"+ Math.floor(140-val) +"</strong> 个字").css('color', '');
            $("#publishedComment").removeAttr("disabled").css("background","#007aff");
        }else {
            console.log("最多输入140个字符！");
            $("#publishedComment").attr("disabled", "disabled").css("background","#ccc");
            $("#checklen").html(" 已经超过<strong>"+ Math.floor(val-140) +"</strong> 个字").css('color', '#f00');
        }
    }
</script>
</body>
</html>