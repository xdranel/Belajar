<?php

namespace Grp\Belajar\Middleware;

use Grp\Belajar\App\View;
use Grp\Belajar\Config\Database;
use Grp\Belajar\Repository\SessionRepository;
use Grp\Belajar\Repository\UserRepository;
use Grp\Belajar\Service\SessionService;

class MustNotLoginMiddleware implements Middleware
{
    private SessionService $sessionService;

    public function __construct()
    {
        $sessionRepository = new SessionRepository(Database::getConnection());
        $userRepository = new UserRepository(Database::getConnection());
        $this->sessionService = new SessionService($sessionRepository, $userRepository);
    }

    function before(): void
    {
        $user = $this->sessionService->current();
        if ($user != null) {
            View::redirect('/');
        }
    }
}