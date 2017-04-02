<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update book</title>
</head>
<body>
<a href = "./books"> <<< Back  </a>
<br/>
<div style="color:red">${errorMsg}</div>
<br/>
<form method="POST" action="./updateBook">
    <br/>
    Book ID: <input type="number" name="bookId" style="width: 200px" value="<c:out value="${bookId}" />" readonly="readonly" /><br/>
    <br/>
    Book Name: <input type="text" name="updatedBookName" style="width: 200px" required maxlength="250" value="<c:out value="${bookName}" />"/><br/>
    <br/>
    Associated Authors (select author(s) to disassociate):
     <p><select name="disassociateAuthors" size="3" multiple>
            <c:forEach var="author" items="${associatedAuthors}">
            <option value="<c:out value="${author.name}" />"><c:out value="${author.name}" /></option>
            </c:forEach>
          </select></p>
     <br/>
     Not associated Authors (select author(s) to associate):
          <p><select name="associateAuthors" size="3" multiple>
                 <c:forEach var="newAuthor" items="${notAssociatedAuthors}">
                 <option value="<c:out value="${newAuthor.name}" />"><c:out value="${newAuthor.name}" /></option>
                 </c:forEach>
               </select></p>
    <input type="submit" value="UPDATE" />
</form>
</body>
</html>