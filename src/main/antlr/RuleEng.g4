// https://stackoverflow.com/questions/40276432/how-to-define-logical-operator-with-parenthesis-in-antlr-grammar

grammar RuleEng; 

/* This will be the entry point of the parser. */ 

parse 
    :   
        expression EOF 
    ; 
expression 
    :   '(' expression ')'            # parenExp
    |   '{' expression '}'            # curlyExp
    |   'NOT' expression              # notExpr
    |   expression 'AND' expression   # andExpr
    |   expression '&&' expression    # andExpr
    |   expression 'OR' expression    # orExpr
    |   expression '||' expression    # orExpr
    |   expression '==' expression    # eqExpr
    |   expression '>=' expression    # gteqExpr
    |   expression '<=' expression    # lteqExpr
    |   expression '>' expression     # gtExpr
    |   expression '<' expression     # ltExpr
    |   ID                            # atomExpr
    ;
    
binOp 
    : 
        ('AND' | 'OR' | '&&' | '||' | '==' | '>=' | '<=' | '>=' | '>' | '<' )  
    ; 
unOp 
    : 
        'NOT' 
    ; 
ID      :   
        ('a'..'z' | 'A'..'Z' | '0'..'9' | '.')+  
    ; 
    
WS 
    : [ \t\r\n] -> skip
    ;
    