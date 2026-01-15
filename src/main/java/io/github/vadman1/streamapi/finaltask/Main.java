package io.github.vadman1.streamapi.finaltask;

import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private static final Faker faker = new Faker(new Locale("en"));
    private static final Random random = new Random();

    public static void main(String[] args) {

        List<Product> productCatalog = List.of(
                // Electronics
                new Product(1L, "Laptop", "Electronics", new BigDecimal("1299.99")),
                new Product(2L, "Desktop Computer", "Electronics", new BigDecimal("1799.00")),
                new Product(3L, "Smartphone", "Electronics", new BigDecimal("999.50")),
                new Product(4L, "Tablet", "Electronics", new BigDecimal("649.90")),
                new Product(5L, "Monitor 27\"", "Electronics", new BigDecimal("399.00")),

                // Home Appliances
                new Product(6L, "Coffee Machine", "Home Appliances", new BigDecimal("249.90")),
                new Product(7L, "Microwave Oven", "Home Appliances", new BigDecimal("189.99")),
                new Product(8L, "Vacuum Cleaner", "Home Appliances", new BigDecimal("329.50")),
                new Product(9L, "Air Conditioner", "Home Appliances", new BigDecimal("899.00")),

                // Toys & Children's products
                new Product(10L, "Toy Car", "Toys", new BigDecimal("79.50")),
                new Product(11L, "Soldier Figure", "Children's products", new BigDecimal("189.00")),
                new Product(12L, "Lego Set", "Children's products", new BigDecimal("259.99")),
                new Product(13L, "Board Game", "Toys", new BigDecimal("149.00")),

                // Books
                new Product(14L, "The Great Gatsby", "Books", new BigDecimal("99.99")),
                new Product(15L, "The Picture of Dorian Gray", "Books", new BigDecimal("250.99")),
                new Product(16L, "Clean Code", "Books", new BigDecimal("499.00")),
                new Product(17L, "Effective Java", "Books", new BigDecimal("699.00")),

                // Office & Accessories
                new Product(18L, "Mechanical Keyboard", "Accessories", new BigDecimal("159.99")),
                new Product(19L, "Wireless Mouse", "Accessories", new BigDecimal("89.50")),
                new Product(20L, "USB-C Hub", "Accessories", new BigDecimal("69.99"))
        );

        Customer customer1 = new Customer(
                1L,
                faker.name().fullName(),
                random.nextLong(5),
                generateOrders(productCatalog, 5, 5)
        );

        Customer customer2 = new Customer(
                2L,
                faker.name().fullName(),
                2L,
                generateOrders(productCatalog, 6, 6)
        );

        Customer customer3 = new Customer(
                3L,
                faker.name().fullName(),
                random.nextLong(5),
                generateOrders(productCatalog, 7, 5)
        );

        Customer customer4 = new Customer(
                4L,
                faker.name().fullName(),
                random.nextLong(5),
                generateOrders(productCatalog, 5, 5)
        );

        Customer customer5 = new Customer(
                5L,
                faker.name().fullName(),
                random.nextLong(5),
                generateOrders(productCatalog, 5, 6)
        );

        List<Customer> customers = List.of(customer1, customer2, customer3, customer4, customer5);

        // Задание 1
        List<Product> booksWithPriceMore100 = getProductStreamByCategory(customers, "Books")
                .distinct()
                .filter(product -> product.getPrice().compareTo(BigDecimal.valueOf(100)) > 0)
                .toList();
        System.out.println("Продукты из категории \"Books\" с ценой более 100:\n" + booksWithPriceMore100 + "\n");

        // Задание 2
        List<Order> childrenProducts = getOrderStream(customers)
                .distinct()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> "Children's products".equals(product.getCategory())))
                .toList();
        System.out.println("Список заказов с продуктами из категории \"Children's products\":\n" + childrenProducts + "\n");

        // Задание 3
        BigDecimal sumToysWithDiscount10Percent = getProductStreamByCategory(customers, "Toys")
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(0.9)))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.valueOf(0));
        System.out.println("Сумма продуктов из категории \"Toys\" со скидкой 10%:\n" + sumToysWithDiscount10Percent + "\n");

        // Задание 4
        List<Product> productsClientTwoLevelAndOrderDateBetween = customers.stream()
                .filter(customer -> customer.getLevel() == 2)
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> {
                    LocalDate orderDate = order.getOrderDate();
                    return orderDate.isAfter(LocalDate.of(2021, 2, 1))
                            && orderDate.isBefore(LocalDate.of(2021, 4, 1));
                })
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .toList();
        System.out.println("Список продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021:\n"
                + productsClientTwoLevelAndOrderDateBetween + "\n");

        // Задание 5
        List<Product> topTwoCheapProduct = getProductStreamByCategory(customers, "Books")
                .distinct()
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2)
                .toList();
        System.out.println("Топ 2 самые дешёвые книг:\n" + topTwoCheapProduct + "\n");

        // Задание 6
        List<Order> threeLastOrders = getOrderStream(customers)
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .toList();
        System.out.println("3 самых последних сделанных заказа:\n" + threeLastOrders + "\n");

        // Задание 7
        System.out.println("id заказов, сделанных 15-марта-2021:\n");
        List<Product> products15March2021 = getOrderStream(customers)
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                .peek(order -> System.out.println(order.getId()))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .toList();
        System.out.println("Список заказов, сделанных 15-марта-2021:\n" + products15March2021 + "\n");

        // Задание 8
        BigDecimal sumOrdersFebruary2021 = getOrderStream(customers)
                .filter(order -> {
                    LocalDate orderDate = order.getOrderDate();
                    return orderDate.isAfter(LocalDate.of(2021, 2, 1))
                            && orderDate.isBefore(LocalDate.of(2021, 3, 1));
                })
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        System.out.println("Общая сумма всех заказов, сделанных в феврале 2021:\n" + sumOrdersFebruary2021 + "\n");

        // Задание 9
        double averageSumOrder14March2021 = getOrderStream(customers)
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 14)))
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0.0);
        System.out.println("Средний платеж по заказам, сделанным 14-марта-2021:\n" + averageSumOrder14March2021 + "\n");

        // Задание 10
        System.out.println("Набор статистических данных продуктов категории \"Книги\"");
        double sumBooks = getProductStreamByCategory(customers, "Books")
                .map(Product::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        System.out.println("Сумма: " + sumBooks);

        double averagePriceBooks = getProductStreamByCategory(customers, "Books")
                .map(Product::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0.0);
        System.out.println("Средняя цена: " + averagePriceBooks);

        double maxPriceBooks = getProductStreamByCategory(customers, "Books")
                .map(Product::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .max()
                .orElse(0.0);
        System.out.println("Максимальная цена: " + maxPriceBooks);

        double minPriceBooks = getProductStreamByCategory(customers, "Books")
                .map(Product::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .min()
                .orElse(0.0);
        System.out.println("Минимальная цена: " + minPriceBooks);

        long countBooks = getProductStreamByCategory(customers, "Books")
                .count();
        System.out.println("Количество: " + countBooks + "\n");

        // Задание 11
        Map<Long, Integer> ordersIdAndProductCount = getOrderStream(customers)
                .collect(Collectors.toMap(
                        Order::getId,
                        order -> order.getProducts().size(),
                        (oldVal, newVal) -> newVal
                ));
        System.out.println("Map<Long, Integer> → key - id заказа, value - кол-во товаров в заказе:\n"
                + ordersIdAndProductCount + "\n");

        // Задание 12
        Map<Customer, List<Order>> customersWithOrdersMap = customers.stream()
                .collect(Collectors.toMap(
                        customer -> customer,
                        customer -> customer.getOrders().stream().toList()
                ));
        System.out.println("Map<Customer, List<Order>> → key - покупатель, value - список его заказов:\n"
                + customersWithOrdersMap + "\n");

        // Задание 13
        Map<Order, Double> ordersWithSumMap = getOrderStream(customers)
                .collect(Collectors.toMap(
                        order -> order,
                        order -> order.getProducts().stream()
                                .map(Product::getPrice)
                                .mapToDouble(BigDecimal::doubleValue)
                                .sum(),
                        (oldVal, newVal) -> newVal
                ));
        System.out.println("Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа:\n"
                + ordersWithSumMap + "\n");

        // Задание 14
        Map<String, List<String>> categoriesWithProductsMap = getProductStream(customers)
                .distinct()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())
                ));
        System.out.println("Map<String, List<String>> → key - категория, value - список названий товаров в категории:\n"
                + categoriesWithProductsMap + "\n");

        // Задание 15
        Map<String, Product> categoriesWithMostExpensiveProductMap = getProductStream(customers)
                .distinct()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(
                                        Comparator.comparing(Product::getPrice)
                                ),
                                opt -> opt.orElse(null)
                        )
                ));
        System.out.println("Map<String, Product> → самый дорогой продукт по каждой категории:\n"
                + categoriesWithMostExpensiveProductMap + "\n");
    }

    private static Stream<Product> getProductStreamByCategory(List<Customer> customers, String category) {
        return getProductStream(customers)
                .filter(product -> category.equals(product.getCategory()));
    }

    private static Stream<Order> getOrderStream(List<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.getOrders().stream());
    }
    
    private static Stream<Product> getProductStream(List<Customer> customers) {
        return getOrderStream(customers)
                .flatMap(order -> order.getProducts().stream());
    }

    static Set<Order> generateOrders(
            List<Product> productCatalog,
            int ordersCount,
            int productsCount
    ) {
        Random random = new Random();

        return IntStream.rangeClosed(1, ordersCount)
                .mapToObj(id -> {
                    Set<Product> products = IntStream.rangeClosed(1, productsCount)
                            .mapToObj(i -> productCatalog.get(random.nextInt(productCatalog.size())))
                            .collect(Collectors.toSet());

                    LocalDate orderDate = randomDate(
                            LocalDate.of(2021, 1, 1),
                            LocalDate.of(2021, 6, 30)
                    );

                    OrderStatus status = randomStatus();

                    LocalDate deliveredDate = status.equals(OrderStatus.DELIVERED)
                            ? orderDate.plusDays(10)
                            : null;

                    return new Order(
                            faker.number().numberBetween(0, 1000000L),
                            orderDate,
                            deliveredDate,
                            status.toString(),
                            products
                    );
                })
                .collect(Collectors.toSet());
    }

    static OrderStatus randomStatus() {
        OrderStatus[] values = OrderStatus.values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    static LocalDate randomDate(LocalDate start, LocalDate end) {
        long startEpochDay = start.toEpochDay();
        long endEpochDay = end.toEpochDay();

        long randomDay = ThreadLocalRandom.current()
                .nextLong(startEpochDay, endEpochDay + 1);

        return LocalDate.ofEpochDay(randomDay);
    }
}