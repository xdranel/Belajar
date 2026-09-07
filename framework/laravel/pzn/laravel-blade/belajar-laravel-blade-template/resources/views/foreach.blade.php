<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Foreach Page</title>
</head>
<body>
<ul>
    @foreach($hobbies as $hobby)
        <li>{{ $hobby }}</li>
    @endforeach
</ul>
</body>
</html>
