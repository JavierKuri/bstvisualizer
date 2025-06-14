import java.awt.*;

public class Node {
    int value;
    int coordx, coordy;
    Node left, right;
    int radius = 30;
    boolean isHighlighted = false;

    public Node(int value, int coordx, int coordy, Node left, Node right) {
        this.value = value;
        this.coordx = coordx;
        this.coordy = coordy;
        this.left = left;
        this.right = right;
    }

    public void drawNode(Graphics2D g) {
        // Draw lines to children from center to center
        g.setColor(Color.BLACK);
        if (left != null) {
            g.drawLine(coordx, coordy, left.coordx, left.coordy);
        }
        if (right != null) {
            g.drawLine(coordx, coordy, right.coordx, right.coordy);
        }

        // Draw node circle
        g.setColor(isHighlighted ? Color.RED : Color.ORANGE);
        g.fillOval(coordx - radius, coordy - radius, 2 * radius, 2 * radius);

        // Draw border and value text centered
        g.setColor(Color.BLACK);
        g.drawOval(coordx - radius, coordy - radius, 2 * radius, 2 * radius);
        String valStr = String.valueOf(value);
        FontMetrics fm = g.getFontMetrics();
        int strWidth = fm.stringWidth(valStr);
        int strHeight = fm.getAscent();
        g.drawString(valStr, coordx - strWidth / 2, coordy + strHeight / 2 - 4);
    }
}
