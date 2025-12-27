package io.github.vadman1.streamapi.lesson3;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> strings = List.of("Java is fun", "Stream API simplifies data processing",
                "Стримы — это абстракция конвейера, они не хранят элементы, а лишь описывают операции над ними.");

        strings.stream()
                .map(str -> str.split("[\\s.,!?;:]"))
                .flatMap(Arrays::stream)
                .filter(str -> str.length() > 3)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}