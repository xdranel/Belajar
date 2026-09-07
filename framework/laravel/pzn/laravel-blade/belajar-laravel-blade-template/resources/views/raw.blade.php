<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Raw PHP Page</title>
</head>
<body>
@php

    class Person {
        public string $name;
        public string $address;
    }

    $person = new Person();
    $person->name = "John";
    $person->address = "USA"

@endphp

<p>Name: {{ $person->name }}</p>
<p>Address: {{ $person->address }}</p>
</body>
</html>
