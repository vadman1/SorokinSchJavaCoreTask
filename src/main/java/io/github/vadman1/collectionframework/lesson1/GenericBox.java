package io.github.vadman1.collectionframework.lesson1;

import java.util.Objects;

public class GenericBox<T> {

    private T value;

    public GenericBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isEqual(GenericBox<T> other) {
        return Objects.equals(this.getValue(), other.getValue());
    }

    public void swap(GenericBox<T> other) {
        T temp = this.getValue();

        this.setValue(other.getValue());
        other.setValue(temp);
    }
}