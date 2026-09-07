<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class HelloTest extends TestCase
{
    public function testHello()
    {
        $this->get('/hello')
            ->assertSeeText('Dashboard Hello')
            ->assertSeeText('Hello John Doe');
    }

    public function testWorld()
    {
        $this->get('/world')
            ->assertSeeText('Dashboard World')
            ->assertSeeText('Hello John Doe');

    }

    public function testWithoutRouting()
    {
        $this->view('hello', [
            'title' => 'Dashboard Without Routing',
            'name' => 'John Doe'
        ])->assertSeeText('Dashboard Without Routing')
            ->assertSeeText('John Doe');

        $this->view('hello.world', [
            'title' => 'Dashboard Without Routing',
            'name' => 'John Doe'
        ])->assertSeeText('Dashboard Without Routing')
            ->assertSeeText('John Doe');

    }


}
