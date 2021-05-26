package impls;

import interfaces.IterableCollectionI;
import interfaces.IteratorI;

import java.util.ArrayList;
import java.util.List;


/***
 * Имлементация интерфейса IterableCollection
 * Хранит элементы в ArrayList
 * @param <T> элемент, который мы хотим добавить в нашу коллекцию
 ***/
public class ConcreteCollection<T> implements IterableCollectionI<T> {

    // Контейнер для элементов
    private final List<T> container;

    public ConcreteCollection() {
        this.container = new ArrayList<>();
    }

    @Override
    public IteratorI<T> createIterator() {
        return new ConcreteIterator<T>(this);
    }

    // добавление элемента
    @Override
    public void add(T item) {
        container.add(item);
    }

    // достать элемент по индексу
    @Override
    public T get(int i) {
        return container.get(i);
    }

    // получить общее количество элементов
    @Override
    public int size() {
        return container.size();
    }
}
