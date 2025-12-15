package io.github.vadman1.collectionframework.finaltask;

import java.util.Scanner;

public class ContactBookClient {

    private static final String MENU = """
            «1»: Добавить контакт
            «2»: Удалить контакт
            «3»: Посмотреть все контакты
            «4»: Найти контакт
            «5»: Посмотреть контакты по группе
            «0»: Выход
            """;

    public static void main(String[] args) {
        ContactBook contactBook = new ContactBook();

        System.out.println("Выберите действие:");
        System.out.println(MENU);

        Scanner scanner = new Scanner(System.in);
        int menuChoice;
        while (true) {
            String str = scanner.nextLine();
            if (str.isEmpty()) {
                continue;
            }

            try {
                menuChoice = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("Введено недопустимое значение: " + str + ". Доступны целые числа от 0 до 5");
                continue;
            }

            switch (menuChoice) {
                case 1 -> {
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine();

                    System.out.print("Введите телефон: ");
                    String phone = scanner.nextLine();

                    System.out.print("Введите email: ");
                    String email = scanner.nextLine();

                    System.out.print("Введите группу: ");
                    String group = scanner.nextLine();

                    Contact contact = new Contact(name, phone, email, group);

                    try {
                        contactBook.addContact(contact);
                        System.out.println("Контакт успешно сохранён.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Введите телефон контакта для удаления: ");
                    String phone = scanner.nextLine();

                    try {
                        contactBook.deleteContact(phone);
                        System.out.println("Контакт успешно удалён.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    if (contactBook.isEmpty()) {
                        System.out.println("Контактная книга пуста");
                    } else {
                        contactBook.printAllContacts();
                    }
                }
                case 4 -> {
                    System.out.print("Введите имя контакта для поиска: ");
                    String name = scanner.nextLine();

                    try {
                        Contact contact = contactBook.findContact(name);
                        System.out.println(contact);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.print("Введите название группы: ");
                    String group = scanner.nextLine();

                    try {
                        contactBook.printContactsByGroup(group);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 0 -> {
                    System.out.println("Программа завершена.");
                    return;
                }
                default -> {
                    System.out.println("Введено некорректное число: " + menuChoice + ". Доступны целые числа от 0 до 5");
                    continue;
                }
            }

            System.out.println();
        }
    }
}