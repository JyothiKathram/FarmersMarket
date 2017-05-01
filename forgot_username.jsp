<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<h1><center><i>University of New Haven</i></center></h1>
		<h2><center>Library Management System</center></h2>
</head>
<body bgcolor="#ccffcc">
<br> </br>
<center><b><i>Reset Password</i></b></center>
<form action="Password_validate" method="POST">
			<center><br>username:<input type="text" name="uname" id="uname" /></br></center>
			<center><br>Password:<input type="password" name="Password1" id="Password1" /> </br></center>
			<center><br>Confirm: <input type="password" name="Password2" id="Password2" /> </br></center>
			<br><center><input type="submit" value="submit" /></center></br>
			<center><span style="color:red;">${errMsg5}</span></center>
			<center><span style="color:red;">${errMsg6}</span></center>
</form>

</body>
</html>