

public class Driver {

	public static void main(String[] args) {
		//double c1[]= {1,2,1}, c2[] = {1,-5};
		//int e1[]= {2,1,0}, e2[] = {1,0};
	
		double c3[] = {6,2,4,5};
		int e3[] = {3,2,1,4};
		
		double c4[] = {1,2,3};
		int e4[] = {5,3,1};
		
		//Polynomial p1=new Polynomial(c1, e1);
		//Polynomial p2=new Polynomial(c2, e2);
		
		
		// Polynomial p3=p1.add(p2);
		
		Polynomial p4=new Polynomial(c3, e3);
		Polynomial p5=new Polynomial(c4, e4);
		
		Polynomial p6=p4.add(p5);
		
		//System.out.println(p3.evaluate(0));
		for(int i=0; i<p6.coeff.length; i++) {
			
			System.out.print(p6.coeff[i] + " ");		
		}
		
		System.out.println();
		
		for(int i=0; i<p6.coeff.length; i++) {
			
			System.out.print(p6.expo[i] + " ");			
		}
		
	}

}