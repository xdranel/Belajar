<?php

namespace App\Http\Controllers;

use App\Events\UserSubscribed;
use App\Models\User;
use Illuminate\Auth\Events\Registered;
use Illuminate\Foundation\Auth\EmailVerificationRequest;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class AuthController extends Controller
{
    //Register User
    public function register(Request $request)
    {
        // Validate
        $fields = $request->validate([
            'username' => ['required', 'string', 'max:255'],
            'email' => ['required', 'string', 'email', 'max:255', 'unique:users'],
            'password' => ['required', 'string', 'min:3', 'confirmed'],
        ]);

        // Register
        $user = User::create($fields);

        // Login
        Auth::login($user);

        event(new Registered($user));

        if ($request->subscribe) {
            event(new UserSubscribed($user));
        }

        // Redirect
        return redirect()->route('dashboard');
    }

    // Verify Email notice handler
    public function verifyNotice()
    {
        return view('auth.verify-email');
    }

    // Email Verification Handler
    public function verifyEmail(EmailVerificationRequest $request) {
        $request->fulfill();

        return redirect()->route('dashboard');
    }

    // Resend Email Verification Handler
    public function verifyHandler(Request $request) {
        $request->user()->sendEmailVerificationNotification();

        return back()->with('message', 'Verification link sent!');
    }

    //Login User
    public function login(Request $request)
    {
        // Validate
        $fields = $request->validate([
            'email' => ['required', 'string', 'email', 'max:255'],
            'password' => ['required', 'string'],
        ]);

        // Login
        if (Auth::attempt($fields, $request->remember)){
            return redirect()->intended('dashboard');
        } else {
            return back()->withErrors([
                'failed' => 'The provided credentials do not match our records.'
            ]);
        }
    }

    // Logout User
    public function logout(Request $request)
    {
        // Logout
        Auth::logout();

        // Invalidate user session
        $request->session()->invalidate();

        // Regenerate CSRF Token
        $request->session()->regenerateToken();

        // Redirect to home
        return redirect()->route('login');
    }
}
