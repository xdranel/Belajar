<?php

namespace Tests\Feature;

use Database\Seeders\CategorySeeder;
use Illuminate\Database\Query\Builder;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
use Tests\TestCase;
use function PHPUnit\Framework\assertCount;
use function PHPUnit\Framework\assertNotEmpty;

class QueryBuilderTest extends TestCase
{
    protected function setUp(): void
    {
        parent::setUp();
        DB::delete('DELETE FROM products');
        DB::delete('DELETE FROM categories');
        DB::delete('DELETE FROM counters');
    }


    public function testInsert()
    {
        DB::table("categories")->insert([
            "id" => "GADGET",
            "name" => "Gadget",
        ]);

        DB::table("categories")->insert([
            "id" => "FOOD",
            "name" => "Food",
        ]);

        $result = DB::select("SELECT COUNT(id) as total FROM categories");
        self::assertEquals(2, $result[0]->total);
    }

    public function testSelect()
    {
        $this->testInsert();

        $collection = DB::table("categories")->select("id", "name")->get();
        self::assertNotNull($collection);

        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function insertCategories()
    {
        $this->seed(CategorySeeder::class);
    }

    public function testWhere()
    {
        $this->insertCategories();

        $collection = DB::table("categories")->where(function (Builder $builder) {
            $builder->where("id", "=", "SMARTPHONE");
            $builder->orWhere("id", "=", "TABLET");
            // SELECT * FROM categories WHERE id = 'SMARTPHONE' OR id = 'TABLET'
            // select * from `categories` where (`id` = ? or `id` = ?)
        })->get();

        self::assertCount(2, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testWhereBetween()
    {
        $this->insertCategories();

        $collection = DB::table("categories")
            ->whereBetween("created_at", ["2021-01-01 00:00:00", "2021-01-02 00:00:00"])->get();
        // select * from `categories` where `created_at` between ? and ?

        self::assertCount(4, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testWhereIn()
    {
        $this->insertCategories();

        $collection = DB::table("categories")->whereIn("id", ["SMARTPHONE", "TABLET"])->get();
        // select * from `categories` where `id` in (?, ?)

        self::assertCount(2, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testWhereNull()
    {
        $this->insertCategories();

        $collection = DB::table("categories")->whereNull("description")->get();
        // select * from `categories` where `description` is null

        self::assertCount(4, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testWhereDate()
    {
        $this->insertCategories();

        $collection = DB::table("categories")->whereDate("created_at", "2021-01-01")->get();
        // select * from `categories` where date(`created_at`) = ?

        self::assertCount(4, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testUpdate()
    {
        $this->insertCategories();

        DB::table("categories")->where("id", "=", "SMARTPHONE")
            ->update(["name" => "Handphone"]);
        // update `categories` set `name` = ? where `id` = ?

        $collection = DB::table("categories")->where("id", "=", "SMARTPHONE")->get();
        // select * from `categories` where `id` = ?

        self::assertEquals("Handphone", $collection[0]->name);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testUpsert()
    {
        DB::table("categories")->updateOrInsert([
            "id" => "VOUCHER"
        ], [
            "name" => "Voucher",
            "description" => "Ticket and Voucher",
            "created_at" => "2021-01-01 00:00:00"
        ]);
        // select exists(select * from `categories` where (`id` = ?)) as `exists`
        // insert into `categories` (`id`, `name`, `description`, `created_at`) values (?, ?, ?, ?)

        $collection = DB::table("categories")->where("id", "=", "VOUCHER")->get();
        self::assertCount(1, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testIncrement()
    {
//        DB::table("counters")->where("id", "=", "sample")
//            ->increment("counter", 1);
        $this->seed(CategorySeeder::class);
        // update `counters` set `counter` = `counter` + 1 where `id` = ?

        $collection = DB::table("counters")->where("id", "=", "sample")->get();
        self::assertCount(0, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testDelete()
    {
        $this->insertCategories();

        DB::table("categories")->where("id", "=", "SMARTPHONE")->delete();
        // delete from `categories` where `id` = ?

        $collection = DB::table("categories")->where("id", "=", "SMARTPHONE")->get();
        self::assertCount(0, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });

    }

    public function insertProducts()
    {
        $this->insertCategories();

        DB::table("products")->insert([
            "id" => "1",
            "name" => "Sony Xperia 5",
            "category_id" => "SMARTPHONE",
            "price" => 21000000,
        ]);
        DB::table("products")->insert([
            "id" => "2",
            "name" => "iPhone 17 Pro",
            "category_id" => "SMARTPHONE",
            "price" => 15000000,
        ]);
    }

    public function testJoin()
    {
        $this->insertProducts();

        $collection = DB::table("products")
            ->join("categories", "products.category_id", "=", "categories.id")
            ->select("products.id", "products.name", "products.price", "categories.name as category_name")
            ->get();
        // select `products`.*, `categories`.`name` as `category_name` from `products` join `categories` on `products`.`category_id` = `categories`.`id`

        self::assertCount(2, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testOrdering()
    {
        $this->insertProducts();

        $collection = DB::table("products")->whereNotNull("id")
            ->orderBy("price", "desc")->orderBy("name", "asc")->get();
        // select * from `products` where `id` is not null order by `price` desc, `name` asc

        self::assertCount(2, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testTakeSkip()
    {
        $this->insertCategories();

        $collection = DB::table("categories")
            ->skip(2)
            ->take(2)
            ->get();

        self::assertCount(2, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function insertManyCategories()
    {
        for ($i = 0; $i < 100; $i++) {
            DB::table("categories")->insert([
                "id" => "CATEGORY-$i",
                "name" => "Category $i",
                "created_at" => "2021-01-01 00:00:00"
            ]);
        }
    }

    public function testChunk()
    {
        // chunk even if not being used the query will be executed
        $this->insertManyCategories();

        DB::table("categories")->orderBy("id", "asc")
            ->chunk(10, function ($categories) {
                self::assertNotNull($categories);
                Log::info("Start chunk");
                $categories->each(function ($item) {
                    Log::info(json_encode($item));
                });
                Log::info("End chunk");
            });

        // select * from categories order by id asc limit 10 offset 0(increments 10, 20 ... End chunk)
    }

    public function testLazy()
    {
        // Lazy chunk even if not being used the query will not be executed
        $this->insertManyCategories();

        $collection = DB::table("categories")->orderBy("id", "asc")
            ->lazy(10)->take(3);
        // select * from categories order by id asc limit 10 offset 0(increments 10, 20 ... End chunk)
        self::assertNotNull($collection);

        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testCursor()
    {
        // Cursor sames as lazy but for memory usage it uses less memory than chunk and lazy
        // but remember cursor can get time out
        $this->insertManyCategories();

        $collection = DB::table("categories")->orderBy("id", "asc")
            ->cursor();
        // select * from categories order by id asc limit 10 offset 0(increments 10, 20 ... End chunk)
        self::assertNotNull($collection);

        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testAggregate()
    {
        $this->insertProducts();

        $result = DB::table("products")->count("id");
        self::assertEquals(2, $result);

        $result = DB::table("products")->sum("price");
        self::assertEquals(36000000, $result);

        $result = DB::table("products")->avg("price");
        self::assertEquals(18000000, $result);

        $result = DB::table("products")->min("price");
        self::assertEquals(15000000, $result);

        $result = DB::table("products")->max("price");
        self::assertEquals(21000000, $result);
    }

    public function testRawAggregate()
    {
        $this->insertProducts();

        $collection = DB::table("products")
            ->select(
                DB::raw("count(id) as total_products"),
                DB::raw("sum(price) as total_price"),
                DB::raw("avg(price) as avg_price"),
                DB::raw("min(price) as min_price"),
                DB::raw("max(price) as max_price")
            )->get();
        // select count(id) as total_products, sum(price) as total_price, avg(price) as avg_price, min(price) as min_price, max(price) as max_price from products

        self::assertEquals(2, $collection[0]->total_products);
        self::assertEquals(36000000, $collection[0]->total_price);
        self::assertEquals(18000000, $collection[0]->avg_price);
        self::assertEquals(15000000, $collection[0]->min_price);
        self::assertEquals(21000000, $collection[0]->max_price);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function insertProductsTablets()
    {
        DB::table("products")->insert([
            "id" => "3",
            "name" => "Samsung Galaxy Tab S7",
            "category_id" => "TABLET",
            "price" => 15000000,
        ]);

        DB::table("products")->insert([
            "id" => "4",
            "name" => "iPad Pro 12",
            "category_id" => "TABLET",
            "price" => 25000000,
        ]);
    }

    public function testGroupBy()
    {
        $this->insertProducts();
        $this->insertProductsTablets();

        $collection = DB::table("products")
            ->select("category_id", DB::raw("count(id) as total_products"))
            ->groupBy("category_id")
            ->orderBy("category_id", "desc")
            ->get();
        // select category_id, count(id) as total_products from products group by category_id order by category_id desc

        self::assertCount(2, $collection);
        self::assertEquals("TABLET", $collection[0]->category_id);
        self::assertEquals("SMARTPHONE", $collection[1]->category_id);
        self::assertEquals(2, $collection[0]->total_products);
        self::assertEquals(2, $collection[1]->total_products);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testGroupByHaving()
    {
        $this->insertProducts();
        $this->insertProductsTablets();

        $collection = DB::table("products")
            ->select("category_id", DB::raw("count(id) as total_products"))
            ->groupBy("category_id")
            ->orderBy("category_id", "desc")
            ->having(DB::raw("count(id)"), ">", 2)
            ->get();
        // select category_id, count(id) as total_products from products group by category_id order by category_id desc having count(id) > ?

        self::assertCount(0, $collection);
        $collection->each(function ($item) {
            Log::info(json_encode($item));
        });
    }

    public function testLocking()
    {
        $this->insertProducts();

        DB::transaction(function () {
            $collection = DB::table("products")
                ->where("id", "=", "1")
                ->lockForUpdate()
                ->get();
            self::assertCount(1, $collection);
        });
        // select * from `products` where `id` = ? for update
    }

    public function testPagination()
    {
        $this->insertCategories();

        $paginate = DB::table("categories")
            ->where("id", "=", "SMARTPHONE")
            ->paginate(perPage: 2, page: 1);

        // select * from `categories` where `id` = ? limit 2 offset 0

        self::assertEquals(1, $paginate->currentPage());
        self::assertEquals(2, $paginate->perPage());
        self::assertEquals(1, $paginate->lastPage());
        self::assertEquals(1, $paginate->total());

        $collection = $paginate->items();
        foreach ($collection as $item) {
            Log::info(json_encode($item));
        }
    }

    public function testIterateAllPagination()
    {
        $this->insertCategories();

        $page = 1;

        while (true) {
            $paginate = DB::table("categories")->paginate(perPage: 2, page: $page);

            if ($paginate->isEmpty()) {
                break;
            } else {
                $page++;

                $collection = $paginate->items();
                assertCount(2, $collection);
                foreach ($collection as $item) {
                    Log::info(json_encode($item));
                }
            }
        }
        // select * from `categories` limit 2 offset 0
    }

    public function testCursorPagination()
    {
        $this->insertCategories();

        $cursor = "id";
        while (true) {
            $paginate = DB::table("categories")->orderBy("id", "asc")
                ->cursorPaginate(perPage: 2, cursor: $cursor);
            // select * from `categories` order by `id` asc limit 3
            // select * from `categories` where (`id` > ?) order by `id` asc limit 3

            foreach ($paginate->items() as $item) {
                self::assertNotNull($item);
                Log::info(json_encode($item));
            }

            $cursor = $paginate->nextCursor();
            if ($cursor == null) {
                break;
            }
        }
    }


}
