#my working space/branch, still trying to wrap my head around github...
Pushed my work, it is tokenizer ( takes a line of code, like LET x = 10, and splits it into individual tokens ) , Next, the Parser class processes these tokens, it checks the first token to determine what type of statement it is, if the first token is LET, it creates an AssignmentStatement that stores the variable name (x) and its value (10), if the first token is PRINT, it creates a PrintStatement that holds the variable to print.  Statements are stored as objects  (AssignmentStatement or PrintStatement).