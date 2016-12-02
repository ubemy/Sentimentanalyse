import java.util.ArrayList;


public class StartClass {

	public static void main(String[] args) {
		LexiconPolarityCalculator cal = new LexiconPolarityCalculator();
		System.out.println(cal.getPolarityForWord("agileren"));
		TestdatenEingabe te = new TestdatenEingabe();
		te.getTestData();
		te.printTestData();
		te.calcTermPolarityCounts(" haben ");
		/*
		ArrayList<String> tweets = new ArrayList<String>();
        tweets.add("this is a very nice movie");
        MachineLearning.init();
        for(String tweet : tweets) {
            System.out.println(tweet + " : " + MachineLearning.findSentiment(tweet));
        }*/
	}

}
