import sys
from antlr4 import *
from Ucs2Parser import Ucs2Parser
from Ucs2Listener import Ucs2Listener
from antlr4.error.ErrorListener import *
import io

class Ucs2ErrorListener(ErrorListener):

    def __init__(self, output):
        self.output = output
        self._symbol = ''

    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        self.output.write(msg)
        if offendingSymbol is not None:
            self._symbol = offendingSymbol.text
        else:
            self._symbol = "??"

    @property
    def symbol(self):
        return self._symbol
