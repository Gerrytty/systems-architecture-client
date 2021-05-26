package interfaces;

/***
 * Интерфейс итератора
 * @param <T> класс элементов, хранящихся в коллекции
 ***/
public interface IteratorI<T> {
    // проверить не пустая ли коллекция
    boolean hasNext();
    // получить следующий элемент
    T next();
}
