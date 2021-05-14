<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="customerService.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/user.js"></script>
<title>customer Management</title>
</head>
<body>
<h1>customer Management</h1>

<form id="formItem" name="formItem" method="post" action="items.jsp">
 name:
<input id="name" name="name" type="text"
 class="form-control form-control-sm">
<br> email:
<input id="email" name="email" type="text"
 class="form-control form-control-sm">
<br> phone number:
<input id="tel" name="tel" type="text"
 class="form-control form-control-sm">
<br> username:
<input id="uname" name="uname" type="text"
 class="form-control form-control-sm">
<br>password:
<input id="pwd" name="pwd" type="text"
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divItemsGrid">
<%

User Obj = new User();
 out.print(Obj.readUser());
%>
</div>
</body>
</html>