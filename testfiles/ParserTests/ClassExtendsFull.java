class Boop {
    public static void main(String[] args) {
        System.out.println(3);
    }
}

class Extra {
    int i;
    boolean b;
    Extra e;

    public int a() {
        return 1;
    }

    public Extra m2() {
	return null;
    }

    public Extra m3() {
	return null;
    }
}

class ExtraSub extends Extra {
    boolean superfluous;
    int redundant;

    public Extra m2() {
	return new ExtraSub();
    }

    public Extra m3() {
	return new ExtraSub();
    }
}
