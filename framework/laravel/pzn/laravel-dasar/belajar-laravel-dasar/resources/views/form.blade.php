<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Form</title>
</head>
<body>
<h1>Form Say Hello</h1>
<form action="/form" method="post">
    <label for="name">
        Name: <input type="text" name="name">
    </label>
    <input type="submit" value="Say Hello">
    <input type="hidden" name="_token" value="{{ csrf_token() }}">
</form>
</body>
</html>
