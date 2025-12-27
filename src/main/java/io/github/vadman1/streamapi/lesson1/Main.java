package io.github.vadman1.streamapi.lesson1;

public class Main {
    public static void main(String[] args) {
        MyCalculator myCalculator1 = new MyCalculator() {
            @Override
            public double calculate(double a, double b) {
                return a + b;
            }
        };

        MyCalculator myCalculator2 = (a , b) -> a + b;

        testCalculator(myCalculator1, 10, 5);
        testCalculator(myCalculator2, 10, 5);
    }

    private static void testCalculator(MyCalculator calculator, double a, double b) {
        double result = calculator.calculate(a, b);
        System.out.println(result);
    }
}
