import java.io.File;

public class Driver {

	public static void main(String[] args) throws Exception {

		// ADD CHECK
		
		double add_A_c[] = {-3,4,-5,6, 2, 1};
		int add_A_e[] = {0,1,2,3,4, 5};
		
		double add_B_c[] = {-3,4,-5,6, 7};
		int add_B_e[] = {3,2,1,0, 7};
		
		Polynomial add_A = new Polynomial(add_A_c, add_A_e);
		Polynomial add_B = new Polynomial(add_B_c, add_B_e);
		
		Polynomial added_AB = add_A.add(add_B);
		
		
		for(int i=0; i<added_AB.coeff.length; i++) {
			
			System.out.print(added_AB.coeff[i] + " ");		
		}
		
		System.out.println();
		
		for(int i=0; i<added_AB.coeff.length; i++) {
			
			System.out.print(added_AB.expo[i] + " ");			
		}
		
		// SECOND ADD CHECK
		
		
		System.out.println();
		System.out.println();
		System.out.println();

		
		
		//check multiply 
		
		double multiplyAc[] = {-3,4,-5,1, 10};
		int multiplyAe[] = {3,1,2,5, 0};
		
		double multiplyBc[] = {-3,4,7};
		int multiplyBe[] = {3,2,1};
		
		Polynomial multiplyA = new Polynomial(multiplyAc, multiplyAe);
		Polynomial multiplyB = new Polynomial(multiplyBc, multiplyBe);

		
		Polynomial multiply_AB = multiplyA.multiply(multiplyB);
		
		
		for(int i=0; i<multiply_AB.coeff.length; i++) {
			
			System.out.print(multiply_AB.coeff[i] + " ");		
		}
		
		System.out.println();
		
		for(int i=0; i<multiply_AB.coeff.length; i++) {
			
			System.out.print(multiply_AB.expo[i] + " ");			
		}
		
		
		// check evaluate 
		
		double eAc[] = {-3,4,-5,6, 2, 1};
		int eAe[] = {10,1,2,3,4, 5};
		
		double eBc[] = {-3,4,-5,6, 7};
		int eBe[] = {3,0,1,10, 7};
		
		
		Polynomial evA = new Polynomial(eAc, eAe);
		Polynomial evB = new Polynomial(eBc, eBe);

		System.out.println();
		System.out.println();

		System.out.println(evA.evaluate(1) + " should result in 5.0");
		System.out.println(evA.evaluate(2) + " should result in -2972.0");
		System.out.println(evA.evaluate(-1) + " should result in -17.0");
		
		System.out.println(multiply_AB.evaluate(0) + " should result in 0.0");
		
		// check root
		
		if (evB.hasRoot(2)) {
			System.out.println("evB has root 2");
		}else {
			System.out.println("evB does not have root 2");
		}
		
		
		if (evA.hasRoot(1)) {
			System.out.println("evB has root 1");
		}else {
			System.out.println("evB does not have root 1");
		}
		
		if (multiply_AB.hasRoot(-1)) {
			System.out.println("multiply_AB has root -1");
		}else {
			System.out.println("evB does not have root -1");
		}
		
		
		double rooT[] = {1};
		int root[] = {2};
		Polynomial roots = new Polynomial (rooT, root);
		if (roots.hasRoot(0)) {
			System.out.println("roots has root 0");
		}else {
			System.out.println("roots does not have root 0");
		}
		
		
			
		 File file = new File("/Users/aaliyahjaleel/eclipse-workspace/BP7LAB2/new.txt");

	        Polynomial ftest = new Polynomial(file);
	        
	        System.out.println("TESTING");
	        for(int i=0; i<ftest.coeff.length;i++)
	        {
	            System.out.println(ftest.coeff[i]+"\t"+ftest.expo[i]);
	        }
	        ftest.saveToFile("do.txt");
	        double testa[] = {-5,1,-6,1};
	        int testb[] = {1,2,3,0};
	        Polynomial check = new Polynomial(testa,testb);
	        check.saveToFile("test.txt");
	
		
		
	}
	
	

}