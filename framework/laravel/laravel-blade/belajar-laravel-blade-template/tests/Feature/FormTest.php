<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class FormTest extends TestCase
{
    public function testForm()
    {
        $this->view("form", [
            "user" => [
                "premium" => true,
                "name" => "John",
                "admin" => true,
            ]
        ])->assertSee("checked")
            ->assertSee("John")
            ->assertDontSee("readonly");

        $this->view("form", [
            "user" => [
                "premium" => false,
                "name" => "John",
                "admin" => false,
            ]
        ])->assertDontSee("checked")
            ->assertSee("John")
            ->assertSee("readonly");

    }

}
