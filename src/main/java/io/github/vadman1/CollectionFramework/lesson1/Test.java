package io.github.vadman1.CollectionFramework.lesson1;

public class Test {
    public static void main(String[] args) {
        GenericBox<String> boxStr1 = new GenericBox<>("Box1");
        GenericBox<String> boxStr2 = new GenericBox<>("Box2");

        System.out.println(boxStr1.isEqual(boxStr2));

        boxStr1.swap(boxStr2);
        System.out.println("Box1 (str): " + boxStr1.getValue());
        System.out.println("Box2 (str): " + boxStr2.getValue());

        boxStr2.setValue("Box2");
        System.out.println("Box1 (str): " + boxStr1.getValue());
        System.out.println("Box2 (str): " + boxStr2.getValue());
        System.out.println(boxStr1.isEqual(boxStr2));

        GenericBox<Integer> boxInt1 = new GenericBox<>(5);
        GenericBox<Integer> boxInt2 = new GenericBox<>(10);
        System.out.println(boxInt1.isEqual(boxInt2));

        boxInt1.setValue(100);
        System.out.println("Box1 (int): " + boxInt1.getValue());
        System.out.println("Box2 (int): " + boxInt2.getValue());

        boxInt1.swap(boxInt2);
        System.out.println("Box1 (int): " + boxInt1.getValue());
        System.out.println("Box2 (int): " + boxInt2.getValue());
    }
}