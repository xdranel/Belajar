<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>While Page</title>
</head>
<body>
@while($i < 5)
    The current value is {{$i}}
    @php
        $i++;
    @endphp
@endwhile
</body>
</html>
