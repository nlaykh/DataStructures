import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree {
    Node root;

    public void insert(Node node) {
        root = insertHelper(root, node);
    }

    private Node insertHelper(Node root, Node node) {
        int data = node.data;
        if (root == null) {
            root = node;
            return root;
        } else if (data < root.data)
            root.left = insertHelper(root.left, node);
        else
            root.right = insertHelper(root.right, node);
        return root;
    }

    public void display() {
        displayHelper(root);
    }

    private void displayHelper(Node root) {
        if (root != null) {
            displayHelper(root.left);
            System.out.println(root.data);
            displayHelper(root.right);
        }
    }

    public boolean search(int data) {
        return searchHelper(root, data);
    }

    private boolean searchHelper(Node root, int data) {
        if (root == null)
            return false;
        else if (root.data == data)
            return true;
        else if (root.data > data)
            return searchHelper(root.left, data);
        else
            return searchHelper(root.right, data);
    }

    public void remove(int data) {
        if (search(data))
            removeHelper(root, data);
        else
            System.out.println(data + " could not be found");
    }


    private Node removeHelper(Node root, int data) {
        if (root == null) return root;
        else if (data < root.data)
            root.left = removeHelper(root.left, data);
        else if (data > root.data)
            root.right = removeHelper(root.right, data);
        else {
            if (root.left == null && root.right == null) {
                root = null;
            }
            else if (root.right != null) {
                root.data = successor(root);
                root.right = removeHelper(root.right, root.data);
            }
            else {
                root.data = predecessor(root);
                root.left = removeHelper(root.left, root.data);
            }
        }

        return root;
    }

    public int successor(Node root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    public int predecessor(Node root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }

    public void inorder() {
        inorderHelper(root);
    }

    private void inorderHelper(Node root) {
        if (root != null) {
            inorderHelper(root.left);
            System.out.print(root.data + " ");
            inorderHelper(root.right);
        }
    }

    public void preorder() {
        preorderHelper(root);
    }

    private void preorderHelper(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderHelper(root.left);
            preorderHelper(root.right);
        }
    }

    public void postorder() {
        postorderHelper(root);
    }

    private void postorderHelper(Node root) {
        if (root != null) {
            postorderHelper(root.left);
            postorderHelper(root.right);
            System.out.print(root.data + " ");
        }
    }

    public int findMin() {
        Node minNode = findMinHelper(root);
        if (minNode != null) {
            return minNode.data;
        } else {
            return -1;
        }
    }

    private Node findMinHelper(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public int findMax() {
        Node maxNode = findMaxHelper(root);
        if (maxNode != null) {
            return maxNode.data;
        } else {
            return -1;
        }
    }

    private Node findMaxHelper(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }


    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = heightHelper(root.left);
        int rightHeight = heightHelper(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(Node root) {
        if (root == null) {
            return 0;
        }
        return sizeHelper(root.left) + sizeHelper(root.right) + 1;
    }

    public boolean isValidBST() {
        return isValidBSTHelper(root, null, null);
    }

    private boolean isValidBSTHelper(Node root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.data <= min) || (max != null && root.data >= max)) {
            return false;
        }
        return isValidBSTHelper(root.left, min, root.data) && isValidBSTHelper(root.right, root.data, max);
    }

    public void printTree() {
        printTreeHelper(root, 0);
    }

    private void printTreeHelper(Node root, int depth) {
        if (root != null) {
            printTreeHelper(root.right, depth + 1);
            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }
            System.out.println(root.data);
            printTreeHelper(root.left, depth + 1);
        }
    }

    public void levelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    public boolean contains(int key) {
        return search(key);
    }

    public void balance() {
        List<Node> nodes = new ArrayList<>();
        storeInOrder(root, nodes);

        root = buildBalancedBST(nodes, 0, nodes.size() - 1);
    }

    private void storeInOrder(Node root, List<Node> nodes) {
        if (root != null) {
            storeInOrder(root.left, nodes);
            nodes.add(root);
            storeInOrder(root.right, nodes);
        }
    }

    private Node buildBalancedBST(List<Node> nodes, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node midNode = nodes.get(mid);

        midNode.left = buildBalancedBST(nodes, start, mid - 1);
        midNode.right = buildBalancedBST(nodes, mid + 1, end);

        return midNode;
    }

    public String serialize() {
        StringBuilder serializedTree = new StringBuilder();
        serializeHelper(root, serializedTree);
        return serializedTree.toString();
    }

    private void serializeHelper(Node root, StringBuilder serializedTree) {
        if (root == null) {
            serializedTree.append("null ");
            return;
        }

        serializedTree.append(root.data).append(" ");
        serializeHelper(root.left, serializedTree);
        serializeHelper(root.right, serializedTree);
    }

    public List<Integer> rangeQuery(int start, int end) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, start, end, result);
        return result;
    }

    private void rangeQueryHelper(Node root, int start, int end, List<Integer> result) {
        if (root == null) {
            return;
        }

        if (root.data >= start && root.data <= end) {
            result.add(root.data);
        }

        if (root.data > start) {
            rangeQueryHelper(root.left, start, end, result);
        }

        if (root.data < end) {
            rangeQueryHelper(root.right, start, end, result);
        }
    }

    public BinarySearchTree copy() {
        BinarySearchTree newTree = new BinarySearchTree();
        newTree.root = copyHelper(root);
        return newTree;
    }

    private Node copyHelper(Node root) {
        if (root == null) {
            return null;
        }

        Node newNode = new Node(root.data);
        newNode.left = copyHelper(root.left);
        newNode.right = copyHelper(root.right);

        return newNode;
    }

    public int kthSmallest(int k) {
        List<Integer> inOrderList = new ArrayList<>();
        inOrderTraversal(root, inOrderList);

        if (k > 0 && k <= inOrderList.size()) {
            return inOrderList.get(k - 1);
        } else {
            // Handle out-of-bounds k
            return Integer.MIN_VALUE;
        }
    }

    public int kthLargest(int k) {
        List<Integer> inOrderList = new ArrayList<>();
        inOrderTraversal(root, inOrderList);

        int n = inOrderList.size();
        if (k > 0 && k <= n) {
            return inOrderList.get(n - k);
        } else {
            return Integer.MIN_VALUE;
        }
    }

    private void inOrderTraversal(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left, result);
        result.add(root.data);
        inOrderTraversal(root.right, result);
    }


    public void update(int key, int newValue) {
        root = updateHelper(root, key, newValue);
    }

    private Node updateHelper(Node root, int key, int newValue) {
        if (root == null) {
            return null;
        }

        if (key < root.data) {
            root.left = updateHelper(root.left, key, newValue);
        } else if (key > root.data) {
            root.right = updateHelper(root.right, key, newValue);
        } else {
            root.data = newValue;
        }

        return root;
    }

}
