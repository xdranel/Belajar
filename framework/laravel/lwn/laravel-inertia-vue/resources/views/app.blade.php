<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    @vite('resources/js/app.js')
    @vite('resources/css/app.css')
    <x-inertia::head />
    @routes
</head>
<body>
    <x-inertia::app />
</body>
</html>
