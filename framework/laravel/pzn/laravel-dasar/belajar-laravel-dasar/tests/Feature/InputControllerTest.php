<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class InputControllerTest extends TestCase
{
    public function testInput()
    {
        $this->get('/input/hello?name=Ramona')
            ->assertSeeText('Hello Ramona');

        $this->post('/input/hello', [
            'name' => 'Ramona'
        ])->assertSeeText('Hello Ramona');
    }

    public function testInputNested()
    {
        $this->post('/input/hello/first', [
            'name' => [
                'first' => 'Ramona',
                'last' => 'Ramadhan'
            ]
        ])->assertSeeText('Hello Ramona Ramadhan');
    }

    public function testInputAll()
    {
        $this->post('/input/hello/input', [
            'name' => [
                'first' => 'Ramona',
                'last' => 'Ramadhan'
            ]
        ])->assertSeeText('name')
            ->assertSeeText('first')
            ->assertSeeText('last')
            ->assertSeeText('Ramona')
            ->assertSeeText('Ramadhan');
    }

    public function testInputArray()
    {
        $this->post('/input/hello/array', [
            'products' => [
                [
                    'name' => 'Macbook Pro',
                    'price' => 30000000
                ],
                [
                    'name' => 'Samsung Galaxy',
                    'price' => 15000000
                ]
            ]
        ])->assertSeeText('Macbook Pro')
            ->assertSeeText('Samsung Galaxy');
    }

    public function testQuery()
    {
        $this->post('/input/hello/query?first=Ramona&last=Ramadhan')
            ->assertSeeText('Ramona')
            ->assertSeeText('Ramadhan');
    }

    public function testName()
    {
        $this->post('/input/type', [
            'name' => 'Ramona',
            'married' => 'true',
            'birth_date' => '2000-01-01'
        ])->assertSeeText('Ramona')
            ->assertSeeText('true')
            ->assertSeeText('2000-01-01');
    }

    public function testFilterOnly()
    {
        $this->post('/input/filter/only', [
            'name' => [
                'first' => 'Gendhi',
                'middle' => 'Ramona',
                'last' => 'Prastyo'
            ]
        ])->assertSeeText('Gendhi')
            ->assertSeeText('Prastyo')
            ->assertDontSeeText('Ramona');
    }

    public function testFilterExcept()
    {
        $this->post('/input/filter/except', [
            "username" => "Gendhi",
            "password" => "secret",
            "admin" => "true",
        ])->assertSeeText('Gendhi')
            ->assertSeeText('secret')
            ->assertDontSeeText('admin');
    }

    public function testFilterMerge()
    {
        $this->post('/input/filter/merge', [
            "username" => "Gendhi",
            "password" => "secret",
            "admin" => "true",
        ])->assertSeeText('Gendhi')
            ->assertSeeText('secret')
            ->assertSeeText('admin')->assertSeeText('false');
    }


}
