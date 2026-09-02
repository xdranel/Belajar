<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class IncludeConditionTest extends TestCase
{
    public function testIncludeCondition()
    {
        $this->view("include-condition", [
            "user" => [
                "name" => "John",
                "owner" => true,
            ],
        ])->assertSeeText("Welcome Owner")
            ->assertSeeText("Welcome John");

        $this->view("include-condition", [
            "user" => [
                "name" => "John",
                "owner" => false,
            ],
        ])->assertDontSeeText("Welcome Owner")
            ->assertSeeText("Welcome John");
    }

}
