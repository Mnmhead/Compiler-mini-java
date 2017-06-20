class Boop {
    public static void main(String[] args) {
        System.out.println(3);
    }
}

class ManyMethods {
    public int methodNoArgs() {
	int i;
	boolean b;
	int[] arr;

	{}
	
	{ i = 5; }

	{ 
	    i = 5;
	    i = i + 1;
	    b = true && false;
	}

	if (b) {
	    i = 5;
	    i = 6;
	    i = 7;
	}
	else
	    i = 6;
	
	while (b) {
	    b = false;
	}

	System.out.println(5 * 4 + 6 * 6 * 6 + 7 - 3);

	arr = new int[5];
	arr[i + 7 - 3 * 5] = 777;
	

	return 5;
    }
}
