<?php
$numbers = $_GET['numbers'];
$total = 0;

foreach ($numbers as $number) {
    $total += $number;
}
?>

<html lang="en">
<head>
    <title>$_GET</title>
</head>
<body>

<h1>$_GET</h1>
<h1>Total = <?= $total ?></h1>

</body>
</html>