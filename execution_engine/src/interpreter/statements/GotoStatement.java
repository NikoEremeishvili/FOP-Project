package interpreter.statements;

import interpreter.core.Statement;
import interpreter.execution.Executor;
import interpreter.execution.SymbolTable;

/**
 * Represents a GOTO statement for jumping to a labeled statement.
 */
public class GotoStatement implements Statement {
    private Integer targetLabel;

    public GotoStatement(Integer targetLabel) {
        this.targetLabel = targetLabel;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        System.out.println("Executing GOTO to label: " + targetLabel);
        // The actual jump happens in the Executor
    }

    public Integer getTargetLabel() {
        return targetLabel;
    }

    @Override
    public String toString() {
        return "GOTO " + targetLabel;
    }
}