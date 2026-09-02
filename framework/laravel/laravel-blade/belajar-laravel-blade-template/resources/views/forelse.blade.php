<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>For Else Page</title>
</head>
<body>
<ul>
    @forelse($hobbies as $hobby)
        <li>{{ $hobby }}</li>
    @empty
        <li>There is no hobbies</li>
    @endforelse
</ul>
</body>
</html>
