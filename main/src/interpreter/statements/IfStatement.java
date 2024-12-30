package interpreter.statements;

import interpreter.core.Statement;
import interpreter.execution.SymbolTable;
import interpreter.core.Expression;

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
        int conditionValue = condition.evaluate(symbolTable);

        if (conditionValue != 0) { // Condition is true
            for (Statement statement : body) {
                statement.execute(symbolTable);
            }
        } else {
            System.out.println("Condition not met, skipping IF body...");
        }
    }

    @Override
    public String toString() {
        return "IF " + condition + " THEN " + body;
    }
}