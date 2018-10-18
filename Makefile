ANTLRDIR=downloads
ANTLRVER=4.7.1
ANTLRJARFILE=antlr-$(ANTLRVER)-complete.jar
ANTLRJAR=$(ANTLRDIR)/$(ANTLRJARFILE)

all: jst

#### download antlr jar ####
$(ANTLRJAR): Makefile
	wget -O $@ http://www.antlr.org/download/$(ANTLRJARFILE)

ajar: $(ANTLRJAR)

ANTLR?=java -cp $(ANTLRJAR) org.antlr.v4.Tool


#### javascript target ####
JSGEN=javascript/generated-src
JSSRC=javascript/src
JSPARSER=$(JSGEN)/Ucs2Parser.js

$(JSPARSER): Ucs2.g4
	$(ANTLR) -o $(JSGEN) -Dlanguage=JavaScript Ucs2.g4

jstoy: $(JSPARSER)
	node $(JSSRC)/run.js FFFD.txt
	node $(JSSRC)/run.js 10000.txt

jst: $(JSPARSER)
	npx jest


#### python target ####
PYLIB=python/generated-src
PYSRC=python/src
PYPARSER=$(PYLIB)/Ucs2Parser.py

$(PYPARSER): Ucs2.g4
	$(ANTLR) -o $(PYLIB) -Dlanguage=Python3 Ucs2.g4

pytoy: $(PYPARSER)
	PYTHONPATH=$(PYSRC):$(PYLIB) python $(PYSRC)/run.py FFFD.txt
	PYTHONPATH=$(PYSRC):$(PYLIB) python $(PYSRC)/run.py 10000.txt

pyt: $(PYPARSER)
	PYTHONPATH=$(PYSRC):$(PYLIB) python3 python/test/Ucs2Tests.py


#### java target ####
JAVAPARSER=generated-src/antlr/main/me/ericprud/examples/ucs2/Ucs2Parser.java

$(JAVAPARSER): Ucs2.g4
	./gradlew generateGrammarSource

javat: $(JAVAPARSER)
	gradle test

