package io.github.vadman1.streamapi.lesson5.lesson;

import java.util.List;
import java.util.stream.Stream;

public class SingleUseStreamExample {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie");
        Stream<String> nameStream = names.stream();

        // Первая терминальная операция
        nameStream.forEach(System.out::println);

        // Попытка повторного использования того же стрима
        try {
            nameStream.forEach(System.out::println);
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: стрим уже был использован!");
        }
    }
}