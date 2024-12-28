package main;

/**
 * Represents a print statement, e.g., PRINT X.
 */
public class PrintStatement implements Statement {
    public VariableExpression variableExpression;

    public PrintStatement(VariableExpression variableExpression) {
        this.variableExpression = variableExpression;
    }

        public String toString() {
            return "PRINT " + variableExpression;
        }

    @Override
    public void execute(SymbolTable symbolTable) {
        if (symbolTable.contains(variableExpression)) {
            int value = symbolTable.getValue(variableExpression);
            System.out.println(variableExpression + " = " + value);
        } else {
            throw new RuntimeException("Variable '" + variableExpression + "' is not defined.");
        }
    }
}