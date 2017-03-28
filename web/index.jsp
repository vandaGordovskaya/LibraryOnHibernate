<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>login form</title>
</head>
<body>
<div style="color:red">${errorMsg}</div>
<form method="post" action="login">
    <br/>
    User Name: <input type="text" name="userName" /><br/>
    <br/>
    Password: <input type="password" name="pass" /><br/>
    <br/>
    <input type="submit" value="LOGIN" />
</form>
</body>
</html>