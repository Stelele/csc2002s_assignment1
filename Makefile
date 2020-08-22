# Assignment 1 Makefile
# Gift Mugweni
# 22 February 2020

# The top level directory
rootdir = .

.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
OUTDIR=output
OUTDOCS=docs

$(BINDIR)/%.class:$(SRCDIR)/%.java
	javac -d $(BINDIR)/ -cp $(BINDIR) $<
	
CLASSES=Basin.class \
		SerialRunner.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

doc:
	rm $(OUTDOCS)/*
	javadoc -d $(OUTDOCS)/ $(SRCDIR)/*.java $<