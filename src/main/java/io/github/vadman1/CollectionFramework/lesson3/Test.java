package io.github.vadman1.CollectionFramework.lesson3;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("C++");
        list.add("C#");
        list.add("Python");
        list.add("Go");
        list.add("Java");
        list.add("Basic");
        list.add("C++");

        System.out.println(list);

//        Set<String> set = new HashSet<>(list);
        Set<String> set = new LinkedHashSet<>(list);

        System.out.println(set);

        list = new ArrayList<>(set);
        System.out.println(list);
    }
}
