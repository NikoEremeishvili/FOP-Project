package interpreter.statements;

import interpreter.core.Expression;
import interpreter.core.Statement;
import interpreter.execution.SymbolTable;
import interpreter.expressions.StringExpression;

import java.util.List;

public class PrintStatement implements Statement {
    private List<Expression> expressions;

    public PrintStatement(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        StringBuilder output = new StringBuilder();

        for (Expression expression : expressions) {
            if (expression instanceof StringExpression) {
                output.append(((StringExpression) expression).getValue());
            } else {
                output.append(expression.evaluate(symbolTable));
            }
            output.append(" "); // Add space between expressions
        }

        System.out.println(output.toString().trim());
    }

    @Override
    public String toString() {
        return "PRINT " + expressions;
    }
}
