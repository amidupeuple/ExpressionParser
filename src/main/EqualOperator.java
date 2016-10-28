package main;

/**
 * Created by dpivovar on 28.10.2016.
 */
public class EqualOperator implements Node {

    private Node field;
    private Node literal;

    public EqualOperator() {
    }

    public EqualOperator(Node field, Node literal) {
        this.field = field;
        this.literal = literal;
    }

    public void setField(Node field) {
        this.field = field;
    }

    public void setLiteral(Node literal) {
        this.literal = literal;
    }

    @Override
    public String getValue() {
        String result = null;
        if (field.getValue().equals(literal.getValue())) {
            result = "true";
        } else {
            result = "false";
        }
        return result;
    }

    @Override
    public int getType() {
        return Node.EQUAL_NODE;
    }
}
