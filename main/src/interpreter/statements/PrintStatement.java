package interpreter.statements;

import interpreter.core.Expression;
import interpreter.core.Statement;
import interpreter.execution.SymbolTable;

/**
 * Represents a print statement, e.g., PRINT X.
 */
public class PrintStatement implements Statement {
    private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }
    @Override
    public String toString() {
        return "PRINT " + expression;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        int value = expression.evaluate(symbolTable);
        System.out.println(value);
    }
}