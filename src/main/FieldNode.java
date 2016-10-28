package main;

/**
 * Created by dpivovar on 28.10.2016.
 */
public class FieldNode implements Node {
    private String fieldName;
    private String fieldValue;

    public FieldNode(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public String getValue() {
        return fieldValue;
    }

    @Override
    public int getType() {
        return Node.FIELD_NAME_NODE;
    }
}
