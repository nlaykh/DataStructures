public class Stack {
    private int maxSize;
    private int top;
    private int[] stackArray;
    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        this.stackArray = new int[maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(int value) {
        if (this.isFull()) {
            System.out.println("Cannot push: Stack overflow");
            return;
        }
        stackArray[++top] = value;
    }

    public int pop() {
        if (this.isEmpty()) {
            System.out.println("Cannot pop. Stack underflow");
            return -1;
        }
        return stackArray[top--];
    }

    public int peek() {
        if (this.isEmpty()) {
            System.out.println("Cannot peek. Stack is empty");
            return -1;
        }
        return stackArray[top];
    }
 }
