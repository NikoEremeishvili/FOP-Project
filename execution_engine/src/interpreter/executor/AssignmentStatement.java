package interpreter.executor;

/**
 * Represents an assignment statement, e.g., LET X = 10.
 */
public class AssignmentStatement implements Statement {
    private String variableName;
    private int value;

    /**
     * Constructor for an assignment statement.
     * 
     * @param variableName The name of the variable.
     * @param value        The value to assign.
     */
    public AssignmentStatement(String variableName, int value) {
        this.variableName = variableName;
        this.value = value;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        // Assign the value to the variable in the symbol table
        symbolTable.set(variableName, value);
    }
}