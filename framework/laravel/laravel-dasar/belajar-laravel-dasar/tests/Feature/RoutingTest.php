<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Tests\TestCase;

class RoutingTest extends TestCase
{
    public function testGet()
    {
        $this->get('/grp')
            ->assertStatus(200)
            ->assertSeeText('Hello GRP');
    }

    public function testRedirect()
    {
        $this->get('/youtube')
            ->assertRedirect('/grp');
    }

    public function testFallback()
    {
        $this->get('/fallback')
            ->assertSeeText('404 Not Found by GRP');

        $this->get('/wrong-url')
            ->assertSeeText('404 Not Found by GRP');
    }

    public function testRouteParameter()
    {
        $this->get('/products/1')
            ->assertSeeText('Product 1');

        $this->get('/products/2')
            ->assertSeeText('Product 2');

        $this->get('/products/1/items/XX')
            ->assertSeeText('Product 1, Item XX');

        $this->get('/products/1/items/YY')
            ->assertSeeText('Product 1, Item YY');
    }

    public function testRouteParameterRegex()
    {
        $this->get('/categories/123')
            ->assertSeeText('Category 123');

        $this->get('/categories/abc')
            ->assertSeeText('404 Not Found by GRP');
    }

    public function testRouteParameterOptional()
    {
        $this->get('/users/ramona')
            ->assertSeeText('User ramona');

        $this->get('/users/')
            ->assertSeeText('User 404');

    }

    public function testRouteConflict()
    {
        $this->get('/conflict/prastyo')
            ->assertSeeText('Conflict prastyo');

        $this->get('/conflict/ramona')
            ->assertSeeText('Conflict Ramona');
    }

    public function testNamedRoute()
    {
        $this->get('/produk/12345')
            ->assertSeeText('Link http://localhost/products/12345');

        $this->get('/produk-redirect/12345')
            ->assertRedirect('/products/12345');

    }


}
