<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User; 
use Illuminate\Support\Facades\Auth; 
use Illuminate\Support\Facades\Hash; 

class AuthController extends Controller
{
    public function login()
    {
        return view('login');
    }

    public function auth(Request $request)
    {
        $credentials = $request->only('email', 'password');

        if (Auth::attempt($credentials)) {
            $request->session()->regenerate();
            return redirect('/home');
        }

        return redirect('/login')->with('error', 'Email / password salah');
    }
   
    public function registration()
    {
        return view('registration');
    }

    public function register(Request $request)
    {
        User::create([
            'name' => $request->name,
            'email' => $request->email,
            'password' => Hash::make($request->password) 
        ]);

        return redirect('/registration')->with('success', 'Registrasi berhasil');
    }

    public function home()
    {
        if (Auth::check()) {
            return view('home'); 
        }

        return redirect('/login');
    }

    public function logout()
    {
        Auth::logout();
        
        return redirect('/login');
    }
}