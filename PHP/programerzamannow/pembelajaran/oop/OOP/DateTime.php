<?php

$datetime = new DateTime();
$datetime->setDate(2025, 12, 12);
$datetime->setTime(12, 12, 12);
var_dump($datetime);

// Adding 1 Year
$datetime->add(new DateInterval("P1Y"));

// Minus 1 Month
$minusMonth = new DateInterval("P1M");
$minusMonth->invert = true;
$datetime->add($minusMonth);
var_dump($datetime);

// Set Timezone
$now = new DateTime();
$now->setTimezone(new DateTimeZone("America/New_York"));
var_dump($now);

// Format
$stringTime = $now->format("Y-m-d H:i:s");
echo "Waktu Sekarang : $stringTime" . PHP_EOL;

// Parse
$date = DateTime::createFromFormat("Y-m-d H:i:s", "2025-12-12 12:12:12", new DateTimeZone("Asia/Jakarta"));
var_dump($date);