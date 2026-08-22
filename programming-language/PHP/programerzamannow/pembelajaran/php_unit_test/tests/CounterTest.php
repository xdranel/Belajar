<?php

declare(strict_types=1);

namespace Grp\Test;

use PHPUnit\Framework\Assert;
use PHPUnit\Framework\Attributes\RequiresOperatingSystemFamily;
use PHPUnit\Framework\Attributes\RequiresPhp;
use PHPUnit\Framework\Attributes\Test;
use PHPUnit\Framework\Attributes\Depends;
use PHPUnit\Framework\TestCase;


class CounterTest extends TestCase
{
    private Counter $counter;

    protected function setUp(): void
    {
        $this->counter = new Counter();
    }

    public function testIncrement()
    {
        $this->markTestIncomplete("Not implemented yet.");
        self::assertEquals(50, $this->counter->getCounter());
    }

    #[Test]
    public function CounterIncrement()
    {
        self::markTestSkipped("Im Still Confused");

        $this->counter->increment();
        Assert::assertEquals(51, $this->counter->getCounter());

        $this->counter->increment();
        $this->assertEquals(52, $this->counter->getCounter());

        $this->counter->increment();
        self::assertEquals(53, $this->counter->getCounter());
    }

    #[Test]
    public function CounterDecrement()
    {
        $this->counter->decrement();
        Assert::assertEquals(49, $this->counter->getCounter());

        $this->counter->decrement();
        $this->assertEquals(48, $this->counter->getCounter());

        $this->counter->decrement();
        self::assertEquals(47, $this->counter->getCounter());
    }

    #[Test]
    public function CounterFirst(): Counter
    {
        $this->counter->increment();
        $this->assertEquals(51, $this->counter->getCounter());
        return $this->counter;
    }

    #[Test]
    #[Depends('CounterFirst')]
    public function CounterSecond(Counter $counter): void
    {
        $counter->increment();
        $this->assertEquals(52, $counter->getCounter());
    }

    #[Test]
    #[RequiresPhp('PHP >= 8.4'), RequiresOperatingSystemFamily('Darwin')]
    public function testOperatingSystem(): void
    {
        self::assertTrue(true, "Only Mac");
    }
}