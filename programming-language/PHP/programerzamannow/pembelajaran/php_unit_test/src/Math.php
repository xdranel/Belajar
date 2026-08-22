<?php

declare(strict_types=1);

namespace Grp\Test;

class Math
{
    public static function add(array $values): int
    {
        $total = 0;
        foreach ($values as $value) {
            $total += $value;
        }
        return $total;
    }
}
