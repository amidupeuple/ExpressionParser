package main;

import main.Tokenizer.Token;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dpivovar on 28.10.2016.
 */
public class Parser {
    LinkedList<Token> tokens;
    Token lookahead;

    public void parse(List<Token> tokens) {
        this.tokens = new LinkedList<>();
        this.tokens = (LinkedList<Token>) ((LinkedList<Token>) tokens).clone();
        lookahead = this.tokens.getFirst();

        Node resNode = expression();
        System.out.println("Expression result=" + resNode.getValue());

        if (lookahead.token != Token.EPSILON) {
            throw new ParserException(String.format("Unexpected %s symbol found", lookahead));
        }
    }

    private Node expression() {
        Node operandNode = logicalOperand();
        return logicalOperator(operandNode);
    }

    private Node logicalOperator(Node operandNode) {
        Node logicalOperator = null;
        boolean isAnd = false;

        if (lookahead.token == Token.LOGICAL_AND_OR) {

            if (lookahead.sequence.equalsIgnoreCase("AND")) {
                isAnd = true;
                logicalOperator = new LogicalAndOperator();
            } else {
                isAnd = false;
                logicalOperator = new LogicalOrOperator();
            }


            nextToken();
            Node rightOperand = logicalOperand();

            if (isAnd) {
                ((LogicalAndOperator) logicalOperator).setRight(rightOperand);
                ((LogicalAndOperator) logicalOperator).setLeft(operandNode);
            } else {
                ((LogicalOrOperator) logicalOperator).setRight(rightOperand);
                ((LogicalOrOperator) logicalOperator).setLeft(operandNode);
            }
        }

        return logicalOperator;
    }

    private Node logicalOperand() {
        Node result = null;
        if (lookahead.token == Token.FIELD_NAME) {
            Node fieldNode = new FieldNode(lookahead.sequence.replace("'", ""), "abcde");
            nextToken();
            Node further = simpleRightEqualPart();
            if (further != null) {
                EqualOperator tmp = (EqualOperator) further;
                tmp.setField((FieldNode) fieldNode);
                result = tmp;
            }
        }
        return result;
    }

    private Node simpleRightEqualPart() {
        if (lookahead.token == Token.EQUAL) {
            Node equalNode = new EqualOperator();
            nextToken();
            Node right = simpleRightPart();
            ((EqualOperator) equalNode).setLiteral(((LiteralNode) right));

            return equalNode;
        } else {
            return null;
        }
    }

    private Node simpleRightPart() {
        if (lookahead.token == Token.LITERAL) {
            String val = lookahead.sequence.replace("\"", "");
            Node node = new LiteralNode(val);
            nextToken();
            return node;
        } else {
            throw new ParserException("Unexpected symbol " + lookahead.sequence + " found");
        }
    }

    private void nextToken() {
        tokens.pop();

        if (tokens.isEmpty()) {
            lookahead = new Token(Token.EPSILON, "");
        } else {
            lookahead = tokens.getFirst();
        }
    }
}
