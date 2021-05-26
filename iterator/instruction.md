## Требования:
- Java 8 и выше

## Описание ***Паттерн итератор***

***Структура проекта***
- класс Main - пример работы с итератором

- пакет interfaces содержит в себе 3 интерфейса: CollectionI (для абстрактной коллекции), IteratorI (для реализации итератора), IterableCollectionI (для итерируемой коллекции)
- пакет impls сщдуржит в себе классы: ConcreteCollection (Реализует интерфейс IterableCollectionI) и ConcreteIterator (реализует интрефейс IteratorI)
- интерфейс IterableCollectionI наследует CollectionI