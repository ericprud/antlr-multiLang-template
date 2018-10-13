/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;
import static org.junit.Assert.*;
import es.weso.shex.parser.ShExDocParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class Ucs2Test {
    @Test public void t_unicodeString() {
        Ucs2 ucs2 = new Ucs2();
        String t = ""//"_:AZazÀÖØöø˿ͰͽͿ῿‌‍⁰↏Ⰰ⿯、퟿豈﷏ﷰ�𐀀󯿽 {}";
+ "<http://a.example/S1> {\n"
+ "   <http://a.example/p1> @_:AZazÀÖØöø˿ͰͽͿ῿‌‍⁰↏Ⰰ⿯、퟿豈﷏ﷰ�𐀀󯿽\n"
+ "}\n"
+ "\n"
+ "_:AZazÀÖØöø˿ͰͽͿ῿‌‍⁰↏Ⰰ⿯、퟿豈﷏ﷰ�𐀀󯿽 {\n"
+ "   <http://a.example/p2> .\n"
+ "}\n"
            ;
        System.out.println(cify(t));
        ShExDocParser.ShExDocContext ctx = ucs2.parseString(t);
        String l = ((ShExDocParser.InlineShapeAtomShapeOrRefContext)((ShExDocParser.ShapeAtomShapeOrRefContext)ctx.notStartAction().shapeExprDecl().shapeExpression().shapeOr().shapeAnd(0).shapeNot(0).shapeAtom()).shapeOrRef().shapeDefinition().tripleExpression().oneOfTripleExpr().groupTripleExpr().singleElementGroup().unaryTripleExpr().tripleConstraint().inlineShapeExpression().inlineShapeOr().inlineShapeAnd(0).inlineShapeNot(0).inlineShapeAtom()).inlineShapeOrRef().shapeRef().shapeExprLabel().blankNode().BLANK_NODE_LABEL().getText();
        assertTrue("should recover label", l.equals("_:AZazÀÖØöø˿ͰͽͿ῿‌‍⁰↏Ⰰ⿯、퟿豈﷏ﷰ�𐀀󯿽"));
    }

    // @Test public void t_missingToken() {
    //     Ucs2 ucs2 = new Ucs2();
    //     String t = "_:aA�𐀀󯿽 _:cd 34";
    //     try {
    //         ShExDocParser.ShExDocContext ctx = ucs2.parseString(t);
    //         assertFalse("should not parse", true);
    //     } catch (ParseCancellationException e) {
    //     }
    // }

    // @Test public void t_unknownChar() {
    //     Ucs2 ucs2 = new Ucs2();
    //     String t = "_:aA�က𐀀󯿽 _:cd 34";
    //     try {
    //         ShExDocParser.ShExDocContext ctx = ucs2.parseString(t);
    //         assertFalse("should not parse", true);
    //     } catch (ParseCancellationException e) {
    //     }
    // }

    static String uify (String s) {
        String ret = "";
        final int length = s.length();
        for (int i = 0; i < length; ++i) {
            final int sc = s.charAt(i);
            ret += String.format("%x ", sc);
        }
        return ret;
    }

    static String cify (String s) {
        String ret = "";
        final int length = s.length();
        System.out.println("length:" + length);
        for (int i = 0; i < length; ) {
            final int sc = s.codePointAt(i);
            ret += String.format("%x ", sc);
            i += Character.charCount(sc);
        }
        return ret;
    }
}

/* resources:
 *   https://github.com/graphql-java/graphql-java/blob/master/src/main/java/graphql/parser/Parser.java
 *   https://stackoverflow.com/questions/18132078/handling-errors-in-antlr4/18139305#18139305
 */