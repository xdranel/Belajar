<?php

namespace Tests\Feature;

use Illuminate\Database\QueryException;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Support\Facades\DB;
use Tests\TestCase;

class TransactionTest extends TestCase
{
    protected function setUp(): void
    {
        parent::setUp();
        DB::delete('DELETE FROM categories');
    }

    public function testTransactionSuccess()
    {
        DB::transaction(function () {
            DB::insert('INSERT INTO categories (id, name, description, created_at) VALUES (:id,:name,:description,:created_at)', [
                'GADGET', 'Gadget', 'Gadget Description', '2021-01-01 00:00:00'
            ]);
            DB::insert('INSERT INTO categories (id, name, description, created_at) VALUES (:id,:name,:description,:created_at)', [
                'FOOD', 'Food', 'Food Description', '2021-01-01 00:00:00'
            ]);
        });

        $result = DB::select('SELECT * FROM categories');
        self::assertCount(2, $result);
    }

    public function testTransactionFailed()
    {
        try {
            DB::transaction(function () {
                DB::insert('INSERT INTO categories (id, name, description, created_at) VALUES (:id,:name,:description,:created_at)', [
                    'GADGET', 'Gadget', 'Gadget Description', '2021-01-01 00:00:00'
                ]);
                DB::insert('INSERT INTO categories (id, name, description, created_at) VALUES (:id,:name,:description,:created_at)', [
                    'GADGET', 'Food', 'Food Description', '2021-01-01 00:00:00'
                ]);
            });
        } catch (QueryException|\Throwable $e) {
            // expected
        }

        $result = DB::select('SELECT * FROM categories');
        self::assertCount(0, $result);
    }

    public function testManualTransactionSuccess()
    {
        try {
            DB::beginTransaction();

            DB::insert('INSERT INTO categories (id, name, description, created_at) VALUES (:id,:name,:description,:created_at)', [
                'GADGET', 'Gadget', 'Gadget Description', '2021-01-01 00:00:00'
            ]);
            DB::insert('INSERT INTO categories (id, name, description, created_at) VALUES (:id,:name,:description,:created_at)', [
                'FOOD', 'Food', 'Food Description', '2021-01-01 00:00:00'
            ]);

            DB::commit();
        } catch (QueryException|\Throwable $e) {
            DB::rollBack();
        }

        $result = DB::select('SELECT * FROM categories');
        self::assertCount(2, $result);
    }

    public function testManualTransactionFailed()
    {
        try {
            DB::beginTransaction();

            DB::insert('INSERT INTO categories (id, name, description, created_at) VALUES (:id,:name,:description,:created_at)', [
                'GADGET', 'Gadget', 'Gadget Description', '2021-01-01 00:00:00'
            ]);
            DB::insert('INSERT INTO categories (id, name, description, created_at) VALUES (:id,:name,:description,:created_at)', [
                'GADGET', 'Food', 'Food Description', '2021-01-01 00:00:00'
            ]);

            DB::commit();
        } catch (QueryException|\Throwable $e) {
            DB::rollBack();
        }

        $result = DB::select('SELECT * FROM categories');
        self::assertCount(0, $result);
    }

}
