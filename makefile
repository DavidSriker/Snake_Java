JFLAGS = -g
JC = javac
JVM = java

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  Snake/SnakeLogic.java \
		  Snake/GameRenderer.java \

MAIN = Snake/SnakeLogic

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) Snake/*.class

run:
	$(JVM) $(MAIN)
