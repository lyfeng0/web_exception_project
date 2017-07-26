<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<taglib uri="http://java.sun.com /jsp/jstl/core" prefix="c" />
<html>
<head>
<title>上传</title>
</head>
<body>
<form action="uploadfile.jhtml" method="post" enctype="multipart/form-data">  
         文件1: <input type="file" name="myfiles"/><br/>     
          文件2: <input type="file" name="myfiles"/><br/>     
          文件3: <input type="file" name="myfiles"/><br/>     
        <input type="submit" value="上传">  
</form>
</body>
</html>