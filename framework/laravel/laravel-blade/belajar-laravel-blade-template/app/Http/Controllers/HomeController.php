<?php

namespace App\Http\Controllers;

use Illuminate\Http\RedirectResponse;
use Illuminate\Http\Request;
use Illuminate\Http\Response;

class HomeController extends Controller
{
    public function home(Request $request): Response
    {
        return response()->view('hello', [
            'title' => 'Dashboard',
//            'name' => $request->name,
            'name' => 'John Doe'
        ]);
    }
}
