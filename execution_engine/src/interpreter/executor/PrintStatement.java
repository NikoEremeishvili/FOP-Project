package interpreter.executor;


/**
 * Represents a print statement, e.g., PRINT X.
 */
public class PrintStatement implements Statement {
    private String variableName;

    public PrintStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        if (symbolTable.contains(variableName)) {
            System.out.println(variableName + " = " + symbolTable.get(variableName));
        } else {
            throw new RuntimeException("Variable '" + variableName + "' is not defined.");
        }
    }
}