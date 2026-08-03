<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="{{ asset('style.css') }}">
</head>
<body>
    <div class="container">
        <h1>Selamat datang, {{ Auth::user()->name }}</h1> 
        
        <div class="action-row" style="margin-left: 0;">
            <a href="/logout" class="btn-logout">Logout</a> 
        </div>
    </div>
</body>
</html>