# Assignment 1 Makefile
# Gift Mugweni
# 22 February 2020

JAVAC=/usr/bin/javac
JAVADOC=/usr/bin/javadoc
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
OUTDIR=output
DOC=doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	javac -d $(BINDIR)/ -cp $(BINDIR) $<
	
CLASSES=SerialRunner.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

doc:
	javadoc -d $(DOC)/ $(SRCDIR)/*.java