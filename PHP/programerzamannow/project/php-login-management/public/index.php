<?php

require_once __DIR__ . '/../vendor/autoload.php';

use Grp\Belajar\App\Router;
use Grp\Belajar\Config\Database;
use Grp\Belajar\Controller\HomeController;
use Grp\Belajar\Controller\UserController;
use Grp\Belajar\Middleware\MustLoginMiddleware;
use Grp\Belajar\Middleware\MustNotLoginMiddleware;

// Database Connection default = 'test', 'prod' for production
Database::getConnection('prod');

// Home Controller
Router::add('GET', '/', HomeController::class, 'index', []);

// User Controller
Router::add('GET', '/users/register', UserController::class, 'register', [MustNotLoginMiddleware::class]);
Router::add('POST', '/users/register', UserController::class, 'postRegister', [MustNotLoginMiddleware::class]);
Router::add('GET', '/users/login', UserController::class, 'login', [MustNotLoginMiddleware::class]);
Router::add('POST', '/users/login', UserController::class, 'postLogin', [MustNotLoginMiddleware::class]);
Router::add('GET', '/users/logout', UserController::class, 'logout', [MustLoginMiddleware::class]);
Router::add('GET', '/users/profile', UserController::class, 'updateProfile', [MustLoginMiddleware::class]);
Router::add('POST', '/users/profile', UserController::class, 'postUpdateProfile', [MustLoginMiddleware::class]);
Router::add('GET', '/users/password', UserController::class, 'updatePassword', [MustLoginMiddleware::class]);
Router::add('POST', '/users/password', UserController::class, 'postUpdatePassword', [MustLoginMiddleware::class]);

// Running
Router::run();