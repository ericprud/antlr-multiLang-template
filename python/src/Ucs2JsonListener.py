import sys
from antlr4 import *
from Ucs2Parser import Ucs2Parser
from Ucs2Listener import Ucs2Listener

class Ucs2JsonListener(Ucs2Listener) :
    def __init__(self):
        self.pairs = None
        self.count = 0

    def exitPair(self, ctx:Ucs2Parser.PairContext):
        self.pairs.append({
            "var": ctx.VAR().getText(),
            "num": int(ctx.NUM().getText())
        })

    def enterUcs2(self, ctx:Ucs2Parser.Ucs2Context):
        self.pairs = []

