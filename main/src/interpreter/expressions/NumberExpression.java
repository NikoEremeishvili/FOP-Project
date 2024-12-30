package interpreter.expressions;

import interpreter.core.Expression;
import interpreter.execution.SymbolTable;

/**
 * Represents a numeric literal expression.
 */

public class NumberExpression extends Expression {
    private int value;

    public NumberExpression(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int evaluate(SymbolTable symbolTable) {
        return value;
    }
}
