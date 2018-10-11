grammar ucs2;

ucs2: pair+ EOF { /* debugger; console.log(localctx.children.map(c => c.getText().charCodeAt(0).toString(16)).join(' ')) */};
pair : VAR NUM ;

PASS : [ \t\r\n]+ -> skip;
VAR : ([a-zA-Z\ufffd] | [\uD800-\uDB7F][\uDC00-\uDFFF])+ ;
NUM : [0-9]+ ;
