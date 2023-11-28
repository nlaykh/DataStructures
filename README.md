# DataStructures
My own implementation of main data structures

This repository contains implementations of various data structures in Java.

## Contents

- [BitVector](BitVector.java): Implementation of a BitVector.
- [DoublyLinkedList](DoublyLinkedList.java): Implementation of a Doubly Linked List.
- [MyVector](MyVector.java): Implementation of a custom Vector class.
- [Queue](Queue.java): Implementation of a Queue.
- [Stack](Stack.java): Implementation of a Stack.

## How to Use

Each Java file contains a class implementation of a specific data structure. You can include these files in your Java project and use the classes accordingly.

### Example

```java
// Example of using DoublyLinkedList
public class Main {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> myList = new DoublyLinkedList<>(new DllNode<>(1));
        myList.addToEnd(2);
        myList.addToEnd(3);

        System.out.println("Doubly Linked List: " + myList);
    }
}
```
## Contributing
If you would like to contribute or report issues, feel free to create a pull request or open an issue.

