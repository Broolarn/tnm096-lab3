import java.util.ArrayList; 
import java.util.List; 
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class CNF {
	 private List<Clauses> conjunctionOfClauses = new ArrayList<Clauses>(); 
	/*function Resolution(A,B) return resolvent of A and B, or false
input: clauses A and B; A and B are local variables
if A.p ∩ B.n = {} and A.n ∩ B.p = {} do
return false
if (A.p ∩ B.n) 6= {} do
a ← Pick random element from(A.p ∩ B.n)
A.p ← A.p − {a}
B.n ← B.n − {a}
otherwise
a ← Pick random element from(A.n ∩ B.p)
A.n ← A.n − {a}
B.p ← B.p − {a}
C.p ← A.p ∪ B.p
C.n ← A.n ∪ B.n
if C.p ∩ C.n 6= {} do // C is a tautology
return false
C ← Remove duplicates(C)
return C

*/
	 public static List<String> intersection( List<String> list1 ,  List<String> list2) {
	        List<String> list = new ArrayList<String>();

	        for (String t : list1) {
	            if(list2.contains(t)) {
	                list.add(t);
	            }
	        }

	        return list;
	    }
	 public static List<String> union(List<String> list1, List<String> list2) {
	        Set<String> set = new HashSet<String>();

	        set.addAll(list1);
	        set.addAll(list2);

	        return new ArrayList<String>(set);
	    }
	 // return new empty Clauses if false 
	  public static Clauses Resolution(Clauses A, Clauses B) { 
		  	
		 // .p motsvarar positiv 
		  // .n motsvarar negativ 
		  // snittet motsvarar Collections.disjoint(A.p, B.n)
		  if(Collections.disjoint(A.p, B.n) && Collections.disjoint(A.n,B.p) )
		  {  
			  return new Clauses();
		  }
		  else 
		  {
			  List<String> intersectionlist;
			  Random rn = new Random();
			  if(Collections.disjoint(A.p, B.n)) {
			   intersectionlist =  intersection(A.p, B.n);
			   int max = intersectionlist.size();	 
			   int rnslot = rn.nextInt(max  + 1) ;
			   String a = intersectionlist.get(rnslot);
			   A.p.remove(a);
			   B.n.remove(a);
			  }
			  else
			  {
				  intersectionlist =  intersection(A.n, B.p);
				   int max = intersectionlist.size();	 
				   int rnslot = rn.nextInt(max  + 1) ;
				   String a = intersectionlist.get(rnslot);
				   A.n.remove(a);
				   B.p.remove(a);
			  }
		
			
			 }
		  Clauses C = new Clauses();
		  C.p = union(A.p,B.p);
		  C.n = union(A.n,B.n);
		  if(!Collections.disjoint(C.p, C.n))
		  {
			  return new Clauses();
		  }
		  else
		  {
			  return C;
		  }
		 
	  }
}
