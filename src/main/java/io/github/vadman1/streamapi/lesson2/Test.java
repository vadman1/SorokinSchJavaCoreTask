package io.github.vadman1.streamapi.lesson2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.stream()
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);

        System.out.println("===========");

        int[] arr = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Arrays.stream(arr)
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);

        System.out.println("===========");

        Stream.of(21, 22, 23, 24, 25, 26, 27, 28, 29, 30)
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);

        System.out.println("===========");

        Random random = new Random();
        IntStream.generate(random::nextInt)
                .limit(10)
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);

        System.out.println("===========");

        IntStream.iterate(42, n -> n * 5)
                .limit(10)
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);
    }
}