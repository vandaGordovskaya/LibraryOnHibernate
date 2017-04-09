<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href = "./jsp/welcomePage.jsp"> <<< Back  </a>
<br/>
<br/>
<div style="color:red">${errorMsg}</div>
<div align="center">
<h1>AUTHORS LIST</h1>
    <table border="1" cellpadding="5">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
                <c:if test="${userRole eq 'admin'}">
                    <th width="100">Delete</th>
                    <th width="100">Edit</th>
                </c:if>
        </tr>
        <c:forEach var="author" items="${listAuthors}">
            <tr>
                <td><c:out value="${author.id}" /></td>
                <td><a target="_self" href="/library/authorData?authorId=${author.id}&authorName=${author.name}">
                    <c:out value="${author.name}" /></a></td>
                <c:if test="${userRole eq 'admin'}">
                    <td><a href = "/library/deleteAuthor?authorId=<c:out value="${author.id}" />">Delete this author</a></td>
                    <td><a href = "./updateAuthor?authorId=${author.id}&authorName=${author.name}">Edit this author</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <br/>
        <c:if test="${userRole eq 'admin'}">
            <form method="LINK" action="/library/jsp/createAuthor.jsp">
                <input type="submit" value="CREATE AUTHOR">
            </form>
        </c:if>
</div>
</body>
</html>