package interpreter.statements;

import java.util.List;

import interpreter.core.Expression;
import interpreter.core.Statement;
import interpreter.execution.SymbolTable;

public class WhileStatement implements Statement {
    private Expression condition;
    private List<Statement> body;

    public WhileStatement(Expression condition, List<Statement> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        // Evaluate the condition and execute the body while it's true
        while (condition.evaluate(symbolTable) != 0) {
            for (Statement statement : body) {
                statement.execute(symbolTable);
            }
        }
    }

    @Override
    public String toString() {
        return "WHILE " + condition + " DO " + body;
    }
}