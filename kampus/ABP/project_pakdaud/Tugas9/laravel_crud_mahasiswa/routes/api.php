<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\MahasiswaController;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

Route::get('/mahasiswas', [MahasiswaController::class,'index']);
Route::post('/mahasiswas', [MahasiswaController::class,'store']);
Route::get('/mahasiswas/{mahasiswa}', [MahasiswaController::class,'show']);
Route::put('/mahasiswas/{mahasiswa}', [MahasiswaController::class,'update']);
Route::delete('/mahasiswas/{mahasiswa}', [MahasiswaController::class,'destroy']);