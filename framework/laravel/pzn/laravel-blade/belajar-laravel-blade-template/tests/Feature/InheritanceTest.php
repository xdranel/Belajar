<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class InheritanceTest extends TestCase
{
    public function testInheritance()
    {
        $this->view("child", [])
            ->assertSeeText("Application Name - Main Page")
            ->assertSeeText("Default Header")
            ->assertSeeText("Header Description")
            ->assertDontSeeText("Default Content")
            ->assertSeeText("This is main page");
    }

    public function testInheritanceWithoutOverride()
    {
        $this->view("child-default", [])
            ->assertSeeText("Application Name - Main Page")
            ->assertSeeText("Default Header")
            ->assertSeeText("Default Content")
            ->assertDontSeeText("Header Description")
            ->assertDontSeeText("This is main page");
    }

}
