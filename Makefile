lib/ucs2Parser.js: ucs2.g4
	antlr4 -o lib -Dlanguage=JavaScript ucs2.g4

t: lib/ucs2Parser.js
	npx jest
