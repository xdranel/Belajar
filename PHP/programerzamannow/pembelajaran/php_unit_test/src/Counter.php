<?php

declare(strict_types=1);

namespace Grp\Test;

class Counter
{
    private int $counter = 50;

    public function increment(): void
    {
        $this->counter++;
    }

    public function decrement(): void
    {
        $this->counter--;
    }

    public function getCounter(): int
    {
        return $this->counter;
    }
}