from antlr4 import *
from Ucs2Lexer import Ucs2Lexer
from Ucs2Parser import Ucs2Parser
from Ucs2JsonListener import Ucs2JsonListener
from Ucs2ErrorListener import Ucs2ErrorListener
import unittest
import io

# Tests SHOULD be dynamically-generated from:
tests = [
    {"text": 'aA� 12 cd 34', "pass": True },
    {"text": 'aA�𐀀 12 cd 34', "pass": True },
    {"text": 'ab 1$ cd 34', "pass": False },
    {"text": 'ab cd 34', "pass": False },
]

class TestUcs2Parser(unittest.TestCase):

    def setup (self, text, passes):
        lexer = Ucs2Lexer(InputStream(text))
        stream = CommonTokenStream(lexer)
        parser = Ucs2Parser(stream)

        self.error = io.StringIO()

        errorListener = Ucs2ErrorListener(self.error)
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)
        lexer.removeErrorListeners()
        lexer.addErrorListener(errorListener)

        self.errorListener = errorListener

        tree = parser.ucs2()
        if passes:
            self.assertEqual(len(self.errorListener.symbol), 0)
            jsonUcs2 = Ucs2JsonListener()
            walker = ParseTreeWalker()
            walker.walk(jsonUcs2, tree)
            return jsonUcs2.pairs
        else:
            self.assertNotEqual(len(self.errorListener.symbol), 0)
            return None

    def test_BMP (self): self.setup('aA� 12 cd 34', True)
    def test_outside_BMP (self): self.setup('aA�𐀀 12 cd 34', True)
    def test_lexter_error (self): self.setup('aA 1$ cd 34', False)
    def test_parser_error (self): self.setup('aA cd 34', False)

if __name__ == '__main__':
    unittest.main()
