<?php

function getDatabaseConfig(): array
{
    $envFile = __DIR__ . '/../.env';
    if (file_exists($envFile)) {
        $lines = file($envFile, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
        foreach ($lines as $line) {
            if (strpos(trim($line), '#') === 0) continue;
            list($name, $value) = explode('=', $line, 2);
            putenv(sprintf('%s=%s', trim($name), trim($value)));
        }
    }
    return [
        "database" => [
            "test" => [
                "url" => "mysql:host=" . getenv('DB_HOST') . ";port=" . getenv('DB_PORT') . ";dbname=" . getenv('DB_NAME_TEST'),
                "username" => getenv('DB_USER'),
                "password" => getenv('DB_PASS')
            ],
            "prod" => [
                "url" => "mysql:host=" . getenv('DB_HOST') . ";port=" . getenv('DB_PORT') . ";dbname=" . getenv('DB_NAME_PROD'),
                "username" => getenv('DB_USER'),
                "password" => getenv('DB_PASS')
            ]
        ]
    ];
}