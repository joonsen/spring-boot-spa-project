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
	<c:forEach var='vo' items='${list }'>
		<div class = 'item'>
			<form name='frm_guestbook' class='frm_guestbook' method='post'>
				<div class='btnZone'>
					<input type='button' class='btnUpdate' value='수정' 
						onclick='gb.modifyView(this.form)'/>
					<input type='button' class='btnDelete' value='X'
						onclick='gb.modalView(this.form)'/>
				</div>
				
				<label>작성자</label>
				<input type = 'text' name='id' value='${vo.id }'>
				<label>작성일</label>
				<output>${vo.nal }</output>
				<br/>
				<textarea rows="7" cols="30" name="doc">${vo.doc }</textarea>
				<br/>
				<div class='updateZone'>
					<span>암호</span>
					<input type='password' name='pwd'/>
					<input type = 'button' value = '수정' class='btnGuestbookUpdate'
						onclick='gb.update(this.form)'/>
					<input type='button' value='취소' 
					onclick="gb.modifyCancel(this.form)"/>
				</div>
				
				<input type='hidden' name='sno' value='${vo.sno }'/>
			</form>
		</div>
	</c:forEach>


</body>
</html>