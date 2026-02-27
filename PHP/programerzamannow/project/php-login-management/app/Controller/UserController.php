<?php

namespace Grp\Belajar\Controller;

use Grp\Belajar\App\View;
use Grp\Belajar\Config\Database;
use Grp\Belajar\Exception\ValidationException;
use Grp\Belajar\Model\UserLoginRequest;
use Grp\Belajar\Model\UserPasswordUpdateRequest;
use Grp\Belajar\Model\UserProfileUpdateRequest;
use Grp\Belajar\Model\UserRegisterRequest;
use Grp\Belajar\Repository\SessionRepository;
use Grp\Belajar\Repository\UserRepository;
use Grp\Belajar\Service\SessionService;
use Grp\Belajar\Service\UserService;

class UserController
{
    private UserService $userService;
    private SessionService $sessionService;

    public function __construct()
    {
        $connection = Database::getConnection();
        $userRepository = new UserRepository($connection);
        $this->userService = new UserService($userRepository);

        $sessionRepository = new SessionRepository($connection);
        $this->sessionService = new SessionService($sessionRepository, $userRepository);
    }

    public function register()
    {
        View::render('User/register', [
            'title' => 'Register New User',
//            'error' => 'A simple primary alert—check it out!'
        ]);
    }

    public function postRegister()
    {
        $request = new UserRegisterRequest();
        $request->id = $_POST['id'];
        $request->name = $_POST['name'];
        $request->password = $_POST['password'];

        try {
            $this->userService->register($request);

            // redirect to /users/login
            View::redirect('/users/login');

        } catch (ValidationException $exception) {
            View::render('User/register', [
                'title' => 'Register New User',
                'error' => $exception->getMessage(),
            ]);
        }
    }

    public function login()
    {
        View::render('User/login', [
            'title' => 'Login user',
        ]);
    }

    public function postLogin()
    {
        $request = new UserLoginRequest();
        $request->id = $_POST['id'];
        $request->password = $_POST['password'];

        try {
            $response = $this->userService->login($request);
            $this->sessionService->create($response->user->id);
            View::redirect('/');
        } catch (ValidationException $exception) {
            View::render('User/login', [
                'title' => 'Login user',
                'error' => $exception->getMessage(),
            ]);
        }
    }

    public function logout()
    {
        $this->sessionService->destroy();
        View::redirect('/');
    }

    public function updateProfile()
    {
        $user = $this->sessionService->current();
        View::render('User/profile', [
            'title' => 'Update User Profile',
            'user' =>  [
                'id' => $user->id,
                'name' => $user->name,
            ],
        ]);
    }

    public function postUpdateProfile()
    {
        $user = $this->sessionService->current();

        $request = new UserProfileUpdateRequest();
        $request->id = $user->id;
        $request->name = $_POST['name'];

        try {
            $this->userService->updateProfile($request);
            View::redirect('/');
        } catch (ValidationException $exception){
            $user = $this->sessionService->current();
            View::render('User/profile', [
                'title' => 'Update User Profile',
                'error' => $exception->getMessage(),
                'user' =>  [
                    'id' => $user->id,
                    'name' => $_POST['name'],
                ],
            ]);
        }
    }

    public function updatePassword()
    {
        $user = $this->sessionService->current();
        View::render('User/password', [
            'title' => 'Update User Password',
            'user' =>  [
                'id' => $user->id,
            ]
        ]);
    }

    public function postUpdatePassword()
    {
        $user = $this->sessionService->current();
        $request = new UserPasswordUpdateRequest();
        $request->id = $user->id;
        $request->oldPassword = $_POST['oldPassword'];
        $request->newPassword = $_POST['newPassword'];

        try {
            $this->userService->updatePassword($request);
            View::redirect('/');
        } catch (ValidationException $exception) {
            View::render('User/password', [
                'title' => 'Update User Password',
                'error' => $exception->getMessage(),
                'user' =>  [
                    'id' => $user->id,
                ]
            ]);
        }
    }
}