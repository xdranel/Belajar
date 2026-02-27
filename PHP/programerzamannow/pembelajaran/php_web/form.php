<html lang="en">
<head>
    <title>Contoh Form POST</title>
</head>
<body>
<h1>$_POST</h1>

<form action="post.php" method="post">
    <label>First Name :
        <input type="text" name="first_name" placeholder="First Name" required>
    </label>
    <br/>
    <label>Last Name :
        <input type="text" name="last_name" placeholder="last Name">
    </label>
    <br/>
    <input type="submit" value="Register" name="submit">
</form>
</body>
</html>
