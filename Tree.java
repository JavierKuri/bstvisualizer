public class Tree {
    private Node initialNode;

    public Tree(int rootValue, int x, int y) {
        this.initialNode = new Node(rootValue, x, y, null, null);
    }

    public void addNode(int value) {
        if (initialNode == null) {
            initialNode = new Node(value, 400, 50, null, null);
            return;
        }

        Node current = initialNode;
        int offsetX = 60;
        int offsetY = 60;

        while (true) {
            if (value == current.value) {
                return;
            } else if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value, current.coordx - offsetX, current.coordy + offsetY, null, null);
                    return;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = new Node(value, current.coordx + offsetX, current.coordy + offsetY, null, null);
                    return;
                } else {
                    current = current.right;
                }
            }
        }
    }

    public Node getInitialNode() {
        return initialNode;
    }
}
