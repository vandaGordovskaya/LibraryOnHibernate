<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>create book form</title>
</head>
<body>
<a href = "./books"> <<< Back  </a>
<br/>
<form method="POST" action="./createBook">
    <br/>
    Book ID: <input type="number" name="bookId" style="width: 200px" required maxlength="5"/><br/>
    <br/>
    Book Name: <input type="text" name="bookName" style="width: 200px" required maxlength="250"/><br/>
    <br/>
    Author(s):

      <p><select multiple name="getListAuthors" size="5" required>
        <c:forEach var="author" items="${listAuthorsFromDB}">
        <option value="<c:out value="${author.id}" />"><c:out value="${author.name}" /></option>
        </c:forEach>
      </select></p>

    <input type="submit" value="CREATE" />
</form>
</body>
</html>