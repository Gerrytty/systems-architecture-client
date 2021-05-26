import impls.ConcreteCollection;
import interfaces.IterableCollectionI;
import interfaces.IteratorI;

/***
 * Пример работы с паттерном "итератор"
 ***/
public class Main {
    public static void main(String[] args) {

        // создаем итерабельную коллекцию
        IterableCollectionI<String> collection = new ConcreteCollection<>();

        // добавляем в нее элементы
        collection.add("First string");
        collection.add("Second string");
        collection.add("Third string");

        // создаем итератор
        IteratorI<String> iterator = collection.createIterator();

        // проходимся итератором по элементам коллекции
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
