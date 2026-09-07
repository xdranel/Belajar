<?php

namespace Tests\Feature;

use App\Data\Bar;
use App\Data\Foo;
use App\Data\Person;
use App\Services\HelloService;
use App\Services\HelloServiceIndonesia;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;
use function PHPUnit\Framework\assertSame;

class ServiceContainerTest extends TestCase
{
    public function testDependency()
    {
        // $foo = new Foo();
        $foo1 = $this->app->make(Foo::class); // new Foo();
        $foo2 = $this->app->make(Foo::class); // new Foo();

        self::assertEquals("Foo", $foo1->foo());
        self::assertEquals("Foo", $foo2->foo());
//        self::assertNotSame($foo1, $foo2);
        self::assertSame($foo1, $foo2);
    }

    public function testBind()
    {
        // $person = $this->app->make(Person::class); // new Person() -> Error: Complex Object;
        // self::assertNotNull($person);

        $this->app->bind(Person::class, function ($app) {
            return new Person('Ramona', 'Prastyo');
        });

        $person1 = $this->app->make(Person::class); // calling function closure() and return new Person()
        $person2 = $this->app->make(Person::class); // calling function closure() and return new Person()

        self::assertEquals("Ramona", $person1->firstName);
        self::assertEquals("Prastyo", $person1->lastName);
        self::assertNotSame($person1, $person2);
    }

    public function testSingleton()
    {
        $this->app->singleton(Person::class, function ($app) {
            return new Person('Ramona', 'Prastyo');
        });

        $person1 = $this->app->make(Person::class); // calling new Person(); if not exist, create new
        $person2 = $this->app->make(Person::class); // return existing Person();

        self::assertEquals("Ramona", $person1->firstName);
        self::assertEquals("Prastyo", $person1->lastName);
        self::assertSame($person1, $person2);
    }

    public function testInstance()
    {
        $person = new Person('Ramona', 'Prastyo'); // an existing object
        $this->app->instance(Person::class, $person); // adding an existing object to the service container

        $person1 = $this->app->make(Person::class); // return object $person
        $person2 = $this->app->make(Person::class); // return object $person

        self::assertEquals("Ramona", $person1->firstName);
        self::assertEquals("Prastyo", $person1->lastName);
        self::assertSame($person, $person1);
        self::assertSame($person, $person2);
        self::assertSame($person1, $person2);

    }

    public function testDependencyInjection()
    {
        $this->app->singleton(Foo::class, function ($app) {
            return new Foo();
        });

        $this->app->singleton(Bar::class, function ($app) {
            // return new Bar($app->make(Foo::class));
            $foo = $app->make(Foo::class);
            return new Bar($foo);
        });

        $foo = $this->app->make(Foo::class);
        $bar1 = $this->app->make(Bar::class);
        $bar2 = $this->app->make(Bar::class);

        // self::assertNotSame($foo, $bar->foo);
        self::assertSame($foo, $bar1->foo);
        self::assertSame($foo, $bar2->foo);
        self::assertSame($bar1->foo, $bar2->foo);

        // self::assertNotSame($bar1, $bar2);
        self::assertSame($bar1, $bar2);
    }

    public function testInterfaceToClass()
    {
        // $this->app->bind(HelloService::class, HelloServiceIndonesia::class); // Keep making new object

        // $this->app->singleton(HelloService::class, HelloServiceIndonesia::class);
        $this->app->singleton(HelloService::class, function ($app) {
            return new HelloServiceIndonesia();
        });

        $helloService = $this->app->make(HelloService::class);

        self::assertEquals("Halo Ramona", $helloService->hello("Ramona"));
    }


}
