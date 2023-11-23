import java.util.Arrays;
import java.util.Iterator;

public class MyVector<T> implements Iterator<T> {
    private T[] array;
    private int size;
    private int capacity = 2;
    private int currentIndex = 0;
    public MyVector() {
        array = (T[]) new Object[capacity];
        size = capacity;
    }

    public MyVector(T[] array) {
        this.array = array;
        this.size = array.length;
        this.capacity = array.length;
    }

    public void setArray(T[] array) {
        this.array = array;
        this.size = array.length;
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int search(T key) {
        boolean found = false;
        int res = -1;
        for (int i = 0; i < size; ++i)
            if (array[i] == key) {
                found = true;
                res = i;
            }
        return res;
    }

    public boolean insert(T value) {
        if (size == capacity) {
            capacity *= 2;
            T[] newArr = (T[]) new Object[capacity];
            for (int i = 0; i < size; ++i)
                newArr[i] = array[i];
            this.array = newArr;
        }

        array[size] = value;
        size++;
        return true;
    }


    public boolean remove(T value) {
        int index = search(value);
        if (index < 0) return false;

        T[] newArr = (T[]) new Object[capacity];

        for (int i = 0; i < index; i++) {
            newArr[i] = array[i];
        }

        for (int i = index + 1; i < size; i++) {
            newArr[i - 1] = array[i];
        }

        this.array = newArr;
        this.size--;
        return true;
    }

    public boolean removeByIndex(int index) {
        if (index < 0 || index >= size) {
            return false; // Index out of bounds
        }

        T[] newArr = (T[]) new Object[capacity];
        for (int i = 0; i < index; i++) {
            newArr[i] = array[i];
        }

        for (int i = index + 1; i < size; i++) {
            newArr[i - 1] = array[i];
        }

        this.array = newArr;
        this.size--;
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        array = (T[]) new Object[capacity];
        size = 0;
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(array[i]);
            if (i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public boolean hasNext() {
        return currentIndex < size;
    }

    @Override
    public T next() {
        try {
            if (hasNext()) {
                T element = array[currentIndex];
                currentIndex++;
                return element;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return null;
        }
    }

}
