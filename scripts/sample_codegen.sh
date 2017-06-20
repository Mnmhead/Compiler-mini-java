NAME=$1

JAVA=SamplePrograms/SampleMiniJavaPrograms/$NAME.java
S=CodeGenTests/TestAssembly/$NAME.s

java -cp build/classes:lib/java-cup-11b.jar MiniJava $JAVA > $S
gcc -Wall -g -std=c11 -o TestExecutables/$NAME $S src/boot.c
