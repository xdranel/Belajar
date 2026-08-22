<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Lat1Controller;

Route::get('/',
    function () {return view('welcome');
});

Route::get('/lat1',
    [Lat1Controller::class, 'index']
);

Route::get('/lat1/m2',
    [Lat1Controller::class, 'method2']
);
