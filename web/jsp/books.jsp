<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body background-color:#9F79EE>
<a href = "./jsp/welcomePage.jsp"> <<< Back  </a>
<br/>
<div style="color:red">${errorMsg}</div>
<br/>
<div align="center">
<h1>BOOKS LIST</h1>
    <table border="1" cellpadding="5">
       <tr>
            <th width="80">ID</th>
            <th width="120">Title</th>
            <c:if test="${sessionScope.userRole eq 'admin'}">
                <th width="100">Delete</th>
                <th width="100">Edit</th>
            </c:if>
       </tr>
       <c:forEach var="book" items="${listBooks}">
         <tr>
            <td><c:out value="${book.id}" /></td>
            <td><a target="_self" href="books?action=bookData&bookId=${book.id}&bookName=${book.name}">
                <c:out value="${book.name}" /></a></td>
            <c:if test="${sessionScope.userRole eq 'admin'}">
                <td><a href = "books?action=deleteBook&bookId=${book.id}">Delete this book</a></td>
                <td><a href = "books?action=updateBook&bookId=${book.id}&bookName=${book.name}">Edit this book</a></td>
            </c:if>
          </tr>
       </c:forEach>
    </table>
    <br/>
        <br/>
        <c:if test="${userRole eq 'admin'}">
            <a href = "books?action=createBook">CREATE NEW BOOK</a>
        </c:if>
</div>
</body>
</html>