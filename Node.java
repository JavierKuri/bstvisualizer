import java.awt.*;

class Node {
    int value;
    int coordx;
    int coordy;
    Node left;
    Node right;
    int radius = 40;    

    //Constructor
    public Node(int value, int coordx, int coordy, Node left, Node right) {
        this.value = value;
        this.coordx = coordx;
        this.coordy = coordy;
        this.left = null;
        this.right = null;
    }

    //Getters and setters
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getCoordx() {
        return coordx;
    }
    public void setCoordx(int coordx) {
        this.coordx = coordx;
    }
    public int getCoordy() {
        return coordy;
    }
    public void setCoordy(int coordy) {
        this.coordy = coordy;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }

    //Methods for drawing each node
    public void drawNode(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillOval(coordx - radius, coordy - radius, 2 * radius, 2 * radius);

        g.setColor(Color.BLACK);
        g.drawOval(coordx - radius, coordy - radius, 2 * radius, 2 * radius);
        g.drawString("Value=" + value, coordx - 10, coordy + 5);

        // Draw λ and μ only if this node connects to next
        if(left != null) {
            int startX = coordx + radius;
            int startY = coordy - radius;
            int endX = left.coordx - radius;
            int endY = left.coordy + radius;
            g.drawLine(startX, startY, endX, endY);
        }

        if(right != null) {
            int startX = coordx + radius;
            int startY = coordy - radius;
            int endX = right.coordx - radius;
            int endY = right.coordy + radius;
            g.drawLine(startX, startY, endX, endY);
        }
    }
}