import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class CNF {
	// private List<Clauses> conjunctionOfClauses = new ArrayList<Clauses>();
	/*
	 * function Resolution(A,B) return resolvent of A and B, or false input: clauses
	 * A and B; A and B are local variables if A.p âˆ© B.n = {} and A.n âˆ© B.p = {}
	 * do return false if (A.p âˆ© B.n) 6= {} do a â†� Pick random element from(A.p
	 * âˆ© B.n) A.p â†� A.p âˆ’ {a} B.n â†� B.n âˆ’ {a} otherwise a â†� Pick random
	 * element from(A.n âˆ© B.p) A.n â†� A.n âˆ’ {a} B.p â†� B.p âˆ’ {a} C.p â†� A.p
	 * âˆª B.p C.n â†� A.n âˆª B.n if C.p âˆ© C.n 6= {} do // C is a tautology
	 * return false C â†� Remove duplicates(C) return C
	 * 
	 */
	public static List<String> intersection(List<String> list1, List<String> list2) {
		List<String> list = new ArrayList<String>();

		for (String t : list1) {
			if (list2.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}

	public static List<String> union(List<String> list1, List<String> list2) {
		Set<String> set = new LinkedHashSet<String>();

		set.addAll(list1);
		set.addAll(list2);

		return new ArrayList<String>(set);
	}

	// return new empty Clauses if false
	public static Clauses Resolution(Clauses Ain, Clauses Bin) {

		// .p motsvarar positiv
		// .n motsvarar negativ
		// snittet motsvarar Collections.disjoint(A.p, B.n)
		Clauses A = new Clauses();
		A.n = new ArrayList<String>(Ain.n);
		A.p = new ArrayList<String>(Ain.p);
		Clauses B = new Clauses();		
		B.n = new ArrayList<String>(Bin.n);
		B.p = new ArrayList<String>(Bin.p);
		if (Collections.disjoint(A.p, B.n) && Collections.disjoint(A.n, B.p)) {
			//System.out.println("null");
			return new Clauses();
		} else {
			List<String> intersectionlist;
			Random rn = new Random();
			if (!Collections.disjoint(A.p, B.n)) {
				intersectionlist = intersection(A.p, B.n);
				int max = intersectionlist.size();

				int rnslot = rn.nextInt(max);
				String a = intersectionlist.get(rnslot);
				
				A.p.remove(a);
				B.n.remove(a);
			} else {
				intersectionlist = intersection(A.n, B.p);
				int max = intersectionlist.size();
				int rnslot = rn.nextInt(max);

				String a = intersectionlist.get(rnslot);
				A.n.remove(a);
				B.p.remove(a);
			}

		}
		Clauses C = new Clauses();
		C.p = union(A.p, B.p);
		C.n = union(A.n, B.n);
		if (!Collections.disjoint(C.p, C.n)) {
			return new Clauses();
		} else {
			Collections.sort(C.p);
			Collections.sort(C.n);
			return C;
		}

	}

	public void validate(Clauses A, Clauses B) {
		System.out.println("Result of validation");

		System.out.println("poss" + A.p);
		System.out.println("neg" + A.n);

		System.out.println("poss" + B.p);
		System.out.println("neg" + B.n);
		// CNF myCNF = new CNF();
		Clauses result = Resolution(A, B);

		if (result.p == null) {
			System.out.println("Result false \n");
		} else
			System.out.println("result" + result.p + "\n");
	}

	public static Set<Clauses> Solver(Set<Clauses> KB) {
		Set<Clauses> KBPrim;
		Set<Clauses> S ;
		Set<Clauses> KBClone = new LinkedHashSet<Clauses> (KB);
		Integer counter = 0;
		boolean same = true;
		
		do {
			S = new LinkedHashSet<Clauses>();
			S.clear();
			same = true;
			
			KBPrim = new LinkedHashSet<>(KB);
			System.out.println("equals  ?? " + KBPrim.equals(KB));
			
			Iterator<Clauses> it = KB.iterator();
			Clauses tempA;
			
			Clauses tempB;
			
			while (it.hasNext()) {
				
				tempA = it.next();
				
				Clauses tempcopyA = tempA;
				
				Iterator<Clauses> it2 = KBClone.iterator();
				
				while(it2.hasNext())
				{
					tempB = it2.next();
					
					Clauses tempcopyB = tempB;
					
					Clauses C = Resolution(tempcopyA, tempcopyB);
					

					if( (C.p != null ))
					{
						Iterator<Clauses> setit = S.iterator();
						boolean found = false;
						while(setit.hasNext())
						{
							Clauses curr = setit.next();
							if(curr.equals(C))
							{
								found = true;
								break;
							}
						}
					
						if(found == false)
							S.add(C);
					}
						
				}
				
			}
			if (S.isEmpty())
				return KB;
			
			Iterator<Clauses> iter = S.iterator();
			System.out.println("it print");
			while(iter.hasNext()) {
				
				Clauses tempp = iter.next();
				System.out.println("Sp" + tempp.p);
				System.out.println("Sn" + tempp.n);	
			}
			

			KB = Incorporate(S, KB);
			S.clear();
			Iterator<Clauses> iter2 = KB.iterator();

			while(iter2.hasNext()) {
				
				Clauses temppp = iter2.next();
				System.out.println("KBp" + temppp.p);
				System.out.println("KBn" + temppp.n);	
			}
			System.out.println("looop" + counter);
			counter++;
			
			Iterator<Clauses> iter56 = KB.iterator();
			Iterator<Clauses> iter57 = KBPrim.iterator();
			
			if(KB.size() == KBPrim.size())
				while(iter56.hasNext())
				{
					if(!iter56.next().equals(iter57.next()))
					{
						same = false;
					}
				}
			
			else
				same = false;
			
		} while (same == false);
		
		return KB;
	}

	private static Set<Clauses> Incorporate(Set<Clauses> S, Set<Clauses> KB) {

		Iterator<Clauses> it = S.iterator();
		Set<Clauses> KBcopy  = new LinkedHashSet<>(KB);
		while (it.hasNext()) {
			KB = Incorporate_clause(it.next(), KBcopy);
		}

		return KB;
	}

	private static Set<Clauses> Incorporate_clause(Clauses A, Set<Clauses> KB) {
		

		Set<Clauses> KBClone = new LinkedHashSet<Clauses> (KB);
		Iterator<Clauses> it = KB.iterator();
		
		boolean B_A = false;
		while (it.hasNext()) {
			B_A = true;
			Clauses B = it.next();

			
			if(A.equals(B))
			{
				B_A = false;
			
			}
			for (int i = 0; i < B.p.size(); ++i) {
				if (!A.p.contains(B.p.get(i))) {
					B_A = false;
					break;
				}
			}
			if (B_A==true) {
				for (int i = 0; i < B.n.size(); ++i) {
					if (!A.n.contains(B.n.get(i))) {
						B_A = false;
						break;
					}
				}
			}
			
			if (B_A==true)
			{
				return KB;
			}
		}

		
		Iterator<Clauses> it2 = KBClone.iterator();
		while (it2.hasNext()) {
			boolean A_B = true;
			Clauses B = it2.next();
			
			for (int i = 0; i < A.p.size(); ++i) {
				if (!B.p.contains(A.p.get(i))) {
					
					A_B = false;
					break;
				}
			}
			if (A_B == true) {
				for (int i = 0; i < A.n.size(); ++i) {
					if (!B.n.contains(A.n.get(i))) {
						A_B = false;
						break;
					}
				}
				
			}
			if (A_B==true)
			{
				KB.remove(B);
			}

		
		}
		KB.add(A);

		return KB;
	}

	
}
