public class Queue <T extends Object> {
    private T[] array;
    private int count;
    private int capacity;
    private int rear;
    private int front;

    public Queue(int size) {
        capacity = size;
        count = 0;
        rear = -1;
        front = 0;
        array = (T[]) new Object[size];
    }

    public boolean isFull() {
        return count == capacity;
    }

    public boolean isEmpty() {
        return count == 0;
    }



    public void enqueue(T obj) {
        if (isFull()) {
            increaseQueueCapacity();
        }
        rear = (rear + 1) % capacity;
        array[rear] = obj;
        ++count;
    }

    public T dequeue() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }
        T obj = array[front];
        front = (front + 1) % capacity;
        --count;
        return obj;
    }

    public T peek() throws IllegalStateException
    {
        if (isEmpty())  {
            throw new IllegalStateException("queue is empty");
        }
        return array[front];
    }

    public void increaseQueueCapacity() {
        int newCapacity = capacity * 2;
        T[] newArray = (T[]) new Object[newCapacity];

        int index = 0;
        for (int i = front; i <= rear; i++) {
            newArray[index++] = array[i % capacity];
        }

        array = newArray;
        front = 0;
        rear = count - 1;
        capacity = newCapacity;
    }
}
