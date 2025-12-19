package io.github.vadman1.collectionframework.finaltask;

import java.util.*;

public class ContactBook {

    private final List<Contact> contactsList = new ArrayList<>();

    private final Set<Contact> contactsSet = new HashSet<>();

    private final Map<String, List<Contact>> contactsByCategory = new HashMap<>();

    public boolean isEmpty() {
        return contactsSet.isEmpty();
    }

    public boolean isContains(Contact contact) {
        return contactsSet.contains(contact);
    }

    public void addContact(Contact contact) {
        if (isContains(contact)) {
            throw new IllegalArgumentException("Такой контакт уже существует!");
        } else {
            contactsList.add(contact);

            contactsSet.add(contact);

            String group = contact.getGroup();
            List<Contact> contactsInMap;
            if (contactsByCategory.containsKey(group)) {
                contactsInMap = contactsByCategory.get(group);
                contactsInMap.add(contact);
            } else {
                contactsInMap = new ArrayList<>();
                contactsInMap.add(contact);
                contactsByCategory.put(group, contactsInMap);
            }
        }
    }

    public void deleteContact(String phone) {
        Contact contact = getContactByPhone(phone);

        if (contact == null) {
            throw new IllegalArgumentException("Контакт по номеру " + phone + " не найден!");
        } else {
            contactsList.remove(contact);
            contactsSet.remove(contact);

            String group = contact.getGroup();
            List<Contact> contactsInMap = contactsByCategory.get(group);
            contactsInMap.remove(contact);
        }
    }

    public void printAllContacts() {
        Iterator<Contact> iterator = contactsList.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public Contact findContact(String name) {
        Contact contact = getContactByName(name);
        if (contact == null) {
            throw new IllegalArgumentException("Контакт по имени " + name + " не найден!");
        } else {
            return contact;
        }
    }

    public boolean isContainsGroup(String group) {
        return contactsByCategory.containsKey(group);
    }

    public void printContactsByGroup(String group) {
        if (isContainsGroup(group)) {
            List<Contact> contacts = contactsByCategory.get(group);

            Iterator<Contact> iterator = contacts.iterator();

            while (iterator.hasNext()) {
                Contact contact = iterator.next();
                System.out.println(contact.getName() + " | " + contact.getPhone() + " | " + contact.getEmail());
            }
        } else {
            throw new IllegalArgumentException("Группа " + group + " отсутствует в Контактной книге");
        }
    }

    private Contact getContactByPhone(String phone) {
        Iterator<Contact> iterator = contactsSet.iterator();

        Contact contact = null;
        while (iterator.hasNext()) {
            Contact cnt = iterator.next();
            if (cnt.getPhone().equals(phone))
                contact = cnt;
        }

        return contact;
    }

    private Contact getContactByName(String name) {
        Iterator<Contact> iterator = contactsSet.iterator();

        Contact contact = null;
        while (iterator.hasNext()) {
            Contact cnt = iterator.next();
            if (cnt.getName().equals(name))
                contact = cnt;
        }

        return contact;
    }
}