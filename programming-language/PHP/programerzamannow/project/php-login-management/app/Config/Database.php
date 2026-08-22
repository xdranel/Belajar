<?php

namespace Grp\Belajar\Config;

class Database
{
    // Store connections in an array so 'test' and 'prod' don't mix up
    private static array $pdos = [];
    private static string $lastEnv = "test";

    public static function getConnection(string $env = "test"): \PDO
    {
        self::$lastEnv = $env;

        if (!isset(self::$pdos[$env])) {
            require_once __DIR__ . "/../../config/database.php";
            $config = getDatabaseConfig();

            self::$pdos[$env] = new \PDO(
                $config['database'][$env]['url'],
                $config['database'][$env]['username'],
                $config['database'][$env]['password'],
                [\PDO::ATTR_ERRMODE => \PDO::ERRMODE_EXCEPTION]
            );
        }

        return self::$pdos[$env];
    }

    public static function beginTransaction()
    {
        self::$pdos[self::$lastEnv]->beginTransaction();
    }

    public static function commitTransaction()
    {
        self::$pdos[self::$lastEnv]->commit();
    }

    public static function rollbackTransaction()
    {
        self::$pdos[self::$lastEnv]->rollBack();
    }
}