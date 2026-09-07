<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class IfTest extends TestCase
{
    public function testIfStatement()
    {
        $this->view("if", ['hobbies' => []] )
            ->assertSeeText('I dont have any hobbies');

        $this->view("if", ['hobbies' => ['Gaming']] )
            ->assertSeeText('I have one hobby');

        $this->view("if", ['hobbies' => ['Gaming', 'Reading']] )
            ->assertSeeText('I have multiple hobbies');
    }

}
