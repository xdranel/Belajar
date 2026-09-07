<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class IncludeTest extends TestCase
{
    public function testInclude()
    {
        $this->view("include", [])
            ->assertSeeText("Gendhi Ramona Prastyo")
            ->assertSeeText("Welcome To Our Website")
            ->assertSeeText("Welcome To My Web");

        $this->view("include", ["title" => "John"])
            ->assertSeeText("John")
            ->assertSeeText("Welcome To Our Website")
            ->assertSeeText("Welcome To My Web");
    }
}
