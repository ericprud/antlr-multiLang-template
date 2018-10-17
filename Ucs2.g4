grammar Ucs2;

ucs2: pair+ EOF { };
pair : VAR NUM ;

PASS : [ \t\r\n]+ -> skip;
VAR : ([a-zA-Z]
        | [\uFDF0-\uFFFD]
        | [\u{10000}-\u{EFFFD}] // #if CharStream_interface (java-only)
        // | [\uD800-\uDB7F][\uDC00-\uDFFF] // #if ANTLRInputStream_interface
        )+;
NUM : [0-9]+ ;
