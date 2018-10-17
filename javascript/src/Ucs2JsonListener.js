const antlr4 = require('antlr4/index');
var Ucs2Listener = require('../generated-src/Ucs2Listener').Ucs2Listener;
 
Ucs2JsonListener = function () { // e.g. process.stdout
    this.pairs = null;
    Ucs2Listener.call(this); // inherit default listener
    return this;
};
 
// inherit default listener
Ucs2JsonListener.prototype = Object.create(Ucs2Listener.prototype);
Ucs2JsonListener.prototype.constructor = Ucs2JsonListener;
 
Ucs2JsonListener.prototype.exitPair = function (ctx) {
  this.pairs.push({
    var: ctx.VAR().getText(),
    num: parseInt(ctx.NUM().getText())
  })
};
 
Ucs2JsonListener.prototype.enterUcs2 = function (ctx) {              
    this.pairs = [];
};
 
exports.Ucs2JsonListener = Ucs2JsonListener;
