import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class ThresholdErrorListener extends BaseErrorListener {

    java.util.List<String> errors = new java.util.ArrayList<>();
    int threshold;

    public ThresholdErrorListener () { threshold = 100; }
    public ThresholdErrorListener (int threshold_p) { threshold = threshold_p; }

    public Exception getException () {
        if (errors.size() == 0)
            return null;
        String joined = "";
        for (String s : errors) {
            if (joined.length() > 0)
                joined += "\n";
            joined += s + "\t";
        }
        return new Exception(joined);
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
        throws ParseCancellationException {
        // throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
        errors.add("line " + line + ":" + charPositionInLine + " " + msg);
        if (errors.size() > threshold) {
            throw new ParseCancellationException("more than " + threshold + " errors: " + getException());
        }
    }
}
