package interpreter.statements;

import interpreter.core.Statement;
import interpreter.execution.SymbolTable;
import interpreter.core.Expression;

import java.util.List;

public class IfStatement implements Statement {
    private Expression condition;
    private List<Statement> thenBody;
    private List<Statement> elseBody; // Optional ELSE body

    public IfStatement(Expression condition, List<Statement> thenBody, List<Statement> elseBody) {
        this.condition = condition;
        this.thenBody = thenBody;
        this.elseBody = elseBody; // Can be null if no ELSE branch exists
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        int conditionValue = condition.evaluate(symbolTable);

        if (conditionValue != 0) { // Condition is true
            for (Statement statement : thenBody) {
                statement.execute(symbolTable);
            }
        } else if (elseBody != null) { // Condition is false, execute ELSE body
            for (Statement statement : elseBody) {
                statement.execute(symbolTable);
            }
        } else {
            System.out.println("Condition not met, no ELSE body to execute...");
        }
    }

    @Override
    public String toString() {
        return "IF " + condition + " THEN " + thenBody +
               (elseBody != null ? " ELSE " + elseBody : "");
    }
}
