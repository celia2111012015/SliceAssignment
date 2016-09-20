<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<form id="request_form" action="/SliceAssignment/submit" method="post">
	<input type="text" id="keyWord" name="keyWord" value=""/>
	<input type="submit" id="submit" value="submit" value="Request" />
</form>
</head>
<body>
<p> {$result}</p>

</body>
</html>