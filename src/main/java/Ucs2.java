/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import me.ericprud.examples.ucs2.Ucs2Parser;
import me.ericprud.examples.ucs2.Ucs2Lexer;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStream;
import java.util.Date;

public class Ucs2 {
    Ucs2Parser p;
    public Ucs2Parser.Ucs2Context parseString(String input) throws ParseCancellationException {
        CharStream stream = CharStreams.fromString(input);
        ANTLRInputStream stream2 = new ANTLRInputStream(input);
        Ucs2Lexer lexer = new Ucs2Lexer(stream2);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Ucs2Parser parser = new Ucs2Parser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        lexer.removeErrorListeners();
        parser.removeErrorListeners(); // not tested
        // System.err.println(new Date());
        return parser.ucs2();
    }
}
