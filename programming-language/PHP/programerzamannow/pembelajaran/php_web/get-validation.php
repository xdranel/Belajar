<?php

if (!isset($_GET['name']) || $_GET['name'] == '') {
    http_response_code(400);
    echo "Name is required";
    exit();
}

$say = "Hello " . htmlspecialchars($_GET['name']);
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