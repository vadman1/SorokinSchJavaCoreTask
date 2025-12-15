package io.github.vadman1.collectionframework.lesson5;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Product, Integer> map = new HashMap<>();

        Product product1 = new Product(1, "One", 20);
        Product product2 = new Product(1, "Two", 30);
        Product product3 = new Product(10, "Three", 50);

        // из-за неправильной реализации метода equals объекты считаются равными
        System.out.println(product1.equals(product2));

        map.put(product1, 1);
        map.put(product2, 2);
        map.put(product3, 3);

        // из-за неправильной реализации метода hashCode и equals объекты считаются равными, поэтому ключ product2 теряется
        // (перезаписывается значение на 2, а ключ product1 остаётся)
        System.out.println(map);
    }
}