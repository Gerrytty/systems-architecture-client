package interfaces;

/***
 * Интерфейс коллекции
 * @param <T> класс элементов, хранящихся в коллекции
 ***/
public interface CollectionI<T> {
    // добавление элемента
    void add(T item);
    // получить элемент по индексу
    T get(int index);
    // получить общее число элементов в коллекции
    int size();
}
