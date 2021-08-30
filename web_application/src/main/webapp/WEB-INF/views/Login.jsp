<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>YAhoooooooooooo</title>
</head>
<body>

<form action="/login" method="post">
<p><font color="red">${error}</font></p>
Enter your name : 
<input type="text" name="name"/>

Enter your Password : 
<input type="password" name="pass"/>

<input type="submit" value="login" />
</form>


</body>
</html>