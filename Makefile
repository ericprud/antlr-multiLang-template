all: jst

jslib/ucs2Parser.js: ucs2.g4
	antlr4 -o jslib -Dlanguage=JavaScript ucs2.g4

jst: jslib/ucs2Parser.js
	npx jest
