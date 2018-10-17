import sys
from antlr4 import CommonTokenStream, FileStream, InputStream, ParseTreeWalker
sys.path.insert(0, '../generated-src')
from Ucs2Lexer import Ucs2Lexer
from Ucs2Parser import Ucs2Parser
from Ucs2JsonListener import Ucs2JsonListener
from io import StringIO
import binascii
from functools import reduce
import json

def main(argv):
    input = FileStream(argv[1], encoding="utf-8")
    # input = InputStream(open(argv[1], 'rb').read().decode("utf-8"))

    lexer = Ucs2Lexer(input)
    stream = CommonTokenStream(lexer)
    parser = Ucs2Parser(stream)
    tree = parser.ucs2()

    jsonUcs2 = Ucs2JsonListener()
    walker = ParseTreeWalker()
    walker.walk(jsonUcs2, tree)
    return jsonUcs2.pairs

if __name__ == '__main__':
    sys.stdout.write(json.dumps(main(sys.argv)) + "\n")
