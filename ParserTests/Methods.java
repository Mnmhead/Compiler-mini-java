// tests method declarations (num of arguments), method calls, method body

class Boop {
    public static void main(String[] args) {
        System.out.println(3);
    }
}

class ManyMethods {
    public int methodNoArgs() {
	return 1;
    }

    public int methodOneArg(int a) {
	return a;
    }

    public ManyMethods methodTwoArgs(int foo, ManyMethods b) {
	return null;
    }

    public int MethodManyArgs(ManyMethods mmoo,
			      ManyMethods nnoo,
			      int myX,
			      int myB) {
	return 1 + 1;
    }

    public int callsNoArg() {
	return this.methodNoArgs();
    }

    public int callsOneArg() {
	return this.methodOneArg(5);
    }

    public ManyMethods callsTwoArgs() {
	return this.methodTwoArgs(5, new ManyMethods());
    }

    public int callsManyArgs() {
	return this.methodManyArgs(null, null, 2, 3);
    }

    public int oneStatement(int a) {
	a = 5;
	return a;
    }

    public int manyStatements(int a) {
	a = 5;
	a = 6;
	return a;
    }

    public int fullMethod(int a, ManyMethods m) {
	int c;
	boolean d;
	c = a + 7;
	c = c + m.manyStatements(5);
	d = false;
	return 5;
    }
}
