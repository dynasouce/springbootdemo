<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/file/uploadFile" enctype="multipart/form-data" method="post">
    文件：<input type="file" name="file" /><br/>
    <input type="submit" value="上传文件" />
</form>


</body>
</html>
