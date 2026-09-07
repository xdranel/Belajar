<?php

use Illuminate\Support\Env;

return [
    "author" => [
        "first" => env("FIRST_NAME", "Gendhi"),
        "last" => Env::get("LAST_NAME", "Ramona")
    ],
    "email" => "gendhiramona@gmail.com",
    "web" => "https://gendhiramona.site"
];
