
import java.util.ArrayList; 
import java.util.List; 

public class Clauses {
 
	 private List<Clauses> conjunctionOfClauses = new ArrayList<Clauses>(); 
	
	 public Clauses() {
		 
	 }
	 public  List<String> p;
	 public  List<String> n;
	 public Clauses(String instring) {
		p = new ArrayList<>();
		n = new ArrayList<>();
		
		 // / motsvar union
		// & motsvarar snitt
		// - motsvar neg
		 // "A/B&-c&-d"
		 String trimstring = instring.trim();
		
		 String Symbols="";
		 
		 for(int i = 0 ; i < trimstring.length(); i++)
		 {
			 
			 char temp = trimstring.charAt(i);
			 //System.out.println(temp);
			 if(Character.isLetter(temp))
			 {
				 if(i != 0)
				 {
					 if(trimstring.charAt(i-1) == '-')
					 {
						 n.add(String.valueOf(temp));
					 }
					 else
					 {
						 p.add(String.valueOf(temp));
					 }
				 }
				 else {
					 p.add(String.valueOf(temp)); 
				 }
				
			 }
			 else
			 {
				
				 Symbols = Symbols +temp;
			 }
		 }
		 
	 }

	// @Override
	 public boolean equals(Clauses B) {
		// System.out.println("EQUALS");
		 if(p.size() != B.p.size() || n.size() != B.n.size())
			 return false;
		 
		 for(int i = 0; i < p.size(); ++i)
		 {
			 if(p.get(i) != B.p.get(i))
				 return false;
		 }
		 
		 for(int i = 0; i < n.size(); ++i)
		 {
			 if(n.get(i) != B.n.get(i))
				 return false;
		 }
		 return true;
		 
		 /*Clauses rhs = (Clauses) B;
	     return (this.p == rhs.p && this.n == rhs.n);
	 */
		 }
	 
	 /*@Override
	    public int hashCode() 
	    { 
	          
	        // We are returning the Geek_id  
	        // as a hashcode value. 
	        // we can also return some  
	        // other calculated value or may 
	        // be memory address of the  
	        // Object on which it is invoked.  
	        // it depends on how you implement  
	        // hashCode() method. 
	        return this.p; 
	    }
	   */ 
}
