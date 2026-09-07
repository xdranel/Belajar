<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class SampleTest extends TestCase
{
    public function testGet()
    {
        $this->get('/hello')
            ->assertStatus(200)
            ->assertSeeText("Hello");

    }

}
