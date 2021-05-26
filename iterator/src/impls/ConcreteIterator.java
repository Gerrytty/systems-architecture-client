package impls;

import interfaces.IterableCollectionI;
import interfaces.IteratorI;

/****
 * Реализация интерфейса interfaces.IteratorI
 * для коллекции impls.ConcreteCollection
 * @param <T> класс элементов коллекции
 ***/
public class ConcreteIterator<T> implements IteratorI<T> {

    private final IterableCollectionI<T> collection;
    private int state = 0;

    public ConcreteIterator(ConcreteCollection<T> collection) {
        this.collection = collection;
    }

    @Override
    public boolean hasNext() {
        return (collection.size() - state) > 0;
    }

    @Override
    public T next() {

        if (hasNext()) {
            return collection.get(state++);
        }

        return null;
    }
}
