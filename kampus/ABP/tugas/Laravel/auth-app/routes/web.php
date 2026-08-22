<?php

use App\Http\Controllers\AuthController;

Route::get('/login', [AuthController::class, 'login'])->name('login');
Route::post('/auth', [AuthController::class, 'auth']);
Route::get('/registration', [AuthController::class, 'registration']);
Route::post('/register', [AuthController::class, 'register']);
Route::get('/home', [AuthController::class, 'home']);
Route::get('/logout', [AuthController::class, 'logout']);
