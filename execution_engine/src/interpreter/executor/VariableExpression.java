package interpreter.executor;


/**
 * Represents a variable expression.
 */
public class VariableExpression extends Expression {
    private String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(SymbolTable symbolTable) {
        return symbolTable.get(name);
    }
}
