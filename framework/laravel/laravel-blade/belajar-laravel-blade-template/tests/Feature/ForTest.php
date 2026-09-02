<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class ForTest extends TestCase
{
    public function testFor()
    {
        $this->view("for", ["limit" => 5])
            ->assertSeeText('0')
            ->assertSeeText('1')
            ->assertSeeText('2')
            ->assertSeeText('3')
            ->assertSeeText('4');
    }

    public function testForEach()
    {
        $this->view("foreach", ["hobbies" => ["Gaming", "Reading", "Coding"]])
            ->assertSeeText('Gaming')
            ->assertSeeText('Reading')
            ->assertSeeText('Coding');
    }

    public function testForElse()
    {
        $this->view("forelse", ["hobbies" => ["Gaming", "Reading"]])
            ->assertSeeText('Gaming')
            ->assertSeeText('Reading');

        $this->view("forelse", ["hobbies" => []])
            ->assertDontSeeText('Gaming')
            ->assertDontSeeText('Reading')
            ->assertSeeText('There is no hobbies');
    }


}
