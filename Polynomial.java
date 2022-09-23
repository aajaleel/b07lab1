
public class Polynomial {
	double coeff[];

	  public Polynomial() {
	    coeff = new double[1];
	    coeff[0] = 0;

	  }

	  public Polynomial(double[] array) {
	      coeff = new double[array.length];
	      for (int i = 0; i < (array.length); i++)
	        coeff[i] = array[i];

	  }

	  public Polynomial add(Polynomial p) {
	    int max = Math.max(this.coeff.length,p.coeff.length);
	    int min = Math.min(this.coeff.length,p.coeff.length);
	    double[] arr = new double[max];
	    Polynomial added = new Polynomial(arr);


	    for (int i = 0; i < min; i++)
	      added.coeff[i] = this.coeff[i] + p.coeff[i];
	      
	    if (this.coeff.length<p.coeff.length){
	      for (int i = min; i<p.coeff.length; i++)
	        added.coeff[i] = p.coeff[i];
	      
	    }
	    else if (p.coeff.length<this.coeff.length){
	      for (int i = min; i<p.coeff.length; i++)
	        added.coeff[i] = p.coeff[i];
	    }
	    return added;
	  }

	  public double evaluate(double x) {

	    double total = 0;

	    for (int i = 0; i < this.coeff.length; i++)
	      total = total + this.coeff[i] * Math.pow(x, i);

	    return total;
	  }

	  public boolean hasRoot(double x) {

	    if (evaluate(x) == 0)
	      return true;

	    return false;
	  }
}
