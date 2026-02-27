<?php

require __DIR__ . '/vendor/autoload.php';

use Monolog\Logger;
use Monolog\Handler\StreamHandler;

$log = new Logger('myLogger');
$log->pushHandler(new StreamHandler(__DIR__ . '/logs/app.log', Logger::INFO));

$log->info('Hello World!');
$log->info('Belajar PHP Composer');