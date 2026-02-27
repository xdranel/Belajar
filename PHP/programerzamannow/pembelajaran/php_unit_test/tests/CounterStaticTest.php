<?php

namespace Grp\Test;

use PHPUnit\Framework\TestCase;

class CounterStaticTest extends TestCase
{
    private static ?Counter $counter;

    public static function setUpBeforeClass(): void
    {
        self::$counter = new Counter();
    }

    public function testCount()
    {
        self::$counter->increment();
        self::assertEquals(51, self::$counter->getCounter());
    }

    public function testDeCount()
    {
        self::$counter->decrement();
        self::assertEquals(50, self::$counter->getCounter());
    }

    public static function tearDownAfterClass(): void
    {
        self::$counter = null;
    }
}