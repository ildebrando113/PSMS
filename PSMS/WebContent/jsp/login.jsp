<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
<link rel="stylesheet" href="../PrimeUi/theme.css" />
    <link rel="stylesheet" type="text/css" href="../PrimeUi/font-awesome.css" />
    <link rel="stylesheet" href="../PrimeUi/jquery-ui.css" />
    <link rel="stylesheet" href="../PrimeUi/primeui.min.css" />
    <script type="text/javascript" src="../PrimeUi/jquery.js"></script>
    <script type="text/javascript" src="../PrimeUi/jquery-ui.js"></script>
    <script type="text/javascript" src="../PrimeUi/primeui.min.js"></script>
</head>
<body>
	<form  name ="myForm" method ="post" action ="/PSMS_ver1/LoginCheck">
	Enter username : <input type ="text" name = "username"><br>
	Enter password : <input type ="text" name = "password"><br>
	<input type ="submit" value = "login">
	</form>
</body>
</html>