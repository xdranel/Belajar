<?php

namespace Grp\Mvc\Controller;

class ProductController
{
    function categories(string $productId, string $categoryId): void
    {
        echo "Product $productId category $categoryId";
    }
}