package interpreter.core;

import interpreter.execution.SymbolTable;

public abstract class Expression {
	public abstract int evaluate(SymbolTable symbolTable);
}

