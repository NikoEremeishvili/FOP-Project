package interpreter.parser;

import interpreter.core.Expression;
import interpreter.core.Statement;
import interpreter.core.Tokenizer;
import interpreter.expressions.*;
import interpreter.statements.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Parser {

    private Iterator<String> lineIterator; // Iterator for program lines
    private Tokenizer tokenizer; // Tokenizer for tokenizing lines

    public Parser(Iterator<String> lineIterator, Tokenizer tokenizer) {
        this.lineIterator = lineIterator;
        this.tokenizer = tokenizer;
    }

    /**
     * Parses an expression from tokens.
     */
    private Expression parseExpression(List<String> tokens) {
        if (tokens.size() == 1) {
            // Single token: either a number or a variable
            String token = tokens.get(0);
            if (Character.isDigit(token.charAt(0))) {
                return new NumberExpression(Integer.parseInt(token)); // Numeric literal
            } else {
                return new VariableExpression(token); // Variable reference
            }
        }

        if (tokens.size() == 3) {
            // Binary operation: <left operand> <operator> <right operand>
            String leftOperand = tokens.get(0);
            String operator = tokens.get(1);
            String rightOperand = tokens.get(2);

            // Parse the left operand
            Expression left = Character.isDigit(leftOperand.charAt(0))
                    ? new NumberExpression(Integer.parseInt(leftOperand))
                    : new VariableExpression(leftOperand);

            // Parse the right operand
            Expression right = Character.isDigit(rightOperand.charAt(0))
                    ? new NumberExpression(Integer.parseInt(rightOperand))
                    : new VariableExpression(rightOperand);

            // Return the binary expression
            return new BinaryExpression(left, right, operator);
        }

        throw new IllegalArgumentException("Unsupported expression: " + tokens);
    }

    /**
     * Parses a statement from tokens.
     */
    public Statement parse(List<String> tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("Empty statement.");
        }

        // Check if the first token is a numeric label
        String firstToken = tokens.get(0);
        if (Character.isDigit(firstToken.charAt(0))) {
            // Remove the label before processing the statement
            tokens = tokens.subList(1, tokens.size());
        }

        String command = tokens.get(0).toUpperCase();

        switch (command) {
            case "LET":
                return parseLetStatement(tokens);
            case "PRINT":
                return parsePrintStatement(tokens);
            case "FOR":
                return parseForStatement(tokens);
            case "NEXT":
                return parseNextStatement(tokens);
            case "IF":
                return parseIfStatement(tokens);
            case "INPUT":
                return parseInputStatement(tokens);
            case "WHILE":
                return parseWhileStatement(tokens);
            case "WEND":
                // WEND is handled in parseWhileStatement and should not reach here
                return null;
            default:
                // Check if it's a variable assignment
                if (isVariableAssignment(tokens)) {
                    return parseAssignmentStatement(tokens);
                }
                throw new IllegalArgumentException("Unsupported command: " + command + ". Tokens: " + tokens);
        }
    }

    private boolean isVariableAssignment(List<String> tokens) {
        // Variable assignment syntax: <variable> = <expression>
        return tokens.size() >= 3 && tokens.get(1).equals("=");
    }

    private Statement parseLetStatement(List<String> tokens) {
        if (tokens.size() < 4 || !tokens.get(2).equals("=")) {
            throw new IllegalArgumentException("Invalid LET statement: Expected 'LET <variable> = <expression>'");
        }
        String variableName = tokens.get(1);
        Expression value = parseExpression(tokens.subList(3, tokens.size()));
        return new AssignmentStatement(variableName, value);
    }

    private Statement parseAssignmentStatement(List<String> tokens) {
        // Generic assignment without "LET": <variable> = <expression>
        String variableName = tokens.get(0); // e.g., TOTAL
        Expression value = parseExpression(tokens.subList(2, tokens.size())); // e.g., TOTAL + COUNT
        return new AssignmentStatement(variableName, value);
    }

    private Statement parsePrintStatement(List<String> tokens) {
        if (tokens.size() < 2) {
            throw new IllegalArgumentException("Invalid PRINT statement syntax.");
        }

        List<Expression> arguments = new ArrayList<>();
        for (String token : tokens.subList(1, tokens.size())) {
            if (token.equals(";")) {
                continue; // Ignore semicolons (used as separators)
            } else if (token.startsWith("\"") && token.endsWith("\"")) {
                // Treat quoted tokens as string literals
                arguments.add(new StringExpression(token.substring(1, token.length() - 1))); // Remove quotes
            } else {
                // Treat other tokens as expressions
                arguments.add(parseExpression(List.of(token)));
            }
        }
        return new PrintStatement(arguments);
    }
    
    private Statement parseWhileStatement(List<String> tokens) {
        if (tokens.size() < 2) {
            throw new IllegalArgumentException("Invalid WHILE statement: Missing condition.");
        }

        // Parse the condition
        List<String> conditionTokens = tokens.subList(1, tokens.size());
        Expression condition = parseExpression(conditionTokens);

        // Parse the loop body
        List<Statement> body = new ArrayList<>();
        while (lineIterator.hasNext()) {
            String nextLine = lineIterator.next().trim();

            // Skip empty lines
            if (nextLine.isEmpty()) continue;

            List<String> bodyTokens = tokenizer.tokenize(nextLine);

            // Break on encountering WEND
            if (bodyTokens.get(0).equalsIgnoreCase("WEND")) {
                break;
            }

            // Parse the body statement and ensure it is not null
            Statement statement = parse(bodyTokens);
            if (statement != null) {
                body.add(statement);
            }
        }

        if (body.isEmpty()) {
            throw new IllegalArgumentException("WHILE loop has no body: " + tokens);
        }

        return new WhileStatement(condition, body);
    }


    private Statement parseForStatement(List<String> tokens) {
        if (!tokens.contains("TO")) {
            throw new IllegalArgumentException("Invalid FOR statement: Missing TO keyword.");
        }

        String loopVar = tokens.get(1); // Loop variable
        Expression startExpression = parseExpression(tokens.subList(3, tokens.indexOf("TO"))); // Start value
        Expression endExpression = parseExpression(tokens.subList(tokens.indexOf("TO") + 1, tokens.size())); // End value

        // Collect statements for the loop body
        List<Statement> loopBody = new ArrayList<>();

        // Keep parsing lines until we encounter the NEXT statement
        while (lineIterator.hasNext()) {
            String nextLine = lineIterator.next().trim();
            List<String> nextTokens = tokenizer.tokenize(nextLine);

            if (nextTokens.get(0).equalsIgnoreCase("NEXT")) {
                if (!nextTokens.get(1).equalsIgnoreCase(loopVar)) {
                    throw new IllegalArgumentException("NEXT variable does not match FOR variable: " + loopVar);
                }
                break; // Stop when we find the NEXT statement
            }

            // Parse the next line and add it to the loop body
            loopBody.add(parse(nextTokens));
        }

        if (loopBody.isEmpty()) {
            throw new IllegalArgumentException("FOR loop has no body: " + tokens);
        }

        // Return the FOR statement with its body
        return new ForStatement(loopVar, startExpression, endExpression, loopBody);
    }

    private Statement parseNextStatement(List<String> tokens) {
        if (tokens.size() != 2) {
            throw new IllegalArgumentException("Invalid NEXT statement: Expected 'NEXT <variable>'");
        }
        String loopVariable = tokens.get(1);
        return new NextStatement(loopVariable);
    }

    private Statement parseIfStatement(List<String> tokens) {
        if (!tokens.contains("THEN")) {
            throw new IllegalArgumentException("Invalid IF statement: Missing THEN keyword.");
        }

        int thenIndex = tokens.indexOf("THEN");
        List<String> conditionTokens = tokens.subList(1, thenIndex); // Tokens for the condition
        Expression condition = parseExpression(conditionTokens); // Parse the condition

        List<String> thenTokens = tokens.subList(thenIndex + 1, tokens.size()); // Tokens after THEN

        // Check if ELSE exists
        List<String> elseTokens = null;
        if (thenTokens.contains("ELSE")) {
            int elseIndex = thenTokens.indexOf("ELSE");
            elseTokens = thenTokens.subList(elseIndex + 1, thenTokens.size()); // Tokens after ELSE
            thenTokens = thenTokens.subList(0, elseIndex); // Tokens for THEN branch
        }

        // Parse THEN and ELSE branches
        Statement thenStatement = parse(thenTokens);
        Statement elseStatement = elseTokens != null ? parse(elseTokens) : null;

        return new IfStatement(condition, List.of(thenStatement), elseStatement != null ? List.of(elseStatement) : null);
    }


    private Statement parseInputStatement(List<String> tokens) {
        if (tokens.size() < 4 || !tokens.get(2).equals(",")) {
            throw new IllegalArgumentException("Invalid INPUT statement: Expected 'INPUT \"<prompt>\", <variable>'");
        }
        String prompt = tokens.get(1).replace("\"", ""); // Remove quotes
        String variable = tokens.get(3);
        return new InputStatement(prompt, variable);
    }
}
