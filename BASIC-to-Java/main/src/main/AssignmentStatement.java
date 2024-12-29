package main;

/**
 * Represents an assignment statement, e.g., LET X = 10.
 */
public class AssignmentStatement implements Statement {
    private String variableName;
    private Expression value;

    /**
     * Constructor for an assignment statement.
     * 
     * @param variableName The name of the variable.
     * @param value        The value to assign.
     */
    public AssignmentStatement(String variableName, Expression value) {
        this.variableName = variableName;
        this.value= value;
    }
    public String toString() {
    	return "LET " + variableName + " = " + value;
  }

    @Override
    public void execute(SymbolTable symbolTable) {
        // Assign the value to the variable in the symbol table
        symbolTable.set(variableName, value);
    }
}