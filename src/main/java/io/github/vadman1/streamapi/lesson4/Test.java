package io.github.vadman1.streamapi.lesson4;

import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("Vad", 27),
                new User("John", 21),
                new User("Kate", 22),
                new User("Sam", 48),
                new User("Bad", 10),
                new User("Jake", 25),
                new User("Brad", 50)
        );

        List<String> names = users.stream()
                .filter(user -> user.getAge() > 25)
                .map(User::getName)
                .distinct()
                .sorted()
                .toList();
        System.out.println(names);

        int ageSum = users.stream()
                .map(User::getAge)
                .reduce(0, (acc, age) -> acc + age);
        System.out.println("Сумма возрастов: " + ageSum);

        // поиск минимального возраста с помощью метода min
        int minAge = users.stream()
                .map(User::getAge)
                .min(Integer::compareTo)
                .get();
        System.out.println("Минимальный возраст пользователя: " + minAge);

        // поиск минимального возраста с помощью метода reduce
//        int minAge = users.stream()
//                .map(User::getAge)
//                .reduce((a, b) -> a < b ? a : b)
//                .get();
//        System.out.println("Минимальный возраст пользователя: " + minAge);
    }
}
