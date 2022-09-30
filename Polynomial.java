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
				ex_arr[c] = this.expo[i]+p.expo[j];
				c++;
			 }
		  }
		  
		  //calculating the number of distinct exponents after multiplying
		  
		  
		  int copy[] = new int[this.expo.length*p.expo.length];
		  int in_it = 0;		  
		  
		  for (int i = 0; i<ex_arr.length; i++) {
			  if (index(ex_arr[i], copy)==-1) {
				  copy[in_it] = ex_arr[i];
				  in_it++;
			  }
		  }
		  
		  
		  //initializing polynomial
		  
		  int[] end_exp = new int[in_it];
		  double[] end_coeff = new double[in_it];

		  int done = 0;
		  int used[] = new int[ex_arr.length];
		  
		  for (int i = 0; i<ex_arr.length; i++) {
			  used[i] = ex_arr[i];
			  for (int j = i+1; j<ex_arr.length; j++) {
				  if (index(ex_arr[i], used)==-1) {
					  if (ex_arr[i] == ex_arr[j]) {
						  end_exp[done] = ex_arr[i];
						  end_coeff[done] = co_arr[i] + co_arr[j];
						  done++;
					  }
				  }
			  }
		  }
		  
		  Polynomial mx = new Polynomial(end_coeff, end_exp);
		  return mx;

	  } 
	  
	  // is using this. okay here?

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
		  
		  //count number of terms in polynomial
		  int num = 1;
		  
		  for (int i = 0; i<line.length(); i++) {
			  if (line.charAt(i) == '+' || line.charAt(i) == '-') {
				  num++;
			  }
		  }
		  
		  //
		  
	      coeff = new double[num];
	      expo = new int[num];
	      //String s = "";
	      
	      
	     int index = 0;
	     
	     for (int i = 0; i<num; i++) {
	    	 
	    	 //add coefficient value to array

		     String co_str = "";

	    	 while(line.charAt(index) != 'x') {
	    		 co_str = co_str + line.charAt(index);
	    		 index++;
	    	 }
	    	 double j = Double.parseDouble(co_str);
	    	 coeff[i] = j;
	    	 
	    	 //add exponent value to array
	    	 
	    	 index++; //skip over the 'x'
	    	 
		     String exp_str = "";

	    	 while(line.charAt(index) != '+' && line.charAt(index) != '-') {
	    		 exp_str = exp_str + line.charAt(index);
	    		 index++;
	    	 }
	    	 int k = Integer.parseInt(exp_str);
	    	 expo[i] = k; 
	    	 index++;
	     }
	     
	     input.close();
	  }
	  
	  
	  void saveToFile(String name) throws Exception{
		  
		  if (this.coeff.length == 0) {
			  return;
		  }
		  
		  String result = ""; 
		  
		  for (int i = 0; i < this.coeff.length; i++) {
			  result += String.valueOf(this.coeff[i]);
			  result += 'x';
			  result += String.valueOf(this.expo[i]);
		  }
		  
		  PrintStream out = new PrintStream(name);
		  out.println(result);
		  out.close();
		  		  
	  }
	  
	  
}

