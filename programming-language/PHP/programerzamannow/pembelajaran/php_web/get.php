<?php
$say = "Hello " . htmlspecialchars($_GET["name"]);
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