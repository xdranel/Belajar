<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Switch Page</title>
</head>
<body>
@switch($value)
    @case('A')
        Happy
        @break
    @case('B')
        Sad
        @break
    @case('C')
        Angry
        @break
    @default
        Boring
@endswitch
</body>
</html>
