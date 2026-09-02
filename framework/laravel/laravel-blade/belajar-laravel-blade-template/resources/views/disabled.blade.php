<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Disabled Blade</title>
</head>
<body>

<h1>Hello @{{ $name }}</h1>

@verbatim
    <p>
        Hello {{$name}}
        Hello {{$name}}
        Hello {{$name}}
        Hello {{$name}}
    </p>
@endverbatim
</body>
</html>
