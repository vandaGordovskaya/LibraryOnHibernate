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
<div style="color:red">${errorMsg}</div>
<br/>
<div align="center">
<h1>BOOKS LIST</h1>
    <table border="1" cellpadding="5">
       <tr>
            <th width="80">ID</th>
            <th width="120">Title</th>
            <th width="100">Delete</th>
            <th width="100">Edit</th>
       </tr>
       <c:forEach var="book" items="${listBooks}">
         <tr>
            <td><c:out value="${book.id}" /></td>
            <td><a target="_self" href="/library/bookData?bookId=${book.id}&bookName=${book.name}">
                <c:out value="${book.name}" /></a></td>
            <td><a href = "./deleteBook?bookId=${book.id}">Delete this book</a></td>
            <td><a href = "./updateBook?bookId=${book.id}&bookName=${book.name}">Edit this book</a></td>
          </tr>
       </c:forEach>
    </table>
    <br/>
        <br/>
            <form method="LINK" action="/library/createBook">
                <input type="submit" value="CREATE BOOK">
            </form>
</div>
</body>
</html>