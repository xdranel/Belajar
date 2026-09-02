<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class SwitchTest extends TestCase
{
    public function testSwitch()
    {
        $this->view('switch', ["value" => "A"])
            ->assertSeeText('Happy');

        $this->view('switch', ["value" => "B"])
            ->assertSeeText('Sad');

        $this->view('switch', ["value" => "C"])
            ->assertSeeText('Angry');

        $this->view('switch', ["value" => "D"])
            ->assertSeeText('Boring');

    }

}
