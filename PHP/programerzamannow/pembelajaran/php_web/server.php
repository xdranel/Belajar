<?php
$server = $_SERVER;
?>

<html lang="en">
<head>
    <title>$_SERVER</title>
</head>
<body>
<h1>$_SERVER</h1>
<table border="1">
    <?php foreach ($server as $key => $value) { ?>
    <tr>
        <td><?= $key ?></td>
        <td><?= $value ?></td>
    </tr>
    <?php } ?>
</table>
</body>
</html>