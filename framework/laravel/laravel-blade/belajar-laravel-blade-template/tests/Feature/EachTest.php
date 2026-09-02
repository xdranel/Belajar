<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class EachTest extends TestCase
{
    public function testEach()
    {
        $this->view("each", ["users" => [
            [
                "name" => "John",
                "hobbies" => ["Gaming", "Reading"]
            ],
            [
                "name" => "Jane",
                "hobbies" => ["Coding"]
            ]
        ]])->assertSeeInOrder([
            ".red",
            "John",
            "Gaming",
            "Reading",
            "Jane",
            "Coding",
        ]);
    }

}
