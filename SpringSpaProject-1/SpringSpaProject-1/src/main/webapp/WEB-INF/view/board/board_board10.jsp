<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>최근 게시물</h2>
<div>
	<c:forEach var='vo' items='${list }'>
		<div>
			[${vo.id }] <span>${vo.subject }</span>
		</div>
	</c:forEach>
</div>

</body>
</html>