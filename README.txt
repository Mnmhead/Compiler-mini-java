Authors: Gyorgy Wyatt Muntean, Justin Adsuara

Repository contains source code for compiler written in college course at University of Washington.
The project was to build a compiler from scratch (with the help of scanner/parser generation tools).
The compiled language is a subset of Java called MiniJava.
The language Context Free Grammar specification can be found here: http://www.cambridge.org/resources/052182060X/MCIIJ2e/grammar.htm
If the link is somehow down. There is an image in the top level directory which is a screencap of the CFG for MiniJava.

This project requires Ant, a build tool. To build, simply type 'ant'. To clean, type 'ant clean'. 
See the build.xml for more information.

The output of the compiler is x86-64 assembly sent to std out. To run the compiler type: 
java -cp build/classes:lib/java-cup-11b.jar MiniJava <name-of-src-file>

Simply capture std out to produce an executable x86-64 assembly file, exectuable (might have to use chmod u+x).

There might be some scrappy, but useful scripts in the scripts directory ease the 'running' step of our compiler.
