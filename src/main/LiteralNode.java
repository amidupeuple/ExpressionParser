package main;

/**
 * Created by dpivovar on 28.10.2016.
 */
public class LiteralNode implements Node {
    private String value;

    public LiteralNode(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getType() {
        return Node.LITERAL_NODE;
    }
}
