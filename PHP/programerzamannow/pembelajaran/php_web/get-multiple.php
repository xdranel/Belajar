<?php
$say = "Hello " . $_GET['first_name'] . " " . $_GET['last_name'];
?>

<html lang="en">
<head>
    <title>$_GET</title>
</head>
<body>

<h1>$_GET</h1>
<h1><?= $say ?></h1>

</body>
</html>