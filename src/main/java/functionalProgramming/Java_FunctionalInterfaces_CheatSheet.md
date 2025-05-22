
# Java 8: `java.util.function` Package - Summary Cheat Sheet

Java 8 introduced the `java.util.function` package to enable functional programming using lambda expressions and the Stream API. It provides a set of commonly used **functional interfaces**.

---

## ✅ Why `java.util.function` Was Added

- To support lambda expressions and method references
- To enable passing functions as arguments
- To provide a standard set of interfaces for functional programming
- To be the foundation of the Streams API

---

## 🧩 Core Functional Interfaces

| Interface           | Description                                | Method Signature         | Example Use Case                             |
| ------------------- | ------------------------------------------ | ------------------------ | -------------------------------------------- |
| `Function<T,R>`     | Takes T, returns R                         | `R apply(T t)`           | Convert String to Integer                    |
| `Predicate<T>`      | Takes T, returns boolean                   | `boolean test(T t)`      | Check if a number is even                    |
| `Consumer<T>`       | Takes T, returns void                      | `void accept(T t)`       | Print a message                              |
| `Supplier<T>`       | Takes nothing, returns T                   | `T get()`                | Provide a random number                      |
| `BiFunction<T,U,R>` | Takes T and U, returns R                   | `R apply(T t, U u)`      | Add two numbers                              |
| `BiConsumer<T,U>`   | Takes T and U, returns void                | `void accept(T t, U u)`  | Print name and age                           |
| `UnaryOperator<T>`  | A Function where input and output are same | `T apply(T t)`           | Convert string to uppercase                  |
| `BinaryOperator<T>` | A BiFunction with same types               | `T apply(T t1, T t2)`    | Find max of two values                       |
| `BiPredicate<T,U>`  | Takes T and U, returns boolean             | `boolean test(T t, U u)` | Check if two strings are equal ignoring case |

---

## 🔚 Summary

- These interfaces make Java functional and concise.
- They're critical in the Stream API and lambda-based operations.
- Use them to simplify logic and improve code clarity.

---

## 🔄 Functional Interfaces in Stream Operations

Stream operations in Java use functional interfaces from `java.util.function`. Here's what each common method takes:

| **Stream Method**         | **Functional Interface**               | **Method Signature**               | **Example Use**                        |
|---------------------------|----------------------------------------|------------------------------------|----------------------------------------|
| `filter()`                | `Predicate<T>`                         | `boolean test(T t)`                | Keep only even numbers                 |
| `map()`                   | `Function<T, R>`                       | `R apply(T t)`                     | Convert strings to lengths             |
| `flatMap()`               | `Function<T, Stream<R>>`               | `Stream<R> apply(T t)`             | Flatten nested lists                   |
| `forEach()`               | `Consumer<T>`                          | `void accept(T t)`                 | Print each element                     |
| `sorted()`                | Uses `Comparable<T>`                   | `int compareTo(T o)`               | Sort naturally                         |
| `sorted(Comparator)`      | `Comparator<T>`                        | `int compare(T o1, T o2)`          | Sort by custom comparator              |
| `reduce()`                | `BinaryOperator<T>` or `(T, T) -> T`   | `T apply(T t1, T t2)`              | Sum, max, concatenate, etc.            |
| `peek()`                  | `Consumer<T>`                          | `void accept(T t)`                 | Debug or log elements                  |
| `allMatch()`, `anyMatch()`| `Predicate<T>`                         | `boolean test(T t)`                | Check match conditions                 |
| `collect()`               | `Collector<T, A, R>` (not in util.function) | Specialized collector interface | Accumulate to List, Map, etc.          |

---