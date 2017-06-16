<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>create book form</title>
</head>
<body>
<a href = "./authors"> <<< Back  </a>
<br/>
<form method="post" action="authors" name="createAuthor">
<input type="hidden" name="action" value="createAuthor" />
    Author Name: <input type="text" name="authorName" style="width: 200px" required maxlength="250"/><br/>
    <br/>
    <input type="submit" value="CREATE" />
</form>
</body>
</html>