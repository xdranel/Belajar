<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class TodolistControllerTest extends TestCase
{
    public function testTodolist()
    {
        $this->withSession([
            "user" => "admin",
            "todolist" => [
                [
                    "id" => "1",
                    "todo" => "Laravel"
                ],
                [
                    "id" => "2",
                    "todo" => "PHP"
                ]
            ]
        ])->get('/todolist')
            ->assertSeeText('1')
            ->assertSeeText('Laravel');
    }

    public function testAddTodoFailed()
    {
        $this->withSession([
            "user" => "admin",
        ])->post("/todolist", [])
            ->assertSeeText("Todo is required");
    }

    public function testAddTodoSuccess()
    {
        $this->withSession([
            "user" => "admin",
        ])->post("/todolist", [
            "todo" => "Laravel",
        ])->assertRedirect("/todolist");
    }

    public function testRemoveTodolist()
    {
        $this->withSession([
            "user" => "admin",
            "todolist" => [
                [
                    "id" => "1",
                    "todo" => "Laravel"
                ],
                [
                    "id" => "2",
                    "todo" => "PHP"
                ]
            ]
        ])->post("/todolist/1/delete")
            ->assertRedirect("/todolist");
    }
}
