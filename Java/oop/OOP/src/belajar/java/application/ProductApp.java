package belajar.java.application;

import belajar.java.data.Product;

public class ProductApp {
    public static void main(String[] args) {

        Product product = new Product("ROG Zephyrus", 2200000);

        System.out.println(product.getName() + " : " + product.getPrice());

        System.out.println(product);

        Product product2 = new Product("ROG Zephyrus", 2200000);

        System.out.println(product.equals(product2));
        System.out.println(product.hashCode() == product2.hashCode());
    }
}
