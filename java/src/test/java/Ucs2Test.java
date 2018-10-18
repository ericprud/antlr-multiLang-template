/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;
import static org.junit.Assert.*;
import me.ericprud.examples.ucs2.Ucs2Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class Ucs2Test {
    @Test public void t_unicodeString() throws Exception {
        Ucs2 ucs2 = new Ucs2();
        String t = "aA�𐀀󯿽 12 cd 34";
        Ucs2Parser.Ucs2Context ctx = ucs2.parseString(t);
        String l = ctx.pair(0).VAR().getText();
        String r = t.substring(0, t.indexOf(' '));
        assertTrue("first token should match input", l.equals(r));
        // System.err.println(uify(l));
    }

    @Test public void t_missingToken() {
        Ucs2 ucs2 = new Ucs2();
        String t = "aA�𐀀󯿽 cd 34";
        try {
            Ucs2Parser.Ucs2Context ctx = ucs2.parseString(t);
            assertFalse("should not parse", true);
        } catch (ParseCancellationException e) {
        } catch (Exception e) {
        }
    }

    @Test public void t_unknownChar() {
        Ucs2 ucs2 = new Ucs2();
        String t = "aA�က𐀀󯿽 cd 34";
        try {
            Ucs2Parser.Ucs2Context ctx = ucs2.parseString(t);
            assertFalse("should not parse", true);
        } catch (ParseCancellationException e) {
        } catch (Exception e) {
        }
    }

    static String uify (String s) {
        String ret = "";
        final int length = s.length();
        for (int i = 0; i < length; ) {
            final int sc = s.codePointAt(i);
            ret += String.format("\\u{%05x}", sc);
            i += Character.charCount(sc);
        }
        return ret;
    }
}

/* resources:
 *   https://github.com/graphql-java/graphql-java/blob/master/src/main/java/graphql/parser/Parser.java
 *   https://stackoverflow.com/questions/18132078/handling-errors-in-antlr4/18139305#18139305
 */