package interpreter.executor;

import java.util.List;

public class IfStatement implements Statement {
    private Expression condition;
    private List<Statement> body;

    public IfStatement(Expression condition, List<Statement> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        // Execute the body if the condition evaluates to true
        if (condition.evaluate(symbolTable) != 0) {
            for (Statement statement : body) {
                statement.execute(symbolTable);
            }
        }
    }

    @Override
    public String toString() {
        return "IF " + condition + " THEN " + body;
    }
}