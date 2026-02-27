<?php

session_start();

// Mengecek Session
if($_SESSION['login'] == true){
    header("location: /session/member.php");
    exit();
}

$error = "";
if($_SERVER["REQUEST_METHOD"] == "POST"){
    if ($_POST['username'] == 'budi' && $_POST['password'] == 'budi'){
        // sukses
        $_SESSION['login'] = true;
        $_SESSION['username'] = 'budi';
        header('Location: /session/member.php');
        exit();
    } else {
        // gagal
        $error = "Username or Password is Incorrect, Failed To Login";
    }
}
?>

<html lang="en">
<head>
    <title>Contoh Login</title>
</head>
<body>
<?php if ($error != ""){ ?>
    <h2><?= $error ?></h2>
<?php } ?>

<h1>$_SESSION</h1>
<h1>LOGIN</h1>
<form action="/session/login.php" method="post">
    <label>Username :
        <input type="text" name="username" placeholder="Username">
    </label>
    <br>
    <label>Password :
        <input type="text" name="password" placeholder="Password">
    </label>
    <br>
    <input type="submit" value="Login" name="login">
</form>
</body>
</html>