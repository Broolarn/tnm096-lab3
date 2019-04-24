
public class mainfile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CNF myCNF = new CNF();
		
		Clauses A = new Clauses("A/B/-C");
		Clauses B = new Clauses("C/B");

		myCNF.validate(A, B);
	
		Clauses A1 = new Clauses("A/B/-C");
		Clauses B1 = new Clauses("D/B/-G");

		myCNF.validate(A1, B1);

		Clauses A2 = new Clauses("-B/C/-T");
		Clauses B2 = new Clauses("-C/Z/B");

		myCNF.validate(A2, B2);
		

	}

}
