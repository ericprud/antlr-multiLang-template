all: jst

jslib/Ucs2Parser.js: Ucs2.g4
	antlr4 -o jslib -Dlanguage=JavaScript Ucs2.g4

jst: jslib/Ucs2Parser.js
	npx jest
