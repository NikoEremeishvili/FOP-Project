package interpreter.expressions;

import interpreter.core.Expression;
import interpreter.execution.SymbolTable;

/**
 * Represents a string literal expression.
 */
public class StringExpression extends Expression {
    private String value;

    public StringExpression(String value) {
        this.value = value;
    }

    @Override
    public int evaluate(SymbolTable symbolTable) {
        throw new UnsupportedOperationException("StringExpression cannot be evaluated to an integer.");
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }
}
