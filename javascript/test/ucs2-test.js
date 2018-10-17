const fs = require("fs");
// const Cancellation = require('antlr4').error.ParseCancellationException;
// console.log(require('antlr4').error);

ucs2 = require('../src/run.js');
describe("A UCS2 parser", function () {
  const tests = [
    {text: 'aA� 12 cd 34', pass: true },
    {text: 'aA�𐀀 12 cd 34', pass: true },
    {text: 'ab 1$ cd 34', pass: false },
    {text: 'ab cd 34', pass: false },
  ]
  tests.forEach(t => {
    if (t.pass) {
      it('should accept "' + t.text + '"', function () {
        let pairs = ucs2(t.text, t.charStream)
        expect(pairs[0].var).toEqual(t.text.substr(0, t.text.indexOf(' ')))
        ucs2.reset();
      })
    } else {
      it('should reject "' + t.text + '"', function (done) {
        let s;
        try {
          s = ucs2(t.text)
          done('this should not parse');
        } catch (e) {
          done();
        }
        ucs2.reset();
      })
    }
  });
})
