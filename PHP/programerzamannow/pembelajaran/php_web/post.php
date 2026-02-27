<?php
$firstName = $_POST['first_name'];
$lastName = $_POST['last_name'];
?>

<html lang="en">
<head>
    <title>Contoh POST</title>
</head>
<body>
<h1>$_POST</h1>

<table border="2">
    <tr>
        <td>First Name</td>
        <td>Last Name</td>
    </tr>
    <tr>
        <td><?= $firstName ?></td>
        <td><?= $lastName ?></td>
    </tr>
</table>
</body>
</html>
