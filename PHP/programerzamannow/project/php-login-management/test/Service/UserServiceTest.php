<?php

namespace Grp\Belajar\Service;

use Grp\Belajar\Config\Database;
use Grp\Belajar\Domain\User;
use Grp\Belajar\Exception\ValidationException;
use Grp\Belajar\Model\UserLoginRequest;
use Grp\Belajar\Model\UserPasswordUpdateRequest;
use Grp\Belajar\Model\UserProfileUpdateRequest;
use Grp\Belajar\Model\UserRegisterRequest;
use Grp\Belajar\Repository\SessionRepository;
use Grp\Belajar\Repository\UserRepository;
use PHPUnit\Framework\TestCase;
use function PHPUnit\Framework\assertEquals;

class UserServiceTest extends TestCase
{
    private UserService $userService;
    private UserRepository $userRepository;
    private SessionRepository $sessionRepository;

    protected function setUp(): void
    {
        $connection = Database::getConnection();
        $this->userRepository = new UserRepository($connection);
        $this->userService = new UserService($this->userRepository);
        $this->sessionRepository = new SessionRepository($connection);

        $this->sessionRepository->deleteAll();
        $this->userRepository->deleteAll();
    }

    public function testRegisterSuccess()
    {
        $request = new UserRegisterRequest();
        $request->id = "JOKO";
        $request->name = "Purnomo";
        $request->password = "rahasia";

        $response = $this->userService->register($request);

        self::assertEquals($request->id, $response->user->id);
        self::assertEquals($request->name, $response->user->name);
        self::assertNotEquals($request->password, $response->user->password);

        self::assertTrue(password_verify($request->password, $response->user->password));
    }

    public function testRegisterFailed()
    {
        $this->expectException(ValidationException::class);

        $request = new UserRegisterRequest();
        $request->id = "";
        $request->name = "";
        $request->password = "";

        $this->userService->register($request);
    }

    public function testRegisterDuplicate()
    {
        $user = new User();
        $user->id = "JOKO";
        $user->name = "Purnomo";
        $user->password = "rahasia";

        $this->userRepository->save($user);

        $this->expectException(ValidationException::class);

        $request = new UserRegisterRequest();
        $request->id = "JOKO";
        $request->name = "Purnomo";
        $request->password = "rahasia";

        $this->userService->register($request);
    }

    public function testLoginNotFound()
    {
        $this->expectException(ValidationException::class);

        $request = new UserLoginRequest();
        $request->id = "JOKO";
        $request->password = "rahasia";

        $this->userService->login($request);
    }

    public function testLoginWrongPassword()
    {
        $user = new User();
        $user->id = "JOKO";
        $user->name = "Purnomo";
        $user->password = password_hash("rahasia", PASSWORD_BCRYPT);

        $this->expectException(ValidationException::class);

        $request = new UserLoginRequest();
        $request->id = "JOKO";
        $request->password = "jkl";

        $this->userService->login($request);
    }

    public function testLoginSuccess()
    {
        $user = new User();
        $user->id = "JOKO";
        $user->name = "Purnomo";
        $user->password = password_hash("rahasia", PASSWORD_BCRYPT);

        $this->expectException(ValidationException::class);

        $request = new UserLoginRequest();
        $request->id = "JOKO";
        $request->password = "rahasia";

        $this->userService->login($request);

        self::assertEquals($request->id, $user->id);
        self::assertTrue(password_verify($request->password, $user->password));
    }

    public function testUpdateSuccess()
    {
        $user = new User();
        $user->id = "JOKO";
        $user->name = "Purnomo";
        $user->password = password_hash("rahasia", PASSWORD_BCRYPT);
        $this->userRepository->save($user);

        $request = new UserProfileUpdateRequest();
        $request->id = "JOKO";
        $request->name = "Purnomo";

        $this->userService->updateProfile($request);

        $result = $this->userRepository->findById($user->id);

        self::assertEquals($request->name, $result->name);
    }

    public function testUpdateValidationError()
    {
        $this->expectException(ValidationException::class);
        $request = new UserProfileUpdateRequest();
        $request->id = "";
        $request->name = "";

        $this->userService->updateProfile($request);
    }

    public function testUpdateNotFound()
    {
        $this->expectException(ValidationException::class);
        $request = new UserProfileUpdateRequest();
        $request->id = "JOKO";
        $request->name = "Purnomo";

        $this->userService->updateProfile($request);

    }

    public function testUpdatePasswordSuccess()
    {
        $user = new User();
        $user->id = "JOKO";
        $user->name = "Purnomo";
        $user->password = password_hash("rahasia", PASSWORD_BCRYPT);
        $this->userRepository->save($user);

        $request = new UserPasswordUpdateRequest();
        $request->id = "JOKO";
        $request->oldPassword = "rahasia";
        $request->newPassword = "secret";

        $this->userService->updatePassword($request);

        $result = $this->userRepository->findById($user->id);
        self::assertTrue(password_verify($request->newPassword, $result->password));
    }

    public function testUpdatePasswordValidationError()
    {
        $this->expectException(ValidationException::class);

        $request = new UserPasswordUpdateRequest();
        $request->id = "JOKO";
        $request->oldPassword = "";
        $request->newPassword = "";

        $this->userService->updatePassword($request);
    }

    public function testUpdatePasswordWrongOldPassword()
    {
        $this->expectException(ValidationException::class);

        $user = new User();
        $user->id = "JOKO";
        $user->name = "Purnomo";
        $user->password = password_hash("rahasia", PASSWORD_BCRYPT);
        $this->userRepository->save($user);

        $request = new UserPasswordUpdateRequest();
        $request->id = "JOKO";
        $request->oldPassword = "salah";
        $request->newPassword = "secret";

        $this->userService->updatePassword($request);
    }

    public function testUpdatePasswordNotFound()
    {
        $this->expectException(ValidationException::class);

        $request = new UserPasswordUpdateRequest();
        $request->id = "JOKO";
        $request->oldPassword = "rahasia";
        $request->newPassword = "secret";

        $this->userService->updatePassword($request);
    }


}
