public class Tree {
    private Node initialNode;

    public Tree(int rootValue, int x, int y) {
        this.initialNode = new Node(rootValue, x, y, null, null);
    }

    public void addNode(int value) {
        addNodeRecursive(initialNode, value, initialNode.coordx, initialNode.coordy, 60, 1);
    }


    private void addNodeRecursive(Node current, int value, int x, int y, int offsetX, int depth) {
        if (value == current.value) return; 

        int verticalGap = 60;

        if (value < current.value) {
            if (current.left == null) {
                int childX = x - (offsetX * (int)Math.pow(2, Math.max(0, 3 - depth)));
                int childY = y + verticalGap;
                current.left = new Node(value, childX, childY, null, null);
            } else {
                addNodeRecursive(current.left, value, current.left.coordx, current.left.coordy, offsetX, depth + 1);
            }
        } else {
            if (current.right == null) {
                int childX = x + (offsetX * (int)Math.pow(2, Math.max(0, 3 - depth)));
                int childY = y + verticalGap;
                current.right = new Node(value, childX, childY, null, null);
            } else {
                addNodeRecursive(current.right, value, current.right.coordx, current.right.coordy, offsetX, depth + 1);
            }
        }
    }


    public Node getInitialNode() {
        return initialNode;
    }
}
