
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

	 
}
