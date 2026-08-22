<?php

namespace Grp\Test;

use PHPUnit\Framework\TestCase;

class PersonTest extends TestCase
{
    private Person $person;
    protected function setUp(): void
    {
        $this->person = new Person("Joko");
    }

    public function testSuccess()
    {
        $this->assertEquals("Hello Budi, my name is Joko", $this->person->sayHello("Budi"));
    }

    public function testException()
    {
        $this->expectException(\Exception::class);
        $this->person->sayHello(null);
    }

    public function testOutputSuccess()
    {
        $this->expectOutputString("Goodbye Budi" . PHP_EOL);
        $this->person->sayGoodbye("Budi");
    }
}