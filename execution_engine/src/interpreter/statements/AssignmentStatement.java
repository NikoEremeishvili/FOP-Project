package interpreter.statements;

import interpreter.core.Expression;
import interpreter.core.Statement;
import interpreter.execution.SymbolTable;

/**
 * Represents an assignment statement, e.g., LET X = 10 or LET X = Y + 5.
 */
public class AssignmentStatement implements Statement {
    private String variableName;
    private Expression expression;

    /**
     * Constructor for an assignment statement.
     * 
     * @param variableName The name of the variable.
     * @param expression   The expression to evaluate and assign.
     */
    public AssignmentStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        // Evaluate the expression and assign the result to the variable
        int value = expression.evaluate(symbolTable);
        symbolTable.set(variableName, value);
    }
}
