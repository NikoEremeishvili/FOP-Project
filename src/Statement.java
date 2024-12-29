// Class for statements
abstract class Statement {}

// Assignment statement (for example LET x = 10)
class AssignmentStatement extends Statement {
    String variable;
    Expression value;

    public AssignmentStatement(String variable, Expression value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public String toString() {
        return "LET " + variable + " = " + value;
    }
}

// Print statement (for example PRINT x)
class PrintStatement extends Statement {
    Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "PRINT " + expression;
    }
}

// If statement (for example IF x > 5 THEN PRINT x)
class IfStatement extends Statement {
    Expression condition;
    Statement thenStatement;

    public IfStatement(Expression condition, Statement thenStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
    }

    @Override
    public String toString() {
        return "IF " + condition + " THEN " + thenStatement;
    }
}

// While statement (for example WHILE x < 15 DO LET x = x + 1)
class WhileStatement extends Statement {
    Expression condition;
    Statement body;

    public WhileStatement(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public String toString() {
        return "WHILE " + condition + " DO " + body;
    }
}

// For statement (for example FOR i = 1 TO 5 DO PRINT i)
class ForStatement extends Statement {
    String variable;
    Expression start;
    Expression end;
    Statement body;

    public ForStatement(String variable, Expression start, Expression end, Statement body) {
        this.variable = variable;
        this.start = start;
        this.end = end;
        this.body = body;
    }

    @Override
    public String toString() {
        return "FOR " + variable + " = " + start + " TO " + end + " DO " + body;
    }
}
