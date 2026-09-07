<?php

use App\Http\Controllers\AuthController;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Route;

//Route::get('/about', function () {
//    return inertia('About', ["name" => "Juan"]);
//});
// same  thing between the two
//Route::inertia('/about', 'About', ['user' => 'Eko'])->name('about');

//Route::inertia('/', 'Home', ['user' => User::all('name')])->name('home');


Route::middleware('auth')->group(function () {
    Route::get('/dashboard', function (Request $request) {
        return inertia('Dashboard', [
            'users' => User::when($request->search, function ($query) use ($request) {
                $query
                    ->where('name', 'like', '%' . $request->search . '%')
                    ->orWhere('email', 'like', '%' . $request->search . '%');
            })->paginate(5)->withQueryString(),

            'searchTerm' => $request->search,

            'can' => [
                'delete_user' => Auth::user()
                    ? Auth::user()->can('delete', User::class)
                    : null,
            ]
        ]);
    })->name('dashboard');

    Route::inertia('/home', 'Home')->name('home');

    Route::post('/logout', [AuthController::class, 'logout'])->name('logout');
});

Route::middleware('guest')->group(function () {
    Route::inertia('/register', 'Auth/Register')->name('register');
    Route::post('/register', [AuthController::class, 'register']);

    Route::inertia('/', 'Auth/Login');
    Route::inertia('/login', 'Auth/Login')->name('login');
    Route::post('/login', [AuthController::class, 'login']);
});




