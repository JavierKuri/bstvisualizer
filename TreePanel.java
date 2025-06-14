import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

class TreePanel extends JPanel {
    private Tree tree;

    public TreePanel(Tree tree) {
        this.tree = tree;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tree.getInitialNode() != null) {
            drawTree((Graphics2D) g, tree.getInitialNode());
        }
    }

    private void drawTree(Graphics2D g, Node node) {
        if (node == null) return;
        node.drawNode(g);
        drawTree(g, node.left);
        drawTree(g, node.right);
    }

    public void refresh() {
        repaint();
    }
}
