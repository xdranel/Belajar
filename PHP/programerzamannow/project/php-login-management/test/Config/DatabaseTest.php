<?php

namespace Grp\Belajar\Config;

use PHPUnit\Framework\TestCase;
use function PHPUnit\Framework\assertSame;

class DatabaseTest extends TestCase
{
    public function testGetConnection()
    {
        $connection = Database::getConnection();
        self::assertNotNull($connection);
    }

    public function testGetConnectionSingleton()
    {
        $connection1 = Database::getConnection();
        $connection2 = Database::getConnection();
        assertSame($connection1, $connection2);
    }


}