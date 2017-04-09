<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update author</title>
</head>
<body>
<a href = "./authors"> <<< Back  </a>
<br/>
<div style="color:red">${errorMsg}</div>
<br/>
<form method="POST" action="./updateAuthor">
    <br/>
    Author ID: <input type="number" name="authorId" style="width: 200px" value="<c:out value="${authorId}" />" readonly="readonly" /><br/>
    <br/>
    Author Name: <input type="text" name="updatedAuthorName" style="width: 200px" required maxlength="250" value="<c:out value="${authorName}" />"/><br/>
    <br/>
    <input type="submit" value="UPDATE" />
</form>
</body>
</html>