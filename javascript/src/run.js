#!/usr/bin/env node

UCS2 = (function () {
  let parser = null;
  let ret = function (input) {
    const antlr4 = require('antlr4');
    const ucs2Lexer = require('../generated-src/Ucs2Lexer').Ucs2Lexer;
    const ucs2Parser = require('../generated-src/Ucs2Parser').Ucs2Parser;
    const ucs2Listener = require('../generated-src/Ucs2Listener').Ucs2Listener;
    const Ucs2JsonListener = require('./Ucs2JsonListener').Ucs2JsonListener;

    const istream = new antlr4.CharStreams.fromString(input)
    const lexer = new ucs2Lexer(istream);
    const tokens  = new antlr4.CommonTokenStream(lexer);
    parser = new ucs2Parser(tokens);
    parser.buildParseTrees = true;
    let errorHandler = new antlr4.error.BailErrorStrategy();
    errorHandler.reportError = function (recognizer, e) {
      debugger;
    }
    parser._errHandler = errorHandler;
    parser.removeErrorListeners();

    lexer.removeErrorListeners();
    lexer.recover = function (re) {
      re.getOffendingToken = () => 'unknown lexical token';
      re.getMessage = () => re.message;
      throw re;
    }
    const tree = parser.ucs2();

    var l = new Ucs2JsonListener();
    antlr4.tree.ParseTreeWalker.DEFAULT.walk(l, tree);
    return l.pairs
  }
  ret.reset = function () {}
  return ret;
})()

if (typeof require !== 'undefined' && typeof exports !== 'undefined') {
  if (module.parent) /* node require */ {
    module.exports = UCS2;
  } else /* istanbul ignore next -- node command line interface */ {
    let istream;
    if (process.argv.length == 2 || process.argv[1] === '-') {
      istream = process.stdin;
    } else {
      istream = require('fs').createReadStream(process.argv[2], { encoding: 'utf-8'});
    }

    let buffer = '';
    istream.setEncoding('utf8');
    istream.on('readable', () => {
      let chunk;
      while ((chunk = istream.read())) { buffer += chunk; }
    });
    istream.on('end', () => {
      console.log(JSON.stringify(UCS2(buffer)));
      UCS2.reset();
    });
  }
}
