import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class mainfile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CNF myCNF = new CNF();
		Clauses At = new Clauses("A/B/-C");
		//Clauses B = new Clauses("A/E/C");
		Clauses Bt = new Clauses("C/B");
		
		Clauses At2 = new Clauses("A/B/-C");
		//Clauses B = new Clauses("A/E/C");
		Clauses Bt2 = new Clauses("D/B/-G");
		
		Clauses At3 = new Clauses("-B/C/T");
		//Clauses B = new Clauses("A/E/C");
		Clauses Bt3 = new Clauses("-C/Z/B");
		myCNF.validate(At, Bt);
		myCNF.validate(At2, Bt2);
		myCNF.validate(At3, Bt3);
		
		// A = sun
		// B = Money
		// C = Ice
		// D = Movie
		Clauses A = new Clauses("-A/-B/C");
		//Clauses B = new Clauses("A/E/C");
		Clauses B = new Clauses("-B/C/D");

		myCNF.validate(A, B);
	
		Clauses A1 = new Clauses("-D/B");
		Clauses B1 = new Clauses("-D/-C");

		myCNF.validate(A1, B1);

		Clauses A2 = new Clauses("D");
		Clauses B2 = new Clauses("-C/Z/B");

		myCNF.validate(A2, B2);
		
		Set<Clauses> KB = new LinkedHashSet<Clauses>();
		KB.add(A); KB.add(B); KB.add(A1); KB.add(B1); KB.add(A2);// KB.add(B2);
		Iterator<Clauses> iterKB = KB.iterator();
		
		System.out.println("-------------");
		Set<Clauses> result = myCNF.Solver(KB);
		
		System.out.println("Result of Solver");
		Iterator<Clauses> iter = result.iterator();

		while(iter.hasNext()) {
			
			Clauses temp = iter.next();
			System.out.println("poss" + temp.p);
			System.out.println("neg" + temp.n);	
		}
	}

}
