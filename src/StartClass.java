import java.util.ArrayList;


public class StartClass {

	public static void main(String[] args) {
		UnclassifiedData ucd = new UnclassifiedData();
		LexiconPolarityCalculator cal = new LexiconPolarityCalculator();
		ucd.readFiles();
		cal.getPolarityForDocument(ucd.getListEnries());
		
		//
		//System.out.println(cal.getPolarityForWord("agileren"));
		/*TestdatenEingabe te = new TestdatenEingabe();
		te.getTestData();
		te.printTestData();
		te.calcTermPolarityCounts(" haben ");*/
		/*
		ArrayList<String> tweets = new ArrayList<String>();
        tweets.add("this is a very nice movie");
        MachineLearning.init();
        for(String tweet : tweets) {
            System.out.println(tweet + " : " + MachineLearning.findSentiment(tweet));
        }*/
	}

}
