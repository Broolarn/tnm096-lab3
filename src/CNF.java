import java.util.ArrayList; 
import java.util.List; 
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class CNF {
	// private List<Clauses> conjunctionOfClauses = new ArrayList<Clauses>(); 
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
			  if(!Collections.disjoint(A.p, B.n)) {
			   intersectionlist =  intersection(A.p, B.n);
			   int max = intersectionlist.size();	
			  
			   int rnslot = rn.nextInt(max ) ;
			   String a = intersectionlist.get(rnslot);
			   A.p.remove(a);
			   B.n.remove(a);
			  }
			  else
			  {
				  intersectionlist =  intersection(A.n, B.p);
				   int max = intersectionlist.size();	 
				   int rnslot = rn.nextInt(max) ;
				   
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
	  
	  public void validate(Clauses A, Clauses B)
	  {
		  	System.out.println("Result of validation");

		  	System.out.println("poss" + A.p);
			System.out.println("neg" + A.n);
			
			System.out.println("poss" + B.p);
			System.out.println("neg" + B.n);
			//CNF myCNF = new CNF();
			Clauses result = Resolution( A, B);
			
			if(result.p == null)
			{
				System.out.println("Result false \n");
			}
			else
				System.out.println("result" + result.p + "\n");
	  }
	  public static Set<Clauses> Solver(Set<Clauses> KB)
	  {
		  // Loop n shit
		  
		  return null;
		  
	  }
	  
	  public static Set<Clauses> reduce(Set<Clauses> oldKB)
	  {
		  Set<Clauses> S = new HashSet<Clauses>();
		  Set<Clauses> KBPrim = oldKB;
		  List<Clauses> Clauselist = new ArrayList<Clauses>(oldKB);
		  
		  for(int i = 0 ; i < Clauselist.size(); i  = i+2)
		  {
			  if(i +1 > Clauselist.size())
			  {
				  S.add(Clauselist.get(i));
				  break;
				  // break 
			  }
			  Clauses C = Resolution(Clauselist.get(i),Clauselist.get(i+1));
			  S.add(C);
		  
		  }
		  
		  return S;
	  }
	  
	/*  function Solver(KB) return set of clauses
			  Input: set of clauses KB
			  repeat
			  S = {}
			  KB0 ← KB
			  for each A, B in KB :
			  C ← Resolution(A,B)
			  if C 6= false do
			  S ← S ∪ {C}
			  if S = {}
			  return KB
			  KB ← Incorporate(S, KB)
			  until KB0 = KB*/
}
