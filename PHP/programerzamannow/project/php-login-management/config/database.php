<?php

function getDatabaseConfig(): array
{
    return [
        "database" => [
            "test" => [
                "url" => "mysql:host=localhost;port=3306;dbname=php_login_management_test",
                "username" => "root",
                "password" => "plm987!"
            ],
            "prod" => [
                "url" => "mysql:host=localhost;port=3306;dbname=php_login_management",
                "username" => "root",
                "password" => "plm987!"
            ]
        ]
    ];
}