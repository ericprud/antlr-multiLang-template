#!/usr/bin/env node

UCS2 = (function () {
  let parser = null;
  let ret = function (input) {
    const antlr4 = require('antlr4');
    const ucs2Lexer = require('./lib/ucs2Lexer').ucs2Lexer;
    const ucs2Parser = require('./lib/ucs2Parser').ucs2Parser;
    const ucs2Listener = require('./lib/ucs2Listener').ucs2Listener;

    const istream = new antlr4.InputStream(input);
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

    //    antlr4.tree.ParseTreeWalker.DEFAULT.walk(ucs2Listener, tree);

    var Customucs2Listener = function() {
      ucs2Listener.call(this);
      return this;
    };

    // continue inheriting default listener
    Customucs2Listener.prototype = Object.create(ucs2Listener.prototype);
    Customucs2Listener.prototype.constructor = Customucs2Listener;

    var printer = new Customucs2Listener();
    antlr4.tree.ParseTreeWalker.DEFAULT.walk(printer, tree);

    return tree;
  }
  ret.reset = function () {}
  return ret;
})()

if (typeof require !== 'undefined' && typeof exports !== 'undefined') {
  if (module.parent) /* node require */ {
    module.exports = UCS2;
  } else /* istanbul ignore next -- node command line interface */ {
    let buffer = '';
    process.stdin.setEncoding('utf8');
    process.stdin.on('readable', () => {
      let chunk;
      while ((chunk = process.stdin.read())) { buffer += chunk; }
    });
    process.stdin.on('end', () => {
      UCS2(buffer);
      UCS2.reset();
    });
  }
}
