<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Auth\Events\Registered;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Validation\Rule;
use Inertia\Inertia;

class RegisterController extends Controller
{
    public function create()
    {
        return Inertia::render('Auth/Register');
    }

    public function store(Request $request)
    {
        $credentials = $request->validate([
            'name' => ['required', 'string', 'max:255'],
            'email' => ['required', 'string', 'email:rfc,spoof', 'max:255', Rule::unique('users', 'email')],
            'password' => ['required', 'string', 'min:3', 'confirmed'],
        ]);

        $user = User::create($credentials);

        // Send verification email for later
        event(new Registered($user));

        Auth::login($user);

        return redirect()->route('home');
    }
}
