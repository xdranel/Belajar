<?php

use App\Http\Controllers\FormController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/grp', function () { // view
    return view('grp');
});

Route::redirect('/youtube', '/grp'); // redirect

Route::fallback(function () {// fallback
    return "404 Not Found by GRP";
});

Route::view('/hello', 'hello', ['name' => 'Gendhi']); // view

Route::get('/hello-again', function () { // closure
    return view('hello', ['name' => 'Ramona']);
});

Route::get('/hello-world', function () { // nested view
    return view('hello.world', ['name' => 'Ramona']);
});

Route::get('/products/{id}', function ($productId) { // parameter
    return "Product $productId";
})->name('product.detail'); // name route

Route::get('/products/{product}/items/{item}', function ($productId, $itemId) { // parameter order
    return "Product $productId, Item $itemId";
})->name('product.item.detail'); // name route

Route::get('/categories/{id}', function ($categoryId) { // regex parameter
    return "Category $categoryId";
})->where('id', '[0-9]+')->name('category.detail'); // name route

Route::get('/users/{id?}', function ($userId = '404') { // optional parameter
    return "User $userId";
})->name('user.detail'); // name route

Route::get('/conflict/ramona', function () {
    return "Conflict Ramona";
});
// conflict parameter
Route::get('/conflict/{name}', function ($name) {
    return "Conflict $name";
});

Route::get('/produk/{id}', function ($id) { // route using named route
    $link = route('product.detail', [
        'id' => $id
    ]);
    return "Link $link";
});

Route::get('/produk-redirect/{id}', function ($id) { // redirect using named route
    return redirect()->route('product.detail', [
        'id' => $id
    ]);
});

// Controller | Route -> Controller -> Method
Route::get('/controller/hello/request', [\App\Http\Controllers\HelloController::class, 'request']);
Route::get('/controller/hello/{name}', [\App\Http\Controllers\HelloController::class, 'hello']);

Route::controller(\App\Http\Controllers\InputController::class)->prefix('/input')->group(function () {
    Route::get('/hello', 'hello');
    Route::post('/hello', 'hello');
    Route::post('/type', 'inputType');

    Route::prefix('/hello')->group(function () {
        Route::post('/first', 'helloFirstName');
        Route::post('/input', 'helloInput');
        Route::post('/array', 'helloArray');
        Route::post('/arrayall', 'helloArrayAll');
        Route::post('/query', 'helloQuery');
    });

    Route::prefix('/filter')->group(function () {
        Route::post('/only', 'filterOnly');
        Route::post('/except', 'filterExcept');
        Route::post('/merge', 'filterMerge');
    });
});

Route::post('/file/upload', [\App\Http\Controllers\FileController::class, 'upload'])
    ->withoutMiddleware([\App\Http\Middleware\VerifyCsrfToken::class]);

Route::controller(\App\Http\Controllers\ResponseController::class)->prefix('/response')->group(function () {
    Route::get('/hello', 'response');
    Route::get('/header', 'header');

    Route::prefix('/type')->group(function () {
        Route::get('/view', 'responseView');
        Route::get('/json', 'responseJson');
        Route::get('/file', 'responseFile');
        Route::get('/download', 'responseDownload');
    });
});

Route::controller(\App\Http\Controllers\CookieController::class)->prefix('/cookie')->group(function () {
    Route::get('/set', 'createCookie');
    Route::get('/get', 'getCookie');
    Route::get('/clear', 'clearCookie');
});

Route::prefix('/redirect')->group(function () {
    Route::controller(\App\Http\Controllers\RedirectController::class)->group(function () {
        Route::get('/from', 'redirectFrom');
        Route::get('/to', 'redirectTo');
        Route::get('/name', 'redirectName');
        Route::get('/name/{name}', 'redirectHello')->name('redirect-hello');
        Route::get('/action', 'redirectAction');
        Route::get('/away', 'redirectAway');
    });

    Route::get('/named', function () {
//        return route('redirect-hello', ['name' => 'Ramona']);
//        return url()->route('redirect-hello', ['name' => 'Ramona']);
        return \Illuminate\Support\Facades\URL::route('redirect-hello', ['name' => 'Ramona']);
    });
});

Route::middleware(['contoh:GRP,401'])->prefix('/middleware')->group(function () {
    Route::get('/api', function () {
        return "OK";
    });
    Route::get('/group', function () {
        return "GROUP";
    });
});

Route::controller(\App\Http\Controllers\FormController::class)->prefix('/form')->group(function () {
    Route::get('', 'form');
    Route::post('', 'submitForm');
});
Route::get('/url/action', function () {
//   return action([\App\Http\Controllers\FormController::class, 'form'], []);
//    return url()->action([FormController::class, 'form'], []);
    return \Illuminate\Support\Facades\URL::action([FormController::class, 'form']);
});

Route::get('/url/current', function () {
    return \Illuminate\Support\Facades\URL::full();
});

Route::get('/session/create', [\App\Http\Controllers\SessionController::class, 'createSession']);
Route::get('/session/get', [\App\Http\Controllers\SessionController::class, 'getSession']);

Route::prefix('/error')->group(function () {
    Route::get('/sample', function () {
        throw new Exception('Sample Error');
    });

    Route::get('/manual', function () {
        report(new Exception('Sample Error'));
        return "OK";
    });

    Route::get('/validation', function () {
        throw new \App\Exceptions\ValidationException("Validation Error");
    });
});

Route::get('/abort/400', function () {
    abort(400, 'Validation Error');
});
Route::get('/abort/401', function () {
    abort(401);
});
Route::get('/abort/500', function () {
    abort(500);
});
