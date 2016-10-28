package main;

/**
 * Created by dpivovar on 28.10.2016.
 */
public interface Node {
    public static final int FIELD_NAME_NODE = 1;
    public static final int LITERAL_NODE = 2;
    public static final int EQUAL_NODE = 3;
    public static final int LOGICAL_AND_OR = 4;

    int getType();
    String getValue();
}
