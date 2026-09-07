<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Loop Variable Page</title>
</head>
<body>
<ul>
    @foreach($hobbies as $hobby)
        <li>{{ $loop->iteration }}. {{$hobby}}</li>
    @endforeach
</ul>
</body>
</html>
