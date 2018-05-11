<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2018/5/10
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</head>
<body>
<form id="test">
    选择文件:<input data-role="none" type="file" name="file" width="120px">

</form>
<button data-role="none" onclick="testUpload()">测试</button>
<button data-role="none" onclick="testUpload()">测试</button>

<script>
    function testUpload(){
        alert("fg");
        var form = new FormData(document.getElementById("test"));
        var url ="/NewsServerApi/user/alterUserLogo";    //这里的“项目访问路径”要改为你自己的路径
        $.ajax({
            url : url,
            data : form,
            type : 'post',
            processData:false,
            contentType:false,
            success : function(data){
                alert("成功")
            },
            error : function(data){

            }
        });
    }


</script>
</body>
</html>
