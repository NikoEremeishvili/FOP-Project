package interpreter.statements;

import java.util.List;

import interpreter.core.Statement;
import interpreter.execution.SymbolTable;

public class ForStatement implements Statement {
    private String variableName;
    private int start;
    private int end;
    private List<Statement> loopBody;

    public ForStatement(String variableName, int start, int end, List<Statement> loopBody) {
        this.variableName = variableName;
        this.start = start;
        this.end = end;
        this.loopBody = loopBody;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        for (int i = start; i <= end; i++) {
            symbolTable.set(variableName, i);
            for (Statement statement : loopBody) {
                statement.execute(symbolTable);
            }
        }
    }
}