Subclass/Child : A class that directly inherits from another class using extends.

Subtype : A broader term for any type that is compatible with another via inheritance or interface implementation.

--------------------------------------------------------------------------------------------------------------------------------------------------------------
In Java, **subclass** (often referred to as a "child" class) and **inheritance** are closely related concepts, but the
terms "subtype" and "child" have distinct meanings in the context of inheritance. Here's a clear explanation of the
difference between **subtype** and **child** in Java inheritance:

### 1. **Child (Subclass)**:

- A **child** class (or **subclass**) is a class that directly extends another class (called the **parent** or *
  *superclass**) using the `extends` keyword in Java.
- The child class **inherits** fields, methods, and other members of the parent class, allowing it to reuse or override
  the parent's functionality.
- The relationship between a child and parent class is explicitly defined in the code through inheritance.
- Example:
  ```java
  class Animal {  // Parent class
      void eat() {
          System.out.println("This animal eats food.");
      }
  }

  class Dog extends Animal {  // Child class (subclass)
      void bark() {
          System.out.println("The dog barks.");
      }
  }
  ```
    - Here, `Dog` is a **child** (or subclass) of `Animal` because it directly extends `Animal`.
    - The child class inherits the `eat()` method and can add its own methods (like `bark()`) or override inherited
      methods.

### 2. **Subtype**:

- A **subtype** is a broader concept that refers to a type (class or interface) that can be used interchangeably with
  another type (its supertype) without breaking the program's correctness. This is based on the **Liskov Substitution
  Principle** (LSP).
- Subtyping in Java is achieved through:
    - **Class inheritance**: A subclass is a subtype of its superclass.
    - **Interface implementation**: A class that implements an interface is a subtype of that interface.
    - **Interface inheritance**: An interface that extends another interface is a subtype of the parent interface.
- A subtype does not necessarily imply a direct parent-child relationship; it could be achieved through interfaces or
  multiple levels of inheritance.
- Example:
  ```java
  interface Flyable {
      void fly();
  }

  class Bird implements Flyable {  // Bird is a subtype of Flyable
      public void fly() {
          System.out.println("The bird flies.");
      }
  }

  class Sparrow extends Bird {  // Sparrow is a child of Bird and a subtype of both Bird and Flyable
      void chirp() {
          System.out.println("The sparrow chirps.");
      }
  }
  ```
    - Here, `Sparrow` is a **child** of `Bird` (because it directly extends `Bird`).
    - `Sparrow` is a **subtype** of both `Bird` and `Flyable` (because it inherits from `Bird` and indirectly implements
      `Flyable`).
    - `Bird` is a **subtype** of `Flyable` (because it implements the `Flyable` interface), but `Flyable` is not a
      parent class—it’s an interface.

### Key Differences Between Subtype and Child in Java Inheritance:

| **Aspect**       | **Child (Subclass)**                                       | **Subtype**                                                                               |
|------------------|------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| **Definition**   | A class that directly extends another class (superclass).  | A type that can be used in place of another type (class or interface).                    |
| **Relationship** | Explicitly defined using the `extends` keyword.            | Can be achieved via class inheritance or interface implementation.                        |
| **Scope**        | Refers specifically to class inheritance.                  | Broader concept, includes both class inheritance and interface implementation.            |
| **Example**      | `Dog extends Animal` makes `Dog` a child of `Animal`.      | `Dog` is a subtype of `Animal` and can also be a subtype of an interface like `Runnable`. |
| **Directness**   | Always a direct relationship (parent-child).               | Can be indirect (e.g., a class implementing an interface or extending a subclass).        |
| **Polymorphism** | A child class is inherently a subtype of its parent class. | A subtype can be a class or interface, enabling polymorphic behavior.                     |

### Key Points to Understand:

- **All child classes are subtypes** of their parent class, but **not all subtypes are child classes**. For example, a
  class implementing an interface is a subtype of that interface but not its child (since interfaces are not classes).
- Subtyping enables **polymorphism**, allowing objects of a subtype to be treated as objects of their supertype. For
  example:
  ```java
  Animal animal = new Dog();  // Polymorphism: Dog (child/subtype) assigned to Animal (parent/supertype)
  animal.eat();  // Calls inherited method
  ```
- Interfaces play a significant role in subtyping because a class can implement multiple interfaces, making it a subtype
  of multiple types without being a child of those interfaces.

### Summary:

- A **child** class is a class that directly extends a parent class, establishing a direct inheritance relationship.
- A **subtype** is a broader concept where a class or interface can be used in place of another type, achieved through
  class inheritance or interface implementation.
- The term "child" is specific to class inheritance, while "subtype" encompasses both class inheritance and interface
  implementation, making it a more general concept in Java's type system.