package io.github.vadman1.streamapi.lesson5.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            numbers.add(i + 1);
        }

        long startFor = System.nanoTime();
        List<Integer> numbersFor = new ArrayList<>();
        for (int n : numbers) {
            if (n % 2 == 0) {
                numbersFor.add(n * n);
            }
        }
        long endFor = System.nanoTime();
        System.out.println("Время выполнения for: " + (endFor - startFor));
        for (int n : numbersFor) {
            System.out.println(n);
        }

        long startStream = System.nanoTime();
        Stream<Integer> stream = numbers.stream();
        List<Integer> numbersStream = stream
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .toList();
        long endStream = System.nanoTime();
        System.out.println("Время выполнения стрима: " + (endStream - startStream));

        for (int n : numbersStream) {
            System.out.println(n);
        }

        // попытка повторного использования стрима
        try {
            stream.forEach(System.out::println);
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: стрим уже был использован!");
        }
    }
}
