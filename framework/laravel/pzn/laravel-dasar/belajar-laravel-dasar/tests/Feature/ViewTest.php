<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class ViewTest extends TestCase
{
    public function testView()
    {
        $this->get('/hello')
            ->assertSeeText('Hello Gendhi');

        $this->get('/hello-again')
            ->assertSeeText('Hello Ramona');
    }

    public function testNested()
    {
        $this->get('/hello-world')
            ->assertSeeText('World Ramona');
    }

    public function testViewWithoutRoute()
    {
        $this->view('hello', ['name' => 'Gendhi'])
            ->assertSeeText('Hello Gendhi');

        $this->view('hello.world', ['name' => 'Gendhi'])
            ->assertSeeText('World Gendhi');
    }
}
