package interpreter.statements;

import interpreter.core.Expression;
import interpreter.core.Statement;
import interpreter.execution.SymbolTable;

import java.util.List;

public class ForStatement implements Statement {
    private String variableName;
    private Expression startExpression;
    private Expression endExpression;
    private List<Statement> loopBody;

    public ForStatement(String variableName, Expression startExpression, Expression endExpression, List<Statement> loopBody) {
        this.variableName = variableName;
        this.startExpression = startExpression;
        this.endExpression = endExpression;
        this.loopBody = loopBody;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        int start = startExpression.evaluate(symbolTable);
        int end = endExpression.evaluate(symbolTable);

        for (int i = start; i <= end; i++) {
            symbolTable.set(variableName, i); // Assign current value to loop variable
            for (Statement statement : loopBody) {
                statement.execute(symbolTable); // Execute loop body
            }
        }
    }

    @Override
    public String toString() {
        return "FOR " + variableName + " = " + startExpression + " TO " + endExpression + " DO " + loopBody;
    }
}