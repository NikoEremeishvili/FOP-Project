package interpreter.executor;


/**
 * Represents a numeric literal expression.
 */

public class NumberExpression extends Expression {
    private int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(SymbolTable symbolTable) {
        return value;
    }
}
