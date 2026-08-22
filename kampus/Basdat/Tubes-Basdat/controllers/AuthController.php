<?php
session_start();
require_once '../models/User.php';
require_once '../models/Activity.php';

class AuthController
{
    private $user;

    public function __construct()
    {
        $this->user = new User();
    }

    public function login()
    {
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $username = trim($_POST['username']);
            $password = trim($_POST['password']);

            if (empty($username) || empty($password)) {
                return $this->response(false, 'Username dan password harus diisi');
            }

            if ($this->user->login($username, $password)) {
                $_SESSION['user_id'] = $this->user->id;
                $_SESSION['username'] = $this->user->username;
                $_SESSION['name'] = $this->user->name;
                $_SESSION['role'] = $this->user->role;
                $_SESSION['logged_in'] = true;

                Activity::log($this->user->id, 'Login', 'User logged in successfully');
                return $this->response(true, 'Login berhasil');
            } else {
                Activity::log(null, 'Failed Login', "Failed login attempt for username: $username", 'failed');
                return $this->response(false, 'Username atau password salah');
            }
        }

    }

    public function logout()
    {
        if (isset($_SESSION['user_id'])) {
            Activity::log($_SESSION['user_id'], 'Logout', 'User logged out');
        }
        $_SESSION = array();
        session_destroy();

        $redirectPath = dirname($_SERVER['PHP_SELF']) . './index.php';
        header('Location: ' . $redirectPath);
        exit();
    }


    public function isLoggedIn()
    {
        return isset($_SESSION['logged_in']) && $_SESSION['logged_in'] === true;
    }

    public function isAdmin()
    {
        return $this->isLoggedIn() && $_SESSION['role'] === 'admin';
    }

    public function requireAuth()
    {
        if (!$this->isLoggedIn()) {
            header('Location: ../public/index.php');
            exit();
        }
    }

    public function requireAdmin()
    {
        if (!$this->isAdmin()) {
            header('Location: ../public/index.php');
            exit();
        }
    }

    private function response($success, $message)
    {
        return [
            'success' => $success,
            'message' => $message
        ];
    }
}