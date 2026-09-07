<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class CategorySeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table("categories")->insert([
            "id" => "SMARTPHONE",
            "name" => "Smartphone",
            "created_at" => "2021-01-01 00:00:00"
        ]);
        DB::table("categories")->insert([
            "id" => "TABLET",
            "name" => "Tablet",
            "created_at" => "2021-01-01 00:00:00"
        ]);
        DB::table("categories")->insert([
            "id" => "LAPTOP",
            "name" => "Laptop",
            "created_at" => "2021-01-01 00:00:00"
        ]);
        DB::table("categories")->insert([
            "id" => "PC",
            "name" => "PC",
            "created_at" => "2021-01-01 00:00:00"
        ]);
    }
}
