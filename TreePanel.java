import javax.swing.*;
import java.awt.*;

public class TreePanel extends JPanel {
    private Tree tree;
    private int offsetX = 0;
    private int offsetY = 0;

    public TreePanel(Tree tree) {
        this.tree = tree;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tree != null && tree.getInitialNode() != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Apply translation to move the tree into visible positive coords
            g2d.translate(offsetX, offsetY);

            drawTree(g2d, tree.getInitialNode());

            g2d.dispose();
        }
    }

    private void drawTree(Graphics2D g, Node node) {
        if (node == null) return;

        if (node.left != null) {
            g.drawLine(node.coordx, node.coordy, node.left.coordx, node.left.coordy);
            drawTree(g, node.left);
        }
        if (node.right != null) {
            g.drawLine(node.coordx, node.coordy, node.right.coordx, node.right.coordy);
            drawTree(g, node.right);
        }

        node.drawNode(g);
    }

    @Override
    public Dimension getPreferredSize() {
        if (tree == null || tree.getInitialNode() == null) {
            return new Dimension(900, 600);
        }

        Rectangle bounds = getTreeBounds(tree.getInitialNode());
        int padding = 50;

        // Calculate offsets to shift the tree so that minX and minY >= 0
        offsetX = bounds.x < 0 ? -bounds.x + padding/2 : padding/2;
        offsetY = bounds.y < 0 ? -bounds.y + padding/2 : padding/2;

        return new Dimension(bounds.width + offsetX + padding, bounds.height + offsetY + padding);
    }

    private Rectangle getTreeBounds(Node node) {
        if (node == null) return new Rectangle(0, 0, 0, 0);

        Rectangle leftBounds = getTreeBounds(node.left);
        Rectangle rightBounds = getTreeBounds(node.right);

        int minX = Math.min(node.coordx - node.radius, Math.min(leftBounds.x, rightBounds.x));
        int minY = Math.min(node.coordy - node.radius, Math.min(leftBounds.y, rightBounds.y));

        int maxX = Math.max(node.coordx + node.radius, Math.max(leftBounds.x + leftBounds.width, rightBounds.x + rightBounds.width));
        int maxY = Math.max(node.coordy + node.radius, Math.max(leftBounds.y + leftBounds.height, rightBounds.y + rightBounds.height));

        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }

    public void refresh() {
        revalidate();
        repaint();
    }
}
