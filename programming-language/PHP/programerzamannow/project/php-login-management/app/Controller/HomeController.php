<?php

namespace Grp\Belajar\Controller;

use Grp\Belajar\App\View;
use Grp\Belajar\Config\Database;
use Grp\Belajar\Repository\SessionRepository;
use Grp\Belajar\Repository\UserRepository;
use Grp\Belajar\Service\SessionService;

class HomeController
{
    private SessionService $sessionService;

    public function __construct()
    {
        $connection = Database::getConnection();
        $sessionRepository = new SessionRepository($connection);
        $userRepository = new UserRepository($connection);
        $this->sessionService = new SessionService($sessionRepository, $userRepository);
    }

    function index()
    {
        $user = $this->sessionService->current();
        if ($user == null) {
            View::render('Home/index', [
                "title" => "PHP Login Management",
            ]);
        } else {
            View::render('Home/dashboard', [
                "title" => "Dashboard",
                "user" => [
                    "name" => $user->name,
                ]
            ]);
        }
    }

//View::render('Home/dashboard', ["title" => "Dashboard",
//"user" => ["name" => $user->name,],]);
}