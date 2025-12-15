package io.github.vadman1.collectionframework.lesson4;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("Яблоко", 100);
        map.put("Банан", 80);
        map.put("Апельсин", 120);
        System.out.println("Начальное содержимое HashMap:");
        printMap(map);

        map.put("Банан", 90);
        System.out.println("\nПосле обновления цены на Банан:");
        printMap(map);

        map.remove("Апельсин");
        System.out.println("\nПосле удаления Апельсина:");
        printMap(map);
    }

    private static void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Фрукт: " + entry.getKey() + ", Цена: " + entry.getValue());
        }
    }
}