package io.github.vadman1.collectionframework.lesson6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(i + 1);
        }

        System.out.println(list);

        Iterator<Integer> iterator = list.iterator();

        // удаление всех нечётных чисел из списка с помощью Iterator
        while (iterator.hasNext()) {
            Integer elem = iterator.next();

            if (elem % 2 == 1) {
                iterator.remove();
            }
        }

        // удаление не через Iterator, возникает ошибка ConcurrentModificationException
//        while (iterator.hasNext()) {
//            Integer elem = iterator.next();
//
//            if (elem % 2 == 1) {
//                list.remove(elem);
//            }
//        }

        // удаление нечётных чисел из списка с помощью ListIterator с конца (сам придумал задание для практики)
//        ListIterator<Integer> listIterator = list.listIterator(list.size());
//        while (listIterator.hasPrevious()) {
//            Integer elem = listIterator.previous();
//            if (elem % 2 == 1) {
//                listIterator.remove();
//            }
//        }

        System.out.println(list);

        // проверка отработки удаления нечётных чисел
        boolean isListContainsOddElem = false;
        for (int x : list) {
            System.out.println(x);
            if (x % 2 == 1) {
                isListContainsOddElem = true;
                break;
            }
        }
        System.out.println(isListContainsOddElem);
    }
}