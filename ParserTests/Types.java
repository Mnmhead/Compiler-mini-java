// tests Types

class Boop {
    public static void main(String[] args) {
        System.out.println(3);
    }
}

class ManyMethods {
    int[] myArray;
    int myInt;
    boolean myBooBool;
    ManyMethods myMM;
    InvalidClassName myICN;
    
    public boolean returnBool() {
	return false;
    }

    public int[] returnArray() {
	return new int[5];
    }

    public int returnInt() {
	return 5;
    }

    public ManyMethods m() {
	return new ManyMethods();
    }
}
