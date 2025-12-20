package io.github.vadman1.collectionframework.lesson7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateChecker {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        // Заполнение списка числами от 0 до 100000 с дублированием одного значения
        for (int i = 0; i <= 100000; i++) {
            numbers.add(i);
        }
        numbers.add(50000); // Вставляем дубликат

        long start = System.currentTimeMillis();
        boolean hasDuplicates = false;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i).equals(numbers.get(j))) {
                    hasDuplicates = true;
                    break;
                }
            }
            if (hasDuplicates) break;
        }
        System.out.println("Дубликаты найдены: " + hasDuplicates);
        long end = System.currentTimeMillis();
        System.out.println("Неоптимизированный способ занял: " + (end - start));

        start = System.currentTimeMillis();
        Set<Integer> numbersSet = new HashSet<>(numbers);
        hasDuplicates = numbers.size() != numbersSet.size();
        end = System.currentTimeMillis();
        System.out.println("Дубликаты найдены (оптимизированный способ): " + hasDuplicates);
        System.out.println("Оптимизированный способ занял: " + (end - start));
    }
}
