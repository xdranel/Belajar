<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="{{ asset('style.css') }}">
</head>
<body>
    <div class="container">
        <h1>Registration</h1>

        @if(session('success'))
            <div class="alert" style="color: green;">{{ session('success') }}</div> 
        @endif

        <form action="/register" method="POST">
            @csrf
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" required> 
            </div>

            <div class="form-group">
                <label>Nama</label>
                <input type="text" name="name" required> 
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required> 
            </div>

            <div class="action-row">
                <button type="submit" class="btn-primary">Register</button> 
                <a href="/login" class="link-text">Sudah punya akun? Login</a> 
            </div>
        </form>
    </div>
</body>
</html>