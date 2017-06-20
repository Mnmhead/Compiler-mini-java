class Factorial{
    public static void main(String[] a){
	System.out.println(new Fac().ComputeFac(10));
    }


   /* oh yes */

   // comment comment

}

class Fac {

   /* oh no
   *
   *
    *a0870t T(^& TA(*)&DG) A*FD)&^AS)*(R%*%&*)&(PHJBDASYP(*HIOLSKD:ADJA"DASLKDNAS" N"AJDAPOS}ASDKO}ASPO CD}PAJ D{)( QHWP GI&^ F*~^*~G!@)*&~Y!@)(~U` [0`9 u[21j`op1[`po3j';ewh

//////////
//
//
//
///ds
//f/as
//f/
//a4 rra/ 4/r//4fw jtj/w4j ? (UE(!!@$?!@?$??/1//1/42 1up97(P?/?/1/4/12o4 / 
//****************** / * / * /* /* 98***) (updnUYP(* Ydp9
   * ^
*/

    public int ComputeFac(int num){
	int num_aux ;
	if (num < 1)
	    num_aux = 1 ;
	else 
	    num_aux = num * (this.ComputeFac(num-1)) ;
	return num_aux ;
    }

}
