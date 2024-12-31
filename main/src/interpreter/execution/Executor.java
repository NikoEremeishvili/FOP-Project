package interpreter.execution;

import interpreter.core.Statement;
import interpreter.statements.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Executor {
	private SymbolTable symbolTable;
	private Map<Integer, Integer> labelIndexMap; // Maps labels to statement indices

	public Executor() {
		this.symbolTable = new SymbolTable();
		this.labelIndexMap = new HashMap<>();
	}

	public void execute(List<Statement> program) {
		// Build the label map
		for (int i = 0; i < program.size(); i++) {
			Statement statement = program.get(i);
			if (statement.getLabel() != null) {
				labelIndexMap.put(statement.getLabel(), i);
			}
		}

		int i = 0;
		Set<Integer> executedStatements = new HashSet<>();

		while (i < program.size()) {
			Statement statement = program.get(i);

			if (statement instanceof GotoStatement) {
				GotoStatement gotoStatement = (GotoStatement) statement;
				Integer targetLabel = gotoStatement.getTargetLabel();

				if (!labelIndexMap.containsKey(targetLabel)) {
					throw new RuntimeException("Label not found: " + targetLabel);
				}

				i = labelIndexMap.get(targetLabel); // Jump to the target label
				continue; // Restart loop after jumping
			}

			statement.execute(symbolTable);

			// If statement is not part of a loop, prevent re-execution
			if (!(statement instanceof ForStatement || statement instanceof WhileStatement)) {
				executedStatements.add(i);
			}

			i++; // Increment only if no GOTO
		}
	}
}