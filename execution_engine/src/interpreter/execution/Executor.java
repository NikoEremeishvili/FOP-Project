package interpreter.execution;

import interpreter.core.Statement;
import interpreter.statements.GotoStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("Label Map: " + labelIndexMap);

        int i = 0;
        while (i < program.size()) {
            Statement statement = program.get(i);

            System.out.println("Executing statement at index: " + i + " (Label: " + statement.getLabel() + ")");

            if (statement instanceof GotoStatement) {
                GotoStatement gotoStatement = (GotoStatement) statement;
                Integer targetLabel = gotoStatement.getTargetLabel();

                if (!labelIndexMap.containsKey(targetLabel)) {
                    throw new RuntimeException("Label not found: " + targetLabel);
                }

                System.out.println("GOTO jumping to label: " + targetLabel);
                i = labelIndexMap.get(targetLabel); // Jump to the target label
                continue; // Restart the loop after jumping
            } else {
                statement.execute(symbolTable);
                i++; // Increment only if no GOTO
            }
        }
    }
}