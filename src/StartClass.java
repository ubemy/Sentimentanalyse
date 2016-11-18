
public class StartClass {

	public static void main(String[] args) {
		LexiconPolarityCalculator cal = new LexiconPolarityCalculator();
		System.out.println(cal.getPolarityForWord("agileren"));
		TestdatenEingabe te = new TestdatenEingabe();
		te.getTestData();
		te.printTestData();
		te.calcTermPolarityCounts(" die ");
	}

}
