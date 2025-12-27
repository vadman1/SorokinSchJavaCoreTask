package io.github.vadman1.streamapi.lesson5.lesson;

import java.util.List;

public class LazyExecutionExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> result = numbers.stream()
                .peek(n -> System.out.println("Начальное значение: " + n))
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("После filter: " + n))
                .map(n -> n * n)
                .peek(n -> System.out.println("После map: " + n))
                .toList();

        System.out.println("Результат: " + result);
    }
}
