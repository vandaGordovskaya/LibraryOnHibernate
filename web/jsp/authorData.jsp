<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href = "/library/authors"> <<< Back </a>
<br/>
<br/>
<h1>Author Details : ${authorName}</h1>
<br/>
<br/>
<table border="1" cellpadding="5">
  <tr>
    <th width="120">Book(s)</th>
  </tr>
  <c:forEach var="book" items="${listBooks}">
  <tr>
    <td><c:out value="${book.name}" /></td>
  </tr>
  </c:forEach>
</table>
</body>
</html>