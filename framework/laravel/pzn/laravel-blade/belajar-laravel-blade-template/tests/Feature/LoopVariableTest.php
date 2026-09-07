<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class LoopVariableTest extends TestCase
{
    public function testLoopVariable()
    {
        $this->view('loop-variable', ["hobbies" => ["Gaming", "Reading"]])
            ->assertSeeText('1. Gaming')
            ->assertSeeText('2. Reading');
    }

}
