package interpreter.parser;

import java.util.List;

import interpreter.core.Expression;
import interpreter.core.Statement;
import interpreter.expressions.*;
import interpreter.statements.*;

public class Parser {

	public Statement parse(List<String> tokens) {
		String command = tokens.get(0);

		if (command.equals("LET")) {
			String variable = tokens.get(1);
			Expression value = parseExpression(tokens.subList(3, tokens.size()));
			return new AssignmentStatement(variable, value);
		} else if (command.equals("PRINT")) {
			Expression expression = parseExpression(tokens.subList(1, tokens.size()));
			return new PrintStatement(expression);
		} else if (command.equals("IF")) {
			if (!tokens.contains("THEN")) {
				throw new IllegalArgumentException("Invalid IF statement: Missing THEN keyword");
			}

			// Parse condition (tokens before THEN)
			int thenIndex = tokens.indexOf("THEN");
			Expression condition = parseExpression(tokens.subList(1, thenIndex));

			// Parse body (tokens after THEN)
			List<String> thenTokens = tokens.subList(thenIndex + 1, tokens.size());
			List<Statement> body = List.of(parse(thenTokens)); // Parse the body as a list of statements

			return new IfStatement(condition, body);
		} else if (command.equals("WHILE")) {
			if (!tokens.contains("DO")) {
				throw new IllegalArgumentException("Invalid WHILE statement: Missing DO keyword.");
			}

			int doIndex = tokens.indexOf("DO");

			// Parse condition (tokens before DO)
			Expression condition = parseExpression(tokens.subList(1, doIndex));

			// Parse body (tokens after DO)
			List<String> bodyTokens = tokens.subList(doIndex + 1, tokens.size());
			List<Statement> body = List.of(parse(bodyTokens)); // Parse as a list of statements

			return new WhileStatement(condition, body);
		} else if (command.equals("FOR")) {
			if (!tokens.contains("DO")) {
				throw new IllegalArgumentException("Invalid FOR statement: Missing DO keyword.");
			}

			String variable = tokens.get(1);

			// Parse start and end expressions
			Expression start = parseExpression(tokens.subList(3, tokens.indexOf("TO")));
			Expression end = parseExpression(tokens.subList(tokens.indexOf("TO") + 1, tokens.indexOf("DO")));

			// Parse body (tokens after DO)
			List<String> bodyTokens = tokens.subList(tokens.indexOf("DO") + 1, tokens.size());
			List<Statement> body = List.of(parse(bodyTokens)); // Parse as a list of statements

			return new ForStatement(variable, start, end, body);
		}

		throw new IllegalArgumentException("Unsupported command: " + command);
	}

	// Parses expressions (numbers, variables, simple comparison)
	private Expression parseExpression(List<String> tokens) {
		if (tokens.size() == 1) {
			String token = tokens.get(0);
			if (Character.isDigit(token.charAt(0))) {
				return new NumberExpression(Integer.parseInt(token)); // Single number
			} else {
				return new VariableExpression(token); // Single variable
			}
		}

		// Handles operations
		if (tokens.size() == 3) {
			String leftOperand = tokens.get(0);
			String operator = tokens.get(1);
			String rightOperand = tokens.get(2);

			Expression left = new VariableExpression(leftOperand); // Assuming it's a variable
			Expression right = new NumberExpression(Integer.parseInt(rightOperand)); // Assuming it's a number

			// For arithmetic operators
			if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
				return new BinaryExpression(left, right, operator);
			}

			// For comparison
			if (operator.equals(">") || operator.equals("<") || operator.equals(">=") || operator.equals("<=")
					|| operator.equals("==")) {
				return new BinaryExpression(left, right, operator);
			}
		}

		throw new IllegalArgumentException("Unsupported expression: " + tokens);
	}
}
