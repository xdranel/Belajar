<?php

declare(strict_types=1);

namespace Grp\Test;

use PHPUnit\Framework\Attributes\TestWith;
use PHPUnit\Framework\Attributes\DataProvider;
use PHPUnit\Framework\Attributes\Test;
use PHPUnit\Framework\TestCase;


class MathTest extends TestCase
{
    #[Test]
    public function AddManual()
    {
        $this->assertEquals(10, Math::add([5, 5]));
        $this->assertEquals(20, Math::add([4, 4, 4, 4, 4]));
        $this->assertEquals(9, Math::add([3, 3, 3]));
    }

    #[Test]
    public static function mathSumData(): array
    {
        return [
            [[5, 5], 10],
            [[4, 4, 4, 4, 4], 20],
            [[3, 3, 3], 9],
        ];
    }

    #[Test]
    #[DataProvider('mathSumData')]
    public function DataProvide(array $values, int $expected)
    {
        $this->assertEquals($expected, Math::add($values));
    }

    #[Test]
    #[TestWith([[5, 5], 10])]
    #[TestWith([[4, 4, 4, 4, 4], 20])]
    #[TestWith([[3, 3, 3], 9])]
    public function testWith(array $values, int $expected): void
    {
        $this->assertEquals($expected, Math::add($values));
    }
}