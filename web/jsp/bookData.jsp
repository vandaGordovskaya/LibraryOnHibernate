<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href = "/library/books"> <<< Back  </a>
<br/>
<br/>
<h1>Book Details : <c:out value="${bookName}" /></h1>
<br/>
<br/>
<table border="1" cellpadding="5">
  <tr>
    <th width="120">Author(s)</th>
  </tr>
  <c:forEach var="author" items="${listAuthors}">
  <tr>
    <td><c:out value="${author.name}" /></td>
  </tr>
  </c:forEach>
</table>
</body>
</html>