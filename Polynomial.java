import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
class Polynomial {
	double coeff[];
	int expo[];

	  // constructor to initialize empty Polynomial
	
	  public Polynomial() {

	  }
	  
	  // constructor to initialize Polynomial with values

	  public Polynomial(double[] co, int[] exp) {
	      coeff = new double[co.length];
	      expo = new int[exp.length];
	      
	      for (int i = 0; i < (co.length); i++)
	        coeff[i] = co[i];
	      
	      for (int i = 0; i < (exp.length); i++)
		    expo[i] = exp[i];

	  }
	  
	  // method to find index of a value in an array
	  
	  int index(int val, int[] array) {
	    	for (int i = 0; i<array.length; i++) {
	    		if (val == array[i]) {
	    			return i;
	    		}
	    		
	    	}
	    	return -1;
	    }
	  
	  //method to add two polynomials

	  public Polynomial add(Polynomial p) {
	    
		 //finding number of similarities
		  
	    int counter = 0;
	    
	    for (int i = 0; i<p.expo.length; i++) {
	    	for (int j = 0; j<this.expo.length; j++) {
	    		if (p.expo[i] == this.expo[j]) 
	    			counter++;
	    	}
	    }
	    
	    //initializing new polynomial with values
	    
	    double[] co_arr = new double[this.expo.length+p.expo.length-counter];
	    int[] ex_arr = new int[this.expo.length+p.expo.length-counter];

	    Polynomial added = new Polynomial(co_arr, ex_arr);
	    
	    //adding polynomials by finding if they're in the other polynomial
	    
	    int start = 0;
	    
	    //iterate through all items in calling polynomial
	    
	    for (int i = 0; i<this.expo.length; i++) {
	    	int X = index(this.expo[i], p.expo);
			added.expo[i] = this.expo[i];
    		if (X == -1) {
    			added.coeff[i] = this.coeff[i];
    		}else if (X > -1) {
    			added.coeff[i] = this.coeff[i]+p.coeff[X];
    		}
    		start++;
	    }
	    
	    //iterate through the items in argument that don't overlap
	    
	    for (int i = 0; i <p.expo.length; i++) {
	    	if (index(p.expo[i], this.expo)==-1) {
    			added.expo[start] = p.expo[i];
    			added.coeff[start] = p.coeff[i];
    			start++;
	    	}
	    }
	   
	    return added;
	  }
	  
	  //method to multiply two polynomials
	  
	  public Polynomial multiply(Polynomial p) {
		  
		  //initializing new arrays of correct length 
		  
		  double[] co_arr = new double[this.expo.length*p.expo.length];
		  int[] ex_arr = new int[this.expo.length*p.expo.length];
		  
		  //creating two arrays representing the expanded multiplied polynomial
		  
		  int c = 0;
		  
		  for (int i = 0; i<this.expo.length; i++) {
			 for (int j = 0; j<p.expo.length; j++) {
				co_arr[c] = this.coeff[i]*p.coeff[j];
				//System.out.println(co_arr[c]);
				ex_arr[c] = this.expo[i]+p.expo[j];
				//System.out.println(ex_arr[c]);
				c++;
			 }
		  }
		  
		  //calculating the number of distinct exponents after multiplying
		  
		  int existing_exponents[] = new int[this.expo.length*p.expo.length];
		  int in_it = 0;		  
		  
		  for (int i = 0; i<existing_exponents.length; i++) {
			  if (index(ex_arr[i], existing_exponents)==-1) {
				  existing_exponents[i] = ex_arr[i];
				  in_it++;
			  }
		  }
		  
		  //initializing polynomial
		  
		  int[] end_exp = new int[in_it];
		  double[] end_coeff = new double[in_it];
		  int used_index = 0;
		  
		  for (int i = 0; i<ex_arr.length; i++) {
			  // look to see if this index has already been put into final exponent array
			  int x = index(ex_arr[i], end_exp);
			  //if it hasn't, add to final exponent array and add corresponding coefficient
			  if (x==-1) {  
				  end_exp[used_index] = ex_arr[i];
				  end_coeff[used_index] = co_arr[i];
				  used_index++;
			  //if it has, we only need to modify the coeff value at the existing index of exponent
			  }else {
				  end_coeff[x] = end_coeff[x] + co_arr[i];
			  }
		  }
		  Polynomial mx = new Polynomial(end_coeff, end_exp);
		  return mx;
	  } 
	  

	  public double evaluate(double x) {

	    double total = 0;
	    for (int i = 0; i < this.coeff.length; i++) 
	    	total = total + this.coeff[i] * Math.pow(x, this.expo[i]);

	    return total;
	  }

	  public boolean hasRoot(double x) {

	    if (evaluate(x) == 0)
	      return true;

	    return false;
	  }
	  
	  public Polynomial(File file) throws FileNotFoundException {
		  
		  //scan the one-line file

		  Scanner input = new Scanner(file);
		  String line = input.nextLine();
		  String [] polys = line.split("((?=\\-)|(\\+))");
		  
		  //count number of terms in polynomial
	
	      coeff = new double[polys.length];
	      expo = new int[polys.length];
	     for (int i = 0; i<polys.length; i++) {

		     String co_str = "";
		     int index = 0;
		     int counterr = 0;
		     for (int x = 0; x<polys[i].length(); x++) {
		    	 if (polys[i].charAt(x) == 'x') {
		    		 counterr++;		    	 }
		     }
		     
		     if (counterr==0) {
		    	 double p = Double.parseDouble(polys[i]);
	    		 coeff[i] = p;
	    		 expo[i] = 0;
		     }
		     
		     else {
		     
		    	 while(polys[i].charAt(index) != 'x') {
		    		 co_str = co_str + polys[i].charAt(index);
		    		 index++;
		    		 if (index == polys[i].length()){
		    			 break;
		    		 }
		    	 }
		    	 
		    	 
		    	 if (co_str == "" ) {
			    	 coeff[i] = 1;
		    	 
		    	 }else if (co_str == "-") {
			    	 coeff[i] = -1;
			    	 
		    	 }else {
		    		 double j = Double.parseDouble(co_str);
		    		 coeff[i] = j;
		    	 }
		    	 
		     if (index == polys[i].length()-1) {
	    		 expo[i] = 1;
	    	 }
	    	 
	    	 else {
	    		 index++;
			     String exp_str = "";
	    		 
	    		 while (index<polys[i].length()) {
	    			 exp_str = exp_str + polys[i].charAt(index);
		    		 index++;
	    		 }
	    		 
	    		 int k = Integer.parseInt(exp_str);
	    		 expo[i] = k; 
	    	 }
		     }
	    	 
	     } input.close();
	  }
	    		  
	  void saveToFile(String name) throws Exception{
		 
		  String result = "";
		  for (int i = 0; i < coeff.length; i++) {
			  String co = String.valueOf(coeff[i]);
			  if (co.charAt(0)!= '-' && i!=0) {
				  result+='+';
			  }
			  
			  if (expo[i]==0) {
				  result+=co;	  
				  
			  }else {
				  result += co;
				  result += 'x';
				  result += String.valueOf(expo[i]);
			  }  
		  }
		  
		  PrintStream out = new PrintStream(name);
		  out.println(result);
		  out.close(); 		  
	  }
}



