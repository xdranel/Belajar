<?php

namespace Tests\Feature;

use App\Data\Person;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Support\LazyCollection;
use Tests\TestCase;
use function PHPUnit\Framework\assertEquals;

class CollectionTest extends TestCase
{
    public function testCreateCollection()
    {
        $collect = collect([1, 2, 3]);
        $this->assertEquals([1, 2, 3], $collect->toArray());
        self::assertEqualsCanonicalizing([1, 2, 3], $collect->all());
    }

    public function testForEach()
    {
        $collection = collect([1, 2, 3, 4, 5]);
        foreach ($collection as $key => $value) {
            $this->assertEquals($key + 1, $value);
        }
    }

    public function testCRUD()
    {
        $collections = collect([]);
        $collections->push(1, 2, 3, 4, 5);
        $this->assertEqualsCanonicalizing([1, 2, 3, 4, 5], $collections->all());

        $result1 = $collections->pop();
        $this->assertEquals(5, $result1);
        $this->assertEqualsCanonicalizing([1, 2, 3, 4], $collections->all());

        $collections->prepend(6);
        $this->assertEqualsCanonicalizing([6, 1, 2, 3, 4], $collections->all());
    }

    public function testMap()
    {
        $collections = collect([1, 2, 3, 4, 5]);
        $result = $collections->map(function ($item) {
            return $item * 2;
        });
        $this->assertEqualsCanonicalizing([2, 4, 6, 8, 10], $result->all());
    }

    public function testMapInto()
    {
        $collection = collect(["John"]);
        $result = $collection->mapInto(Person::class);
        $this->assertEquals([new Person("John")], $result->all());
    }

    public function testMapSpread()
    {
        $collection = collect([["John", "Doe"], ["Jane", "Doe"]]);
        $result = $collection->mapSpread(function ($firstName, $lastName) {
            $fullName = $firstName . " " . $lastName;
            return new Person($fullName);
        });

        $this->assertEquals([
            new Person("John Doe"),
            new Person("Jane Doe")
        ], $result->all());
    }

    public function testMapToGroups()
    {
        $collection = collect([
            [
                "name" => "John",
                "department" => "IT"
            ],
            [
                "name" => "Jane",
                "department" => "HR"
            ],
            [
                "name" => "Bob",
                "department" => "IT"
            ]
        ]);

        $result = $collection->mapToGroups(function ($person) {
            return [
                $person["department"] => $person["name"]
            ];
        });

        $this->assertEquals([
            "IT" => collect(["John", "Bob"]),
            "HR" => collect(["Jane"])
        ], $result->all());
    }

    public function testZip()
    {
        $collection1 = collect([1, 2, 3]);
        $collection2 = collect(["a", "b", "c"]);
        $result = $collection1->zip($collection2);
        $this->assertEquals([
            collect([1, "a"]),
            collect([2, "b"]),
            collect([3, "c"])
        ], $result->all());
    }

    public function testConcat()
    {
        $collection1 = collect([1, 2, 3]);
        $collection2 = collect(["a", "b", "c"]);
        $result = $collection1->concat($collection2);
        $this->assertEqualsCanonicalizing([1, 2, 3, "a", "b", "c"], $result->all());
    }

    public function testCombine()
    {
        $collection1 = collect([1, 2, 3]);
        $collection2 = collect(["a", "b", "c"]);
        $result = collect($collection1)->combine($collection2);
        $this->assertEqualsCanonicalizing([
            1 => "a",
            2 => "b",
            3 => "c"
        ], $result->all());
    }

    public function testCollapse()
    {
        $collection = collect([
            [1, 2, 3],
            [4, 5, 6],
            [7, 8, 9]
        ]);
        $result = $collection->collapse();
        $this->assertEqualsCanonicalizing([1, 2, 3, 4, 5, 6, 7, 8, 9], $result->all());
    }

    public function testFlatMap()
    {
        $collection = collect([
            [
                "name" => "John",
                "hobbies" => ["reading", "coding"]
            ],
            [
                "name" => "Jane",
                "hobbies" => ["swimming", "dancing"]
            ]
        ]);

        $result = $collection->flatMap(function ($person) {
            return $person["hobbies"];
        });

        $this->assertEqualsCanonicalizing(["reading", "coding", "swimming", "dancing"], $result->all());
    }

    public function testStringRepresentation()
    {
        $collection = collect(["John", "Jane", "Bob"]);
        $this->assertEquals("John-Jane-Bob", $collection->join("-"));
        $this->assertEquals("John-Jane_Bob", $collection->join("-", "_"));
    }

    public function testFilter()
    {
        $collection = collect([
            "John" => 100,
            "Jane" => 80,
            "Bob" => 90
        ]);

        $result = $collection->filter(function ($value, $key) {
            return $value >= 90;
        });

        $this->assertEquals([
            "John" => 100, "Bob" => 90
        ], $result->all());
    }

    public function testFilterIndex()
    {
        $collection = collect([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
        $result = $collection->filter(function ($value, $key) {
            return $value % 2 == 0;
        });
        $this->assertEqualsCanonicalizing([2, 4, 6, 8, 10], $result->all());
    }

    public function testPartition()
    {
        $collection = collect([
            "John" => 100,
            "Jane" => 80,
            "Bob" => 90
        ]);

        [$bigger, $smaller] = $collection->partition(function ($value, $key) {
            return $value >= 90;
        });

        $this->assertEquals(["John" => 100, "Bob" => 90], $bigger->all());
        $this->assertEquals(["Jane" => 80], $smaller->all());
    }

    public function testTesting()
    {
        $collection = collect(["John", "Jane", "Bob"]);
        $this->assertTrue($collection->contains("John"));
        $this->assertTrue($collection->contains(function ($value, $key) {
            return $value == "Jane";
        }));

//        $this->assertTrue($collection->has(0));
    }

    public function testGrouping()
    {
        $collection = collect([
            [
                "name" => "John",
                "department" => "IT"
            ],
            [
                "name" => "Jane",
                "department" => "HR"
            ],
            [
                "name" => "Bob",
                "department" => "IT"
            ]
        ]);

        $result1 = $collection->groupBy("department");
        $this->assertEquals([
            "IT" => collect([
                ["name" => "John", "department" => "IT"],
                ["name" => "Bob", "department" => "IT"]
            ]),
            "HR" => collect([
                ["name" => "Jane", "department" => "HR"]
            ])
        ], $result1->all());

        assertEquals([
            "IT" => collect([
                ["name" => "John", "department" => "IT"],
                ["name" => "Bob", "department" => "IT"]
            ]),
            "HR" => collect([
                ["name" => "Jane", "department" => "HR"]
            ])
        ], $collection->groupBy(function ($value, $key) {
            return $value["department"];
        })->all());
    }

    public function testSlice()
    {
        $collection = collect([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
        $result = $collection->slice(3, 4);
        $this->assertEqualsCanonicalizing([4, 5, 6, 7], $result->all());
        var_dump($result);
    }

    public function testTake()
    {
        $collection = collect([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
        $result = $collection->take(3);
        $this->assertEqualsCanonicalizing([1, 2, 3], $result->all());
        var_dump($result);

        $result = $collection->takeUntil(function ($value, $key) {
            return $value == 5;
        });
        $this->assertEqualsCanonicalizing([1, 2, 3, 4], $result->all());

        $result = $collection->takeWhile(function ($value, $key) {
            return $value < 3;
        });
        $this->assertEqualsCanonicalizing([1, 2], $result->all());
    }

    public function testSkip()
    {
        $collection = collect([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
        $result = $collection->skip(3);
        $this->assertEqualsCanonicalizing([4, 5, 6, 7, 8, 9, 10], $result->all());

        $result = $collection->skipUntil(function ($value, $key) {
            return $value == 5;
        });
        $this->assertEqualsCanonicalizing([5, 6, 7, 8, 9, 10], $result->all());

        $result = $collection->skipWhile(function ($value, $key) {
            return $value < 3;
        });
        $this->assertEqualsCanonicalizing([3, 4, 5, 6, 7, 8, 9, 10], $result->all());
    }

    public function testChunk()
    {
        $collection = collect([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
        $result = $collection->chunk(3);
        $this->assertEqualsCanonicalizing([1, 2, 3], $result->first()->all());
        $this->assertEqualsCanonicalizing([4, 5, 6], $result->all()[1]->all());
        $this->assertEqualsCanonicalizing([7, 8, 9], $result->all()[2]->all());
        $this->assertEqualsCanonicalizing([10], $result->all()[3]->all());
    }

    public function testFirst()
    {
        $collection = collect([1, 2, 3, 4, 5]);
        $this->assertEquals(1, $collection->first());
//        $this->assertEquals(1, $collection->first(function ($value, $key) {
//            return $value == 1;
//        }));
    }

    public function testFirstOrFail()
    {
        $collection = collect([1, 2, 3, 4, 5]);
        $this->assertEquals(1, $collection->firstOrFail());
    }

    public function testFirstFunc()
    {
        $collection = collect([1, 2, 3, 4, 5]);
        $this->assertEquals(1, $collection->first(function ($value, $key) {
            return $value == 1;
        }));
    }

    public function testFirstWhere()
    {
        $collection = collect([
            [
                "name" => "John",
                "department" => "IT"
            ],
            [
                "name" => "Jane",
                "department" => "HR"
            ],
            [
                "name" => "Bob",
                "department" => "IT"
            ]
        ]);

        $result = $collection->firstWhere("department", "IT");
        $this->assertEquals(["name" => "John", "department" => "IT"], $result);

        $result = $collection->firstWhere("department", "HR");
        $this->assertEquals(["name" => "Jane", "department" => "HR"], $result);
    }

    public function testLast()
    {
        $collection = collect([1, 2, 3, 4, 5]);
        $result = $collection->last();
        $this->assertEquals(5, $result);

        $result = $collection->last(function ($value, $key) {
            return $value < 3;
        });
        $this->assertEquals(2, $result);
        var_dump($result);
    }

    public function testRandom()
    {
        $collection = collect([1, 2, 3, 4, 5]);
        $result = $collection->random();
        $this->assertTrue(in_array($result, [1, 2, 3, 4, 5]));

//        $result = $collection->random(3);
//        $this->assertEqualsCanonicalizing([1,2,3,4,5], $result->all());
    }

    public function testCheckingExistence()
    {
        $collection = collect([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
        $this->assertTrue($collection->isNotEmpty());
        $this->assertFalse($collection->isEmpty());
        $this->assertTrue($collection->contains(5));
        $this->assertFalse($collection->contains(11));
        self::assertTrue($collection->contains(function ($value, $key) {
            return $value == 3;
        }));
    }

    public function testSort()
    {
        $collection = collect([1, 5, 3, 4, 2]);
        $result = $collection->sort();
        $this->assertEqualsCanonicalizing([1, 2, 3, 4, 5], $result->all());

        $result = $collection->sortBy(function ($value, $key) {
            return $value;
        });
        $this->assertEqualsCanonicalizing([1, 2, 3, 4, 5], $result->all());
    }

    public function testSortDesc()
    {
        $collection = collect([1, 5, 3, 4, 2]);
        $result = $collection->sortDesc();
        $this->assertEqualsCanonicalizing([5, 4, 3, 2, 1], $result->all());

        $result = $collection->sortByDesc(function ($value, $key) {
            return $value;
        });
        $this->assertEqualsCanonicalizing([5, 4, 3, 2, 1], $result->all());
    }

    public function testSortKeys()
    {
        self::assertTrue(True);
    }

    public function testAggregate()
    {
        $collection = collect([1, 2, 3, 4, 5]);
        $result = $collection->sum();
        $this->assertEquals(15, $result);

        $result = $collection->avg();
        $this->assertEquals(3, $result);

        $result = $collection->min();
        $this->assertEquals(1, $result);

        $result = $collection->max();
        $this->assertEquals(5, $result);

        $result = $collection->count();
        $this->assertEquals(5, $result);

        $result = $collection->countBy();
        $this->assertEqualsCanonicalizing([1 => 1, 2 => 1, 3 => 1, 4 => 1, 5 => 1], $result->all());

        $result = $collection->sum(function ($value, $key) {
            return $value * 2;
        });
        $this->assertEquals(30, $result);
    }

    public function testReduce()
    {
        $collection = collect([1, 2, 3, 4, 5, 6, 7, 8, 9]);
        $result = $collection->reduce(function ($carry, $item) {
            var_dump($carry);
            var_dump($item);
            return $carry + $item;
        });
        $this->assertEquals(45, $result);
    }

    public function testLazyCollection()
    {
        $collection = LazyCollection::make(function () {
            $value = 0;
            while (true) {
                yield $value;
                $value++;
            }
        });

        $result = $collection->take(5);
        $this->assertEqualsCanonicalizing([0, 1, 2, 3, 4], $result->all());

    }


}
