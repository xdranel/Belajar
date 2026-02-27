<?php

namespace Grp\Belajar\Controller;

use Grp\Belajar\Config\Database;
use Grp\Belajar\Domain\Session;
use Grp\Belajar\Domain\User;
use Grp\Belajar\Repository\SessionRepository;
use Grp\Belajar\Repository\UserRepository;
use Grp\Belajar\Service\SessionService;
use PHPUnit\Framework\TestCase;

class HomeControllerTest extends TestCase
{

    private HomeController $homeController;
    private UserRepository $userRepository;
    private SessionRepository $sessionRepository;

    protected function setUp(): void
    {
        $this->homeController = new HomeController();
        $this->sessionRepository = new SessionRepository(Database::getConnection());
        $this->userRepository = new UserRepository(Database::getConnection());

        $this->sessionRepository->deleteAll();
        $this->userRepository->deleteAll();
    }

    public function testGuest()
    {
        $this->homeController->index();

        $this->expectOutputRegex("[Login Management]");
    }

    public function testUserLogin()
    {
        $user = new User();
        $user->id = "joko";
        $user->name = "Joko";
        $user->password = "rahasia";
        $this->userRepository->save($user);

        $session = new Session();
        $session->id = uniqid();
        $session->userId = $user->id;
        $this->sessionRepository->save($session);

        $_COOKIE[SessionService::$COOKIE_NAME] = $session->id;

        $this->homeController->index();

        $this->expectOutputRegex("[Hello]");
    }
}
