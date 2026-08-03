<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="{{ asset('style.css') }}">
</head>
<body>
    <div class="container">
        <h1>Login</h1>

        @if(session('error'))
            <div class="alert" style="color: red;">{{ session('error') }}</div>
        @endif

        <form action="/auth" method="POST">
            @csrf
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" required>
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required>
            </div>

            <div class="action-row">
                <button type="submit" class="btn-primary">Login</button>
                <a href="/registration" class="link-text">Belum punya akun? Register</a>
            </div>
        </form>
    </div>
</body>
</html>