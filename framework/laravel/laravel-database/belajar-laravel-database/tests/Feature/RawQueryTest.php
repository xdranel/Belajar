<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Support\Facades\DB;
use Tests\TestCase;

class RawQueryTest extends TestCase
{
    protected function setUp(): void
    {
        parent::setUp();
        DB::delete('DELETE FROM categories');
    }

    public function testCRUD()
    {
        DB::insert('INSERT INTO categories (id, name, description, created_at) VALUES (?,?,?,?)', [
            'GADGET', 'Gadget', 'Gadget Description', '2021-01-01 00:00:00'
        ]);

        $result = DB::select('SELECT * FROM categories where id = ?', ['GADGET']);

        self::assertCount(1, $result);
        self::assertEquals('GADGET', $result[0]->id);
        self::assertEquals('Gadget', $result[0]->name);
        self::assertEquals('Gadget Description', $result[0]->description);
        self::assertEquals('2021-01-01 00:00:00', $result[0]->created_at);
    }

    public function testCRUDNamedParameters()
    {
        DB::insert('INSERT INTO categories (id, name, description, created_at) VALUES (:id,:name,:description,:created_at)', [
            'id' => 'GADGET',
            'name' => 'Gadget',
            'description' => 'Gadget Description',
            'created_at' => '2021-01-01 00:00:00'
        ]);

        $result = DB::select('SELECT * FROM categories where id = ?', ['GADGET']);

        self::assertCount(1, $result);
        self::assertEquals('GADGET', $result[0]->id);
        self::assertEquals('Gadget', $result[0]->name);
        self::assertEquals('Gadget Description', $result[0]->description);
        self::assertEquals('2021-01-01 00:00:00', $result[0]->created_at);
    }


}
