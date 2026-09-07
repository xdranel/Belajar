<?php

namespace Tests\Feature;

use App\Services\UserService;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Support\Facades\App;
use Tests\TestCase;

class UserServiceTest extends TestCase
{
    private UserService $userService;

    protected function setUp(): void
    {
        parent::setUp();

        $this->userService = App::make(UserService::class);
//        $this->userService = $this->app->make(UserService::class);
    }

    public function testLoginSuccess()
    {
        self::assertTrue($this->userService->login('admin', '123456'));
    }

    public function testLoginUserNotFound()
    {
        self::assertFalse($this->userService->login('eko', '1234567'));
    }

    public function testLoginPasswordIncorrect()
    {
        self::assertFalse($this->userService->login('admin', '12345'));
    }
}
