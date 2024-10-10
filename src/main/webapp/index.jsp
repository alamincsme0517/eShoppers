<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h2>Welcome new my web application</h2>
<h3>Good job</h3>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<h2>Prime Numbers from 1 to 10:</h2>
<c:set var="start" value="1" />
<c:set var="end" value="10" />
</body>
</html>