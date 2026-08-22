<?php

namespace Grp\Test;

class ProductService
{
    public function __construct(private ProductRepository $repository)
    {
    }

    public function register(Product $product): Product
    {
        if ($this->repository->findById($product->getId()) != null) {
            throw new \Exception("Product {$product->getId()} Already Exists");
        }
        return $this->repository->save($product);
    }

    public function delete(string $id): void
    {
        $product = $this->repository->findById($id);
        if ($product == null) {
            throw new \Exception("Product {$id} Not Found");
        }

        $this->repository->delete($product);
    }
}