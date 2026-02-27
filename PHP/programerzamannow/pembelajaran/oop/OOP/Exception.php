<?php

require_once "exception/ValidationException.php";
require_once "data/LoginRequest.php";
require_once "helper/Validation.php";

$loginRequest = new LoginRequest();
$loginRequest->username = "   ";
$loginRequest->password = "   ";

try {
    ValidateLoginRequest($loginRequest);
    echo "Validation" . PHP_EOL;
} catch (ValidationException | Exception $excepetion) {
    echo "Validation Error : " . $excepetion->getMessage() . PHP_EOL;

    var_dump($excepetion->getTrace()) . PHP_EOL;

    echo $excepetion->getTraceAsString() . PHP_EOL;
} finally {
    echo "Get Executed" . PHP_EOL;
}
