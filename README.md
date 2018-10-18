# Antlr Multi-language template

This shows how to use Antlr to create parsers for multiple languages.
Recent changes to Antlr allow it to recognize codepoints in the form `\u{10000}`, enabling it to generage lexers to recognizes symbols outside the Basic Multilingual Plane (BMP).
Antlr's old `ANTLRStream` yields characters in [supplementary planes](https://en.wikipedia.org/wiki/Plane_(Unicode)#Supplementary_Multilingual_Plane) as [surrogate pairs](https://en.wikipedia.org/wiki/UTF-16#U.2B010000_to_U.2B10FFFF), requiring terminals to be defined as surrogate pairs, e.g. `[\uD800-\uDB7F][\uDC00-\uDFFF]`.
Now that Antlr recognizes the `\{xxxxx}` idiom, character (ranges) in supplementary planes can be expressed directly, e.g. `[\u{10000}-\u{EFFFD}]`.
Many Antlr examples use the old UTF-16 streams so be sure to look for `CharStream` or something like it.

## Languages

Apart from Java, each language appears in its own directory.
The `isolate-java` branch starts this for java but gradle no longer runs the tests.

### Javascript
```
npm install
make jst
```

### Java

```
gradle wrapper --gradle-version 3.4
make javat
```

### Python

If you're not already running python 3:
```
virtualenv --python=python3 pyenv
. ./pyenv/bin/activate
```

```
pip install antlr4-python3-runtime
make pyt
```

