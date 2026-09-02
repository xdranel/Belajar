<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Class CSS Page</title>
    <style>
        .red {
            color: red;
        }

        .bold {
            font-weight: bold;
        }
    </style>
</head>
<body>
@foreach($hobbies as $hobby)
    {{--    <li class="@if($hobby['love']) bold @endif red">{{$hobby['name']}}</li>--}}
    <li @class(["red", "bold" => $hobby["love"]])>{{$hobby['name']}}</li>

@endforeach
</body>
</html>
