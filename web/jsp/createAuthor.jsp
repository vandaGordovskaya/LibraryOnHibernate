<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>create book form</title>
</head>
<body>
<a href = "./authors"> <<< Back  </a>
<br/>
<form method="post" action="../createAuthor">
    <br/>
    Author ID: <input type="number" name="authorId" style="width: 200px" required maxlength="5"/><br/>
    <br/>
    Author Name: <input type="text" name="authorName" style="width: 200px" required maxlength="250"/><br/>
    <br/>
    <input type="submit" value="CREATE" />
</form>
</body>
</html>