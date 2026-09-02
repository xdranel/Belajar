<?php

namespace App\Http\Controllers;

use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\View\View;

class RedirectController extends Controller
{
    public function redirectTo(): string
    {
        return "Redirect to";
    }

    public function redirectFrom(): RedirectResponse
    {
        return redirect("/redirect/to");
    }

    public function redirectHello(string $name): View
    {
        return view('hello', ['name' => $name]);
    }

    public function redirectName(): RedirectResponse
    {
        return redirect()->route('redirect-hello', [
            'name' => 'Gendhi'
        ]);
    }

    public function redirectAction(): RedirectResponse
    {
        return redirect()->action([RedirectController::class, 'redirectHello'], ['name' => 'Ramona']);
    }

    public function redirectAway(): RedirectResponse
    {
        return redirect()->away('https://www.google.com');
    }
}

