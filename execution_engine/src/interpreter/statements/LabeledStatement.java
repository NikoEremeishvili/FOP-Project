package interpreter.statements;

import interpreter.core.Statement;
import interpreter.execution.SymbolTable;

/**
 * Represents a statement with an optional label.
 */
public class LabeledStatement implements Statement {
    private Integer label;
    private Statement statement;

    public LabeledStatement(Integer label, Statement statement) {
        this.label = label;
        this.statement = statement;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        statement.execute(symbolTable);
    }

    @Override
    public Integer getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return (label != null ? label + ": " : "") + statement.toString();
    }
}