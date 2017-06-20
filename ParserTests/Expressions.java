class Boop {
    public static void main(String[] args) {
        System.out.println(3);
    }
}

class ManyMethods {
    public int methodNoArgs() {
	boolean b;
	int i;
	int[] arr;
	ManyMethods myMM;

	i = i + 1;
	i = i - 2;
	i = 5 * 5;
	i = i + 1 * i - 2;
	i = i * i - i * 2;

	b = true && false;
	b = i < i + 1;

	arr[i] = arr[0];
	arr[5 + i * j] = arr[2 * 2 * 2];
	
	i = this.methodNoArgs();
	i = 20202020;

	myMM = new ManyMethods();
	myMM = myMM;
	myMM = this;
	
	arr = new int[5 * 6 + i * 7 + 3 * 3 * 3];
	
	b = !b;
	b = !b && !b;
	b = !b && 3 < 5 * 4;

	b = !(b && 3 < 5 * 4);

	b = 3 < 5 * 4 && 4 + 3 * 5 < i * 6 && !b && i < i + 1;
	return 1;
    }
}
