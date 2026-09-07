<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Once or Each Page</title>

    @once
        <style>
            .red {
                color: red;
            }
        </style>
    @endonce
</head>
<body>
<h1>{{$user['name']}}</h1>

<ul>
    @foreach($user['hobbies'] as $hobby)
        <li class="red">{{$hobby}}</li>
    @endforeach
</ul>
</body>
</html>
