package main;

/**
 * Created by dpivovar on 28.10.2016.
 */
public class LogicalOrOperator implements Node {
    private Node left;
    private Node right;

    public LogicalOrOperator(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public LogicalOrOperator() {
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String getValue() {
        String result = null;

        boolean l = Boolean.valueOf(left.getValue());
        boolean r = Boolean.valueOf(right.getValue());

        if (l || r) {
            result = "true";
        } else {
            result = "false";
        }
        return result;
    }

    @Override
    public int getType() {
        return Node.LOGICAL_AND_OR;
    }
}
