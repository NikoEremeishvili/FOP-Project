package interpreter.statements;

import interpreter.core.Statement;
import interpreter.execution.SymbolTable;

/**
 * Represents a NEXT statement to continue a FOR loop.
 */
public class NextStatement implements Statement {
    private String loopVariable;

    public NextStatement(String loopVariable) {
        this.loopVariable = loopVariable;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        // The actual continuation of the loop is handled in the Executor
    }

    @Override
    public String toString() {
        return "NEXT " + loopVariable;
    }
}