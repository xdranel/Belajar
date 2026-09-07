<?php

namespace App\Http\Controllers;

use App\Models\Post;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Routing\Controllers\HasMiddleware;
use Illuminate\Support\Facades\Auth;

class DashboardController extends Controller
{
    public function index()
    {
        // Manual Way to get the user id
        // $posts = Post::where('user_id', auth()->user()->id)->get();

        $posts = Auth::user()->posts()->latest()->paginate(4);
        // $posts = Auth::user()->posts()->latest()->simplePaginate(4);

        return view('users.dashboard', ['posts' => $posts]);
    }

    // If you have complex middleware is better to manage it on the controller or middleware
    // but if it's just simple like this, you can use it on route
//    public static function middleware(): array
//    {
//        return [
//            'auth',
//        ];
//    }

    public function userPosts(User $user)
    {
        $userPosts = $user->posts()->latest()->paginate(4);
        return view('users.posts', [
            'posts' => $userPosts,
            'user' => $user,
        ]);
    }
}
