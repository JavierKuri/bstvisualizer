import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tree tree = new Tree(10, 450, 50);
            tree.addNode(5);
            tree.addNode(15);
            tree.addNode(3);
            tree.addNode(7);
            tree.addNode(12);
            tree.addNode(18);

            TreePanel treePanel = new TreePanel(tree);
            JScrollPane scrollPane = new JScrollPane(treePanel);
            scrollPane.setPreferredSize(new Dimension(900, 600));

            JTextField inputField = new JTextField(5);
            JButton addButton = new JButton("Add Node");

            JTextField outputField = new JTextField();
            outputField.setEditable(false);

            JButton inorderButton = new JButton("Inorder");
            JButton preorderButton = new JButton("Preorder");
            JButton postorderButton = new JButton("Postorder");

            addButton.addActionListener(e -> {
                try {
                    int val = Integer.parseInt(inputField.getText());
                    tree.addNode(val);
                    treePanel.refresh();
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer");
                }
            });

            inorderButton.addActionListener(e -> new Thread(() -> {
                List<Integer> result = new ArrayList<>();
                clearHighlights(tree.getInitialNode());
                inorderStep(tree.getInitialNode(), result, treePanel);
                outputField.setText("Inorder: " + result);
            }).start());

            preorderButton.addActionListener(e -> new Thread(() -> {
                List<Integer> result = new ArrayList<>();
                clearHighlights(tree.getInitialNode());
                preorderStep(tree.getInitialNode(), result, treePanel);
                outputField.setText("Preorder: " + result);
            }).start());

            postorderButton.addActionListener(e -> new Thread(() -> {
                List<Integer> result = new ArrayList<>();
                clearHighlights(tree.getInitialNode());
                postorderStep(tree.getInitialNode(), result, treePanel);
                outputField.setText("Postorder: " + result);
            }).start());

            JPanel topPanel = new JPanel();
            topPanel.add(new JLabel("Value:"));
            topPanel.add(inputField);
            topPanel.add(addButton);
            topPanel.add(inorderButton);
            topPanel.add(preorderButton);
            topPanel.add(postorderButton);

            JFrame frame = new JFrame("Binary Tree Visualizer");
            frame.setLayout(new BorderLayout());
            frame.add(topPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(outputField, BorderLayout.SOUTH);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void inorderStep(Node node, List<Integer> result, TreePanel panel) {
        if (node == null) return;
        inorderStep(node.left, result, panel);
        highlightNode(node, result, panel);
        inorderStep(node.right, result, panel);
    }

    private static void preorderStep(Node node, List<Integer> result, TreePanel panel) {
        if (node == null) return;
        highlightNode(node, result, panel);
        preorderStep(node.left, result, panel);
        preorderStep(node.right, result, panel);
    }

    private static void postorderStep(Node node, List<Integer> result, TreePanel panel) {
        if (node == null) return;
        postorderStep(node.left, result, panel);
        postorderStep(node.right, result, panel);
        highlightNode(node, result, panel);
    }

    private static void highlightNode(Node node, List<Integer> result, TreePanel panel) {
        node.isHighlighted = true;
        panel.refresh();
        try {
            Thread.sleep(700);
        } catch (InterruptedException ignored) {}
        result.add(node.value);
        node.isHighlighted = false;
        panel.refresh();
    }

    private static void clearHighlights(Node node) {
        if (node == null) return;
        node.isHighlighted = false;
        clearHighlights(node.left);
        clearHighlights(node.right);
    }
}
