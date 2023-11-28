public class DoublyLinkedList<T> {
    DllNode<T> head;
    public DoublyLinkedList(DllNode<T> head) {
        this.head = head;
    }

    void addToEnd(T data) {
        DllNode<T> n = new DllNode<>(data);
        if (head == null)
            head = n;
        else {
            DllNode<T> curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = n;
            n.prev = curr;
        }
    }

    void addToStart(T data) {
        DllNode<T> n = new DllNode<>(data);
        if (head == null)
            head = n;
        else {
            n.next = head;
            head.prev = null;
            head = n;
        }
    }

    void addAfter(DllNode<T> curr, T insertAfter, T data) {
        if (curr == null) return;
        if (curr.data == insertAfter) {
            DllNode<T> n = new DllNode<>(data);
            if (curr.next != null) {
                curr.next.prev = n;
                n.next = curr.next;
            }
            curr.next = n;
            n.prev = curr;
        }
        else addAfter(curr.next, insertAfter, data);
    }

    DllNode<T> deleteLast() {
        DllNode<T> toDelete = head;
        if (head == null || head.next == null) {
            head = null;
            return toDelete;
        }
        while (toDelete.next != null)
            toDelete = toDelete.next;
        toDelete.prev.next = null;
        return toDelete;
    }

    DllNode<T> deleteFirst() {
        DllNode<T> toDelete = head;
        if (head == null || head.next == null) {
            head = null;
            return toDelete;
        }
        head = head.next;
        head.prev = null;
        return toDelete;
    }

    DllNode<T> deleteAfter(T data) {
        DllNode<T> toDelete = head;
        for (; toDelete != null; toDelete = toDelete.next) {
            if (toDelete.data.equals(data)) {
                toDelete = toDelete.next;
                break;
            }
        }
        if (toDelete != null) {
            if (toDelete.next != null)
                toDelete.next.prev = toDelete.prev;
            toDelete.prev.next = toDelete.next;
        }
        return toDelete;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        DllNode<T> current = head;

        while (current != null) {
            result.append(current.data);
            if (current.next != null) {
                result.append(", ");
            }
            current = current.next;
        }

        result.append("]");
        return result.toString();
    }
}
