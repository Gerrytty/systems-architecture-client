package interfaces;

/***
 * Интерфейс для коллекций по которым можно пройтись итератором
 * @param <T> класс элементов коллекции
 ***/
public interface IterableCollectionI<T> extends CollectionI<T> {
    IteratorI<T> createIterator();
}
