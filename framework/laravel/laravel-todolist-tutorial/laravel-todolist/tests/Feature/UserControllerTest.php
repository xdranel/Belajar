<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class UserControllerTest extends TestCase
{
    public function testLoginPage()
    {
        $this->get('/login')
            ->assertStatus(200)
            ->assertSeeText('Login');
    }

    public function testLoginPageForMember()
    {
        $this->withSession([
            "user" => "admin"
        ])->get('/login')
            ->assertRedirect("/");
    }

    public function testLoginSuccess()
    {
        $this->post('/login', [
            "user" => "admin",
            "password" => "123456",
        ])->assertRedirect("/")
            ->assertSessionHas("user", "admin");
    }

    public function testLoginForUserAlreadyLoggedIn()
    {
        $this->withSession([
            "user" => "admin"
        ])->post('/login', [
            "user" => "admin",
            "password" => "123456",
        ])->assertRedirect("/");
    }

    public function testLoginValidationError()
    {
        $this->post('/login', [])
            ->assertSeeText("User or password is required");
    }

    public function testLoginFailed()
    {
        $this->post('/login', [
            "user" => "hm",
            "password" => "hm",
        ])->assertSeeText("User or password is incorrect");
    }

    public function testLogout()
    {
        $this->withSession([
            "user" => "admin"
        ])->post("/logout")
            ->assertRedirect("/")
            ->assertSessionMissing("user");
    }

    public function testLogoutGuest()
    {
        $this->post("/logout")
            ->assertRedirect("/");
    }

}
