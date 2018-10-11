grammar ucs2;

ucs2: pair+ EOF { debugger; /*console.log(localctx.children.map(c => c.getText().charCodeAt(0).toString(16)).join(' ')) */};
pair : VAR NUM ;

PASS : [ \t\r\n]+ -> skip;
// VAR : CHAR+ ;
// fragment CHAR : ([A-Z] | [a-z] | [d800-db7f][dc00-dfff]) ;
VAR : [a-zA-Z]+ ;
NUM : [0-9]+ ;
